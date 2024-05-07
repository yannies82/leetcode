package leetcode.linkedlists;

public class DoubleANumberRepresentedAsALinkedList {

	public static void main(String[] args) {
		ListNode list1 = ListNode.createList(1, 8, 9);
		ListNode expectedList1 = ListNode.createList(3, 7, 8);
		check(list1, expectedList1);
		list1 = ListNode.createList(9, 9, 9);
		expectedList1 = ListNode.createList(1, 9, 9, 8);
		check(list1, expectedList1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/double-a-number-represented-as-a-linked-list.
	 * Iterative solution which calculates the double of each node and uses it for
	 * the calculation of both the current level sum and the next level carry. Time
	 * complexity is O(n) where n is the number of elements in the linked list.
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode doubleIt(ListNode head) {
		ListNode prevHead = new ListNode(0, head);
		ListNode current = prevHead;
		// keeps the double value of the current element
		int currentVal = 0;
		while (current.next != null) {
			// calculate the double value of the next element
			int nextVal = current.next.val << 1;
			// calculate the carry for the current level
			int carry = nextVal / 10;
			// calculate the current element value
			current.val = (currentVal + carry) % 10;
			current = current.next;
			// update current value for the next level
			currentVal = nextVal;
		}
		// calculate last digit value
		current.val = currentVal % 10;
		// check if there is a carry on the highest level
		if (prevHead.val == 0) {
			return head;
		}
		return prevHead;
	}

	/**
	 * This solution updates recursively each element, calculating the carry and
	 * passing it to the next order digit. Time complexity is O(n) where n is the
	 * number of elements in the linked list.
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode doubleIt2(ListNode head) {
		ListNode prevHead = new ListNode(0, head);
		doubleAndReturnCarry(prevHead);
		if (prevHead.val == 0) {
			return head;
		}
		return prevHead;
	}

	private static int doubleAndReturnCarry(ListNode node) {
		if (node == null) {
			return 0;
		}
		// update next element and calculate carry
		int carry = doubleAndReturnCarry(node.next);
		// double current element and add carry to it
		int newVal = (node.val << 1) + carry;
		node.val = newVal % 10;
		return newVal / 10;
	}

	private static void check(ListNode head, ListNode expected) {
		System.out.println("head is: " + (head == null ? null : head.printAll()));
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		ListNode doubleIt = doubleIt(head);
		System.out.println("doubleIt is: " + (doubleIt == null ? null : doubleIt.printAll()));
	}

}
