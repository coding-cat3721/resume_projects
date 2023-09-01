package project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

public class Unemployment {
  public static void insertionSort(double[] list) {
    int count = 0;
    for (int i = 1; i < list.length; i++) {
      // insert list[i] into a sorted sublist list[0..i-1] so that
      // list[0..i] is sorted.
      double currentElement = list[i];
      int k;
      for (k = i - 1; k >= 0 && list[k] > currentElement; k--) {
        list[k + 1] = list[k];
        count++;
        System.out.println("i= " + i + " k= " + k + " count= " + count);
      }

      // Insert the current element into list[k+1]
      list[k + 1] = currentElement;
    }
  }

  public static void selectionSort(double[] list) {
    int count = 0;
    for (int i = 0; i < list.length - 1; i++) {
      // Find the minimum in the list[i...list.length-1]
      double currentMin = list[i];
      int currentMinIndex = i;
      for (int j = i + 1; j < list.length; j++) {
        if (currentMin > list[j]) {
          currentMin = list[j];
          currentMinIndex = j;
        }
        count++;
        System.out.println("i= " + i + " j= " + j + " count= " + count);
      }
      // Swap list[i] with list[currentMinIndex] if necessary;
      if (currentMinIndex != i) {
        list[currentMinIndex] = list[i];
        list[i] = currentMin;
      }
    }
  }

  int foo(int x, int y) {
    if (x == 0)
      return 0;
    else {
      int tmp = x / 2;
      int val = foo(tmp, y);
      if (x % 2 == 0)
        return val + val;
      else
        return val + val + y;
    }
  }

  public static void main(String[] args) {

    Queue<String> tmp=new LinkedList();
tmp.add("a");
tmp.add("b");
tmp.add("c");
System.out.println(tmp.peek());
System.out.println(tmp.remove());
System.out.println(tmp.remove());
System.out.println(tmp.remove());
/** 
    // verify that the command line argument exists
    // if (args.length == 0) {
    // System.err.println("Usage Error: the program expects file name as an
    // argument.\n");
    // System.exit(1);
    // }

    // verify that command line argument contains a name of an existing file
    // File colorFile = new File(args[0]);
    File colorFile = new File(
        "C:\\5G_Migration_files_old_computer\\NYU_stern\\multivariable-regression\\HW6\\data\\hw6_data2020_2021_fistsixmonth_final.csv");
    if (!colorFile.exists()) {
      System.err.println("Error: the file " + colorFile.getAbsolutePath() + " does not exist.\n");
      System.exit(1);
    }
    if (!colorFile.canRead()) {
      System.err.println("Error: the file " + colorFile.getAbsolutePath() +
          " cannot be opened for reading.\n");
      System.exit(1);
    }

    // open the file for reading
    Scanner inColors = null;
    // Scanner inColors;

    try {
      inColors = new Scanner(colorFile);
    } catch (FileNotFoundException e) {
      System.err.println("Error: the file " + colorFile.getAbsolutePath() +
          " cannot be opened for reading.\n");
      System.exit(1);
    }

    // read the content of the file and save the data in a list of named colors
    // ColorList list = new ColorList();

    inColors.nextLine();// skip the first line
    int length = 22633;// data length
    String[] stateArray = inColors.nextLine().split(",");

    String[] state = new String[length];

    int[] lockdown = new int[length];
    int[] cases = new int[length];
    int[] death = new int[length];
    double[] vaccine = new double[length];
    int index = 0;
    System.out.println("state,lockdown,cases,deaths,vaccinned");
    while (inColors.hasNextLine()) {
      String[] line = inColors.nextLine().split(",");
      state[index] = line[0];
      lockdown[index] = Integer.parseInt(line[2]);
      cases[index] = Integer.parseInt(line[3]);
      death[index] = Integer.parseInt(line[4]);
      vaccine[index] = Double.parseDouble(line[5]);
      index++;
    }

    for (int i = 0; i < stateArray.length; i++) {
      int casesTmp = 0;
      int deathTmp = 0;
      double vaccineTmp = 0;
      int count = 0;
      int casesTmp1 = 0;
      int deathTmp1 = 0;
      double vaccineTmp1 = 0;
      int count1 = 0;
      for (int j = 0; j < length; j++) {
        //System.out.println(state[j]+","+stateArray[i]);
        if (state[j].equals(stateArray[i]))
          if (lockdown[j] == 1) {
            casesTmp = casesTmp + cases[j];
            deathTmp = deathTmp + death[j];
            vaccineTmp = vaccineTmp + vaccine[j];
            count++;
          } else if (lockdown[j] == 2) {
            casesTmp1 = casesTmp1 + cases[j];
            deathTmp1 = deathTmp1 + death[j];
            vaccineTmp1 = vaccineTmp1 + vaccine[j];
            count1++;
          }

      }
      if (count!=0)
      System.out
          .println(stateArray[i] + "," + 1 + "," + casesTmp / count + "," + deathTmp / count + "," + vaccineTmp / count);
      if(count1!=0)
          System.out.println(
        stateArray[i] + "," + 2 + "," + casesTmp1 / count1 + "," + deathTmp1 / count1 + "," + vaccineTmp1 / count1);

    }
*/
  }

}
