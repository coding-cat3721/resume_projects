package edu.nyu.cs;

import java.util.ArrayList;

/**
 * Word class, child of class OrderedThing, implement interface
 * SequentiallyOrdered.
 *
 * @author Jessy Huang
 * @version 0.1
 */
public class Word extends OrderedThing implements SequentiallyOrdered {
    // list of charactor objects
    private ArrayList<Character> charList;

    /**
     * No-args constructor that does no setup
     */
    public Word() {

    }

    /**
     * Constructor
     * 
     * @param words
     * @param position
     */
    public Word(String words, int position) {
        super.setPosition(position);
        charList = new ArrayList<Character>();
        for (int i = 0; i < words.length(); i++) {
            Character tempChar = new Character(words.charAt(i), i);
            charList.add(i, tempChar);
        }
    }

    /**
     * method to return the first Character object of the Word
     * @return, first Character object of the Word
     */
    public OrderedThing getFirst() {
        return this.charList.get(0);
    }

    /**
     * method to return the last Character object of the Word
     * @return, last Character object of the Word
     */
    public OrderedThing getLast() {
        return charList.get(charList.size() - 1);
    }

    /**
     * method to return an ArrayList containing all the Character objects in the
     * Word.
     * @return, an ArrayList containing all the Character objects in the Word.
     */
    public ArrayList<OrderedThing> getSequence() {
        // return this.words;
        ArrayList<OrderedThing> temp = new ArrayList<OrderedThing>();
        for (int i = 0; i < this.charList.size(); i++) {
            temp.add(this.charList.get(i));
        }
        return temp;
    }

    /**
     * method to print the object
     * @return, string to print the object
     */
    public String toString() {
        String result = "";
        for (int i = 0; i < charList.size(); i++) {
            result = result + ((Character) this.charList.get(i)).getChar();
        }
        return result;
    }

}
