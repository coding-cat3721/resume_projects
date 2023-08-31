package project2;

/**
 * TreeList class is used to store a collection of Tree objects.
 * This class implements simple version of linked list with reference to head
 * and tail. It adds Tree specific functions that allow search by species name and New York
 * City boroughs.
 * 
 * This class does not store Tree objects in any particular order.
 * 
 * @author Jessy Huang
 *
 */
public class TreeList {

    // define internal Node class
    private class Node {
        public Tree data;
        public Node next;

        public Node(Tree data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;

    /*
     * Creates an empty list object.
     */
    public TreeList() {
        head = null;
        tail = null;
    }

    /*
     * method that adds a given tree object to the current list; this method should
     * throw an instance of IllegalArgumentException if the specified reference is
     * null.
     * @param tree, the tree object to be added into the list
     * @return boolean, return true if sucessful, false if not sucessful
     * 
     */
    public boolean add(Tree tree) throws IllegalArgumentException {
        if (tree == null) {
            throw new IllegalArgumentException("it should not be null");
        }
        Node n = new Node(tree);
        // check where I'm adding
        if (head == null) {
            n.next = head;
            head = n;
            tail = n;   
        } else { // add to the end
            tail.next = n;
            tail = tail.next;
        }
        return true;
    }

    // method that returns the total number of Tree objects stored in this list.
    //@return the total number of Tree objects stored in this list
    public int getTotalNumberOfTrees() {
        Node tmp = head;
        int i = 0;
        while (tmp != null) {
            i++;
            tmp = tmp.next;
        }
        return i;
    }

    // method that returns the number of Tree objects in the list whose common name
    // is the same as the given speciesName. This method should be case insensitive.
    // If the method is called with a non-existent species, the return value should
    // be 0.
    //@param speciesName, 
    //@return the number of trees which belongs to the common species name.
    public int getCountByCommonName(String speciesName) {
        Node tmp = head;
        int i = 0;
        while (tmp != null) {
            if (tmp.data.getCommonName().equalsIgnoreCase(speciesName))
                i++;
            tmp = tmp.next;
        }
        return i;
    };

    // method that returns the number of Tree objects in the list whose Latin name
    // is the same as the given speciesName. This method should be case insensitive.
    // If the method is called with a non-existent species, the return value should
    // be 0.
    //@param speciesName
    //@return the number of trees which belongs to the latin species name.
    public int getCountByLatinName(String speciesName) {
        Node tmp = head;
        int i = 0;
        while (tmp != null) {
            if (tmp.data.getLatinName().equalsIgnoreCase(speciesName))
                i++;
            tmp = tmp.next;
        }
        return i;
    }

    // method that returns the number of Tree objects in the list that are located
    // in the borough equal to boroName. This method should be case insensitive. If
    // the method is called with a non-existent borough name, the return value
    // should be 0.
    //@param boroName
    //@return the number of trees which belongs to the borougs name.

    public int getCountByBorough(String boroName) {
        Node tmp = head;
        int i = 0;
        while (tmp != null) {
            if (tmp.data.getBoroname() != null && tmp.data.getBoroname().equalsIgnoreCase(boroName))
                i++;
            tmp = tmp.next;
        }
        return i;
    };

    // method that returns the number of Tree objects in the list whose common name
    // is the same as the given speciesName and which are located in the borough
    // boroName. This method should be case insensitive. If the method is called
    // with a non-existent borough name or species, the return value should be 0.
    //@param speciesName, boroName
    //@return the number of trees which belongs to common species and the borougs name.
    public int getCountByCommonNameBorough(String speciesName, String boroName) {
        Node tmp = head;
        int i = 0;
        while (tmp != null) {
            if (tmp.data.getBoroname() != null && tmp.data.getBoroname().equalsIgnoreCase(boroName)
                    && tmp.data.getCommonName().equalsIgnoreCase(speciesName))
                i++;
            tmp = tmp.next;
        }
        return i;

    };

    // method that returns the number of Tree objects in the list whose Latin name
    // is the same as the given speciesName and which are located in the borough
    // boroName. This method should be case insensitive. If the method is called
    // with a non-existent borough name or species, the return value should be 0.
    //@param speciesName, boroName
    //@return the number of trees which belongs to latin species and the borougs name.
    public int getCountByLatinNameBorough(String speciesName, String boroName) {
        Node tmp = head;
        int i = 0;
        while (tmp != null) {
            if (tmp.data.getBoroname().equalsIgnoreCase(boroName)
                    && tmp.data.getLatinName().equalsIgnoreCase(speciesName))
                i++;
            tmp = tmp.next;
        }
        return i;

    };
// mehtod to print out this tree list.
//@return the string of tree object.
    public String toString() {
        Node tmp = head;
        String result = "";

        while (tmp != null) {
            result = result + tmp.data.toString();
            tmp = tmp.next;
        }

        return result;
    }

}
