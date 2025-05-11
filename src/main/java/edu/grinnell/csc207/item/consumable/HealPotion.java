package edu.grinnell.csc207.item.consumable;

/**
 * HealPotion
 */
public class HealPotion extends Consumable {
    /**
     * Instantiate HealPotion
     */
    public HealPotion() {
        super("Healing Potion", "Heal 1/3 maximum HP", 20,
                "Just like its name, the healing potion. For your own good, "
                        + "it's best not to investigate the ingredients of this potion");
    }

}
