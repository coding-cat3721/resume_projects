//package project5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class FindSums {

    private boolean isPath = false;
    private int target;
    ArrayList<String> allPath = new ArrayList<String>();

    FindSums(int target) {
        this.target = target;
    }

    public static void main(String[] args) throws Exception {
        // interactive mode:
        Scanner userInput = new Scanner(System.in);
        FindSums findsum = new FindSums(userInput.nextInt());
        int numOfnumber = userInput.nextInt();
        userInput.nextLine();

        String line = userInput.nextLine();
        String[] numsString = line.split(" ");
        int[] nums = new int[numOfnumber];

        for (int j = 0; j < numOfnumber; j++) {
            nums[j] = Integer.parseInt(numsString[j]);
        }
        Integer[] path = new Integer[numOfnumber];
        Arrays.fill(path, 0);

        System.out.println("Sums of " + findsum.target + ":");
        // FindSums findsum = new FindSums();
        findsum.groupSum(0, nums, findsum.target, path);

        Collections.sort(findsum.allPath, Collections.reverseOrder());
        if (findsum.isPath == false) {
            System.out.println("NONE");
        } else {
            for (String s : findsum.allPath)
                System.out.println(s);
        }
        userInput.close();

    }

    public void groupSum(int start, int[] nums, int target, Integer[] path) {
        if (target == 0) {
            String tmp = printPath(path);
            if (!allPath.contains(tmp))
                allPath.add(tmp);
            isPath = true;
            return;
        } else if (target < 0 || start >= nums.length) {
            return;
        } else {
            Integer[] newpath1 = new Integer[path.length];
            Integer[] newpath2 = new Integer[path.length];
            Arrays.fill(newpath1, 0);
            Arrays.fill(newpath2, 0);
            int length = 0;
            for (int i = 0; i < path.length; i++) {
                newpath1[i] = path[i];
                newpath2[i] = path[i];
                if (path[i] != 0)
                    length++;
            }
            newpath1[length] = nums[start];
            groupSum(start + 1, nums, (target - nums[start]), newpath1);
            groupSum(start + 1, nums, target, newpath2);
        }
    }

    public String printPath(Integer[] path) {
        Arrays.sort(path, Collections.reverseOrder());
        String pathset = "" + path[0];
        for (int i = 1; i < path.length; i++) {
            if (path[i] != 0)
                pathset = pathset + "+" + path[i];
        }
        return pathset.trim();
    }
}
