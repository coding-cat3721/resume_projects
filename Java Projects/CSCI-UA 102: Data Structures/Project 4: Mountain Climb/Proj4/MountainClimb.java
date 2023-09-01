package project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * 
 *  
 * This class is the program for a Hiker to perform path search.
 *   
 * This is the class that is the program. This means it has the main method. This class is responsible 
 * for parsing and validating the command line arguments, reading and parsing the input file, producing 
 * any error messages, handling any exceptions thrown by other classes, and producing output.
 * 
 * When the program is executed the name of the input file containing the list of all the named 
 * rest stops is provided as the program's single command line argument. The data in this file 
 * serves as a database of all the named restStops. 
 * 
 * @author Jessy Huang
 */
public class MountainClimb {
    /**
     * The main() method of this program.
     * 
     * @param args array of Strings provided on the command line when the program is
     *             started; the first string should be the name of the input file
     *             containing
     *             the list of named RestStop.
     */
    public static void main(String[] args) {

        // verify that the command line argument exists
            if (args.length == 0) {
                System.err.println("Usage Error: the program expects file name as an argument.\n");
           
                 System.exit(1);
            }
       
        // verify that command line argument contains a name of an existing file
        File mountainFile = new File(args[0]);
        
            if (!mountainFile.exists()) {
                 System.err.println("Error: the file " + mountainFile.getAbsolutePath() + " does not exist.\n");
                 System.exit(1);
            } 
        
            if (!mountainFile.canRead()) { 
                 System.err.println("Error: the file " + mountainFile.getAbsolutePath() +
                 " cannot be opened for reading.\n");
                 System.exit(1);
            }
        // open the file for reading
        Scanner inMountain = null;

        try {
            inMountain = new Scanner(mountainFile);
        } catch (FileNotFoundException e) {
            System.err.println("Error: the file " + mountainFile.getAbsolutePath() +
                    " cannot be opened for reading.\n");
            System.exit(1);
        }

        // read the content of the file and save the data in a list of named
        // restStopList
        RestStop[] restStopList = new RestStop[50];
        String line = null;
        Scanner parseLine = null;
        String lable = null;
        RestStop current = null;
        String supplysample = "food raft axe";
        String obsticalsample = "river fallen tree";
        String tmp;
        int i = 0;
        int j = 0;
        int k = 0;
        // while loop to read the content of the input file line by line.
        while (inMountain.hasNextLine()) {
            String[] supply = new String[5];
            String[] obstical = new String[5];
            try {
                i = 0;
                j = 0;

                line = inMountain.nextLine();
                parseLine = new Scanner(line);
                parseLine.useDelimiter(" ");
                lable = parseLine.next();// read the name of the restStop.
                boolean doneWithSupply = false;
                boolean doneWithObstical = false;
                if (lable.isEmpty())
                    continue;
                while (parseLine.hasNext()) {
                    tmp = parseLine.next();
                    if (supplysample.contains(tmp)) {
                        if (doneWithSupply == false) {
                            supply[i] = tmp;// save the supplies in the restStop
                            i++;
                        } else {
                            doneWithObstical = true;
                        }
                    } else if (obsticalsample.contains(tmp) && doneWithObstical == false) {// save the obsticales in the
                                                                                           // restStop.
                        doneWithSupply = true;
                        if (tmp.equals("river")) {
                            obstical[j] = tmp;
                            j++;
                        }
                        if (tmp.equals("fallen")) {
                            tmp = tmp + " " + parseLine.next();
                            obstical[j] = tmp;
                            j++;
                        }
                    }
                }

            } catch (NoSuchElementException ex) {
                // caused by an incomplete or miss-formatted line in the input file
                System.err.println(line);
                continue;
            }
            try {
                // insert new restStop object in the list.
                current = new RestStop(lable, supply, obstical);
                restStopList[k] = current;
                k++;
            } catch (IllegalArgumentException ex) {
                // ignore this exception and skip to the next line
            }
        }
        // hiker to search path in the BSTMountain.
        Hiker hiker = new Hiker();
        BSTMountain mountain = new BSTMountain(restStopList);
        mountain.searchPath(hiker);

    }

}
