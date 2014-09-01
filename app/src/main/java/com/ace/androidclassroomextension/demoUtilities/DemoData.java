package com.ace.androidclassroomextension.demoUtilities;

import android.util.Log;

import com.ace.androidclassroomextension.dataAccessObject.AceDAO;
import com.ace.androidclassroomextension.models.Lesson;
import com.ace.androidclassroomextension.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A set of demonstration Lesson and Student Data
 *
 * Created by Dave on 27/08/2014.
 */
public class DemoData {

    public static Map<Integer, Lesson> getDemoLessonList() {

        Log.i("getDemoLessonList()", "Lesson list generated");

        //Generate Teachers
        User demoTeacher1 = new User("Mr A.Teach");
        demoTeacher1.setIsTeacher(true);
        User demoTeacher2 = new User("Mr B.Teach");
        demoTeacher2.setIsTeacher(true);
        User demoTeacher3 = new User("Mr C.Teach");
        demoTeacher3.setIsTeacher(true);
        User demoTeacher4 = new User("Mr D.Teach");
        demoTeacher4.setIsTeacher(true);

        //Generate Lessons
        Lesson lesson1 = new Lesson(demoTeacher1, "Algebra Demo", "A demo lesson about Algebra");
        populateLessonWithDemoStudents(lesson1);

        Lesson lesson2 = new Lesson(demoTeacher2, "Balloons Demo", "A demo lesson about Balloons");
        populateLessonWithDemoStudents(lesson2);

        Lesson lesson3 = new Lesson(demoTeacher3, "Calligraphy Demo", "A demo lesson about Calligraphy");
        populateLessonWithDemoStudents(lesson3);

        Lesson lesson4 = new Lesson(demoTeacher4, "Drawing Demo", "A demo lesson about Drawing");
        populateLessonWithDemoStudents(lesson4);

        Map<Integer, Lesson> result = new HashMap<Integer, Lesson>();
        result.put(lesson1.getLessonId(),lesson1);
        result.put(lesson2.getLessonId(),lesson2);
        result.put(lesson3.getLessonId(),lesson3);
        result.put(lesson4.getLessonId(),lesson4);

        return result;

    }


    public static List<User> getDemoStudentList() {

        Log.i("getDemoStudentList()", "DemoStudentlist generated");
        //Generate Students
        User student1 = new User("Andy Ant");
        User student2 = new User("Barry Bear");
        User student3 = new User("Chris Cat");
        User student4 = new User("Dave Dolphin");
        User student5 = new User("Ed Eagle");
        User student6 = new User("Fred Fox");
        User student7 = new User("Gary Gorilla");
        User student8 = new User("Harry Hermit Crab");
        return new ArrayList<User>(Arrays.asList(student1, student2, student3, student4,
                student5, student6, student7, student8));


    }

        /**
         * Demo method for populating a lesson with some students
         * @param result lesson full of students
         */
    public static void populateLessonWithDemoStudents(Lesson result) {
            List<User> generatedList = getDemoStudentList();
            //populate the lesson with students
            for (User x: generatedList) {
                result.addStudent(x);
            }
    }
}


