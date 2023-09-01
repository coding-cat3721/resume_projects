//package project5;

import java.math.BigInteger;
import java.util.PriorityQueue;
import java.util.Scanner;

public class EfficientAdding {
    private PriorityQueue<BigInteger> pq = new PriorityQueue<>();
    // private PriorityQueue<Integer> queue=new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        PriorityQueue<Integer> pqNew = new PriorityQueue<>();
        // interactive mode:
        Scanner userInput = new Scanner(System.in);

        int numElement = userInput.nextInt();
        userInput.nextLine();

        String[] numsString = userInput.nextLine().split(" ");
        int[] nums = new int[numElement];
        for (int j = 0; j < numElement; j++) {
            // nums[j] = ;
            pqNew.add(Integer.parseInt(numsString[j]));
            nums[j] = Integer.parseInt(numsString[j]);
        }

        int firstTwosum = pqNew.poll() + pqNew.poll();
        if (numElement == 2) {
            System.out.println(firstTwosum);
            return;
        }
        BigInteger cost = new BigInteger(Integer.toString(firstTwosum));
        while (!pqNew.isEmpty()) {
            if (firstTwosum <= pqNew.peek()) {
                firstTwosum = firstTwosum + pqNew.poll();
                BigInteger tmp = new BigInteger(Integer.toString(firstTwosum));
                cost = cost.add(tmp);
            } else {
                pqNew.add(firstTwosum);
                firstTwosum = pqNew.poll() + pqNew.poll();
                BigInteger tmp = new BigInteger(Integer.toString(firstTwosum));
                cost = cost.add(tmp);
            }

        }

        System.out.println(cost);
      //  EfficientAdding effAdd = new EfficientAdding();
      //  effAdd.findCostRec(nums, BigInteger.valueOf(0), "");
      //  System.out.println("recerv:  " + effAdd.pq.poll());
        userInput.close();

    }

    /**
     * public void findCostRecNewAlgorithm(int[] nums, BigInteger cost)
     * {
     * 
     * int length = nums.length;
     * if (length == 2) {
     * int tmp = nums[0] + nums[1];
     * // BigInteger newCost = cost.add() + nums[0] + nums[1];
     * BigInteger newCost = cost.add(BigInteger.valueOf(tmp));
     * pq.add(newCost);
     * return;
     * }
     * 
     * for (int i = 0; i < length; i++) {
     * if (length%2==0){
     * 
     * }
     * int newNum = nums[i] + nums[j];
     * int[] newNums = new int[length/2];
     * System.arraycopy(nums, 0, newNums, 0, j);
     * System.arraycopy(nums, j + 1, newNums, j, length - 1 - j);
     * newNums[i] = newNum;
     * BigInteger newcost = cost.add(BigInteger.valueOf(newNum));
     * if (pq.isEmpty() || (!pq.isEmpty() && newcost.compareTo(pq.peek()) < 0)) {
     * findCostRec(newNums, newcost, "");
     * }
     * 
     * }
     * 
     * }
     */
    public void findCostRec(int[] nums, BigInteger cost, String path) {

        int length = nums.length;
        if (length == 2) {
            int tmp = nums[0] + nums[1];
            // BigInteger newCost = cost.add() + nums[0] + nums[1];
            BigInteger newCost = cost.add(BigInteger.valueOf(tmp));
            pq.add(newCost);
            // System.out.println(newCost+"cost with path "+
            // path+""+nums[0]+"+"+nums[1]+"!");
            return;
        }
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                int newNum = nums[i] + nums[j];
                int[] newNums = new int[length - 1];
                System.arraycopy(nums, 0, newNums, 0, j);
                System.arraycopy(nums, j + 1, newNums, j, length - 1 - j);
                newNums[i] = newNum;
                BigInteger newcost = cost.add(BigInteger.valueOf(newNum));
                String pathNew = path + nums[i] + "+" + nums[j] + "!";
                if (pq.isEmpty() || (!pq.isEmpty() && newcost.compareTo(pq.peek()) < 0)) {
                    findCostRec(newNums, newcost, pathNew);
                }
            }

        }

    }

}
