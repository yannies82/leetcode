package leetcode.divideandconquer;

public class SortList {

	public static void main(String[] args) {
		ListNode head = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));
		ListNode expected = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
		check(head, expected);
		head = new ListNode(-1, new ListNode(5, new ListNode(3, new ListNode(4, new ListNode(0)))));
		expected = new ListNode(-1, new ListNode(0, new ListNode(3, new ListNode(4, new ListNode(5)))));
		check(head, expected);
		check(null, null);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/sort-list. Merge sort
	 * implementation, splits the list in two subLists and merges them recursively.
	 * Time complexity is O(N*logN) where N is the size of the list.
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode sortList(ListNode head) {
		// early exit if the list has 0 or 1 elements
		if (head == null || head.next == null) {
			return head;
		}
		// split the list in two
		// pointer which will point to the middle of the list
		ListNode mid = head;
		// pointer which will reach the end of the list
		ListNode end = head.next;
		while (end != null && end.next != null) {
			// increase mid pointer
			mid = mid.next;
			// increase end pointer by two
			end = end.next.next;
		}
		// this will be the head of the second sub list
		ListNode secondHead = mid.next;
		// set the tail of the first sub list to point to null
		mid.next = null;
		// recursively sort first list
		ListNode first = sortList(head);
		// recursively sort second list
		ListNode second = sortList(secondHead);
		// merge lists and return head
		return mergeSort(first, second);
	}

	private static ListNode mergeSort(ListNode first, ListNode second) {
		// select the smaller of the subList heads as the merged list head
		ListNode head;
		if (first.val > second.val) {
			head = second;
			second = second.next;
		} else {
			head = first;
			first = first.next;
		}
		ListNode current = head;
		// iterate while both lists have elements
		while (first != null && second != null) {
			// for each iteration add the smaller element from the two
			// lists to the merged list and advance the respective head
			if (first.val > second.val) {
				current.next = second;
				second = second.next;
			} else {
				current.next = first;
				first = first.next;
			}
			current = current.next;
		}
		// if the first list still has elements append them
		if (first != null) {
			current.next = first;
		}
		// if the second list still has elements append them
		if (second != null) {
			current.next = second;
		}
		return head;
	}

	private static class ListNode {
		int val;
		ListNode next;

		public ListNode(int val) {
			this.val = val;
		}

		public ListNode(int val, ListNode next) {
			super();
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

	private static void check(ListNode head, ListNode expected) {
		System.out.println("head is: " + (head == null ? null : head.printAll()));
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		ListNode sortList = sortList(head); // Calls your implementation
		System.out.println("sortList is: " + (sortList == null ? null : sortList.printAll()));
	}
}
