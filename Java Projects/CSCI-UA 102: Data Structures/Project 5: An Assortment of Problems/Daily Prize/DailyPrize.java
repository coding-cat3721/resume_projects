//package project5;

import java.math.BigInteger;
//import java.util.ArrayList;
import java.util.Collections;
//import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DailyPrize {
    public static void main(String[] args) throws Exception {
        // interactive mode:
        Scanner userInput = new Scanner(System.in);
        //ArrayList<Integer> pq = new ArrayList<>();
        PriorityQueue<Integer> pqmin = new PriorityQueue<>();
        PriorityQueue<Integer> pqmax = new PriorityQueue<>(10, Collections.reverseOrder());
        int day = Integer.parseInt(userInput.nextLine());
        BigInteger prize = new BigInteger(Integer.toString(0));

        for (int i = 0; i < day; i++) {
            int j = 0;
            int totalbills = userInput.nextInt();
            int bill = 0;
            while (j < totalbills && userInput.hasNext()) {
                bill = userInput.nextInt();
                pqmin.add(bill);
                pqmax.add(bill);
                j++;
            }

            if (pqmin.isEmpty()) {
                continue;
            } else if (pqmin.size() == 1) {
                BigInteger tmp1 = new BigInteger(Integer.toString(pqmin.poll()));
                prize = prize.add(tmp1);
                pqmax.poll();

            } else {
                //Collections.sort(pq);
                int min=pqmin.poll();
                int max=pqmax.poll();
                int tmp = max-min;
                BigInteger tmp1 = new BigInteger(Integer.toString(tmp));
                prize = prize.add(tmp1);
                pqmin.remove(max);
                pqmax.remove(min);
            }

        }
        System.out.println(prize);
        userInput.close();
    }

}
