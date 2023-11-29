package com.example.android_lesson_4.model;

import java.io.Serializable;

public class ItemQuantityPrice implements Serializable {
    private double price;
    private int quantity;

    private String drink;

    public ItemQuantityPrice(double price, int quantity, String drink) {
        this.price = price;
        this.quantity = quantity;
        this.drink = drink;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }
}
