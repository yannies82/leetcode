package leetcode.linkedlists;

public class RemoveDuplicatesSortedList2 {

	public static void main(String[] args) {
		ListNode list1 = ListNode.createList(1, 2, 3, 3, 4, 4, 5);
		ListNode expectedList1 = ListNode.createList(1, 2, 5);
		check(list1, expectedList1);
		list1 = ListNode.createList(1, 1, 1, 2, 3);
		expectedList1 = ListNode.createList(2, 3);
		check(list1, expectedList1);
		list1 = ListNode.createList(1, 1);
		check(list1, null);
	}

	/**
	 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii. This
	 * solution iterates all nodes and keeps track of duplicate ones. When nodes
	 * with the same value are found, they are bypassed. Time complexity is O(n)
	 * where n is the number of nodes in the list.
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode deleteDuplicates(ListNode head) {
		// early exit if head is null
		if (head == null) {
			return null;
		}
		// create a dummy node before the list head to assist the operations
		ListNode prevHead = new ListNode(-1000, head);
		ListNode prev = prevHead;
		ListNode current = prev;
		int number = prev.val;
		int count = 1;
		// iterate the list, keep track of the consecutive nodes with same number
		while (current.next != null) {
			if (current.next.val != number) {
				// the next node has a different number than the previous one
				if (count == 1) {
					// the previous number was encountered exactly 1 time, just update
					// the previous number node
					prev = current;
				} else {
					// the previous number was encountered more than 1 times
					// bypass the duplicate nodes by setting the
					// next property of the prev element appropriately
					prev.next = current.next;
					// reset the count for the new number
					count = 1;
				}
				// set the new number from the current node
				number = current.next.val;
			} else {
				// the previous node has the same number as the current one, increase the count
				count++;
			}
			current = current.next;
		}
		// if the last elements were duplicates bypass them
		if (count > 1) {
			prev.next = null;
		}
		return prevHead.next;
	}

	private static void check(ListNode head, ListNode expected) {
		System.out.println("head is: " + (head == null ? null : head.printAll()));
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		ListNode deleteDuplicates = deleteDuplicates(head);
		System.out.println("deleteDuplicates is: " + (deleteDuplicates == null ? null : deleteDuplicates.printAll()));
	}

}
