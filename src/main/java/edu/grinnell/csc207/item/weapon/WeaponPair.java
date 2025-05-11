package edu.grinnell.csc207.item.weapon;

import edu.grinnell.csc207.item.ItemPair;

/**
 * Stores Weapon and its quantity
 */
public class WeaponPair extends ItemPair {
    private Weapon weapon;

    /**
     * Instantiate WeaponPair
     * 
     * @param counts
     * @param weapon
     */
    public WeaponPair(int counts, Weapon weapon) {
        this.counts = counts;
        this.weapon = weapon;
    }

    /**
     * Give the quantity and weapon message
     */
    @Override
    public String toString() {
        String ret = "\nQuantity: " + counts + "\n" + weapon.toString();
        return ret;
    }

    /**
     * Get the weapon stored in current WeaponPair
     * 
     * @return weapon
     */
    public Weapon getWeapon() {
        return this.weapon;
    }
}
