package project4;

import java.util.Iterator;


/**
 * This class inherits from  BST<RestStop>. It represents the mountain itself, each node 
 * store data items of type RestStop.  
 * 
 * @author Jessy Huang
 */
public class BSTMountain extends BST<RestStop> {
    private RestStop[] inorder;// inorder traversal array
    private RestStop[] preorder;// preorder traversal array

    /**
     * Empty Construtor.
     */
    public BSTMountain() {

    }

    /**
     * construtor, create RestStops BST tree with nodes in {@code}collections, build
     * inorder traverse array
     * and preorder traverse array of this RestStops BST tree.
     * 
     * @param collections, array of RestStops objects
     * @throws NullPointerException, if {@code}collections contain null element.
     */
    public BSTMountain(RestStop[] collections) throws NullPointerException{
        super(collections);
        inorder = new RestStop[this.size()];
        preorder = new RestStop[this.size()];
        Iterator<RestStop> ltr = this.iterator();
        int i = 0;
        while (ltr.hasNext()) {
            inorder[i] = ltr.next();
            i++;
        }
        Iterator<RestStop> preorderltr = this.preorderIterator();
        i = 0;
        while (preorderltr.hasNext()) {
            preorder[i] = preorderltr.next();
            i++;
        }
    }

    /**
     * Hiker traverses the tree from the root to find all possible path.
     * 
     * @param hiker, hiker who traverse the tree from the root.
     */
    public void searchPath(Hiker hiker) {
        if (hiker == null)
            return;
        searchPath(0, inorder.length - 1, 0, preorder.length - 1, hiker);
    }

    /**
     * Hiker traverses the tree to find out the possible paths.
     * 
     * @param inorderStart,  subtree start index in the inorder traversal array.
     * @param inorderEnd,    subtree end index in the inorder traversal array.
     * @param preorderStart, subtree start index in the preorder traversal array.
     * @param preorderEnd,   subtree end index in the preorder traversal array.
     * @param hiker,         hiker who is going to traverse the subtree
     */
    public void searchPath(int inorderStart, int inorderEnd, int preorderStart, int preorderEnd, Hiker hiker) {
        if (inorderStart > inorderEnd && preorderStart > preorderEnd)
            return;// invalid subtree
        if (!hiker.updateStatus(preorder[preorderStart]))
            return;// there is obstical which can not solve.
        // at the leaf, check if it is cliff
        if (inorderStart == inorderEnd && preorderStart == preorderEnd) {
            if (hiker.getPath().size() == this.height()) {
                //System.out.println(hiker.getPath());
                hiker.printPath();
            }
        } else {
            if (!hiker.isFood())// no food to continue
                return;
            else
                hiker.removeFood();// use one food
            // find next level left subtree, right subtree.
            int partitionIndex = findInorderIndex(preorder[preorderStart]);
            int leftTreeSize = partitionIndex - 1 - inorderStart + 1;
            int rightTreeSize = inorderEnd - (partitionIndex + 1) + 1;
            Hiker leftHiker = new Hiker();
            leftHiker.copyContent(hiker);
            // continue traversing the left and right subtree to find the path.
            if (leftTreeSize > 0)
                searchPath(inorderStart, partitionIndex - 1, preorderStart + 1, preorderStart + 1 + leftTreeSize - 1,
                        leftHiker);
            Hiker rightHiker = new Hiker();
            rightHiker.copyContent(hiker);
            if (rightTreeSize > 0)
                searchPath(partitionIndex + 1, inorderEnd, preorderStart + leftTreeSize + 1, preorderEnd,
                        rightHiker);
        }
    }

    /**
     * to find out the index of subtree root nodes in inorder traversal array.
     * 
     * @param root, subtree root
     * @return int, the index of subtree root node in the inorder traversal array.
     */
    public int findInorderIndex(RestStop root) {
        int i = 0;
        for (RestStop s : inorder) {
            if (root.compareTo(s) == 0)
                break;
            i++;
        }
        return i;
    }

}
