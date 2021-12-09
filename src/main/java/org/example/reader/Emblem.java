package org.example.reader;


import java.text.DecimalFormat;

public abstract class Emblem {

    private String emblemName;
    private int amount;
    private int chaosSpent;
    private double exaltedSpent;
    private int splinters;

    public Emblem() {
    }

    public Emblem(String name) {
        emblemName = "Timeless " + name + " Emblem";
    }

    public void add(String currency, int amountTrade, double price, String name) {
        if (name.contains("Emblem")) {
            amount += amountTrade;
        } else if (name.contains("Splinter")) {
            splinters += amountTrade;
        }
        if (splinters >= 100) {
            amount += splinters / 100;
            splinters = splinters % 100;
        }
        if (currency.equals("Chaos Orb")) chaosSpent += price;
        if (currency.equals("Exalted Orb")) exaltedSpent += price;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.#");
        return emblemName
                + " -> TOTAL: " + amount
                + ", chaos orbs spent: " + chaosSpent
                + ", exalted orbs spent: " + df.format(exaltedSpent)
                + (splinters > 0 ? ", splinters left: " + splinters : "");
    }

    public double getAveragePrice(int ratio) {
        return (chaosSpent + exaltedSpent * ratio) / amount;
    }
}
