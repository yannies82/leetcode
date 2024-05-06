package leetcode.linkedlists;

public class RemoveNodesFromLinkedList {

	public static void main(String[] args) {
		ListNode list1 = ListNode.createList(5, 2, 13, 3, 8);
		ListNode expectedList1 = ListNode.createList(13, 8);
		check(list1, expectedList1);
		list1 = ListNode.createList(1, 1, 1, 1);
		expectedList1 = ListNode.createList(1, 1, 1, 1);
		check(list1, expectedList1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/remove-nodes-from-linked-list. This solution
	 * reverses the list, then iterates the reversed list. Every time a descending
	 * sequence of elements is found, it is skipped. The list is re-reversed while
	 * skipping these elements. Time complexity is O(n) where n is the number of
	 * elements in the list.
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode removeNodes(ListNode head) {
		// reverse list
		ListNode prev = head;
		ListNode current = head.next;
		while (current != null) {
			ListNode next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		head.next = null;
		head = prev;
		current = head.next;
		// iterate reversed list
		while (current != null) {
			if (current.val < prev.val) {
				// next element is less than the previous one, skip it
				current = current.next;
			} else {
				// reverse the list again after skipping descending elements
				ListNode next = current.next;
				current.next = prev;
				prev = current;
				current = next;
			}
		}
		head.next = null;
		head = prev;
		return head;
	}

	/**
	 * This solution traverses the list and checks if the elements are in non
	 * ascending order. Every time an ascending element is found, all current
	 * elements with lesser values are removed. Time complexity is O(n^2) where n is
	 * the length of the linked list.
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode removeNodes2(ListNode head) {
		// dummy previous element to facilitate head node removal
		ListNode prevHead = new ListNode(1000000, head);
		ListNode current = prevHead;
		while (current.next != null) {
			if (current.val < current.next.val) {
				// the next element is greater than the current one
				// remove all elements from the start which are less than
				// the next element value
				ListNode start = prevHead;
				while (start.next.val >= current.next.val) {
					start = start.next;
				}
				start.next = current.next;
			}
			current = current.next;
		}
		return prevHead.next;
	}

	private static void check(ListNode head, ListNode expected) {
		System.out.println("head is: " + (head == null ? null : head.printAll()));
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		ListNode removeNodes = removeNodes(head);
		System.out.println("removeNodes is: " + (removeNodes == null ? null : removeNodes.printAll()));
	}

}
