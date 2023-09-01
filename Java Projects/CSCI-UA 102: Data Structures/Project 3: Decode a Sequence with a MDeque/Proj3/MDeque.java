package project3;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* *
* A linear collection that supports element insertion and removal at three points: front, middle and back. 
* The name mdeque is short for "double ended queue" (deque) with m for "middle" and is pronounced "em-deck".
* MDeque has no fixed limits on the number of elements it contains.
* The remove operations all return null values if the mdeque is empty. The structure does not allow null 
* as an element.
* All pop..., push..., and peek... operations (from all three points of access) are constant time operations.
* The middle position is defined as (size+1)/2 when inserting an element in the middle and as (size/2) when 
* retrieving an element from the middle. The position count is zero based.
*  [A, B, C, D]  -- middle element is C, insert at middle would add at index 2 (between B and C).
*  [A, B, C, D, E] -- middle element is C, insert at middle would add at index 3 (between C and D).
*
* @ author  Jessy Huang
* @ version Nov 7, 2022
*/

public class MDeque<E>
    extends java.lang.Object
    implements java.lang.Iterable<E> {

  // define internal Node class
  private static class Node<E> {
    public E data;
    public Node<E> next;
    public Node<E> previouse;

    public Node(E data) {
      this.data = data;
      this.next = null;
      this.previouse = null;

    }
  }

  /**
   * Pointer to the front node.
   */
  transient Node<E> front;
  /**
   * Pointer to the back node.
   */
  transient Node<E> back;
  /**
   * Pointer to the middle node.
   */
  transient Node<E> middle;

  transient int size;

  // Creates an empty MDeque object.
  MDeque() {
    front = null;
    back = null;
    middle = null;
    size = 0;
  }

  /**
   * return an iterator over the elements in this mdeque in proper sequence. The
   * elements will be returned in order from front to back. Specified by iterator
   * in interface java.lang.Iterable<E>
   * 
   * @return: an iterator over the elements in this mdeque in proper sequence
   */

  public java.util.Iterator<E> iterator() {
    return new Itr();
  }

  /**
   * Adapter to provide iterators.
   */
  private class Itr implements Iterator<E> {
    private Node<E> lastReturned;
    private Node<E> next;
    private int nextIndex;

    Itr() {

      int index = 1;
      next = front;
      nextIndex = index;
    }

    public boolean hasNext() {
      return nextIndex <= size;
    }

    public E next() {

      if (next == null)
        throw new NoSuchElementException();

      lastReturned = next;
      next = next.next;
      nextIndex++;
      return lastReturned.data;
    }

  }

  /**
   * return an iterator over the elements in this mdeque in reverse sequential
   * order. The elements will be returned in order from back to front.
   * 
   * @return: an iterator over the elements in this mdeque in reverse sequence
   */
  public java.util.Iterator<E> reverseIterator() {
    return new ReverseItr();

  }

  /**
   * Adapter to provide reverse iterators
   */

  private class ReverseItr implements Iterator<E> {
    private Node<E> lastReturned;
    private Node<E> next;
    private int nextIndex;

    ReverseItr() {
      next = back;
      nextIndex = size;
    }

    public boolean hasNext() {
      return nextIndex > 0;
    }

    public E next() {
      if (next == null)
        throw new NoSuchElementException();
      lastReturned = next;
      next = next.previouse;
      nextIndex--;
      return lastReturned.data;
    }

  }

  /**
   * Retrieves the back element of this mdeque.
   * 
   * @return: the back of this mdeque, or null if this mdeque is empty
   */
  public E peekBack() {
    if (size == 0)
      return null;
    else
      return back.data;
  }

  /**
   * Retrieves the first element of this mdeque.
   * 
   * @return: the front of this mdeque, or null if this mdeque is empty
   */
  public E peekFront() {
    if (size == 0)
      return null;
    else
      return front.data;

  }

  /**
   * Retrieves the middle element of this mdeque.
   * 
   * @return: the middle of this mdeque, or null if this mdeque is empty
   */
  public E peekMiddle() {
    if (size == 0)
      return null;
    else
      return middle.data;
  }

  /**
   * Retrieves and removes the back element of this mdeque.
   * 
   * @return: the back of this mdeque, or null if this mdeque is empty
   */
  public E popBack() {
    if (size == 0) {
      return null;
    }
    size--;
    Node<E> current = back;
    if (size == 0) {
      front = null;
      back = null;
      middle = null;
      return current.data;
    }
    back = back.previouse;
    back.next = null;

    // change the middle node pointer,
    if (size % 2 != 0)
      middle = middle.previouse;
    return current.data;
  }

  /**
   * Retrieves and removes the first element of this mdeque.
   * 
   * @return:the front of this mdeque, or null if this mdeque is empty
   */
  public E popFront() {
    if (size == 0) {
      return null;
    }
    size--;
    Node<E> current = front;
    if (size == 0) {
      front = null;
      back = null;
      middle = null;
      return current.data;
    }
    front = front.next;
    front.previouse = null;
    // change middle node pointer.
    if (size % 2 == 0)
      middle = middle.next;
    return current.data;
  }

  /**
   * Retrieves and removes the middle element of this mdeque.
   * 
   * @return: the middle of this mdeque, or null if this mdeque is empty
   */
  public E popMiddle() {
    if (size == 0)
      return null;
    size--;
    Node<E> current = middle;
    if (size == 0) {
      front = null;
      back = null;
      middle = null;
      return current.data;
    }
    if (size == 1) {
      middle = middle.previouse;
      middle.next = null;
      front.next = null;
      back = front;
      return current.data;
    }
    if (size == 2) {
      middle = middle.next;
      middle.next = null;
      middle.previouse = front;
      front.next = middle;
      back = middle;
      return current.data;
    }
    if (size % 2 != 0) {
      middle = middle.previouse;
      middle.next = current.next;
      current.next.previouse = middle;
    } else {
      middle = middle.next;
      middle.previouse = current.previouse;
      current.previouse.next = middle;
    }
    return current.data;
  }

  /**
   * 
   * Inserts the specified item at the back of this mdeque.
   * 
   * @param: item - the element to add
   * @throws: java.lang.IllegalArgumentException - if item is null
   */
  public void pushBack​(E item) throws IllegalArgumentException {
    if (item == null)
      throw new IllegalArgumentException("Item is null");
    Node<E> current = new Node<E>(item);
    size++;
    if (size == 1) {
      front = current;
      back = current;
      middle = current;
    } else {
      current.next = null;
      current.previouse = back;
      back.next = current;
      back = current;
      // change middle node pointer.
      if (size % 2 == 0)
        middle = middle.next;
    }

  }

  /**
   * Inserts the specified item at the front of this mdeque.
   * 
   * @param: item - the element to add
   * @throws: java.lang.IllegalArgumentException - if item is null
   */
  public void pushFront​(E item) throws IllegalArgumentException {
    if (item == null)
      throw new IllegalArgumentException("Item is null");
    Node<E> current = new Node<>(item);
    size++;
    if (size == 1) {
      front = current;
      back = current;
      middle = current;

    } else {
      current.next = front;
      front.previouse = current;
      front = current;
      // change middle node pointer.
      if (size % 2 != 0) {
        middle = middle.previouse;

      }
    }

  }

  /**
   * Inserts the specified item in the middle of this mdeque.
   * 
   * @param:item - the element to add
   * @throws:java.lang.IllegalArgumentException - if item is null
   */
  public void pushMiddle​(E item) throws IllegalArgumentException {

    if (item == null)
      throw new IllegalArgumentException("Item is null");
    Node<E> current = new Node<>(item);
    size++;
    if (size == 1) {
      front = current;
      back = current;
      middle = current;
      return;

    }
    if (size == 2) {
      front.next = current;
      current.previouse = front;
      back = current;
      middle = current;
      return;

    }

    if (size % 2 == 0) {
      current.next = middle.next;
      current.previouse = middle;
      middle.next.previouse = current;
      middle.next = current;
      middle = current;
      return;
    }
    if (size % 2 != 0) {
      current.next = middle;
      current.previouse = middle.previouse;
      middle.previouse.next = current;
      middle.previouse = current;
      middle = current;
    }

  }

  /**
   * return the number of elements in this mdeque.
   * 
   * @return: the number of elements in this mdeque
   */
  public int size() {
    return size;
  }

  /**
   *  return a string representation of this mdeque. The string representation
   *  consists of a list of the collection's elements in the order they are returned by its iterator,
   *  enclosed in square brackets ("[]"). Adjacent elements are separated by the characters ", " (comma and space).
   *  @Overrides:toString in class java.lang.Object
   *  @return: a string representation of this mdeque
   */
  public java.lang.String toString() {
    if (front == null)
      return "[]";
    String tmp = "[";
    int i = 1;
    for (E e : this) {

      if (i == size)
        tmp = tmp + e.toString();
      else
        tmp = tmp + e.toString() + ", ";

      i++;
    }
    tmp = tmp + "]";

    return tmp;
  }

}