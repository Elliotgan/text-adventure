package edu.grinnell.csc207.item;

import java.util.ArrayList;
import java.util.List;

import edu.grinnell.csc207.item.armor.Armor;
import edu.grinnell.csc207.item.armor.ArmorPair;
import edu.grinnell.csc207.item.consumable.Consumable;
import edu.grinnell.csc207.item.consumable.ConsumablePair;
import edu.grinnell.csc207.item.consumable.Grease;
import edu.grinnell.csc207.item.consumable.HealPotion;
import edu.grinnell.csc207.item.consumable.SpiderWeb;
import edu.grinnell.csc207.item.weapon.Weapon;
import edu.grinnell.csc207.item.weapon.WeaponPair;

/**
 * Item is basically the warehouse
 */
public class Item {

    private List<ArmorPair> armors;
    private List<WeaponPair> weapons;
    private List<ConsumablePair> consumables;
    private Armor currentArmor;
    private Weapon currentWeapon;

    /**
     * Instantiate Item
     */
    public Item() {
        this.armors = new ArrayList<>();
        this.weapons = new ArrayList<>();
        this.consumables = new ArrayList<>();
        this.consumables.add(new ConsumablePair(3, new HealPotion()));
        this.consumables.add(new ConsumablePair(1, new Grease()));
        this.consumables.add(new ConsumablePair(5, new SpiderWeb()));
        this.currentWeapon = new Weapon("Ordinary Sword", 5, 20,
                "\"This iron sword is capable of chopping firewood, cutting vegetables,"
                        + "digging trenches, and even fighting monsters!\" You are curious why"
                        + "fighting monsters seems like an added feature in the advertisement");
        this.currentArmor = new Armor("Ordinary Armor", 5, 20,
                "Thin armor like cardboard, which can withstand a couple"
                        + "of stabs from a dagger in good luck");
    }

    /**
     * Add a weapon to Item
     * 
     * @param newWeapon
     */
    public void addWeapon(Weapon newWeapon) {
        for (int iter = 0; iter < weapons.size(); iter++) {
            if (weapons.get(iter).getWeapon().equals(newWeapon)) {
                weapons.get(iter).changeCounts(1);
                return;
            }
        }
        weapons.add(new WeaponPair(1, newWeapon));
    }

    /**
     * Add a armor to Item
     * 
     * @param newArmor
     */
    public void addArmor(Armor newArmor) {
        for (int iter = 0; iter < armors.size(); iter++) {
            if (armors.get(iter).getArmor().equals(newArmor)) {
                armors.get(iter).changeCounts(1);
                return;
            }
        }
        armors.add(new ArmorPair(1, newArmor));
    }

    /**
     * Add a consumable to Item
     * 
     * @param newConsumable
     */
    public void addConsumable(Consumable newConsumable) {
        for (int iter = 0; iter < consumables.size(); iter++) {
            if (consumables.get(iter).getConsumable().equals(newConsumable)) {
                consumables.get(iter).changeCounts(1);
                return;
            }
        }
        consumables.add(new ConsumablePair(1, newConsumable));
    }

    /**
     * Add a dropable to Item
     * 
     * @param dropClass
     * @param dropable
     */
    public void addDropable(String dropClass, Dropable dropable) {
        switch (dropClass) {
            case "Weapon":
                addWeapon((Weapon) dropable);
                break;
            case "Armor":
                addArmor((Armor) dropable);
                break;
            case "Consumable":
                addConsumable((Consumable) dropable);
        }
    }

    /**
     * get ArmorPairs
     * 
     * @return list of ArmorPairs
     */
    public List<ArmorPair> getArmors() {
        return this.armors;
    }

    /**
     * Get WeaponPairs
     * 
     * @return list of WeaponPairs
     */
    public List<WeaponPair> getWeapons() {
        return this.weapons;
    }

    /**
     * Get ConsumablePairs
     * 
     * @return list of ConsumablePairs
     */
    public List<ConsumablePair> getConsumables() {
        return this.consumables;
    }

    /**
     * Remove a armor from Item
     * 
     * @param removedArmor
     * @throws IllegalArgumentException
     */
    public void removeArmor(Armor removedArmor) throws IllegalArgumentException {
        for (int iter = 0; iter < armors.size(); iter++) {
            if (removedArmor.equals(armors.get(iter).getArmor())) {
                if (armors.get(iter).getCounts() <= 1) {
                    armors.remove(iter);
                    return;
                }
                armors.get(iter).changeCounts(-1);
                return;
            }
        }
        throw new IllegalArgumentException("The armor intended "
                + "to remove does not exist in the item list");
    }

    /**
     * Remove a weapon from Item
     * 
     * @param removedWeapon
     * @throws IllegalArgumentException
     */
    public void removeWeapon(Weapon removedWeapon) throws IllegalArgumentException {
        for (int iter = 0; iter < weapons.size(); iter++) {
            if (removedWeapon.equals(weapons.get(iter).getWeapon())) {
                if (weapons.get(iter).getCounts() <= 1) {
                    weapons.remove(iter);
                    return;
                }
                weapons.get(iter).changeCounts(-1);
                return;
            }
        }
        throw new IllegalArgumentException("The wepon intended to "
                + "remove does not exist in the item list");
    }

    /**
     * Remove a consumable from Item
     * 
     * @param removedConsumable
     * @throws IllegalArgumentException
     */
    public void removeConsumable(Consumable removedConsumable) throws IllegalArgumentException {
        for (int iter = 0; iter < consumables.size(); iter++) {
            if (removedConsumable.equals(consumables.get(iter).getConsumable())) {
                if (consumables.get(iter).getCounts() <= 1) {
                    consumables.remove(iter);
                    return;
                }
                consumables.get(iter).changeCounts(-1);
                return;
            }
        }
        throw new IllegalArgumentException("The Consumable intended "
                + "to remove does not exist in the item list");
    }

    /**
     * Get equipped armor
     * 
     * @return equipped armor
     */
    public Armor getCurrentArmor() {
        return this.currentArmor;
    }

    /**
     * Get equipped weapon
     * 
     * @return equipped weapon
     */
    public Weapon getCurrentWeapon() {
        return this.currentWeapon;
    }

    /**
     * Get a armor in Item based on name of armor
     * 
     * @param armorName
     * @return armor
     */
    public Armor getArmor(String armorName) {
        for (int iter = 0; iter < armors.size(); iter++) {
            String lsArmorName = armors.get(iter).getArmor().getName().toLowerCase();
            if (armorName.toLowerCase().equals(lsArmorName)) {
                return armors.get(iter).getArmor();
            }
        }
        System.out.println("getArmor in Item is returning null");
        return null;
    }

    /**
     * Get a weapon in Item based on name of weapon
     * 
     * @param weaponName
     * @return weapon
     */
    public Weapon getWeapon(String weaponName) {
        for (int iter = 0; iter < weapons.size(); iter++) {
            String lsWeaponName = weapons.get(iter).getWeapon().getName().toLowerCase();
            if (weaponName.toLowerCase().equals(lsWeaponName)) {
                return weapons.get(iter).getWeapon();
            }
        }
        System.out.println("getWeapon in Item is returning null");
        return null;
    }

    /**
     * Get a consumable in Item based on name of consumable
     * 
     * @param consumableName
     * @return consumable
     */
    public Consumable getConsumable(String consumableName) {
        for (int iter = 0; iter < consumables.size(); iter++) {
            String lsConsumableName = consumables.get(iter).getConsumable().getName().toLowerCase();
            if (consumableName.toLowerCase().equals(lsConsumableName)) {
                return consumables.get(iter).getConsumable();
            }
        }
        return null;
    }

    /**
     * Switch a weapon in weapon list with current equipped weapon
     * 
     * @param weaponName
     */
    public void switchWeapon(String weaponName) {
        Weapon current = currentWeapon;
        Weapon newWeapon = getWeapon(weaponName);
        System.out.println("You want to equip " + newWeapon.getName());
        removeWeapon(newWeapon);
        addWeapon(current);
        currentWeapon = newWeapon;
        System.out.println("current weapon: " + currentWeapon.getName());
    }

    /**
     * Switch a armor in armor list with current equipped armor
     * 
     * @param armorName
     */
    public void switchArmor(String armorName) {
        Armor current = currentArmor;
        Armor newArmor = getArmor(armorName);
        System.out.println("You want to equip " + newArmor.getName());
        removeArmor(newArmor);
        addArmor(current);
        currentArmor = newArmor;
        System.out.println("current armor: " + currentArmor.getName());
    }
}
