package com.ace.androidclassroomextension.creatorActivities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ace.androidclassroomextension.R;
import com.ace.androidclassroomextension.models.User;
import com.ace.androidclassroomextension.utilities.UriFactory;
import com.ace.androidclassroomextension.utilities.UriFactoryImpl;

/**
 * For creating all the assets necessary to instantiate a classroom
 *
 * Some code adapted from Android guides
 * at http://developer.android.com/guide/topics/media/camera.html
 *
 *
 */
public class CreateUser extends Activity {

    private User user;
    private Uri userImageUri;
    private ImageView profilePicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        //Retrieve data from previous activity
        Intent intent = getIntent();

        //Set User using intent data
        if(user == null) {
            user = intent.getParcelableExtra("user");
        }

        TextView userName = (TextView) findViewById(R.id.nameOfUser);
        userName.setText(user.getName());

        //Assign the saved photo to the user if available
        profilePicture = (ImageView) findViewById(R.id.user_photo);
        if(user.getProfilePictureUri() != null) {
            profilePicture.setImageURI(user.getProfilePictureUri());
        }
    }

    //'Request' result code for using image afterwards
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    /**
     * Take a photo using the on-board camera application of a device
     * @param view the current view
     */
    public void takePhoto(View view) {

        // Intent to take a picture then return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //Get a unique Uri for an image
        UriFactory uriFactory = new UriFactoryImpl();

        // create an Immutable Uri reference. to save the image
        userImageUri = uriFactory.getImageUri();

        // set the image file name
        intent.putExtra(MediaStore.EXTRA_OUTPUT, userImageUri);

        // start the image capture Intent
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Check result code
        if(requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            //check result OK
            if(resultCode == RESULT_OK) {
                //set the user image and replace in ImageView in activity
                user.setProfilePictureUri(userImageUri);
                profilePicture.setImageURI(userImageUri);
            }
        }
    }

    /**
     * Start the Teacher Activity path
     * @param view the current view
     */
    public void createTeacher(View view) {
        Intent createUserIntent = new Intent(this, CreateTeacher.class);
        createUserIntent.putExtra("user", user);
        startActivity(createUserIntent);
    }

    /**
     * Start the Student Activity path
     * @param view the current view
     */
    public void createStudent(View view) {
        Intent createUserIntent = new Intent(this, CreateStudent.class);
        createUserIntent.putExtra("user", user);
        startActivity(createUserIntent);
    }

    /**
     * Receives the permission choice whether to stream Audio
     * @param view the current view
     */
    public void sendAudioToggle(View view) {
        boolean on = ((Switch) view).isChecked();

        if (on) {
            user.setAllowAudio(true);
            Toast.makeText(this, getString(R.string.audio_on), Toast.LENGTH_SHORT).show();
        } else {
            user.setAllowAudio(false);
            Toast.makeText(this, getString(R.string.audio_off), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receives the permission choice whether to stream Audio
     * @param view the current view
     */
    public void sendVideoToggle(View view) {
        boolean on = ((Switch) view).isChecked();

        if (on) {
            user.setAllowVideo(true);
            Toast.makeText(this, getString(R.string.video_on), Toast.LENGTH_SHORT).show();
        } else {
            user.setAllowVideo(false);
            Toast.makeText(this, getString(R.string.video_off), Toast.LENGTH_SHORT).show();
        }
    }
}

