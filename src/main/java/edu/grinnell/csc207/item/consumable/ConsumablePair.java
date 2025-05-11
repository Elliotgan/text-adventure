package edu.grinnell.csc207.item.consumable;

import edu.grinnell.csc207.item.ItemPair;

/**
 * Store Consumable and its quantity
 */
public class ConsumablePair extends ItemPair {
    private Consumable consumable;

    /**
     * Instantiate ConsumablePair
     * 
     * @param counts
     * @param consumable
     */
    public ConsumablePair(int counts, Consumable consumable) {
        this.consumable = consumable;
        this.counts = counts;
    }

    /**
     * Get message of quantity and consumable
     */
    @Override
    public String toString() {
        String ret = "\nQuantity: " + counts + "\n" + consumable.toString();
        return ret;
    }

    /**
     * Get the consumable stored in ConsumablePair
     * 
     * @return consumable
     */
    public Consumable getConsumable() {
        return this.consumable;
    }

}
