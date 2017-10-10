package com.example.kenan.calorify.dal.services;

import com.example.kenan.calorify.dl.models.Product;

import java.util.List;

/**
 * Created by Kenan on 10/10/2017.
 */

public interface FoodService {
    List<Product> getAll();
}
