package leetcode.linkedlists;

public class MergeInBetweenLinkedLists {

	public static void main(String[] args) {
		ListNode list1 = new ListNode(10,
				new ListNode(1, new ListNode(13, new ListNode(6, new ListNode(9, new ListNode(5, null))))));
		ListNode list2 = new ListNode(1000000, new ListNode(1000001, new ListNode(1000002, null)));
		ListNode expected = new ListNode(10, new ListNode(1, new ListNode(13,
				new ListNode(1000000, new ListNode(1000001, new ListNode(1000002, new ListNode(5, null)))))));
		check(list1, 3, 4, list2, expected);
		list1 = new ListNode(0, new ListNode(1,
				new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6, null)))))));
		list2 = new ListNode(1000000,
				new ListNode(1000001, new ListNode(1000002, new ListNode(1000003, new ListNode(1000004, null)))));
		expected = new ListNode(0, new ListNode(1, new ListNode(1000000, new ListNode(1000001,
				new ListNode(1000002, new ListNode(1000003, new ListNode(1000004, new ListNode(6, null))))))));
		check(list1, 2, 5, list2, expected);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/merge-in-between-linked-lists. This solution
	 * uses a counter to find the nodes where the second list will be intersected.
	 * Time complexity is O(b).
	 * 
	 * 
	 * @param list1
	 * @param a
	 * @param b
	 * @param list2
	 * @return
	 */
	public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
		ListNode tail2 = list2;
		// find tail node of list2
		while (tail2.next != null) {
			tail2 = tail2.next;
		}
		int count = 1;
		ListNode current = list1;
		// count all nodes until the one before a
		while (current.next != null && count < a) {
			current = current.next;
			count++;
		}
		// append head of list2 to the node before a
		ListNode temp = current;
		current = current.next;
		temp.next = list2;
		// count all nodes until b
		while (current.next != null && count < b) {
			current = current.next;
			count++;
		}
		// append node after b to tail of list2
		tail2.next = current.next;
		return list1;
	}

	private static void check(ListNode list1, int a, int b, ListNode list2, ListNode expected) {
		System.out.println("list1 is: " + (list1 == null ? null : list1.printAll()));
		System.out.println("a is: " + a);
		System.out.println("b is: " + b);
		System.out.println("list2 is: " + (list2 == null ? null : list2.printAll()));
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		ListNode mergeInBetween = mergeInBetween(list1, a, b, list2);
		System.out.println("mergeTwoLists is: " + (mergeInBetween == null ? null : mergeInBetween.printAll()));
	}

	private static class ListNode {

		int val;
		ListNode next;

		ListNode(int val, ListNode next) {
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
}
