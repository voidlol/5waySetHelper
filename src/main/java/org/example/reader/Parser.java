package org.example.reader;
import org.apache.commons.lang3.StringUtils;
import org.example.domain.Trade;

import java.util.ArrayList;
import java.util.List;
//<tr><td>21/11/2021 17:09:24</td><td>Buy</td><td>2</td><td>Exalted Orb</td><td>291</td><td>Chaos Orb</td><td>regret_base (2)</td><td>KayfatVol</td><td>Completed</td></tr>
//<tr><td>24/11/2021 16:49:50</td><td>Sell</td><td>12</td><td>Chaos Orb</td><td>15</td><td>Timeless Templar Splinter</td><td>regret_base (12)</td><td>Tbxi</td><td>Failed</td></tr>
public class Parser {

    private List<Trade> trades = new ArrayList<>();

    public void readString(String line) {
        for (String s: StringUtils.substringsBetween(line, "<tr>", "</tr>")) {
            String[] params = StringUtils.substringsBetween(s, "<td>", "</td>");
            if (params[8].equals("Completed") && !params[5].equals("Chaos Orb")) {
                Trade currentTrade = new Trade(params[0], params[5], params[3], Integer.parseInt(params[4]), Double.parseDouble(params[2].replace(',','.')));
                trades.add(currentTrade);
                System.out.println(currentTrade);
            }
        }

        System.out.println(trades.size());

        sortTrades();
    }

    private void sortTrades() {
        DataStorage ds = DataStorage.getInstance();
        for (Trade t: this.trades) {
            ds.add(t);
        }

        System.out.println(ds);
        System.out.println(ds.getAverageSetPrice(140));
        System.out.println(ds.getAverageSetPrice(140) / 140);
    }

}
