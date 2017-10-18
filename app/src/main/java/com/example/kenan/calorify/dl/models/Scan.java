package com.example.kenan.calorify.dl.models;

import org.joda.time.LocalDateTime;

import java.util.GregorianCalendar;

/**
 * Created by Pieter-Jan on 17/10/2017.
 */

public class Scan {

    private final LocalDateTime timeStamp;
    private final int productId;

    public Scan(LocalDateTime timeStamp, int productId) {
        this.timeStamp = timeStamp;
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
