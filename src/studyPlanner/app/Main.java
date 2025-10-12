package studyPlanner.app;

import studyPlanner.models.Task;
import studyPlanner.models.Subject;
import studyPlanner.models.StudySession;

import studyPlanner.services.SubjectService;
import studyPlanner.services.TaskService;
import studyPlanner.services.StudySessionService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final SubjectService subjectService = new SubjectService();
    private static final TaskService taskService = new TaskService();
    private static final StudySessionService sessionService = new StudySessionService();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addSubjectCLI();
                case 2 -> addTaskCLI();
                case 3 -> logStudySessionCLI();
                case 4 -> displaySubjects();
                case 5 -> displayTasks();
                case 6 -> displaySessions();
                case 7 -> markTaskCompleteCLI();
                case 8 -> showTotalStudyTimeCLI();
                case 9 -> showRemindersCLI();
                case 0 -> {
                    running = false;
                    System.out.println("Exiting StudyPlanner. Goodbye!");
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n=== CLI StudyPlanner ===");
        System.out.println("1. Add Subject");
        System.out.println("2. Add Task");
        System.out.println("3. Log Study Session");
        System.out.println("4. View All Subjects");
        System.out.println("5. View All Tasks");
        System.out.println("6. View Study Sessions");
        System.out.println("7. Mark Task Complete");
        System.out.println("8. Show Total Study Time for Subject");
        System.out.println("9. Show Tasks with Reminders Due");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addSubjectCLI() {
        System.out.print("Enter Subject ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Subject Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Subject Duration: ");
        String duration = scanner.nextLine();

        ArrayList<Subject> prerequisites;

        Subject newSubject = new Subject(id, name, duration, new ArrayList<>() , new ArrayList<>());
        subjectService.addSubject(newSubject);
        System.out.println("Subject added successfully!");
    }

    private static void addTaskCLI() {
        System.out.print("Enter Task ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Task Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Due Date (YYYY-MM-DD): ");
        LocalDate due = LocalDate.parse(scanner.nextLine());
        System.out.print("Set Reminder? (true/false): ");
        boolean reminder = Boolean.parseBoolean(scanner.nextLine());
        System.out.print("Enter Priority (LOW, MEDIUM, HIGH): ");
        Task.Priority priority = Task.Priority.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Enter Subject ID for this Task: ");
        int subjId = Integer.parseInt(scanner.nextLine());
        Subject subj = subjectService.getSubjectById(subjId);

        if (subj == null) {
            System.out.println("Subject not found. Task not added.");
            return;
        }

        Task task = new Task(id, name, reminder, due, subj, priority, Task.Status.PENDING);
        taskService.addTask(task);
        System.out.println("Task added successfully!");
    }

    private static void logStudySessionCLI() {
        System.out.print("Enter Subject ID for this session: ");
        int subjId = Integer.parseInt(scanner.nextLine());
        Subject subj = subjectService.getSubjectById(subjId);

        if (subj == null) {
            System.out.println("Subject not found. Session not logged.");
            return;
        }

        System.out.print("Enter Session Date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter Duration in minutes: ");
        int duration = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Notes/Description: ");
        String notes = scanner.nextLine();

        StudySession session = new StudySession(subj, date, duration,notes);
        sessionService.logSession(session);
        System.out.println("Study session logged successfully!");
    }

    private static void displaySubjects() {
        System.out.println("\n=== Subjects ===");
        for (Subject s : subjectService.getAllSubjects()) {
            System.out.println("ID: " + s.getSubjectId() + ", Name: " + s.getSubjectName() +
                    ", Duration: " + s.getSubjectDuration() +
                    ", PreReqs: " + s.getPreRequisites().size());
        }
    }

    private static void displayTasks() {
        System.out.println("\n=== Tasks ===");
        for (Task t : taskService.getAllTasks()) {
            System.out.println("ID: " + t.getTaskId() + ", Name: " + t.getTaskName() +
                    ", Subject: " + t.getSubject().getSubjectName() +
                    ", Due: " + t.getDueDate() +
                    ", Priority: " + t.getPriority() +
                    ", Status: " + t.getStatus() +
                    ", Reminder: " + t.isReminder());
        }
    }

    private static void displaySessions() {
        System.out.println("\n=== Study Sessions ===");
        for (StudySession s : sessionService.getAllSessions()) {
            System.out.println("Subject: " + s.getSubject().getSubjectName() +
                    ", Date: " + s.getSessionDate() +
                    ", Duration: " + s.getDurationMinutes() +
                    " mins, Notes: " + s.getDescription());
        }
    }

    private static void markTaskCompleteCLI() {
        System.out.print("Enter Task ID to mark complete: ");
        int id = Integer.parseInt(scanner.nextLine());
        boolean success = taskService.updateTaskStatus(id, Task.Status.COMPLETED);
        if (success) System.out.println("Task marked as completed!");
        else System.out.println("Task not found.");
    }

    private static void showTotalStudyTimeCLI() {
        System.out.print("Enter Subject ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Subject subj = subjectService.getSubjectById(id);

        if (subj != null) {
            int total = sessionService.getTotalStudyTime(subj.getSubjectName());
            System.out.println("Total study time for " + subj.getSubjectName() + ": " + total + " minutes");
        } else {
            System.out.println("Subject not found.");
        }
    }

    private static void showRemindersCLI() {
        System.out.println("\n=== Tasks with Reminders Due ===");
        for (Task t : taskService.getDueReminders()) {
            System.out.println("- " + t.getTaskName() + " (" + t.getSubject().getSubjectName() + ")");
        }
    }
}
