package com.ace.androidclassroomextension.lessonActivities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ace.androidclassroomextension.R;
import com.ace.androidclassroomextension.models.User;

import java.util.ArrayList;

/**
 * Created by Dave on 24/07/2014.
 */
public class StudentListAdapter extends ArrayAdapter{

    public StudentListAdapter(Context context, ArrayList<User> students) {
        super(context, R.layout.student_row, students);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater theInflater = LayoutInflater.from(getContext());

        View studentRowView = theInflater.inflate(R.layout.student_row, parent, false);

        User student = (User) getItem(position);


        //Set the studentName section of the view
        TextView studentNameView = (TextView) studentRowView.findViewById(R.id.studentName);
        studentNameView.setText(student.getName());

        TextView textSent = (TextView) studentRowView.findViewById(R.id.textSent);
        textSent.setText("Unused:" + student.getName());


        //TODO add the other sections of the custom student row
        ImageView studentImage = (ImageView) studentRowView.findViewById(R.id.studentImage);
//        studentImage.setImageResource(R.drawable.silhouette);

        ImageView handUp = (ImageView) studentRowView.findViewById(R.id.handUp);
//        handUp.setImageResource(R.drawable.bordertemplate);
        return studentRowView;
    }
}