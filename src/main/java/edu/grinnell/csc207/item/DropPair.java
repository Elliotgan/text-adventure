package edu.grinnell.csc207.item;

/**
 * A pair that stores dropable item and its weight to be dropped
 */
public class DropPair {
    private int weight;
    private Dropable dropable;

    /**
     * Instantiate DropPair
     * 
     * @param weight
     * @param dropable
     */
    public DropPair(int weight, Dropable dropable) {
        this.weight = weight;
        this.dropable = dropable;
    }

    /**
     * Get the weight of current Dropable item(its possibility to drop)
     * 
     * @return weight of current Dropable item
     */
    public int getWeight() {
        return this.weight;
    }

    /**
     * Get the dropable item
     * 
     * @return dropable item
     */
    public Dropable getDropable() {
        return this.dropable;
    }
}
