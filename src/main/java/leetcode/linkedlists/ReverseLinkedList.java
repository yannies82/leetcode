package leetcode.linkedlists;

public class ReverseLinkedList {

	public static void main(String[] args) {
		ListNode list1 = ListNode.createList(1, 2, 3, 4, 5);
		ListNode expectedReversedList1 = ListNode.createList(5, 4, 3, 2, 1);
		check(list1, expectedReversedList1);
		check(new ListNode(5), new ListNode(5));
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/reverse-linked-list. Time
	 * complexity is O(n) where n is the number of nodes in the list.
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode reverseList(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode current = head;
		ListNode prev = null;
		while (current.next != null) {
			ListNode temp = current;
			current = current.next;
			temp.next = prev;
			prev = temp;
		}
		current.next = prev;
		return current;
	}

	private static void check(ListNode head, ListNode expected) {
		System.out.println("head is: " + (head == null ? null : head.printAll()));
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		ListNode reverseList = reverseList(head);
		System.out.println("reverseList is: " + (reverseList == null ? null : reverseList.printAll()));
	}

}
