package org.example.reader;

import org.example.domain.Trade;

public class DataStorage {

    private static final Templar templar = new Templar();
    private static final Maraketh maraketh = new Maraketh();
    private static final Vaal vaal = new Vaal();
    private static final Eternal eternal = new Eternal();
    private static final Karui karui = new Karui();
    private static DataStorage instance;
    private static final int exaltedPrice = ExaltedPrice.getExPrice();

    private DataStorage() {
    }

    public static DataStorage getInstance() {
        if (instance == null) {
            instance = new DataStorage();
        }
        return instance;
    }

    public void add(Trade t) {
            if (t.getItem().contains("Timeless Eternal")) eternal.add(t.getCurrency(), t.getAmount(), t.getPrice(), t.getItem());
            if (t.getItem().contains("Timeless Karui")) karui.add(t.getCurrency(), t.getAmount(), t.getPrice(), t.getItem());
            if (t.getItem().contains("Timeless Vaal")) vaal.add(t.getCurrency(), t.getAmount(), t.getPrice(), t.getItem());
            if (t.getItem().contains("Timeless Templar")) templar.add(t.getCurrency(), t.getAmount(), t.getPrice(), t.getItem());
            if (t.getItem().contains("Timeless Maraketh")) maraketh.add(t.getCurrency(), t.getAmount(), t.getPrice(), t.getItem());
    }

    @Override
    public String toString() {
        return eternal.toString() + "\n"
                + karui.toString() + "\n"
                + vaal.toString() + "\n"
                + templar.toString() + "\n"
                + maraketh.toString();
    }

    public double getAverageSetPriceC() {
        double eternalAverage = eternal.getAveragePrice();
        double karuiAverage = karui.getAveragePrice();
        double vaalAverage = vaal.getAveragePrice();
        double templarAverage = templar.getAveragePrice();
        double marakethAverage = maraketh.getAveragePrice();

        return eternalAverage + karuiAverage + vaalAverage + templarAverage + marakethAverage;
    }

    public double getAverageSetPriceEx() {
        return getAverageSetPriceC() / exaltedPrice;
    }

    public int getExaltedPrice() {
        return exaltedPrice;
    }
}
