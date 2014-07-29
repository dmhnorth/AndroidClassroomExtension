package com.ace.androidclassroomextension;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.InvalidParameterException;


public class MainActivity extends Activity {

    private EditText nameEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            return true;
        } else if (id == R.id.exitTheApp){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Gets the entered username
     * @return username
     */
    private String getUserName() {
        nameEntry = (EditText) findViewById(R.id.nameEntry);
        return String.valueOf(nameEntry.getText());
    }

    public void createTeacher(View view) {
        Intent createTeacherIntent = new Intent(this, CreateTeacher.class);
        createTeacherIntent.putExtra("name", getUserName());
        startActivity(createTeacherIntent);
    }

    public void createStudent(View view) {
        Intent createStudentIntent = new Intent(this, CreateStudent.class);
        createStudentIntent.putExtra("name", getUserName());
        startActivity(createStudentIntent);
    }
}
