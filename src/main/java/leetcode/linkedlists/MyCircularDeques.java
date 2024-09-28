package leetcode.linkedlists;

import java.util.ArrayDeque;
import java.util.Deque;

public class MyCircularDeques {

	public static void main(String[] args) {
		MyCircularDeque deque = new MyCircularDeque(3);
		System.out.println("insertLast 1 result is: " + deque.insertLast(1) + ", expected is: " + true);
		System.out.println("insertLast 2 result is: " + deque.insertLast(2) + ", expected is: " + true);
		System.out.println("insertFront 3 result is: " + deque.insertFront(3) + ", expected is: " + true);
		System.out.println("insertFront 4 result is: " + deque.insertFront(4) + ", expected is: " + false);
		System.out.println("getRear result is: " + deque.getRear() + ", expected is: " + 2);
		System.out.println("isFull result is: " + deque.isFull() + ", expected is: " + true);
		System.out.println("isEmpty result is: " + deque.isEmpty() + ", expected is: " + false);
		System.out.println("deleteLast result is: " + deque.deleteLast() + ", expected is: " + true);
		System.out.println("insertFront 4 result is: " + deque.insertFront(4) + ", expected is: " + true);
		System.out.println("getFront result is: " + deque.getFront() + ", expected is: " + 4);
		System.out.println("deleteFront result is: " + deque.deleteFront() + ", expected is: " + true);
		MyCircularDeque1 deque1 = new MyCircularDeque1(3);
		System.out.println("insertLast 1 result is: " + deque1.insertLast(1) + ", expected is: " + true);
		System.out.println("insertLast 2 result is: " + deque1.insertLast(2) + ", expected is: " + true);
		System.out.println("insertFront 3 result is: " + deque1.insertFront(3) + ", expected is: " + true);
		System.out.println("insertFront 4 result is: " + deque1.insertFront(4) + ", expected is: " + false);
		System.out.println("getRear result is: " + deque1.getRear() + ", expected is: " + 2);
		System.out.println("isFull result is: " + deque1.isFull() + ", expected is: " + true);
		System.out.println("isEmpty result is: " + deque1.isEmpty() + ", expected is: " + false);
		System.out.println("deleteLast result is: " + deque1.deleteLast() + ", expected is: " + true);
		System.out.println("insertFront 4 result is: " + deque1.insertFront(4) + ", expected is: " + true);
		System.out.println("getFront result is: " + deque1.getFront() + ", expected is: " + 4);
		System.out.println("deleteFront result is: " + deque1.deleteFront() + ", expected is: " + true);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/design-circular-deque. This
	 * solution uses a custom bi-directional linked list to keep the inserted
	 * elements. Time complexity of all operations is O(1).
	 * 
	 * @author yanni
	 *
	 */
	private static class MyCircularDeque {

		Node head;
		Node tail;
		int capacity;
		int size = 0;

		public MyCircularDeque(int k) {
			capacity = k;
		}

		public boolean insertFront(int value) {
			if (size == capacity) {
				return false;
			}
			if (head == null) {
				head = tail = new Node(value);
				size++;
				return true;
			}
			Node temp = head;
			head = new Node(value, null, head);
			temp.prev = head;
			size++;
			return true;
		}

		public boolean insertLast(int value) {
			if (size == capacity) {
				return false;
			}
			if (head == null) {
				head = tail = new Node(value);
				size++;
				return true;
			}
			Node temp = tail;
			tail = new Node(value, tail, null);
			temp.next = tail;
			size++;
			return true;
		}

		public boolean deleteFront() {
			if (size == 0) {
				return false;
			}
			if (size == 1) {
				head = tail = null;
				size = 0;
				return true;
			}
			head = head.next;
			head.prev = null;
			size--;
			return true;
		}

		public boolean deleteLast() {
			if (size == 0) {
				return false;
			}
			if (size == 1) {
				head = tail = null;
				size = 0;
				return true;
			}
			tail = tail.prev;
			tail.next = null;
			size--;
			return true;
		}

		public int getFront() {
			if (head == null) {
				return -1;
			}
			return head.val;
		}

		public int getRear() {
			if (tail == null) {
				return -1;
			}
			return tail.val;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public boolean isFull() {
			return size == capacity;
		}

		private static class Node {
			int val;
			Node prev;
			Node next;

			public Node(int val) {
				super();
				this.val = val;
			}

			public Node(int val, Node prev, Node next) {
				super();
				this.val = val;
				this.prev = prev;
				this.next = next;
			}
		}
	}

	/**
	 * Implementation using a standard ArrayDeque. Time complexity for all
	 * operations is O(1).
	 * 
	 * @author yanni
	 *
	 */
	private static class MyCircularDeque1 {

		Deque<Integer> deque;
		int capacity;

		public MyCircularDeque1(int k) {
			deque = new ArrayDeque<>(k);
			capacity = k;
		}

		public boolean insertFront(int value) {
			if (deque.size() == capacity) {
				return false;
			}
			return deque.offerFirst(value);
		}

		public boolean insertLast(int value) {
			if (deque.size() == capacity) {
				return false;
			}
			return deque.offerLast(value);
		}

		public boolean deleteFront() {
			return deque.pollFirst() != null;
		}

		public boolean deleteLast() {
			return deque.pollLast() != null;
		}

		public int getFront() {
			Integer value = deque.peekFirst();
			return value == null ? -1 : value;
		}

		public int getRear() {
			Integer value = deque.peekLast();
			return value == null ? -1 : value;
		}

		public boolean isEmpty() {
			return deque.isEmpty();
		}

		public boolean isFull() {
			return deque.size() == capacity;
		}
	}

}
