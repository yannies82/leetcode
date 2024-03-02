package leetcode.linkedlists;

public class MergeTwoSortedLists {

	public static void main(String[] args) {
		ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4, null)));
		ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4, null)));
		ListNode expected = new ListNode(1,
				new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(4, null))))));
		check(list1, list2, expected);
		check(null, null, null);
		check(null, new ListNode(0, null), new ListNode(0, null));
	}

	public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		ListNode head = null;
		ListNode current = null;
		while (list1 != null || list2 != null) {
			ListNode next;
			if (list1 == null) {
				next = list2;
				list2 = list2.next;
			} else if (list2 == null) {
				next = list1;
				list1 = list1.next;
			} else if (list1.val <= list2.val) {
				next = list1;
				list1 = list1.next;
			} else {
				next = list2;
				list2 = list2.next;
			}
			if (head == null) {
				head = next;
			} else {
				current.next = next;
			}
			current = next;
		}
		return head;
	}

	private static void check(ListNode list1, ListNode list2, ListNode expected) {
		System.out.println("list1 is: " + (list1 == null ? null : list1.printAll()));
		System.out.println("list2 is: " + (list2 == null ? null : list2.printAll()));
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		ListNode mergeTwoLists = mergeTwoLists(list1, list2);
		System.out.println("mergeTwoLists is: " + (mergeTwoLists == null ? null : mergeTwoLists.printAll()));
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
