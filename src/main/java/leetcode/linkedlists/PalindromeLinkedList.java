package leetcode.linkedlists;

import java.util.ArrayDeque;
import java.util.Deque;

public class PalindromeLinkedList {

	public static void main(String[] args) {
		check(ListNode.createList(1, 2, 2, 1), true);
		check(ListNode.createList(1, 0, 0), false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/palindrome-linked-list. This
	 * solution finds the middle node, then reverses the second half of the list and
	 * checks that the nodes of the first and second half are equal. It uses O(1)
	 * extra space but is destructive to the input list. Time complexity is O(n)
	 * where n is the number of nodes in the list.
	 * 
	 * @param head
	 * @return
	 */
	public static boolean isPalindrome(ListNode head) {
		if (head == null || head.next == null) {
			// early exit in case of empty list or single node
			return true;
		}
		// find middle of the list
		ListNode slow = head;
		ListNode fast = head;
		while (fast.next != null) {
			slow = slow.next;
			fast = fast.next;
			if (fast.next != null) {
				fast = fast.next;
			}
		}
		// reverse second half of the list
		ListNode current = slow;
		ListNode prev = null;
		while (current.next != null) {
			ListNode temp = current;
			current = current.next;
			temp.next = prev;
			prev = temp;
		}
		current.next = prev;
		// compare first half of the list with second half
		ListNode first = head;
		ListNode second = current;
		while (second != null) {
			if (first.val != second.val) {
				// if the two nodes do not have the same value return false
				return false;
			}
			first = first.next;
			second = second.next;
		}
		return true;
	}

	/**
	 * This solution uses a stack to store the first half of the list, then pops
	 * each value from the stack and compares it with the next node value. Time
	 * complexity is O(n) where n is the number of nodes in the list.
	 * 
	 * @param head
	 * @return
	 */
	public static boolean isPalindrome2(ListNode head) {
		if (head == null || head.next == null) {
			// early exit in case of empty list or single node
			return true;
		}
		// find middle of the list
		// add all values to the stack until the middle of the list is reached
		ListNode slow = head;
		ListNode fast = head;
		Deque<Integer> stack = new ArrayDeque<>();
		stack.offerFirst(slow.val);
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			stack.offerFirst(slow.val);
		}
		// discard middle value if the list contains odd number of nodes
		if (fast.next == null) {
			stack.poll();
		}
		// iterate second half and compare node values with stack values
		while (slow.next != null) {
			slow = slow.next;
			if (slow.val != stack.poll()) {
				return false;
			}
		}
		return true;
	}

	private static void check(ListNode head, boolean expected) {
		System.out.println("head is: " + (head == null ? null : head.printAll()));
		System.out.println("expected is: " + expected);
		boolean isPalindrome = isPalindrome(head);
		System.out.println("isPalindrome is: " + isPalindrome);
	}

	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int val) {
			this.val = val;
		}

		String printAll() {
			ListNode current = this;
			StringBuilder result = new StringBuilder();
			do {
				if (!result.isEmpty()) {
					result.append(",");
				}
				result.append(current.val);
				current = current.next;
			} while (current != null);
			return result.toString();
		}

		private static ListNode createList(int... values) {
			ListNode start = new ListNode(0);
			ListNode current = start;
			for (int val : values) {
				current.next = new ListNode(val);
				current = current.next;
			}
			return start.next;
		}
	}

}
