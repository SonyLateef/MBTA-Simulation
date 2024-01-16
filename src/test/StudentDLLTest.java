package test;

import org.junit.jupiter.api.Test;

import main.DoubleLinkedList;
import static org.junit.jupiter.api.Assertions.*;

class DoubleLinkedListTest {

	@Test
	void testInsertAndSize() {
		DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
		list.insert(1);
		list.insert(2);
		list.insert(3);
		assertEquals(3, list.size());
	}

	@Test
	void testDelete() {
		DoubleLinkedList<String> list = new DoubleLinkedList<>();
		list.insert("test1");
		list.insert("test2");
		list.insert("test3");

		String deleted = list.delete("test2");
		assertEquals("test2", deleted);
		assertNull(list.get("test2"));
		assertEquals(2, list.size());
	}

	@Test
	void testGet() {
		DoubleLinkedList<String> list = new DoubleLinkedList<>();
		list.insert("test1");
		list.insert("test2");
		list.insert("test3");

		assertEquals("test2", list.get("test2"));
		assertNull(list.get("test4"));
	}

	@Test
	void testToString() {
		DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
		list.insert(1);
		list.insert(2);
		list.insert(3);

		assertEquals("[1, 2, 3]", list.toString());
	}
}
