package com.ace.androidclassroomextension.creatorActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ace.androidclassroomextension.R;
import com.ace.androidclassroomextension.creatorActivities.CreateUser;
import com.ace.androidclassroomextension.models.User;
import com.ace.androidclassroomextension.models.UserImpl;
import com.ace.androidclassroomextension.utilities.DataManager;
import com.ace.androidclassroomextension.utilities.DataManagerImpl;


public class MainActivity extends Activity {

    private EditText nameEntry;
    private User user;
    private DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataManager = new DataManagerImpl(this);
        user = dataManager.loadUserData();

        updateView();

    }

    /**
     * Update the view if Ace Data already exists on device
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

            initialiseUserWithName();
            dataManager.saveUserData(user);

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
     * Initialises the User object in memory using the user input
     */
    private void initialiseUserWithName() {
        if (user == null) {
            user = new UserImpl(getUserInputName());
        } else {
            user.setName(getUserInputName());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(getUserInputName().isEmpty()){
            //Do Nothing
        } else {
            initialiseUserWithName();
            dataManager.saveUserData(user);
        }
    }

}