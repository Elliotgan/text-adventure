package edu.grinnell.csc207.room.events.enemy;

import java.util.Random;

import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.player.Player;

/**
 * Hellhound
 */
public class Hellhound extends Enemy {

    private boolean charge;

    /**
     * Instantiate Hellhound
     */
    public Hellhound() {
        this.attack = 50;
        this.hitpoint = 300;
        this.exp = 2000;
        this.charge = false;
        this.name = "Hellhound";
        this.maxCoin = 20000;
        this.minCoin = 20000;
        this.stunRound = 0;
    }

    /**
     * Hellhound's round
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
            System.out.println("Hellhound cannot move!");
            charge = false;
            stunRound--;
            return;
        } else {
            int damage;
            if (charge) {
                if (defenseflag) {
                    damage = player.gotHit((attack * 2 * 6) / 10);
                } else {
                    damage = player.gotHit(attack * 6);
                }
                System.out.println("The hellhound spat out a huge fireball, causing you"
                        + damage + " points of damage");
                return;
            }
            Random rand = new Random();
            int attackPatern = rand.nextInt(6);
            if (attackPatern <= 2) {
                if (defenseflag) {
                    damage = player.gotHit((attack * 2) / 10);
                } else {
                    damage = player.gotHit(attack);
                }
                System.out.println("The hellhound scratched you with its claws, causing you "
                        + damage + " points of damage");
                return;
            }
            if (attackPatern <= 4) {
                if (defenseflag) {
                    damage = player.gotHit((attack * 2) / 10);
                } else {
                    damage = player.gotHit(attack * 2);
                }
                System.out.println("The hellhound spat out a small fireball, causing you "
                        + damage + " points of damage");
                return;
            }
            charge = true;
            System.out.println("The hellhound is charging up, and flames are constantly"
                    + "emerging from the gaps in its mouth");
        }
    }

}
