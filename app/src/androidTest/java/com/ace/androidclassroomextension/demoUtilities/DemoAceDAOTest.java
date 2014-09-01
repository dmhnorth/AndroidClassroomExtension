package com.ace.androidclassroomextension.demoUtilities;

import com.ace.androidclassroomextension.dataAccessObject.AceDAO;
import com.ace.androidclassroomextension.models.Lesson;
import com.ace.androidclassroomextension.models.User;

import junit.framework.TestCase;

import java.util.List;

public class DemoAceDAOTest extends TestCase {

    private AceDAO aceDAO;
    private int testId;
    private User teacher;
    private User student;
    private String lessonName;
    private String lessonDescription;

    public void setUp() throws Exception {
        super.setUp();

        aceDAO = new DemoAceDAO();

        student = new User("Mr.Student");
        student.setIsTeacher(false);
        teacher = new User("Mr.Teacher");
        teacher.setIsTeacher(true);

        lessonName = "Test Lesson Name";
        lessonDescription = "Test Lesson Description";
        testId = aceDAO.createNewLessonOnDAO(teacher, lessonName, lessonDescription);

    }

    public void tearDown() throws Exception {
        aceDAO = null;
        student = null;
        teacher = null;
    }

    public void testGetLessonsAsList() throws Exception {
        assertEquals(aceDAO.getLessonsAsList().size(), 5);
    }

    public void testGetLessonViaId() throws Exception {
        assertEquals(testId, aceDAO.getLessonViaId(testId).getLessonId());
    }

    public void testCreateNewLessonOnDAO() throws Exception {
        int newLesson = aceDAO.createNewLessonOnDAO(teacher, lessonName, lessonDescription);
        assertEquals(aceDAO.getLessonViaId(newLesson).getLessonId(), newLesson);
    }

    public void testAddLessonToDAO() throws Exception {
        List<Lesson> lessonsAsList = aceDAO.getLessonsAsList();
        int oldSize = lessonsAsList.size();

        Lesson lesson = new Lesson(teacher, lessonName, lessonDescription);
        aceDAO.addLessonToDAO(lesson);
        int newsSize = aceDAO.getLessonsAsList().size();
        assertEquals(oldSize + 1, newsSize);
    }
}