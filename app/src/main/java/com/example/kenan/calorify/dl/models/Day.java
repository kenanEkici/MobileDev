package com.example.kenan.calorify.dl.models;

import com.orm.SugarRecord;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.io.Serializable;

/**
 * Created by Kenan on 18/10/2017.
 */

public class Day extends SugarRecord<Day> implements Serializable {

    private String date;
    private double totalCalories;

    public Day() {

    }

    public Day(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }

    @Override
    public String toString() {
        return date;
    }
}
