package com.example.kenan.calorify.dal.repos;

import com.example.kenan.calorify.dl.models.ConsumedProduct;
import com.example.kenan.calorify.dl.models.ScannedProduct;

import java.util.List;

/**
 * Created by Kenan on 31/10/2017.
 */

public class ScannedProductRepository {
    public void addProduct(ScannedProduct product) {
        product.save();
    }

    public ScannedProduct getProductByid(Long id) {
        return ScannedProduct.findById(ScannedProduct.class,id);
    }

    public void deleteEverything() {
        ScannedProduct.deleteAll(ScannedProduct.class);
    }

    public List<ScannedProduct> getAllProducts() {
        return ScannedProduct.listAll(ScannedProduct.class);
    }
}
