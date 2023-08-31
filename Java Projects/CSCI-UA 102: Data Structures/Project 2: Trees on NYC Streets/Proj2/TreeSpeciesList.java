package project2;

import java.util.ArrayList;

/**
 * TreeSpeciesList class is used to store a collection of tree species objects.
 * This class inherits all of its properties from an ArrayList<TreeSpecies>. It
 * adds tree species-specific functions that allow search by common species name
 * and by latin species name.
 * 
 * This class does not store tree species objects in any particular order.
 * 
 * @author Jessy Huang
 *
 */
public class TreeSpeciesList extends ArrayList<TreeSpecies> {

    /*
     * Method to returns a list of species whose common name contains keyword as a
     * substring.
     * The method should be case insensitive. It should throw and instance of
     * IllegalArgumentException exception if called with null argument. It should
     * return null if there are no TreeSpecies objects matching the keyword.
     * 
     * @param keyword
     * 
     * @return tree species list whoes common species name contain the keyword.
     */
    public TreeSpeciesList getByCommonName(String keyword) throws IllegalArgumentException {
        if (keyword == null)
            throw new IllegalArgumentException("null common name");
        TreeSpeciesList tmp = new TreeSpeciesList();

        for (TreeSpecies spc : this) {
            String commonName = spc.getCommonName();
            if (commonName.toLowerCase().contains(keyword.toLowerCase())) {
                tmp.add(spc);
            }
        }
        if (tmp.size() != 0)
            return tmp;
        else
            return null;
    };

    /*
     * method to returns a list of species whose Latin name contains keyword as a substring.
     * The method should be case insensitive. It should throw and instance of
     * IllegalArgumentException exception if called with null argument. It should
     * return null if there are no TreeSpecies objects matching the keyword.
     * @param keyword
     * @return the list of tree species whose latin name contain the keyword.
     */
    public TreeSpeciesList getByLatinName(String keyword) throws IllegalArgumentException{
        if (keyword == null)
            throw new IllegalArgumentException("null Latin name");
        TreeSpeciesList tmp = new TreeSpeciesList();

        for (TreeSpecies spc : this) {
            String latinName = spc.getLatinName();
            if (latinName.toLowerCase().contains(keyword.toLowerCase())) {
                tmp.add(spc);
            }
        }
        if (tmp.size() != 0)
            return tmp;
        else
            return null;
    }
}
