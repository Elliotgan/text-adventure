package edu.grinnell.csc207.room;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.grinnell.csc207.RoundCounter;
import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.item.ItemGenerator;
import edu.grinnell.csc207.player.Player;
import edu.grinnell.csc207.room.events.specialEvents.SpecialEvent;

/**
 * SpecialEventRoom
 */
public class SpecialEventRoom extends Room {
    private boolean initializedFlag;

    /**
     * Instantiate SpecialEventRoom
     * 
     * @param scanner
     */
    public SpecialEventRoom(Scanner scanner) {
        this.roomName = "Special";
        this.scanner = scanner;
        this.hasEast = this.hasNorth = this.hasSouth = this.hasWest = false;
    }

    /**
     * wait for one round
     */
    public void waitRound() {
        ((SpecialEvent) event).waitRound();
        return;
    }

    /**
     * special event room is not empty
     * 
     * @return true
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * get description of current special event room
     * 
     * @return description
     */
    public String getDescription() {
        return (((SpecialEvent) event).getDescription());
    }

    /**
     * handle with instructions other than those in Basicops
     * 
     * @param instruction
     */
    public void specialInstructions(String instruction) {
        event.trigger(player, item, scanner, itemGenerator, roundCounter, instruction);
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
        if (!initializedFlag) {
            this.player = player;
            this.item = item;
            this.itemGenerator = itemGenerator;
            this.roundCounter = roundCounter;
            this.event = new SpecialEvent();
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
        List<String> additionalInstructions = ((SpecialEvent) event).getValidInstructions();
        for (int iter = 0; iter < additionalInstructions.size(); iter++) {
            instructions.add(additionalInstructions.get(iter));
        }
        instructions.add("Wait");

    }
}
