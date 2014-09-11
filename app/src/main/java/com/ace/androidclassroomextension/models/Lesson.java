package com.ace.androidclassroomextension.models;

import java.util.ArrayList;

/**
 * Created by Dave on 11/09/2014.
 */
public interface Lesson {
    /**
     * to set the teacher
     * @param teacher the teacher required for the lesson
     * @throws IllegalArgumentException if the user entered is not a teacher
     */
    void setTeacher(User teacher) throws IllegalArgumentException;

    /**
     * getter for the teacher
     * @return the teacher assigned to this lesson
     */
    User getTeacher();

    /**
     * add a student to a lesson
     *
     * @param student to add
     */
    void addStudent(User student);

    /**
     * getter for the lesson name
     * @return the lesson name
     */
    String getLessonName();

    /**
     * setter for the lesson
     * @param lessonName the name of the lesson
     */
    void setLessonName(String lessonName);

    /**
     * sets the lesson description
     * @param lessonDescription description of the lesson
     */
    void setLessonDescription(String lessonDescription);

    /**
     * getter for the lesson description
     * @return the lesson description
     */
    String getLessonDescription();

    /**
     * returns the students assigned to the lesson as an ArrayList of User
     * @return students users as an ArrayList
     */
    ArrayList<User> getStudents();

    /**
     * gets the lesson id
     * @return the lesson id
     */
    int getLessonId();

    /**
     * sets the lesson id
     * @param lessonId the lesson id required
     */
    void setLessonId(int lessonId);

    @Override
    String toString();
}
