package edu.grinnell.csc207.room.events.enemy;

import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.player.Player;

/**
 * Ratling
 */
public class Ratling extends Enemy {
    /**
     * Instantiate Ratling
     */
    public Ratling() {
        this.attack = 20;
        this.hitpoint = 80;
        this.exp = 200;
        this.name = "Ratling";
        this.maxCoin = 150;
        this.minCoin = 100;
        this.stunRound = 0;
    }

    /**
     * Ratling's round
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
            System.out.println("Ratling cannot move!");
            stunRound--;
            return;
        } else {
            int damage;
            if (defenseflag) {
                damage = player.gotHit((attack * 2) / 10);
            } else {
                damage = player.gotHit(attack);
            }
            System.out.println(
                    "Ratling scratched you with its sharp front claws, "
                            + "causing " + damage + " points of damage.");
        }
    }

}
