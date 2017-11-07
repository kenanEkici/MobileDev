package com.example.kenan.calorify.dl.models;

        import com.orm.SugarRecord;

        import org.joda.time.LocalDateTime;

/**
 * Created by Pieter-Jan on 7/11/2017.
 */

public class Weight extends SugarRecord<Weight> {


    private String date;
    private String graphDate;
    private double weight;

    public Weight(){}

    public String getDate() {
        return date;
    }

    public String getGraphDate() {
        return graphDate;
    }

    public double getWeight() {
        return weight;
    }

    public void setDate(LocalDateTime date) {
        this.date = date.toString();

    }

    public void setGraphDate(LocalDateTime time) {
        this.graphDate = time.getDayOfMonth()  + "/" + time.getMonthOfYear();

    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}

