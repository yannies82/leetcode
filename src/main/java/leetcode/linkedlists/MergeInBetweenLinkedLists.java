package leetcode.linkedlists;

public class MergeInBetweenLinkedLists {

	public static void main(String[] args) {
		ListNode list1 = ListNode.createList(10, 1, 13, 6, 9, 5);
		ListNode list2 = ListNode.createList(1000000, 1000001, 1000002);
		ListNode expected = ListNode.createList(10, 1, 13, 1000000, 1000001, 1000002, 5);
		check(list1, 3, 4, list2, expected);
		list1 = ListNode.createList(0, 1, 2, 3, 4, 5, 6);
		list2 = ListNode.createList(1000000, 1000001, 1000002, 1000003, 1000004);
		expected = ListNode.createList(0, 1, 1000000, 1000001, 1000002, 1000003, 1000004, 6);
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

}
