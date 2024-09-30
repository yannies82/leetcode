package leetcode.linkedlists;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CustomStacks {

	public static void main(String[] args) {
		CustomStack stack = new CustomStack(3);
		check(stack::push, stack::pop, stack::increment);
		CustomStack2 stack2 = new CustomStack2(3);
		check(stack2::push, stack2::pop, stack2::increment);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/design-a-stack-with-increment-operation. This
	 * solution uses an array. Time complexity for push and pop methods is O(1) and
	 * time complexity for increment method is O(k).
	 * 
	 * @author yanni
	 *
	 */
	private static class CustomStack {

		int[] stack = new int[1000];
		int maxSize;
		int size = 0;

		public CustomStack(int maxSize) {
			this.maxSize = maxSize;
		}

		public void push(int x) {
			if (size == maxSize) {
				return;
			}
			stack[size++] = x;
		}

		public int pop() {
			if (size == 0) {
				return -1;
			}
			return stack[--size];
		}

		public void increment(int k, int val) {
			int limit = Math.min(size, k);
			for (int i = 0; i < limit; i++) {
				stack[i] += val;
			}
		}
	}

	/**
	 * Alternative solution using a double ended linked list. Time complexity for
	 * push and pop methods is O(1) and time complexity for increment method is
	 * O(k).
	 * 
	 * @author yanni
	 *
	 */
	private static class CustomStack2 {

		Node head;
		Node tail;
		int maxSize;
		int size = 0;

		public CustomStack2(int maxSize) {
			this.maxSize = maxSize;
		}

		public void push(int x) {
			if (size == maxSize) {
				return;
			}
			if (head == null) {
				head = tail = new Node(x);
			} else {
				Node temp = head;
				head = new Node(x, null, head);
				temp.prev = head;
			}
			size++;
		}

		public int pop() {
			if (size == 0) {
				return -1;
			}
			int value = head.val;
			if (size == 1) {
				head = tail = null;
			} else {
				head = head.next;
				head.prev = null;
			}
			size--;
			return value;
		}

		public void increment(int k, int val) {
			Node current = tail;
			int count = 0;
			while (current != null && count < k) {
				current.val += val;
				current = current.prev;
				count++;
			}
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

	private static void check(Consumer<Integer> push, Supplier<Integer> pop, BiConsumer<Integer, Integer> increment) {
		push.accept(1);
		push.accept(2);
		System.out.println("pop result is: " + pop.get() + ", expected is: " + 2);
		push.accept(2);
		push.accept(3);
		push.accept(4);
		increment.accept(5, 100);
		increment.accept(2, 100);
		System.out.println("pop result is: " + pop.get() + ", expected is: " + 103);
		System.out.println("pop result is: " + pop.get() + ", expected is: " + 202);
		System.out.println("pop result is: " + pop.get() + ", expected is: " + 201);
		System.out.println("pop result is: " + pop.get() + ", expected is: " + -1);
	}

}
