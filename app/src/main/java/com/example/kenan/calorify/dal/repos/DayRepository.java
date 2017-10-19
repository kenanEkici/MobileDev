package com.example.kenan.calorify.dal.repos;

import com.example.kenan.calorify.dl.models.Day;
import com.example.kenan.calorify.dl.models.Product;

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
        return Day.listAll(Day.class);
    }

}
