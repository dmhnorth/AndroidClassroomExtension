package com.ace.androidclassroomextension.lessonActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ace.androidclassroomextension.R;
import com.ace.androidclassroomextension.models.User;

/**
 * Created by Dave on 25/08/2014.
 */
public class UserDetailsPopupActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_popup);

        Intent intent = getIntent();

        User user = intent.getParcelableExtra("user");

        //Update the view
        TextView isTeacher = (TextView) findViewById(R.id.isTeacher);
        if(user.getIsTeacher()) {
            isTeacher.setText(getString(R.string.teacher));
        } else {
            isTeacher.setText(getString(R.string.student));
        }

        TextView userName = (TextView) findViewById(R.id.userName);
        userName.setText(user.getName());

        TextView question = (TextView) findViewById(R.id.question);
        question.setText(user.getCurrentQuestion());

        ImageView imageView = (ImageView) findViewById(R.id.studentImage);
        //TODO these are null because they are currently not initiated
//        imageView.setImageURI(user.getProfilePictureUri());
    }

    /**
     * closes the user information viewer and returns to the current lesson
     * @param view
     */
    public void returnToLesson(View view) {
        this.finish();
    }
}
