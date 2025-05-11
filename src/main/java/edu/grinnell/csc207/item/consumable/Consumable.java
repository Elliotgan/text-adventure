package edu.grinnell.csc207.item.consumable;

import edu.grinnell.csc207.item.Dropable;

/**
 * Consumable
 */
public class Consumable extends Dropable {
    private String effect;
    private String description;

    /**
     * consumable is not nothing, return false
     * @return false
     */
    public boolean isNothing() {
        return false;
    }

    /**
     * type of Consumable is Consumable
     * @return Consumable
     */
    public String getType() {
        return "Consumable";
    }

    /**
     * Instantiate Consumable
     * 
     * @param name
     * @param effect
     * @param price
     * @param description
     */
    public Consumable(String name, String effect, int price, String description) {
        this.name = name;
        this.effect = effect;
        this.price = price;
        this.description = description;
    }

    /**
     * Get message of consumable
     */
    @Override
    public String toString() {
        return (this.name + "\n"
                + "Effect: " + this.effect + "\n"
                + "Description: " + this.description);
    }

    /**
     * Get message of consumable, with price and without description
     * 
     * @return message of consumable
     */
    public String shopString() {
        return (this.name + "\n"
                + "Effect: " + this.effect + "\n"
                + "Price: " + this.price);
    }
}
