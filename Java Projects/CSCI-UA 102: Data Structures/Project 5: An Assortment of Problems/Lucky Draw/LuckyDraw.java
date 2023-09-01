//package project5;

import java.util.Scanner;
import java.util.Stack;

public class LuckyDraw {

    public static void main(String[] args) throws Exception {
        // interactive mode:

        Scanner userInput = new Scanner(System.in);
        if (userInput == null)
            throw new Exception("null point");
        int totalPeople = userInput.nextInt();
        int[] list = new int[totalPeople];
        if (list == null)
            throw new Exception("null point");

        userInput.nextLine();
        /**
         * String[] stringlist = userInput.nextLine().split(" ");
         * for (int j = 0; j < totalPeople; j++) {
         * list[j] = Integer.parseInt(stringlist[j]);
         * }
         */
        int j = 0;
        while (j<totalPeople&&userInput.hasNext()) {
            list[j] = userInput.nextInt();
            j++;
        }
        //userInput.nextLine();
        LuckyDraw luck = new LuckyDraw();
        luck.removeEven(list);
        userInput.close();
    }

    public void removeEven(int[] list) throws Exception {
        int reallength = list.length;
        if (reallength == 1) {
            System.out.print(reallength);
            return;
        }
        Stack<Integer> remainCandidate = new Stack<>();
        if (remainCandidate == null) {
            throw new Exception("null point");
        }
        remainCandidate.push(list[0]);
        for (int i = 1; i < reallength; i++) {
            if (remainCandidate.isEmpty()) {
                remainCandidate.push(list[i]);
            } else if ((remainCandidate.peek() + list[i]) % 2 == 0) {
                remainCandidate.pop();
            } else {
                remainCandidate.push(list[i]);
            }

        }
        System.out.print(remainCandidate.size());
    }
}