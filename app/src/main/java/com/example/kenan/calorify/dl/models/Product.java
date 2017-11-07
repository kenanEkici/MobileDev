package com.example.kenan.calorify.dl.models;

import com.facebook.stetho.json.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.List;

/**
 * Created by Kenan on 10/10/2017.
 */

public class Product extends SugarRecord<Product>{

    private Day consumedAt;
    private double consumedQuantity;
    private String consumedUnit;
    private String scannedAt;
    private double calculatedCalories;

    @SerializedName("food_name")
    private String foodName;

    @SerializedName("brand_name")
    private String brandName;

    @SerializedName("serving_qty")
    private double servingQuantity;

    @SerializedName("serving_weight_grams")
    private double servingWeightInGrams;

    @SerializedName("nf_calories")
    private double calories;

    @SerializedName("nf_total_fat")
    private double totalFat;

    @SerializedName("nf_saturated_fat")
    private double saturatedFatOfTotal;

    @SerializedName("nf_cholesterol")
    private double cholesterol;

    @SerializedName("nf_sodium")
    private double sodium;

    @SerializedName("nf_total_carbohydrate")
    private double carbsTotal;

    @SerializedName("nf_dietary_fiber")
    private double fibers;

    @SerializedName("nf_sugars")
    private double sugars;

    @SerializedName("nf_protein")
    private double protein;

    @SerializedName("nf_potassium")
    private double potassium;

    public Day getConsumedAt() {
        return consumedAt;
    }

    public void setConsumedAt(Day consumedAt) {
        this.consumedAt = consumedAt;
    }

    public double getConsumedQuantity() {
        return consumedQuantity;
    }

    public void setConsumedQuantity(double consumedQuantity) {
        this.consumedQuantity = consumedQuantity;
    }

    public String getConsumedUnit() {
        return consumedUnit;
    }

    public void setConsumedUnit(String consumedUnit) {
        this.consumedUnit = consumedUnit;
    }

    public String getScannedAt() {
        return scannedAt;
    }

    public void setScannedAt(String scannedAt) {
        this.scannedAt = scannedAt;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public double getServingQuantity() {
        return servingQuantity;
    }

    public void setServingQuantity(double servingQuantity) {
        this.servingQuantity = servingQuantity;
    }

    public double getServingWeightInGrams() {
        return servingWeightInGrams;
    }

    public void setServingWeightInGrams(double servingWeightInGrams) {
        this.servingWeightInGrams = servingWeightInGrams;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(double totalFat) {
        this.totalFat = totalFat;
    }

    public double getSaturatedFatOfTotal() {
        return saturatedFatOfTotal;
    }

    public void setSaturatedFatOfTotal(double saturatedFatOfTotal) {
        this.saturatedFatOfTotal = saturatedFatOfTotal;
    }

    public double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public double getCarbsTotal() {
        return carbsTotal;
    }

    public void setCarbsTotal(double carbsTotal) {
        this.carbsTotal = carbsTotal;
    }

    public double getFibers() {
        return fibers;
    }

    public void setFibers(double fibers) {
        this.fibers = fibers;
    }

    public double getSugars() {
        return sugars;
    }

    public void setSugars(double sugars) {
        this.sugars = sugars;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getPotassium() {
        return potassium;
    }

    public void setPotassium(double potassium) {
        this.potassium = potassium;
    }

    public double getCalculatedCalories() {
        return calculatedCalories;
    }

    public void setCalculatedCalories(double calculatedCalories) {
        this.calculatedCalories = calculatedCalories;
    }

    @Override
    public String toString(){
        return getBrandName();
    }

    public Double getTotalCalories() {
        return calories * servingQuantity;
    }
}
