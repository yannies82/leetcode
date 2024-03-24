package leetcode.linkedlists;

public class PartitionList {

	public static void main(String[] args) {
		ListNode list1 = ListNode.createList(1, 4, 3, 2, 5, 2);
		ListNode expectedList1 = ListNode.createList(1, 2, 2, 4, 3, 5);
		check(list1, 3, expectedList1);
		list1 = ListNode.createList(2, 1);
		expectedList1 = ListNode.createList(1, 2);
		check(list1, 2, expectedList1);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/partition-list. This solution
	 * iterates all nodes and puts them into one of two sublists according to their
	 * value. Finally it concatenates the two lists. Time complexity is O(n) where n
	 * is the number of nodes in the initial list.
	 * 
	 * @param head
	 * @param x
	 * @return
	 */
	public static ListNode partition(ListNode head, int x) {
		// early exit if list is empty
		if (head == null) {
			return null;
		}
		// create dummy nodes to serve as prev for the heads of the left and right
		// partitions
		// these will aid with the operations
		ListNode leftPartitionStart = new ListNode(0);
		ListNode rightPartitionStart = new ListNode(0);
		ListNode leftPartition = leftPartitionStart;
		ListNode rightPartition = rightPartitionStart;
		ListNode current = head;
		// iterate the list and add each node to one of two sub lists
		// leftPartition or rightPartition according to its value
		while (current != null) {
			if (current.val < x) {
				// this node will be added to the left partition because its
				// value is less than x
				leftPartition.next = current;
				leftPartition = current;
			} else {
				// this node will be added to the right partition because its
				// value is greater than or equal to x
				rightPartition.next = current;
				rightPartition = current;
			}
			current = current.next;
		}
		// end the right partition by setting the last node's next pointer to null
		rightPartition.next = null;
		// concatenate the two partitions and return the head of the final list
		// append the right partition head to the left partition and return the left
		// partition head
		leftPartition.next = rightPartitionStart.next;
		return leftPartitionStart.next;
	}

	private static void check(ListNode head, int k, ListNode expected) {
		System.out.println("head is: " + (head == null ? null : head.printAll()));
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		ListNode partition = partition(head, k);
		System.out.println("partition is: " + (partition == null ? null : partition.printAll()));
	}

}
