package edu.grinnell.csc207.item;

/**
 * Dropable
 */
public abstract class Dropable {
    protected String name;
    protected int price;

    /**
     * Get the name of Dropable
     * 
     * @return name of Dropable
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the price of Dropable
     * 
     * @return price of Dropable
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * get type of dropable
     * @return type of dropable
     */
    public abstract String getType();

    /**
     * tell is dropable is nothing
     * @return if dropable is nothing
     */
    public abstract boolean isNothing();
}
