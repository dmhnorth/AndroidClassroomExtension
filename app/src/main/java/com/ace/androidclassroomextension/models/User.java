package com.ace.androidclassroomextension.models;

import android.net.Uri;
import android.os.Parcelable;

/**
 * Interface for the User class
 *
 * Created by Dave on 11/09/2014.
 */
public interface User extends Parcelable {
    /**
     * getter for name
     * @return the User name
     */
    String getName();

    /**
     * sets the name
     * @param name the User name you require
     */
    void setName(String name);

    /**
     * get the Android style Uri for the profile picture
     * @return Android style Uri for the profile picture
     */
    Uri getProfilePictureUri();

    /**
     * sets the profilePictureUri in Android format
     * @param profilePictureUri the Uri in Android format of the picture file
     */
    void setProfilePictureUri(Uri profilePictureUri);

    /**
     * getter for the teacher permission property
     * @return the boolean of the teacher permission property
     */
    Boolean getIsTeacher();

    /**
     * sets the teacher permission property
     * @param isTeacher set to True if the User is a teacher
     */
    void setIsTeacher(Boolean isTeacher);

    /**
     * get the permission property for allowing audio streaming
     * @return the permission for allowing audio streaming
     */
    Boolean getAllowAudio();

    /**
     * set the permission property for allowing audio
     * @param allowAudio whether you wish to allow audio
     */
    void setAllowAudio(Boolean allowAudio);

    /**
     * get the permission property for allowing video streaming
     * @return the permission for allowing video streaming
     */
    Boolean getAllowVideo();

    /**
     * set the permission property for allowing video
     * @param allowVideo whether you wish to allow video
     */
    void setAllowVideo(Boolean allowVideo);

    /**
     * Boolean for whether a User has their hand up within a lesson
     * defaults to false
     * @return whether a User has their hand up to alert the teacher
     */
    boolean isHandUp();

    /**
     * getter for the current question a User is asking
     * @return the question of a User in a string format
     */
    String getCurrentQuestion();

    /**
     * setter for the current question
     * @param currentQuestion the question you wish to present on screen
     */
    void setCurrentQuestion(String currentQuestion);
}
