package project1;

public class Singleton {
    // data fields
   // private Singleton instance = new Singleton();
   private Singleton instance;
    /**
     * Constructor
     */
    public Singleton() {
        // initialize the data fields
        instance=this;
    }

    /**
     * Returns the Singleton instance
     */
    public Singleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {

        Singleton s= new Singleton();
        s.getInstance();
    }
}
