package studyPlanner.services;

import studyPlanner.models.Subject;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class to manage subjects in the Study Planner.
 * Provides methods to add, update, delete, and retrieve subjects.
 */
public class SubjectService {

    // Internal list to store subjects
    private List<Subject> subjects;

    public SubjectService(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public SubjectService() {

    }

    // Constructor
    public void SubjectService() {
        this.subjects = new ArrayList<>();
    }

    // -------------------- ADD SUBJECT --------------------

    /**
     * Adds a new subject to the planner.
     * @param newSubject Subject object to add
     */
    public void addSubject(Subject newSubject) {
        subjects.add(newSubject);
        System.out.println("✅ Subject added: " + newSubject.getSubjectName());
    }

    // -------------------- GET SUBJECT --------------------

    /**
     * Returns all subjects.
     * @return list of subjects
     */
    public List<Subject> getAllSubjects() {
        return subjects;
    }

    /**
     * Get subject by its ID.
     * @param id Subject ID
     * @return subject object if found, null otherwise
     */
    public Subject getSubjectById(int id) {
        for (Subject s : subjects) {
            if (s.getSubjectId() == id) return s;
        }
        return null;
    }

    // -------------------- UPDATE SUBJECT --------------------

    /**
     * Update a subject's name and duration.
     * @param id Subject ID
     * @param newName New name of the subject
     * @param newDuration New duration
     * @return true if update successful, false if subject not found
     */
    public boolean updateSubject(int id, String newName, String newDuration) {
        Subject s = getSubjectById(id);
        if (s != null) {
            s.setSubjectName(newName);
            s.setSubjectDuration(newDuration);
            return true;
        }
        return false;
    }

    // -------------------- DELETE SUBJECT --------------------

    /**
     * Remove a subject by ID.
     * @param id Subject ID
     * @return true if deletion successful, false if subject not found
     */
    public boolean removeSubject(int id) {
        Subject s = getSubjectById(id);
        if (s != null) {
            subjects.remove(s);
            return true;
        }
        return false;
    }

    // -------------------- HELPER METHODS --------------------

    /**
     * Add a prerequisite to a subject.
     * @param subjectId ID of the subject
     * @param prerequisite Subject object to add as prerequisite
     * @return true if added, false if subject not found
     */
    public boolean addPrerequisite(int subjectId, Subject prerequisite) {
        Subject s = getSubjectById(subjectId);
        if (s != null) {
            if (s.getPreRequisites() == null) {
                s.setPreRequisites(new ArrayList<>());
            }
            s.getPreRequisites().add(prerequisite);
            return true;
        }
        return false;
    }
}
