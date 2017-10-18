package com.example.kenan.calorify.dl.Interactors;

import android.text.format.DateUtils;

import com.example.kenan.calorify.dl.models.Consumption;
import com.example.kenan.calorify.dl.models.Product;
import com.example.kenan.calorify.dl.models.User;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Pieter-Jan on 17/10/2017.
 */

public class SchemeInteractor {

    private User activeUser;


    public double GetCaloriesOfDay(LocalDateTime specificDay) {
        int calsOfDays = 0;
        ArrayList<Consumption> consumptionHistory = activeUser.getConsumptionHistory();
        for (Consumption c : consumptionHistory) {
            if (CheckIfDayIsEqual(c.getTimeStamp(), specificDay)) {
                //GetProductFrom DB
                Product P = activeUser.getProductFromSortedMap(c.getProductID());
                //Calculation
                double cals = P.getCalories();
                //ServingQuantity
                double ProductServiceQuantity = P.getServingQuantity();
                //Calories
                double consumptionQuantity = c.getQuantity();
                //Calculate Calories
                calsOfDays += cals * (consumptionQuantity / ProductServiceQuantity);
            }
        }
        return calsOfDays;
    }


    public int getDaysPassedSinceRegistration() {
        LocalDateTime currDat = new LocalDateTime();
        return Days.daysBetween(activeUser.getRegisterDate(), currDat).getDays();
    }

    public HashMap<LocalDateTime, List<Consumption>> getConsumptionsOfDay(LocalDateTime day) {
        List<Consumption> consumptionsOfDay = new ArrayList<Consumption>();

        ArrayList<Consumption> userConsumptionHistory = activeUser.getConsumptionHistory();
        for (Consumption c : userConsumptionHistory) {
            if (CheckIfDayIsEqual(c.getTimeStamp(), day)) {
                consumptionsOfDay.add(c);
            }
        }
        HashMap<LocalDateTime, List<Consumption>> returnHashMap = new HashMap<LocalDateTime, List<Consumption>>();
        returnHashMap.put(day, consumptionsOfDay);
        return returnHashMap;
    }

    public Product getProductWithSpecificID(UUID productID) {
        return activeUser.getProductFromSortedMap(productID);
    }

    public double GetCaloriesOfConsumption(Consumption consumption) {
        double calories;
        double quantity = consumption.getQuantity();
        Product p = activeUser.getProductFromSortedMap(consumption.getProductID());
        double serviceQuantity = p.getServingQuantity();
        double calsPerServiceQuantity = p.getCalories();
        //Calorieën maal de hoeveelheid van de consumptie gedeeld door de servicehoeveelheid(waarvoor het aantal calorieën is gespecifieerd)
        return calsPerServiceQuantity * (quantity / serviceQuantity);
    }

    //HELPER FUNCTIES
    //Kijkt na of twee calenders dezelfde dag zijn
    private boolean CheckIfDayIsEqual(LocalDateTime consumptionTimestamp, LocalDateTime currentTimestamp) {
        if (Days.daysBetween(consumptionTimestamp, currentTimestamp).getDays() != 0) {
            return true;
        }
        return false;
    }

}
