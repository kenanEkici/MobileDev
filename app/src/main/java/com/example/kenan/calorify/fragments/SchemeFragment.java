package com.example.kenan.calorify.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kenan.calorify.R;

/**
 * Created by Kenan on 10/10/2017.
 */

public class SchemeFragment extends Fragment {

    public static SchemeFragment newInstance() {
        return new SchemeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.scheme_page_fragment, container, false);
    }
}
