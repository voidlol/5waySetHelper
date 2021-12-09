package org.example.domain;

import java.text.DecimalFormat;

public class Trade {

    private static int totalTrades = 0;
    private final String date;
    private final String item;
    private final String currency;
    private final double price;
    private final double pricePerUnit;
    private final int amount;

    public Trade(String date, String item, String currency, int amount, double price) {
        this.amount = amount;
        this.date = date;
        this.item = item;
        this.currency = currency;
        this.price = price;
        this.pricePerUnit = amount > 1 ? price / amount : price;
        totalTrades++;
    }

    public String getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public String getItem() {
        return item;
    }

    public String getCurrency() {
        return currency;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.#");
        return this.amount + "x " + this.item + " for " + df.format(this.price) + " " + this.currency +
                (this.pricePerUnit != price ? " (" + df.format(this.pricePerUnit) + " " + this.currency + " per 1 " + this.item + ")"
                        : "");
    }
}
