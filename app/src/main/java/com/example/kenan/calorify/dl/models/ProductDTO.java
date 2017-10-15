package com.example.kenan.calorify.dl.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kenan on 15/10/2017.
 */

public class ProductDTO
{
    @SerializedName("foods")
    private Product[] products;

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }
}
