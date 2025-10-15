package MQPlanner.app;

import MQPlanner.models.Task;
import MQPlanner.models.Subject;
import MQPlanner.services.TaskService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Command-Line Interface for Task management.
 * Handles user interaction for adding, updating, viewing, filtering, and deleting tasks.
 */
public class TaskCLI {

    private final TaskService taskService;
    private final Scanner scanner;

    public TaskCLI(TaskService taskService) {
        this.taskService = taskService;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the main Task menu and handles user choices.
     */
    public void start() {
        int choice;
        do {
            System.out.println("\n===== Task Menu =====");
            System.out.println("1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. View Tasks by Subject");
            System.out.println("4. View Tasks by Priority");
            System.out.println("5. View Tasks by Status");
            System.out.println("6. View Tasks Due Before a Date");
            System.out.println("7. Update Task Status");
            System.out.println("8. Set/Unset Task Reminder");
            System.out.println("9. Remove Task");
            System.out.println("10. View Due Reminders");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addTaskMenu();
                case 2 -> printTasks(taskService.getAllTasks());
                case 3 -> viewTasksBySubject();
                case 4 -> viewTasksByPriority();
                case 5 -> viewTasksByStatus();
                case 6 -> viewTasksDueBefore();
                case 7 -> updateTaskStatus();
                case 8 -> setTaskReminder();
                case 9 -> removeTask();
                case 10 -> printTasks(taskService.getDueReminders());
                case 0 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 0);
    }

    /** Helper method to print a list of tasks neatly */
    private void printTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        System.out.println("\n--- Tasks ---");
        for (Task t : tasks) {
            System.out.println("ID: " + t.getTaskId()
                    + " | Name: " + t.getTaskName()
                    + " | Subject: " + (t.getSubject() != null ? t.getSubject().getSubjectName() : "None")
                    + " | Priority: " + t.getPriority()
                    + " | Status: " + t.getStatus()
                    + " | Due: " + (t.getDueDate() != null ? t.getDueDate() : "None")
                    + " | Reminder: " + t.isReminder());
        }
    }

    /** Add a new task interactively */
    private void addTaskMenu() {
        try {
            System.out.print("Enter Task ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter Task Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Priority (LOW/MEDIUM/HIGH): ");
            Task.Priority priority = Task.Priority.valueOf(scanner.nextLine().toUpperCase());

            System.out.print("Enter Status (PENDING/IN_PROGRESS/COMPLETED): ");
            Task.Status status = Task.Status.valueOf(scanner.nextLine().toUpperCase());

            System.out.print("Set Reminder? (true/false): ");
            boolean reminder = Boolean.parseBoolean(scanner.nextLine());

            System.out.print("Enter Due Date (yyyy-MM-dd) or leave blank: ");
            String dateStr = scanner.nextLine();
            LocalDate dueDate = null;
            if (!dateStr.isEmpty()) {
                dueDate = LocalDate.parse(dateStr);
            }

            System.out.print("Enter Subject Name or leave blank: ");
            String subjectName = scanner.nextLine();
            Subject subject = null;
            // subject lookup should happen outside CLI or be passed in via SubjectService
            // For now, we leave null if not provided

            Task task = new Task(id, name, reminder, dueDate, subject, priority, status);
            taskService.addTask(task);
            System.out.println("Task added successfully!");

        } catch (IllegalArgumentException | DateTimeParseException e) {
            System.out.println("Invalid input! Please try again.");
        }
    }

    /** Filter tasks by subject */
    private void viewTasksBySubject() {
        System.out.print("Enter Subject Name: ");
        String name = scanner.nextLine();
        List<Task> filtered = taskService.getAllTasks().stream()
                .filter(t -> t.getSubject() != null && t.getSubject().getSubjectName().equalsIgnoreCase(name))
                .toList();
        printTasks(filtered);
    }

    /** Filter tasks by priority */
    private void viewTasksByPriority() {
        System.out.print("Enter Priority (LOW/MEDIUM/HIGH): ");
        try {
            Task.Priority priority = Task.Priority.valueOf(scanner.nextLine().toUpperCase());
            printTasks(taskService.getTasksByPriority(priority));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid priority!");
        }
    }

    /** Filter tasks by status */
    private void viewTasksByStatus() {
        System.out.print("Enter Status (PENDING/IN_PROGRESS/COMPLETED): ");
        try {
            Task.Status status = Task.Status.valueOf(scanner.nextLine().toUpperCase());
            printTasks(taskService.getTasksByStatus(status));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status!");
        }
    }

    /** Filter tasks due before a certain date */
    private void viewTasksDueBefore() {
        System.out.print("Enter date (yyyy-MM-dd): ");
        try {
            LocalDate date = LocalDate.parse(scanner.nextLine());
            printTasks(taskService.getTasksDueBefore(date));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format!");
        }
    }

    /** Update the status of a task */
    private void updateTaskStatus() {
        System.out.print("Enter Task ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter new Status (PENDING/IN_PROGRESS/COMPLETED): ");
        try {
            Task.Status status = Task.Status.valueOf(scanner.nextLine().toUpperCase());
            if (taskService.updateTaskStatus(id, status)) {
                System.out.println("Task status updated successfully!");
            } else {
                System.out.println("Task not found.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status!");
        }
    }

    /** Set or unset a task reminder */
    private void setTaskReminder() {
        System.out.print("Enter Task ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Set reminder? (true/false): ");
        boolean reminder = Boolean.parseBoolean(scanner.nextLine());
        if (taskService.setReminder(id, reminder)) {
            System.out.println("Reminder updated successfully!");
        } else {
            System.out.println("Task not found.");
        }
    }

    /** Remove a task by ID */
    private void removeTask() {
        System.out.print("Enter Task ID to remove: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (taskService.removeTask(id)) {
            System.out.println("Task removed successfully!");
        } else {
            System.out.println("Task not found.");
        }
    }

}
