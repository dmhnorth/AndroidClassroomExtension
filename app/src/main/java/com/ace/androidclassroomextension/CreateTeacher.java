package com.ace.androidclassroomextension;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.ace.androidclassroomextension.userTypes.Teacher;
import com.ace.androidclassroomextension.userTypes.User;

/**
 * The Teacher creator backend
 * Created by Dave on 05/08/2014.
 */
public class CreateTeacher extends Activity {
    private Teacher teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_teacher);

        Intent intent = getIntent();

        User user = (User) intent.getSerializableExtra("user");

        teacher = new Teacher(user.getName(), user.getProfilePictureUri());

        TextView userName = (TextView) findViewById(R.id.userName);
        userName.setText(teacher.getName());


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
