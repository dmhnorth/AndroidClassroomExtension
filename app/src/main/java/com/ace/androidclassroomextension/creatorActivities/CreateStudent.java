package com.ace.androidclassroomextension.creatorActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.ace.androidclassroomextension.R;
import com.ace.androidclassroomextension.lessonActivities.StartLesson;
import com.ace.androidclassroomextension.models.User;

import java.util.Arrays;

/**
 * The Student creator activity backend
 * Created by Dave on 05/08/2014.
 */
public class CreateStudent extends Activity {

    private User user;
    private TextView userType, userName;
    private ImageView profilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);

        //TODO repeated code, is there a way around this? can this be put in the fragment backend?
        Intent intent = getIntent();

        user = intent.getParcelableExtra("user");

        //Set teacher status
        user.setIsTeacher(false);
        userType = (TextView) findViewById(R.id.isTeacher);
        userType.setText("Student");

        //Set checkboxes
        CheckBox audio = (CheckBox) findViewById(R.id.checkBoxAudio);
        audio.setChecked(user.getAllowAudio());
        CheckBox video = (CheckBox) findViewById(R.id.checkBoxVideo);
        video.setChecked(user.getAllowVideo());

        Log.i("Student Creator||isTeacher|audio|video|: ", Arrays.toString(new Boolean[]{user.getIsTeacher(), user.getAllowAudio(), user.getAllowVideo()}));

        userName = (TextView) findViewById(R.id.userName);
        userName.setText(user.getName());

        profilePicture = (ImageView) findViewById(R.id.user_details_photo);
        profilePicture.setImageURI(user.getProfilePictureUri());
    }


    public void joinLesson(View view) {

        Intent startLessonIntent = new Intent(this, StartLesson.class);

        startLessonIntent.putExtra("user", user);

        //TODO put the lesson choice from the spinner in. probably an ID system

        startActivity(startLessonIntent);

    }
}
