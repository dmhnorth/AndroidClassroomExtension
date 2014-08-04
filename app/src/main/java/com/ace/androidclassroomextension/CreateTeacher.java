package com.ace.androidclassroomextension;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

/**
 * For creating all the assets necessary to instantiate a classroom
 */
public class CreateTeacher extends Activity {

    private String name;

    //TODO find out if a static uri is best practice?
    // Uri address to save the media
    private static Uri userImageUri;
    private static Bitmap userImage;

    private ImageView profilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_teacher);

        Intent nameData = getIntent();
        name = nameData.getStringExtra("name");

        TextView nameTeacher = (TextView) findViewById(R.id.nameOfTeacher);
        nameTeacher.append(": " + name);

        //Assign the saved photo to the user if available
        profilePicture = (ImageView) findViewById(R.id.user_photo);
        if(!(userImage == null)) {
            profilePicture.setImageBitmap(userImage);
        }




        //TODO find the user microphone

        //TODO find the user webcam

        //TODO assign all these elements to a teacher class

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

    public void selectPhoto(View view) {

        // Intent to take a picture then return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //Get a unique URI for an image
        OutputMediaFileUriManager outputMediaFileUriManager = new OutputMediaFileUriManager();

        // create an Immutable URI reference. to save the image
        userImageUri = outputMediaFileUriManager.getImageUri();

        // set the image file name
        intent.putExtra(MediaStore.EXTRA_OUTPUT, userImageUri);

        // start the image capture Intent
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

        //TODO add the photo to the local Android gallery DOESN'T WORK
        galleryAddPic();
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(userImageUri);
        this.sendBroadcast(mediaScanIntent);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //check which result came back
        if(requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            //check the result was OK
            if(resultCode == RESULT_OK) {

                //cast the result to a bitmap
                try {
                    //Set the image in the creator Activity
                    userImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), userImageUri);
                    profilePicture.setImageBitmap(userImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

            }
        }
    }
}

