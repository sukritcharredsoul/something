package MQPlanner.models;

import java.util.ArrayList;

public class Subject {

    /**
     * Subject ID is the first parameter making it easier to move about the application and implement functions using the
     * ID itself.
     */

    int subjectId ;


    /**
     * Subject Name would be the next attribute defined under this model class to log on user-readable names which
     * help store the data which can be easily decipher.
     *
     **/
    String subjectName ;


    /**
     *
     * We define SubjectDuration to understand and store how long the data would be with us and in future scope we
     * can delete the data after a certain timezone if we wish too apart from that it'll be good for productivity
     * standards to store the data of the things / Majors you have covered.
     *
     **/

    String subjectDuration ;


    /**
     *
     * It makes sense that the University Subject that you are going to do may have some pre-requisites itself. For Example
     * Engineering-Physics 204 , might need Engineering-Physics 102 hence storing these data is also necessary.
     *
     **/

    ArrayList<Subject> preRequisites;

    /**
     *
     * Tasks that are currently stored in a subject , It starts of as empty, and we can fill up this ArrayList of Task
     * after the logging in the tasks with the help of Task Service.
     **/

    ArrayList<Task> tasks  ;



    /**
     * Constructor which makes the object creation way simpler than it is usually. We have two types of constructors
     * the one below is actually what we call a parameterised Constructor and there is one default constructor which is
     * made by default.
     **/



    public Subject(int subjectId, String subjectName, String subjectDuration, ArrayList<Subject> preRequisites, ArrayList<Task> tasks) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectDuration = subjectDuration;
        this.preRequisites = preRequisites != null ? preRequisites : new ArrayList<>();
        this.tasks = tasks != null ? tasks : new ArrayList<>();
    }


    /**
     *
     * Getter and Setter which are methods which set and get the Attributes we have defined of the Task Class
     * These can be generated via IDE ( Intellij / VS Code via easy shortcuts ) or Written by hand. All the Attributes of
     * this class has it's getter and setter created here which will be used in subsequent service classes by me.
     *
     **/

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

