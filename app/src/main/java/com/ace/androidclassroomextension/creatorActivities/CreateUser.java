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
import com.ace.androidclassroomextension.utilities.UriFactory;
import com.ace.androidclassroomextension.userTypes.User;

/**
 * For creating all the assets necessary to instantiate a classroom
 *
 * Some code adapted from http://developer.android.com/guide/topics/media/camera.html
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
        String name = intent.getStringExtra("name");

        //Set User name using intent data
        user = new User(name);

        TextView userName = (TextView) findViewById(R.id.nameOfUser);
        userName.setText(user.getName());

        //Assign the saved photo to the user if available
        profilePicture = (ImageView) findViewById(R.id.user_photo);
        if(user.getProfilePictureUri() != null) {
            profilePicture.setImageURI(user.getProfilePictureUri());
        }

        //TODO find the user microphone

        //TODO find the user web cam

        //TODO assign all these elements to a User class


    }

    //'Request' result code for using image afterwards
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    /**
     * To take a photo using the on-board camera application of a device
     * @param view the current view
     */
    public void takePhoto(View view) {

        // Intent to take a picture then return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //Get a unique Uri for an image
        UriFactory uriFactory = new UriFactory();

        // create an Immutable Uri reference. to save the image
        userImageUri = uriFactory.getImageUri();

        // set the image file name
        intent.putExtra(MediaStore.EXTRA_OUTPUT, userImageUri);

        // start the image capture Intent
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

        //TODO add the photo to the local Android gallery requires OutputStream to be written in UriFactory
//        galleryAddPic();
    }


    /**
     * Adds the photo to the main Android Gallery for use elsewhere
     */
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(userImageUri);
        this.sendBroadcast(mediaScanIntent);
    //TODO Write the image file to an OutputStream so it shows in other galleries could potentially just wipe the userData for the app each load
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

    public void createTeacher(View view) {
        Intent createUserIntent = new Intent(this, CreateTeacher.class);
        createUserIntent.putExtra("user", user);
        startActivity(createUserIntent);
    }

    public void createStudent(View view) {
        Intent createUserIntent = new Intent(this, CreateStudent.class);
        createUserIntent.putExtra("user", user);
        startActivity(createUserIntent);
    }

    public void sendAudioToggle(View view) {
        boolean on = ((Switch) view).isChecked();

        if (on) {

            user.setAllowAudio(true);
            //TODO find the audio stream
            Toast.makeText(this, "Audio is now on", Toast.LENGTH_SHORT).show();
        } else {
            user.setAllowAudio(false);
            //TODO clear the audio stream
            Toast.makeText(this, "Audio is now off", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendVideoToggle(View view) {
        boolean on = ((Switch) view).isChecked();

        if (on) {

            user.setAllowVideo(true);
            //TODO find the video stream
            Toast.makeText(this, "Video is now on", Toast.LENGTH_SHORT).show();
        } else {
            user.setAllowVideo(false);
            //TODO clear the video stream
            Toast.makeText(this, "Video is now off", Toast.LENGTH_SHORT).show();
        }
    }
}

