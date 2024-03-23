package leetcode.linkedlists;

public class ReverseLinkedList2 {

	public static void main(String[] args) {
		ListNode list1 = ListNode.createList(1, 2, 3, 4, 5);
		ListNode expectedReversedList1 = ListNode.createList(1, 4, 3, 2, 5);
		check(list1, 2, 4, expectedReversedList1);
		check(new ListNode(5), 1, 1, new ListNode(5));
		check(new ListNode(3, new ListNode(5)), 1, 2, ListNode.createList(5, 3));
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/reverse-linked-list-ii. This
	 * solution skips the nodes until the left index, then reverses the sublist from
	 * left index to right index and appends it to the appropriate initial list
	 * nodes. Time complexity is O(n) where n is the number of nodes in the list.
	 * 
	 * @param head
	 * @param left
	 * @param right
	 * @return
	 */
	public static ListNode reverseBetween(ListNode head, int left, int right) {
		// create an element before head node to help with operations
		ListNode prevHead = new ListNode(0, head);
		ListNode current = prevHead;
		int index = 1;
		// iterate until we reach the element at the left index
		while (current != null && index < left) {
			current = current.next;
			index++;
		}
		// this is the element at index left-1, before the subList that will be reversed
		ListNode start = current;
		// this is the element at index left, the first element of the subList to be
		// reversed
		// it will be the last element of the reversed subList
		current = current.next;
		ListNode reverseEnd = current;
		ListNode prev = null;
		// iterate and reverse nodes until we reach the element at the right index
		while (current != null && index <= right) {
			ListNode temp = current;
			current = current.next;
			temp.next = prev;
			prev = temp;
			index++;
		}
		// append the head of the reversed sublist to the element before left index
		start.next = prev;
		// append the element after right index to the tail of the reversed sublist
		reverseEnd.next = current;
		return prevHead.next;
	}

	private static void check(ListNode head, int left, int right, ListNode expected) {
		System.out.println("head is: " + (head == null ? null : head.printAll()));
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		ListNode reverseBetween = reverseBetween(head, left, right);
		System.out.println("reverseBetween is: " + (reverseBetween == null ? null : reverseBetween.printAll()));
	}

}
