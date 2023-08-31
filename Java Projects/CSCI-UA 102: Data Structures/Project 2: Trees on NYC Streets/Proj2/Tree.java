package project2;

/*
 * The Tree class stores information about a particular tree that grows in New York City. 
 * The class should store only a subset of the entries from the input file, namely:
 * tree_id as a non-negative integer
 * status as a string, valid values: "Alive", "Dead", "Stump", or empty string or null
 * health as a string, valid values: "Good", "Fair", "Poor", or empty string or null
 * spc_latin (Latin name) as a, possibly empty, string, cannot be null
 * spc_common (common name) as a, possibly empty, string, cannot be null
 * zipcode as a positive five digit integer (This means that any number from 0 to 99999 is acceptable. The values that are shorter should be treated as if they had leading zeroes, i.e., 8608 represents zipcode 08608, 98 represents zip code 00098, etc.)
 * boroname as a string, valid values: "Manhattan", "Bronx", "Brooklyn", "Queens", "Staten Island"
 * x_sp as a double
 * y_sp as a double
 * All of the string data fields should be case insensitive - i.e., "Alive", "alive", "ALIVE" and "aLIVe" are all valid values for the status data field.
 *@author Jessy Huang
 *
 */
public class Tree implements Comparable<Tree> {
    private int tree_id;// as a non-negative integer
    private String status;// as a string, valid values: "Alive", "Dead", "Stump", or empty string or null
    private String health;// as a string, valid values: "Good", "Fair", "Poor", or empty string or null
    private String spc_latin;// (Latin name) as a, possibly empty, string, cannot be null
    private String spc_common;// (common name) as a, possibly empty, string, cannot be null
    private int zipcode;// as a positive five digit integer (This means that any number from 0 to 99999
                        // is acceptable. The values that are shorter should be treated as if they had
                        // leading zeroes, i.e., 8608 represents zipcode 08608, 98 represents zip code
                        // 00098, etc.)
    private String boroname;// as a string, valid values: "Manhattan", "Bronx", "Brooklyn", "Queens",
                            // "Staten Island"
    private double x_sp;
    private double y_sp;

    /*
     * This is two parameter constructor.
     * The treeID should be a non-negative integer and the species should not be
     * null.
     * If the constructor is called with invalid arguments, then an instance of
     * IllegalArgumentException
     * should be thrown carrying an appropriate error message.
     * 
     */
    public Tree(int treeID, TreeSpecies species) throws IllegalArgumentException {
        if (treeID < 0)
            throw new IllegalArgumentException("Invalid value for tree ID. "
                    + "Valid range is non-negtive integer.");
        if (species == null)
            throw new IllegalArgumentException("null species.");

        tree_id = treeID;
        spc_common = species.getCommonName();
        spc_latin = species.getLatinName();
    }

    /*
     * validate and set the value of status for this tree object
     * 
     * @param status value to be examined and set.
     * 
     * @throws IllegalArgumentException if not valid
     * 
     */

    public void setStatus(String status) throws IllegalArgumentException {
        if (status.equalsIgnoreCase("Alive") || status.equalsIgnoreCase("Dead") || status.equalsIgnoreCase("Stump")
                || status.isEmpty() || status == null)
            this.status = status;
        else
            throw new IllegalArgumentException("invalid status");
    }

    /*
     * validate and set the value of health for this tree object
     * 
     * @param health value to be examined and set.
     * 
     * @throws IllegalArgumentException if not valid
     * 
     */
    public void setHealth(String health) throws IllegalArgumentException {
        if (health == null || health.isEmpty() || health.equalsIgnoreCase("Good") || health.equalsIgnoreCase("Fair")
                || health.equalsIgnoreCase("Poor"))
            this.health = health;
        else
            throw new IllegalArgumentException("invalid health");
    }

    /*
     * validate and set the value of zipcode for this tree object
     * 
     * @param zipcode value to be examined and set.
     * 
     * @throws IllegalArgumentException if not valid
     * 
     */
    public void setZipcode(int zipcode) throws IllegalArgumentException {
        if (zipcode < 0)
            throw new IllegalArgumentException("invalid zipcode");
        else
            this.zipcode = zipcode;
    }

    /*
     * validate and set the value of boroname for this tree object
     * 
     * @param boroname value to be examined and set.
     * 
     * @throws IllegalArgumentException if not valid
     * 
     */
    public void setBoroname(String boroname) throws IllegalArgumentException {
        if (boroname.equalsIgnoreCase("Manhattan") || boroname.equalsIgnoreCase("Bronx")
                || boroname.equalsIgnoreCase("Brooklyn") || boroname.equalsIgnoreCase("Queens")
                || boroname.equalsIgnoreCase("Staten Island"))
            this.boroname = boroname;
        else
            throw new IllegalArgumentException("invalid Boroname");
    }

    /*
     * setter to set x_sp value in this tree object
     * 
     * @param x_sp
     */
    public void setX_sp(double x_sp) {
        this.x_sp = x_sp;

    }

    /*
     * setter to set y_sp value in this tree object
     * 
     * @param y_sp
     */
    public void setY_sp(double y_sp) {
        this.y_sp = y_sp;
    }

    /*
     * getter to get status value of this tree object
     * 
     * @return status;
     */
    public String getStatus() {
        return status;
    }

    /*
     * getter to get health value of this tree object
     * 
     * @return health;
     */
    public String getHealth() {
        return health;
    }

    /*
     * getter to get zipcode value of this tree object
     * 
     * @return zipcode;
     */
    public int getZipcode() {
        return zipcode;
    }

    /*
     * getter to get x_sp value of this tree object
     * 
     * @return x_sp;
     */
    public double getX_sp() {
        return x_sp;

    }

    /*
     * getter to get y_sp value of this tree object
     * 
     * @return y_sp;
     */
    public double getY_sp() {
        return y_sp;
    }

    /*
     * getter to get common species name value of this tree object
     * 
     * @return spc_common;
     */
    public String getCommonName() {
        return spc_common;
    }

    /*
     * getter to get latin species name value of this tree object
     * 
     * @return spc_latin;
     */
    public String getLatinName() {
        return spc_latin;
    }

    /*
     * getter to get boroname value of this tree object
     * 
     * @return boroname;
     */
    public String getBoroname() {
        return boroname;
    }

    /**
     * Compares this object with the specified object for order.
     * 
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is
     *         less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Tree o) {
        int tmp;
        tmp = this.spc_common.compareToIgnoreCase(o.spc_common);
        if (tmp != 0)
            return tmp;
        return (Integer.compare(tree_id, o.tree_id));

    }

    /**
     * Indicates whether some object obj is "equal to" this one.
     * To Tree objects are considered equal if their tree_id, spc_common, spc_latin
     * are the same.
     * @param obj
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Tree))
            return false;
        Tree other = (Tree) obj;
        if (tree_id != other.tree_id)
            return false;
        if (!spc_common.equalsIgnoreCase(other.spc_common))
            return false;
        if (!spc_latin.equalsIgnoreCase(other.spc_latin))
            return false;
        return true;
    }

/*
 * method to return string to display the content of this tree object
 * @return string, the content of the tree object.
 */
    public String toString() {
         
        return String.format("%15s, %15s, %15d",
                spc_common, spc_latin, tree_id);
       
    }

}
