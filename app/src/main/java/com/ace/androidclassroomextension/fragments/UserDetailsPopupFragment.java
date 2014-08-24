package com.ace.androidclassroomextension.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ace.androidclassroomextension.R;

/**
 * Backend of the popup user details fragment
 *
 * Created by Dave on 24/08/2014.
 */
public class UserDetailsPopupFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_details_popup, container, false);
    }
}
