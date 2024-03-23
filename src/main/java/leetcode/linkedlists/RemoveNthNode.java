package leetcode.linkedlists;

public class RemoveNthNode {

	public static void main(String[] args) {
		ListNode list1 = ListNode.createList(1, 2, 3, 4, 5);
		ListNode expectedList1 = ListNode.createList(1, 2, 3, 5);
		check(list1, 2, expectedList1);
		list1 = new ListNode(1);
		expectedList1 = null;
		check(list1, 1, expectedList1);
		list1 = ListNode.createList(1, 2);
		expectedList1 = new ListNode(1);
		check(list1, 1, expectedList1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/remove-nth-node-from-end-of-list. This solution
	 * iterates all nodes of the list and keeps a pointer n nodes behind the current
	 * pointer. When the current pointer reaches the end of the list, the next node
	 * from the second pointer is bypassed. Time complexity is O(n) where n is the
	 * number of nodes in the list.
	 * 
	 * @param head
	 * @param n
	 * @return
	 */
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		// create a dummy node before the list head to assist the operations
		ListNode prevHead = new ListNode(0, head);
		ListNode current = prevHead;
		// this node will keep the previous element to the target element
		ListNode targetPrev = null;
		int count = 1;
		// iterate the first n nodes of the list
		while (count <= n) {
			current = current.next;
			count++;
		}
		// set the targetPrev element
		targetPrev = prevHead;
		// iterate the rest of the list, maintain the targetPrev element to be n nodes
		// behind the current element while iterating
		while (current.next != null) {
			targetPrev = targetPrev.next;
			current = current.next;
			count++;
		}
		// bypass the target element by making its previous element point to its next
		// element
		targetPrev.next = targetPrev.next.next;
		// return head
		return prevHead.next;
	}

	private static void check(ListNode head, int n, ListNode expected) {
		System.out.println("head is: " + (head == null ? null : head.printAll()));
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		ListNode removeNthFromEnd = removeNthFromEnd(head, n);
		System.out.println("removeNthFromEnd is: " + (removeNthFromEnd == null ? null : removeNthFromEnd.printAll()));
	}

}
