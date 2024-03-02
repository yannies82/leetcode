package leetcode.linkedlists;

import java.util.HashMap;
import java.util.Map;

public class LRUCaches {

	public static void main(String[] args) {

		LRUCache cache = new LRUCache(1);
		cache.put(2, 1);
		cache.get(2);
		cache.put(3, 2);
		cache.get(2);
		cache.get(3);
	}

	/**
	 * Implementation with doubly linked list and map.
	 * 
	 * @author yanni
	 *
	 */
	public static class LRUCache {

		private int capacity;
		private int size;
		private ListNode head;
		private ListNode tail;
		private Map<Integer, ListNode> cache;

		public LRUCache(int capacity) {
			super();
			this.capacity = capacity;
			this.cache = new HashMap<>();
		}

		public int get(int key) {
			// check if the cache contains the key and get the value
			ListNode current = cache.get(key);
			if (current == null) {
				return -1;
			}
			// move the ListNode to the head of the list before
			// returning its value
			moveToHead(current);
			return current.value;
		}

		public void put(int key, int value) {
			ListNode current = cache.get(key);
			if (current == null) {
				// the cache did not contain the key, create a new ListNode and
				// put it in the cache and place it at the head of the list
				current = new ListNode(key, value, null, head);
				if (head != null) {
					head.prev = current;
				}
				head = current;
				if (tail == null) {
					// if this is the first element set it as tail too
					tail = current;
				}
				if (size == capacity) {
					// the list has reached its capacity, remove the element at tail
					// from the cache and adjust the tail pointer
					cache.remove(tail.key);
					ListNode tailPrev = tail.prev;
					if (tailPrev != null) {
						tailPrev.next = null;
					}
					tail.prev = null;
					tail = tailPrev;
				} else {
					// the list has not reached its capacity, increase the size
					size++;
				}
				// add the element to the cache
				cache.put(key, current);
			} else {
				// the cache contains the key
				// update the cache
				current.value = value;
				// move the element to the head of the list
				moveToHead(current);
			}
		}

		private void moveToHead(ListNode current) {
			if (current.prev == null) {
				// nothing to move, the element is already at head
				return;
			}
			current.prev.next = current.next;
			if (current.next != null) {
				current.next.prev = current.prev;
			}
			if (tail == current) {
				// if the element was the tail one, the tail has to be adjusted
				tail = current.prev;
			}
			head.prev = current;
			current.prev = null;
			current.next = head;
			head = current;
		}

		private static class ListNode {
			int key;
			int value;
			ListNode prev;
			ListNode next;

			public ListNode(int key, int value, ListNode prev, ListNode next) {
				super();
				this.key = key;
				this.value = value;
				this.prev = prev;
				this.next = next;
			}

		}

	}

}
