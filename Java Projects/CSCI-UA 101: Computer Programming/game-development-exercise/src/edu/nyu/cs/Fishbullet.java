package edu.nyu.cs;

/**
 * The Fishbullet class represents a Fish bullet object.
 * 
 * @author Jessy Huang
 * @version 0.1
 */
 
public class Fishbullet extends GameObject {
    // instance properties
    private float startRotation;//the rotation angle 
    private boolean firing = false;//boolean to indicate if this fishbullet is out of boundary.
    private String imgFilePath ="images/fish1.png";

    /**
     * Constructor to create a Star object at a specific position on the screen
     * 
     * @param app a reference to the Game object that created this object
     * @param x   the x coordinate of this object on the screen
     * @param y   the y coordinate of this object on the screen
     */
    public Fishbullet(Game app, String imgFilePath, int x, int y) {
        super(app, imgFilePath, x, y);
        this.speed=5;
    }

    /** A method to draw fishbullet image at x, y and rotate in proper angle
     */
    public void draw() {
        app.pushMatrix();
        app.translate(x, y);
        float temRotation = startRotation + (float) Math.PI;
        app.rotate(temRotation);
        // draw this object's image at its x and y coordinates
        app.image(img, 0, 0);
        app.popMatrix();
    }
/**
 *  A method to set property firing
 * @param firing boolean to indicate if the bullet is out of boundary or not
 */
    public void setActiveState(boolean firing) {
        this.firing = firing;
    }

    /**
     * A method to get if the bullet is out of boundary or not
     * @return
     */
    public boolean getActiveState() {
        return firing;
    }
/**
 * A method to get the rotation property
 * @return float the rotation angle
 */
    public float getRotation() {
        return startRotation;
    }

    /**
     * A method to set x, y, rotation angle property
     * @param location int[], x, y property
     * @param startRotation float, rotation angle
     */
    public void setLocation(int[] location, float startRotation) {
        this.x = location[0];
        this.y = location[1];
        this.startRotation = startRotation;
    }

    /**
     * move the fish bullet with the angle toward CAT.
     */
    public void move() {
        if (firing) {
            x += Math.cos(startRotation) * speed;
            y += Math.sin(startRotation) * speed;
            if (x > width || x < 0 || y > height || y < 0) {
               firing = false;//bullet is out of boundary, set to false in order to remove from array list.
            }
        }

    }

}
