package studyPlanner.models;

import java.time.LocalDate;

public class Task {
    // For easy access of tasks and future indexing if we wish to.
    private int taskId ;

    // Every Task should have a name.
    private String taskName ;




    // Will help us to implement Priority Filtering the Tasks at hand.


    public enum Priority {
        LOW,
        MEDIUM,
        HIGH,
    } ;

    // A new Task will always start with Pending first.
    public enum Status {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }


    // Reminder if i as a user want to have reminders about the tasks i've given.
    private boolean reminder ;

    // Date / Time implementation all modern Task Planners have one.
    private LocalDate dueDate;

    private Subject subject ;

    private Priority priority ;

    private Status status ;


    /*
    *
    * Future Scope , for recurring tasks at hand ;
    * private boolean isRecurring ;
    *
    */

    /*
    * Constructor
    */

    public Task(int taskId, String taskName, boolean reminder, LocalDate dueDate, Subject subject, Priority priority, Status status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.reminder = reminder;
        this.dueDate = dueDate;
        this.subject = subject;
        this.priority = priority;
        this.status = status;
    }


    /*
    *
    * Getter and Setter which are methods which set and get the Attributes we have defined of the Task Class
    * These can be generated via IDE ( Intellij / VS Code via easy shortcuts ) or Written by hand.
    *
    */

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isReminder() {
        return reminder;
    }

    public void setReminder(boolean reminder) {
        this.reminder = reminder;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setPriority(Priority priority) { this.priority = priority; }

    public void setStatus(Status status) { this.status = status; }

    public Priority getPriority() { return priority; }
    public Status getStatus() { return status; }



}


