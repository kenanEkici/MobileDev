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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

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



        //Profile chart
        LineChart chart = (LineChart) view.findViewById(R.id.profile_Chart);

        List<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(0f,80.0f));
        entries.add(new Entry(1f,85.0f));
        entries.add(new Entry(2f,90.0f));
        entries.add(new Entry(3f,95.0f));
        entries.add(new Entry(4f,100.0f));

        
        LineDataSet dataSet  = new LineDataSet(entries, "");
        dataSet.setLineWidth(2f);
        dataSet.setDrawCircles(true);
        dataSet.setValueTextSize(5f);
        LineData lineData = new LineData(dataSet);



        final String[] labels = new String[] {"1/11","2/11","3/11","4/11","5/11"};
        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return labels[(int) v];
            }
        };



        //Change axis
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);


        YAxis leftYAxis = chart.getAxis(YAxis.AxisDependency.LEFT);
        leftYAxis.setDrawLabels(true);
        leftYAxis.setTextSize(10f);

        YAxis rightYAxis = chart.getAxis(YAxis.AxisDependency.RIGHT);
        rightYAxis.setDrawLabels(false);

        chart.getDescription().setEnabled(false);
        chart.setData(lineData);
        chart.invalidate();
        // Inflate the layout for this fragment/*
        setHasOptionsMenu(true);
        return view;
    }
}
