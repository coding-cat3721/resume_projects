package project4;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * An implementation of a binary search tree. The elements are ordered using
 * their natural ordering. This implementation provides guaranteed O(H) (H is
 * the height of this tree which could be as low as logN for balanced trees, but could be
 * as large as N for unbalanced trees) time cost for the basic operations (add, remove and
 * contains).
 * 
 * This class implements many of the methods provided by the Java framework's
 * TreeSet class.
 * 
 * @author Jessy Huang, Joanna Klukowska
 */

public class BST<E extends Comparable<E>>
        extends Object
        implements Iterable<E> {

    private Node root;// reference to the root node of the tree
    private int size;// size of the tree
    private boolean found; // helper variable used by the remove methods
    private Stack<Node> heightUpdateStack;// used to store path for Height update.

    /**
     * Constructs a new, empty tree.
     * elements.
     */
    public BST() {
        root = null;
        size = 0;
        found = false;
        heightUpdateStack = new Stack<Node>();
    }

    /**
     * Constructs a new tree containing the elements in the specified collection,
     * sorted according to the natural ordering of its elements. All elements
     * inserted into the tree must implement the Comparable interface.
     * This operation should be O(N logN) where N is the number of elements in the
     * collection. This implies, that the tree that is constructed has to have the
     * high that is approximately logN, not N.
     * 
     * @param collection - collection whose elements will comprise the new tree
     * @throws NullPointerException- if the specified collection is null
     */

    public BST(E[] collection) throws NullPointerException {
        if (collection == null)
            throw new NullPointerException();
        int i = 0;
        while (collection[i] != null) {
            this.addWithoutHeight​(collection[i]);
            i++;
        }
        root.height = updateAllHeight(root);
    }

    /**
     * Update the @height field for {@code} treeNode and all of its subtree
     * elements.
     * 
     * @param treeNode, subtree root
     *                  @return, the height of {@code}treeNode.
     */
    public int updateAllHeight(Node treeNode) {
        // leaf has 1 height
        if (treeNode.left != null && treeNode.right != null)
            treeNode.height = 1 + Math.max(updateAllHeight(treeNode.left), updateAllHeight(treeNode.right));
        else if (treeNode.left != null)
            treeNode.height = 1 + updateAllHeight(treeNode.left);
        else if (treeNode.right != null)
            treeNode.height = 1 + updateAllHeight(treeNode.right);
        else
            treeNode.height = 1;// leav height is 1.
        return treeNode.height;
    }

    /**
     * update the {@code} height field for all treeNode stored in {@code} heightUpdateStack.
     */
    private void updateHeight() {
        while (!heightUpdateStack.isEmpty()) {
            Node current = heightUpdateStack.pop();     
                if (current.left == null && current.right == null)
                    current.height = 1; // leaf
                else if (current.left == null)
                    current.height = current.right.height;
                else if (current.right == null)
                    current.height = current.left.height;
                else
                    current.height = 1 + Math.max(current.left.height, current.right.height);        
        }

    }

    /**
     * Adds the specified element to this set if it is not already present. More
     * formally, adds the specified element e to this tree if the set contains no
     * element e2 such that Objects.equals(e, e2). If this set already contains the
     * element, the call leaves the set unchanged and returns false.
     * This operation should be O(H).
     * 
     * @param e- element to be added to this set
     * @return, true if this set did not already contain the specified
     *           element
     * @throws NullPointerException- if the specified element is null and this set
     *                               uses natural ordering, or its comparator does not
     *                               permit null elements
     *                               
     */
    public boolean add​(E e) throws NullPointerException {
        if (e == null)
            throw new NullPointerException("null value found");
        if (root == null)// create the root of the tree.
        {
            root = new Node(e);
            root.height = 1;
            size++;
            return true;
        }
        Node current = root;
        heightUpdateStack.clear();
        while (current != null) {
            // store all treeNode in the path into the stack to be used for height update
            // later.
            heightUpdateStack.push(current);
            int comp = current.data.compareTo(e);
            if (comp > 0) { // add in the left subtree
                if (current.left == null) {
                    current.left = new Node(e);
                    current.left.height = 1;
                    updateHeight();// update the height for treeNodes stored in the stack.
                    size++;
                    return true;
                } else {
                    current = current.left;
                }
            } else if (comp < 0) {// add in the right subtree
                if (current.right == null) {
                    current.right = new Node(e);
                    current.right.height = 1;
                    updateHeight();// update the height for treeNodes stored in the stack.
                    size++;
                    return true;
                } else {
                    current = current.right;
                }
            } else { // duplicate
                return false;
            }
        }
        // we should never get to this line
        return false;
    }

    /**
     * this method is mainly used in BST constructor which is to build the tree
     * through array of elements.
     * This method will not update the height field of the treeNode. The height
     * field will be updated
     * together in one tree traverse(O(N)) to make it more efficient.
     * 
     * Adds the specified element to this set if it is not already present. More
     * formally, adds the specified element e to this tree if the set contains no
     * element e2 such that Objects.equals(e, e2). If this set already contains the
     * element, the call leaves the set unchanged and returns false.
     * This operation should be O(H).
     * 
     * @param e- element to be added to this set
     * @return, true if this set did not already contain the specified
     *           element
     * @throws NullPointerException- if the specified element is null and this set
     *                               uses natural ordering, or its comparator does not
     *                               permit null elements
     *                               
     */
    public boolean addWithoutHeight​(E e) throws NullPointerException {
        if (e == null)
            throw new NullPointerException("null value found");
        if (root == null)// create the first node
        {
            root = new Node(e);
            size++;
            return true;
        }

        Node current = root;
        while (current != null) {
            int comp = current.data.compareTo(e);
            if (comp > 0) { // add in the left subtree
                if (current.left == null) {
                    current.left = new Node(e);
                    size++;
                    return true;
                } else {
                    current = current.left;
                }
            } else if (comp < 0) {// add in the right subtree
                if (current.right == null) {
                    current.right = new Node(e);
                    size++;
                    return true;
                } else {
                    current = current.right;
                }
            } else { // duplicate
                return false;
            }
        }
        // we should never get to this line
        return false;
    }

    /**
     * Removes the specified element from this tree if it is present. More formally,
     * removes an element e such that Objects.equals(o, e), if this tree contains
     * such an element. Returns true if this tree contained the element (or
     * equivalently, if this tree changed as a result of the call). (This tree will
     * not contain the element once the call returns.)
     * This operation should be O(H).
     * 
     * @param o- object to be removed from this set, if present
     * @return true if this set contained the specified element
     * @throws
     * ClassCastException         - if the specified object cannot be compared with
     *                            the
     *                            elements currently in this tree
     * NullPointerException - if the specified element is
     *                            null
     */
    public boolean remove​(Object o) throws NullPointerException, ClassCastException {

        if (o == null)
            throw new NullPointerException("null value found");
        if (root == null)
            return false;
        found = false;
        try {
            E other = (E) o;
            heightUpdateStack.clear();
            root = this.removeRec​(root, other);
            //update size and the height field of each impacted treeNode.
            if (found) {
                updateHeight();
                size--;
            }
            return found;
        } catch (ClassCastException e) {
            throw e;
        }

    }

    /**
     * recursive traverse the tree to find out and remove the node.
     * 
     * @param treeNode, root of subtree
     * @param o,        object to be removed from this set, if present
     * @return
     *         -- null if it is empty tree
     *         -- treeNode's child if treeNode is the element to remove
     *         and it has only one child.
     *         -- if treeNode is the element to remove, and has too children,
     *         replace the content of treeNode with its succedor, remove succedor,
     *         return treeNode itself.
     */
    public Node removeRec​(Node treeNode, E o) {
        /* Base Case: If the tree is empty */
        if (treeNode == null) {
            return treeNode;
        } else {
            heightUpdateStack.push(treeNode);
        }
        /* Otherwise, recur down the tree */
        int comp = o.compareTo(treeNode.data);
        if (comp < 0)
            treeNode.left = this.removeRec​(treeNode.left, o);
        else if (comp > 0)
            treeNode.right = this.removeRec​(treeNode.right, o);

        // if key is same as root's key, then This is the node to be deleted
        else {
            found = true;
            if (treeNode.left == null && treeNode.right == null) {
                heightUpdateStack.pop();
                return null;
            }
            // node with only one child or no child
            else if (treeNode.left == null) {
                heightUpdateStack.pop();
                return treeNode.right;
            } else if (treeNode.right == null) {
                heightUpdateStack.pop();
                return treeNode.left;
            } else {
                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                treeNode.data = getPostdecessor(treeNode.right);

                // Delete the inorder successor
                treeNode.right = this.removeRec​(treeNode.right, treeNode.data);
            }
        }

        return treeNode;
    }

    /**
     * find out the {@code} current's successdor's data
     * 
     * @param current, treeNode to be found out its successdor's data
     *                 @return, {@code} current's successdor's data.
     */
    E getPostdecessor(Node current) {
        if (current == null)
            throw new NullPointerException("getPostdecessor called with an empty subtree");
        E minData = current.data;
        while (current.left != null) {
            minData = current.left.data;
            current = current.left;
        }
        return minData;
    }

    /**
     * Removes all of the elements from this set. The set will be empty after this
     * call returns. This operation should be O(1).
     */
    public void clear() {
        root = null;
        size = 0;
        
    }

    /**
     * Returns true if this set contains the specified element. More formally,
     * returns true if and only if this set contains an element e such that
     * Objects.equals(o, e). This operation should be O(H).
     * 
     * @param o - object to be checked for containment in this set
     * @return true if this set contains the specified element
     * @throws
     * ClassCastException         - if the specified object cannot be compared with
     *                            the
     *                            elements currently in the set
     * NullPointerException - if the specified element is
     *                            null and this set uses
     *                            natural ordering, or its comparator does not
     *                            permit null elements
     * 
     */
    public boolean contains​(Object o) throws NullPointerException, ClassCastException {
        if (o == null || root == null)
            throw new NullPointerException("Null element");

        Node current = root;
        int comp = 0;
        try {
            while (current != null) {
                comp = current.data.compareTo((E) o);
                if (comp == 0)// find the element
                    return true;
                if (comp > 0)
                    current = current.left;
                else
                    current = current.right;
            }
        } catch (ClassCastException ex) {
            throw ex;
            // System.out.println("ClassCastException thrown");
        }
        return false;
    }

    /**
     * Returns the number of elements in this tree.
     * This operation should be O(1).
     * 
     * @return the number of elements in this tree
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this set contains no elements.
     * This operation should be O(1).
     * 
     * @return true if this set contains no elements
     */
    public boolean isEmpty() {
        if (size == 0)
            return true;
        else
            return false;
    }

    /**
     * Returns the height of this tree. The height of a leaf is 1. The height of the
     * tree is the height of its root node.
     * This operation should be O(1).
     * 
     * @return the height of this tree or zero if the tree is empty
     */
    public int height() {
        return root.height;
    }

    /**
     * Returns an iterator over the elements in this tree in ascending order.
     * This operation should be O(N).
     * Specified by iterator in interface Iterable<E extends Comparable<E>>
     * 
     * @returns: an iterator over the elements in this set in ascending order
     */

    public Iterator<E> iterator() {
        return new Itr();
    }

    /**
     * Returns an iterator over the elements in this tree in order of the preorder
     * traversal. This operation should be O(N).
     * 
     * @return an iterator over the elements in this tree in order of the preorder
     *         traversal
     * 
     */
    public Iterator<E> preorderIterator() {
        return new PreorderItr();
    }

    /**
     * Returns an iterator over the elements in this tree in order of the postorder
     * traversal. This operation should be O(N).
     * 
     * @return an iterator over the elements in this tree in order of the postorder
     *         traversal
     */
    public Iterator<E> postorderIterator() {
        return new PostorderItr();
    }

    /**
     * Returns the element at the specified position in this tree. The order of the
     * indexed elements is the same as provided by this tree's iterator. The
     * indexing is zero based (i.e., the smallest element in this tree is at index 0
     * and the largest one is at index size()-1).
     * This operation should be O(H).
     * 
     * @param index- index of the element to return
     * @returnthe element at the specified position in this tree
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0
     *                                   || index >= size())
     *                                   
     */
    public E get​(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("out of boundary");
        Iterator<E> ltr = iterator();
        E current = null;

        for (int i = 0; i <= index; i++) {
            if (ltr.hasNext()) {
                current = ltr.next();
            } else {
                current = null;
                break;
            }

        }
        return current;
    }

    /**
     * Returns the least element in this tree greater than or equal to the given
     * element, or null if there is no such element.
     * This operation should be O(H).
     * 
     * @param e - the value to match
     * @return the least element greater than or equal to e, or null if there is no
     *         such element
     *         
     * @throws ClassCastException - if the specified element cannot be compared with
     *                            the elements currently in the set
     *  NullPointerException - if the specified element is
     *                            null
     */
    public E ceiling​(E e) throws NullPointerException, ClassCastException {
        if (e == null || root == null)
            throw new NullPointerException();

        Iterator<E> ltr = iterator();
        E current = null;

        try {// while loop to find out the least greater or equal to element
            while (ltr.hasNext()) {

                current = ltr.next();
                if (current.compareTo(e) >= 0)
                    return current;
            }
            return null;
        } catch (ClassCastException ex) {
            throw ex;
        }
    }

    /**
     * Returns the greatest element in this set less than or equal to the given
     * element, or null if there is no such element.
     * This operation should be O(H).
     * 
     * @param e- the value to match
     * @return the greatest element less than or equal to e, or null if there is no
     *         such
     *         element
     * @throws
     * ClassCastException         - if the specified element cannot be compared with
     *                            the elements currently in the set
     * NullPointerException - if the specified element is
     *                            null
     */
    public E floor​(E e) throws NullPointerException, ClassCastException {

        if (e == null || root == null)
            throw new NullPointerException();

        Iterator<E> ltr = iterator();
        E current = null;
        E prev = null;

        try {// while loop to find the greatest, but also is less or equal to "e"
            while (ltr.hasNext()) {
                prev = current;
                current = ltr.next();
                if (current.compareTo(e) >= 0)
                    break;
            }
            if (current.compareTo(e) <= 0)
                return current;
            else if (prev != null && prev.compareTo(e) <= 0)
                return prev;
            else
                return null;
        } catch (ClassCastException ex) {
            throw ex;
        }

    }

    /**
     * Returns the first (lowest) element currently in this tree.
     * This operation should be O(H).
     * 
     * @return the first (lowest) element currently in this tree
     * @throws NoSuchElementException - if this set is empty
     */
    public E first() throws NoSuchElementException {
        Iterator<E> itr = iterator();
        if (!itr.hasNext()) {
            throw new NoSuchElementException("No such element");
        }
        return itr.next();
    }

    /**
     * Returns the last (highest) element currently in this tree.
     * This operation should be O(H).
     * 
     * @return the last (highest) element currently in this tree
     * @throws NoSuchElementException- if this set is empty
     */
    public E last() throws NoSuchElementException {
        Iterator<E> itr = iterator();
        E current = null;
        while (itr.hasNext()) {
            current = itr.next();
        }
        if (current == null) {
            throw new NoSuchElementException("No such element");
        }
        return current;
    }

    /**
     * Returns the greatest element in this set strictly less than the given
     * element, or null if there is no such element.
     * This operation should be O(H).
     * 
     * @param e- the value to match
     * @return the greatest element less than e, or null if there is no such element
     * @throws ClassCastException - if the specified element cannot be compared with
     *                            the elements currently in the set
     * 
     * NullPointerException - if the specified element is
     *                            null
     * 
     */
    public E lower​(E e) throws NullPointerException, ClassCastException {

        if (e == null || root == null)
            throw new NullPointerException();

        Iterator<E> ltr = iterator();
        E current = null;
        E prev = null;
        try {

            while (ltr.hasNext()) {
                prev = current;
                current = ltr.next();
                if (current.compareTo(e) > 0)
                    break;
            }

            if (current.compareTo(e) < 0)
                return current;
            else if (prev != null && prev.compareTo(e) < 0)
                return prev;
            else
                return null;
        } catch (ClassCastException ex) {
            throw ex;
        }

    }

    /**
     * Returns the least element in this tree strictly greater than the given
     * element, or null if there is no such element.
     * This operation should be O(H).
     * 
     * @param e- the value to match
     * @return the least element greater than e, or null if there is no such element
     * @throws ClassCastException - if the specified element cannot be compared with
     *                            the
     *                            elements currently in the set
     * NullPointerException - if the specified element is
     *                            null
     */
    public E higher​(E e) throws NullPointerException, ClassCastException {
        if (e == null || root == null)
            throw new NullPointerException();

        Iterator<E> ltr = iterator();
        E current = null;
        try {
            while (ltr.hasNext()) {

                current = ltr.next();
                if (current.compareTo(e) > 0)
                    break;
            }

            if (current.compareTo(e) > 0)
                return current;
            else
                return null;
        } catch (ClassCastException ex) {
            throw ex;
        }

    }

    /**
     * Compares the specified object with this tree for equality. Returns true if
     * the given object is also a tree, the two trees have the same size, and every
     * member of the given tree is contained in this tree.
     * This operation should be O(N).
     * 
     * 
     * @Override equals in class Object
     * @param obj- object to be compared for equality with this tree
     * @return true if the specified object is equal to this tree
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof BST))
            return false;
        try {
            BST<E> other = (BST<E>) obj;
            if (other.size() != this.size) {
                return false;
            }
            // BST current = other.root;
            Iterator<E> itr1 = other.preorderIterator();
            Iterator<E> itr = this.preorderIterator();
            while (itr.hasNext()) {
                E tmp = itr.next();
                E tmp1 = itr1.next();
                if (tmp != null && !tmp.equals(tmp1))
                    return false;
            }
        } catch (ClassCastException ex) {
            return false;
        }
        return true;
    }

    /**
     * Returns a string representation of this tree. The string representation
     * consists of a list of the tree's elements in the order they are returned by
     * its iterator (inorder traversal), enclosed in square brackets ("[]").
     * Adjacent elements are separated by the characters ", " (comma and space).
     * Elements are converted to strings as by String.valueOf(Object).
     * This operation should be O(N).
     * 
     * @Overrides:toString in class Object
     * @returns:a string representation of this collection
     * 
     */
    @Override
    public String toString() {
        String result = "[";
        Iterator<E> ltr = iterator();
        while (ltr.hasNext()) {
            result = result + String.valueOf(ltr.next()) + ", ";
        }
        result = result.substring(0, result.length() - 3) + "]";
        return result;
    }

    /**
     * Produces tree like string representation of this tree. Returns a string
     * representation of this tree in a tree-like format. The string representation
     * consists of a tree-like representation of this tree. Each node is shown in
     * its own line with the indentation showing the depth of the node in this tree.
     * The root is printed on the first line, followed by its left subtree, followed
     * by its right subtree. For example
     * K
     * |--D
     * |--B
     * |--A
     * |--null
     * |--null
     * |--null
     * |--J
     * |--null
     * |--null
     * |--P
     * |--M
     * |--L
     * |--null
     * |--null
     * |--O
     * |--N
     * |--null
     * |--null
     * |--null
     * |--null
     * 
     * is a string representation of this tree:
     * 
     * 
     * K
     * / \
     * D P
     * / \ /
     * B J M
     * / / \
     * A L O
     * /
     * N
     * 
     * 
     * 
     * This operation should be O(N).
     * 
     * 
     * @return string containing tree-like representation of this tree.
     */
    public String toStringTreeFormat() {
        String prefix = "|-";
        return toStringTreeFormat(root, prefix);
    }

    public String toStringTreeFormat(Node treeNode, String prefix) {
        if (treeNode == null)
            return "";
        String result = null;
        if (treeNode == root) {         
            result = String.valueOf(treeNode.data);
        } else {
          
            result = prefix + String.valueOf(treeNode.data);
        }      
        return result + "\n" + toStringTreeFormat(treeNode.left, prefix + "-")
                + toStringTreeFormat(treeNode.right, prefix + "-");

    }

    /*
     * Node class for this BST
     */
    private class Node implements Comparable<Node> {

        public E data;// data of this node
        public Node left; // left child of this node
        public Node right;// right child of this node
        // height of this node. The reason to add this field is to track down height for 
        //each node during "add", "remove" operation. It will achieve O(1) to get the height of root 
        //which is the height of the tree as well. It will be usful for later AVL tree verification.
        public int height;
        /**
         * construct Node with the data
         * 
         * @param data, data of the node.
         */
        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
            height = 0;
        }

        /**
         * compare two objects.
         * 
         * @param other
         * @return, int, 0 if equal, 1 if bigger, -1 if less than the
         *          {@code} other.
         */
        public int compareTo(Node other) {
            return this.data.compareTo(other.data);
        }
    }

    /**
     * Adapter to provide iterators.
     */
    private class Itr implements Iterator<E> {
        private Stack<Node> inOrderStack;
        private Node current;

        /**
         * constructor which push root, left child, left left child and so on
         * until left child is leaf in the stack.
         */

        Itr() {
            inOrderStack = new Stack<>();
            current = root;
            while (current != null) {
                inOrderStack.push(current);
                current = current.left;
            }
        }

        /**
         * check if there is element in the tree.
         * 
         * @return boolean, trure if there is next element, false otherwise
         */
        public boolean hasNext() {
            return !inOrderStack.isEmpty();
        }

        /**
         * to find next element's data.
         * 
         * @return the next element's data
         */
        public E next() {

            Node nextNode = inOrderStack.pop();
            current = nextNode.right;

            while (current != null) {
                inOrderStack.push(current);
                current = current.left;
            }

            if (nextNode != null)
                return nextNode.data;
            else
                return null;
        }

    }

    /**
     * Adapter to provide preorder iterators
     */

    private class PreorderItr implements Iterator<E> {

        private Stack<Node> preorderStack;

        /**
         * constructor, push root in the stack.
         */
        PreorderItr() {
            preorderStack = new Stack<Node>();
            preorderStack.push(root);
        }

        /**
         * check if there is element in the tree.
         * 
         * @return boolean, trure if there is next element, false otherwise
         */
        public boolean hasNext() {
            return !preorderStack.isEmpty();
        }

        /**
         * to find next element's data.
         * 
         * @return the next element's data
         */
        public E next() {
            Node next = preorderStack.pop();
            if (next.right != null)
                preorderStack.push(next.right);
            if (next.left != null)
                preorderStack.push(next.left);
            if (next != null)
                return next.data;
            else
                return null;
        }

    }

    /**
     * Adapter to provide reverse iterators
     */

    private class PostorderItr implements Iterator<E> {
        private Stack<Node> postorderStack;

        /**
         * constructor, find the first leaf in a tree rooted at current and
         * store intermediate nodes in the stack.
         */
        PostorderItr() {
            postorderStack = new Stack<>();
            findNextLeaf(root);
        }

        /**
         * check if there is element in the tree.
         * 
         * @return boolean, trure if there is next element, false otherwise
         */
        public boolean hasNext() {
            return !postorderStack.isEmpty();
        }

        /**
         * to find next element's data.
         * 
         * @return the next element's data
         */
        public E next() {
            Node next = postorderStack.pop();
            Node top = postorderStack.peek();
            if (top.left == next)
                findNextLeaf(top.right);
            if (next == null)
                throw new NoSuchElementException();
            return next.data;
        }

        /**
         * find the first leaf in a tree rooted at current and store
         * intermediate nodes in the stack.
         */
        private void findNextLeaf(Node current) {
            while (current != null) {
                postorderStack.push(current);
                if (current.left != null) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
        }
    }

}