package edu.grinnell.csc207.room.events.specialEvents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import edu.grinnell.csc207.Parser;
import edu.grinnell.csc207.RoundCounter;
import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.item.ItemGenerator;
import edu.grinnell.csc207.player.Player;

/**
 * TreasureBoxEvent
 */
public class TreasureBoxEvent extends ASpecialEvent {

    private boolean triggered;

    /**
     * Instantiate TreasureBoxEvent
     */
    public TreasureBoxEvent() {
        triggered = false;
        eventInstructions = new ArrayList<>();
        eventInstructions.add("Open it");
        eventInstructions.add("Walk away");
    }

    /**
     * Get special instructions of this event
     * 
     * @param waitCounter
     * @return instructions
     */
    public List<String> getSpecialInstructions(int waitCounter) {
        List<String> ret = new ArrayList<>();
        if (!triggered) {
            ret.add("Look at treasure box");
        }
        return ret;
    }

    /**
     * Get description of this event
     * 
     * @param waitCounter
     * @return description
     */
    public String getDescription(int waitCounter) {
        if (!triggered) {
            return "In the corner of the room, there is a huge box, which should be a treasure box";
        }
        return "There is only an empty treasure bix in the room";
    }

    /**
     * trigger this event
     * 
     * @param player
     * @param item
     * @param scanner
     * @param itemGenerator
     * @param roundCounter
     * @param instruction
     * 
     */
    public void trigger(Player player, Item item, Scanner scanner, ItemGenerator itemGenerator,
            RoundCounter roundCounter, String instruction) {
        roundCounter.nextRound();
        System.out.println("\n" + roundCounter.toString());
        System.out.println("This is truly an exquisite treasure box; you need to think "
                + "carefully about whether to open this box");
        Parser action = new Parser(scanner);
        String eventInstruction = action.act(eventInstructions);
        switch (eventInstruction) {
            case "Open it":
                System.out.println("You opened the box, and now you have a "
                        + "pile of coins in front of you");
                Random rand = new Random();
                List<String> pickUpCoins = new ArrayList<>();
                pickUpCoins.add("Pick up coins");
                action.act(pickUpCoins);
                int getCoins = 400 + rand.nextInt(201);
                System.out.println("You get " + getCoins + " coins!");
                player.changeCoins(getCoins);
                System.out.println("You now have " + player.getCoins() + " coins");
                triggered = true;
                break;
            case "Walk away":
                System.out.println("You believe that there are no good things that fall from"
                        + " the sky, this treasure chest might be a scam");
        }
    }

}
