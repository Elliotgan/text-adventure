package edu.grinnell.csc207.room.events.enemy;

import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.player.Player;

/**
 * Goblin
 */
public class Goblin extends Enemy {
    /**
     * Instantiate Goblin
     */
    public Goblin() {
        this.attack = 5;
        this.hitpoint = 20;
        this.exp = 100;
        this.name = "Goblin";
        this.maxCoin = 50;
        this.minCoin = 30;
        this.stunRound = 0;
    }

    /**
     * Goblin's round
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
            System.out.println("Goblin cannot move!");
            stunRound--;
            return;
        } else {
            int damage;
            if (defenseflag) {
                damage = player.gotHit((attack * 2) / 10);
            } else {
                damage = player.gotHit(attack);
            }
            System.out.println("Goblin use the sword to hit you, and caused "
                    + damage + " points of damage.");
        }
    }

}
