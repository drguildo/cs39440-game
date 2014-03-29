package uk.ac.aber.cs39440.game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Options {
    private static final String OPTIONS_FILENAME = "settings";
    private static Options ref = null;

    public static Options getOptions() {
        if (ref == null) {
            ref = new Options();
        }
        return ref;
    }

    private Properties defaults, p;

    private Options() {
        defaults = new Properties();

        // FIXME: This should always be an odd number.
        defaults.setProperty("number_of_rounds", "5");
        defaults.setProperty("shield_strength", "100");

        p = new Properties(defaults);

        try {
            p.load(new FileReader(OPTIONS_FILENAME));
        } catch (FileNotFoundException e) {
            p = defaults;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return p.getProperty(key);
    }


    // Attempt to return the option value as an integer.
    public int getAsInt(String key) {
        return Integer.parseInt(get(key));
    }

    public void save() {
        try {
            p.store(new FileWriter(OPTIONS_FILENAME),
                    "Game configuration data.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void set(String key, String value) {
        if (p.containsKey(key)) {
            p.setProperty(key, value);
        }
    }

}
