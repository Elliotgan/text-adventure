package edu.grinnell.csc207.room.events.enemy;

import java.util.Random;

import edu.grinnell.csc207.item.Item;
import edu.grinnell.csc207.player.Player;

/**
 * Enemy
 */
public abstract class Enemy {
    protected int attack;
    protected int hitpoint;
    protected int exp;
    protected String name;
    protected int stunRound;
    protected int maxCoin;
    protected int minCoin;

    /**
     * Get hp of enemy
     * 
     * @return hp
     */
    public int getHp() {
        return this.hitpoint;
    }

    /**
     * Get attack of enemy
     * 
     * @return attack of enemy
     */
    public int getAttack() {
        return this.attack;
    }

    /**
     * Get name of enemy
     * 
     * @return name of enemy
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get exp enemy gives
     * 
     * @return exp that enemy gives
     */
    public int getExp() {
        return this.exp;
    }

    /**
     * Get coins that enemy gives
     * 
     * @return coins that enemy gives
     */
    public int getCoins() {
        Random rand = new Random();
        int ret = rand.nextInt(maxCoin - minCoin + 1);
        ret += minCoin;
        return ret;

    }

    /**
     * Enemy has been hit by player
     * 
     * @param damage
     */
    public void gotAttack(int damage) {
        hitpoint -= damage;
        System.out.println("Dealt " + damage + " points of damage to the " + name);
    }

    /**
     * Get if enemy is alive
     * 
     * @return is enemy is alive
     */
    public boolean isAlive() {
        return (hitpoint > 0);
    }

    /**
     * Enemy round, different enemy would act differently
     * 
     * @param player
     * @param item
     * @param stun
     * @param defenseflag
     */
    public abstract void enemyRound(Player player, Item item, boolean stun, boolean defenseflag);
}
