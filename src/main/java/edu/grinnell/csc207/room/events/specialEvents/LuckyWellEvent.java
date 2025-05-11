package edu.grinnell.csc207.room.events.specialEvents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import edu.grinnell.csc207.Parser;
import edu.grinnell.csc207.RoundCounter;
import edu.grinnell.csc207.item.Dropable;
import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.item.ItemGenerator;
import edu.grinnell.csc207.player.Player;

/**
 * LuckyWellEvent
 */
public class LuckyWellEvent extends ASpecialEvent {

    private boolean triggered;
    private boolean coinTossed;
    private boolean itemAppear;

    /**
     * Instantiate LuckyWellEvent
     */
    public LuckyWellEvent() {
        triggered = false;
        coinTossed = false;
        itemAppear = false;
        eventInstructions = new ArrayList<>();
        eventInstructions.add("Toss 20 coins");
        eventInstructions.add("Walk away");
    }

    /**
     * Get special instructions of this event
     * 
     * @param waitCounter
     * @return instructions
     */
    public List<String> getSpecialInstructions(int waitCounter) {
        List<String> ret = new ArrayList<>();
        if (!triggered) {
            ret.add("Look at well");
        }
        return ret;
    }

    /**
     * Get description of this event
     * 
     * @param waitCounter
     * @return description
     */
    public String getDescription(int waitCounter) {
        if (!triggered) {
            if (!coinTossed) {
                return "There is a well in the middle of the room";
            }
            if (waitCounter < 4) {
                return "The well is bubbling";
            }
            itemAppear = true;
            return "It seems that a item has surfaced in the well";
        }
        return "The lucky well has already played its role, and it probably won't work again";
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
        roundCounter.nextRound();
        System.out.println("\n" + roundCounter.toString());
        Random rand = new Random();
        Dropable droppedItem = null;
        String droppedItemType = "";
        if (rand.nextInt(2) == 0) {
            droppedItem = itemGenerator.getWeapon("Magical Wooden Sword");
            droppedItemType = "Weapon";
        } else {
            droppedItem = itemGenerator.getArmor("Standard Plate Armor");
            droppedItemType = "Armor";
        }
        if (!coinTossed) {
            System.out.println("It looks like this is a lucky well. "
                    + "Want to toss a few coins to give it a try?");
        } else if (!itemAppear) {
            System.out.println("The well is bubbling");
        } else {
            System.out.println("There is a item on the surface of the well");
        }
        Parser action = new Parser(scanner);
        eventInstructions = new ArrayList<>();
        if (!itemAppear) {
            if (!coinTossed) {
                eventInstructions.add("Toss 20 coins");
            }
        } else {
            eventInstructions.add("Pick up item");
        }
        eventInstructions.add("Walk away");
        String eventInstruction = action.act(eventInstructions);
        switch (eventInstruction) {
            case "Toss 20 coins":
                if (player.getCoins() >= 20) {
                    System.out.println("You tossed 20 coins into the well");
                    player.changeCoins(-20);
                    System.out.println("You currently have " + player.getCoins() + " coins");
                    coinTossed = true;
                } else {
                    System.out.println("You don't have enough coins!");
                }
                break;
            case "Pick up item":
                System.out.println("You get " + droppedItem.getName());
                item.addDropable(droppedItemType, droppedItem);
                triggered = true;
                break;
            case "Walk away":
                System.out.println("This thing looks unreliable, just forget it");
        }
    }
}
