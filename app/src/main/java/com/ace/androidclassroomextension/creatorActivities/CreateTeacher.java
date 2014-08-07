package com.ace.androidclassroomextension.creatorActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ace.androidclassroomextension.R;
import com.ace.androidclassroomextension.userTypes.User;

/**
 * The Teacher creator backend
 * Created by Dave on 05/08/2014.
 */
public class CreateTeacher extends Activity {

    private TextView userName;
    private ImageView profilePicture;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_teacher);

        Intent intent = getIntent();

        user = (User) intent.getParcelableExtra("user");

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
}
