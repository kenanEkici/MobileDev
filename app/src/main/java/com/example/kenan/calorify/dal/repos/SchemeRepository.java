package com.example.kenan.calorify.dal.repos;

import com.example.kenan.calorify.dl.models.Day;
import com.example.kenan.calorify.dl.models.ConsumedProduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Kenan on 19/10/2017.
 */

public class SchemeRepository {
    private DayRepository dayRepo;
    private ConsumedProductRepository productRepo;

    public SchemeRepository() {
        dayRepo = new DayRepository();
        productRepo = new ConsumedProductRepository();
    }

    public HashMap<String, List<String>> getSchemeOfActiveUser() {
        HashMap<String, List<String>> scheme = new HashMap<>();
        List<Day> days = dayRepo.getAllDays();
        List<ConsumedProduct> products = productRepo.getAllProducts();

        for (Day d : Day.listAll(Day.class)) {
            List<String> productsPerDay = new ArrayList<>();
            for (ConsumedProduct prod : products) {
                if (prod.getConsumedAt() != null) {
                    if (prod.getConsumedAt().getDate().equals(d.getDate())) {
                        productsPerDay.add(prod.getBrandName() + " : " + prod.getCalculatedCalories() + " kcal");
                    }
                }
            }
            scheme.put(d.getDate() + "\t\t\t"  + String.valueOf((int)d.getTotalCalories()) + " kcal", productsPerDay);
        }

        //scheme sorteren van nieuw naar oud?? (thanks)
        //hangt af van uw dag klasse

        return  new HashMap<String, List<String>>(scheme);
    }

    public List<List<ConsumedProduct>> getSchemeDataOfActiveUser() {
        List<List<ConsumedProduct>> dataScheme = new ArrayList<List<ConsumedProduct>>();
        List<Day> days = dayRepo.getAllDays();
        List<ConsumedProduct> products = productRepo.getAllProducts();

        for (Day d : days) {
            List<ConsumedProduct> productsPerDay = new ArrayList<>();
            for (ConsumedProduct prod : products) {
                if (prod.getConsumedAt() != null) {
                    if (prod.getConsumedAt().getDate().equals(d.getDate())) {
                        productsPerDay.add(prod);
                    }
                }
            }
            dataScheme.add(productsPerDay);
        }
        return dataScheme;
    }


}
