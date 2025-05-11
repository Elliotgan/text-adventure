package edu.grinnell.csc207.room.events.specialEvents;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.grinnell.csc207.Parser;
import edu.grinnell.csc207.RoundCounter;
import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.item.ItemGenerator;
import edu.grinnell.csc207.player.Player;

/**
 * HealingSpringEvent
 */
public class HealingSpringEvent extends ASpecialEvent {

    private boolean triggered;

    /**
     * Instantiate HealingSpringEvent
     */
    public HealingSpringEvent() {
        triggered = false;
        eventInstructions = new ArrayList<>();
        eventInstructions.add("Lying in the spring");
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
            ret.add("Look at spring");
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
            return "There is a pool of spring inside the room that exudes a magical aura";
        }
        return "There is only a pool of ordinary spring water in the room";
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
        System.out.println("The room contains a pool of healing spring water, "
                + "the magic contained within is probably only enough to heal you to full HP once. "
                + "Do you want to use it?");
        Parser action = new Parser(scanner);
        String eventInstruction = action.act(eventInstructions);
        switch (eventInstruction) {
            case "Lying in the spring":
                System.out.println("You lay down in the spring water, and the magical power of the "
                        + "spring water healed all the injuries on your body");
                player.gotHealed(9999999);
                System.out.println("HP: " + player.getCurrentHp() + "/" + player.getHp());
                triggered = true;
                break;
            case "Walk away":
                System.out.println("You believe it is best to reserve this treatment "
                        + "opportunity for a time when it is more needed");
        }
    }

}
