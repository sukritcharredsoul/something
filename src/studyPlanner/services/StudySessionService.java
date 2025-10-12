package studyPlanner.services;

import studyPlanner.models.StudySession;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class to manage Study Sessions in the Study Planner.
 * Handles logging sessions, retrieving them, and calculating total study time.
 */
public class StudySessionService {

    private List<StudySession> sessions;

    public void StudySessionService() {
        this.sessions = new ArrayList<>();
    }


    // -------------------- ADD / LOG SESSION --------------------

    /**
     * Logs a new study session.
     * @param session StudySession object to add
     */
    public void logSession(StudySession session) {
        sessions.add(session);
        System.out.println("Study session logged for subject: " + session.getSubject().getSubjectName());
    }

    // -------------------- GET SESSIONS --------------------

    /**
     * Returns all study sessions.
     * @return list of StudySession objects
     */
    public List<StudySession> getAllSessions() {
        return sessions;
    }

    /**
     * Returns all sessions for a specific subject.
     * @param subjectName Name of the subject
     * @return list of StudySession objects for that subject
     */
    public List<StudySession> getSessionsBySubject(String subjectName) {
        List<StudySession> result = new ArrayList<>();
        for (StudySession s : sessions) {
            if (s.getSubject().getSubjectName().equals(subjectName)) {
                result.add(s);
            }
        }
        return result;
    }

    // -------------------- TOTAL STUDY TIME --------------------

    /**
     * Calculates total study time (in minutes) for a specific subject.
     * @param subjectName Name of the subject
     * @return total duration in minutes
     */
    public int getTotalStudyTime(String subjectName) {
        int total = 0;
        for (StudySession s : sessions) {
            if (s.getSubject().getSubjectName().equals(subjectName)) {
                total += s.getDurationMinutes();
            }
        }
        return total;
    }

    /**
     * Calculates total study time (in minutes) across all subjects.
     * @return total duration in minutes
     */
    public int getTotalStudyTime() {
        int total = 0;
        for (StudySession s : sessions) {
            total += s.getDurationMinutes();
        }
        return total;
    }

    // -------------------- REMOVE SESSION --------------------

    /**
     * Removes a study session by its index in the list.
     * @param index Index of the session to remove
     * @return true if removed successfully, false otherwise
     */
    public boolean removeSession(int index) {
        if (index >= 0 && index < sessions.size()) {
            sessions.remove(index);
            return true;
        }
        return false;
    }
}
