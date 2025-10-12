package studyPlanner.models;

import java.time.LocalDate;

public class StudySession {
    private Subject subject;
    private LocalDate sessionDate;
    private int durationMinutes;
    private String description;

    public StudySession(Subject subject, LocalDate sessionDate, int durationMinutes, String description) {
        this.subject = subject;
        this.sessionDate = sessionDate;
        this.durationMinutes = durationMinutes;
        this.description = description;
    }

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
