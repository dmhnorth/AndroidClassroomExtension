package com.ace.androidclassroomextension.models;

import junit.framework.Assert;
import junit.framework.TestCase;


/**
 * JUnit 3 tests for LessonImpl
 */
public class LessonImplTest extends TestCase {

    private User teacher;
    private User student;

    private String teacherName = "Mr.Teach";
    private String lessonName = "Lesson";
    private String lessonDescription = "A lesson";
    private String studentName = "Student";
    private Lesson lesson;

    public void setUp() throws Exception {
        super.setUp();

        teacher = new UserImpl(teacherName);
        teacher.setIsTeacher(Boolean.TRUE);

        student = new UserImpl(studentName);

        lesson = new LessonImpl(teacher, lessonName, lessonDescription);
    }

    public void tearDown() throws Exception {
        teacher = null;
        student = null;
        lesson = null;
    }

    public void testSetTeacher() throws Exception {
        assertEquals(lesson.getTeacher(), teacher);
    }

    public void testSetTeacherNotTeacher() {
        try {
        Lesson lesson = new LessonImpl(student, lessonName, lessonDescription);
            Assert.fail("IllegalArgumentException Thrown");
        }
        catch (IllegalArgumentException i){
            //Success
        }


    }

    public void testAddStudent() throws Exception {
        lesson.addStudent(student);
        assertTrue(lesson.getStudents().contains(student));
    }

    public void testAddTeacherToStudents() throws Exception {
        try {
            lesson.addStudent(teacher);
            Assert.fail("IllegalArgumentException Thrown");
        }
        catch (IllegalArgumentException i){
            //Success
        }
    }

    public void testToString() throws Exception {
        assertEquals(lessonName, lesson.getLessonName());
    }
}