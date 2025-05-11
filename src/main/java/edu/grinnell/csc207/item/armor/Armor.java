package edu.grinnell.csc207.item.armor;

import edu.grinnell.csc207.item.Dropable;

/**
 * Armor class
 */
public class Armor extends Dropable {
    private int defense;
    private String description;

    /**
     * Armor is not nothing, so it should return false
     * @return false
     */
    public boolean isNothing() {
        return false;
    }

    /**
     * The type of armor is armor
     * @return armor
     */
    public String getType() {
        return "Armor";
    }

    /**
     * Instantiate Armor
     * 
     * @param name
     * @param defense
     * @param price
     * @param description
     */
    public Armor(String name, int defense, int price, String description) {
        this.name = name;
        this.defense = defense;
        this.price = price;
        this.description = description;
    }

    /**
     * Get the defense of armor
     * 
     * @return defense of armor
     */
    public int getDefense() {
        return this.defense;
    }

    /**
     * Get the description of armor
     * 
     * @return description of armor
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Give armor message
     */
    @Override
    public String toString() {
        return (this.name + "\n"
                + "Defense: " + this.defense + "\n"
                + "Description: " + this.description);
    }

    /**
     * Give armor message without the description but with the price
     * 
     * @return armor message without the description but with the price
     */
    public String shopString() {
        return (this.name + "\n"
                + "Defense: " + this.defense + "\n"
                + "Price: " + this.price);
    }

    /**
     * Test if two armors are equal based on their names
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Armor)) {
            return false;
        }
        return (((Armor) obj).getName().toLowerCase().equals(this.name.toLowerCase()));
    }
}
