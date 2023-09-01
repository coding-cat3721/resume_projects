package edu.nyu.cs;

import java.util.Arrays;

import javax.lang.model.util.ElementScanner6;

/**
 * A virtual moped, roaming the streets of New York.
 * The signatures of a few methods are given and must be completed and used as
 * indicated.
 * Create as many additional properties or methods as you want, as long as the
 * given methods behave as indicated in the instructions.
 * Follow good object-oriented design, especially the principles of abstraction
 * (i.e. the black box metaphor) and encapsulation (i.e. methods and properties
 * belonging to specific objects), as we have learned them.
 * The rest is up to you.
 */
public class Moped {
    private String orientation;
    private int[] location = new int[2];
    private int gasLevel=20;// The moped's gas tank stores up to 1 gallon. It comes pre-filled.
                         // Driving the moped burns 1/20th of gallon per city block.
    private boolean forwardState=true;

    public Moped() {
        this.location[0] = 10;
        this.location[1] = 5;
        this.orientation = "south";
    }
    public Moped(int street, int ave)
    {
        this.location[0] = street;
        this.location[1] = ave;
        this.orientation = "south";
    }

    /**
     * Sets the orientation of the moped to a particular cardinal direction.
     * 
     * @param orientation A string representing which cardinal direction at which to
     *                    set the orientation of the moped. E.g. "north", "south",
     *                    "east", or "west".
     */
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    /**
     * Returns the current orientation of the moped, as a lowercase String.
     * E.g. "north", "south", "east", or "west".
     * 
     * @return The current orientation of the moped, as a lowercase String.
     */
    public String getOrientation() {
        return orientation; // placeholder only... delete this!
    }

    /**
     * Prints the current location, by default exactly following the format:
     * Now at 12th St. and 5th Ave, facing South.
     *
     * If the current location is associated with location-based advertising, this
     * method should print exactly following format:
     * Now at 12th St. and 4th Ave, facing West. Did you know The Strand has 18
     * Miles of new, used and rare books, and has been in business since 1927?
     * 
     * Note that the suffixes for the numbers must be correct: i.e. the "st" in
     * "1st", "nd" in "2nd", "rd" in "3rd", "th" in "4th", etc, must be correct.
     */
    public void printLocation() {
        String result = "";
        result = "Now at " + numberConversion(location[0]) + " St. and " + numberConversion(location[1])
                + " Ave, facing "
                + orientation;
        result = result + adverTisement(location);
        System.out.println(result);
    };

    public String numberConversion(int n) {
        String result = "";
        if (n == 1)
            result = "1st";
        else if (n == 2)
            result = "2nd";
        else
            result = n + "th";
        return result;
    }

    public String adverTisement(int[] location) {
        // at 79th St. and 8th Ave., the Moped should output an ad for the American
        // Museum of Natural History.
        // at 74th St. and 1st Ave., the Moped should output an ad for Memorial Sloan
        // Kettering.
        // at 56th St. and 3rd Ave., the Moped should output an ad for Tina's Cuban
        // Cuisine restaurant.
        // at 12th St. and 4th Ave., the Moped should output an ad for The Strand book
        // shop.
        if (location[0] == 79 && location[1] == 8)
            return "Discover the worlk of shark, did you know that the American Museum of Natural History has served the society for 150 years?";
        else if (location[0] == 74 && location[1] == 1)
            return "Did you know how patient are taken care at Memorial Sloan Kettering";
        else if (location[0] == 56 && location[1] == 3)
            return "Did you know how deliciouse is the food at Tina's Cuban Cuisine restaurant?";
        else if (location[0] == 12 && location[1] == 4)
            return "Did you know The Strand has 18 Miles of new, used and rare books, and has been in business since 1927?";
        else
            return "";
    }

    /**
     * Handles the command, `go left`.
     * Moves the moped one block to the left, and causes the moped to face the
     * appropriate new cardinal direction.
     * Consumes gas with each block moved, and doesn't move or turn unless there is
     * sufficient gas, as according to the instructions.
     * If attempting to drive off the map, the moped will turn but not move a block.
     * Turns-only consume no gas.
     * This method must not print anything.
     */
    public void goLeft() {
        if (gasLevel == 0) {
            System.out.println("We have run out of gas. Bye bye!");
            System.exit(0);
        }
        if (forwardState == true) {
            forwardGoLeft();
        } else {
            backwardGoLeft();
        }
        printLocation();
    }

    public void backwardGoLeft() {
        if (orientation.equals("north")) {
            orientation = "east";
            if (location[1] > 1) {
                location[1]++;
                gasLevel--;
            }
        } else if (orientation.equals("south")) {
            orientation = "west";

            if (location[1] < 10) {
                location[1]--;
                gasLevel--;
            }
        } else if (orientation.equals("east")) {
            orientation = "south";

            if (location[0] > 1) {
                location[0]++;
                gasLevel--;
            }
        } else if (orientation.equals("west")) {
            orientation = "north";
            if (location[0] < 200) {
                location[0]--;
                gasLevel--;
            }
        }
    }

    public void forwardGoLeft() {
        if (orientation.equals("north")) {
            orientation = "west";
            if (location[1] < 10) {
                location[1]++;
                gasLevel--;
            }
        } else if (orientation.equals("south")) {
            orientation = "east";
            if (location[1] > 1) {
                location[1]--;
                gasLevel--;
            }
        } else if (orientation.equals("east")) {
            orientation = "north";
            if (location[0] < 200) {
                location[0]++;
                gasLevel--;
            }
        } else if (orientation.equals("west")) {
            orientation = "south";
            if (location[0] > 1) {
                location[0]--;
                gasLevel--;
            }
        }
    }

    /**
     * Handles the command, `go right`.
     * Moves the moped one block to the right, and causes the moped to face the
     * appropriate new cardinal direction.
     * Consumes gas with each block moved, and doesn't move or turn unless there is
     * sufficient gas, as according to the instructions.
     * If attempting to drive off the map, the moped will turn but not move a block.
     * Turns-only consume no gas.
     * This method must not print anything.
     */
    public void goRight() {
        if (gasLevel == 0) {
            System.out.println("We have run out of gas. Bye bye!");
            System.exit(0);
        }
        if (forwardState == true) {
            forwardGoRight();
        } else {
            backwardGoRight();
        }
        printLocation();
    }

    public void backwardGoRight() {
        if (orientation.equals("north")) {
            orientation = "west";
            if (location[1] >1) {
                location[1]--;
                gasLevel--;
            }
        } else if (orientation.equals("south")) {
            orientation = "east";
            if (location[1] < 10) {
                location[1]++;
                gasLevel--;
            }
        } else if (orientation.equals("east")) {
            orientation = "north";
            if (location[0] >1) {
                location[0]--;
                gasLevel--;
            }
        } else if (orientation.equals("west")) {
            orientation = "south";
            if (location[0] < 200) {
                location[0]++;
                gasLevel--;
            }
        }
    }

    public void forwardGoRight() {
        if (orientation.equals("north")) {
            orientation = "east";
            if (location[1] > 1) {
                location[1]--;
                gasLevel--;
            }
        } else if (orientation.equals("south")) {
            orientation = "west";
            if (location[1] < 10) {
                location[1]++;
                gasLevel--;
            }
        } else if (orientation.equals("east")) {
            orientation = "south";
            if (location[0] > 1) {
                location[0]--;
                gasLevel--;
            }
        } else if (orientation.equals("west")) {
            orientation = "north";
            if (location[0] < 200) {
                location[0]++;
                gasLevel--;
            }
        }
    }

    /**
     * Handles the command,`straight on`.
     * Moves the moped one block straight ahead.
     * Consumes gas with each block moved, and doesn't move unless there is
     * sufficient gas, as according to the instructions.
     * This method must not print anything.
     */
    public void goStraight() {
        if (gasLevel == 0) {
            System.out.println("We have run out of gas. Bye bye!");
            System.exit(0);
        }

        this.forwardState = true;
        if (orientation.equals("north") && (location[0] < 200)) {
            location[0]++;
            gasLevel--;
        } else if (orientation.equals("south") && (location[0] > 1)) {
            location[0]--;
            gasLevel--;
        } else if (orientation.equals("east") && (location[1] > 1)) {
            location[1]--;
            gasLevel--;
        } else if (orientation.equals("west") && (location[1] < 10)) {
            location[1]++;
            gasLevel--;
        }
        printLocation();
    }

    /**
     * Handles the command,`back up`.
     * Moves the moped one block backwards, but does not change the cardinal
     * direction the moped is facing.
     * Consumes gas with each block moved, and doesn't move unless there is
     * sufficient gas, as according to the instructions.
     * This method must not print anything.
     */
    public void goBackwards() {
        if (gasLevel == 0) {
            System.out.println("We have run out of gas. Bye bye!");
            System.exit(0);
        }
        this.forwardState = false;
        if (orientation.equals("north") && (location[0] > 1)) {
            location[0]--;
            gasLevel--;
        } else if (orientation.equals("south") && (location[0] < 200)) {
            location[0]++;
            gasLevel--;
        } else if (orientation.equals("east") && (location[1] < 10)) {
            location[1]++;
            gasLevel--;
        } else if (orientation.equals("west") && (location[1] > 1)) {
            location[1]--;
            gasLevel--;
        }
        printLocation();
    }

    /**
     * This method is to make drunk driver do random walk. Fill up gas if empty.
     */
    public void randomWalk() {
        if (getGasLevel() == 0) {
            fillGas();
        }
        int randomNum = 1 + (int) (Math.random() * 4);
        switch (randomNum) {
            case 1:

                System.out.print("go Left, ");
                this.goLeft();
                break;
            case 2:
                System.out.print("go Right, ");
                this.goRight();
                break;
            case 3:
                System.out.print("go Straight, ");
                this.goStraight();
                break;
            case 4:
                System.out.print("go backwards, ");
                this.goBackwards();

            default:

        }
    }

    /**
     * Handles the command,`how we doin'?`.
     * This method must not print anything.
     * 
     * @return The current gas level, as an integer from 0 to 100.
     */
    public int getGasLevel() {
        return gasLevel * 100 / 20; // placeholder only... delete this!
    }

    /**
     * Prints the current gas level, by default exactly following the format:
     * The gas tank is currently 85% full.
     *
     * If the moped is out of gas, this method should print exactly following
     * format:
     * We have run out of gas. Bye bye!
     */
    public void printGasLevel() {
        if (gasLevel == 0)
            System.out.println("We have run out of gas.  Bye bye!");
        else {
            int gasPercentage = gasLevel * 100 / 20;
            System.out.printf("The gas tank is currently %d%% full.", gasPercentage);
        }
    }

    /**
     * Handles the command, `fill it up`.
     * This method must not print anything.
     * Fills the gas level to the maximum.
     */
    public void fillGas() {
        gasLevel = 20;
    }

    /**
     * Handles the command, `park`.
     * This causes the program to quit.
     * You can use System.exit(0); to cause a program to quit with status code 0,
     * which indicates a normal graceful exit.
     * (In case you were wondering, status code 1 represents quitting as a result of
     * an error of some kind).
     */
    public void park() {
        System.exit(0);
    }

    /**
     * Handles the command, `go to Xi'an Famous Foods`
     * Causes the moped to self-drive, block-by-block, to 8th Ave. and 15th St.
     * Consumes gas with each block, and doesn't move unless there is sufficient
     * gas, as according to the instructions.
     */
    public void goToXianFamousFoods() {

        if (forwardState == false) {
            goStraight();
        }
        int totalCrossStreet = location[0] - 15;
        int totalCrossAve = location[1] - 8;

        if (location[0] == 15 && totalCrossAve > 0) {
            if (orientation.equals("north")) {
                goRight();
                for (int i = 0; i < totalCrossAve - 1; i++) {
                    goStraight();
                }
            } else if (orientation.equals("south")) {
                goLeft();
                for (int i = 0; i < totalCrossAve - 1; i++) {
                    goStraight();
                }
            } else if (orientation.equals("east")) {
                for (int i = 0; i < totalCrossAve; i++) {
                    goStraight();
                }
            } else if (orientation.equals("west")) {
                for (int i = 0; i < totalCrossAve; i++) {
                    goBackwards();
                }
            }

        } else if (location[0] == 15 && totalCrossAve < 0) {
            if (orientation.equals("north")) {
                goLeft();
                for (int i = 0; i < -totalCrossAve - 1; i++) {
                    goStraight();
                }
            } else if (orientation.equals("south")) {
                goRight();
                for (int i = 0; i < -totalCrossAve - 1; i++) {
                    goStraight();
                }
            } else if (orientation.equals("west")) {
                for (int i = 0; i < -totalCrossAve; i++) {
                    goStraight();
                }
            } else if (orientation.equals("east")) {
                for (int i = 0; i < -totalCrossAve; i++) {
                    goBackwards();
                }
            }
        } else if (location[1] == 8 && totalCrossStreet > 0) {
            if (orientation.equals("east")) {
                goRight();
                for (int i = 0; i < totalCrossStreet - 1; i++) {
                    goStraight();
                }
            } else if (orientation.equals("west")) {
                goLeft();
                for (int i = 0; i < totalCrossStreet - 1; i++) {
                    goStraight();
                }
            } else if (orientation.equals("south")) {
                for (int i = 0; i < totalCrossStreet; i++) {
                    goStraight();
                }
            } else if (orientation.equals("north")) {
                for (int i = 0; i < totalCrossStreet; i++) {
                    goBackwards();
                }
            }
        } else if (location[1] == 8 && totalCrossStreet < 0) {
            if (orientation.equals("west")) {
                goRight();
                for (int i = 0; i < totalCrossStreet - 1; i++) {
                    goStraight();
                }
            } else if (orientation.equals("east")) {
                goLeft();
                for (int i = 0; i < totalCrossStreet - 1; i++) {
                    goStraight();
                }
            } else if (orientation.equals("north")) {
                for (int i = 0; i < -totalCrossStreet; i++) {
                    goStraight();
                }
            } else if (orientation.equals("south")) {
                for (int i = 0; i < -totalCrossStreet; i++) {
                    goBackwards();
                }
            }
        } else if (location[0] > 15 && location[1] > 8) {
            if (orientation.equals("south")) {
                for (int i = 0; i < totalCrossStreet; i++) {
                    goStraight();
                }
                goLeft();
                for (int i = 0; i < totalCrossAve - 1; i++) {
                    goStraight();
                }
            } else if (orientation.equals("east")) {
                for (int i = 0; i < totalCrossAve; i++) {
                    goStraight();
                }
                goRight();
                for (int i = 0; i < totalCrossStreet - 1; i++) {
                    goStraight();
                }

            } else if (orientation.equals("north")) {
                goRight();
                for (int i = 0; i < totalCrossAve - 1; i++) {
                    goStraight();
                }
                goRight();
                for (int i = 0; i < totalCrossStreet - 1; i++) {
                    goStraight();
                }
            } else if (orientation.equals("west")) {
                goLeft();
                for (int i = 0; i < totalCrossStreet - 1; i++) {
                    goStraight();
                }
                goLeft();
                for (int i = 0; i < totalCrossAve - 1; i++) {
                    goStraight();
                }

            }
        } else if (location[0] > 15 && location[1] < 8) {
            if (orientation.equals("south")) {
                for (int i = 0; i < totalCrossStreet; i++) {
                    goStraight();
                }
                goRight();
                for (int i = 0; i < -totalCrossAve - 1; i++) {
                    goStraight();
                }
            } else if (orientation.equals("west")) {
                for (int i = 0; i < -totalCrossAve; i++) {
                    goStraight();
                }
                goLeft();
                for (int i = 0; i < totalCrossStreet - 1; i++) {
                    goStraight();
                }

            } else if (orientation.equals("north")) {
                goLeft();
                for (int i = 0; i < -totalCrossAve - 1; i++) {
                    goStraight();
                }
                goLeft();
                for (int i = 0; i < totalCrossStreet - 1; i++) {
                    goStraight();
                }
            } else if (orientation.equals("east")) {
                goRight();
                for (int i = 0; i < totalCrossStreet - 1; i++) {
                    goStraight();
                }
                goRight();
                for (int i = 0; i < -totalCrossAve - 1; i++) {
                    goStraight();
                }

            }
        } else if (location[0] < 15 && location[1] < 8) {
            if (orientation.equals("north")) {
                for (int i = 0; i < -totalCrossStreet; i++) {
                    goStraight();
                }
                goLeft();
                for (int i = 0; i < -totalCrossAve - 1; i++) {
                    goStraight();
                }
            } else if (orientation.equals("west")) {
                for (int i = 0; i < -totalCrossAve; i++) {
                    goStraight();
                }
                goRight();
                for (int i = 0; i < -totalCrossStreet - 1; i++) {
                    goStraight();
                }

            } else if (orientation.equals("east")) {
                goLeft();
                for (int i = 0; i < -totalCrossStreet - 1; i++) {
                    goStraight();
                }
                goLeft();
                for (int i = 0; i < -totalCrossAve - 1; i++) {
                    goStraight();
                }
            } else if (orientation.equals("south")) {
                goRight();
                for (int i = 0; i < -totalCrossAve - 1; i++) {
                    goStraight();
                }
                goRight();
                for (int i = 0; i < -totalCrossStreet - 1; i++) {
                    goStraight();
                }

            }
        } else if (location[0] < 15 && location[1] > 8) {
            if (orientation.equals("north")) {
                for (int i = 0; i < -totalCrossStreet; i++) {
                    goStraight();
                }
                goRight();
                for (int i = 0; i < totalCrossAve - 1; i++) {
                    goStraight();
                }
            } else if (orientation.equals("east")) {
                for (int i = 0; i < totalCrossAve; i++) {
                    goStraight();
                }
                goLeft();
                for (int i = 0; i < -totalCrossStreet - 1; i++) {
                    goStraight();
                }

            } else if (orientation.equals("west")) {
                goRight();
                for (int i = 0; i < -totalCrossStreet - 1; i++) {
                    goStraight();
                }
                goRight();
                for (int i = 0; i < totalCrossAve - 1; i++) {
                    goStraight();
                }
            } else if (orientation.equals("south")) {
                goLeft();
                for (int i = 0; i < totalCrossAve - 1; i++) {
                    goStraight();
                }
                goLeft();
                for (int i = 0; i < -totalCrossStreet - 1; i++) {
                    goStraight();
                }

            }
        }
        System.out.println(
                "We have reached Xi'an Famous Foods.  Enjoy your noodles.");
    }

    /**
     * Generates a string, containing a list of all the user commands that the
     * program understands.
     * 
     * @return String containing commands that the user can type to control the
     *         moped.
     */
    public String getHelp() {
        String result = "The program accepts the following commands from the user:"
                + "\ngo left"
                + "\ngo right"
                + "\nstraight on"
                + "\nback up"
                + "\nhow we doin'?"
                + "\nfill it up"
                + "\npark"
                + "\ngo to Xi'an Famous Foods"
                + "\nhelp";
        return result; // placeholder only... delete this!
    }

    /**
     * Sets the current location of the moped.
     * 
     * @param location an int array containing the new location at which to place
     *                 the moped, in the order {street, avenue}.
     */
    public void setLocation(int[] location) {
        this.location[0] = location[0];
        this.location[1] = location[1];
    }

    /**
     * Gets the current location of the moped.
     * 
     * @return The current location of the moped, as an int array in the order
     *         {street, avenue}.
     */
    public int[] getLocation() {
        // the following two lines are placeholder... delete them and return this
        // moped's correct coordinates.
        // int[] location = {3, 4}; // an example array at 3rd st and 4th Ave....
        // placeholder only... delete this!
        return location;
    }

}
