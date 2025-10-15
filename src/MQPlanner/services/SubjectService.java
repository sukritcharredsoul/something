package MQPlanner.services;

import MQPlanner.models.Subject;
import MQPlanner.models.Task;
import MQPlanner.utils.FileStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class to manage subjects in the Study Planner.
 * Provides methods to add, update, delete, and retrieve subjects.
 */
public class SubjectService {

    private ArrayList<Subject> subjects;

    /** Default constructor loads subjects from file */
    public SubjectService() {
        this.subjects = FileStorage.loadSubjects();
    }

    /** Adds a new subject and saves to file */
    public void addSubject(Subject newSubject) {
        subjects.add(newSubject);
        FileStorage.saveSubjects(subjects);
        System.out.println("Subject added: " + newSubject.getSubjectName());
    }

    /** Returns all subjects */
    public List<Subject> getAllSubjects() {
        return subjects;
    }

    /** Get subject by ID */
    public Subject getSubjectById(int id) {
        for (Subject s : subjects) {
            if (s.getSubjectId() == id) return s;
        }
        return null;
    }

    /** Update subject details and save to file */
    public boolean updateSubject(int id, String newName, String newDuration) {
        Subject s = getSubjectById(id);
        if (s != null) {
            s.setSubjectName(newName);
            s.setSubjectDuration(newDuration);
            FileStorage.saveSubjects(subjects);
            return true;
        }
        return false;
    }

    /** Remove subject by ID and save to file */
    public boolean removeSubject(int id) {
        Subject s = getSubjectById(id);
        if (s != null) {
            subjects.remove(s);
            FileStorage.saveSubjects(subjects);
            return true;
        }
        return false;
    }

    /** Returns all tasks associated with a given subject using TaskService */
    public List<Task> getTasksOfSubject(Subject subject, TaskService taskService) {
        List<Task> tasksOfSubject = new ArrayList<>();
        if (subject == null || taskService == null) return tasksOfSubject;

        for (Task t : taskService.getAllTasks()) {
            if (t.getSubject() != null && t.getSubject().equals(subject)) {
                tasksOfSubject.add(t);
            }
        }
        return tasksOfSubject;
    }

    /** Returns all prerequisite subjects for a given subject */
    public List<Subject> getPrerequisites(Subject subject) {
        List<Subject> prerequisites = new ArrayList<>();
        if (subject == null) return prerequisites;

        if (subject.getPreRequisites() != null) {
            prerequisites.addAll(subject.getPreRequisites());
        }
        return prerequisites;
    }

    /** Adds a prerequisite to a subject and saves to file */
    public boolean addPrerequisite(int subjectId, Subject prerequisite) {
        Subject s = getSubjectById(subjectId);
        if (s != null) {
            if (s.getPreRequisites() == null) {
                s.setPreRequisites(new ArrayList<>());
            }
            s.getPreRequisites().add(prerequisite);
            FileStorage.saveSubjects(subjects);
            return true;
        }
        return false;
    }
}
