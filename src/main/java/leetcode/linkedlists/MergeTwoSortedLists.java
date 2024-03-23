package leetcode.linkedlists;

public class MergeTwoSortedLists {

	public static void main(String[] args) {
		ListNode list1 = ListNode.createList(1, 2, 4);
		ListNode list2 = ListNode.createList(1, 3, 4);
		ListNode expected = ListNode.createList(1, 1, 2, 3, 4, 4);
		check(list1, list2, expected);
		check(null, null, null);
		check(null, new ListNode(0), new ListNode(0));
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/merge-two-sorted-lists. This
	 * solution has a time complexity of O(n) where n is the length of the smaller
	 * of the two lists.
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		ListNode start = new ListNode(0);
		ListNode current = start;
		// while both lists have elements, compare the head elements and append the
		// smaller one
		while (list1 != null && list2 != null) {
			if (list1.val <= list2.val) {
				current.next = list1;
				list1 = list1.next;
			} else {
				current.next = list2;
				list2 = list2.next;
			}
			current = current.next;
		}
		if (list1 != null) {
			// if the first list still has elements append it to the result
			current.next = list1;
		} else if (list2 != null) {
			// if the second list still has elements append it to the result
			current.next = list2;
		}
		return start.next;
	}

	private static void check(ListNode list1, ListNode list2, ListNode expected) {
		System.out.println("list1 is: " + (list1 == null ? null : list1.printAll()));
		System.out.println("list2 is: " + (list2 == null ? null : list2.printAll()));
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		ListNode mergeTwoLists = mergeTwoLists(list1, list2);
		System.out.println("mergeTwoLists is: " + (mergeTwoLists == null ? null : mergeTwoLists.printAll()));
	}

}
