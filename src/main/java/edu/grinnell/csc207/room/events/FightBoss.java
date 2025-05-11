package edu.grinnell.csc207.room.events;

import java.util.List;
import java.util.Scanner;

import edu.grinnell.csc207.Parser;
import edu.grinnell.csc207.RoundCounter;
import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.item.ItemGenerator;
import edu.grinnell.csc207.item.SelectItem;
import edu.grinnell.csc207.item.consumable.ConsumablePair;
import edu.grinnell.csc207.item.consumable.Grease;
import edu.grinnell.csc207.item.consumable.HealPotion;
import edu.grinnell.csc207.item.consumable.SpiderWeb;
import edu.grinnell.csc207.player.Player;
import edu.grinnell.csc207.room.events.enemy.Hellhound;

/**
 * FightBoss
 */
public class FightBoss extends FightEvent {
    /**
     * Instantiate FightBoss
     */
    public FightBoss() {
        super(1);
        this.enemy = new Hellhound();
    }

    /**
     * Override trigger, since boss event should directly end the game after success
     */
    @Override
    public void trigger(Player player, Item item, Scanner scanner, ItemGenerator itemGenerator,
            RoundCounter roundCounter, String instruction) {
        instructions.set(0, instructions.get(0) + " " + enemy.getName());
        System.out.println("");
        System.out.println("You are fighting " + enemy.getName());
        Parser action = new Parser(scanner);
        boolean defenseflag = false;
        boolean stopRound = false;
        boolean doubledamage = false;
        boolean stunflag = false;
        while (enemy.isAlive() && player.isAlive()) {
            if (!stopRound) {
                System.out.println("");
                roundCounter.nextRound();
                System.out.println(roundCounter.toString());
                System.out.println("Your turn");
                System.out.println("You have " + player.getCurrentHp() + " HP, "
                        + enemy.getName() + " have " + enemy.getHp() + " HP");
            }
            stopRound = false;
            String curAction = action.act(instructions);
            if (curAction.equals("Attack" + " " + enemy.getName())) {
                int attack;
                if (doubledamage) {
                    attack = player.getAttack() * 2;
                    doubledamage = false;
                } else {
                    attack = player.getAttack();
                }
                enemy.gotAttack(attack);
            } else if (curAction.equals("Defense")) {
                defenseflag = true;
            } else if (curAction.equals("View Consumables")) {
                List<ConsumablePair> consumablePairs = item.getConsumables();
                for (int iter = 0; iter < consumablePairs.size(); iter++) {
                    System.out.println(consumablePairs.get(iter).toString());
                }
                System.out.println("");
                stopRound = true;
            } else if (curAction.equals("Use Consumable")) {
                SelectItem selectItem = new SelectItem(scanner);
                String selected = selectItem.selectConsumablePair(item.getConsumables());
                switch (selected) {
                    case "Healing Potion":
                        player.gotHealed(player.getHp() / 3);
                        item.removeConsumable(new HealPotion());
                        break;
                    case "Magic Grease":
                        item.removeConsumable(new Grease());
                        doubledamage = true;
                        break;
                    case "Spider Web":
                        item.removeConsumable(new SpiderWeb());
                        stunflag = true;
                        break;
                    case "Quit":
                        stopRound = true;
                }
            } else if (curAction.equals("Do Nothing")) {
            }
            ;

            if (!stopRound && enemy.isAlive()) {
                System.out.println("");
                enemy.enemyRound(player, item, stunflag, defenseflag);
                defenseflag = stunflag = false;
            }
        }
        if (!player.isAlive()) {
            return;
        }
        System.out.println("Congratulations, you have defeated the "
                + "hellhound! Thank you for playing this game");
        scanner.close();
        System.exit(0);
    }

}
