package edu.grinnell.csc207.item.armor;

import edu.grinnell.csc207.item.ItemPair;

/**
 * Stores armor and its quantity
 */
public class ArmorPair extends ItemPair {
    private Armor armor;

    /**
     * Instantiate ArmorPair
     * 
     * @param counts
     * @param armor
     */
    public ArmorPair(int counts, Armor armor) {
        this.armor = armor;
        this.counts = counts;
    }

    /**
     * Give the quantity and armor message
     */
    @Override
    public String toString() {
        String ret = "\nQuantity: " + counts + "\n" + armor.toString();
        return ret;
    }

    /**
     * Get the armor stored in ArmorPair
     * 
     * @return armor stored in ArmorPair
     */
    public Armor getArmor() {
        return this.armor;
    }
}
