package leetcode.linkedlists;

public class ReverseLinkedList {

	public static void main(String[] args) {
		ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
		ListNode expectedReversedList1 = new ListNode(5,
				new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(1)))));
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
