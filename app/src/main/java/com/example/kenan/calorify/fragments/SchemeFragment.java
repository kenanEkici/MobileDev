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
    HashMap<String, List<String>> expandableListDetail;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.scheme_page_fragment, container, false);

        //HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        //child
//        List<String> basketball = new ArrayList<String>();
//        basketball.add("United States");
//        basketball.add("Spain");
//        basketball.add("Argentina");
//        basketball.add("France");
//        basketball.add("Russia");

//        expandableListDetail.put("BASKETBALL TEAMS", basketball); //parent

//        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
//        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
//        expandableListAdapter = new CustomExpandableListAdapter(getContext(), expandableListTitle, expandableListDetail);
//        expandableListView.setAdapter(expandableListAdapter);
//
//        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) ->  {
//                Toast.makeText(
//                        getContext(),
//                        expandableListTitle.get(groupPosition)
//                                + " -> "
//                                + expandableListDetail.get(
//                                expandableListTitle.get(groupPosition)).get(
//                                childPosition), Toast.LENGTH_SHORT
//                ).show();
//                return false;
//        });

        return view;
    }
}
