package edu.grinnell.csc207.room;

import java.util.ArrayList;
import java.util.Scanner;

import edu.grinnell.csc207.RoundCounter;
import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.item.ItemGenerator;
import edu.grinnell.csc207.player.Player;
import edu.grinnell.csc207.room.events.ShopEvent;

/**
 * ShopRoom
 */
public class ShopRoom extends Room {
    private boolean initializedFlag;

    /**
     * Instantiate ShopRoom
     * 
     * @param scanner
     */
    public ShopRoom(Scanner scanner) {
        this.roomName = "Shop";
        this.scanner = scanner;
        this.hasEast = this.hasNorth = this.hasSouth = this.hasWest = false;
    }

    /**
     * wait round does nothing
     */
    public void waitRound() {
        return;
    }

    /**
     * shop room is not empty
     * 
     * @return false
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * get description of shop room
     * 
     * @return description
     */
    public String getDescription() {
        return "There is a shop open in the room, and the owner is smiling at you";
    }

    /**
     * Handle with instructions other than those in Basicops
     * 
     * @param instruction
     */
    public void specialInstructions(String instruction) {
        if (instruction.equals("Talk to Shop Owner")) {
            event.trigger(player, item, scanner, itemGenerator, roundCounter, null);
        }
    }

    /**
     * Refresh the room
     * 
     * @param player
     * @param item
     * @param itemGenerator
     * @param roundCounter
     */
    public void refreshRoom(Player player, Item item, ItemGenerator itemGenerator,
            RoundCounter roundCounter) {
        if (!initializedFlag) {
            this.player = player;
            this.item = item;
            this.itemGenerator = itemGenerator;
            this.roundCounter = roundCounter;
            event = new ShopEvent(itemGenerator);
            initializedFlag = true;
        }
        this.instructions = new ArrayList<>();
        if (this.hasNorth == true) {
            instructions.add("Go North");
        }
        if (this.hasSouth == true) {
            instructions.add("Go South");
        }
        if (this.hasWest == true) {
            instructions.add("Go West");
        }
        if (this.hasEast == true) {
            instructions.add("Go East");
        }
        instructions.add("View Status");
        instructions.add("View Items");
        instructions.add("Print Map");
        instructions.add("Talk to Shop Owner");
        instructions.add("Wait");
    }

}
