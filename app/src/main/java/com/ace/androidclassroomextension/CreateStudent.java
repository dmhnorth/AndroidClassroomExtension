package com.ace.androidclassroomextension;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ace.androidclassroomextension.R;

public class CreateStudent extends Activity {

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);

        Intent nameData = getIntent();
        name = nameData.getStringExtra("name");

        TextView nameStudent = (TextView) findViewById(R.id.nameOfStudent);
        nameStudent.append(": " + name);
    }

    public void takePhoto(View view) {
        //TODO Enable the ability to select a photo from the users handset
    }
}
