package com.ace.androidclassroomextension.lessonActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ace.androidclassroomextension.R;
import com.ace.androidclassroomextension.models.Lesson;
import com.ace.androidclassroomextension.models.User;
import com.ace.androidclassroomextension.serverDemoUtilities.DemoLibrary;


/**
 * Created by Dave on 22/08/2014.
 */
public class StartLesson extends Activity {

    private User user;
    private Lesson lesson;
    private TextView userName;
    private ImageView profilePicture;
    private Lesson lessonForView;

    private DemoLibrary demoLibrary = new DemoLibrary();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_lesson);


        //Gather the details about the user and create a lesson
        Intent intent = getIntent();
        user = intent.getParcelableExtra("user");
        String lessonName = intent.getStringExtra("lessonName");
        String lessonDescription = intent.getStringExtra("lessonDescription");


        //Create the lesson for upload
        lesson = new Lesson(user, lessonName, lessonDescription);
        //Upload the lesson to the server list of lessons in progress
        //TODO Upload the lesson to the server list of lessons in progress


        //TODO retrieve lesson from the server if haven't already using JSON

            //DEMO LIBRARY
            lessonForView = demoLibrary.getDemoLesson(lesson.getTeacher());



        //Update the View details
        userName = (TextView) findViewById(R.id.userName);
        userName.setText(lessonForView.getTeacher().getName());

        profilePicture = (ImageView) findViewById(R.id.user_details_photo);
        profilePicture.setImageURI(lessonForView.getTeacher().getProfilePictureUri());



        //Populate the ListView
        final ListAdapter studentListAdapter = new StudentListAdapter(this, (User[]) lessonForView.getStudents().toArray());

        ListView studentListView = (ListView) findViewById(R.id.studentListView);

        studentListView.setAdapter(studentListAdapter);


    }
}
