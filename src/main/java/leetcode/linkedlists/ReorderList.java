package leetcode.linkedlists;

public class ReorderList {

	public static void main(String[] args) {
		check(ListNode.createList(1, 2, 3, 4), ListNode.createList(1, 4, 2, 3));
		check(ListNode.createList(1, 2, 3, 4, 5), ListNode.createList(1, 5, 2, 4, 3));
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/reorder-list. This solution
	 * finds the middle of the list and cuts off the first half from the second half
	 * into two separate lists. It then reverses the second half and finally merges
	 * the two halves into a single list. Time complexity is O(n) where n is the
	 * number of nodes in the list.
	 * 
	 * @param head
	 */
	public static void reorderList(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			// early exit in case of empty list or single node
			return;
		}
		// find middle of the list
		ListNode slow = head;
		ListNode fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		// cut off the first half from the second half
		ListNode lastFirst = slow;
		slow = slow.next;
		lastFirst.next = null;
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
		// merge first and second half of the list
		ListNode first = head;
		ListNode second = current;
		while (second != null) {
			ListNode temp = first.next;
			first.next = second;
			first = temp;
			temp = second.next;
			second.next = first;
			second = temp;
		}
	}

	private static void check(ListNode head, ListNode expected) {
		System.out.println("head is: " + (head == null ? null : head.printAll()));
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		reorderList(head);
		System.out.println("reorderList is: " + head.printAll());
	}

}
