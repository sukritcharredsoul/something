package MQPlanner.models;

import java.time.LocalDate;

public class StudySession {
    /**
     *
     * Subject Class object , subject is created as the first attribute of this class which basically links the Subject
     * to Session logged by the user.
     *
     **/

    private Subject subject;

    /**
     *
     * Session Date , which basically logs in the data where you are going to log in the session that you are going to study about.
     *
     **/

    private LocalDate sessionDate;

    /**
     *
     * duration of the session defined in minutes and the data type used is int making it easier to perform numeric
     * functions and at the same time reduces the complexity of converting String to time.
     * For future scope we can use a util function to fetch minutes or time from string.
     *
     **/

    private int durationMinutes;

    /**
     *
     * Description of the study Session , which can help retain information by the user in the long term , for it may
     * be a little vague about what have I studied 2 months back whereas if we have a short description it may be
     * easier to remember.
     *
     **/

    private String description;


    /**
     *
     * Constructor which makes the object creation way simpler than it is usually. We have two types of constructors
     * the one below is actually what we call a parameterised Constructor and there is one default constructor which is
     * made by default.
     *
     **/

    public StudySession(Subject subject, LocalDate sessionDate, int durationMinutes, String description) {
        this.subject = subject;
        this.sessionDate = sessionDate;
        this.durationMinutes = durationMinutes;
        this.description = description;
    }



    /**
     *
     * Getter and Setter which are methods which set and get the Attributes we have defined of the Task Class
     *
     **/


    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
