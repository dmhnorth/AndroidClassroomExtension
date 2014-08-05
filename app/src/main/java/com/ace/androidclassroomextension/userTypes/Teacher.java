package com.ace.androidclassroomextension.userTypes;

import android.net.Uri;

import java.net.URI;

/**
 * Teacher class with specifics for a Teacher implementation
 * Created by Dave on 05/08/2014.
 */
public class Teacher extends User {
    public Teacher(String name, Uri profilePicture) {
        super(name);
        setProfilePictureUri(profilePicture);
    }
}
