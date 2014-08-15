package com.ace.androidclassroomextension;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ace.androidclassroomextension.creatorActivities.CreateUser;
import com.ace.androidclassroomextension.userTypes.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class MainActivity extends Activity {

    private EditText nameEntry;
    private User user;

    private String PERSISTENCE_TEST = "Didn't work yet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/**
        //Load the persistent user data
        FileInputStream fin = null;
        try {
            fin = openFileInput(USER_FILE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (fin != null) {
            try {
                PERSISTENCE_TEST = String.valueOf(fin.read());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fin != null) {
            try {
                fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
 **/
        Log.d("Persistence: ", PERSISTENCE_TEST);

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

            //TODO delete this test
            PERSISTENCE_TEST = "TEST PRESS WORKED";
            Log.d("Persistence: ", PERSISTENCE_TEST);

            return true;
        } else if (id == R.id.exitTheApp) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Gets the entered username
     *
     * @return username
     */
    private String getUserName() {
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
        Log.d("current name: ", getUserName());

        if (!(getUserName().equals(""))) {
            Intent createUserIntent = new Intent(this, CreateUser.class);

            //Initialise the user if it hasn't been done
            if (user == null) {
                user = new User(getUserName());
            }

            createUserIntent.putExtra("user", user);

            startActivity(createUserIntent);
        } else {
            Toast.makeText(this, "Enter a name!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //TODO implement this so the user is saved, may have to lose 'new' within createUser

        //Save the persistent user data
        try {

            FileOutputStream fos = openFileOutput(PERSISTENCE_TEST, Context.MODE_PRIVATE);
            fos.write(PERSISTENCE_TEST.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("onDestroy:","was called and persistance");
    }
}