package com.ace.androidclassroomextension.serverDemoUtilities;

import com.ace.androidclassroomextension.dataAccessObject.DAO;
import com.ace.androidclassroomextension.models.Lesson;
import com.ace.androidclassroomextension.models.User;

import java.util.Arrays;
import java.util.List;

/**
 * A library of methods and data structures for demonstrating the Ace Client in
 * either Student or Teacher mode
 *
 * Created by Dave on 22/08/2014.
 */
public class DemoLibrary implements DAO {

    private List<User> studentList;
    private List<Lesson> lessonList;


    public DemoLibrary(){
        generateDemoDataSet();
    }

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
     * Generates a demonstration DataSet for the DAO
     */
    private void generateDemoDataSet() {
        //Generate Teachers
        User demoTeacher1 = new User("Mr A.Teach");
        demoTeacher1.setIsTeacher(true);
        User demoTeacher2 = new User("Mr B.Teach");
        demoTeacher2.setIsTeacher(true);
        User demoTeacher3 = new User("Mr C.Teach");
        demoTeacher3.setIsTeacher(true);
        User demoTeacher4 = new User("Mr D.Teach");
        demoTeacher4.setIsTeacher(true);

        //Generate Students
        User student1 = new User("Andy Ant");
        User student2 = new User("Barry Bear");
        User student3 = new User("Chris Cat");
        User student4 = new User("Dave Dolphin");
        User student5 = new User("Ed Eagle");
        User student6 = new User("Fred Fox");
        User student7 = new User("Gary Gorilla");
        User student8 = new User("Harry Hermit Crab");
        setStudentList(Arrays.asList(student1, student2, student3, student4,
                student5, student6, student7, student8));

        //Generate Lessons
        Lesson lesson1 = new Lesson(demoTeacher1, "Algebra Demo", "A demo lesson about Algebra");
        Lesson lesson2 = new Lesson(demoTeacher2, "Balloons Demo", "A demo lesson about Balloons");
        Lesson lesson3 = new Lesson(demoTeacher3, "Calligraphy Demo", "A demo lesson about Calligraphy");
        Lesson lesson4 = new Lesson(demoTeacher4, "Drawing Demo", "A demo lesson about Drawing");
        setLessonList(Arrays.asList(lesson1, lesson2, lesson3, lesson4));

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



    public Lesson getDemoLessonViaId(double chosenLessonId) {

        //TODO this needs to be much more efficient
        for (Lesson x : getLessonList()){
            if (x.getLessonId() == chosenLessonId)
                return x;
        }
        return null;
    }

    public double createNewLessonOnServer(User user, String lessonName, String lessonDescription) {
        Lesson lesson = new Lesson(user, lessonName, lessonDescription);
        List<Lesson> lessons = getLessonList();
        lessons.add(lesson);
        setLessonList(lessons);
        return lesson.getLessonId();
    }
}
