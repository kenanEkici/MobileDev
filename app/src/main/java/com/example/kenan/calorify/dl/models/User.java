package com.example.kenan.calorify.dl.models;

import android.support.v7.util.SortedList;

import com.example.kenan.calorify.dl.enums.Gender;
import com.orm.SugarRecord;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Created by Kenan on 7/10/2017.
 */

public class User extends SugarRecord<User> {

    private boolean isActive;
    private String fullName;
    private Gender gender;
    private double weight;
    private double height;
    private int age;
    private double bmi;

    private final LocalDateTime registerDate;
    //private Map<UUID, Product> productSortedMap = new TreeMap<UUID, Product>();
    //private ArrayList<Scan> scanHistory = new ArrayList<Scan>();
    //private ArrayList<Consumption> consumptionHistory = new ArrayList<Consumption>();


    public User(){
        registerDate = new LocalDateTime();
    }

    public User(String fullName, Gender gender, double weight, double height, int age, boolean isActive){
        setActive(isActive);
        setFullName(fullName);
        setGender(gender);
        setWeight(weight);
        setHeight(height);
        setAge(age);
        setBmi(weight,height);
        registerDate = new LocalDateTime();
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double height, double weight) {
        this.bmi = weight / Math.pow(height,2);
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return getFullName();
    }

//    public Product getProductFromSortedMap(UUID productID){
//        return productSortedMap.get(productID);
//    }
//
//    public void addProductToSortedMap(Product prod) {
//        productSortedMap.put(UUID.randomUUID(), prod);
//    }
//
//    public ArrayList<Consumption> getConsumptionHistory() {
//        return consumptionHistory;
//    }
//
//    public ArrayList<Scan> getScanHistory() {
//        return scanHistory;
//    }


    public LocalDateTime getRegisterDate() {
        return registerDate;
    }
}
