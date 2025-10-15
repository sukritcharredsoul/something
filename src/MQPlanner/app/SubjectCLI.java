package MQPlanner.app;

import MQPlanner.models.Subject;
import MQPlanner.models.Task;
import MQPlanner.services.SubjectService;
import MQPlanner.services.TaskService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * CLI interface for managing subjects.
 * Provides interactive console options for CRUD operations and viewing tasks/prerequisites.
 */
public class SubjectCLI {

    private final SubjectService subjectService;
    private final TaskService taskService;
    private final Scanner scanner;

    public SubjectCLI(SubjectService subjectService, TaskService taskService) {
        this.subjectService = subjectService;
        this.taskService = taskService;
        this.scanner = new Scanner(System.in);
    }

    /** Start the CLI loop */
    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- SUBJECT MENU ---");
            System.out.println("1. List all subjects");
            System.out.println("2. Add a subject");
            System.out.println("3. Update a subject");
            System.out.println("4. Remove a subject");
            System.out.println("5. Show prerequisites of a subject");
            System.out.println("6. Show tasks of a subject");
            System.out.println("0. Exit Subject Menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> listSubjects();
                case "2" -> addSubject();
                case "3" -> updateSubject();
                case "4" -> removeSubject();
                case "5" -> showPrerequisites();
                case "6" -> showTasksOfSubject();
                case "0" -> running = false;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    /** Lists all subjects */
    private void listSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        if (subjects.isEmpty()) {
            System.out.println("No subjects found.");
            return;
        }
        System.out.println("--- Subjects ---");
        for (Subject s : subjects) {
            System.out.printf("ID: %d | Name: %s | Duration: %s%n",
                    s.getSubjectId(), s.getSubjectName(), s.getSubjectDuration());
        }
    }

    /** Adds a new subject */
    private void addSubject() {
        System.out.print("Enter subject ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter subject name: ");
        String name = scanner.nextLine();

        System.out.print("Enter subject duration: ");
        String duration = scanner.nextLine();

        Subject newSubject = new Subject(id, name, duration, new ArrayList<>(), new ArrayList<>());
        subjectService.addSubject(newSubject);
    }

    /** Updates an existing subject */
    private void updateSubject() {
        System.out.print("Enter subject ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new duration: ");
        String duration = scanner.nextLine();

        if (subjectService.updateSubject(id, name, duration)) {
            System.out.println("Subject updated successfully!");
        } else {
            System.out.println("Subject not found.");
        }
    }

    /** Removes a subject */
    private void removeSubject() {
        System.out.print("Enter subject ID to remove: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (subjectService.removeSubject(id)) {
            System.out.println("Subject removed successfully!");
        } else {
            System.out.println("Subject not found.");
        }
    }

    /** Shows prerequisites of a subject */
    private void showPrerequisites() {
        Subject subject = selectSubject();
        if (subject == null) return;

        List<Subject> prerequisites = subjectService.getPrerequisites(subject);
        if (prerequisites.isEmpty()) {
            System.out.println("No prerequisites found.");
            return;
        }

        System.out.println("--- Prerequisites ---");
        for (Subject s : prerequisites) {
            System.out.printf("ID: %d | Name: %s%n", s.getSubjectId(), s.getSubjectName());
        }
    }

    /** Shows tasks associated with a subject */
    private void showTasksOfSubject() {
        Subject subject = selectSubject();
        if (subject == null) return;

        List<Task> tasks = subjectService.getTasksOfSubject(subject, taskService);
        if (tasks.isEmpty()) {
            System.out.println("No tasks found for this subject.");
            return;
        }

        System.out.println("--- Tasks for " + subject.getSubjectName() + " ---");
        for (Task t : tasks) {
            System.out.printf("ID: %d | Name: %s | Priority: %s | Status: %s | Due: %s | Reminder: %s%n",
                    t.getTaskId(), t.getTaskName(), t.getPriority(), t.getStatus(),
                    t.getDueDate() != null ? t.getDueDate() : "No due date",
                    t.isReminder() ? "Yes" : "No");
        }
    }

    /** Helper method to select a subject */
    private Subject selectSubject() {
        listSubjects();
        System.out.print("Enter subject ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Subject subject = subjectService.getSubjectById(id);
        if (subject == null) {
            System.out.println("Subject not found.");
        }
        return subject;
    }
}
