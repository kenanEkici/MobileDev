package com.example.kenan.calorify.dal.repos;

import com.example.kenan.calorify.dl.models.Weight;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pieter-Jan on 7/11/2017.
 */

public class WeightRepository {

    public Weight getWeight(String Date) {
        Weight foundWeight = null;
        for (Weight w : getAllWeights()) {
            if(w.getDate().toString().equals(Date)) {
                foundWeight = w;
                break;
            }
        }
        return foundWeight;
    }

    public List<Weight> getAllWeights() {
        return Weight.listAll(Weight.class);
    }


    public void addWeight(LocalDateTime now , double weight){
        Weight newWeight = new Weight();
        newWeight.setWeight(weight);
        newWeight.setDate(now);
        newWeight.setGraphDate(now);
        newWeight.save();
    }
}
