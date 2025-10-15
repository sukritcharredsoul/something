package MQPlanner.test;


import org.junit.jupiter.api.*;
import MQPlanner.models.Subject;
import MQPlanner.models.Task;
import MQPlanner.services.SubjectService;
import MQPlanner.services.TaskService;

import java.time.LocalDate;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SubjectServiceTest {

    SubjectService subjectService;
    TaskService taskService ;



    @Test
    void testGetSubjectById() {
        Subject s = new Subject(2, "Physics", "4 months", null,null);
        subjectService.addSubject(s);

        Subject fetched = subjectService.getSubjectById(2);
        assertNotNull(fetched);
        assertEquals("Physics", fetched.getSubjectName());
    }



    @Test
    void testAddTask(){
        Subject chemistry = new Subject(1, "Chemistry", "3 months",null, null);
        Task t = new Task( 101, "Solve Maths", true, LocalDate.of(2025,10,15),chemistry, Task.Priority.HIGH, Task.Status.PENDING) ;
        taskService.addTask(t);
        ArrayList<Task> tasks = taskService.getAllTasks();
        assertEquals(1,tasks.size());
        assertEquals("Solve Maths",tasks.get(0).getTaskName());

    }



}

