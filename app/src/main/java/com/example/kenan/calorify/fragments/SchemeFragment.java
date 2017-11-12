package com.example.kenan.calorify.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kenan.calorify.R;
import com.example.kenan.calorify.dal.repos.DayRepository;
import com.example.kenan.calorify.dal.repos.SchemeRepository;
import com.example.kenan.calorify.dl.models.ConsumedProduct;
import com.example.kenan.calorify.fragments.adapters.CustomExpandableListAdapter;

import org.joda.time.LocalDate;

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
    List<List<ConsumedProduct>> schemeData;
    ImageView empty_list_icon;
    TextView empty_list_notif;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.menu_scheme_frag, container, false);
        empty_list_icon = (ImageView) view.findViewById(R.id.android_sad);
        empty_list_notif = (TextView) view.findViewById(R.id.notify_empty);
        updateScheme();
        return view;
    }

    private void updateScheme() {
        DayRepository dayRepo = new DayRepository();

        if (dayRepo.getAllDays().size() == 0) {
            empty_list_icon.setVisibility(View.VISIBLE);
            empty_list_notif.setVisibility(View.VISIBLE);

        } else {
            empty_list_icon.setVisibility(View.GONE);
            empty_list_notif.setVisibility(View.GONE);

            SchemeRepository schemeRepo = new SchemeRepository();
            scheme = schemeRepo.getSchemeOfActiveUser();

            schemeData = schemeRepo.getSchemeDataOfActiveUser();
            expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
            expandableListTitle = dayRepo.getAllDaysAsStrings();
            expandableListAdapter = new CustomExpandableListAdapter(getContext(), expandableListTitle, scheme);
            expandableListView.setAdapter(expandableListAdapter);

            expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) ->  {
                //hier zou je dezelfde modal kunnen openen als je een product scant om
                //details te zien van een geconsumeerde product

                Bundle args = new Bundle();
                ProductDialogFragment dialog = new ProductDialogFragment();
                args.putLong("productId", schemeData.get(groupPosition).get(childPosition).getId());
                args.putString("openedBy", "scheme");
                args.putSerializable("parentDay", new DayRepository().getDayByIndex(groupPosition));
                dialog.setArguments(args);
                dialog.show(getFragmentManager(), "ProductDialogFragment");

                return true;
            });
        }
    }
}
