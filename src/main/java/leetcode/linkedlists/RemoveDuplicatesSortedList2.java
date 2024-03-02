package leetcode.linkedlists;

public class RemoveDuplicatesSortedList2 {

	public static void main(String[] args) {
		ListNode list1 = new ListNode(1,
				new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(4, new ListNode(4, new ListNode(5)))))));
		ListNode expectedList1 = new ListNode(1, new ListNode(2, new ListNode(5)));
		check(list1, expectedList1);
		list1 = new ListNode(1, new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3)))));
		expectedList1 = new ListNode(2, new ListNode(3));
		check(list1, expectedList1);
	}

	public static ListNode deleteDuplicates(ListNode head) {
		// early exit if head is null
		if (head == null) {
			return null;
		}
		ListNode prev = null;
		ListNode current = head;
		int number = head.val;
		int count = 1;
		// iterate the list, keep track of the consecutive nodes with same number
		while (current != null) {
			if (current.next == null || current.next.val != number) {
				// the next node is null or has a different number than the previous one
				if (count == 1) {
					// the previous number was encountered exactly 1 time, just update
					// the previous number node
					prev = current;
				} else {
					// the previous number was encountered more than 1 times
					if (prev == null) {
						// if no prev node exists, bypass the duplicate nodes by resetting the head
						head = current.next;
					} else {
						// if a prev node exists, bypass the duplicate nodes by setting its
						// next property appropriately
						prev.next = current.next;
					}
					// reset the count for the new number
					count = 1;
				}
				// set the new number from the current node
				number = current.next == null ? Integer.MIN_VALUE : current.next.val;
			} else {
				// the previous node has the same number as the current one, increase the count
				count++;
			}
			current = current.next;
		}
		return head;
	}

	private static void check(ListNode head, ListNode expected) {
		System.out.println("head is: " + (head == null ? null : head.printAll()));
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		ListNode deleteDuplicates = deleteDuplicates(head);
		System.out.println("deleteDuplicates is: " + (deleteDuplicates == null ? null : deleteDuplicates.printAll()));
	}

	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
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
	}

}
