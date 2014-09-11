package com.ace.androidclassroomextension.utilities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.ace.androidclassroomextension.R;
import com.ace.androidclassroomextension.models.User;
import com.ace.androidclassroomextension.models.UserImpl;
import com.google.gson.Gson;

/**
 * For managing data saves and loads of Users within ACE
 *
 * Created by Dave on 08/09/2014.
 */
public class DataManagerImpl implements DataManager {


    private SharedPreferences mPrefs;
    private Activity activityToSaveWithin;
    private static final String PREF_FILE = "PREF_FILE";
    public final String userData = "userData";

    public DataManagerImpl(Activity activity) {
        activityToSaveWithin = activity;
        mPrefs = activityToSaveWithin.getSharedPreferences(PREF_FILE, activityToSaveWithin.MODE_PRIVATE);
    }


    @Override
    public User loadUserData() {

        Gson gson = new Gson();
        String json = mPrefs.getString(userData, "");
        User user = gson.fromJson(json, UserImpl.class);

        if(user == null){
            Toast.makeText(activityToSaveWithin, activityToSaveWithin.getString(R.string.no_stored_data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(activityToSaveWithin, activityToSaveWithin.getString(R.string.data_loaded) + user.getName(), Toast.LENGTH_SHORT).show();
        }
        return user;
    }

    @Override
    public void saveUserData(User user){
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);

        prefsEditor.putString(userData, json);
        prefsEditor.apply();
        try{

        Toast.makeText(activityToSaveWithin, activityToSaveWithin.getString(R.string.data_saved) + user.getName(), Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            //Do nothing, just a safety
        }

        prefsEditor.commit();



    }

}
