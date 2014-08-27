package com.ace.androidclassroomextension.serverDemoUtilities;

import android.util.Log;

import com.ace.androidclassroomextension.dataAccessObject.DAO;
import com.ace.androidclassroomextension.models.Lesson;
import com.ace.androidclassroomextension.models.User;
import com.ace.androidclassroomextension.utilities.DemoData;

import java.util.Arrays;
import java.util.List;

/**
 * A library of methods and data structures for demonstrating the Ace Client in
 * either Student or Teacher mode
 *
 * Created by Dave on 22/08/2014.
 */
public class DemoLibrary implements DAO {

    private List<User> studentList = DemoData.getStudentList();
    private List<Lesson> lessonList = DemoData.getLessonList();

    //Setters and Getters
    public List<User> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<User> studentList) {
        this.studentList = studentList;
    }

    public List<Lesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    /**
     * For creating a demo lesson with a given user as the teacher
     * @param user that is going to be a teacher or a student for a demo lesson
     * @return full demo lesson with user entered as teacher or student
     */
    public Lesson createDemoLessonViaUserType(User user, String lessonName, String lessonDescription) {

        Lesson result = null;
        if(user.getIsTeacher()) {
            result = new Lesson(user, lessonName, lessonDescription);
        }
        populateLessonWithDemoStudents(result);
        return result;
    }

    /**
     * Demo method for populating a lesson with some students
     * @param result lesson full of students
     */
    private void populateLessonWithDemoStudents(Lesson result) {
        List<User> generatedList = getStudentList();
        //populate the lesson with students
        for (User x: generatedList) {
            result.addStudent(x);
        }

    }



    public Lesson getDemoLessonViaId(int chosenLessonId) {

        //TODO this needs to be much more efficient
        for (Lesson x : lessonList){
            if (x.getLessonId() == chosenLessonId)
                Log.i("LessonId found getDemoLessonViaId in DemoLibrary: ", String.valueOf(x.getLessonId()));
                return x;
        }
        return null;
    }

    public int createNewLessonOnServer(User user, String lessonName, String lessonDescription) {
        Lesson lesson = new Lesson(user, lessonName, lessonDescription);
        lessonList.add(lesson);
        return lesson.getLessonId();
    }
}
