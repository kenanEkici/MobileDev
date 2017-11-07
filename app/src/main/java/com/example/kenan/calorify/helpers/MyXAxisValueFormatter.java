package com.example.kenan.calorify.helpers;


import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gebruiker on 7/11/2017.
 */

public class MyXAxisValueFormatter implements IAxisValueFormatter {

    private List<String> mValues;

    public MyXAxisValueFormatter(ArrayList<String> values){
        this.mValues = values;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        // "value" represents the position of the label on the axis (x or y)µ
        try {
            return mValues.get((int) value);
        } catch (Exception e) {
            return "";
        }
    }
}
