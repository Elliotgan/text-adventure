package edu.grinnell.csc207.room.events;

import java.util.List;
import java.util.Scanner;

import edu.grinnell.csc207.RoundCounter;
import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.item.ItemGenerator;
import edu.grinnell.csc207.player.Player;

/**
 * Events
 */
public abstract class Events {
    protected List<String> triggerInstructions;
    protected List<String> instructions;

    /**
     * trigger event
     * 
     * @param player
     * @param item
     * @param scanner
     * @param itemGenerator
     * @param roundCounter
     * @param instruction
     * 
     */
    public abstract void trigger(Player player, Item item, Scanner scanner,
            ItemGenerator itemGenerator, RoundCounter roundCounter, String instruction);

}
