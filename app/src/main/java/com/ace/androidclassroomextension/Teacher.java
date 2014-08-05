package com.ace.androidclassroomextension;

import android.graphics.Bitmap;

/**
 * Teacher class with specifics for a Teacher implementation
 * Created by Dave on 05/08/2014.
 */
public class Teacher extends User {
    public Teacher(String name, Bitmap profilePicture) {
        super(name);
        setProfilePicture(profilePicture);
    }
}
