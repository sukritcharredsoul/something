package studyPlanner.services;

import studyPlanner.models.Task;
import studyPlanner.models.Subject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class to manage tasks in the Study Planner.
 * Handles adding, updating, filtering, and listing tasks without using streams.
 */
public class TaskService {

    private final List<Task> tasks;


    public TaskService() {
        this.tasks = new ArrayList<>();
    }


    /**
     * Adds a new task to the planner.
     * @param task Task object to add
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("✅ Task added: " + task.getTaskName());
    }

    /**
     * Adds a task and associates it with a subject.
     * @param task Task object
     * @param subject The subject the task belongs to
     */
    public void addTask(Task task, Subject subject) {
        task.setSubject(subject);
        addTask(task);
    }


    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getTaskById(int id) {
        for (Task t : tasks) {
            if (t.getTaskId() == id) {
                return t;
            }
        }
        return null;
    }

    public List<Task> getTasksBySubject(Subject subject) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getSubject() != null && t.getSubject().equals(subject)) {
                result.add(t);
            }
        }
        return result;
    }

    public List<Task> getTasksByPriority(Task.Priority priority) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getPriority() == priority) {
                result.add(t);
            }
        }
        return result;
    }

    public List<Task> getTasksByStatus(Task.Status status) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getStatus() == status) {
                result.add(t);
            }
        }
        return result;
    }

    public List<Task> getTasksDueBefore(LocalDate date) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDueDate() != null && t.getDueDate().isBefore(date)) {
                result.add(t);
            }
        }
        return result;
    }


    public boolean updateTaskStatus(int taskId, Task.Status status) {
        Task t = getTaskById(taskId);
        if (t != null) {
            t.setStatus(status);
            return true;
        }
        return false;
    }


    public boolean setReminder(int taskId, boolean reminder) {
        Task t = getTaskById(taskId);
        if (t != null) {
            t.setReminder(reminder);
            return true;
        }
        return false;
    }


    public boolean removeTask(int taskId) {
        Task t = getTaskById(taskId);
        if (t != null) {
            tasks.remove(t);
            return true;
        }
        return false;
    }


    /**
     * Returns all tasks with reminders due today or overdue.
     * @return list of tasks with reminders
     */
    public List<Task> getDueReminders() {
        LocalDate today = LocalDate.now();
        List<Task> reminderTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.isReminder() && t.getDueDate() != null && !t.getDueDate().isAfter(today)) {
                reminderTasks.add(t);
            }
        }
        return reminderTasks;
    }
}
