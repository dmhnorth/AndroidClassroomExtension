package com.ace.androidclassroomextension.demoUtilities;

import android.util.Log;

import com.ace.androidclassroomextension.models.Lesson;
import com.ace.androidclassroomextension.models.User;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.List;
import java.util.Map;

public class DemoAceDAOTest extends TestCase {

    private DemoAceDAO demoAceDAO;
    private int testId;
    private User teacher;
    private User student;

    public void setUp() throws Exception {
        super.setUp();
        //Create the demoAceDAO
        demoAceDAO = new DemoAceDAO();

        student = new User("Mr.Student");
        student.setIsTeacher(false);
        teacher = new User("Mr.Teacher");
        teacher.setIsTeacher(true);

        String lessonName = "Test Lesson Name";
        String lessonDescription = "Test Lesson Description";
        testId = demoAceDAO.createNewLessonOnDAO(teacher, lessonName, lessonDescription);

        //Populate it with lessons
        for(Map.Entry<Integer, Lesson> x: DemoData.getDemoLessonList().entrySet()){
            demoAceDAO.addLessonToDAO(x.getValue());
        }

    }

    public void tearDown() throws Exception {
        //TODO
    }

    public void testGetLessonsAsList() throws Exception {
        assertNotNull(demoAceDAO.getLessonsAsList());

    }

    public void testCreateLessonViaUserType() throws Exception {
        //TODO
    }

    public void testGetLessonViaId() throws Exception {
        assertEquals(testId, demoAceDAO.getLessonViaId(testId).getLessonId() );
    }

    public void testCreateNewLessonOnDAO() throws Exception {
        //TODO
    }

    public void testAddLessonToDAO() throws Exception {
        //TODO
    }
}