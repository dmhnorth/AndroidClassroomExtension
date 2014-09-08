package com.ace.androidclassroomextension;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ace.androidclassroomextension.creatorActivities.CreateUser;
import com.ace.androidclassroomextension.models.User;
import com.google.gson.Gson;


public class MainActivity extends Activity {

    private EditText nameEntry;
    private User user;

    //Persistence Variables
    private SharedPreferences mPrefs;
    public final String userData = "userData";
    private static final String PrefFile = "PrefFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating a shared preference file
        mPrefs = getSharedPreferences(PrefFile, MODE_PRIVATE);
//        mPrefs = getPreferences(MODE_PRIVATE);

        loadUserData();
        updateView();
    }

    /**
     * Update the view if userData already exists on device
     */
    private void updateView() {
        if(user != null) {
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
            saveUserData(user);
            return true;
        } else if (id == R.id.exitTheApp) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Gets test the user entered username
     *
     * @return String username entered by the user
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
            initialiseUserWithName();

            createUserIntent.putExtra("user", user);

            startActivity(createUserIntent);
        } else {
            Toast.makeText(this, getString(R.string.enter_a_name), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Initialises the User object in memory
     */
    private void initialiseUserWithName() {
        if (user == null) {
            user = new User(getUserInputName());
        } else {
            user.setName(getUserInputName());
        }
    }


    //Data Persistence Methods

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveUserData(user);
    }

    /**
     * Saves the user data
     * @param user that was previously used on the system
     */
    private void saveUserData(User user) {
        initialiseUserWithName();

        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);

        prefsEditor.putString(userData, json);
        prefsEditor.apply();
        Toast.makeText(this, getString(R.string.data_saved) + user.getName(), Toast.LENGTH_SHORT).show();

        prefsEditor.commit();
    }

    /**
     * Loads the user data and assigns the user
     */
    private void loadUserData() {
        Gson gson = new Gson();
        String json = mPrefs.getString(userData, "");
        user = gson.fromJson(json, User.class);

        if(user == null){
            Toast.makeText(this, getString(R.string.no_stored_data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.data_loaded) + user.getName(), Toast.LENGTH_SHORT).show();
        }
    }
}