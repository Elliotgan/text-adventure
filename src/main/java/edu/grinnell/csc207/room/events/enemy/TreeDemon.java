package edu.grinnell.csc207.room.events.enemy;

import java.util.Random;

import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.player.Player;

/**
 * TreeDemon
 */
public class TreeDemon extends Enemy {

    private boolean charge;

    /**
     * Instantiate TreeDemon
     */
    public TreeDemon() {
        this.attack = 30;
        this.hitpoint = 150;
        this.exp = 300;
        this.charge = false;
        this.name = "Tree Demon";
        this.maxCoin = 250;
        this.minCoin = 200;
        this.stunRound = 0;
    }

    /**
     * Tree Demon's round
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
            System.out.println("Tree Demon cannot move!");
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
                System.out.println("All sharp branches of the tree demon simultaneously "
                        + "stabbed at you, causing " + damage + " points of damage");
                return;
            }
            Random rand = new Random();
            int attackPatern = rand.nextInt(4);
            if (attackPatern <= 2) {
                if (defenseflag) {
                    damage = player.gotHit((attack * 2) / 10);
                } else {
                    damage = player.gotHit(attack);
                }
                System.out.println("The tree demon poked you with a sharp branch, causing "
                        + damage + " points of damage");
                return;
            }
            charge = true;
            System.out.println("The tree demon stretches its branches, pointing all the "
                    + "sharp branches at you, and begins to charging up");
        }
    }

}
