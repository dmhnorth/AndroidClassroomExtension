package com.ace.androidclassroomextension.serverDemoUtilities;

import com.ace.androidclassroomextension.models.Lesson;
import com.ace.androidclassroomextension.models.User;

/**
 * A library of methods and data structures for demonstrating the Ace Client in
 * either Student or Teacher mode
 *
 * Created by Dave on 22/08/2014.
 */
public class demoLibrary {



    public User[] getDemoStudentList() {


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

    public Lesson getDemoLesson() {
        Lesson lesson = null;

        return lesson;
    }

}
