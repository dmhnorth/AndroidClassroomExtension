package com.ace.androidclassroomextension.creatorActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ace.androidclassroomextension.R;
import com.ace.androidclassroomextension.models.User;

import java.util.Arrays;

/**
 * The Teacher creator backend
 * Created by Dave on 05/08/2014.
 */
public class CreateTeacher extends Activity {

    private TextView userType, userName;
    private ImageView profilePicture;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_teacher);

        Intent intent = getIntent();

        user = intent.getParcelableExtra("user");

        //Set teacher status and update view
        user.setIsTeacher(true);
        userType = (TextView) findViewById(R.id.isTeacher);
        userType.setText("Teacher");

        //Set checkboxes
        CheckBox audio = (CheckBox) findViewById(R.id.checkBoxAudio);
        audio.setChecked(user.getAllowAudio());
        CheckBox video = (CheckBox) findViewById(R.id.checkBoxVideo);
        video.setChecked(user.getAllowVideo());


        Log.i("Teacher Creator||isTeacher|audio|video|: ", Arrays.toString(new Boolean[]{user.getIsTeacher(), user.getAllowAudio(), user.getAllowVideo()}));

        userName = (TextView) findViewById(R.id.userName);
        userName.setText(user.getName());

        profilePicture = (ImageView) findViewById(R.id.user_details_photo);
        profilePicture.setImageURI(user.getProfilePictureUri());
    }

    /**
     * Gets the lesson description entered by the user.
     * @return the lesson description as a String
     */
    private String getLessonDescription(){
        EditText descriptionET = (EditText) findViewById(R.id.lessonDescription);
        return String.valueOf(descriptionET.getText());
    }

    public void confirmLessonCreation(View view) {
        if(getLessonDescription().isEmpty()){
            Toast.makeText(this, "Enter a Lesson description", Toast.LENGTH_SHORT).show();
        } else {
            user.setLessonDescription(getLessonDescription());
            Toast.makeText(this, "Creating Lesson", Toast.LENGTH_SHORT).show();
            //TODO start the lesson activity
        }
    }
}
