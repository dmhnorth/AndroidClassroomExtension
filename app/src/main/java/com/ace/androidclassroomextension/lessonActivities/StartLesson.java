package com.ace.androidclassroomextension.lessonActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private Lesson lessonForView;

    private TextView userName, lessonDescriptionTV;
    private ImageView profilePicture;


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
        //TODO Upload the lesson to the server lesson list as JSONObject


        //TODO replace demo library retrieve lesson from the server as JSONObject

            //DEMO LIBRARY lesson using teacher
            lessonForView = demoLibrary.getDemoLesson(lesson.getTeacher());



        //Update the View details
        userName = (TextView) findViewById(R.id.userName);
        userName.setText(lessonForView.getTeacher().getName());

        profilePicture = (ImageView) findViewById(R.id.user_details_photo);
        profilePicture.setImageURI(lessonForView.getTeacher().getProfilePictureUri());

        lessonDescriptionTV = (TextView) findViewById(R.id.lessonDescription);
        lessonDescriptionTV.setText(lessonForView.getLessonDescription());

        //Populate the ListView
        updateStudentListView();


    }

    private void updateStudentListView() {
        final ListAdapter studentListAdapter = new StudentListAdapter(this, lessonForView.getStudents());

        ListView studentListView = (ListView) findViewById(R.id.studentListView);

        studentListView.setAdapter(studentListAdapter);

        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                User student = (User) adapterView.getItemAtPosition(position);
                String studentPicked = "Picked " + student.getName()
                        + ": Student info fragment to appear here";
                Toast.makeText(StartLesson.this, studentPicked, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
