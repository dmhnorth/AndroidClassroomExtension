package com.ace.androidclassroomextension;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * The Super class of Teacher and Student, used initially when the user is creating
 * their user profile picture and name.
 * Created by Dave on 05/08/2014.
 */
public class User implements Serializable{

    private String name;
    private Bitmap profilePicture;

    public User(String name){
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Bitmap profilePicture) {
        this.profilePicture = profilePicture;
    }
}
