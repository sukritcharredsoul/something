package MQPlanner.models;

import java.time.LocalDate;

public class Task {

    /**
    *
    * Task ID is the first field which will make our access to the tasks logged easy and fast.
    *
    **/

    private int taskId ;

    /**
    *
    * Task Name is necessary for it won't be feasible for the user to remember the Task-ID all the times especially
    * when we have large amounts of data logged in.
    *
     **/

    private String taskName ;

    /**
     *
     * Priority of the tasks enable us user to filter out things based on Priority making it easier for implementation
     * of various functions in future scope based on Priority.
     *
     **/

    public enum Priority {
        LOW,
        MEDIUM,
        HIGH,
    } ;

    /**
    *
    * Status of a task is needed to actually know what things we have actually completed and which ones are left to make
    * it better and understandable.
    *
    **/

    public enum Status {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }

    /**
    *
    * Reminder if i as a user want to have reminders about the tasks the user has given whether to remind the user on
    * the day specified by or not.
    *
    **/

    private boolean reminder ;

    /**
     *
     * Due Date is used actually store the submission date of the tasks for the users to realise about time management.
     *
     **/

    private LocalDate dueDate;


    /**
    *
    * We have created a subject object of the Subject Class to make it easier for us to integrate a class under another
    * class. So that we can map tasks and subject together.
    *
    **/

    private Subject subject ;

    /**
     *
     * We have created a priority object of the enum Priority to make it easier for us to handle the priority addition
     * in constructor itself and the same goes for the other enum defined in this ( Task Model ) which is Status.
     *
     **/

    private Priority priority ;


    private Status status ;


    /**
    *
    * Future Scope , for recurring tasks at hand ;
    * private boolean isRecurring ;
    *
    **/

    /**
    * Constructor which makes the object creation way simpler than it is usually. We have two types of constuctors
    * the one below is actually what we call a parameterised Constructor.
    **/

    public Task(int taskId, String taskName, boolean reminder, LocalDate dueDate, Subject subject, Priority priority, Status status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.reminder = reminder;
        this.dueDate = dueDate;
        this.subject = subject;
        this.priority = priority;
        this.status = status;
    }


    /**
    *
    * Getter and Setter which are methods which set and get the Attributes we have defined of the Task Class
    * These can be generated via IDE ( Intellij / VS Code via easy shortcuts ) or Written by hand. All the Attributes of
    * this class has it's getter and setter created here which will be used in subsequent service classes by me.
    *
    **/

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


