package leetcode.linkedlists;

public class HasCycle {

	public static void main(String[] args) {
		ListNode l1 = new ListNode(2, null);
		ListNode l2 = new ListNode(5, l1);
		ListNode l3 = new ListNode(3, l2);
		check(l3, false);
		l1.next = l3;
		check(l3, true);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/linked-list-cycle. This
	 * solution uses a slow and a fast pointer and moves the fast pointer at double
	 * the speed of the slow one. If the list has a cycle the two pointers will meet
	 * at the same node. Time complexity is O(n) where n is the number of nodes in
	 * the list.
	 * 
	 * @param head
	 * @return
	 */
	public static boolean hasCycle(ListNode head) {
		if (head == null) {
			return false;
		}
		ListNode nextSlow = head;
		ListNode nextFast = head;
		while (nextSlow.next != null && nextFast.next != null && nextFast.next.next != null) {
			nextSlow = nextSlow.next;
			nextFast = nextFast.next.next;
			if (nextSlow == nextFast) {
				return true;
			}
		}
		return false;
	}

	private static void check(ListNode head, boolean expected) {
		System.out.println("head is: " + head.printAll());
		System.out.println("expected is: " + expected);
		boolean hasCycle = hasCycle(head);
		System.out.println("hasCycle is: " + hasCycle);
	}

}
