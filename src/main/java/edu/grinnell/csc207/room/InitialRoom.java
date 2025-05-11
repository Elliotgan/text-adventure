package edu.grinnell.csc207.room;

import java.util.ArrayList;
import java.util.Scanner;

import edu.grinnell.csc207.RoundCounter;
import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.item.ItemGenerator;
import edu.grinnell.csc207.player.Player;

/**
 * InitialRoom
 */
public class InitialRoom extends Room {
    /**
     * Instantiate InitialRoom
     * 
     * @param scanner
     */
    public InitialRoom(Scanner scanner) {
        this.roomName = "Initial";
        this.scanner = scanner;
        this.hasEast = this.hasNorth = this.hasSouth = this.hasWest = this.present = false;
    }

    /**
     * initial room should not have special instruction
     * 
     * @param instruction
     */
    public void specialInstructions(String instruction) {
        return;
    }

    /**
     * initial room is not empty
     * 
     * @return false
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * wait round does nothing
     */
    public void waitRound() {
        return;
    }

    /**
     * get description of initial room
     * 
     * @return description
     */
    public String getDescription() {
        return "This is your initial room, there is nothing in the room";
    }

    /**
     * refresh room
     * 
     * @param player
     * @param item
     * @param itemGenerator
     * @param roundCounter
     */
    public void refreshRoom(Player player, Item item, ItemGenerator itemGenerator,
            RoundCounter roundCounter) {
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
        instructions.add("Wait");
    }

}
