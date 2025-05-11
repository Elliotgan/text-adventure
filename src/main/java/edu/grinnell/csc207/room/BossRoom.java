package edu.grinnell.csc207.room;

import java.util.ArrayList;
import java.util.Scanner;

import edu.grinnell.csc207.RoundCounter;
import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.item.ItemGenerator;
import edu.grinnell.csc207.player.Player;
import edu.grinnell.csc207.room.events.FightBoss;
import edu.grinnell.csc207.room.events.FightEvent;

/**
 * BossRoom
 */
public class BossRoom extends FightRoom {
    /**
     * Instantiate BossRoom
     * 
     * @param scanner
     */
    public BossRoom(Scanner scanner) {
        super(scanner);
        this.roomName = "Boss";
    }

    /**
     * Override getDescription, because boss room should return different
     * description
     */
    @Override
    public String getDescription() {
        return "There is a hellhound in the room, and it is your target this time -- "
                + "but you better make sure you are prepared before you go after it";
    }

    /**
     * Override refresh, because event should be fight boss
     * 
     * @param player
     * @param item
     * @param itemGenerator
     * @param roundCounter
     */
    @Override
    public void refreshRoom(Player player, Item item, ItemGenerator itemGenerator,
            RoundCounter roundCounter) {
        if (!initializedFlag) {
            this.player = player;
            this.item = item;
            this.roundCounter = roundCounter;
            this.itemGenerator = itemGenerator;
            event = new FightBoss();
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
