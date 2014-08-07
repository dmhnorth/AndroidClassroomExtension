package com.ace.androidclassroomextension.userTypes;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.net.URI;
import java.io.Serializable;

/**
 * The Super class of Teacher and Student, used initially when the user is creating
 * their user profile picture and name.
 *
 * Parcelable code adapted from http://shri.blog.kraya.co.uk/2010/04/26/android-parcel-data-to-pass-between-activities-using-parcelable-classes/
 *
 * Created by Dave on 05/08/2014.
 */
public class User implements Parcelable {

    private String name;
    private Uri profilePictureUri;

    public User(String name) {
        setName(name);
    }

    public User(Parcel in){
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in) {
        name = in.readString();
        profilePictureUri = in.readParcelable(Uri.class.getClassLoader());
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeParcelable(profilePictureUri, flags);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public User createFromParcel(Parcel in) {
            return new User(in); }
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
