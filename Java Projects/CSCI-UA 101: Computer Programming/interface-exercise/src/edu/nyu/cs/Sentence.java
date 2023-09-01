package edu.nyu.cs;

import java.util.ArrayList;

/**
 * * Sentence class, implement interface SequentiallyOrdered.
 *
 * @author Jessy Huang
 * @version 0.1
 */
public class Sentence implements SequentiallyOrdered {
    // private ArrayList<OrderedThing> words;
    private ArrayList<Word> words;

    /**
     * No-args constructor that does no setup
     */
    Sentence() {
    }

    /**
     * Constructor
     * 
     * @param sentence
     */
    Sentence(String sentence) {
        // words = new ArrayList<OrderedThing>();
        words = new ArrayList<Word>();
        // split by any non-alphanumeric character
        String[] wordArray = sentence.split("[^\\w']+");
        for (int i = 0; i < wordArray.length; i++) {
            Word tempWord = new Word(wordArray[i], i);
            words.add(i, tempWord);
        }
    }

    /**
     * method to return the first Word of the Sentence,
     * @return, first word in the sentence
     */
    public OrderedThing getFirst() {
        return words.get(0);
    }

    /**
     * method to return the last Word of the Sentence,
     * @return, last word in the sentence
     */
    public OrderedThing getLast() {
        return this.words.get(words.size() - 1);
    }

    /**
     * Getter to return an ArrayList containing all the Word objects in the Sentence
     * @return, an ArrayList containing all the Word objects in the Sentence
     */
    public ArrayList<OrderedThing> getSequence() {
        // return this.words;
        ArrayList<OrderedThing> temp = new ArrayList<OrderedThing>();
        for (int i = 0; i < this.words.size(); i++) {
            temp.add(this.words.get(i));
        }
        return temp;
    }

}
