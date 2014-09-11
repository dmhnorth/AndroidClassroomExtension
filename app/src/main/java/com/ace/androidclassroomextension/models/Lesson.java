package com.ace.androidclassroomextension.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Lesson class, contains all the details about a lesson
 *
 * Created by Dave on 22/08/2014.
 */
public class Lesson {
    private User teacher;
    private ArrayList<User> students;
    private String lessonName;
    private String lessonDescription;
    private int lessonId;
    private static int dateId = 0;

    public Lesson(User teacher, String lessonName, String lessonDescription) {
        setTeacher(teacher);
        setLessonName(lessonName);
        setLessonDescription(lessonDescription);
        students = null;

        //TODO very basic ID generation, extract this to a method, Add the time to this
        dateId++;
        Random r = new Random();
        setLessonId(Math.abs(r.nextInt()) + dateId);
        Log.i("lesson Created with id: ", String.valueOf(getLessonId()));
    }

    /**
     * to set the teacher
     * @param teacher the teacher required for the lesson
     * @throws IllegalArgumentException if the user entered is not a teacher
     */
    public void setTeacher(User teacher) throws IllegalArgumentException {
        if (teacher.getIsTeacher()) {
            this.teacher = teacher;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * getter for the teacher
     * @return the teacher assigned to this lesson
     */
    public User getTeacher() {
        return teacher;
    }

    /**
     * add a student to a lesson
     *
     * @param student to add
     */
    public void addStudent(User student) {
        if (students == null) {
            students = new ArrayList<User>();
            students.add(student);
        } else {
            students.add(student);
        }
    }

    /**
     * getter for the lesson name
     * @return the lesson name
     */
    public String getLessonName() {
        return lessonName;
    }

    /**
     * setter for the lesson
     * @param lessonName the name of the lesson
     */
    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    /**
     * sets the lesson description
     * @param lessonDescription description of the lesson
     */
    public void setLessonDescription(String lessonDescription) {
        this.lessonDescription = lessonDescription;
    }

    /**
     * getter for the lesson description
     * @return the lesson description
     */
    public String getLessonDescription() {
        return lessonDescription;
    }

    /**
     * returns the students assigned to the lesson as an ArrayList of User
     * @return students users as an ArrayList
     */
    public ArrayList<User> getStudents() {
        return students;
    }

    /**
     * gets the lesson id
     * @return the lesson id
     */
    public int getLessonId() {
        return lessonId;
    }

    /**
     * sets the lesson id
     * @param lessonId the lesson id required
     */
    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    @Override
    public String toString(){
        return getLessonName();
    }
}

