package com.ace.androidclassroomextension.serverDemoUtilities;

import android.util.Log;

import com.ace.androidclassroomextension.models.Lesson;
import com.ace.androidclassroomextension.models.User;

import java.util.ArrayList;

/**
 * A library of methods and data structures for demonstrating the Ace Client in
 * either Student or Teacher mode
 *
 * Created by Dave on 22/08/2014.
 */
public class demoLibrary {

    private User[] demoStudentList;
    private Lesson[] demoLessonList;

    private User demoTeacher1, demoTeacher2, demoTeacher3;
    private User student1, student2, student3, student4, student5, student6, student7, student8;
    private Lesson lesson1, lesson2, lesson3, lesson4;

    public demoLibrary(){
        generateDemoTeachers();
        generateDemoStudents();
        generateDemoLessons();
        
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
    private User[] generateDemoStudentList() {
        return new User[]{student1, student2, student3, student4,
                student5, student6, student7, student8,};
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
    private Lesson[] generateDemoLessonList() {
        return new Lesson[]{lesson1, lesson2, lesson3, lesson4};
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

        User[] generatedList = generateDemoStudentList();
        //populate the lesson with students
        for (User x: generatedList) {
            result.addStudent(x);
        }
        return result;
    }


    //Setters and Getters
    public User[] getDemoStudentList() {
        return demoStudentList;
    }

    public void setDemoStudentList(User[] demoStudentList) {
        this.demoStudentList = demoStudentList;
    }

    public Lesson[] getDemoLessonList() {
        return demoLessonList;
    }

    public void setDemoLessonList(Lesson[] demoLessonList) {
        this.demoLessonList = demoLessonList;
    }
}
