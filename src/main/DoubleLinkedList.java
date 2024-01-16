/**
* <Provides the implementation of a generic non-circular doubly linked list.>
* Known Bugs: <“None”>
*
* @author Sulaiman Lateef
* <sulaimanlateef@brandeis.edu>
* <October 17, 2023>
* COSI 21A PA1
*/

package main;

public class DoubleLinkedList<T> {
	private final Node<T> head;
	private Node<T> tail;
	private int numEntries;

	/**
	 * Initializes a doubly linked list to have 0 elements.
	 * runtime: O(1)
     */
	
	public DoubleLinkedList() {
		// Always dummy element in the front.
		tail = head = new Node<T>(null);
	}
	
	/**
     * Gets the first node in the list or null if one does not exist.
     *
     * runtime: O(1)
     * @return The first node in the list
     */
	public Node<T> getFirst() {
		return head.getNext();
	}
	
	/**
     * Adds an element to the end of this double linked list
     *
     * runtime: O(1)
     * @param element element to be inserted.
     */
	public void insert(T element) {
		Node<T> node = new Node<T>(element);
		node.setPrev(tail);
		tail.setNext(node);
		tail = node;
		numEntries++;
	}
	
	/**
     * Deletes the first element from this list that matches the provided key.
     *
     * runtime: O(n)
     * @param key The element to be deleted.
     * @return The deleted element, or null if the key is not found in the list.
     */
	public T delete(T key) {
		for (Node<T> node = head.getNext(); node != null; node = node.getNext()) {
			if (node.getData().equals(key)) {
				Node<T> prev = node.getPrev();
				Node<T> next = node.getNext();
				prev.setNext(next);
				if (next != null) {
					next.setPrev(prev);
				} else {
					tail = prev;
				}
				--numEntries;
				return node.getData();
			}
		}
		return null;
	}
	
	/**
     * Returns the first element in the list that matches the provided key 
     * or null if one cannot be found.
     * 
     * runtime: O(n)
     * @param key The element to look for.
     * @return The matching element, or null if the key cannot be found in the list.
     */
	
	public T get(T key) {
		for (Node<T> node = head.getNext(); node != null; node = node.getNext()) {
			if (node.getData().equals(key)) {
				return node.getData();
			}
		}
		return null;
	}
	
	/**
     * Returns the number of elements
     *
     * runtime: O(1)
     * @return returns the number of elements in the list.
     */
	
	public int size() {
		return numEntries;
	}
	
    /**
     * Returns a String representation of this list’s elements.
     *
     * runtime: O(n)
     * @return A string representation of the list's elements.
     */
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		boolean first = true;
		for (Node<T> node = head.getNext(); node != null; node = node.getNext()) {
			if (!first) sb.append(", ");
			first = false;
			sb.append(node.getData());
		}
		sb.append("]");
		return sb.toString();
	}
}
