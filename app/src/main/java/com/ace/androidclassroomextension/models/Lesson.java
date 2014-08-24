package com.ace.androidclassroomextension.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * The Lesson class, contains all the information about a lesson
 *
 * Created by Dave on 22/08/2014.
 */
public class Lesson {
    private User teacher;
    private ArrayList<User> students;
    private String lessonName;
    private String lessonDescription;

    public Lesson(User user, String lessonName, String lessonDescription) {
        setTeacher(user);
        setLessonName(lessonName);
        setLessonDescription(lessonDescription);
        students = null;
    }

    public void setTeacher(User teacher) throws IllegalArgumentException {
        if (teacher.getIsTeacher()) {
            this.teacher = teacher;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     *
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

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public void setLessonDescription(String lessonDescription) {
        this.lessonDescription = lessonDescription;
    }

    public String getLessonDescription() {
        return lessonDescription;
    }

    public ArrayList<User> getStudents() {
        return students;
    }
}

