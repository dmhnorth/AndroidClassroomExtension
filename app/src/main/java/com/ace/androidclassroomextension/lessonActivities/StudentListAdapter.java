package com.ace.androidclassroomextension.lessonActivities;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ace.androidclassroomextension.R;
import com.ace.androidclassroomextension.models.User;

import java.util.List;

/**
 * Custom Adapter for the Student list fragment
 *
 * Created by Dave on 24/07/2014.
 */
public class StudentListAdapter extends ArrayAdapter<User> {

    public StudentListAdapter(Context context, List<User> students) {
        super(context, R.layout.student_row, students);
    }

    //Inner class for use with the ViewHolder Pattern
    private class ViewHolder{
        TextView studentNameView;
        TextView textSent;
        ImageView studentImage;
        ImageView handUp;
        ViewHolder(View v){
            studentNameView = (TextView) v.findViewById(R.id.studentName);
            textSent = (TextView) v.findViewById(R.id.textSent);
            studentImage = (ImageView) v.findViewById(R.id.studentImage);
            handUp = (ImageView) v.findViewById(R.id.handUp);

        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View studentRowView = convertView;
        ViewHolder holder;

        if (studentRowView == null){
            LayoutInflater theInflater = LayoutInflater.from(getContext());
            studentRowView = theInflater.inflate(R.layout.student_row, parent, false);
            holder = new ViewHolder(studentRowView);
            studentRowView.setTag(holder);

        } else {
            holder = (ViewHolder) studentRowView.getTag();
        }

        User student = getItem(position);

        //Set the studentName section of the view
        holder.studentNameView.setText(student.getName());

        //for further information in sections of the custom student row
        holder.textSent.setText(student.getCurrentQuestion());

//        holder.studentImage.setImageResource(R.drawable.silhouette);

        if(student.isHandUp()){
            holder.handUp.setBackgroundColor(Color.parseColor("#b30303"));
        } else{
            holder.handUp.setBackgroundColor(Color.parseColor("#a8c942"));
        }
        return studentRowView;
    }
}
