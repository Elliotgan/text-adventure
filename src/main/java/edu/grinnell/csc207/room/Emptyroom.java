package edu.grinnell.csc207.room;

import edu.grinnell.csc207.RoundCounter;
import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.item.ItemGenerator;
import edu.grinnell.csc207.player.Player;

/**
 * EmptyRoom
 */
public class Emptyroom extends Room {

    /**
     * empty room should not handle with any instruction
     * @param instruction
     */
    public void specialInstructions(String instruction) {
        return;
    }

    /**
     * empty room is empty
     * 
     * @return true
     */
    public boolean isEmpty() {
        return true;
    }

    /**
     * wait round does nothing
     */
    public void waitRound() {
        return;
    }

    /**
     * there is no need to refresh empty room
     * 
     * @param player
     * @param item
     * @param itemGenerator
     * @param roundCounter
     */
    public void refreshRoom(Player player, Item item, ItemGenerator itemGenerator,
            RoundCounter roundCounter) {
        return;
    }

    /**
     * there is no need to get description of empty room
     * 
     * @return null
     */
    public String getDescription() {
        return null;
    }
}
