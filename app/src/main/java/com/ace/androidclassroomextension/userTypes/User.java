package com.ace.androidclassroomextension.userTypes;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * The User class. Contains all persistent data unique to the device for both
 * Student and Teacher mode.
 *
 * Parcelable code adapted from
 * http://shri.blog.kraya.co.uk/2010/04/26/android-parcel-data-to-pass-between-activities-using-parcelable-classes/
 *
 * Created by Dave on 05/08/2014.
 */
public class User implements Parcelable {

    private String name;
    private Uri profilePictureUri;
    private boolean isTeacher;
    private boolean allowAudio;
    private boolean allowVideo;

    /**
     * standard constructor
     * @param name Chosen name
     */
    public User(String name) {
        setName(name);
    }

    /**
     * Constructor for Parcel creation
     * @param in Parcel generated
     */
    public User(Parcel in){
        readFromParcel(in);
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

    //Implemented methods required for all Parcelable objects.

    /**
     * For reading out Parcels
     * @param in Parcel generated
     */
    private void readFromParcel(Parcel in) {
        name = in.readString();
        profilePictureUri = in.readParcelable(Uri.class.getClassLoader());

        //TODO Test these are being returned effectively THEY ARE ACCORDING TO LOG
        boolean[] options = in.createBooleanArray();
        isTeacher = options[0];
        allowAudio = options[1];
        allowVideo = options [2];
//        Log.i("readParcel up options: ", Arrays.toString(options));

    }

    /*
     * Required helper method for Parcelable to work
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * For writing parcels
     * @param write destination parcel
     * @param flags generated and required by Android
     */
    @Override
    public void writeToParcel(Parcel write, int flags) {
        write.writeString(name);
        write.writeParcelable(profilePictureUri, flags);

        boolean[] options = {isTeacher, allowAudio, allowVideo};
        write.writeBooleanArray(options);
//        Log.i("current isTeacher", String.valueOf(isTeacher));
    }

    /*
     * Required helper method for Parcelable to work
     */
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public User createFromParcel(Parcel in) {
            return new User(in); }
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public Boolean getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(Boolean isTeacher) {
        this.isTeacher = isTeacher;
    }

    public Boolean getAllowAudio() {
        return allowAudio;
    }

    public void setAllowAudio(Boolean allowAudio) {
        this.allowAudio = allowAudio;
    }

    public Boolean getAllowVideo() {
        return allowVideo;
    }

    public void setAllowVideo(Boolean allowVideo) {
        this.allowVideo = allowVideo;
    }
}
