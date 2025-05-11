package edu.grinnell.csc207;

/**
 * RoundCOunter
 */
public class RoundCounter {
    private int round;

    /**
     * Instantiate RoundCounter
     */
    public RoundCounter() {
        this.round = 0;
    }

    /**
     * get current round
     * 
     * @return current round
     */
    public int getRound() {
        return this.round;
    }

    /**
     * move to next round
     */
    public void nextRound() {
        this.round++;
    }

    /**
     * get the new round sign
     */
    @Override
    public String toString() {
        String printInt = Integer.toString(round);
        for (int iter = printInt.length(); iter < 3; iter++) {
            printInt = "0" + printInt;
        }
        return (printInt + " ====");
    }

}
