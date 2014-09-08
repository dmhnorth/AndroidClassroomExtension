package com.ace.androidclassroomextension;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ace.androidclassroomextension.creatorActivities.CreateUser;
import com.ace.androidclassroomextension.models.User;
import com.ace.androidclassroomextension.utilities.UriFactory;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends Activity {

    private EditText nameEntry;
    private User user;
    public final String userData = "userData";

    private SharedPreferences mPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating a shared preference file
        mPrefs = getPreferences(MODE_PRIVATE);

        loadUserData();

        if(user != null) {
            Toast.makeText(this, "User Name:" + user.getName(), Toast.LENGTH_SHORT).show();
            nameEntry = (EditText) findViewById(R.id.nameEntry);
            nameEntry.setText(user.getName());
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this, "No settings yet!", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.exitTheApp) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Gets the user entered username
     *
     * @return username entered by the user
     */
    private String getUserInputName() {
        nameEntry = (EditText) findViewById(R.id.nameEntry);
        return String.valueOf(nameEntry.getText());
    }

    /**
     * Initiate the option and Activity for creating a User
     *
     * @param view current view
     */
    public void createUser(View view) {

        //Validate name entry
        Log.d("current name: ", getUserInputName());

        if (!(getUserInputName().equals(""))) {
            Intent createUserIntent = new Intent(this, CreateUser.class);

        //Initialise the user if it hasn't been done
            initialiseUser();

            createUserIntent.putExtra("user", user);

            startActivity(createUserIntent);
        } else {
            Toast.makeText(this, "Enter a name!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Creates the User object in memory
     */
    private void initialiseUser() {
        if (user == null) {
            user = new User(getUserInputName());
        } else {
            user.setName(getUserInputName());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        initialiseUser();
        outState.putParcelable("user", user);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        //TODO persist the user data here
        saveUserData(user);
    }

    /**
     * Saves the user data
     * @param user
     */
    private void saveUserData(User user) {
        initialiseUser();

        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString(userData, json);
        prefsEditor.commit();
    }

    /**
     * Loads the user data and assigns the user
     */
    private void loadUserData() {
        Gson gson = new Gson();
        String json = mPrefs.getString(userData, "");
        user = gson.fromJson(json, User.class);
    }
}