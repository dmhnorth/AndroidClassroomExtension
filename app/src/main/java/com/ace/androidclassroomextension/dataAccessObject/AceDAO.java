package com.ace.androidclassroomextension.dataAccessObject;

import com.ace.androidclassroomextension.models.Lesson;
import com.ace.androidclassroomextension.models.User;

import java.util.List;

/**
 * This DAO is for use with the Android Classroom Extension in order to manage lessons, and
 * deliver them to and from Android devices
 *
 * Created by Dave on 27/08/2014.
 */
public interface AceDAO {

    /**
     * Create a new lesson on the DAO
     * @param teacher lead user of the class
     * @param lessonName name of the lesson
     * @param lessonDescription description of the lesson
     * @return the id of the lesson
     */
    int createNewLessonOnDAO(User teacher, String lessonName, String lessonDescription);

    /**
     * For returning the current lessons on the DAO as a list
     * @return list of lessons currently active
     */
    List<Lesson> getLessonsAsList();

    /**
     * To find an active lesson on the DAO using the lesson ID
     * @param id of the lesson
     * @return the lesson with the given id
     */
    Lesson getLessonViaId(int id);

    /**
     * for adding a lesson that is already created to the DAO via alternative means
     * (eg. Moving lessons from server to server)
     * @param lesson The Lesson you want to add to the AceDAO
     */
    void addLessonToDAO(Lesson lesson);

    /**
     * For finding out how many lessons the AceDAO is
     * @return the amount of lessons on AceDAO
     */
    int getSize();
}
