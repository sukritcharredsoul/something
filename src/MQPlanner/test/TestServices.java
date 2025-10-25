package MQPlanner.test;

import MQPlanner.models.StudySession;
import MQPlanner.models.Subject;
import MQPlanner.services.StudySessionService;
import MQPlanner.services.SubjectService;
import MQPlanner.utils.FileStorage;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * Tests core functionalities:
 * - Logging sessions
 * - Retrieving all sessions
 * - Filtering by subject
 * - Calculating total study time
 * - Removing sessions
 * - Subject management
 */
public class TestServices {

    private StudySessionService studySessionService;
    private ArrayList<Subject> subjects;
    private SubjectService subjectService;

    /**
     * Initializes test data before each test case.
     * Clears any stored sessions for isolation.
     */
    @BeforeEach
    public void setUp() {
        subjects = new ArrayList<>();
        subjects.add(new Subject(1,"Mathematics", "Formulas and problem solving",null,null));
        subjects.add(new Subject(2,"Physics", "Conceptual and numerical topics",null,null));

        FileStorage.saveSessions(new ArrayList<>());

        studySessionService = new StudySessionService(subjects);
        subjectService = new SubjectService();
    }

    /**
     * Tests total study time calculation for a specific subject.
     */
    @Test
    public void testGetTotalStudyTimeForSubject() {
        studySessionService.logSession(new StudySession(subjects.get(0), LocalDate.now(), 30, "Integration"));
        studySessionService.logSession(new StudySession(subjects.get(0), LocalDate.now(), 45, "Derivatives"));
        studySessionService.logSession(new StudySession(subjects.get(1), LocalDate.now(), 20, "Laws of Motion"));

        int totalMathTime = studySessionService.getTotalStudyTime("Mathematics");
        assertEquals(75, totalMathTime);
    }

    /**
     * Test to check whether subjects can be added properly.
     */
    @Test
    @DisplayName("Add Subject - should add and persist new subject")
    void testAddSubject() {
        Subject s = new Subject(3, "Chemistry", "4 months", null , null);
        subjectService.addSubject(s);

        List<Subject> all = subjectService.getAllSubjects();
        boolean found = all.stream().anyMatch(sub -> sub.getSubjectName().equals("Chemistry"));
        assertTrue(found, "Subject 'Chemistry' should be added successfully");
    }

    /**
     * Tests total study time calculation across all subjects.
     */
    @Test
    public void testGetTotalStudyTimeOverall() {
        studySessionService.logSession(new StudySession(subjects.get(0), LocalDate.now(), 50, "Statistics"));
        studySessionService.logSession(new StudySession(subjects.get(1), LocalDate.now(), 70, "Thermodynamics"));

        int total = studySessionService.getTotalStudyTime();
        assertEquals(120, total);
    }

    /**
     * Tests removal of a study session with a valid index.
     */
    @Test
    public void testRemoveSessionValidIndex() {
        studySessionService.logSession(new StudySession(subjects.get(0), LocalDate.now(), 45, "Algebra"));
        studySessionService.logSession(new StudySession(subjects.get(1), LocalDate.now(), 30, "Waves"));

        boolean removed = studySessionService.removeSession(0);
        assertTrue(removed);
        assertEquals(1, studySessionService.getAllSessions().size());
    }

    /**
     * Tests removal attempt with an invalid index (should fail gracefully).
     */
    @Test
    public void testRemoveSessionInvalidIndex() {
        studySessionService.logSession(new StudySession(subjects.get(0), LocalDate.now(), 45, "Geometry"));

        boolean removed = studySessionService.removeSession(5);
        assertFalse(removed);
        assertEquals(1, studySessionService.getAllSessions().size());
    }
}
