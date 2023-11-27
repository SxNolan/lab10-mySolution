package it.unibo.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {
    
    private final DrawNumber model;
    private List<DrawNumberView> views;

    /**
     * @param views
     *            the views to attach
     * @throws IOException
     */
    public DrawNumberApp() throws IOException {
        views = new ArrayList<>();
        /*
         * Side-effect proof
         */
        views.add(new DrawNumberViewImpl());
        views.add(new DrawNumberViewImpl());
        
        //TO BE COMPLETED HERE

        views.forEach(elem -> {
            elem.start();
        });
        this.model = new DrawNumberImpl();
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view: views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view: views) {
                view.numberIncorrect();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        /*
         * A bit harsh. A good application should configure the graphics to exit by
         * natural termination when closing is hit. To do things more cleanly, attention
         * should be paid to alive threads, as the application would continue to persist
         * until the last thread terminates.
         */
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     * @throws IOException
     */
    public static void main(final String... args) throws IOException {
        new DrawNumberApp();
    }

}
