package com.ace.androidclassroomextension.demoUtilities;

import android.util.Log;

import com.ace.androidclassroomextension.dataAccessObject.AceDAO;
import com.ace.androidclassroomextension.models.Lesson;
import com.ace.androidclassroomextension.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * A library of methods and data structures for demonstrating the Ace Client in
 * either Student or Teacher mode
 *
 * Created by Dave on 22/08/2014.
 */
public class DemoAceDAO implements AceDAO {

    private List<User> studentList = DemoData.getDemoStudentList();
    private Map<Integer, Lesson> lessons = new HashMap<Integer, Lesson>();

    public DemoAceDAO(){

        for(Map.Entry<Integer, Lesson> x: DemoData.getDemoLessonList().entrySet()){
            addLessonToDAO(x.getValue());
        }
    }


    public List<Lesson> getLessonsAsList() {
        ArrayList<Lesson> result = new ArrayList<Lesson>();
        for(Map.Entry<Integer, Lesson> x: lessons.entrySet()){
            result.add(x.getValue());
        }
        return result;
    }

    /**
     * Creates a lesson with a given user as the teacher
     *
     * @param user that is going to be a teacher or a student for a demo lesson
     * @return full demo lesson with user entered as teacher or student
     */
    public Lesson createLessonViaUserType(User user, String lessonName, String lessonDescription) {

        Lesson result = null;
        if (user.getIsTeacher()) {
            result = new Lesson(user, lessonName, lessonDescription);
        }
        DemoData.populateLessonWithDemoStudents(result);
        return result;
    }

    public Lesson getLessonViaId(int chosenLessonId) throws NoSuchElementException {

        Log.i("Attempting to getLessonViaId", String.valueOf(chosenLessonId));

        if (lessons.get(chosenLessonId) == null) {
        Log.i("Lesson doesn't exist in DAO with ID", String.valueOf(chosenLessonId));
            throw new NoSuchElementException();
        } else {
        Log.i("Successfully retrieved Lesson", String.valueOf(chosenLessonId));
            return lessons.get(chosenLessonId);
        }
    }



    public int createNewLessonOnDAO(User teacher, String lessonName, String lessonDescription) {
        Lesson lesson = new Lesson(teacher, lessonName, lessonDescription);

        //Add Demo students for this new lesson
        DemoData.populateLessonWithDemoStudents(lesson);

        lessons.put(lesson.getLessonId(), lesson);
        Log.i("createdNewLessonOnDAO", String.valueOf(getLessonViaId(lesson.getLessonId()).getLessonId()));

        return lesson.getLessonId();
    }

    /**
     * for adding a lesson to the DAO via alternative means
     * (eg. Moving lessons from server to server)
     * @param lesson The Lesson you want to add to the AceDAO
     */
    public void addLessonToDAO(Lesson lesson){
        createNewLessonOnDAO(lesson.getTeacher(), lesson.getLessonName(), lesson.getLessonDescription());
    }
}
