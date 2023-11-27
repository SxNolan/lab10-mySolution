package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.StringTokenizer;

/**
 *
 */
public final class DrawNumberImpl implements DrawNumber {

    private int choice;
    private final int min;
    private final int max;
    private final int attempts;
    private int remainingAttempts;
    private final Random random = new Random();
    private String relativePath = "src/main/resources/config.yml";
    BufferedReader myBufferedReader;
    /**
     * @throws IOException
     * @throws IllegalStateException if the configuration is not consistent
     */
    public DrawNumberImpl() throws IOException {
        myBufferedReader = new BufferedReader(new FileReader(relativePath));
        String[] dividedString;
        dividedString = myBufferedReader.readLine().split(":");
        this.min = Integer.parseInt(dividedString[1]);
        dividedString = myBufferedReader.readLine().split(":");
        this.max = Integer.parseInt(dividedString[1]);
        dividedString = myBufferedReader.readLine().split(":");
        this.attempts = Integer.parseInt(dividedString[1]);
        myBufferedReader.close();
        this.reset();
    }

    @Override
    public void reset() {
        this.remainingAttempts = this.attempts;
        this.choice = this.min + random.nextInt(this.max - this.min + 1);
    }

    @Override
    public DrawResult attempt(final int n) {
        if (this.remainingAttempts <= 0) {
            return DrawResult.YOU_LOST;
        }
        if (n < this.min || n > this.max) {
            throw new IllegalArgumentException("The number is outside boundaries");
        }
        remainingAttempts--;
        if (n > this.choice) {
            return DrawResult.YOURS_HIGH;
        }
        if (n < this.choice) {
            return DrawResult.YOURS_LOW;
        }
        return DrawResult.YOU_WON;
    }

}
