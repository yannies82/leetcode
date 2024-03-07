package leetcode.linkedlists;

public class MiddleOfTheLinkedList {

	public static void main(String[] args) {
		ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
		ListNode l2 = new ListNode(1,
				new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6, null))))));
		check(l1, 3);
		check(l2, 4);
	}

	/**
	 * This solution uses a fast pointer, which traverses in steps of 2 nodes and a
	 * slow pointer which traverses a single node in each step. When the fast
	 * pointer reaches the end of the list then the slow pointer is at the middle.
	 * Time complexity is O(n) where n is the length of the linked list.
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode middleNode(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		while (fast.next != null) {
			slow = slow.next;
			fast = fast.next;
			if (fast.next != null) {
				fast = fast.next;
			}
		}
		return slow;
	}

	private static void check(ListNode head, int expected) {
		System.out.println("head is: " + head.printAll());
		System.out.println("expected is: " + expected);
		ListNode middleNode = middleNode(head);
		System.out.println("middleNode is: " + middleNode.val);
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
