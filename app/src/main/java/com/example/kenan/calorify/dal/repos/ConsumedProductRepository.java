package com.example.kenan.calorify.dal.repos;

import com.example.kenan.calorify.dl.models.Day;
import com.example.kenan.calorify.dl.models.ConsumedProduct;
import com.example.kenan.calorify.dl.models.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Kenan on 18/10/2017.
 */

public class ConsumedProductRepository {
    public void addProduct(ConsumedProduct product) {
        product.save();
    }

    public ConsumedProduct getProductByid(Long id) {
        return ConsumedProduct.findById(ConsumedProduct.class,id);
    }

    public void deleteEverything(){
        ConsumedProduct.deleteAll(ConsumedProduct.class);
    }

    public List<ConsumedProduct> getAllProducts() {
        return ConsumedProduct.listAll(ConsumedProduct.class);
    }

    public void deleteProductFromScheme(ConsumedProduct product) {
        product.delete();
    }
}