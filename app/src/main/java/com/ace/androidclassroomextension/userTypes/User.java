package com.ace.androidclassroomextension.userTypes;

import android.net.Uri;

import java.net.URI;
import java.io.Serializable;

/**
 * The Super class of Teacher and Student, used initially when the user is creating
 * their user profile picture and name.
 * Created by Dave on 05/08/2014.
 */
public class User implements Serializable {

    private String name;
    private Uri profilePictureUri;

    public User(String name){
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getProfilePictureUri() {
        return profilePictureUri;
    }

    public void setProfilePictureUri(Uri profilePictureUri) {
        this.profilePictureUri = profilePictureUri;
    }


}
