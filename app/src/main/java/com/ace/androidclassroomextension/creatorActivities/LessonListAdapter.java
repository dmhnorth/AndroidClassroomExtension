package com.ace.androidclassroomextension.creatorActivities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ace.androidclassroomextension.R;
import com.ace.androidclassroomextension.models.Lesson;

import java.util.ArrayList;

/**
 * Custom ArrayAdapter class for inflating lesson information from a lesson list
 *
 * Created by Dave on 26/08/2014.
 */
public class LessonListAdapter extends ArrayAdapter<Lesson> {

    public LessonListAdapter(Context context, ArrayList<Lesson> lessons) {
        super(context, R.layout.lesson_row, lessons);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater theInflater = LayoutInflater.from(getContext());

        View lessonRowView = theInflater.inflate(R.layout.lesson_row, parent, false);

        Lesson lesson = (Lesson) getItem(position);

        //Set the Lesson name section of the view
        TextView lessonNameView = (TextView) lessonRowView.findViewById(R.id.lesson_row_name);
        lessonNameView.setText(lesson.getLessonName());

        //Set the Teacher name section of the view
        TextView lessonTeacherNameView = (TextView) lessonRowView.findViewById(R.id.lesson_row_teacher_name);
        lessonTeacherNameView.setText(lesson.getTeacher().getName());

        return lessonRowView;
    }
}
