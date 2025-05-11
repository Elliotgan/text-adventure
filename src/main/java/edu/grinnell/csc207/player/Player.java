package edu.grinnell.csc207.player;

import edu.grinnell.csc207.item.Item;

/**
 * This class carry all player informationss
 */
public class Player {
    private int level;
    private int exp;
    private int attack;
    private int hitpoint;
    private int currenthitpoint;
    private Item items;
    private int coins;

    /**
     * Instantiate player
     */
    public Player() {
        this.level = 1;
        this.exp = 0;
        this.attack = 10;
        this.hitpoint = 20;
        this.coins = 50;
        this.currenthitpoint = this.hitpoint;
        this.items = new Item();
    }

    /**
     * Player get exp
     * 
     * @param getexp
     */
    public void getExp(int getexp) {
        System.out.println("\nYou have gained " + getexp + " EXP!");
        this.exp += getexp;
        while (this.exp >= level * 100) {
            exp = exp % (level * 100);
            level += 1;
            System.out.println("Congrats! You have reached level " + level);
            int prevattack = attack;
            int prevhitpoint = hitpoint;
            attack = level * 10;
            currenthitpoint = hitpoint = level * 20;
            System.out.println("Attack: " + prevattack + "-->"
                    + attack + " HP: " + prevhitpoint + "-->" + hitpoint);
        }
    }

    /**
     * Player has been healed
     * 
     * @param healVal
     */
    public void gotHealed(int healVal) {
        this.currenthitpoint += healVal;
        if (currenthitpoint > hitpoint) {
            currenthitpoint = hitpoint;
        }
        System.out.println("\nYou have been healed! Current HP: " + currenthitpoint);
    }

    /**
     * Get level of player
     * 
     * @return level
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Get attack of player
     * 
     * @return attack
     */
    public int getAttack() {
        return (this.attack + items.getCurrentWeapon().getAttack());
    }

    /**
     * Player has been hit by other
     * 
     * @param damage
     * @return the damage that player suffers
     */
    public int gotHit(int damage) {
        int hpcost = ((100 - items.getCurrentArmor().getDefense()) * damage / 100);
        this.currenthitpoint -= hpcost;
        return hpcost;
    }

    /**
     * Get if player is alive
     * 
     * @return if player is alive
     */
    public boolean isAlive() {
        return (currenthitpoint > 0);
    }

    /**
     * get maximum HP of player
     * 
     * @return maximum HP
     */
    public int getHp() {
        return this.hitpoint;
    }

    /**
     * get current HP of player
     * 
     * @return current HP
     */
    public int getCurrentHp() {
        return this.currenthitpoint;
    }

    /**
     * Get number of coins player holds
     * 
     * @return number of coins
     */
    public int getCoins() {
        return this.coins;
    }

    /**
     * Change the quantity of coins player holds
     * 
     * @param changeval
     */
    public void changeCoins(int changeval) {
        this.coins += changeval;
        if (this.coins < 0) {
            this.coins = 0;
        }
    }

    /**
     * Get the Item of player
     * 
     * @return Item
     */
    public Item getItem() {
        return this.items;
    }

    /**
     * Get message of player
     */
    @Override
    public String toString() {
        return ("Level: " + this.level + "\n"
                + "EXP: " + this.exp + "/" + this.level * 100 + "\n"
                + "Weapon: " + items.getCurrentWeapon().getName() + "\n"
                + "Armor " + items.getCurrentArmor().getName() + "\n"
                + "Attack: " + this.attack + ", Weapon attack: "
                + this.items.getCurrentWeapon().getAttack()
                + ", Total attack: "
                + (this.attack + this.items.getCurrentWeapon().getAttack()) + "\n"
                + "Defense:" + this.items.getCurrentArmor().getDefense() + "\n"
                + "HP: " + this.currenthitpoint + "/" + this.hitpoint + "\n"
                + "Coins: " + this.coins);
    }
}
