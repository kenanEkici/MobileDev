package com.example.kenan.calorify.dl.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kenan on 15/10/2017.
 */

public class ProductDTO
{
    @SerializedName("foods")
    private ScannedProduct[] products;

    public ScannedProduct[] getProducts() {
        return products;
    }

    public void setProducts(ScannedProduct[] products) {
        this.products = products;
    }
}
