package org.example.reader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) throws IOException {
        init();
    }

    private static void init() throws IOException {
        Parser parser = new Parser();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    //Document tradeLog = Jsoup.parse(new File("\\\\voidbook\\Users\\udrog\\OneDrive\\Документы\\ExileAgent\\" + "TradeLog.html"), "UTF-8");
                    Document tradeLog = Jsoup.parse(new File("TradeLog.html"), "UTF-8");
                    clearScreen();
                    parser.readString(tradeLog.body().getElementById("tbBody").getElementsByTag("tr").toString());
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
        }, 1000, 10000);
    }

    private static void clearScreen() {
        try{
            final String os = System.getProperty("os.name");
            if (os.contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
