package edu.grinnell.csc207.room.events.enemy;

import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.player.Player;

/**
 * Slime
 */
public class Slime extends Enemy {
    /**
     * Instantiate Slime
     */
    public Slime() {
        this.attack = 10;
        this.hitpoint = 50;
        this.exp = 150;
        this.name = "Slime";
        this.maxCoin = 100;
        this.minCoin = 70;
        this.stunRound = 0;
    }

    /**
     * Slime's round
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
            System.out.println("Slime cannot move!");
            stunRound--;
            return;
        } else {
            int damage;
            if (defenseflag) {
                damage = player.gotHit((attack * 2) / 10);
            } else {
                damage = player.gotHit(attack);
            }
            System.out.println("The slime splashed corrosive slime on you, "
                    + "causing " + damage + " points of damage.");
        }
    }
}
