package edu.nyu.cs;

/**
 * The Cat class represents a Cat object.
 * 
 * @author Jessy Huang
 * @version 0.1
 */
public class Cat extends GameObject {
    // instance properties
    private float startRotation;// CAT move rotation angle.
    private boolean ishitFish = false; // booean to trace if the cat got hit by fishbullet.
    private Fishbullet fishbullet = null; // the fishbullet which hit the cat.

    /**
     * Constructor to create a Star object at a specific position on the screen
     * 
     * @param app a reference to the Game object that created this object
     * @param x   the x coordinate of this object on the screen
     * @param y   the y coordinate of this object on the screen
     */
    public Cat(Game app, String imgFilePath, int x, int y) {
        super(app, imgFilePath, x, y);
        this.speed=2;
    }

    /**
     * A method to move the Cat. Cat will move toward the Mice, If Cat got hit by fish bullet, Cat will
     * move together with the fish bullet.
     */
    public void move() {
        // System.out.printf("\n before move cat X= %d y= %d rotation =%f", x, y,
        // once cat is hit by fish bullet, CAT will move together with fish bullet.
        if (ishitFish) {
            moveWithFish();
        } else {
            this.x += Math.cos(startRotation) * speed;
            this.y += Math.sin(startRotation) * speed;
            //if cat move out of bounday, reset to randow location.
            if (this.x > this.width || this.x < 20 || this.y > this.height || this.y < 20) {
                if (!ishitFish) {
                    reset();
                }
            }
        }
    }

    /**
     * A method to move the CAT together with the fish bullet after CAT got hit by bullet.
     */
    public void moveWithFish() {
        // update CAT locaiton with fish bullet coordinates
        int[] fishlocation = fishbullet.getLocation(); 
        this.x = fishlocation[0] - 50;
        this.y = fishlocation[1];
    }

    /**
     * A method to set rotation
     * @param startRotation rotation property
     */
    public void setRotation(float startRotation) {
        this.startRotation = startRotation;
    }

    /**
     * A method to set the fishbullet propery which hit the cat.
     * @param fishbullet
     */
    public void setFishbullet(Fishbullet fishbullet) {
        this.fishbullet = fishbullet;
    }

    /**
     * A method to get fishbullet property
     * @return Fishbullet
     */
    public Fishbullet getFishbullet() {
        return fishbullet;

    }
/**
 * A method to get if cat got hit by fish bullet
 * @return boolean cat got hit by bullet or not
 */
    public boolean getisHitFish() {
        return ishitFish;
    }

    /**
     * A method to set ishitfish property
     * @param ishitfish 
     */
    public void setIsHitfish(boolean ishitfish) {
        this.ishitFish = ishitfish;

    }
/**
 * A method to reset the CAT at random location.
 */
    public void reset() {
        this.x = (int) (Math.random() * this.width + 50);
        this.y = (int) (Math.random() * this.height + 50);

    }

}