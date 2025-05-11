package edu.grinnell.csc207;

import java.util.Scanner;

import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.item.ItemGenerator;
import edu.grinnell.csc207.player.Player;
import edu.grinnell.csc207.room.Rooms;

/**
 * TextAdventure, main class
 */
public class TextAdventure {
    /**
     * main, run the game
     * 
     * @param args
     */
    public static void main(String[] args) {
        Player player = new Player();
        Scanner scanner = new Scanner(System.in);
        Item item = player.getItem();
        ItemGenerator itemGenerator = null;
        RoundCounter roundCounter = new RoundCounter();
        try {
            itemGenerator = new ItemGenerator("treasury");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        System.out.println("Welcome to the \"This is Not an Adventure\" Adventure Game!");
        System.out.println("Your goal is to find and defeat the hellhound "
                + "that attacked many nearby villagers");

        Rooms rooms = new Rooms(scanner, 7, 7);
        rooms.getCurrentRoom().refreshRoom(player, item, itemGenerator, roundCounter);
        rooms.getCurrentRoom().changePresent(true);

        Basicops basicops = new Basicops(player, scanner, item, rooms, itemGenerator, roundCounter);
        boolean isAlive = true;
        boolean stall = false;
        while (isAlive) {
            stall = basicops.next(stall);
            isAlive = player.isAlive();
        }
        System.out.println("You died, hope you can be a bit luckier in the next adventure");
        scanner.close();

    }
}
