package uk.ac.aber.cs39440.experiments;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Options {

    public static void main(String[] args) {
        Properties p1 = new Properties();
        Properties p2 = new Properties();

        System.out.println(p1.getProperty("test1", "Good."));

        p1.setProperty("test2", "Good.");
        System.out.println(p1.getProperty("test2", "Bad."));

        try {
            p1.store(new FileWriter("settings.txt"), "A comment.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            p2.load(new FileReader("settings.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(p2.getProperty("test2", "Bad."));
    }

}
