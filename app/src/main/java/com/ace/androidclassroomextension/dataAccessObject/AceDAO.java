package com.ace.androidclassroomextension.dataAccessObject;

import com.ace.androidclassroomextension.models.Lesson;
import com.ace.androidclassroomextension.models.User;

import java.util.List;

/**
 * Created by Dave on 27/08/2014.
 */
public interface AceDAO {

    int createNewLessonOnDAO(User teacher, String lessonName, String lessonDescription);

    List<Lesson> getLessonsAsList();

    Lesson getLessonViaId(int id);

    /**
     * for adding a lesson to the DAO via alternative means
     * (eg. Moving lessons from server to server)
     * @param lesson The Lesson you want to add to the AceDAO
     */
    void addLessonToDAO(Lesson lesson);

}
