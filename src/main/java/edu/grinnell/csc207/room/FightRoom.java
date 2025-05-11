package edu.grinnell.csc207.room;

import java.util.ArrayList;
import java.util.Scanner;

import edu.grinnell.csc207.RoundCounter;
import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.item.ItemGenerator;
import edu.grinnell.csc207.player.Player;
import edu.grinnell.csc207.room.events.FightEvent;

/**
 * FightRoom
 */
public class FightRoom extends Room {
    protected boolean eventTriggered;
    protected boolean initializedFlag;

    /**
     * Instantiate FightRoom
     * 
     * @param scanner
     */
    public FightRoom(Scanner scanner) {
        this.roomName = "Fight";
        this.scanner = scanner;
        eventTriggered = false;
        this.hasEast = this.hasNorth = this.hasSouth = this.hasWest = false;
    }

    /**
     * wait round does nothing
     */
    public void waitRound() {
        return;
    }

    /**
     * Fight room is not empty
     * 
     * @return false
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * get description of this fight room
     * 
     * @return description
     */
    public String getDescription() {
        if (eventTriggered) {
            return "The monster in this room has been defeated, now it is an empty room";
        } else {
            return "There is a " + ((FightEvent) event).getMonsterName() + " in the room";
        }
    }

    /**
     * Handle with instructions other than those in Basicops
     * 
     * @param instruction
     */
    public void specialInstructions(String instruction) {
        if (instruction.equals("Fight " + ((FightEvent) event).getMonsterName())) {
            event.trigger(player, item, scanner, itemGenerator, roundCounter, null);
            eventTriggered = true;
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
            this.roundCounter = roundCounter;
            this.itemGenerator = itemGenerator;
            event = new FightEvent(player.getLevel());
            initializedFlag = true;
        }
        this.instructions = new ArrayList<>();
        if (eventTriggered) {
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
            instructions.add("Wait");
        } else {
            instructions.add("Fight " + ((FightEvent) event).getMonsterName());
            instructions.add("Return to previous room");
        }
    }
}
