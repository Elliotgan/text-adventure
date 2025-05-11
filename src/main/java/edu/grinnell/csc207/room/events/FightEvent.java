package edu.grinnell.csc207.room.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import edu.grinnell.csc207.Parser;
import edu.grinnell.csc207.RoundCounter;
import edu.grinnell.csc207.item.Dropable;
import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.item.ItemGenerator;
import edu.grinnell.csc207.item.SelectItem;
import edu.grinnell.csc207.item.consumable.ConsumablePair;
import edu.grinnell.csc207.item.consumable.Grease;
import edu.grinnell.csc207.item.consumable.HealPotion;
import edu.grinnell.csc207.item.consumable.SpiderWeb;
import edu.grinnell.csc207.player.Player;
import edu.grinnell.csc207.room.events.enemy.Enemy;
import edu.grinnell.csc207.room.events.enemy.GiantSpider;
import edu.grinnell.csc207.room.events.enemy.Goblin;
import edu.grinnell.csc207.room.events.enemy.Ratling;
import edu.grinnell.csc207.room.events.enemy.Slime;
import edu.grinnell.csc207.room.events.enemy.TreeDemon;
import edu.grinnell.csc207.room.events.enemy.Troll;

/**
 * FightEvent
 */
public class FightEvent extends Events {
    private List<Enemy> enemies;
    protected Enemy enemy;

    /**
     * Instantiate FightEvent
     * 
     * @param playerLevel
     */
    public FightEvent(int playerLevel) {
        this.enemies = new ArrayList<>();
        this.instructions = new ArrayList<>();
        enemies.add(new Goblin());
        enemies.add(new GiantSpider());
        enemies.add(new Ratling());
        enemies.add(new Slime());
        enemies.add(new Troll());
        enemies.add(new TreeDemon());
        Random rand = new Random();
        int max;
        if (playerLevel <= 1) {
            max = 1;
        } else if (playerLevel <= 3) {
            max = 2;
        } else if (playerLevel <= 5) {
            max = 4;
        } else {
            max = 6;
        }
        int enemyIndex = rand.nextInt(max);
        this.enemy = enemies.get(enemyIndex);
        instructions.add("Attack");
        instructions.add("Defense");
        instructions.add("View Consumables");
        instructions.add("Use Consumable");
        instructions.add("Do Nothing");
    }

    /**
     * get name of the monster
     * 
     * @return name of monster
     */
    public String getMonsterName() {
        return this.enemy.getName();
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
        System.out.println("\nYou have defeated " + enemy.getName() + "!");
        int coins = enemy.getCoins();
        player.changeCoins(coins);
        System.out.println("\nYou have received " + coins + " coins");
        Dropable dropItem = itemGenerator.dropItem(enemy.getName());
        System.out.println("\nYou get:\n" + dropItem.toString());
        item.addDropable(dropItem.getType(), dropItem);
        player.getExp(enemy.getExp());
    }
}
