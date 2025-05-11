package edu.grinnell.csc207.item;

/**
 * it stores an item and its quantity
 */
public abstract class ItemPair {
    protected int counts;

    /**
     * Get the quantity of item
     * @return quantity
     */
    public int getCounts() {
        return this.counts;
    }

    /**
     * Change the quantity of item
     * @param changeVal
     */
    public void changeCounts(int changeVal) {
        this.counts += changeVal;
    }

}
