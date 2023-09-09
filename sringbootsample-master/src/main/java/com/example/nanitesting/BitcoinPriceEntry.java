package com.example.nanitesting;

public class BitcoinPriceEntry {
    private String date;
    private double price;
    private String currency;
    private boolean highest;
    private boolean lowest;

    public BitcoinPriceEntry() {

    }

    public BitcoinPriceEntry(String date, double price, String currency, boolean highest, boolean lowest) {
        this.date = date;
        this.price = price;
        this.currency = currency;
        this.highest = highest;
        this.lowest = lowest;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isHighest() {
        return highest;
    }

    public void setHighest(boolean highest) {
        this.highest = highest;
    }

    public boolean isLowest() {
        return lowest;
    }

    public void setLowest(boolean lowest) {
        this.lowest = lowest;
    }
}
