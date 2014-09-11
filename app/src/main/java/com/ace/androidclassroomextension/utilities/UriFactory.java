package com.ace.androidclassroomextension.utilities;

import android.net.Uri;

/**
 * Created by Dave on 11/09/2014.
 */
public interface UriFactory {
    //Media format codes
    int MEDIA_FORMAT_IMAGE = 1;
    int MEDIA_FORMAT_VIDEO = 2;

    /**
     * To get a unique Image Uri
     * @return image Uri
     */
    Uri getImageUri();

    /**
     * To get a unique Video Uri
     * @return Video Uri
     */
    Uri getVideoUri();
}
