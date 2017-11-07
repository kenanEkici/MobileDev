package com.example.kenan.calorify.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kenan.calorify.R;
import com.example.kenan.calorify.dal.repos.UserRepository;
import com.example.kenan.calorify.dal.repos.WeightRepository;
import com.example.kenan.calorify.dl.models.User;
import com.example.kenan.calorify.dl.models.Weight;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

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
        WeightRepository weightRepo = new WeightRepository();

        LineChart chart = (LineChart) view.findViewById(R.id.profile_Chart);

        List<Entry> entries = new ArrayList<Entry>();
        List<Entry> idealEntries = new ArrayList<Entry>();

        ArrayList<String> labels = new ArrayList<String>();
        List<Weight> weightList = weightRepo.getAllWeights();

        for (int i = 0; i < weightList.size(); i++) {
            entries.add(new Entry((float) i, (float) weightList.get(i).getWeight()));
            idealEntries.add(new Entry((float) i, (float) activeUser.getIdealWeight()));
            labels.add(weightList.get(i).getGraphDate());
        }

        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return (String) labels.toArray()[(int) v];
            }
        };

        LineDataSet dataSet = new LineDataSet(entries, "");
        dataSet.setLineWidth(5f);
        dataSet.setDrawCircles(true);
        dataSet.setValueTextSize(10f);
        LineData lineData = new LineData(dataSet);


        LineDataSet idealDataSet = new LineDataSet(idealEntries, "");
        idealDataSet.setLineWidth(2f);
        idealDataSet.setDrawCircles(false);
        idealDataSet.setDrawValues(false);
        idealDataSet.disableDashedHighlightLine();
        idealDataSet.setColor(Color.rgb(255,165,0));
        LineData idealLineData = new LineData(idealDataSet);


        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(idealDataSet);
        dataSets.add(dataSet);


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
        LineData data =  new LineData(dataSets);
        chart.setData(data);
        chart.getLegend().setEnabled(false);
        chart.invalidate();


        // Inflate the layout for this fragment/*
        setHasOptionsMenu(true);
        return view;
    }
}
