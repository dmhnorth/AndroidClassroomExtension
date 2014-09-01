package com.ace.androidclassroomextension.serverDemoUtilities;

import android.util.Log;
import android.widget.Toast;

import com.ace.androidclassroomextension.dataAccessObject.AceDAO;
import com.ace.androidclassroomextension.models.Lesson;
import com.ace.androidclassroomextension.models.User;
import com.ace.androidclassroomextension.utilities.DemoData;

import java.util.List;

/**
 * A library of methods and data structures for demonstrating the Ace Client in
 * either Student or Teacher mode
 *
 * Created by Dave on 22/08/2014.
 */
public class DemoAceDAO implements AceDAO {

    private List<User> studentList = DemoData.getDemoStudentList();
    private List<Lesson> lessonList = DemoData.getDemoLessonList();

    public List<Lesson> getLessonList() {
        return lessonList;
    }

    /**
     * Creates a lesson with a given user as the teacher
     * @param user that is going to be a teacher or a student for a demo lesson
     * @return full demo lesson with user entered as teacher or student
     */
    public Lesson createLessonViaUserType(User user, String lessonName, String lessonDescription) {

        Lesson result = null;
        if(user.getIsTeacher()) {
            result = new Lesson(user, lessonName, lessonDescription);
        }
        DemoData.populateLessonWithDemoStudents(result);
        return result;
    }

    public Lesson getLessonViaId(int chosenLessonId) throws NullPointerException {

        for (Lesson x : lessonList)
            if (x.getLessonId() == chosenLessonId) {
                Log.i("LessonId found getLessonViaId in AceDAO: ", String.valueOf(x.getLessonId()));
                return x;
            }
        Log.e("getLessonViaId" , "No lesson with matching ID found");

        return null;

    }
    public int createNewLessonOnDAO(User teacher, String lessonName, String lessonDescription) {
        Lesson lesson = new Lesson(teacher, lessonName, lessonDescription);

        //Add Demo students for this new lesson
        DemoData.populateLessonWithDemoStudents(lesson);

        lessonList.add(lesson);
        return lesson.getLessonId();
    }
}
