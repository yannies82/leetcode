package leetcode.linkedlists;

public class AddTwoNumbers {

	public static void main(String[] args) {
		ListNode l1 = ListNode.createList(2, 4, 3);
		ListNode l2 = ListNode.createList(5, 6, 4);
		ListNode expected = ListNode.createList(7, 0, 8);
		check(l1, l2, expected);

		l1 = new ListNode(0);
		l2 = new ListNode(0);
		check(l1, l2, new ListNode(0));

		l1 = ListNode.createList(9, 9, 9, 9, 9, 9, 9);
		l2 = ListNode.createList(9, 9, 9, 9);
		expected = ListNode.createList(8, 9, 9, 9, 0, 0, 0, 1);
		check(l1, l2, expected);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/add-two-numbers. This
	 * solution traverses simultaneously the two lists, adding the numbers and
	 * keeping the remainder for the next position. Time complexity is O(n) where n
	 * is the number of nodes in the bigger of the two lists.
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode head = new ListNode(0);
		ListNode current = head;
		while (current != null) {
			int sum = current.val;
			if (l1 != null) {
				sum += l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				sum += l2.val;
				l2 = l2.next;
			}
			current.val = (sum % 10);
			int remainder = sum / 10;
			if (l1 != null || l2 != null || remainder > 0) {
				// initialize sum of next position with the remainder of this position
				current.next = new ListNode(remainder);
			}
			current = current.next;
		}
		return head;
	}

	private static void check(ListNode l1, ListNode l2, ListNode expected) {
		System.out.println("l1 is: " + l1.printAll());
		System.out.println("l2 is: " + l2.printAll());
		System.out.println("expected is: " + expected.printAll());
		System.out.println("l1 + l2 is: " + addTwoNumbers(l1, l2).printAll());
		System.out.println("l1 decimal is: " + l1.getNumber());
		System.out.println("l2 decimal is: " + l2.getNumber());
		System.out.println("l1 + l2 decimal is: " + addTwoNumbers(l1, l2).getNumber());
	}

}
