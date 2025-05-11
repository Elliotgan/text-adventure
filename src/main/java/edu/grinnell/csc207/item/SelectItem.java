package edu.grinnell.csc207.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.grinnell.csc207.Parser;
import edu.grinnell.csc207.item.armor.Armor;
import edu.grinnell.csc207.item.armor.ArmorPair;
import edu.grinnell.csc207.item.consumable.Consumable;
import edu.grinnell.csc207.item.consumable.ConsumablePair;
import edu.grinnell.csc207.item.weapon.Weapon;
import edu.grinnell.csc207.item.weapon.WeaponPair;

/**
 * Get a list of item, let user to select one of them
 */
public class SelectItem extends Parser {
    /**
     * Instantiate SelectItem
     * 
     * @param scanner
     */
    public SelectItem(Scanner scanner) {
        super(scanner);
    }

    /**
     * Select one of the consumable in consumablePairs
     * 
     * @param consumablePairs
     * @return consumable
     */
    public String selectConsumablePair(List<ConsumablePair> consumablePairs) {
        List<String> options = new ArrayList<>();

        for (int iter = 0; iter < consumablePairs.size(); iter++) {
            options.add(consumablePairs.get(iter).getConsumable().getName());
        }
        options.add("Quit");

        String ret = act(options);
        return ret;

    }

    /**
     * Select one of the weapon in weaponPairs
     * 
     * @param weaponPairs
     * @return weapon
     */
    public String selectWeaponPair(List<WeaponPair> weaponPairs) {
        List<String> options = new ArrayList<>();

        for (int iter = 0; iter < weaponPairs.size(); iter++) {
            options.add(weaponPairs.get(iter).getWeapon().getName());
        }
        options.add("Quit");

        String ret = act(options);
        return ret;
    }

    /**
     * Select one of the armor in armorPairs
     * 
     * @param armorPairs
     * @return armor
     */
    public String selectArmorPair(List<ArmorPair> armorPairs) {
        List<String> options = new ArrayList<>();

        for (int iter = 0; iter < armorPairs.size(); iter++) {
            options.add(armorPairs.get(iter).getArmor().getName());
        }
        options.add("Quit");

        String ret = act(options);
        return ret;
    }

    /**
     * Select one of the consumable in consumables
     * 
     * @param consumablePairs
     * @return consumable
     */
    public String selectConsumable(List<Consumable> consumablePairs) {
        List<String> options = new ArrayList<>();

        for (int iter = 0; iter < consumablePairs.size(); iter++) {
            options.add(consumablePairs.get(iter).getName());
        }
        options.add("Quit");

        String ret = act(options);
        return ret;

    }

    /**
     * Select one of the weapon in weapons
     * 
     * @param weaponPairs
     * @return weapon
     */
    public String selectWeapon(List<Weapon> weaponPairs) {
        List<String> options = new ArrayList<>();

        for (int iter = 0; iter < weaponPairs.size(); iter++) {
            options.add(weaponPairs.get(iter).getName());
        }
        options.add("Quit");

        String ret = act(options);
        return ret;
    }

    /**
     * Select one of the armor in armors
     * 
     * @param armorPairs
     * @return armor
     */
    public String selectArmor(List<Armor> armorPairs) {
        List<String> options = new ArrayList<>();

        for (int iter = 0; iter < armorPairs.size(); iter++) {
            options.add(armorPairs.get(iter).getName());
        }
        options.add("Quit");

        String ret = act(options);
        return ret;
    }
}
