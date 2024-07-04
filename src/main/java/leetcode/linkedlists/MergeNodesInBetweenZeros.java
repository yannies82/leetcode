package leetcode.linkedlists;

public class MergeNodesInBetweenZeros {

	public static void main(String[] args) {
		check(ListNode.createList(0, 3, 1, 0, 4, 5, 2, 0), ListNode.createList(4, 11));
		check(ListNode.createList(0, 1, 0, 3, 0, 2, 2, 0), ListNode.createList(1, 3, 4));
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/merge-nodes-in-between-zeros.
	 * This solution traverses the linked list and adds the sum of intermediate
	 * nodes to the nodes with 0 value. Time complexity is O(n) where n is the
	 * number of nodes in the linked list.
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode mergeNodes(ListNode head) {
		ListNode current = head;
		ListNode tail = head;
		int sum = 0;
		while ((current = current.next) != null) {
			if (current.val == 0) {
				current.val = sum;
				sum = 0;
				tail.next = current;
				tail = current;
			} else {
				sum += current.val;
			}
		}
		return head.next;
	}

	private static void check(ListNode head, ListNode expected) {
		System.out.println("head is: " + head.printAll());
		System.out.println("expected is: " + expected.printAll());
		ListNode mergeNodes = mergeNodes(head);
		System.out.println("mergeNodes is: " + mergeNodes.printAll());
	}

}
