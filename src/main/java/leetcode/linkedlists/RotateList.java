package leetcode.linkedlists;

public class RotateList {

	public static void main(String[] args) {
		ListNode list1 = ListNode.createList(1, 2, 3, 4, 5);
		ListNode expectedList1 = ListNode.createList(4, 5, 1, 2, 3);
		check(list1, 2, expectedList1);
		list1 = ListNode.createList(0, 1, 2);
		expectedList1 = ListNode.createList(2, 0, 1);
		check(list1, 4, expectedList1);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/rotate-list. This solution
	 * iterates the list to find its length then finds the actual number of
	 * rotations that should be performed. Finally it iterates the list again to
	 * find the node which will be the new tail and performs the rotation by
	 * adjusting the next pointers of the head, tail and the new tail. Time
	 * complexity is O(n) where n is the number of nodes in the list.
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	public static ListNode rotateRight(ListNode head, int k) {
		// early exit if list is empty
		if (head == null) {
			return null;
		}
		// early exit if k == 0 and return the list as is
		if (k == 0) {
			return head;
		}
		ListNode next = head;
		int count = 1;
		// iterate the list in order to find its length and tail element
		while (next.next != null) {
			next = next.next;
			count++;
		}
		int length = count;
		ListNode tail = next;
		// calculate the actual number of rotations in case that k > count
		int realK = k % length;
		// early exit if k == 0 and return the list as is
		if (realK == 0) {
			return head;
		}
		// iterate the list in order to find the element at the point of rotation
		int targetIndex = length - realK;
		next = head;
		count = 1;
		while (next != null) {
			if (count == targetIndex) {
				// this is the element at the point of rotation
				// perform the rotation by appropriately manipulating the next pointers
				// of the head, tail and the current node
				tail.next = head;
				head = next.next;
				next.next = null;
				return head;
			}
			next = next.next;
			count++;
		}
		return head;
	}

	/**
	 * Alternate solution which keeps the index of each node in an array in order to
	 * avoid the second traversal. Time complexity is O(n) where n is the number of
	 * nodes in the list.
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	public static ListNode rotateRight2(ListNode head, int k) {
		// early exit if list is empty
		if (head == null) {
			return null;
		}
		// early exit if k == 0 and return the list as is
		if (k == 0) {
			return head;
		}
		ListNode prev = null;
		ListNode next = head;
		int count = 0;
		ListNode[] nodes = new ListNode[501];
		// iterate the list in order to find its size and tail element
		// also keep a map of the nodes and their indexes
		while (next != null) {
			nodes[count] = next;
			prev = next;
			next = next.next;
			count++;
		}
		int length = count;
		ListNode tail = prev;
		// calculate the actual number of rotations in case that k > count
		int realK = k % length;
		// early exit if k == 0 and return the list as is
		if (realK == 0) {
			return head;
		}
		// get the element at the point of rotation from the map
		int targetIndex = length - realK;
		next = nodes[targetIndex - 1];
		// this is the element at the point of rotation
		// perform the rotation by appropriately manipulating the next pointers of the
		// of the head, tail and the current node
		tail.next = head;
		head = next.next;
		next.next = null;
		return head;
	}

	private static void check(ListNode head, int k, ListNode expected) {
		System.out.println("head is: " + (head == null ? null : head.printAll()));
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		ListNode rotateRight = rotateRight(head, k);
		System.out.println("removeNthFromEnd is: " + (rotateRight == null ? null : rotateRight.printAll()));
	}

}
