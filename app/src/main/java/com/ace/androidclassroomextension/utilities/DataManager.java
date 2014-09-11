package com.ace.androidclassroomextension.utilities;

import com.ace.androidclassroomextension.models.User;

/**
 * Created by Dave on 11/09/2014.
 */
public interface DataManager {
    /**
     * for loading the data of a particular User from the shared preferences folder
     * @return the User loaded
     */
    User loadUserData();

    /**
     * for saving the User to the phone to the shared preferences file
     * @param user the user you wish to save
     */
    void saveUserData(User user);
}
