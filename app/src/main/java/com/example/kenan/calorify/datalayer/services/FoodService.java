package com.example.kenan.calorify.datalayer.services;

import com.example.kenan.calorify.datalayer.models.Product;

import java.util.List;

/**
 * Created by Kenan on 10/10/2017.
 */

public interface FoodService {
    List<Product> getAll();
}
