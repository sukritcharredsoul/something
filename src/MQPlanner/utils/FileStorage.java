package MQPlanner.utils;

import MQPlanner.models.Subject;
import MQPlanner.models.Task;
import MQPlanner.models.StudySession;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * Utility class for saving and loading Subjects, Tasks, and StudySessions
 * to and from CSV files. This provides storage of data into .csv Files under the data folder
 *
 */
public class FileStorage {

    /**
     * Here I've defined the file String and where actually the data will actually be stored , I've followed
     * the All-Caps format to basically separate out the difference between normal Variables and the ones that
     * I'm using for Application in terms of the normal In-Memory Computions.
     */
    private static final String DATA_DIR = "src/data";
    private static final String SUBJECTS_FILE = DATA_DIR + "/subjects.csv";
    private static final String TASKS_FILE = DATA_DIR + "/tasks.csv";
    private static final String SESSIONS_FILE = DATA_DIR + "/sessions.csv";

    /**
     *
     * Ensures that the data directory exists. Creates it if it does not exist and if It doesn't It also prints out
     * a text message , "Failed to Create data directory".
     *
     */
    private static void ensureDataDirExists() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (!created) {
                System.out.println("Failed to create data directory.");
            }
        }
    }

    /*
    *
    * Here below i've defined the two methods i would require for all the models, one for pulling the data from the .csv filles
     * and the other for pushing the data into the .csv file. Finally After milestone 2 , I changed the services to
     * basically instead of doing in memory calculations use these utilary methods to store it instead.
     *
     *
    */


    /**
     * Saves all subjects into the subjects CSV file.
     *
     * @param subjects List of subjects to save
     */
    public static void saveSubjects(ArrayList<Subject> subjects) {
        ensureDataDirExists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SUBJECTS_FILE))) {
            writer.write("subjectId,subjectName,duration\n");

            for (Subject s : subjects) {
                writer.write(s.getSubjectId() + "," +
                        escape(s.getSubjectName()) + "," +
                        escape(s.getSubjectDuration()) + "\n");
            }

            System.out.println("Subjects saved successfully.");
        } catch (IOException e) {
            System.out.println("Error while saving subjects: " + e.getMessage());
        }
    }

    /**
     * Loads all subjects from the subjects CSV file.
     *
     * @return List of subjects loaded from file
     */
    public static ArrayList<Subject> loadSubjects() {
        ensureDataDirExists();

        ArrayList<Subject> subjects = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(SUBJECTS_FILE))) {
            reader.readLine(); // Skip header
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 3) continue;

                int id = Integer.parseInt(parts[0]);
                String name = unescape(parts[1]);
                String duration = unescape(parts[2]);

                subjects.add(new Subject(id, name, duration, new ArrayList<>(), new ArrayList<>()));
            }

            System.out.println("Subjects loaded successfully.");
        } catch (IOException e) {
            System.out.println("No subjects found. Starting fresh.");
        }
        return subjects;
    }

    /*
     *  We
     */


    /**
     * Saves all tasks into the tasks CSV file.
     *
     * @param tasks List of tasks to save
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        ensureDataDirExists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TASKS_FILE))) {
            writer.write("taskId,taskName,priority,status,dueDate,reminder,subjectId\n");

            for (Task t : tasks) {
                int subjectId = (t.getSubject() != null) ? t.getSubject().getSubjectId() : -1;
                writer.write(t.getTaskId() + "," +
                        escape(t.getTaskName()) + "," +
                        t.getPriority() + "," +
                        t.getStatus() + "," +
                        (t.getDueDate() != null ? t.getDueDate() : "") + "," +
                        t.isReminder() + "," +
                        subjectId + "\n");
            }

            System.out.println("Tasks saved successfully.");
        } catch (IOException e) {
            System.out.println("Error while saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads all tasks from the tasks CSV file and links them to their subjects.
     *
     * @param subjects List of subjects to match task references
     * @return List of tasks loaded from file
     */
    public static ArrayList<Task> loadTasks(ArrayList<Subject> subjects) {
        ensureDataDirExists();

        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TASKS_FILE))) {
            reader.readLine(); // Skip header
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 7) continue;

                int taskId = Integer.parseInt(parts[0]);
                String taskName = unescape(parts[1]);
                Task.Priority priority = Task.Priority.valueOf(parts[2]);
                Task.Status status = Task.Status.valueOf(parts[3]);
                LocalDate dueDate = parts[4].isEmpty() ? null : LocalDate.parse(parts[4]);
                boolean reminder = Boolean.parseBoolean(parts[5]);
                int subjectId = Integer.parseInt(parts[6]);

                Subject linkedSubject = null;
                for (Subject s : subjects) {
                    if (s.getSubjectId() == subjectId) {
                        linkedSubject = s;
                        break;
                    }
                }

                tasks.add(new Task(taskId, taskName, reminder, dueDate, linkedSubject, priority, status));
            }

            System.out.println("Tasks loaded successfully.");
        } catch (IOException e) {
            System.out.println("No tasks found. Starting fresh.");
        }

        return tasks;
    }



    /**
     * Saves all study sessions into the sessions CSV file.
     *
     * @param sessions List of study sessions to save
     */
    public static void saveSessions(ArrayList<StudySession> sessions) {
        ensureDataDirExists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SESSIONS_FILE))) {
            writer.write("subjectName,durationMinutes,date,description\n");

            for (StudySession s : sessions) {
                writer.write(escape(s.getSubject().getSubjectName()) + "," +
                        s.getDurationMinutes() + "," +
                        s.getSessionDate() + "," +
                        escape(s.getDescription()) + "\n");
            }

            System.out.println("Study sessions saved successfully.");
        } catch (IOException e) {
            System.out.println("Error while saving sessions: " + e.getMessage());
        }
    }

    /**
     * Loads all study sessions from the sessions CSV file.
     *
     * @param subjects List of subjects to match sessions
     * @return List of study sessions loaded from file
     */
    public static ArrayList<StudySession> loadSessions(ArrayList<Subject> subjects) {
        ensureDataDirExists();

        ArrayList<StudySession> sessions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(SESSIONS_FILE))) {
            reader.readLine(); // Skip header
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4) continue;

                String subjectName = unescape(parts[0]);
                int duration = Integer.parseInt(parts[1]);
                LocalDate date = LocalDate.parse(parts[2]);
                String description = unescape(parts[3]);

                for (Subject s : subjects) {
                    if (s.getSubjectName().equals(subjectName)) {
                        sessions.add(new StudySession(s, date, duration, description));
                        break;
                    }
                }
            }

            System.out.println("Study sessions loaded successfully.");
        } catch (IOException e) {
            System.out.println("No sessions found. Starting fresh.");
        }

        return sessions;
    }

    // Helper methods to sort out this.

    /**
     * Escapes commas in strings to avoid breaking CSV format.
     *
     * @param s Input string
     * @return Escaped string
     */
    private static String escape(String s) {
        return s.replace(",", ";");
    }

    /**
     * Unescapes strings loaded from CSV files.
     *
     * @param s Escaped string
     * @return Original string
     */
    private static String unescape(String s) {
        return s.replace(";", ",");
    }
}
