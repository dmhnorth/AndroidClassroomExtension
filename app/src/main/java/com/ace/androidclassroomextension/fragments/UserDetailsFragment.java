package com.ace.androidclassroomextension.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ace.androidclassroomextension.R;

/**
 * A fragment for the User details already entered before a user decides
 * which type of user they're going to be
 *
 * Created by Dave on 05/08/2014.
 */
public class UserDetailsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_details, container, false);
    }
}
