package project4;

/**
 * This class represents a single rest stop. It stores the label of the rest
 * stop along with a list of the supplies that a hiker can collect at this rest-stop 
 * and a list of obstacles that a hiker may encounter at this rest-stop. It implements 
 * the Comparable interface.
 * 
 * @author Jessy Huang
 */
public class RestStop implements Comparable<RestStop> {
    private String lable;// variable to store lable of the restStop
    private String[] supply = null;// variable to store the supplys of the restStop
    private String[] obstical = null;// vatiable to store the obsticals of the restStop

    // constructor to create empty RestStop object
    public RestStop() {

    }

    // constructor to create RestStop object with the provided information
    // of "lable""supply""obstical"
    public RestStop(String lable, String[] supply, String[] obstical) {
        this.lable = lable;
        this.supply = supply;
        this.obstical = obstical;
    }

    /**
     * perform two RestStop object comparison.
     * @param RestStop
     * @return int, 0 for equal, 1 for bigger, -1 for less than the compared object.
     */
    public int compareTo(RestStop t) {
        if (t == null)
            throw new NullPointerException();
        if (!(t instanceof RestStop))
            throw new ClassCastException();
        RestStop other = (RestStop) t;
        return (this.lable.compareTo(other.lable));
    }

    /**
     * return the string of displaying RestStop object.
     * @return String, represend this RestStop object.
     */
    public String toString() {
        String resut = "" + this.lable;
        return resut;
    }

    /**
     * getter to get private variable {@code}lable.
     * 
     * @return, String the lable of this Rest Stop. 
     */
    public String getLable() {
        return lable;
    }

    /**
     * getter to get private variable {@code}supply
     * @return String, {@code}supply
     */
    public String[] getSupply() {
        return supply;
    }

    /**
     * getter to get private variable {@code}obstical
     * @return String {@code}obstical.
     */
    public String[] getObsticle() {
        return obstical;
    }
}