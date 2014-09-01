package com.ace.androidclassroomextension.lessonActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.ace.androidclassroomextension.demoUtilities.DemoAceDAO;


/**
 *
 * This class creates the lesson on the server and then updates the view using the
 * new lesson that has been uploaded to the server.
 *
 * Created by Dave on 22/08/2014.
 */
public class StartLesson extends Activity {

    private User user;
    private int lessonForViewId;
    private Lesson lesson;
    private Lesson lessonForView;


    private TextView userName, lessonNameTV, lessonDescriptionTV;
    private ImageView profilePicture;

    private DemoAceDAO aceDAO = new DemoAceDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_lesson);


        //Gather the details about the user and lesson
        Intent intent = getIntent();
        user = intent.getParcelableExtra("user");
        String lessonName = intent.getStringExtra("lessonName");
        String lessonDescription = intent.getStringExtra("lessonDescription");



        //Create the lesson on the server if a teacher
        if(user.getIsTeacher()){
            lessonForViewId = aceDAO.createNewLessonOnDAO(user, lessonName, lessonDescription);
        } else {

            try {
                //Get the Id of the lesson a student wants and add the student to the lesson
        lessonForViewId = getIntent().getIntExtra("lessonId", 0);
                //Add the student to the lesson
                aceDAO.getLessonViaId(lessonForViewId).addStudent(user);
            } catch (Exception e){
                e.printStackTrace();
            }
        }




        //Retrieve the lesson for the View
        if(lessonForView == null){
            lessonForView = aceDAO.getLessonViaId(lessonForViewId);
            Log.i("LessonForView: ", String.valueOf(lessonForView.getLessonId()));
        }



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

        //Show the lesson ID
        Toast.makeText(this, "Lesson ID: "+ lessonForView.getLessonId(), Toast.LENGTH_SHORT).show();
    }

    private void updateStudentListView() {

        final ListAdapter studentListAdapter = new StudentListAdapter(this, lessonForView.getStudents());

        ListView studentListView = (ListView) findViewById(R.id.studentListView);

        studentListView.setAdapter(studentListAdapter);

        //For when a User is picked within the list
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
