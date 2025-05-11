package edu.grinnell.csc207.room.events.enemy;

import java.util.Random;

import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.player.Player;

/**
 * Troll
 */
public class Troll extends Enemy {

    private boolean charge;

    /**
     * Instantiate Troll
     */
    public Troll() {
        this.attack = 30;
        this.hitpoint = 120;
        this.exp = 300;
        this.name = "Troll";
        this.maxCoin = 250;
        this.minCoin = 200;
        this.charge = false;
        this.stunRound = 0;
    }

    /**
     * Troll's round
     * 
     * @param player
     * @param item
     * @param stun
     * @param defenseflag
     */
    public void enemyRound(Player player, Item item, boolean stun, boolean defenseflag) {
        if (stun) {
            stunRound += 2;
        }
        if (stunRound > 0) {
            System.out.println("Trorll cannot move!");
            stunRound--;
            return;
        } else {
            int damage;
            if (charge) {
                if (defenseflag) {
                    damage = player.gotHit((attack * 2 * 3) / 10);
                } else {
                    damage = player.gotHit(attack * 3);
                }
                System.out.println("The troll hit you hard with a mace vertically, causing"
                        + damage + " points of damage");
                return;
            }
            Random rand = new Random();
            int attackPatern = rand.nextInt(3);
            if (attackPatern <= 2) {
                if (defenseflag) {
                    damage = player.gotHit((attack * 2) / 10);
                } else {
                    damage = player.gotHit(attack);
                }
                System.out.println("The troll hit you with a mace, causing"
                        + damage + " points of damage");
                return;
            }
            charge = true;
            System.out.println("The troll raised the mace in its hand and began to charge up");
        }
    }

}
