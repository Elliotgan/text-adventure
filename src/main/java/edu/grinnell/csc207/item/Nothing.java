package edu.grinnell.csc207.item;

/**
 * Nothing
 */
public class Nothing extends Dropable {
    /**
     * Instantiate nothing
     */
    public Nothing() {
        this.name = "Nothing";
    }

    /**
     * There is no information stored in nothing
     */
    @Override
    public String toString() {
        return "Nothing";
    }

    /**
     * Type of Nothing is Nothing
     * @return nothing
     */
    public String getType() {
        return "Nothing";
    }

    /**
     * Nothing is Nothing, so return true
     * @return true
     */
    public boolean isNothing() {
        return true;
    }
    
}
