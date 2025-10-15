package MQPlanner.services;

import MQPlanner.models.StudySession;
import MQPlanner.models.Subject;
import MQPlanner.utils.FileStorage;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Service class to manage Study Sessions in the Study Planner.
 * Handles logging sessions, retrieving them, and calculating total study time.
 * Sessions is a functionality i thought about in a sense that it would keep everyone in check about the time
 *
 */
public class StudySessionService {


    private ArrayList<StudySession> sessions;


    /**
     *
     * StudySessionService Constructor which accepts an ArrayList Subjects.
     *
     */
    public StudySessionService(ArrayList<Subject> subjects) {
        this.sessions = FileStorage.loadSessions(subjects) ;
    }


    /**
     *
     * Logs a new study session.
     * @param session StudySession object to add
     */
    public void logSession(StudySession session) {
        sessions.add(session);
        FileStorage.saveSessions(sessions);
        System.out.println("Study session logged for subject: " + session.getSubject().getSubjectName());
    }



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


    /**
     * Removes a study session by its index in the list.
     * @param index Index of the session to remove
     * @return true if removed successfully, false otherwise
     */
    public boolean removeSession(int index) {
        if (index >= 0 && index < sessions.size()) {
            sessions.remove(index);
            FileStorage.saveSessions(sessions);
            return true;
        }
        return false;
    }
}
