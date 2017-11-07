package com.example.kenan.calorify.helpers;

import com.example.kenan.calorify.dl.models.ConsumedProduct;
import com.example.kenan.calorify.dl.models.ScannedProduct;

/**
 * Created by Kenan on 31/10/2017.
 */

public class ProductHelper {

    public static ConsumedProduct transformScannedToConsumed(ScannedProduct scannedProduct){
        ConsumedProduct product = new ConsumedProduct();
        product.setBrandName(scannedProduct.getBrandName());
        product.setCalories(scannedProduct.getCalories());
        product.setCarbsTotal(scannedProduct.getCarbsTotal());
        product.setCholesterol(scannedProduct.getCholesterol());
        product.setFibers(scannedProduct.getFibers());
        product.setFoodName(scannedProduct.getFoodName());
        product.setPotassium(scannedProduct.getPotassium());
        product.setProtein(scannedProduct.getProtein());
        product.setSaturatedFatOfTotal(scannedProduct.getSaturatedFatOfTotal());
        product.setServingQuantity(scannedProduct.getServingQuantity());
        product.setServingWeightInGrams(scannedProduct.getServingWeightInGrams());
        product.setSodium(scannedProduct.getSodium());
        product.setSugars(scannedProduct.getSugars());
        product.setTotalFat(scannedProduct.getTotalFat());
        return product;
    }
}
