package com.example.kenan.calorify.dal.repos;

import com.example.kenan.calorify.dl.models.Day;
import com.example.kenan.calorify.dl.models.Product;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Kenan on 19/10/2017.
 */

public class DayRepository {

    public Product saveOrUpdateDayOfConsumedProduct(Day day, Product product) {
        for (Day d : Day.listAll(Day.class)) {
            if (d.getDate().equals(day.getDate())) {
                product.setConsumedAt(d);
                return product;
            }
        }

        day.save();
        product.setConsumedAt(day);
        return product;
    }

    public List<Day> getAllDays() {
        List<LocalDate> sortedLocalDates = new ArrayList<LocalDate>();
        List<Day> sortedKenanDates = new ArrayList<Day>();

        //Return SortedList
        for (Day d : Day.listAll(Day.class)) {
            sortedLocalDates.add(LocalDate.parse(d.getDate()));
        }
        Collections.sort(sortedLocalDates);
        for(LocalDate local : sortedLocalDates){
            sortedKenanDates.add(new Day(local.toString()));
        }
        return sortedKenanDates;
    }

    public List<String> getAllDaysAsStrings(){
        List<LocalDate> sortedLocalDates = new ArrayList<LocalDate>();
        List<String> sortedStringDates = new ArrayList<String>();

        //Return SortedList
        for (Day d : Day.listAll(Day.class)) {
            sortedLocalDates.add(LocalDate.parse(d.getDate()));
        }
        Collections.sort(sortedLocalDates);

        for(LocalDate local : sortedLocalDates){
            sortedStringDates.add(local.toString());
        }
        return sortedStringDates;
    }
}
