package edu.nyu.cs;

import java.util.ArrayList;
import org.apache.commons.lang3.SystemUtils;
import processing.core.*; // import the base Processing library
import processing.sound.*; // import the processing sound library

/**
 * Describe your game succinctly here, and update the author info below.
 * Some starter code has been included for your reference - feel free to delete
 * or modify it.
 * **************************************************************************
 * This game is called "CAT & MICE Cheese" game. There are two phases:
 * --Game phase I: CAT and MICE
 * --Game phase II: MICE and Trapers
 * 
 * Game phase I: CAT and MICE, by click the "space" key, game starts.
 * 
 * MICE carries cheese and starts at its starting location, move toward the gate. Key "UP, DOWN, LEFT, RIGHT" 
 * control the MICE movement. During the course, there are bunch of CATs in random location walking 
 * toward the MICE and try to catch the MICE. MICE can fire fish bullet to fight back the CAT
 * by click the mouse on the CAT location. 
 * -- If CAT is hit by fish bullet, this CAT will move away with fish bullet, and out of the game. 
 *    the remaining CATs will continue. 
 * -- If CAT catch the MICE, game is over, CAT wins.
 * -- If MICE is able to get rid of all CATs with fish bullets, the game goes to the next level.
 * 
 * Game phase II: MICE and Trapers
 * 
 * bunch of mouse trappers will show up at random location.
 * --If MICE hit the traper, game is over, MICE lose.
 * --If MICE is able to escapte all the trapers on the way to the gate, sucessfull arrive at the gate 
 *   carrying the cheese, MICE wins.
 * 
 * The screening will display the winer and MICE score.
 *  
 * @author Jessy Huang
 * @version 0.1
 * 
 * 
 */
public class Game extends PApplet {
	//window size of this app
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 600;
  private SoundFile soundStartup; // will refer to a sound file to play when the program first starts
  private SoundFile soundClick; // will refer to a sound file to play when the user clicks the mouse
  private ArrayList<Cat> cats; // will hold an ArrayList of CAT objects
  private ArrayList<GameObject> miceblockers;//will hold trapers
  private Mouse mouse;//will hold Mice
  private final int NUM_CATS = 3; // the number of cats to create
  private final int NUM_MICE_BLOCKERS = 5;// the number of trappers
  private final int POINTS_PER_CAT = 2; // the number of points to award the user for each cat mice fights out.
  private final int POINTS_MOUSE_BLOCKER = 10;// the number of points to award the Mice for escape all trapers.
  private int score = 0; // the mice's score
  private boolean isCATwin = false; //flag to show if cat wins
  private boolean isMousewin = false;//flag to show if the Mice wins
  private boolean isGameover = false;//flag to show if the game is over with Mice hit the trapper.
  private boolean isGameOpen = false;//flag to show if game starts

  /**
   * This method will be automatically called by Processing when the program runs.
   * - Use it to set up the initial state of any instance properties you may use
   * in the draw method.
   */
  public void setup() {
    // set the cursor to crosshairs
    this.cursor(PApplet.CROSS);

    // load up a sound file and play it once when program starts up
    this.soundStartup = new SoundFile(this, "sounds/vibraphon.mp3"); // if you're on Windows, you may have to change
                                                                     // this to "sounds\\vibraphon.mp3"
    this.soundStartup.play();

    // load up a sound file and play it once when the user clicks
    this.soundClick = new SoundFile(this, "sounds/thump.aiff"); // if you're on Windows, you may have to change this to
                                                                // "sounds\\thump.aiff"

    // some basic settings for when we draw shapes
    this.ellipseMode(PApplet.CENTER); // setting so ellipses radiate away from the x and y coordinates we specify.
    this.rectMode(PApplet.CENTER);
    this.imageMode(PApplet.CENTER); // setting so the ellipse radiates away from the x and y coordinates we specify.
    // create a mouse
    this.mouse = new Mouse(this, "images/mouse.jpg", this.width - 40, this.height / 2);
    // create some cats, mice trapper starting life at random location.
    this.cats = new ArrayList<Cat>();
    this.miceblockers = new ArrayList<GameObject>();
    for (int i = 0; i < this.NUM_CATS; i++) {
      // create a cat and add it to the array list
      Cat cat = new Cat(this, "images/cat.png", this.width / 2 + i * 80, 50);
      this.cats.add(cat);
    }

    for (int i = 0; i < this.NUM_MICE_BLOCKERS; i++) {
      // create a mice trapper at random location and add it to the array list
      int dx = (int) (Math.random() * this.width + 150);
      int dy = (int) (Math.random() * this.height + 50);
      // adjust the location if out of boundary.
      if (dx >= width - 200) {
        dx = width - 200;
      }
      if (dy >= height - 50) {
        dy = height - 80;
      }
      GameObject miceblocker = new GameObject(this, "images/miceblocker.png", dx, dy);
      this.miceblockers.add(miceblocker);
    }
  }

  /**
   * This method is called automatically by Processing every 1/60th of a second by
   * default.
   * - Use it to modify what is drawn to the screen.
   * - There are methods for drawing various shapes, including `ellipse()`,
   * `circle()`, `rect()`, `square()`, `triangle()`, `line()`, `point()`, etc.
   */
  public void draw() {
    // fill the window with solid color
    this.background(51, 204, 255); // fill the background with the specified r, g, b color.
    // show an image of me that wanders around the window
    this.mouse.draw();// draw mouse at the edge of window.
    // draw an ellipse at the current position of the mouse
    this.fill(255, 255, 255); // set the r, g, b color to use for filling in any shapes we draw later.
    this.ellipse(this.mouseX, this.mouseY, 60, 60); // draw an ellipse wherever the mouse is
    this.rect(10, this.height / 2, 100, 150);//draw gate where MICE is moving to.

    int[] mouseLocation = mouse.getLocation();
    //display different screening content.
    if (this.isGameOpen == false) {
      displayGameOpening();
    } else if (this.isCATwin || this.isMousewin || this.isGameover) {
      displayGameOver();
    } else if (cats.size() == 0) {
      if (mouseLocation[0] < 50 && mouseLocation[1] > this.height / 2 - 20 && mouseLocation[1] < this.height / 2 + 20) {
        this.isMousewin = true;
        //Mice sucessfull arrive at the gate, Mice wins. 
        // if so, award the user some points
        score += POINTS_MOUSE_BLOCKER;
        // play a thump sound
        this.soundClick.play();
      }
      for (int i = 0; i < this.miceblockers.size(); i++) {
        GameObject tempMiceBlocker = this.miceblockers.get(i);
        tempMiceBlocker.draw();
        if (this.mouse.overlaps(tempMiceBlocker.getLocation()[0], tempMiceBlocker.getLocation()[1], 10)) {
          this.isGameover = true; //Mice hit the trapper, game over.
        }

      }
    } else if (this.cats.size() != 0) {
      this.MiceFightCatWithFishbullet(mouseLocation);//Mice fight with CATs by fish bullets.
    }
    // show the score at the bottom of the window
    String scoreString = String.format("SCORE: %d", this.score);
    textSize(24);
    text(scoreString, this.width / 2, this.height - 50);
  }

  /*
   * This method is used for Mice to fights CAT by throwing fish.
   * @param mice location object
   */
  public void MiceFightCatWithFishbullet(int[] mouseLocation) {
    ArrayList<Fishbullet> fishbullets = this.mouse.getFishbullet();
    //draw all bulltes, remove bullet from arrayList if out of bounday.
    if (!fishbullets.isEmpty()) {
      for (int i = 0; i < fishbullets.size(); i++) {
        Fishbullet tempFishbullet = fishbullets.get(i);
        if (tempFishbullet.isOutofBoundry()) {
          fishbullets.remove(i);
        } else if (tempFishbullet.getActiveState()) {
          tempFishbullet.move();
          tempFishbullet.draw();
        }
      }
    }
    // draw all cats to their current position
    for (int i = 0; i < this.cats.size(); i++) {
      Cat cat = this.cats.get(i); // get the current Star object from the ArrayList
      int[] catLocation = cat.getLocation();

      if (cat.overlaps(mouseLocation[0], mouseLocation[1], 10)) {
        this.isCATwin = true;// CAT capture mouse, CAT win.
      }
      //check if fish bullet hit cat. If yes, set CAT state as "fish bullet hit", and award score to mice, 
      //and CAT will move away with fish bullet.
      if (!fishbullets.isEmpty() && cat.getisHitFish() == false) {
        for (int j = 0; j < fishbullets.size(); j++) {
          Fishbullet fishbullet = fishbullets.get(j);
          if (fishbullet.overlaps(catLocation[0], catLocation[1], 10)) {
            // if so, award the user some points
            score += POINTS_PER_CAT;
            // play a thump sound
            this.soundClick.play();
            cat.setIsHitfish(true);
            cat.setFishbullet(fishbullet);
          }
        }
      }
      //CAT is hit by fish bullet, and move out of bounday, then remove the cat from arrayList.
      if (cat.getisHitFish() == true) {
        if (cat.isOutofBoundry()) {
          this.cats.remove(cat);// delete the star from the ArrayList
        } else {
          cat.move();
          cat.draw();
        }

      } else {
        float rotation = atan2(mouseLocation[1] - catLocation[1], mouseLocation[0] - catLocation[0]);
        cat.setRotation(rotation);//calculate the direction toward the Mice.
        cat.move(); // move the cat towards the Mice.
        cat.draw(); // draw the cat to the screen
      }

    }

  }

  /**
   * This method is automatically called by Processing every time the user presses
   * a key while viewing the map.
   * - The `key` variable (type char) is automatically is assigned the value of
   * the key that was pressed.
   * - The `keyCode` variable (type int) is automatically is assigned the numeric
   * ASCII/Unicode code of the key that was pressed.
   */
  public void keyPressed() {
    // the `key` variable holds the char of the key that was pressed, the `keyCode`
    // variable holds the ASCII/Unicode numeric code for that key.
    System.out.println(String.format("Key pressed: %s, key code: %d.", this.key, this.keyCode));
    // left/right/up/down arrow keys to move mouse.
    switch (this.keyCode) {
      case 37:// LEFT key
      case 38:// UP key
      case 39:// RIGHT key
      case 40:// DOWN key
        mouse.move(keyCode);
        break;
      case ' ':
        isGameOpen = true;//Key "space" to start the game, draw all cats at random location.
        for (int i = 0; i < this.NUM_CATS; i++) {
          int dx = (int) (Math.random() * this.width - 350);
          int dy = (int) (Math.random() * this.height - 50);
          if (dx >= this.width) {
            dx = this.width - 350;//adjust location if out of boundary.
          }
          if (dy >= this.height) {
            dy = this.height - 150;//adjust location if out of boundary.
          }

          Cat cat = cats.get(i);
          cat.setLocation(dx, dy);
          cat.draw();
        }

    }

  }

  /**
   * This method is automatically called by Processing every time the user clicks
   * a mouse button.
   * - The `mouseX` and `mouseY` variables are automatically is assigned the
   * coordinates on the screen when the mouse was clicked.
   * - The `mouseButton` variable is automatically assigned the value of either
   * the PApplet.LEFT or PApplet.RIGHT constants, depending upon which button was
   * pressed.
   * 
   * this method is modified such that the MICE will fire the fish bullet toward  mouse 
   * click location.
   */
  public void mouseClicked() {
    System.out.println(String.format("Mouse clicked at: %d:%d.", this.mouseX, this.mouseY));

    int[] mouseLocation = mouse.getLocation();
    float rotation = atan2(this.mouseY - mouseLocation[1], this.mouseX - mouseLocation[0]);
    mouse.fireFishBullet(mouseLocation, rotation); 
  }

  /**
   * This method is automatically called by Processing every time the user presses
   * down and drags the mouse.
   * The `mouseX` and `mouseY` variables are automatically is assigned the
   * coordinates on the screen when the mouse was clicked.
   */
  public void mouseDragged() {
    System.out.println(String.format("Mouse dragging at: %d:%d.", mouseX, mouseY));
  }

  /**
   * A method that can be used to modify settings of the window, such as set its
   * size.
   * This method shouldn't really be used for anything else.
   * Use the setup() method for most other tasks to perform when the program first
   * runs.
   */
  public void settings() {
    size(Game.WIDTH, Game.HEIGHT); // set the map window size, using the OpenGL 2D rendering engine
    System.out.println(String.format("Set up the window size: %d, %d.", width, height));
  }
/**
 * A method to display the game opening screening content.
 */
  public void displayGameOpening() {
    textSize(48);
    fill(0, 48, 612);
    this.text("Welcome to CAT & Mice Cheese Game!   ", this.width / 10, this.height / 2);
    textSize(18);
    this.text("Press space key to start the game. ", 320, this.height - 150);
    this.text("To fight CAT, mouse points to the cat, right click to shoot fish", 320, this.height - 130);
    this.text("Arrow Key UP,DOWN, LEFT, RIGHT to move Mice", 320, this.height - 110);
    mouse.setLocation(width - 40, height / 2);
    mouse.draw();
    for (int i = 0; i < this.NUM_CATS; i++) {
      Cat cat = cats.get(i);
      cat.draw();
    }

  }
/**
 * A method to display the game over screening content.
 */
  public void displayGameOver() {
    this.textSize(68);
    fill(0, 38, 612);
    if (isCATwin) {
      this.text("CATS  WIN! GAME   OVER!", 200, this.height / 2);
    } else if(isMousewin) {
      this.text("MICE WINS! GAME   OVER!", 200, this.height / 2);
    } else{
      this.text("MICE LOSE! GAME   OVER!", 200, this.height / 2);
    }
  }

  /**
   * The main function is automatically called first in a Java program.
   * When using the Processing library, this method must call PApplet's main
   * method and pass it the full class name, including package.
   * You shouldn't need to modify this method.
   * 
   * @param args An array of any command-line arguments.
   */
  public static void main(String[] args) {
    // make sure we're using Java 1.8
    System.out.printf("\n###  JDK IN USE ###\n- Version: %s\n- Location: %s\n### ^JDK IN USE ###\n\n",
        SystemUtils.JAVA_VERSION, SystemUtils.getJavaHome());
    boolean isGoodJDK = SystemUtils.IS_JAVA_1_8;
    if (!isGoodJDK) {
      System.out.printf("Fatal Error: YOU MUST USE JAVA 1.8, not %s!!!\n", SystemUtils.JAVA_VERSION);
    } else {
      PApplet.main("edu.nyu.cs.Game"); // do not modify this!
    }
  }

}
