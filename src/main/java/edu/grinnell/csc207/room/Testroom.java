package edu.grinnell.csc207.room;

import java.util.ArrayList;
import java.util.Scanner;

import edu.grinnell.csc207.RoundCounter;
import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.item.ItemGenerator;
import edu.grinnell.csc207.player.Player;

/**
 * no event, simply for test and generate
 */
public class Testroom extends Room {

    /**
     * Instantiate Testroom
     * 
     * @param scanner
     */
    public Testroom(Scanner scanner) {
        this.roomName = "Testroom";
        this.scanner = scanner;
        this.hasEast = this.hasNorth = this.hasSouth = this.hasWest = this.present = false;
    }

    /**
     * no special Instruction
     * 
     * @param instruction
     */
    public void specialInstructions(String instruction) {
        return;
    }

    /**
     * test room is not empty
     * 
     * @return false
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * waitround does nothing
     */
    public void waitRound() {
        return;
    }

    /**
     * get description of test room
     * 
     * @return description
     */
    public String getDescription() {
        return "It is a test room. If you are in this room, "
                + "the program is in test mode, or we are in trouble";
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
