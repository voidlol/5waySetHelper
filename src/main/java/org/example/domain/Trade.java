package org.example.domain;

import org.example.reader.ExaltedPrice;

import java.text.DecimalFormat;
import java.util.Objects;

public class Trade {

    private final String date;
    private final String item;
    private final String currency;
    private final double price;
    private final double pricePerUnit;
    private final int amount;
    private static final ExaltedPrice exaltedPrice = new ExaltedPrice(150);

    public Trade(String date, String item, String currency, int amount, double price) {
        this.amount = amount;
        this.date = date;
        this.item = item;
        this.currency = currency;
        this.price = price;
        this.pricePerUnit = amount > 1 ? price / amount : price;
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

    public static int getExPrice() {
        return exaltedPrice.getExPrice();
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.#");
        return this.amount + "x " + this.item + " for " + df.format(this.price) + " " + this.currency +
                (this.pricePerUnit != price ? " (" + df.format(this.pricePerUnit) + " " + this.currency + " per 1 " + this.item + ")"
                        : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trade trade = (Trade) o;
        return Double.compare(trade.getPrice(), getPrice()) == 0 && Double.compare(trade.pricePerUnit, pricePerUnit) == 0 && getAmount() == trade.getAmount() && getDate().equals(trade.getDate()) && getItem().equals(trade.getItem()) && getCurrency().equals(trade.getCurrency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getItem(), getCurrency(), getPrice(), pricePerUnit, getAmount());
    }
}
