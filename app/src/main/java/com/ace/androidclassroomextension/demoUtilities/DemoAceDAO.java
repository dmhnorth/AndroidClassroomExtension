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
    private Map<Integer, Lesson> lessonMap = new HashMap<Integer, Lesson>();

    public DemoAceDAO(){

        for(Map.Entry<Integer, Lesson> x: DemoData.getDemoLessonList().entrySet()){
            addLessonToDAO(x.getValue());
        }
    }


    @Override
    public List<Lesson> getLessonsAsList() {
        ArrayList<Lesson> result = new ArrayList<Lesson>();
        for(Map.Entry<Integer, Lesson> x: lessonMap.entrySet()){
            result.add(x.getValue());
        }
        return result;
    }


    @Override
    public Lesson getLessonViaId(int id) throws NoSuchElementException {

        Log.i("Attempting to getLessonViaId", String.valueOf(id));

        if (lessonMap.get(id) == null) {
        Log.i("Lesson doesn't exist in DAO with ID", String.valueOf(id));
            throw new NoSuchElementException();
        } else {
        Log.i("Successfully retrieved Lesson", String.valueOf(id));
            return lessonMap.get(id);
        }
    }



    @Override
    public int createNewLessonOnDAO(User teacher, String lessonName, String lessonDescription) {
        Lesson lesson = new Lesson(teacher, lessonName, lessonDescription);

        //Add Demo students for this new lesson
        DemoData.populateLessonWithDemoStudents(lesson);

        lessonMap.put(lesson.getLessonId(), lesson);
        Log.i("createdNewLessonOnDAO", String.valueOf(getLessonViaId(lesson.getLessonId()).getLessonId()));

        return lesson.getLessonId();
    }

    @Override
    public void addLessonToDAO(Lesson lesson){
        createNewLessonOnDAO(lesson.getTeacher(), lesson.getLessonName(), lesson.getLessonDescription());
    }
}
