package edu.nyu.cs;
import java.util.ArrayList;
 /**
 * The Mouse class represents a Mice object.
 * @author Jessy Huang
 * @version 0.1
 */
public class Mouse extends GameObject{
    // instance properties
    private ArrayList<Fishbullet> fishbullets;
    /**
     * Constructor to create a Star object at a specific position on the screen
     * 
     * @param app a reference to the Game object that created this object
     * @param x   the x coordinate of this object on the screen
     * @param y   the y coordinate of this object on the screen
     */
    public Mouse(Game app, String imgFilePath, int x, int y) {
       super(app, imgFilePath, x, y);
       fishbullets = new ArrayList<Fishbullet>();//creat arrayList of fish bullets.
    }
  
 /**
  * A method to move the Mice object by the key.
  * @param keyCode keyCode received from key board.
  */
    public void move(int keyCode) {
        // calculate random amount to move this star
        int dx = this.MAX_MOVEMENT;
        int dy = this.MAX_MOVEMENT;
        switch (keyCode) {
            case 37:
                // update the mouse's coordinates
                this.x -= dx;//move LEFT
                break;
            case 39:
                this.x += dx;//Move RIGHT
                break;
            case 38:
                this.y -= dy;//move UP
                break;
            case 40:
                this.y += dy;//move down
                break;
            default:

        }
    }
    
    /**
     * A method  for MICE to fire fish bullet toward the cat.
     * @param mouseLocation mouse click location
     * @param rotation rotation angle toward the cat.
     */
    public void fireFishBullet(int[] mouseLocation, float rotation){ 
        //create fish bullet
     Fishbullet fishbullet = new Fishbullet(app, "images/fish1.png", this.width - 40, this.height / 2);
     //set fish bullet rotation angle and starting position.
     fishbullet.setLocation(mouseLocation, rotation);
     fishbullet.setActiveState(true);//set fishbullet as active state
     this.fishbullets.add(fishbullet);//add fish bullet to the array list
    }
/**
 * A method to get the fish bullet array list property
 * @return ArrayList<Fishbullet>
 */
    public ArrayList<Fishbullet> getFishbullet(){
        return fishbullets;
    }
}
