package project2;

/*
 * This class represents a tree species. 
 * This class represent a single tree species that has both the common name and the Latin name of the 
 * species.
 * @author Jessy Huang
 */
public class TreeSpecies {
    private String commonName;
    private String latinName;

    /*
     * two parameter constructor, Both names are, possibly empty, strings. They
     * cannot be null. If this constructor is called with a null argument, it should
     * throw and instance of IllegalArgumentException with an appropriate error
     * message.
     * 
     */
    public TreeSpecies(String commonName, String latinName) throws IllegalArgumentException {
        if (commonName == null || latinName == null)
            throw new IllegalArgumentException("Null common Name or Latin name");
        else {
            this.commonName = commonName;
            this.latinName = latinName;
        }
    }

// this is the getter of common species name
// @return the common species name
    public String getCommonName() {
        return commonName;
    };

// this is the getter of latin species name
// @return the latin species name
    public String getLatinName() {
        return latinName;
    };

    /*
     * This is to override the equals method. Two TreeSpecies objects are
     * equal if both the names are the same. The comparison should be case
     * insensitive.
     * @param obj 
     * @return boolean, true if equal, false if not equal.
     */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof TreeSpecies))
            return false;
        TreeSpecies other = (TreeSpecies) obj;
        if (!commonName.equalsIgnoreCase(other.commonName))
            return false;
        if (!latinName.equalsIgnoreCase(other.latinName))
            return false;
        return true;
    }
}
