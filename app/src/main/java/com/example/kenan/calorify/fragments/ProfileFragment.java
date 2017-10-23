package com.example.kenan.calorify.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kenan.calorify.R;
import com.example.kenan.calorify.dal.repos.UserRepository;
import com.example.kenan.calorify.dl.models.User;

/**
 * Created by Kenan on 10/10/2017.
 */

public class ProfileFragment extends Fragment {

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = inflater.inflate(R.layout.menu_profile_frag, null);

        UserRepository userRepo = new UserRepository();
        User activeUser = userRepo.getActiveUser();

        TextView nameText = (TextView) view.findViewById(R.id.profile_Name);
        TextView ageText = (TextView) view.findViewById(R.id.profile_Age);
        TextView weightText = (TextView) view.findViewById(R.id.profile_Weight);
        TextView genderText = (TextView) view.findViewById(R.id.profile_Gender);
        TextView heightText = (TextView) view.findViewById(R.id.profile_Height);
        TextView idealWeightText = (TextView) view.findViewById(R.id.profile_IdealWeight);
        TextView BMIText = (TextView) view.findViewById(R.id.profile_BMI);


        //Fill With Data

        nameText.setText(activeUser.getFullName());
        ageText.setText(Integer.toString(activeUser.getAge()));
        genderText.setText(activeUser.getGender().toString());
        weightText.setText(String.format("%.1f", activeUser.getWeight()));
        heightText.setText(String.format("%.1f", activeUser.getHeight()));
        idealWeightText.setText(String.format("%.1f", activeUser.getIdealWeight()));
        BMIText.setText(String.format("%.2f", activeUser.getBmi()));

        // Inflate the layout for this fragment/*
        setHasOptionsMenu(true);
        return view;
    }
}
