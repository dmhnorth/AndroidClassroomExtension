package com.ace.androidclassroomextension.models;

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
public class UserImpl implements User {

    private String name;
    private Uri profilePictureUri;
    private boolean isTeacher;
    private boolean allowAudio;
    private boolean allowVideo;
    private boolean handUp;
    private String currentQuestion = "Silent";

    /**
     *
     * @param name Chosen name of the User
     */
    public UserImpl(String name) {
        setHandUp(false);
        setName(name);
    }

    /**
     * Constructor for Parcel creation
     * @param in Parcel generated
     */
    public UserImpl(Parcel in){
        readFromParcel(in);
    }


    @Override
    public String getName(){
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Uri getProfilePictureUri() {
        return profilePictureUri;
    }

    @Override
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

        boolean[] options = in.createBooleanArray();
        isTeacher = options[0];
        allowAudio = options[1];
        allowVideo = options [2];
//        Log.i("readParcel up options: ", Arrays.toString(options));

    }

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

    /**
     * Required helper method for Parcelable to work
     */
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public User createFromParcel(Parcel in) {
            return new UserImpl(in); }
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public Boolean getIsTeacher() {
        return isTeacher;
    }

    @Override
    public void setIsTeacher(Boolean isTeacher) {
        this.isTeacher = isTeacher;
    }

    @Override
    public Boolean getAllowAudio() {
        return allowAudio;
    }


    @Override
    public void setAllowAudio(Boolean allowAudio) {
        this.allowAudio = allowAudio;
    }

    @Override
    public Boolean getAllowVideo() {
        return allowVideo;
    }

    @Override
    public void setAllowVideo(Boolean allowVideo) {
        this.allowVideo = allowVideo;
    }

    @Override
    public boolean isHandUp() {
        return handUp;
    }

    /**
     * setter for the handup property
     * @param handUp set whether the User hand is up
     */
    private void setHandUp(boolean handUp) {
        this.handUp = handUp;
    }

    @Override
    public String getCurrentQuestion() {
        return currentQuestion;
    }

    @Override
    public void setCurrentQuestion(String currentQuestion) {
        setHandUp(true);
        this.currentQuestion = currentQuestion;
    }
}
