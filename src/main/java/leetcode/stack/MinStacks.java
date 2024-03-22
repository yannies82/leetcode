package leetcode.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MinStacks {

	/**
	 * Leetcode problem: https://leetcode.com/problems/min-stack. This solution uses
	 * two linked lists which operate as stacks. The first one keeps all added
	 * elements and the second one keeps the updated min whenever a new element is
	 * added. Time complexity for all operations is is O(1).
	 * 
	 * @author yanni
	 *
	 */
	public static class MinStack {

		private Element head;
		private Element minHead;

		public void push(int val) {
			head = new Element(val, head);
			int min = minHead == null ? Integer.MAX_VALUE : minHead.value;
			int newMin = val < min ? val : min;
			minHead = new Element(newMin, minHead);
		}

		public int pop() {
			int top = head.value;
			head = head.next;
			minHead = minHead.next;
			return top;
		}

		public int top() {
			return head.value;
		}

		public int getMin() {
			return minHead.value;
		}
	}

	public static class MinStack2 {

		private final Deque<Integer> deque = new ArrayDeque<>();
		private final List<Integer> minList = new ArrayList<>();

		public void push(int val) {
			deque.offerFirst(val);
			int min = minList.isEmpty() ? Integer.MAX_VALUE : getMin();
			if (val < min) {
				min = val;
			}
			minList.add(min);
		}

		public int pop() {
			minList.remove(minList.size() - 1);
			return deque.poll();
		}

		public Integer top() {
			return deque.peek();
		}

		public int getMin() {
			return minList.get(minList.size() - 1);
		}
	}

	private static class Element {
		int value;
		Element next;

		public Element(int value, Element next) {
			super();
			this.value = value;
			this.next = next;
		}

	}
}
