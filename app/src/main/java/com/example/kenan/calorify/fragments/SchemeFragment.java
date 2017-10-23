package com.example.kenan.calorify.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.kenan.calorify.R;
import com.example.kenan.calorify.dal.repos.DayRepository;
import com.example.kenan.calorify.dal.repos.SchemeRepository;
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
    List<List<Product>> schemeData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.menu_scheme_frag, container, false);
        updateScheme();
        return view;
    }

    private void updateScheme() {
        DayRepository dayRepo = new DayRepository();
        SchemeRepository schemeRepo = new SchemeRepository();
        scheme = schemeRepo.getSchemeOfActiveUser();

        schemeData = schemeRepo.getSchemeDataOfActiveUser();
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        expandableListTitle = new ArrayList<>(dayRepo.getAllDaysAsStrings());
        expandableListAdapter = new CustomExpandableListAdapter(getContext(), expandableListTitle, scheme);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) ->  {
            //hier zou je dezelfde modal kunnen openen als je een product scant om
            //details te zien van een geconsumeerde product

            Bundle args = new Bundle();
            SchemeDialogFragment dialog = new SchemeDialogFragment();
            args.putLong("productId", schemeData.get(groupPosition).get(childPosition).getId());
            dialog.setArguments(args);
            dialog.show(getFragmentManager(), "SchemeDialogFragment");

            return true;
        });
    }
}
