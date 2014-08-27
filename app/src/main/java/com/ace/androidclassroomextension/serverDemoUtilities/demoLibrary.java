package com.ace.androidclassroomextension.serverDemoUtilities;

import android.util.Log;

import com.ace.androidclassroomextension.models.Lesson;
import com.ace.androidclassroomextension.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A library of methods and data structures for demonstrating the Ace Client in
 * either Student or Teacher mode
 *
 * Created by Dave on 22/08/2014.
 */
public class demoLibrary {

    private List<User> demoStudentList;
    private List<Lesson> demoLessonList;

    private User demoTeacher1, demoTeacher2, demoTeacher3;
    private User student1, student2, student3, student4, student5, student6, student7, student8;
    private Lesson lesson1, lesson2, lesson3, lesson4;

    public demoLibrary(){

        if(demoLessonList == null||demoStudentList == null ) {
            generateDemoTeachers();
            generateDemoStudents();
            generateDemoLessons();
        }
        setDemoStudentList(generateDemoStudentList());
        setDemoLessonList(generateDemoLessonList());
    }

    /**
     * Generates some demo Teachers
     */
    private void generateDemoTeachers() {
        //TODO set images for these examples
        demoTeacher1 = new User("Mr A.Teach");
        demoTeacher2 = new User("Mr B.Teach");
        demoTeacher3 = new User("Mr C.Teach");
        demoTeacher1.setIsTeacher(true);
        demoTeacher2.setIsTeacher(true);
        demoTeacher3.setIsTeacher(true);
    }

    /**
     * Generates some demo Students
     */
    private void generateDemoStudents(){
        //TODO set images for these examples
        student1 = new User("Andy Ant");
        student2 = new User("Barry Bear");
        student3 = new User("Chris Cat");
        student4 = new User("Dave Dolphin");
        student5 = new User("Ed Eagle");
        student6 = new User("Fred Fox");
        student7 = new User("Gary Gorilla");
        student8 = new User("Harry Hermit Crab");
    }

    /**
     * generates a demo student array
     */
    private List<User> generateDemoStudentList() {
        return Arrays.asList(student1, student2, student3, student4,
                student5, student6, student7, student8);
    }

    /**
     * generates demo lessons
     */
    private void generateDemoLessons() {
        lesson1 = new Lesson(demoTeacher1, "Algebra Demo", "A demo lesson about Algebra");
        lesson2 = new Lesson(demoTeacher1, "Balloons Demo", "A demo lesson about Balloons");
        lesson3 = new Lesson(demoTeacher1, "Calligraphy Demo", "A demo lesson about Calligraphy");
        lesson4 = new Lesson(demoTeacher1, "Drawing Demo", "A demo lesson about Drawing");
    }

    /**
     * generates a demo lesson array
     */
    private List<Lesson> generateDemoLessonList() {
        return Arrays.asList(lesson1, lesson2, lesson3, lesson4);
    }

    /**
     * For creating a demo lesson with a given user as the teacher
     * @param user that is going to be a teacher or a student for a demo lesson
     * @return full demo lesson with user entered as teacher or student
     */
    public Lesson createDemoLessonViaUserType(User user, String lessonName, String lessonDescription) {

        Lesson result;

        if(user.getIsTeacher()) {
            result = new Lesson(user, lessonName, lessonDescription);
        } else {
            result = lesson1;
            result.addStudent(user);
        }

        List<User> generatedList = getDemoStudentList();
        //populate the lesson with students
        for (User x: generatedList) {
            result.addStudent(x);
        }
        return result;
    }


    //Setters and Getters
    public List<User> getDemoStudentList() {
        return demoStudentList;
    }

    public void setDemoStudentList(List<User> demoStudentList) {
        this.demoStudentList = demoStudentList;
    }

    public List<Lesson> getDemoLessonList() {
        return demoLessonList;
    }

    public void setDemoLessonList(List<Lesson> demoLessonList) {
        this.demoLessonList = demoLessonList;
    }

    public Lesson getDemoLessonWithId(double chosenLessonId) {
        //TODO enable the ability to get a lesson via an ID number
        return null;
    }
}
