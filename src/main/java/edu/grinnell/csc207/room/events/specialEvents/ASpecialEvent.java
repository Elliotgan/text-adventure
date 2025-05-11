package edu.grinnell.csc207.room.events.specialEvents;

import java.util.List;
import java.util.Scanner;

import edu.grinnell.csc207.RoundCounter;
import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.item.ItemGenerator;
import edu.grinnell.csc207.player.Player;

/**
 * one special event
 */
public abstract class ASpecialEvent {
    protected List<String> eventInstructions;

    /**
     * get instructions that does not exist in basicops
     * @param waitCounter
     * @return inistructions
     */
    public abstract List<String> getSpecialInstructions(int waitCounter);

    /**
     * get description of current room
     * @param waitCounter
     * @return description
     */
    public abstract String getDescription(int waitCounter);

    /**
     * trigger the event
     * @param player
     * @param item
     * @param scanner
     * @param itemGenerator
     * @param roundCounter
     * @param instruction
     */
    public abstract void trigger(Player player, Item item, Scanner scanner,
            ItemGenerator itemGenerator, RoundCounter roundCounter, String instruction);

}
