/**
* <Provides the implementation of a doubly linked list node. 
* These nodes have a pointer to the next node, a pointer to the previous node, and data.>
* Known Bugs: <“None”>
*
* @author Sulaiman Lateef
* <sulaimanlateef@brandeis.edu>
* <October 17, 2023>
* COSI 21A PA1
*/

package main;

import java.util.Objects;

public class Node<T> {
	private T data;
	private Node<T> prev;
	private Node<T> next;
	
	/**
     * Constructs a doubly linked list node that holds a data field 
     * but does not point to any other nodes.
     *
     * @param data data field.
     */
	
	public Node(T data) {
		this.data = data;
	}
	
	/**
     * sets the data field of this node.
     *
     * @param data data field.
     */
	
	public void setData(T data) {
		this.data = data;
	}
	
	/**
     * Sets the next pointer of this node.
     *
     * @param next next node.
     */
	
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	/**
     * sets the previous pointer of this node.
     *
     * @param prev previous node
     */
	
	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}
	
	/**
     * returns the data stored in this node.
     *
     * runtime: O(1)
     * @return The data stored in this node
     */
	public T getData() {
		return data;
	}
	
	
	/**
     * Returns the pointer to the next node or null if one does not exist
     *
     * runtime: O(1)
     * @return The next node in the list or null if one does not exist
     */
	public Node<T> getNext() {
		return next;
	}
	
	/**
     * Returns the pointer to the previous node or null if one does not exist
     *
     * runtime: O(1)
     * @return The previous node in the list or null if one does not exist
     */
	
	public Node<T> getPrev() {
		return prev;
	}
	
	/**
     * Returns the String representation of this node’s element.
     *
     * @return the String representation of this node’s element, or null if the data is null.
     */
	
	@Override
	public String toString() {
		return Objects.toString(data, "<null>");
	}
}
