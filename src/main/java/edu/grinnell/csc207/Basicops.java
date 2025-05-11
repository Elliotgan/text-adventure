package edu.grinnell.csc207;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.item.ItemGenerator;
import edu.grinnell.csc207.item.SelectItem;
import edu.grinnell.csc207.player.Player;
import edu.grinnell.csc207.room.Room;
import edu.grinnell.csc207.room.Rooms;

/**
 * handle with some basic options
 */
public class Basicops {
    private Player player;
    private Scanner scanner;
    private Item item;
    private Rooms rooms;
    private ItemGenerator itemGenerator;
    private RoundCounter roundCounter;

    /**
     * Instantiate Basicops
     * 
     * @param player
     * @param scanner
     * @param item
     * @param rooms
     * @param itemGenerator
     * @param roundCounter
     */
    public Basicops(Player player, Scanner scanner, Item item, Rooms rooms,
            ItemGenerator itemGenerator, RoundCounter roundCounter) {
        this.player = player;
        this.scanner = scanner;
        this.item = item;
        this.rooms = rooms;
        this.itemGenerator = itemGenerator;
        this.roundCounter = roundCounter;
    }

    /**
     * Execute next round (if event triggered, there might be more than one round)
     * 
     * @param stall if the roundCounter need to be stalled
     * @return boolean that tells if next round need to stall the roundCounter
     */
    public boolean next(boolean stall) {
        Room curRoom = rooms.getCurrentRoom();
        curRoom.refreshRoom(player, item, itemGenerator, roundCounter);
        if (!stall) {
            roundCounter.nextRound();
            System.out.println("\n" + roundCounter.toString());
            System.out.println(curRoom.getDescription());
        }
        List<String> instructions = curRoom.getValidInstructions();
        Parser actions = new Parser(scanner);
        String act = actions.act(instructions);
        switch (act) {
            case "Go North":
                rooms.move("North");
                rooms.getCurrentRoom().changePresent(true);
                break;
            case "Go South":
                rooms.move("South");
                rooms.getCurrentRoom().changePresent(true);
                break;
            case "Go West":
                rooms.move("West");
                rooms.getCurrentRoom().changePresent(true);
                break;
            case "Go East":
                rooms.move("East");
                rooms.getCurrentRoom().changePresent(true);
                break;
            case "View Status":
                System.out.println(player.toString());
                return true;
            case "View Items":
                System.out.println("\nCurrently equiped Weapon:");
                System.out.println(item.getCurrentWeapon().toString());
                System.out.println("\nCurrently equiped Armor:");
                System.out.println(item.getCurrentArmor().toString());
                System.out.println("\nWeapon List:");
                for (int iter = 0; iter < item.getWeapons().size(); iter++) {
                    System.out.println(item.getWeapons().get(iter).toString());
                }
                System.out.println("\nArmor List:");
                for (int iter = 0; iter < item.getArmors().size(); iter++) {
                    System.out.println(item.getArmors().get(iter).toString());
                }
                System.out.println("\nConsumable List:");
                for (int iter = 0; iter < item.getConsumables().size(); iter++) {
                    System.out.println(item.getConsumables().get(iter).toString());
                }
                List<String> itemInstructions = new ArrayList<>();
                itemInstructions.add("Change current weapon");
                itemInstructions.add("Change current armor");
                itemInstructions.add("Quit");
                String itemInstruction = actions.act(itemInstructions);
                switch (itemInstruction) {
                    case "Change current weapon":
                        SelectItem selectWeapon = new SelectItem(scanner);
                        String selectedWeapon = selectWeapon.selectWeaponPair(item.getWeapons());
                        if (selectedWeapon.toLowerCase().equals("quit")) {
                            return true;
                        }
                        item.switchWeapon(selectedWeapon);
                        return true;
                    case "Change current armor":
                        SelectItem selectArmor = new SelectItem(scanner);
                        String selectedArmor = selectArmor.selectArmorPair(item.getArmors());
                        if (selectedArmor.toLowerCase().equals("quit")) {
                            return true;
                        }
                        item.switchArmor(selectedArmor);
                        return true;
                    case "Quit":
                        return true;
                }
            case "Print Map":
                rooms.printMap();
                return true;
            case "Wait":
                rooms.getCurrentRoom().waitRound();
                break;
            case "Return to previous room":
                rooms.returnToPreviousPos();
                rooms.getCurrentRoom().changePresent(true);
                break;
            default:
                rooms.getCurrentRoom().specialInstructions(act);
                return true;
        }
        return false;
    }
}
