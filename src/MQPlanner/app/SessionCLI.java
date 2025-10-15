package MQPlanner.app;

import MQPlanner.models.StudySession;
import MQPlanner.models.Subject;
import MQPlanner.services.StudySessionService;
import MQPlanner.services.SubjectService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * CLI class to interact with StudySessionService.
 * Allows logging sessions, listing sessions, calculating study time, and removing sessions.
 */
public class SessionCLI {

    private final StudySessionService sessionService;
    private final SubjectService subjectService;
    private final Scanner scanner;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public SessionCLI(StudySessionService sessionService, SubjectService subjectService) {
        this.sessionService = sessionService;
        this.subjectService = subjectService;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Start the CLI loop for Study Sessions
     */
    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n--- Study Session CLI ---");
            System.out.println("1. Log new session");
            System.out.println("2. List all sessions");
            System.out.println("3. List sessions by subject");
            System.out.println("4. Show total study time for a subject");
            System.out.println("5. Show total study time overall");
            System.out.println("6. Remove a session");
            System.out.println("0. Back to main menu");
            System.out.print("Select an option: ");

            String input = scanner.nextLine().trim();
            switch (input) {
                case "1" -> logSession();
                case "2" -> listAllSessions();
                case "3" -> listSessionsBySubject();
                case "4" -> showTotalTimeBySubject();
                case "5" -> showTotalTimeOverall();
                case "6" -> removeSession();
                case "0" -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void logSession() {
        System.out.println("\n Add New Study Session");

        // Select subject
        List<Subject> subjects = subjectService.getAllSubjects();
        if (subjects.isEmpty()) {
            System.out.println("No subjects found. Please add subjects first.");
            return;
        }
        System.out.println("Select a subject:");
        for (int i = 0; i < subjects.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, subjects.get(i).getSubjectName());
        }

        int subjectIndex = readIntInRange(1, subjects.size()) - 1;
        Subject subject = subjects.get(subjectIndex);

        // Session date
        LocalDate date = null;
        while (date == null) {
            System.out.print("Enter session date [YYYY-MM-DD]  ");
            String dateInput = scanner.nextLine().trim();
            try {
                date = LocalDate.parse(dateInput, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format.");
            }
        }

        // Duration
        System.out.print("Enter duration in minutes ");
        int duration = readPositiveInt();

        // Description
        System.out.print("Enter session description ");
        String description = scanner.nextLine().trim();

        StudySession session = new StudySession(subject, date, duration, description);
        sessionService.logSession(session);
        System.out.println("Session logged successfully ");
    }

    private void listAllSessions() {
        System.out.println("\n  Study Sessions ");
        List<StudySession> sessions = sessionService.getAllSessions();
        if (sessions.isEmpty()) {
            System.out.println("No study sessions found.");
            return;
        }

        for (int i = 0; i < sessions.size(); i++) {
            StudySession s = sessions.get(i);
            System.out.printf("%d. %s | Date: %s | Duration: %d min | Description: %s\n",
                    i + 1,
                    s.getSubject().getSubjectName(),
                    s.getSessionDate(),
                    s.getDurationMinutes(),
                    s.getDescription());
        }
    }

    private void listSessionsBySubject() {
        System.out.println("\n  Sessions by Subject ");
        Subject subject = selectSubject();
        if (subject == null) return;

        List<StudySession> sessions = sessionService.getSessionsBySubject(subject.getSubjectName());
        if (sessions.isEmpty()) {
            System.out.println("No sessions found for this subject.");
            return;
        }

        for (StudySession s : sessions) {
            System.out.printf("Date: %s | Duration: %d min | Description: %s\n",
                    s.getSessionDate(),
                    s.getDurationMinutes(),
                    s.getDescription());
        }
    }

    private void showTotalTimeBySubject() {
        System.out.println("\n Total Study Time by Subject ");
        Subject subject = selectSubject();
        if (subject == null) return;

        int total = sessionService.getTotalStudyTime(subject.getSubjectName());
        System.out.printf("Total study time for %s: %d minutes\n", subject.getSubjectName(), total);
    }

    private void showTotalTimeOverall() {
        int total = sessionService.getTotalStudyTime();
        System.out.printf("\nTotal study time across all subjects: %d minutes\n", total);
    }

    private void removeSession() {
        System.out.println("\n Remove a Study Session ");
        List<StudySession> sessions = sessionService.getAllSessions();
        if (sessions.isEmpty()) {
            System.out.println("No sessions to remove.");
            return;
        }

        for (int i = 0; i < sessions.size(); i++) {
            StudySession s = sessions.get(i);
            System.out.printf("%d. %s | Date: %s | Duration: %d min | Description: %s\n",
                    i + 1,
                    s.getSubject().getSubjectName(),
                    s.getSessionDate(),
                    s.getDurationMinutes(),
                    s.getDescription());
        }

        System.out.print("Select session to remove ");
        int index = readIntInRange(1, sessions.size()) - 1;

        if (sessionService.removeSession(index)) {
            System.out.println("Session removed successfully.");
        } else {
            System.out.println("Failed to remove session.");
        }
    }


    private Subject selectSubject() {
        List<Subject> subjects = subjectService.getAllSubjects();
        if (subjects.isEmpty()) {
            System.out.println("No subjects found. Please add subjects first.");
            return null;
        }

        System.out.println("Select a subject:");
        for (int i = 0; i < subjects.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, subjects.get(i).getSubjectName());
        }
        int index = readIntInRange(1, subjects.size()) - 1;
        return subjects.get(index);
    }

    private int readIntInRange(int min, int max) {
        while (true) {
            try {
                int val = Integer.parseInt(scanner.nextLine().trim());
                if (val >= min && val <= max) return val;
                System.out.printf("Enter a number between %d and %d.\n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
            }
        }
    }

    private int readPositiveInt() {
        while (true) {
            try {
                int val = Integer.parseInt(scanner.nextLine().trim());
                if (val > 0) return val;
                System.out.println("Enter a positive number.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
            }
        }
    }
}
