package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import main.Queue;

class StudentQueueTest {

    @Test
    void testEnqueueAndSize() {
        Queue<Integer> queue = new Queue<>(3);
        assertEquals(0, queue.size());

        queue.enqueue(1);
        assertEquals(1, queue.size());

        queue.enqueue(2);
        assertEquals(2, queue.size());

        queue.enqueue(3);
        assertEquals(3, queue.size());
    }

    @Test
    void testDequeue() {
        Queue<String> queue = new Queue<>(3);
        queue.enqueue("test1");
        queue.enqueue("test2");
        queue.enqueue("test3");

        queue.dequeue();
        assertEquals(2, queue.size());

        queue.dequeue();
        assertEquals(1, queue.size());

        queue.dequeue();
        assertEquals(0, queue.size());

        assertThrows(java.util.NoSuchElementException.class, () -> {
            queue.dequeue();
        });
    }

    @Test
    void testFront() {
        Queue<String> queue = new Queue<>(3);
        queue.enqueue("test1");
        queue.enqueue("test2");
        queue.enqueue("test3");

        assertEquals("test1", queue.front());

        queue.dequeue();
        assertEquals("test2", queue.front());

        queue.dequeue();
        assertEquals("test3", queue.front());

        queue.dequeue();
        assertThrows(java.util.NoSuchElementException.class, () -> {
            queue.front();
        });
    }

    @Test
    void testToString() {
        Queue<Integer> queue = new Queue<>(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals("[1, 2, 3]", queue.toString());
    }
}
