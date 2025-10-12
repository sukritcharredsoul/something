package studyPlanner.models;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class Subject {
    // Subject id for Subject's That you are going to have Tasks about.
    int subjectId ;
    // Basic old Subject Name.
    String subjectName ;
    // The duration which the subject is prescribed for like one trimester or two.
    String subjectDuration ;
    // The prerequisites to study the current subject , if none it'll be an empty list.
    ArrayList<Subject> preRequisites;

    ArrayList<Task> tasks  ;

    // Constructor

    public Subject(int subjectId, String subjectName, String subjectDuration, ArrayList<Subject> preRequisites, ArrayList<Task> tasks) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectDuration = subjectDuration;
        this.preRequisites = preRequisites;
        this.tasks = new ArrayList<>() ;
    }


    // Getter and Setters ;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectDuration() {
        return subjectDuration;
    }

    public void setSubjectDuration(String subjectDuration) {
        this.subjectDuration = subjectDuration;
    }

    public ArrayList<Subject> getPreRequisites() {
        return preRequisites;
    }

    public void setPreRequisites(ArrayList<Subject> preRequisites) {
        this.preRequisites = preRequisites;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}

