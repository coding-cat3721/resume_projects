package edu.nyu.cs.recursion_exercise;

import processing.core.PApplet;

/**
 * Class Tree
 * Tree class is designed to draw a tree using recusive method and applet. The tree is
 * colour green. While the tree gets more details, the color green gets brighter.       
 * And every 5 seconds, by similar way, new tree will be genarated to replace the        
 * old tree. 
 * 
 * @Auther,??, with comments by Jessy Huang (devils-advocate3721)
 * @version 0.1
         
 *          
 * Methods:
 * -- public static double degToRad(int deg);         
 * this method is used to convert angle unit from degree to radian.
 * 
 * -- public void drawFractal(int x, int y, int n, int angle);
 * This method is to generate tree by recursive. Each time this method is called,
 * it will draw the current tree, meanwhile it will generate four next level subtrees. 
 * each next level subtree will generate four next next level subtrees, this pattern 
 * continues until base case conditon is met, recursive stops. More recursive goes,
 * tree gets more details and the color of subtree will be brighter green colour.       
 *  
 * The tree is gradually created by drawing a line of starting point and end point of the tree
 * with the assigned colour every time calling this method. 
 *          
 * x, y are coordinators of the starting point of the tree. 
 * n is the number of the recersive
 * angle is the angle of the tree         
 * xn1, yn1, which is the x, y coordinator of the end point of the tree is calculated by the 
 * following fomula:
 *  len = (int) Math.round(Math.pow(treeSize, n - 1));
 *  xn1 = (int) Math.round(x - (2 * len * Math.sin(degToRad(angle))));
 *  yn1 = (int) Math.round(y - (2 * len * Math.cos(degToRad(angle))));
 * 
 * subtree#1 starting point is in the middle of parent tree.
 * subtree#2 starting point is in the middle of subtree#1 starting point and parent tree end point.
 * subtree#3 starting point is in the middle of subtree#1 staring point and parent tree starting point.        
 * subtree#4 starting point is in the middle of starting points of subtree#1 and subtree #3        
 *  
 * subtree angle is decided by the following formula:
 *  subtree angle = ( parent tree angle + randy.nextInt(randFact) + constFact[0]) % maxAngle); 
 * the middle component is random generated in the range (0,randFact), constFact[] is array to assign different 
 * angle value to the four subtrees, and mode of maxAngle is to make sure angle is not exceeding 360 degree,         
 *           
 * Variables:         
 * -- public static final int maxAngle 
 * the max angle allowed
 * -- public static final int startX,startY
 * these are the x, y coordinator of the root of the tree
 * -- public static final int numOfRecursions 
 * number of recursions. each recursion represents a subtree.        
 * -- public static final int startAngle
 * the start angle of tree         
 * --public static final double treeSize 
 * it is used to compute the coordinator of end point of the tree.                                          
 * -- public static final int Detail 
 * it is the base case of the recursive method drawFractal().        
 * -- public static final int randFact 
 * it is used in the formula to generate subtree starting anagle, generating random value between 0 to randFact.                                       
 * -- public static final int[] constFact 
 * it is used in the formula to generate subtree starting angle. Each element of array maps to the subtree,        
 * for example constFact[0] goes to subtree #1...                                                                         
 * -- public static int[] red, green, blue, 
 * colour array, used to set different kind of green. the more recursive goes, green gets brighter 
 * 
 *                                                           
 */
public class Tree extends PApplet {

    public static final int maxAngle = 360;// max angle allowed
    public static final int startX = 600;// x coordinator of the root of the tree
    public static final int startY = 800;// y coordinator of the root of the tree
    public static final int numOfRecursions = 9;// number of recursions
    public static final int startAngle = 0;// the start angle of tree
    public static final double treeSize = 2;// it is used to compute the coordinator of end point of the tree.
    public static final int Detail = 3;// base case of the recursive method drawFractal().
    public static final int randFact = 30;// it is used in the formula to generate subtree starting anagle
                                          // generating random value between 0 to randFact.
    public static final int[] constFact = { -60, 05, -50, 45 };// it is used in the formula to generate
                                                               // subtree starting angle. Each element of array maps
                                                               // to the subtree, for example constFact[0] goes to
                                                               // subtree #1...
    // colour array, used to set different kind of green. the more recursive goes,
    // green gets brighter
    public static int[] red = { 0, 0, 0, 0, 7, 15, 23, 31, 39, 47, 55, 43 };
    public static int[] green = { 171, 159, 147, 135, 123, 111, 99, 87, 75, 63, 51, 43 };
    public static int[] blue = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

    /**
     * Method to convert angle unit from degree to radian.
     * 
     * @param deg, int, angle degree
     * @return double, angle radian
     */
    public static double degToRad(int deg) {
        return deg * Math.PI / 180;
    }

    /**
     * Method to generate tree by recursive. Each time this method is called,
     * it will draw a line with assigned green colour, meanwhile it will generate four next level subtrees. 
     * Each next level subtree will generate four next next level subtrees, this pattern 
     * continues until base case conditon is met, "the number of recursive ==3", 
     * then recursive stops. More recursive goes, tree gets more details and the color of subtree
     * gets brighter green colour.
     * 
     * The tree is gradually created by drawing a line of starting point and end point of the tree
     * with the assigned colour every time calling this method. 
     * 
     * @param x,     starting x coordinator of the tree
     * @param y,     starting y coordinator of the tree
     * @param n,     the number of recursive.
     * @param angle, the starting angle of the tree
     */
    public void drawFractal(int x, int y, int n, int angle) {
        // base case, recursive stops.
        if (n == Detail)
            return;
        // it is used to find the end point of the tree.
        int len = (int) Math.round(Math.pow(treeSize, n - 1));

        // x, y coordinators of the end point of the tree
        int xn1 = (int) Math.round(x - (2 * len * Math.sin(degToRad(angle))));
        int yn1 = (int) Math.round(y - (2 * len * Math.cos(degToRad(angle))));
    
        // starting x, y coordinators of first subtree
        int mid1x = (x + xn1) / 2;
        int mid1y = (y + yn1) / 2;
        // starting x, y coordinators of second subtree
        int mid2x = (mid1x + xn1) / 2;
        int mid2y = (mid1y + yn1) / 2;
        // starting x, y coordinators of third subtree
        int mid3x = (x + mid1x) / 2;
        int mid3y = (y + mid1y) / 2;
        // starting x, y coordinators of fourth subtree
        int mid4x = (mid3x + mid1x) / 2;
        int mid4y = (mid3y + mid1y) / 2;

        java.util.Random randy = new java.util.Random();
        // recursively create four subtrees, subtree angle is calculated by adding "parent tree angle
        //+ random generated number in the range (0, randFact) + variable constFact[], which has assigned
        // different value to the four subtree. and mode of maxAngle is to prevent exceeding 360 degree.
        drawFractal(mid1x, mid1y, n - 1, (angle + randy.nextInt(randFact) + constFact[0]) % maxAngle);
        drawFractal(mid2x, mid2y, n - 1, (angle + randy.nextInt(randFact) + constFact[1]) % maxAngle);
        drawFractal(mid3x, mid3y, n - 1, (angle + randy.nextInt(randFact) + constFact[2]) % maxAngle);
        drawFractal(mid4x, mid4y, n - 1, (angle + randy.nextInt(randFact) + constFact[3]) % maxAngle);

        // setup tree colour, the index of the color array is decided by the value by
        // adding a random number range (0,2) on top of variable n value
        //  which is to track "the number of recursion".
        float r = Tree.red[(randy.nextInt() % 3) + n];
        float g = Tree.green[(randy.nextInt() % 3) + n];
        float b = Tree.blue[(randy.nextInt() % 3) + n];

        // draw the line from staring point (x,y) to the ending point (xn1, yn1) with assigned colour 
        this.stroke(r, g, b);
        this.line(x, y, xn1, yn1);
    }

    /**
     * Method to initialize window size 1200x1000
     */
    public void settings() {
        this.size(1200, 1000);
    }

    // Mehtod to setup background black and start to draw the tree.
    public void setup() {
        background(0, 0, 0);
        drawFractal(startX, startY, numOfRecursions, startAngle);
    }

    // Method to regenerate new tree and replace the old tree every 5 seconds.
    public void draw() {
        if (PApplet.second() % 5 == 0) {
            background(0, 0, 0);
            drawFractal(startX, startY, numOfRecursions, startAngle);
        }
    }

    // main() method to launch applet.
    public static void main(String args[]) {
        PApplet.main("edu.nyu.cs.recursion_exercise.Tree");
    }
}