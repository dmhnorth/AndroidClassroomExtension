package com.ace.androidclassroomextension.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ace.androidclassroomextension.R;

/**
 * Simply shows just the Teacher Details
 *
 * Created by Dave on 22/08/2014.
 */
public class TeacherDetailsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_teacher_details, container, false);
    }
}
