package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Encapsulates the concept of configuration.
 */
public final class Configuration {

    private int max;
    private int min;
    private int attempts;
    private BufferedReader br;

    /**
     * Constructor modified for Configuration; takes the infos by a res file in YML format.
     */
    public Configuration() {
        try {
            String[] regexReturn; 
            br = new BufferedReader(new FileReader("lab10-mySolution/102-advanced-mvc/src/main/resources/config.yml"));
            try {
                regexReturn = br.readLine().split(": ");
                this.min = Integer.parseInt(regexReturn[1]);
                regexReturn = br.readLine().split(": ");
                this.max = Integer.parseInt(regexReturn[1]);
                regexReturn = br.readLine().split(": ");
                this.attempts = Integer.parseInt(regexReturn[1]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * @return the maximum value
     */
    public int getMax() {
        return max;
    }

    /**
     * @return the minimum value
     */
    public int getMin() {
        return min;
    }

    /**
     * @return the number of attempts
     */
    public int getAttempts() {
        return attempts;
    }

    /**
     * @return true if the configuration is consistent
     */
    public boolean isConsistent() {
        return attempts > 0 && min < max;
    }

}

