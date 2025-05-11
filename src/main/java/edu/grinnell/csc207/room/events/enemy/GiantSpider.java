package edu.grinnell.csc207.room.events.enemy;

import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.player.Player;

/**
 * Giant spider
 */
public class GiantSpider extends Enemy {
    /**
     * Instantiate GiantSpider
     */
    public GiantSpider() {
        this.attack = 10;
        this.hitpoint = 40;
        this.exp = 150;
        this.name = "Giant Spider";
        this.maxCoin = 80;
        this.minCoin = 50;
        this.stunRound = 0;
    }

    /**
     * Giant Spider's round
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
            System.out.println("Giant Spider cannot move!");
            stunRound--;
            return;
        } else {
            int damage;
            if (defenseflag) {
                damage = player.gotHit((attack * 2) / 10);
            } else {
                damage = player.gotHit(attack);
            }
            System.out.println("Giant spider stung you, causing " + damage + " points of damage.");
        }
    }
}
