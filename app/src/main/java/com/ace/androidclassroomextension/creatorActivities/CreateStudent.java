package com.ace.androidclassroomextension.creatorActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ace.androidclassroomextension.R;
import com.ace.androidclassroomextension.dataAccessObject.AceDAO;
import com.ace.androidclassroomextension.lessonActivities.StartLesson;
import com.ace.androidclassroomextension.models.Lesson;
import com.ace.androidclassroomextension.models.User;
import com.ace.androidclassroomextension.demoUtilities.DemoAceDAO;

import java.util.Arrays;
import java.util.List;

/**
 * The Student creator activity backend
 * Created by Dave on 05/08/2014.
 */
public class CreateStudent extends Activity {

    private User user;
    private TextView userType, userName;
    private ImageView profilePicture;

    private int chosenLessonId;

    Intent startLessonIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);

        Intent intent = getIntent();

        user = intent.getParcelableExtra("user");

        //Set teacher status
        user.setIsTeacher(false);
        userType = (TextView) findViewById(R.id.isTeacher);
        userType.setText(getString(R.string.student));

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

        refreshLessonSpinner(findViewById(R.id.lesson_chooser));
    }


    public void joinLesson(View view) {

        if(chosenLessonId == 0){
            Toast.makeText(this, getString(R.string.select_a_lesson), Toast.LENGTH_SHORT).show();
        } else {

            startLessonIntent = new Intent(this, StartLesson.class);

            startLessonIntent.putExtra("user", user);
            startLessonIntent.putExtra("lessonId", chosenLessonId);

            startActivity(startLessonIntent);
        }
    }



    public void refreshLessonSpinner(View view) {

        //Get the available lessons
        AceDAO aceDAO = DemoAceDAO.getInstance();
        Log.i("DAO size", String.valueOf(aceDAO.getSize()));

        List<Lesson> lessons = aceDAO.getLessonsAsList();

        //Find the lesson spinner
        Spinner lessonSpinner = (Spinner) findViewById(R.id.lesson_chooser);

        //Create the custom ArrayAdapter
        LessonListAdapter adapter = new LessonListAdapter(this, lessons);

        //Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        //Apply the adapter to the spinner
        lessonSpinner.setAdapter(adapter);


        //TODO make something happen when a lesson is selected
        lessonSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Lesson lesson = (Lesson) adapterView.getItemAtPosition(position);

                //Place the lesson id for the chosen lesson in the Intent
                chosenLessonId = lesson.getLessonId();
                Log.i("Chosen Lesson", String.valueOf(lesson.getLessonId()));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                chosenLessonId = Integer.parseInt(null);
                Log.i("Chosen No Lesson so id is", String.valueOf(chosenLessonId));
            };
        });
    }
}



