package com.example.kenan.calorify.dal.repos;

import com.example.kenan.calorify.dl.models.Product;
import com.example.kenan.calorify.dl.models.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Kenan on 18/10/2017.
 */

public class ProductRepository {
    public void addProduct(Product product) {
        product.save();
    }

    public Product getProductByid(Long id) {
        return Product.findById(Product.class,id);
    }

    public List<Product> getAllProducts() {
        return Product.listAll(Product.class);
    }
}
