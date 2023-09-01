//package project5;

import java.util.PriorityQueue;
import java.util.Scanner;

public class HowManyWords {
    public static void main(String[] args) throws Exception {
        // interactive mode:
        Scanner userInput = new Scanner(System.in);
        PriorityQueue<String> pq = new PriorityQueue<>();
        char letter = ' ';
        //boolean isSpecial = false;
        boolean isWord = false;
        int length = 0;
        StringBuilder current = new StringBuilder();
        while (userInput.hasNextLine()) { // This is the main condition for EOF
            StringBuilder line = new StringBuilder(userInput.nextLine());
            length = line.length();
            for (int i = 0; i < length; i++) {
                letter = line.charAt(i);
               if ('A' <= letter && letter <= 'Z' || 'a' <= letter && letter <= 'z') {
                    current = current.append(letter);
                } else {
                    isWord = true;
                }
                if ((i == line.length() - 1) || isWord) {
                    isWord = false;
                    if (!current.toString().isEmpty()) {
                        String lowcaseCurrent = current.toString().toLowerCase();
                        if (!pq.contains(lowcaseCurrent)) {
                            pq.add(lowcaseCurrent);
                        }
                        current.setLength(0);
                    }
                }

            }
        }
        userInput.close();
        String tmp = null;

        while (!pq.isEmpty()) {
            tmp = pq.poll();
            System.out.println(tmp.trim());

        }

    }

}
