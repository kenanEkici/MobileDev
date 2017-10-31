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

    public HashMap<String, List<String>> getSchemeOfActiveUser() {
        HashMap<String, List<String>> scheme = new HashMap<>();
        List<Day> days = dayRepo.getAllDays();
        List<Product> products = productRepo.getAllProducts();

        for (Day d : Day.listAll(Day.class)) {
            List<String> productsPerDay = new ArrayList<>();
            for (Product prod : products) {
                if (prod.getConsumedAt() != null) {
                    if (prod.getConsumedAt().getDate().equals(d.getDate())) {
                        productsPerDay.add(prod.getBrandName() + " : " + prod.getCalculatedCalories() + " kcal");
                    }
                }
            }
            scheme.put(d.getDate() + "\t\t\t"  + String.valueOf(d.getTotalCalories()) + " kcal", productsPerDay);
        }

        //scheme sorteren van nieuw naar oud?? (thanks)
        //hangt af van uw dag klasse

        return  new HashMap<String, List<String>>(scheme);
    }

    public List<List<Product>> getSchemeDataOfActiveUser() {
        List<List<Product>> dataScheme = new ArrayList<List<Product>>();
        List<Day> days = dayRepo.getAllDays();
        List<Product> products = productRepo.getAllProducts();

        for (Day d : days) {
            List<Product> productsPerDay = new ArrayList<>();
            for (Product prod : products) {
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
