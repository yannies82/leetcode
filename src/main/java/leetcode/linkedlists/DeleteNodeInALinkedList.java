package leetcode.linkedlists;

public class DeleteNodeInALinkedList {

	public static void main(String[] args) {
		ListNode list1 = ListNode.createList(1, 2, 3, 4, 5);
		check(list1, list1.next.next, ListNode.createList(1, 2, 4, 5));
		list1 = ListNode.createList(1, 2, 3, 4, 5);
		check(list1, list1, ListNode.createList(2, 3, 4, 5));
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/delete-node-in-a-linked-list.
	 * This solution replaces the value of each node with the value of the next one,
	 * effectively removing the current node value from the list. Time complexity is
	 * O(n) where n is the number of nodes in the sublist starting at node.
	 * 
	 * @param node
	 */
	public static void deleteNode(ListNode node) {
		ListNode current = node;
		while (current != null && current.next != null) {
			current.val = current.next.val;
			if (current.next.next == null) {
				current.next = null;
			} else {
				current = current.next;
			}
		}

	}

	private static void check(ListNode head, ListNode node, ListNode expected) {
		System.out.println("head is: " + (head == null ? null : head.printAll()));
		System.out.println("node is: " + node.val);
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		deleteNode(node);
		System.out.println("deleteNode is: " + (head == null ? null : head.printAll()));
	}

}
