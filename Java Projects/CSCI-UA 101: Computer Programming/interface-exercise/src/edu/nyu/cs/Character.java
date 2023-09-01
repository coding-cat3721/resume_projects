package edu.nyu.cs;

/**
 * Character class
 *
 * @author Jessy Huang
 * @version 0.1
 */
public class Character extends OrderedThing {
    // to store 'character'
    private char A;

    /**
     * No-args constructor that does no setup
     */
    Character() {
    }

    /**
     * constructor
     * 
     * @param a,        character of the object
     * @param position, position of the object
     */
    Character(char a, int position) {
        this.A = a;
        super.setPosition(position);
    }

    /**
     * Setter to set the character
     * 
     * @param a,character of the object
     */
    public void setChar(char a) {
        this.A = a;
    }

    /**
     * Getter of the object
     * 
     * @return the character of the object
     */
    public char getChar() {
        return this.A;
    }

    /**
     * method to print the object
     * @return, string to print the object
     */
    public String toString() {
        String result = " " + this.A;
        return result;
    }

}
