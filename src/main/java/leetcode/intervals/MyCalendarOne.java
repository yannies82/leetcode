package leetcode.intervals;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class MyCalendarOne {

	public static void main(String[] args) {
		MyCalendar calendar = new MyCalendar();
		check(10, 20, calendar::book, true);
		check(15, 25, calendar::book, false);
		check(20, 30, calendar::book, true);
		MyCalendar2 calendar2 = new MyCalendar2();
		check(10, 20, calendar2::book, true);
		check(15, 25, calendar2::book, false);
		check(20, 30, calendar2::book, true);
		MyCalendar3 calendar3 = new MyCalendar3();
		check(10, 20, calendar3::book, true);
		check(15, 25, calendar3::book, false);
		check(20, 30, calendar3::book, true);
		MyCalendar4 calendar4 = new MyCalendar4();
		check(10, 20, calendar4::book, true);
		check(15, 25, calendar4::book, false);
		check(20, 30, calendar4::book, true);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/my-calendar-i. This solution
	 * uses a tree structure to keep the events. Time complexity is between O(logn)
	 * and O(n) where n is the number of elements in the tree, depending on whether
	 * the tree is balanced or not.
	 * 
	 * @author yanni
	 *
	 */
	private static class MyCalendar {

		TreeNode root;

		public MyCalendar() {

		}

		public boolean book(int start, int end) {
			if (root == null) {
				// add first event as the root node
				root = new TreeNode(start, end);
				return true;
			}
			TreeNode current = root;
			while (current != null) {
				if (end <= current.start) {
					// the event should be placed on the left subtree
					if (current.left == null) {
						current.left = new TreeNode(start, end);
						return true;
					}
					current = current.left;
				} else if (start >= current.end) {
					// the event should be placed on the right subtree
					if (current.right == null) {
						current.right = new TreeNode(start, end);
						return true;
					}
					current = current.right;
				} else {
					// there is overlap, return false
					return false;
				}
			}
			return false;
		}

		private static class TreeNode {

			int start;
			int end;
			TreeNode left;
			TreeNode right;

			public TreeNode(int start, int end) {
				super();
				this.start = start;
				this.end = end;
			}
		}
	}

	/**
	 * This solution uses an arraylist to keep the events sorted by start. For every
	 * new event binary search is performed to find the index it should be placed in
	 * the list and it is compared to the previous and next element to check if
	 * there is overlap. Worst case time complexity for book is O(nlogn) where n is
	 * the number of events in the calendar.
	 * 
	 * @author yanni
	 *
	 */
	private static class MyCalendar2 {

		List<int[]> events = new ArrayList<>();

		public MyCalendar2() {

		}

		public boolean book(int start, int end) {
			int size = events.size();
			// perform binary search to find the index where the event should be placed
			int index = findIndex(size, start);
			if ((index > 0 && start < events.get(index - 1)[1]) || (index < size && end > events.get(index)[0])) {
				// check if there is overlap with the previous and next element
				return false;
			}
			// add element
			events.add(index, new int[] { start, end });
			return true;
		}

		/**
		 * Returns the index right after the element with the greatest start value that
		 * is less than or equal to i.
		 * 
		 * @param size
		 * @param i
		 * @return
		 */
		private int findIndex(int size, int i) {
			int start = 0;
			int end = size;
			int result = 0;
			while (start < end) {
				int mid = (start + end) / 2;
				if (i >= events.get(mid)[0]) {
					start = mid + 1;
					result = start;
				} else {
					end = mid;
				}
			}
			return result;
		}
	}

	/**
	 * This solution uses a linked list to keep the events in a sorted order. Time
	 * complexity is O(n) where n is the number of events in the calendar.
	 * 
	 * @author yanni
	 *
	 */
	private static class MyCalendar3 {

		Node prevRoot = new Node(-1, -1, null);

		public MyCalendar3() {

		}

		public boolean book(int start, int end) {
			Node current = prevRoot;
			Node next;
			while ((next = current.next) != null && next.start < start) {
				current = current.next;
			}
			if ((current.end > start) || (current.next != null && current.next.start < end)) {
				return false;
			}
			current.next = new Node(start, end, next);
			return true;

		}
	}

	private static class Node {

		int start;
		int end;
		Node next;

		public Node(int start, int end, Node next) {
			super();
			this.start = start;
			this.end = end;
			this.next = next;
		}
	}

	/**
	 * Simple but effective solution using arraylist. Time complexity for the book
	 * operation is O(n) where n is the number of events in the calendar.
	 * 
	 * @author yanni
	 *
	 */
	private static class MyCalendar4 {

		int[][] events = new int[1000][2];

		int eventsIndex = 0;

		public boolean book(int start, int end) {
			for (int i = 0; i < eventsIndex; i++) {
				if (Math.max(events[i][0], start) < Math.min(events[i][1], end)) {
					return false;
				}
			}
			events[eventsIndex][0] = start;
			events[eventsIndex++][1] = end;
			return true;
		}
	}

	private static void check(int start, int end, BiFunction<Integer, Integer, Boolean> function, boolean expected) {
		System.out.println(
				"book [" + start + ", " + end + "] is: " + function.apply(start, end) + ", expected is: " + expected);
	}

}
