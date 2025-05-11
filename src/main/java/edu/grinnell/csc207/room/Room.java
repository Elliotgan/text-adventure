package edu.grinnell.csc207.room;

import java.util.List;
import java.util.Scanner;

import edu.grinnell.csc207.RoundCounter;
import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.item.ItemGenerator;
import edu.grinnell.csc207.player.Player;
import edu.grinnell.csc207.room.events.Events;

/**
 * Room
 */
public abstract class Room {
    protected Player player;
    protected Item item;
    protected ItemGenerator itemGenerator;
    protected RoundCounter roundCounter;
    protected Events event;
    protected String roomName;
    protected boolean hasNorth;
    protected boolean hasSouth;
    protected boolean hasWest;
    protected boolean hasEast;
    protected Scanner scanner;
    protected boolean present;
    protected List<String> instructions;

    /**
     * get special instructions this room have
     * 
     * @return special instructions
     */
    public List<String> getValidInstructions() {
        return this.instructions;
    }

    /**
     * tell is there is room in the north of this room
     * 
     * @param hasNorth
     */
    public void changeNorth(boolean hasNorth) {
        this.hasNorth = hasNorth;
    }

    /**
     * tell is there is room in the south of this room
     * 
     * @param hasSouth
     */
    public void changeSouth(boolean hasSouth) {
        this.hasSouth = hasSouth;
    }

    /**
     * tell is there is room in the west of this room
     * 
     * @param hasWest
     */
    public void changeWest(boolean hasWest) {
        this.hasWest = hasWest;
    }

    /**
     * tell is there is room in the east of this room
     * 
     * @param hasEast
     */
    public void changeEast(boolean hasEast) {
        this.hasEast = hasEast;
    }

    /**
     * change the visibility of current room
     * 
     * @param present
     */
    public void changePresent(boolean present) {
        this.present = present;
    }

    /**
     * tell if current room is visible
     * 
     * @return if current room is visible
     */
    public boolean isPresent() {
        return this.present;
    }

    /**
     * return number of rooms connected to current room
     * 
     * @return number of rooms connected to current room
     */
    public int getConnectionNum() {
        int ret = 0;
        if (hasNorth) {
            ret++;
        }
        if (hasSouth) {
            ret++;
        }
        if (hasEast) {
            ret++;
        }
        if (hasWest) {
            ret++;
        }
        return ret;
    }

    /**
     * print second line of room
     */
    public void printSecondLine() {
        System.out.printf("%-1s %-11s %-1s", "|", roomName, "|");
    }

    /**
     * print third line of room
     */
    public void printThirdLine() {
        if (hasWest) {
            if (hasEast) {
                System.out.printf("%-1s %-11s %-1s", "", "", "");
            } else {
                System.out.printf("%-1s %-11s %-1s", "", "", "|");
            }
        } else {
            if (hasEast) {
                System.out.printf("%-1s %-11s %-1s", "|", "", "");
            } else {
                System.out.printf("%-1s %-11s %-1s", "|", "", "|");
            }
        }
    }

    /**
     * print fourth line of room
     * 
     * @param isInRoom
     */
    public void printFourthLine(boolean isInRoom) {
        if (isInRoom) {
            System.out.printf("%-1s %-11s %-1s", "|", "you", "|");
            return;
        }
        System.out.printf("%-1s %-11s %-1s", "|", "", "|");
    }

    /**
     * print first line of room
     */
    public void printTop() {
        if (hasNorth) {
            System.out.printf("%-13s", "-----   -----");
            return;
        }
        System.out.printf("%-13s", "-------------");
    }

    /**
     * print bottom line of room
     */
    public void printBottom() {
        if (hasSouth) {
            System.out.printf("%-13s", "-----   -----");
            return;
        }
        System.out.printf("%-13s", "-------------");
    }

    /**
     * print a empty line
     */
    public void printEmptyLine() {
        System.out.printf("%-13s", "");
    }

    /**
     * indicated player has wait for a round
     */
    public abstract void waitRound();

    /**
     * tell if room is empty
     * 
     * @return if room is empty
     */
    public abstract boolean isEmpty();

    /**
     * refresh room
     * 
     * @param player
     * @param item
     * @param itemGenerator
     * @param roundCounter
     */
    public abstract void refreshRoom(Player player, Item item, ItemGenerator itemGenerator,
            RoundCounter roundCounter);

    /**
     * get description
     * 
     * @return description
     */
    public abstract String getDescription();

    /**
     * get instructions other than those ini Basicops
     * @param instruction
     */
    public abstract void specialInstructions(String instruction);

}
