package MQPlanner.services;

import MQPlanner.models.StudySession;
import MQPlanner.models.Subject;
import MQPlanner.utils.FileStorage;
import MQPlanner.utils.LinkedList;

import java.util.ArrayList;


/**
 *
 * Service class to manage Study Sessions in the Study Planner.
 * Handles logging sessions, retrieving them, and calculating total study time.
 * Sessions is a functionality i thought about in a sense that it would keep everyone in check about the time
 *
 */


public class StudySessionService {

    private LinkedList<StudySession> sessions;

    /**
     * Constructor accepts an ArrayList of subjects (for Main compatibility)
     */
    public StudySessionService(ArrayList<Subject> subjects) {
        this.sessions = new LinkedList<>();
        ArrayList<StudySession> loaded = FileStorage.loadSessions(subjects);
        for (StudySession s : loaded) {
            sessions.add(s);
        }
    }

    /**
     * Alternative constructor for LinkedList<Subject>
     */
    public StudySessionService(LinkedList<Subject> subjects) {
        this.sessions = new LinkedList<>();
        ArrayList<Subject> subjectsList = convertLinkedListToArrayList(subjects);
        ArrayList<StudySession> loaded = FileStorage.loadSessions(subjectsList);
        for (StudySession s : loaded) {
            sessions.add(s);
        }
    }

    public void logSession(StudySession session) {
        sessions.add(session);
        FileStorage.saveSessions(convertLinkedListToArrayList(sessions));
        System.out.println("Study session logged for subject: " + session.getSubject().getSubjectName());
    }

    public LinkedList<StudySession> getAllSessions() {
        return sessions;
    }

    public LinkedList<StudySession> getSessionsBySubject(String subjectName) {
        LinkedList<StudySession> result = new LinkedList<>();
        for (int i = 0; i < sessions.size(); i++) {
            StudySession s = sessions.get(i);
            if (s.getSubject().getSubjectName().equals(subjectName)) {
                result.add(s);
            }
        }
        return result;
    }

    public int getTotalStudyTime(String subjectName) {
        int total = 0;
        for (int i = 0; i < sessions.size(); i++) {
            StudySession s = sessions.get(i);
            if (s.getSubject().getSubjectName().equals(subjectName)) {
                total += s.getDurationMinutes();
            }
        }
        return total;
    }

    public int getTotalStudyTime() {
        int total = 0;
        for (int i = 0; i < sessions.size(); i++) {
            total += sessions.get(i).getDurationMinutes();
        }
        return total;
    }

    public boolean removeSession(int index) {
        boolean removed = sessions.remove(index);
        if (removed) {
            FileStorage.saveSessions(convertLinkedListToArrayList(sessions));
        }
        return removed;
    }

    /** Helper to convert LinkedList to ArrayList for FileStorage */
    private <T> ArrayList<T> convertLinkedListToArrayList(LinkedList<T> list) {
        ArrayList<T> arrayList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(list.get(i));
        }
        return arrayList;
    }
}
