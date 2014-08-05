package com.ace.androidclassroomextension;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

/**
 * For creating all the assets necessary to instantiate a classroom
 */
public class CreateUser extends Activity {

    private User user;
    private static Uri userImageUri;
    private static Bitmap userImage;
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
        if(userImage != null) {
            profilePicture.setImageBitmap(userImage);
            scaleAndSetProfilePicture();
        }

        //TODO find the user microphone

        //TODO find the user webcam

        //TODO assign all these elements to a User class


    }

    /**
     * Gets the lesson description entered by the user.
     * @return the lesson description as a String
     */
    private String getLessonDescription(){
        EditText descriptionET = (EditText) findViewById(R.id.lessonDescription);
        return String.valueOf(descriptionET.getText());
    }


    //'Request' result code for using image afterwards
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    /**
     * To take a photo using the on-board camera application of a device
     * @param view
     */
    public void takePhoto(View view) {

        // Intent to take a picture then return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //Get a unique URI for an image
        MediaFileAndUriManager mediaFileAndUriManager = new MediaFileAndUriManager();

        // create an Immutable URI reference. to save the image
        userImageUri = mediaFileAndUriManager.getImageUri();

        // set the image file name
        intent.putExtra(MediaStore.EXTRA_OUTPUT, userImageUri);

        // start the image capture Intent
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

        //TODO add the photo to the local Android gallery requires OutputStream to be written in MediaFileAndUriManager
//        galleryAddPic();
    }

    /**
     * Scales the Profile Bitmap within the Activity to minimise memory usage
     */
    private void scaleAndSetProfilePicture() {
        // Get the dimensions of the View
        int targetW = profilePicture.getWidth();
        int targetH = profilePicture.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(userImageUri.getEncodedPath(), bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        //TODO Currently hard coding the scale factor due to internal variables not being available outside this method
        int scaleFactor = 2;
//        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(userImageUri.getEncodedPath(), bmOptions);
        profilePicture.setImageBitmap(bitmap);
    }


    /**
     * Adds the photo to the main Android Gallery for use elsewhere
     */
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(userImageUri);
        this.sendBroadcast(mediaScanIntent);
    //TODO Write the image file to an OutputStream so it shows in other galleries
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Check result code
        if(requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            //check result OK
            if(resultCode == RESULT_OK) {
                //cast the result to a bitmap
                try {
                    //Set the image in the Activity and the User
                    userImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), userImageUri);
                    user.setProfilePicture(userImage);
                    scaleAndSetProfilePicture();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    public void createTeacher(View view) {
        Toast.makeText(this, "Not yet available", Toast.LENGTH_SHORT).show();
        //TODO launch a teacher creator
    }

    public void createStudent(View view) {
        Toast.makeText(this, "Not yet available", Toast.LENGTH_SHORT).show();
        //TODO launch a student creator
    }
}

