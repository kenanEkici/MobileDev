package com.example.kenan.calorify.datalayer.models;

import com.orm.SugarRecord;

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

    public User(){
    }

    public User(String fullName, Gender gender, double weight, double height, int age, boolean isActive){
        setActive(isActive);
        setFullName(fullName);
        setGender(gender);
        setWeight(weight);
        setHeight(height);
        setAge(age);
        setBmi(weight,height);
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
}
