/**
* <Provides the implementation of a generic circular first-in-first-out queue.> 
* Known Bugs: <“None”>
*
* @author Sulaiman Lateef
* <sulaimanlateef@brandeis.edu>
* <October 17, 2023>
* COSI 21A PA1
*/

package main;

import java.util.NoSuchElementException;

public class Queue<T> {

	public T[] q;
	public int head;
	public int tail;
	public int numEntries;
	
	/**
     * Constructs an empty queue that can hold a specified number of elements.
     *
     * @param capacity The maximum capacity of the queue.
     */
	
	@SuppressWarnings("unchecked")
	public Queue(int capacity) {
		this.q = (T[]) new Object[capacity];
	}
	
	/**
     * Adds an element at the tail of the queue.
     *
     * runtime: O(1)
     * @param element enqueued element
     * 
     */
	
	public void enqueue(T element) {
		if (numEntries >= q.length) throw new RuntimeException("Queue is full");
		q[tail] = element;
		tail = incAndWrap(tail);
		numEntries++;
	}
	
	/**
     * removes the element at the head of the queue.
     * 
     * runtime: O(1)
     * @throws NoSuchElementException if there is no such element
     */
	
	public void dequeue() { 
		if (numEntries <= 0) throw new NoSuchElementException();
		q[head] = null;
		head = incAndWrap(head);
		numEntries--;
	}
	
	/**
     * returns the element at the head of the queue without removing it.
     *
     * runtime: O(1)
     * @return The element at the front of the queue.
     * @throws NoSuchElementException if there is no such element
     */
	
	public T front() {
		if (numEntries <= 0) throw new NoSuchElementException();
		return q[head];
	}
	
	/**
     * returns the number of elements in the queue.
     * 
     * runtime: O(1)
     *
     * @return The number of elements in the queue.
     */
	
	public int size() {
		return numEntries;
	}
	
	/**
     * returns a String representation of the queue’s elements.
     *
     * @return A string representation of the queue’s elements.
     */
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0, j = head; i < numEntries; i++, j = incAndWrap(j)) {
			if (i > 0) sb.append(", ");
			sb.append(q[j]);
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Increments an integer n and wraps it around to zero if it reaches the limit
	 *
	 * @param n integer being incremented and potentially wrapped.
	 * @return incremented integer, possibly wrapped to zero.
	 */
	
	private int incAndWrap(int n) {
		if (n++ == q.length) n = 0;
		return n;
	}
}
