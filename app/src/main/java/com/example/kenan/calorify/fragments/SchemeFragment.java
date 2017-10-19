package com.example.kenan.calorify.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kenan.calorify.R;
import com.example.kenan.calorify.dal.repos.SchemeRepository;
import com.example.kenan.calorify.dl.models.Day;
import com.example.kenan.calorify.dl.models.Product;
import com.example.kenan.calorify.fragments.adapters.CustomExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Kenan on 10/10/2017.
 */

public class SchemeFragment extends Fragment {

    public static SchemeFragment newInstance() {
        return new SchemeFragment();
    }

    private View view;

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> scheme;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.scheme_page_fragment, container, false);
        updateScheme();
        return view;
    }

    private void updateScheme() {
        scheme = new SchemeRepository().getSchemeOfActiveUser();
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        expandableListTitle = new ArrayList<>(scheme.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(getContext(), expandableListTitle, scheme);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) ->  {
            //hier zou je dezelfde modal kunnen openen als je een product scant om
            //details te zien van een geconsumeerde product
            return false;
        });
    }
}
