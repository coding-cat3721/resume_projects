package edu.nyu.cs;

import java.util.Scanner;

public class TestDrive {

    /**
     * The main function is automatically called first in a Java program.
     * It should instantiate a Moped and contain the main logic of the program,
     * following the instructions.
     * 
     * @param args An array of any command-line arguments.
     */
    public int testAttibute = 9;

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        Moped m = new Moped();
        // create two drunkDrive Mopeds at random location.
        int randomStreet = 1 + (int) (Math.random() * 200);
        int randomAve = 1 + (int) (Math.random() * 10);
        Moped drunkDrive1 = new Moped(randomStreet, randomAve);
        randomStreet = 1 + (int) (Math.random() * 200);
        randomAve = 1 + (int) (Math.random() * 10);
        Moped drunkDrive2 = new Moped(randomStreet, randomAve);

        // solution
        System.out.println(
                "Thanks for jumping on the moped.  We're currently parked outside Dr. Rossinsky DDS's office at 10th St. and 5th Ave, facing South.  May I say your teeth look very clean.");
        System.out.println("What would you like to do?  At any time, say \"help\" for assistance.");
        System.out.print("drunkDrive1 ");
        drunkDrive1.printLocation();
        System.out.print("drunkDrive2 ");
        drunkDrive2.printLocation();
        // boolean keepGoing = true;
        String response = "";
        while (!response.equals("park")) {
            response = scn.nextLine();
            switch (response) {
                case "go left":
                    m.goLeft();
                    // two drunk Drives are doing random walk;
                    System.out.print("drunkDrive1 ");
                    drunkDrive1.randomWalk();
                    System.out.print("drunkDrive2 ");
                    drunkDrive2.randomWalk();
                    break;
                case "go right":
                    m.goRight();
                    // two drunk Drives are doing random walk;
                    System.out.print("drunkDrive1 ");
                    drunkDrive1.randomWalk();
                    System.out.print("drunkDrive2 ");
                    drunkDrive2.randomWalk();
                    break;
                case "straight on":
                    m.goStraight();
                    // two drunk Drives are doing random walk;
                    System.out.print("drunkDrive1 ");
                    drunkDrive1.randomWalk();
                    System.out.print("drunkDrive2 ");
                    drunkDrive2.randomWalk();
                    break;
                case "back up":
                    m.goBackwards();
                    // two drunk Drives are doing random walk;
                    System.out.print("drunkDrive1 ");
                    drunkDrive1.randomWalk();
                    System.out.print("drunkDrive2 ");
                    drunkDrive2.randomWalk();
                    break;
                case "fill it up":
                    m.fillGas();
                    break;
                case "how we doin'?":
                    m.printGasLevel();
                    break;
                case "park":
                    m.park();
                    break;
                case "help":
                    System.out.println(m.getHelp());
                    break;
                case "go to Xi'an Famous Foods":
                    m.goToXianFamousFoods();
                    break;
                default:
                    System.out
                            .println("I'm sorry, I don't understand that command. Type 'help' for a list of commands");
            }

            // check if drunk driver crash with user's Moped.
            if (m.getLocation()[0] == drunkDrive1.getLocation()[0]
                    && m.getLocation()[1] == drunkDrive1.getLocation()[1]) {
                System.out.println("drunkDrive1 crash with user's Moped");
                System.exit(1);
            }
            if (m.getLocation()[0] == drunkDrive2.getLocation()[0]
                    && m.getLocation()[1] == drunkDrive2.getLocation()[1]) {
                System.out.println("drunkDrive2 crash with user's Moped");
                System.exit(1);
            }
        }
        scn.close();
    }

}
