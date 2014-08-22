package com.ace.androidclassroomextension.lessonActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ace.androidclassroomextension.R;
import com.ace.androidclassroomextension.models.Lesson;
import com.ace.androidclassroomextension.models.User;

/**
 * Created by Dave on 22/08/2014.
 */
public class StartLesson extends Activity {

    private User user;
    private Lesson lesson;
    private TextView userName;
    private ImageView profilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_lesson);


        //Gather the details about the user and create a lesson
        Intent intent = getIntent();
        user = intent.getParcelableExtra("user");
        String lessonName = intent.getStringExtra("lessonName");
        String lessonDescription = intent.getStringExtra("lessonDescription");

        //Create the lesson
        lesson = new Lesson(user, lessonName, lessonDescription);

        //Upload the lesson to the server list of lessons in progress
        //TODO Upload the lesson to the server list of lessons in progress

        //Update the view with teacher details
        userName = (TextView) findViewById(R.id.userName);
        userName.setText(user.getName());

        profilePicture = (ImageView) findViewById(R.id.user_details_photo);
        profilePicture.setImageURI(user.getProfilePictureUri());

    }
}
