package com.ace.androidclassroomextension.models;

import android.util.Log;

import java.util.ArrayList;

/**
 * Implementation of the Lesson class
 *
 * Created by Dave on 22/08/2014.
 */
public class LessonImpl implements Lesson {
    private User teacher;
    private ArrayList<User> students;
    private String lessonName;
    private String lessonDescription;
    private int lessonId;

    public LessonImpl(User teacher, String lessonName, String lessonDescription) {
        setTeacher(teacher);
        setLessonName(lessonName);
        setLessonDescription(lessonDescription);
        students = null;
        setLessonId(IDGenerator.getNewId());
        Log.i("lesson Created with id: ", String.valueOf(getLessonId()));
    }

    @Override
    public void setTeacher(User teacher) throws IllegalArgumentException {
        if (teacher.getIsTeacher()) {
            this.teacher = teacher;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public User getTeacher() {
        return teacher;
    }

    @Override
    public void addStudent(User student) throws IllegalArgumentException {
        if(student.getIsTeacher()){
            throw new IllegalArgumentException();
        }

        if (students == null) {
            students = new ArrayList<User>();
            students.add(student);
        } else {
            students.add(student);
        }
    }

    @Override
    public String getLessonName() {
        return lessonName;
    }

    @Override
    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    @Override
    public void setLessonDescription(String lessonDescription) {
        this.lessonDescription = lessonDescription;
    }

    @Override
    public String getLessonDescription() {
        return lessonDescription;
    }

    @Override
    public ArrayList<User> getStudents() {
        return students;
    }

    @Override
    public int getLessonId() {
        return lessonId;
    }

    @Override
    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    @Override
    public String toString(){
        return getLessonName();
    }
}

