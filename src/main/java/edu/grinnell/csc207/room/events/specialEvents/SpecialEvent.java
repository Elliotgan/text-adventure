package edu.grinnell.csc207.room.events.specialEvents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import edu.grinnell.csc207.RoundCounter;
import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.item.ItemGenerator;
import edu.grinnell.csc207.player.Player;
import edu.grinnell.csc207.room.events.Events;

/**
 * SpecialEvent
 */
public class SpecialEvent extends Events {
    protected List<ASpecialEvent> specialEvents;
    protected ASpecialEvent curSpecialEvent;
    protected int waitCounter;

    /**
     * Instantiate SpecialEvent
     */
    public SpecialEvent() {
        waitCounter = 0;
        specialEvents = new ArrayList<>();
        specialEvents.add(new OldWizardEvent());
        specialEvents.add(new LuckyWellEvent());
        specialEvents.add(new LuckyWellEvent());
        specialEvents.add(new TreasureBoxEvent());
        specialEvents.add(new HealingSpringEvent());
        Random rand = new Random();
        int eventNum = rand.nextInt(specialEvents.size());
        curSpecialEvent = specialEvents.get(eventNum);
        triggerInstructions = curSpecialEvent.getSpecialInstructions(waitCounter);
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
        curSpecialEvent.trigger(player, item, scanner, itemGenerator, roundCounter, instruction);
    }

    /**
     * wait for one round
     */
    public void waitRound() {
        System.out.println("currentWaitRound: " + waitCounter);
        waitCounter++;
    }

    /**
     * Get valid instructions of this event
     * 
     * @return valid instructions
     */
    public List<String> getValidInstructions() {
        triggerInstructions = curSpecialEvent.getSpecialInstructions(waitCounter);
        return triggerInstructions;
    }

    /**
     * Get description of this event
     * 
     * @return description
     */
    public String getDescription() {
        return curSpecialEvent.getDescription(waitCounter);
    }

}
