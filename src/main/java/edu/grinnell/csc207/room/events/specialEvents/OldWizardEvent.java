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
 * OldWizardEvent
 */
public class OldWizardEvent extends ASpecialEvent {
    private boolean triggered;

    /**
     * Instantiate OldWizardEvent
     */
    public OldWizardEvent() {
        triggered = false;
        eventInstructions = new ArrayList<>();
        eventInstructions.add("Agree");
        eventInstructions.add("Disagree");
    }

    /**
     * get special instructions of this event
     * 
     * @param waitCounter
     * @return instructions
     */
    public List<String> getSpecialInstructions(int waitCounter) {
        List<String> ret = new ArrayList<>();
        if (!triggered) {
            ret.add("Talk to Old Wizard");
        }
        return ret;
    }

    /**
     * Get description of this event
     * 
     * @param waitCounter
     * @return descriptions
     */
    public String getDescription(int waitCounter) {
        if (!triggered) {
            return "There is a man with a wizard hat and a head full of white hair "
                    + "standing in the room in a daze, he looks like a old wizard";
        }
        return "No one is in this room anymore; the person who claimed "
                + "to be the old wizard has already left";
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
        System.out.println("The old wizard asks you if you can give him 20 coins."
                + "He promises to give you some exp as a reward, but to be honest, "
                + "he looks a little suspicious");
        Parser action = new Parser(scanner);
        String eventInstruction = action.act(eventInstructions);
        switch (eventInstruction) {
            case "Agree":
                if (player.getCoins() >= 20) {
                    System.out.println("The old wizard took your coin and muttered for a while, "
                            + "you felt your exp increase...Wait, why did it only increase by this "
                            + "little? That wizard ran away!");
                    player.changeCoins(-20);
                    System.out.println("You currently have " + player.getCoins() + " coins");
                    player.getExp(50);
                    triggered = true;
                }
                break;
            case "Disagree":
                System.out.println(
                        "The old wizard sighed, \"Fine, I'll just be here "
                                + "waiting for you to change your mind.\"");
        }
    }

}
