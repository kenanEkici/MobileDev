package com.example.kenan.calorify.dl.models;

import org.joda.time.LocalDateTime;

import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * Created by Pieter-Jan on 17/10/2017.
 */

public class Consumption {
    private final LocalDateTime timeStamp;
    private final UUID productID;
    private final double quantity;

    public Consumption(LocalDateTime timeStamp, UUID productID, double quantity) {
        this.timeStamp = timeStamp;
        this.productID = productID;
        this.quantity = quantity;
    }

    public UUID getProductID() {
        return productID;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public double getQuantity() {
        return quantity;
    }
}