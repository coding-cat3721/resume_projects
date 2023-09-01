package edu.nyu.cs;

import java.util.ArrayList;

public class TestSequence {
    public static void main(String[] args) {
        // make a sentence, print out first word, last word and postion,
        Sentence s = new Sentence("next year is 2023");
        System.out
                .println("First word in sentence is:  " + s.getFirst() + " postion is: " + s.getFirst().getPosition());
        System.out.println("Last word in sentence is: " + s.getLast() + " postion is: " + s.getLast().getPosition());
        // go throught each word in the sentence, print out word and position.
        ArrayList<OrderedThing> words = s.getSequence();
        for (int i = 0; i < words.size(); i++) {
            Word tempWord = (Word) words.get(i);
            System.out.println(tempWord);
            System.out.println(
                    "first char is: " + tempWord.getFirst() + " postion is: " + tempWord.getFirst().getPosition());
            System.out.println(
                    "last char is: " + tempWord.getLast() + " postion is: " + tempWord.getLast().getPosition());
            ArrayList<OrderedThing> chars = tempWord.getSequence();
            // For each word, go through each char and print out char and postion in the
            // word
            for (int j = 0; j < chars.size(); j++) {
                Character tempChar = (Character) chars.get(j);
                System.out.println(tempChar);
            }

        }

        // polymorphism!!!
        SequentiallyOrdered[] seqWords = { s, (Word) s.getFirst() };
        for (SequentiallyOrdered seqWord : seqWords) {
            if (seqWord instanceof Word) {
                Character tempChar = (Character) seqWord.getFirst();
                System.out.println("first word is: " + seqWord);
                System.out.println("the first char in the first word is: " + tempChar);
            }
        }

    }

}
