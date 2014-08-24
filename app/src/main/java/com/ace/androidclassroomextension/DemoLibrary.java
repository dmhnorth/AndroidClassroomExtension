package com.ace.androidclassroomextension.serverDemoUtilities;

import android.util.Log;

import com.ace.androidclassroomextension.models.Lesson;
import com.ace.androidclassroomextension.models.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A library of methods and data structures for demonstrating the Ace Client in
 * either Student or Teacher mode
 *
 * Created by Dave on 22/08/2014.
 */
public class DemoLibrary {
    private ArrayList<User> demoStudentList;
    private User demoTeacher;

    public DemoLibrary(){
        generateTeacher();
    }

    private void generateTeacher() {
        demoTeacher = new User("Mr.Teach");
        demoTeacher.setIsTeacher(true);
    }

    public User[] generateDemoStudentList() {
        //TODO set images for these examples
        User student1 = new User("Andy Ant");
        User student2 = new User("Barry Bear");
        User student3 = new User("Chris Cat");
        User student4 = new User("Dave Dolphin");
        User student5 = new User("Ed Eagle");
        User student6 = new User("Fred Fox");
        User student7 = new User("Gary Gorilla");
        User student8 = new User("Harry Hermit Crab");

        return new User[]{student1, student2, student3, student4,
                student5, student6, student7, student8,};
    }

    /**
     * For generating a demo lesson with a given user as the teacher
     * @param user that is going to be a teacher for the demo lesson
     * @return full demo lesson with user entered as teacher
     */
    public Lesson getDemoLesson(User user, String lessonName, String lessonDescription) {

        Lesson lesson;

        if(user.getIsTeacher()) {
            lesson = new Lesson(user, lessonName, lessonDescription);
        } else {
            lesson = new Lesson(demoTeacher, "A demo lesson", "A description of this lesson");
            lesson.addStudent(user);
        }


        User[] generatedList = generateDemoStudentList();
        //populate the lesson with students
        for (User x: generatedList) {
            lesson.addStudent(x);
        }
        Log.i("Demo Lesson created: ", "Teacher:" + lesson.getTeacher().getName() + " Description: " + lesson.getLessonDescription()
                + " Students:" + lesson.getStudents().toString());
        return lesson;
    }

    public void setDemoStudentList(ArrayList<User> demoStudentList) {
        this.demoStudentList = demoStudentList;
    }
}
