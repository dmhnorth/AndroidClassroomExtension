package com.ace.androidclassroomextension.utilities;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * For management and generation of unique Uri identifiers.
 * Adapted from code found at http://developer.android.com/guide/topics/media/camera.html
 *
 * Created by Dave on 01/08/2014.
 */
public class UriFactoryImpl implements UriFactory {

    /** Create a file Uri for saving an image or video */
    public static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * Create a timestamped media File on the SD card
     * @param type code for file type
     * @return the media File created
     */
    private static File getOutputMediaFile(int type){

        Environment.getExternalStorageState();

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Ace");

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("Ace", "failed to create directory");
                return null;
            }
        }

        // Create a media file name using the date and type
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_FORMAT_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_FORMAT_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }
        return mediaFile;
    }

    @Override
    public Uri getImageUri() {
        return getOutputMediaFileUri(MEDIA_FORMAT_IMAGE);
    }

    @Override
    public Uri getVideoUri() {
        return getOutputMediaFileUri(MEDIA_FORMAT_VIDEO);
    }


}
