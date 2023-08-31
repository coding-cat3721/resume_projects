package project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.lang.Double;

/**
 * This class is the program performing New York STREET TREE CENSUS.
 * The program is interactive. When the program is executed the name of the
 * input file containing the list of all the tree information is provided as the
 * program's single command line argument. The data in this file serves as a database 
 * of all the trees in New York.
 * 
 * In the interactive part, the user enters a string representation of a
 * tree species name or part of it. The program responds by printing the list of
 * tree species names which contains the search string,
 * and the number of trees of these species, total trees, percentage on
 * different New York City boroughs..
 * 
 * 
 * @author Jessy Huang
 *
 */
public class NYCStreetTrees {
    // The main() method of this program.
    // @param args array of Strings provided on the command line when the program is
    // started;
    // the first string should be the name of the input file containing the list
    // of named Trees.

    public static void main(String[] args) {

        // verify that the command line argument exists
        if (args.length == 0) {
            System.err.println("Usage Error: the program expects file name as an argument.\n");
            System.exit(1);
        }

        // verify that command line argument contains a name of an existing file
        File treeFile = new File(args[0]);
        if (!treeFile.exists()) {
            System.err.println("Error: the file " + treeFile.getAbsolutePath() + " does not exist.\n");
            System.exit(1);
        }
        if (!treeFile.canRead()) {
            System.err.println("Error: the file " + treeFile.getAbsolutePath() +
                    " cannot be opened for reading.\n");
            System.exit(1);
        }

        // open the file for reading
        Scanner inTrees = null;
        // Scanner inTrees;

        try {
            inTrees = new Scanner(treeFile);
        } catch (FileNotFoundException e) {
            System.err.println("Error: the file " + treeFile.getAbsolutePath() +
                    " cannot be opened for reading.\n");
            System.exit(1);
        }

        // read the content of the file and save the data in a list of named Trees
        CSV csv = new CSV(inTrees);
        TreeList list = new TreeList();
        TreeSpeciesList speciesList = new TreeSpeciesList();
        Tree tmpTree;
        TreeSpecies tmpTreeSpecies;
        int tree_id = 0;
        String status = "";
        String health = "";
        String spc_latin;
        String spc_common;
        int zipcode = 0;
        String boroname = "";
        double x_sp = 0;
        double y_sp = 0;
        ArrayList<String> line = null;

        ArrayList<String> boronameList = new ArrayList();
        boronameList.add("Manhattan");
        boronameList.add("Bronx");
        boronameList.add("Brooklyn");
        boronameList.add("Queens");
        boronameList.add("Staten Island");

        line = csv.getNextRow();// skip the first line which is title.

        // skip lines if no valid tree id or not valid species name
        for (int i = 1; i < csv.getNumOfRows(); i++) {
            try {
                line = csv.getNextRow();
                tree_id = Integer.parseInt(line.get(0).trim());
                spc_common = line.get(9).trim();
                spc_latin = line.get(8).trim();
                if (tree_id < 0 || spc_common.isEmpty() || spc_common == null || spc_latin == null)
                    continue;
                // build species name list
                tmpTreeSpecies = new TreeSpecies(spc_common, spc_latin);
                if ((!spc_common.isEmpty() && speciesList.getByCommonName(spc_common) == null)) {
                    speciesList.add(tmpTreeSpecies);
                }
            } catch (NoSuchElementException ex) {
                System.err.println("invalid tree ID or species or both" + line);
                continue;
            } catch (Exception ex) {
                System.err.println("invalid tree ID or species or both" + line);
                continue;
            }

            try {

                boroname = line.get(29).trim();
                if (boroname.isEmpty() || boroname == null)
                    continue;
                status = line.get(6).trim();
                health = line.get(7).trim();
                zipcode = Integer.parseInt(line.get(25).trim());
                x_sp = Double.parseDouble(line.get(39).trim());
                y_sp = Double.parseDouble(line.get(40).trim());
            } catch (NoSuchElementException ex) {
                // caused by an incomplete or miss-formatted line in the input file
                System.err.println("invalid tree field");
                // ignor the exception.
            } catch (Exception ex) {
                System.err.println("invalid tree field");
                // ignor the exception.
            }
            try {
                tmpTree = new Tree(tree_id, tmpTreeSpecies);
            } catch (IllegalArgumentException ex) {
                // ignore this exception and skip to the next line
                System.err.println("invalid Tree Augument");
                continue;
            }

            try {
                tmpTree.setStatus(status);
                tmpTree.setHealth(health);
                tmpTree.setZipcode(zipcode);
                tmpTree.setBoroname(boroname);
                tmpTree.setX_sp(x_sp);
                tmpTree.setY_sp(y_sp);

            } catch (Exception e) {
                System.err.println("invalid other tree Augument");
                // ignore the exception.
            }
            list.add(tmpTree);

        }

        // interactive mode:
        Scanner userInput = new Scanner(System.in);
        String userValue = "";

        do {
            System.out.println("Enter the tree species to learn more about it or \"quit\" to stop:");
            // get value of from the user
            userValue = userInput.nextLine();
            if (!userValue.equalsIgnoreCase("quit")) {
                TreeSpeciesList t1 = speciesList.getByCommonName(userValue);
                TreeSpeciesList t2 = speciesList.getByLatinName(userValue);

                // the list of unique tree species which contain the search string.
                TreeSpeciesList t = new TreeSpeciesList();
                // the list of common and latin species name which contains the search string.
                ArrayList<String> searchSpeciesResult = new ArrayList<>();

                //create the list of unique tree species which contain the search string.
                //create the list of common and latin species name which contains the search string.
                if (t1 != null) {
                    for (TreeSpecies s : t1) {
                        searchSpeciesResult.add(s.getCommonName());
                    }
                    t = t1;
                }
                if (t2 != null) {
                    for (TreeSpecies s : t2) {
                        searchSpeciesResult.add(s.getLatinName());
                        if (!t.contains(s))
                            t.add(s);
                    }
                }
                if (searchSpeciesResult.isEmpty()) {
                    System.out.println("Tere are no records of " + userValue + " on NYC streets");
                    continue;
                }

                //sort the search result, and display matching species.
                Collections.sort(searchSpeciesResult);
                System.out.println("All matching species:");
                for (String s : searchSpeciesResult) {
                    System.out.println("    " + s);
                }
                System.out.println("Popularity in the city:");
                int totalSpeciesCount = 0;
                int totalTree = 0;
                String output = "";

                //calculate the number of trees of the matching species, the total tree, percentage 
                //for each boroughs, and for NYC as a whole.
                for (String b : boronameList) {
                    int totalTreeBoron = list.getCountByBorough(b);
                    int speciesCount = 0;
                    for (TreeSpecies s : t) {
                        speciesCount += list.getCountByCommonNameBorough(s.getCommonName(), b);
                    }
                    double percentage = ((double) (speciesCount) / totalTreeBoron) * 100;
                    if (speciesCount == 0)
                        percentage = 0;
                    output = output
                            + String.format("    %-15s: %,10d (%,10d) %10.2f %% %n", b, speciesCount, totalTreeBoron,
                                    percentage);
                    totalSpeciesCount = totalSpeciesCount + speciesCount;
                    totalTree = totalTree + totalTreeBoron;
                }
                double totalPercentage = ((double) (totalSpeciesCount) / totalTree) * 100;
                System.out.printf("    %-15s: %,10d (%,10d) %10.2f %% %n", "NYC", totalSpeciesCount, totalTree,
                        totalPercentage);
                System.out.print(output);
            }
        } while (!userValue.equalsIgnoreCase("quit"));
        userInput.close();
    }
}
