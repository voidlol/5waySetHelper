package org.example.reader;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        BufferedReader br = new BufferedReader(new FileReader(new File("resources/" + "string.txt")));
        parser.readString(br.readLine());
    }
}
