package com.ace.androidclassroomextension;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Picture;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ace.androidclassroomextension.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * For creating all the assets necessary to instantiate a classroom
 */
public class CreateTeacher extends Activity {

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_teacher);

        Intent nameData = getIntent();
        name = nameData.getStringExtra("name");

        TextView nameTeacher = (TextView) findViewById(R.id.nameOfTeacher);
        nameTeacher.append(": " + name);

        //TODO assign the stock photo to the user

        //TODO find the user microphone

        //TODO find the user webcam

        //TODO assign all these elements somewhere, either to a class or whatever

    }

    /**
     * Gets the lesson description.
     * @return the lesson description
     */
    private String getLessonDescription(){
        EditText descriptionET = (EditText) findViewById(R.id.lessonDescription);
        return String.valueOf(descriptionET.getText());
    }




    //request result code for using image afterwards
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    //Uri address to save the media
    private Uri fileUri;



    public void selectPhoto(View view) {

        // Intent to take a picture then return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


        MediaFileUriManager mediaFileUriManager = new MediaFileUriManager();

        //TODO don't call the choice image explicitly
        // create an Immutable URI reference. to save the image
        fileUri = mediaFileUriManager.getOutputMediaFileUri(mediaFileUriManager.getImageUri());

        // set the image file name
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //check which result came back
        if(requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            //check the result was OK
            if(resultCode == RESULT_OK) {
                //get the ImageView
                ImageView profilePicture = (ImageView) findViewById(R.id.user_photo);
                //Set the image in the creator Activity
                profilePicture.setImageURI(fileUri);
            }
        }
    }
}

