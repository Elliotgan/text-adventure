package edu.grinnell.csc207.item.weapon;

import edu.grinnell.csc207.item.Dropable;

/**
 * Weapon
 */
public class Weapon extends Dropable {
    private int attack;
    private String description;

    /**
     * Weapon is not nothing, return false;
     * @return false
     */
    public boolean isNothing() {
        return false;
    }

    /**
     * Type of Weapon is Weapon
     * @return weapon
     */
    public String getType() {
        return "Weapon";
    }

    /**
     * Instantiate Weapon
     * 
     * @param name
     * @param attack
     * @param price
     * @param description
     */
    public Weapon(String name, int attack, int price, String description) {
        this.name = name;
        this.attack = attack;
        this.price = price;
        this.description = description;
    }

    /**
     * Get the attack value of weapon
     * 
     * @return the attack value of weapon
     */
    public int getAttack() {
        return this.attack;
    }

    /**
     * Get the description of weapon
     * 
     * @return the description of weapon
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get message of weapon
     */
    @Override
    public String toString() {
        return (this.name + "\n"
                + "Attack: " + this.attack + "\n"
                + "Description: " + this.description);
    }

    /**
     * Get message of weapon, with price and without description
     * 
     * @return message of weapon
     */
    public String shopString() {
        return (this.name + "\n"
                + "Attack: " + this.attack + "\n"
                + "Price: " + this.price);
    }

    /**
     * Test if two weapons are equal by comparing their name
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Weapon)) {
            return false;
        }
        return (((Weapon) obj).getName().equals(this.name));
    }
}
