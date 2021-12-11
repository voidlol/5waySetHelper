package org.example.reader;

import java.text.DecimalFormat;

public abstract class Emblem {

    private String emblemName;
    private int amount;
    private int chaosSpent;
    private double exaltedSpent;
    private int splinters;
    private int totalSplinters;
    private int spentOnSplinters;
    private double maxPriceC = 0, minPriceC = 0;
    private double maxPriceE = 0, minPriceE = 0;

    public Emblem() {
    }

    public Emblem(String name) {
        emblemName = name + " Emblem";
    }

    public void add(String currency, int amountTrade, double price, String name) {
        if (name.contains("Emblem")) {
            amount += amountTrade;
        } else if (name.contains("Splinter")) {
            splinters += amountTrade;
            spentOnSplinters += price;
            totalSplinters += amountTrade;
        }
        if (splinters >= 100) {
            amount += splinters / 100;
            splinters = splinters % 100;
        }
        if (currency.equals("Chaos Orb")) {
            chaosSpent += price;
            maxPriceC = Math.max(price, maxPriceC);
            minPriceC = (minPriceC == 0) ? price : (Math.min(minPriceC, price));
        }
        if (currency.equals("Exalted Orb")) {
            exaltedSpent += price;
            maxPriceE = Math.max(price * ExaltedPrice.getExPrice(), maxPriceE);
            minPriceE = (minPriceE == 0) ? price * ExaltedPrice.getExPrice() : (Math.min(minPriceE, price * ExaltedPrice.getExPrice()));
        }
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.##");
        return emblemName
                + " -> TOTAL: " + amount
                + ", average: " + df.format(getAveragePrice())
                + "c, spent: " + chaosSpent
                + "c, " + df.format(exaltedSpent)
                + "ex " + (splinters > 0 ? ", splinters left: " + splinters : "")
                + (totalSplinters > 0 ? "(" + totalSplinters + " for " + spentOnSplinters + "c)" : "");
    }

    public double getAveragePrice() {
        double average = (chaosSpent + exaltedSpent * ExaltedPrice.getExPrice()) / amount;
        return (Double.isNaN(average) || amount == 0) ? 0d : average;
    }

    public int getTotalSplinters() {
        return totalSplinters;
    }

    public int getSpentOnSplinters() {
        return spentOnSplinters;
    }
}
