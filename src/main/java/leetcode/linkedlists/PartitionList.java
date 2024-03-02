package leetcode.linkedlists;

public class PartitionList {

	public static void main(String[] args) {
		ListNode list1 = new ListNode(1,
				new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5, new ListNode(2))))));
		ListNode expectedList1 = new ListNode(1,
				new ListNode(2, new ListNode(2, new ListNode(4, new ListNode(3, new ListNode(5))))));
		check(list1, 3, expectedList1);
		list1 = new ListNode(2, new ListNode(1));
		expectedList1 = new ListNode(1, new ListNode(2));
		check(list1, 2, expectedList1);
	}

	public static ListNode partition(ListNode head, int x) {
		// early exit if list is empty
		if (head == null) {
			return null;
		}
		ListNode leftPartition = null;
		ListNode rightPartition = null;
		ListNode leftPartitionHead = null;
		ListNode rightPartitionHead = null;
		ListNode current = head;
		// iterate the list and add each node to one of two sub lists
		// leftPartition or rightPartition according to its value
		while (current != null) {
			if (current.val < x) {
				// this node will be added to the left partition because its
				// value is less than x
				if (leftPartition == null) {
					// this is the first node of the left partition
					// which will also be the head of the final list
					leftPartition = current;
					leftPartitionHead = current;
				} else {
					// add the node to the left partition
					// and advance the leftPartition pointer
					leftPartition.next = current;
					leftPartition = current;
				}
			} else {
				// this node will be added to the right partition because its
				// value is greater than or equal to x
				if (rightPartition == null) {
					// this is the first node of the right partition
					rightPartition = current;
					rightPartitionHead = current;
				} else {
					// add the node to the right partition
					// and advance the rightPartition pointer
					rightPartition.next = current;
					rightPartition = current;
				}
			}
			current = current.next;
		}
		// end the right partition by setting the last node's next pointer to null
		if (rightPartition != null) {
			rightPartition.next = null;
		}
		// concatenate the two partitions and return the head of the final list
		if (leftPartitionHead == null) {
			// if the left partition is empty, return the head of the right partition
			return rightPartitionHead;
		} else {
			// if the left partition is not empty, append the right partition
			// to it and return the leftPartitionHead
			leftPartition.next = rightPartitionHead;
			return leftPartitionHead;
		}
	}

	private static void check(ListNode head, int k, ListNode expected) {
		System.out.println("head is: " + (head == null ? null : head.printAll()));
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		ListNode partition = partition(head, k);
		System.out.println("partition is: " + (partition == null ? null : partition.printAll()));
	}

	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
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
