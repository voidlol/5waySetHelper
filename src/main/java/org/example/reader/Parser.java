package org.example.reader;
import org.apache.commons.lang3.StringUtils;
import org.example.domain.Trade;

import java.text.DecimalFormat;
import java.util.HashSet;

public class Parser {

    private boolean firstRun = true;
    private final DecimalFormat df = new DecimalFormat("0.##");
    private final DataStorage ds = DataStorage.getInstance();
    private final HashSet<Trade> tradeHashSet = new HashSet<>();

    public void readString(String line) {
        for (String s: StringUtils.substringsBetween(line, "<tr>", "</tr>")) {
            String[] params = StringUtils.substringsBetween(s, "<td>", "</td>");
            if (params.length == 9 && params[8].equals("Completed") && !params[5].equals("Chaos Orb") && !params[5].equals("Exalted Orb")) {
                Trade currentTrade = new Trade(params[0], params[5], params[3], Integer.parseInt(params[4]), Double.parseDouble(params[2].replace(',', '.')));
                if (!firstRun && !tradeHashSet.contains(currentTrade)) {
                    ds.add(currentTrade);
                }
                tradeHashSet.add(currentTrade);
            }
        }
        firstRun = false;

        showTrades();
    }

    private void showTrades() {
        System.out.println(ds);
        System.out.println(df.format(ds.getAverageSetPriceC()) + " Chaos Orbs");
        System.out.println(df.format(ds.getAverageSetPriceEx()) + " Exalted Orbs");
    }

}
