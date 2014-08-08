package com.ace.androidclassroomextension.creatorActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.ace.androidclassroomextension.R;
import com.ace.androidclassroomextension.userTypes.User;

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

        //TODO repeated code, is there a way around this?
        Intent intent = getIntent();

        user = (User) intent.getParcelableExtra("user");

        //Set teacher status
        user.setIsTeacher(false);
        userType = (TextView) findViewById(R.id.isTeacher);
        userType.setText("Student");

        Log.i("Student Creator||isTeacher|audio|video|: ", Arrays.toString(new Boolean[]{user.getIsTeacher(), user.getAllowAudio(), user.getAllowVideo()}));

        userName = (TextView) findViewById(R.id.userName);
        userName.setText(user.getName());

        profilePicture = (ImageView) findViewById(R.id.user_details_photo);
        profilePicture.setImageURI(user.getProfilePictureUri());
    }
}
