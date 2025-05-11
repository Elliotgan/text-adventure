package edu.grinnell.csc207.room.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import edu.grinnell.csc207.Parser;
import edu.grinnell.csc207.RoundCounter;
import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.item.ItemGenerator;
import edu.grinnell.csc207.item.SelectItem;
import edu.grinnell.csc207.item.armor.Armor;
import edu.grinnell.csc207.item.consumable.Consumable;
import edu.grinnell.csc207.item.consumable.ConsumablePair;
import edu.grinnell.csc207.item.weapon.Weapon;
import edu.grinnell.csc207.player.Player;

/**
 * ShopEvent
 */
public class ShopEvent extends Events {

    private List<Armor> buyArmors;
    private List<Weapon> buyWeapons;
    private List<Consumable> buyConsumables;

    /**
     * Instantiate ShopEvent
     * 
     * @param itemGenerator
     */
    public ShopEvent(ItemGenerator itemGenerator) {
        this.instructions = new ArrayList<>();
        instructions.add("Sell Items");
        instructions.add("Buy Items");
        instructions.add("Quit");
        Random rand = new Random();
        this.buyArmors = new ArrayList<>();
        this.buyWeapons = new ArrayList<>();
        this.buyConsumables = new ArrayList<>();
        for (int iter = 0; iter < itemGenerator.getWeapons().size(); iter++) {
            int randnum = rand.nextInt(2);
            if (randnum == 0) {
                this.buyWeapons.add(itemGenerator.getWeapons().get(iter));
            }
        }
        for (int iter = 0; iter < itemGenerator.getArmors().size(); iter++) {
            int randnum = rand.nextInt(2);
            if (randnum == 0) {
                this.buyArmors.add(itemGenerator.getArmors().get(iter));
            }
        }
        for (int iter = 0; iter < itemGenerator.getConsumables().size(); iter++) {
            this.buyConsumables.add(itemGenerator.getConsumables().get(iter));
        }
    }

    /**
     * trigger this event
     * 
     * @param player
     * @param item
     * @param scanner
     * @param itemGenerator
     * @param roundCounter
     * @param instruction
     * 
     */
    public void trigger(Player player, Item item, Scanner scanner, ItemGenerator itemGenerator,
            RoundCounter roundCounter, String instruction) {
        System.out.println("The shop owner greets you and asks if "
                + "you want to buy item or sell item");
        SelectItem selectItem = new SelectItem(scanner);
        Parser action = new Parser(scanner);
        List<String> selectInstructions = new ArrayList<>();
        selectInstructions.add("Weapon");
        selectInstructions.add("Armor");
        selectInstructions.add("Consumable");
        selectInstructions.add("Quit");
        String curAction = "";
        while (!curAction.equals("Quit")) {
            curAction = action.act(instructions);
            switch (curAction) {
                case "Sell Items":
                    String curSellInstruct = "";
                    while (!curSellInstruct.equals("Quit")) {
                        System.out.println("What you want to sell?");
                        curSellInstruct = action.act(selectInstructions);
                        switch (curSellInstruct) {
                            case "Weapon":
                                System.out.println("\nYour current weapon list: ");
                                for (int iter = 0; iter < item.getWeapons().size(); iter++) {
                                    System.out.println("\n"
                                            + item.getWeapons().get(iter).getWeapon().shopString());
                                }
                                System.out.println("\nYou have " + player.getCoins() + " coins");
                                System.out.println("\nWhich weapon you want to sell?");
                                String weaponSelected = "";
                                while (!weaponSelected.equals("Quit")) {
                                    weaponSelected = selectItem.selectWeaponPair(item.getWeapons());
                                    if (!weaponSelected.equals("Quit")) {
                                        Weapon removedWeapon = item.getWeapon(weaponSelected);
                                        player.changeCoins(removedWeapon.getPrice());
                                        item.removeWeapon(removedWeapon);
                                        System.out.println("You sold " + removedWeapon.getName());
                                        System.out.println("You now have "
                                                + player.getCoins() + " coins");
                                    }
                                }
                                break;
                            case "Armor":
                                System.out.println("\nYour current armor list: ");
                                for (int iter = 0; iter < item.getArmors().size(); iter++) {
                                    System.out.println("\n"
                                            + item.getArmors().get(iter).getArmor().shopString());
                                }
                                System.out.println("\nYou have " + player.getCoins() + " coins");
                                System.out.println("\nWhich armor you want to sell?");
                                String armorSelected = "";
                                while (!armorSelected.equals("Quit")) {
                                    armorSelected = selectItem.selectArmorPair(item.getArmors());
                                    if (!armorSelected.equals("Quit")) {
                                        Armor removedArmor = item.getArmor(armorSelected);
                                        player.changeCoins(removedArmor.getPrice());
                                        item.removeArmor(removedArmor);
                                        System.out.println("You sold " + removedArmor.getName());
                                        System.out.println("You now have "
                                                + player.getCoins() + " coins");
                                    }
                                }
                                break;
                            case "Consumable":
                                System.out.println("\nYour current consumable list: ");
                                for (int iter = 0; iter < item.getConsumables().size(); iter++) {
                                    ConsumablePair curConsumPair = item.getConsumables().get(iter);
                                    Consumable curConsum = curConsumPair.getConsumable();
                                    System.out.println("\n"
                                            + curConsum.shopString());
                                }
                                System.out.println("\nYou have " + player.getCoins() + " coins");
                                System.out.println("\nWhich consumable you want to sell?");
                                String consumS = "";
                                while (!consumS.equals("Quit")) {
                                    List<ConsumablePair> consumPs = item.getConsumables();
                                    consumS = selectItem.selectConsumablePair(consumPs);
                                    if (!consumS.equals("Quit")) {
                                        Consumable removedConsumable = item.getConsumable(consumS);
                                        player.changeCoins(removedConsumable.getPrice());
                                        item.removeConsumable(removedConsumable);
                                        System.out.println("You sold "
                                                + removedConsumable.getName());
                                        System.out.println("You now have "
                                                + player.getCoins() + " coins");
                                    }
                                }
                        }
                    }
                    break;
                case "Buy Items":
                    String curBuyInstruct = "";
                    while (!curBuyInstruct.equals("Quit")) {
                        System.out.println("What you want to buy?");
                        curBuyInstruct = action.act(selectInstructions);
                        switch (curBuyInstruct) {
                            case "Weapon":
                                System.out.println("\nWeapon list: ");
                                for (int iter = 0; iter < buyWeapons.size(); iter++) {
                                    System.out.println("\n" + buyWeapons.get(iter).shopString());
                                }
                                System.out.println("\nYou have " + player.getCoins() + " coins");
                                System.out.println("\nWhich weapon you want to buy?");
                                String weaponSelected = "";
                                while (!weaponSelected.equals("Quit")) {
                                    weaponSelected = selectItem.selectWeapon(buyWeapons);
                                    if (!weaponSelected.equals("Quit")) {
                                        Weapon buyWeapon = itemGenerator.getWeapon(weaponSelected);
                                        if (player.getCoins() >= buyWeapon.getPrice()) {
                                            player.changeCoins(-1 * buyWeapon.getPrice());
                                            item.addWeapon(buyWeapon);
                                            System.out.println("You bought " + buyWeapon.getName());
                                            System.out.println("You now have "
                                                    + player.getCoins() + " coins");
                                        } else {
                                            System.out.println("You don't have enough coins!");
                                        }
                                    }
                                }
                                break;
                            case "Armor":
                                System.out.println("\nArmor list: ");
                                for (int iter = 0; iter < buyArmors.size(); iter++) {
                                    System.out.println("\n" + buyArmors.get(iter).shopString());
                                }
                                System.out.println("\nYou have " + player.getCoins() + " coins");
                                System.out.println("\nWhich armor you want to buy?");
                                String armorSelected = "";
                                while (!armorSelected.equals("Quit")) {
                                    armorSelected = selectItem.selectArmor(buyArmors);
                                    if (!armorSelected.equals("Quit")) {
                                        Armor buyArmor = itemGenerator.getArmor(armorSelected);
                                        if (player.getCoins() >= buyArmor.getPrice()) {
                                            player.changeCoins(-1 * buyArmor.getPrice());
                                            item.addArmor(buyArmor);
                                            System.out.println("You bought " + buyArmor.getName());
                                            System.out.println("You now have "
                                                    + player.getCoins() + " coins");
                                        } else {
                                            System.out.println("You don't have enough coins!");
                                        }
                                    }
                                }
                                break;
                            case "Consumable":
                                System.out.println("\nConsumable list: ");
                                for (int iter = 0; iter < buyConsumables.size(); iter++) {
                                    System.out.println("\n"
                                            + buyConsumables.get(iter).shopString());
                                }
                                System.out.println("\nYou have " + player.getCoins() + " coins");
                                System.out.println("\nWhich consumable you want to buy?");
                                String consumS = "";
                                while (!consumS.equals("Quit")) {
                                    consumS = selectItem.selectConsumable(buyConsumables);
                                    if (!consumS.equals("Quit")) {
                                        Consumable buyConsum = itemGenerator.getConsumable(consumS);
                                        if (player.getCoins() >= buyConsum.getPrice()) {
                                            player.changeCoins(-1 * buyConsum.getPrice());
                                            item.addConsumable(buyConsum);
                                            System.out.println("You bought "
                                                    + buyConsum.getName());
                                            System.out.println("You now have "
                                                    + player.getCoins() + " coins");
                                        } else {
                                            System.out.println("You don't have enough coins!");
                                        }
                                    }
                                }
                        }
                    }
            }
        }
        return;
    }

}
