package edu.nyu.cs;

import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;

/**
 * A variation of the game of Blackjack.
 * Complete this program according to the instructions in the README.md file as
 * well as within the given comments below.
 */
public class Blackjack {

  /**
   * The main function is automatically called first in a Java program.
   * 
   * @param args An array of any command-line arguments.
   */
  public static void main(String[] args) throws Exception {

    // complete this function according to the instructions
    Scanner input = new Scanner(System.in);
    boolean isend = false;
    String result = null;
    int user[];
    int dealer[];
    user = new int[21];
    dealer = new int[21];
    user[0] = 2 + (int) (Math.random() * 10);
    user[1] = 2 + (int) (Math.random() * 10);
    dealer[0] = 2 + (int) (Math.random() * 10);
    dealer[1] = 2 + (int) (Math.random() * 10);
    int i = 1;
    int j = 1;
    System.out.println("Welcome to Blackjack!");
    System.out.println("Your cards are: " + user[0] + " and " + user[1]);
    int totalUser = user[0] + user[1];
    int totalDealer = dealer[0] + dealer[1];
    do {
      System.out.print("Would you like to hit or stand?");
      result = input.nextLine();

      if (result.equalsIgnoreCase("hit")) {
        i++;
        user[i] = 2 + (int) (Math.random() * 10);
        totalUser = totalUser + user[i];
        printCard(user, i, true);
        if (totalUser >= 21) {
          isend = true;
          // printCard(user, i, true);
          System.out.print("You have bust!");
          System.out.print("\nDealer wins!");
          return;
        }
      }
      if (result.equalsIgnoreCase("stand")) {
        isend = true;
      }

    } while (!isend);
    isend = false;
    input.close();
    int dealerInput;
    do {
      dealerInput = (int) (Math.random() * 10);
      if (dealerInput % 2 == 0) {
        System.out.print("\nThe dealer hits.");
        j++;
        dealer[j] = 2 + (int) (Math.random() * 10);
        totalDealer = totalDealer + dealer[j];
        if (totalDealer >= 21) {
          isend = true;
          printCard(user, i, true);
          printCard(dealer, j, false);
          System.out.println("The dealer has bust!");
          System.out.println("You wins!");
        }
      } else {
        isend = true;
        System.out.print("\nThe dealer stands.");
        printCard(user, i, true);
        printCard(dealer, j, false);
        if (totalUser > totalDealer)
          System.out.println("You wins!");
        if (totalUser < totalDealer)
          System.out.println("Dealer wins!");
        if (totalUser == totalDealer)
          System.out.println("Tie!");

      }

    } while (!isend);

  };// main

  static void printCard(int data[], int length, boolean isUser) {
    if (isUser) {
      System.out.print("\nYour cards are: ");

      for (int i = 0; i <= length; i++) {
        if (i == 0) {
          System.out.printf("%d", data[i]);
        } else if (i == length && length == 1) {
          System.out.printf(" and %d\n", data[i]);
        } else if (i < length) {
          System.out.printf(", %d", data[i]);
        } else {
          System.out.printf(", and %d\n", data[i]);
        }

      }
    }

    if (!isUser) {
      System.out.print("The dealer's cards are: ");
      for (int i = 0; i <= length; i++) {
        if (i == 0) {
          System.out.printf("%d", data[i]);
        } else if (i < length) {
          System.out.printf(", %d", data[i]);
        } else {
          System.out.printf(", %d\n", data[i]);
        }

      }
    }
  }// printCard

};
