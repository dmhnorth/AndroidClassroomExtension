package com.ace.androidclassroomextension;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {

    private String name;
    private EditText nameEntry;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEntry = (EditText) findViewById(R.id.nameEntry);



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

    public void createTeacher(View view) {

        name = String.valueOf(nameEntry.getText());

        Intent createTeacherIntent = new Intent(this, CreateTeacher.class);

        createTeacherIntent.putExtra("name", name);

        startActivity(createTeacherIntent);


        //TODO
    }

    public void createStudent(View view) {
        //TODO
    }
}
