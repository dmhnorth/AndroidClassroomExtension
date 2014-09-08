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
import com.ace.androidclassroomextension.lessonActivities.StartLesson;
import com.ace.androidclassroomextension.models.Lesson;
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
     * Gets the lesson name entered by the user.
     * @return the lesson name
     */
    private String getLessonName(){
        EditText lessonNameET = (EditText) findViewById(R.id.lesson_name);
        return String.valueOf(lessonNameET.getText());
    }

    /**
     * Gets the lesson description entered by the user.
     * @return the lesson description
     */
    private String getLessonDescription(){
        EditText descriptionET = (EditText) findViewById(R.id.lessonDescription);
        return String.valueOf(descriptionET.getText());
    }

    /**
     * For creating the lesson once the user has entered all the details required
     * @param view from the current activity
     */
    public void confirmLessonCreation(View view) {
        //Check user has entered required details
        if(getLessonDescription().isEmpty() || getLessonName().isEmpty()){
            Toast.makeText(this, "Enter a Lesson description and name", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Lesson Details OK!", Toast.LENGTH_SHORT).show();
            Intent startLessonIntent = new Intent(this, StartLesson.class);

            //Pass details to intent
            startLessonIntent.putExtra("user", user);
            startLessonIntent.putExtra("lessonName", getLessonName());
            startLessonIntent.putExtra("lessonDescription", getLessonDescription());

            startActivity(startLessonIntent);
        }
    }
}
