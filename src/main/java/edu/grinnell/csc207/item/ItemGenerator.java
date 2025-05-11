package edu.grinnell.csc207.item;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.grinnell.csc207.item.armor.Armor;
import edu.grinnell.csc207.item.consumable.Consumable;
import edu.grinnell.csc207.item.consumable.Grease;
import edu.grinnell.csc207.item.consumable.HealPotion;
import edu.grinnell.csc207.item.consumable.SpiderWeb;
import edu.grinnell.csc207.item.weapon.Weapon;

/**
 * This class generate items
 */
public class ItemGenerator {
    private List<Armor> armors;
    private List<Consumable> consumables;
    private List<Weapon> weapons;
    private List<DropList> dropLists;

    /**
     * Get all armors
     * 
     * @return armors
     */
    public List<Armor> getArmors() {
        return this.armors;
    }

    /**
     * Get all weapons
     * 
     * @return weapons
     */
    public List<Weapon> getWeapons() {
        return this.weapons;
    }

    /**
     * Get all consumables
     * 
     * @return consumables
     */
    public List<Consumable> getConsumables() {
        return this.consumables;
    }

    /**
     * Get a armor based on armor name
     * 
     * @param armorName
     * @return armor
     */
    public Armor getArmor(String armorName) {
        for (int iter = 0; iter < armors.size(); iter++) {
            if (armorName.toLowerCase().equals(armors.get(iter).getName().toLowerCase())) {
                return armors.get(iter);
            }
        }
        return null;
    }

    /**
     * Get a weapon based on name
     * 
     * @param weaponName
     * @return weapon
     */
    public Weapon getWeapon(String weaponName) {
        for (int iter = 0; iter < weapons.size(); iter++) {
            if (weaponName.toLowerCase().equals(weapons.get(iter).getName().toLowerCase())) {
                return weapons.get(iter);
            }
        }
        return null;
    }

    /**
     * Get a consumable based on name
     * 
     * @param consumableName
     * @return consumable
     */
    public Consumable getConsumable(String consumableName) {
        for (int iter = 0; iter < consumables.size(); iter++) {
            String lsConsumableName = consumables.get(iter).getName().toLowerCase();
            if (consumableName.toLowerCase().equals(lsConsumableName)) {
                return consumables.get(iter);
            }
        }
        return null;
    }

    /**
     * Instantiate ItemGenerator
     * 
     * @param filepath
     * @throws FileNotFoundException
     */
    public ItemGenerator(String filepath) throws FileNotFoundException {
        File armorFile = new File(filepath + "/armors.txt");
        File weaponFile = new File(filepath + "/weapons.txt");
        File droplistFile = new File(filepath + "/droplist.txt");
        Scanner armorScanner = null;
        Scanner weaponScanner = null;
        Scanner droplistScanner = null;
        this.consumables = new ArrayList<>();
        this.armors = new ArrayList<>();
        this.weapons = new ArrayList<>();
        this.dropLists = new ArrayList<>();
        try {
            armorScanner = new Scanner(armorFile);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Invalid armor file!");
        }
        try {
            weaponScanner = new Scanner(weaponFile);
        } catch (FileNotFoundException e) {
            armorScanner.close();
            throw new FileNotFoundException("Invalid weapon file!");
        }
        try {
            droplistScanner = new Scanner(droplistFile);
        } catch (FileNotFoundException e) {
            armorScanner.close();
            weaponScanner.close();
            throw new FileNotFoundException("Invalid weapon file!");
        }
        consumables.add(new HealPotion());
        consumables.add(new Grease());
        consumables.add(new SpiderWeb());

        while (armorScanner.hasNextLine()) {
            String[] armorInput = new String[4];
            String currentline = armorScanner.nextLine();
            for (int iter = 0; iter < 4; iter++) {
                armorInput[iter] = "";
            }
            int currentindex = 0;
            for (int iter = 0; iter < currentline.length(); iter++) {
                char currentchar = currentline.charAt(iter);
                if (currentchar == '\t') {
                    currentindex += 1;
                } else {
                    armorInput[currentindex] += Character.toString(currentchar);
                }
            }
            armors.add(new Armor(armorInput[0], Integer.valueOf(armorInput[1]),
                    Integer.valueOf(armorInput[2]), armorInput[3]));
        }

        while (weaponScanner.hasNextLine()) {
            String[] weaponInput = new String[4];
            String currentline = weaponScanner.nextLine();
            for (int iter = 0; iter < 4; iter++) {
                weaponInput[iter] = "";
            }
            int currentindex = 0;
            for (int iter = 0; iter < currentline.length(); iter++) {
                char currentchar = currentline.charAt(iter);
                if (currentchar == '\t') {
                    currentindex += 1;
                } else {
                    weaponInput[currentindex] += Character.toString(currentchar);
                }
            }
            weapons.add(new Weapon(weaponInput[0], Integer.valueOf(weaponInput[1]),
                    Integer.valueOf(weaponInput[2]), weaponInput[3]));
        }

        while (droplistScanner.hasNextLine()) {
            List<DropPair> curDropPairList = new ArrayList<>();
            List<String> drops = new ArrayList<>();
            String currentline = droplistScanner.nextLine();
            String currentString = "";
            for (int iter = 0; iter < currentline.length(); iter++) {
                char currentchar = currentline.charAt(iter);
                /*
                 * if (currentString.equals("Nothing")) {
                 * System.out.println("nothing is going to be added");
                 * DropPair dropNothing = new DropPair(Integer.valueOf(drops.get(drops.size() -
                 * 1)), new Nothing());
                 * curDropPairList.add(dropNothing);
                 * break;
                 * }
                 */
                if (currentchar == '\t') {
                    drops.add(currentString);
                    currentString = "";
                } else {
                    currentString += Character.toString(currentchar);
                }
            }

            int nothingWeight = Integer.valueOf(drops.get(drops.size() - 1));
            DropPair dropNothing = new DropPair(nothingWeight, new Nothing());
            curDropPairList.add(dropNothing);
            String monsterName = drops.get(0);

            for (int iter = 1; iter < drops.size() - 2; iter += 3) {
                int weight = Integer.valueOf(drops.get(iter));
                String dropClass = drops.get(iter + 1);
                String dropName = drops.get(iter + 2);
                switch (dropClass) {
                    case "Weapon":
                        Weapon dropWeapon = getWeapon(dropName);
                        DropPair dropWeaponPair = new DropPair(weight, dropWeapon);
                        curDropPairList.add(dropWeaponPair);
                        break;

                    case "Armor":
                        Armor dropArmor = getArmor(dropName);
                        DropPair dropArmorPair = new DropPair(weight, dropArmor);
                        curDropPairList.add(dropArmorPair);
                        break;
                    case "Consumable":
                        Consumable dropConsumable = getConsumable(dropName);
                        DropPair dropConsumablePair = new DropPair(weight, dropConsumable);
                        curDropPairList.add(dropConsumablePair);
                }
            }
            dropLists.add(new DropList(monsterName, curDropPairList));
        }
        armorScanner.close();
        weaponScanner.close();
        droplistScanner.close();
    }

    /**
     * drop a item
     * 
     * @param monsterName
     * @return item monster dropped
     */
    public Dropable dropItem(String monsterName) {
        for (int iter = 0; iter < dropLists.size(); iter++) {
            if ((dropLists.get(iter).getName().toLowerCase()).equals(monsterName.toLowerCase())) {
                Dropable ret = dropLists.get(iter).dropItem();
                return ret;
            }
        }
        return null;
    }

}