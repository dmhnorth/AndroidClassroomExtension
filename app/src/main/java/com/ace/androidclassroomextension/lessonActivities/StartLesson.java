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

import com.ace.androidclassroomextension.R;
import com.ace.androidclassroomextension.models.Lesson;
import com.ace.androidclassroomextension.models.User;


/**
 *
 * This class creates the lesson on the server and then updates the view using the
 * new lesson that has been uploaded to the server.
 *
 * Created by Dave on 22/08/2014.
 */
public class StartLesson extends Activity {

    private User user;
    private Lesson lesson;
    private Lesson lessonForView;

    private TextView userName, lessonNameTV, lessonDescriptionTV;
    private ImageView profilePicture;


    private com.ace.androidclassroomextension.serverDemoUtilities.demoLibrary demoLibrary = new com.ace.androidclassroomextension.serverDemoUtilities.demoLibrary();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_lesson);


        //Gather the details about the user and lesson
        Intent intent = getIntent();
        user = intent.getParcelableExtra("user");
        String lessonName = intent.getStringExtra("lessonName");
        String lessonDescription = intent.getStringExtra("lessonDescription");

        //Create the lesson for upload if a teacher
        if(user.getIsTeacher()){
        lesson = new Lesson(user, lessonName, lessonDescription);
        //Upload the lesson to the server list of lessons in progress
        //TODO Upload the lesson to the server lesson list as JSONObject
        }


        //TODO replace demo library retrieve lesson from the server as JSONObject and cast for LessonForView
            //DEMO LIBRARY lesson with user, can determine if Teacher or student
            lessonForView = demoLibrary.createDemoLessonViaUserType(user, lessonName, lessonDescription);



        //Update the View only using lessonForView Object
        userName = (TextView) findViewById(R.id.userName);
        userName.setText(lessonForView.getTeacher().getName());

        profilePicture = (ImageView) findViewById(R.id.user_details_photo);
        profilePicture.setImageURI(lessonForView.getTeacher().getProfilePictureUri());

        lessonNameTV = (TextView) findViewById(R.id.teacher_lesson_name);
        lessonNameTV.setText(lessonForView.getLessonName());

        lessonDescriptionTV = (TextView) findViewById(R.id.teacher_lesson_description);
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

                //Generate popup
                Intent userDetailsIntent = new Intent(StartLesson.this, UserDetailsPopupActivity.class);
                userDetailsIntent.putExtra("user", student);
                startActivity(userDetailsIntent);
            }
        });

    }
}
