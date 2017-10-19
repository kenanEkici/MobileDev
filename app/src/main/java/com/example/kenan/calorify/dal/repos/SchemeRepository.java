package com.example.kenan.calorify.dal.repos;

import com.example.kenan.calorify.dl.models.Day;
import com.example.kenan.calorify.dl.models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Kenan on 19/10/2017.
 */

public class SchemeRepository {
    private DayRepository dayRepo;
    private ProductRepository productRepo;

    public SchemeRepository() {
        dayRepo = new DayRepository();
        productRepo = new ProductRepository();
    }

    public HashMap<String,List<String>> getSchemeOfActiveUser() {
        HashMap<String, List<String>> scheme = new HashMap<>();
        List<Day> days = dayRepo.getAllDays();
        List<Product> products = productRepo.getAllProducts();

        for(Day d : days) {
            List<String> productsPerDay = new ArrayList<>();
            for(Product prod : products) {
                if (prod.getConsumedAt() != null) {
                    if (prod.getConsumedAt().getDate().equals(d.getDate())) {
                        productsPerDay.add(prod.getBrandName());
                    }
                }
            }
            scheme.put(d.getDate(), productsPerDay);
        }

        //scheme sorteren van nieuw naar oud?? (thanks)
        return scheme;
    }

}
