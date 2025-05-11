package edu.grinnell.csc207.item;

import java.util.List;
import java.util.Random;

/**
 * a list that tells what a monster drop
 */
public class DropList {
    String monsterName;
    List<DropPair> dropPairs;

    /**
     * Instantiate DropList
     * 
     * @param monsterName
     * @param dropPairs
     */
    public DropList(String monsterName, List<DropPair> dropPairs) {
        this.monsterName = monsterName;
        this.dropPairs = dropPairs;
    }

    /**
     * Get the name of monster that drop item
     * 
     * @return name of monster
     */
    public String getName() {
        return this.monsterName;
    }

    /**
     * Select a item to drop
     * 
     * @return Dropable Item
     */
    public Dropable dropItem() {
        int totalWeight = 0;
        for (int iter = 0; iter < dropPairs.size(); iter++) {
            totalWeight += dropPairs.get(iter).getWeight();
        }
        Random rand = new Random();
        int dropnum = rand.nextInt(totalWeight) + 1;
        int currentWeight;
        Dropable ret = null;
        for (int iter = 0; iter < dropPairs.size() && dropnum > 0; iter++) {
            ret = dropPairs.get(iter).getDropable();
            currentWeight = dropPairs.get(iter).getWeight();
            dropnum -= currentWeight;
        }
        return ret;
    }

}
