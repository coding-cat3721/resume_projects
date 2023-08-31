package edu.nyu.cs;

/**
 * Class OrderedThing.
 *
 * @author Jessy Huang
 * @version 0.1
 */
public class OrderedThing {
    // position in the ordered objects.
    private int position;

    /**
     * No-args constructor that does no setup
     */
    public OrderedThing() {
    };

    /**
     * Getter to get the position.
     * 
     * @return position
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Setter to set the postion.
     * 
     * @param p, position of the ordered object
     */
    public void setPosition(int p) {
        this.position = p;
    }
}
