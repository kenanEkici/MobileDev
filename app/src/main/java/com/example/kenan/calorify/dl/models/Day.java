package com.example.kenan.calorify.dl.models;

import com.orm.SugarRecord;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

/**
 * Created by Kenan on 18/10/2017.
 */

public class Day extends SugarRecord<Day> {

    private String date;

    public Day() {

    }

    public Day(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return date;
    }
}
