package com.ace.androidclassroomextension.creatorActivities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ace.androidclassroomextension.R;
import com.ace.androidclassroomextension.models.Lesson;

import java.util.List;

/**
 * Custom ArrayAdapter class for inflating lesson information from a lesson list
 *
 * Created by Dave on 26/08/2014.
 */
public class LessonListAdapter extends ArrayAdapter<Lesson> {

    public LessonListAdapter(Context context, List<Lesson> lessons) {
        super(context, R.layout.lesson_row, lessons);
    }

    //For use within the ViewHolder Pattern
    private class ViewHolder {
        TextView lessonNameView;
        TextView lessonTeacherNameView;
        ViewHolder(View v){
            lessonNameView = (TextView) v.findViewById(R.id.lesson_row_name);
            lessonTeacherNameView = (TextView) v.findViewById(R.id.lesson_row_teacher_name);

        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View lessonRowView = convertView;
        ViewHolder holder = null;

        if (lessonRowView == null){
            LayoutInflater theInflater = LayoutInflater.from(getContext());
            lessonRowView = theInflater.inflate(R.layout.lesson_row, parent, false);
            holder = new ViewHolder(lessonRowView);
            lessonRowView.setTag(holder);
        } else {
            holder = (ViewHolder) lessonRowView.getTag();
        }


        Lesson lesson = getItem(position);

        //Set the Lesson name section of the view
        holder.lessonNameView.setText(lesson.getLessonName());

        //Set the Teacher name section of the view
        holder.lessonTeacherNameView.setText(lesson.getTeacher().getName());

        return lessonRowView;
    }
}
