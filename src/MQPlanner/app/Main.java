package MQPlanner.app;

import MQPlanner.models.Subject;
import MQPlanner.services.SubjectService;
import MQPlanner.services.StudySessionService;
import MQPlanner.services.TaskService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Initialize services
        SubjectService subjectService = new SubjectService();
        StudySessionService sessionService = new StudySessionService(
                new ArrayList<>(subjectService.getAllSubjects())
        );

        TaskService taskService = new TaskService((ArrayList<Subject>) subjectService.getAllSubjects());


        SubjectCLI subjectCLI = new SubjectCLI(subjectService,taskService);
        SessionCLI sessionCLI = new SessionCLI(sessionService, subjectService);
        TaskCLI taskCLI = new TaskCLI(taskService);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n MQ Planner ");
            System.out.println("1. Manage Subjects");
            System.out.println("2. Manage Study Sessions");
            System.out.println("3. Manage Tasks");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> subjectCLI.start();
                case "2" -> sessionCLI.start();
                case "3" -> taskCLI.start();
                case "0" -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }

        System.out.println("Exiting Study Planner. Goodbye!");
        scanner.close();
    }
}
