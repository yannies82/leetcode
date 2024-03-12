package leetcode.linkedlists;

import java.util.HashMap;
import java.util.Map;

public class RemoveZeroSumConsecutiveNodes {

	public static void main(String[] args) {
		ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(-3, new ListNode(3, new ListNode(1)))));
		ListNode expectedList1 = new ListNode(3, new ListNode(1));
		check(list1, expectedList1);
		list1 = new ListNode(1);
		expectedList1 = null;
		list1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(-3, new ListNode(4)))));
		expectedList1 = new ListNode(1, new ListNode(2, new ListNode(4)));
		check(list1, expectedList1);
		list1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(-3, new ListNode(-2)))));
		expectedList1 = new ListNode(1);
		check(list1, expectedList1);
		list1 = new ListNode(1, new ListNode(3, new ListNode(2, new ListNode(-3,
				new ListNode(-2, new ListNode(5, new ListNode(5, new ListNode(-5, new ListNode(1)))))))));
		expectedList1 = new ListNode(1, new ListNode(5, new ListNode(1)));
		check(list1, expectedList1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list.
	 * This solution uses a map to store the nodes per accumulated sum. When a new
	 * node has the same accumulated sum with a previous one, it replaces the value
	 * in the map entry. Then the list is traversed again and the map is used for
	 * shortcutting to the tail. Time complexity is O(n) where n is the length of
	 * the linked list.
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode removeZeroSumSublists(ListNode head) {
		// create dummy element, used for replacing the head of the list if necessary
		ListNode prevHead = new ListNode(0, head);
		// keeps the nodes per sum
		Map<Integer, ListNode> sumMap = new HashMap<>();
		ListNode current = prevHead;
		int sum = 0;
		// traverse the list, store the nodes per sum
		// latest node with same sum overwrites previous one
		while (current != null) {
			sum += current.val;
			sumMap.put(sum, current);
			current = current.next;
		}
		ListNode prev = prevHead;
		sum = 0;
		// traverse the list again and use the map to shortcut
		// to the latest element with the same sum
		while ((prev.next = sumMap.get(sum).next) != null) {
			sum += prev.next.val;
			prev = prev.next;
		}
		return prevHead.next;
	}

	/**
	 * This solution traverses all nodes and keeps the sum of their values. When the
	 * same sum is encountered, all nodes between the duplicate sums are removed.
	 * Time complexity is O(n) where n is the number of nodes int he linked list.
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode removeZeroSumSublists2(ListNode head) {
		// keeps the nodes per sum
		Map<Integer, ListNode> sumMap = new HashMap<>();
		// keeps the sums per node
		Map<ListNode, Integer> nodesMap = new HashMap<>();
		sumMap.put(0, null);
		ListNode current = head;
		int sum = 0;
		// traverse the list
		while (current != null) {
			sum += current.val;
			if (sumMap.containsKey(sum)) {
				// the sum has already been encountered
				ListNode start = sumMap.get(sum);
				if (start == null) {
					// if the previous sum was at the start of the list, reset head and clear maps
					head = current.next;
					sumMap.clear();
					nodesMap.clear();
					sumMap.put(0, null);
				} else {
					// remove all intermediate nodes between the two identical sums
					// and remove the respective map entries
					ListNode toRemove = start.next;
					while (toRemove != current) {
						sumMap.remove(nodesMap.remove(toRemove));
						toRemove = toRemove.next;
					}
					start.next = current.next;
				}
			} else {
				// the sum has not been encountered, add to maps
				sumMap.put(sum, current);
				nodesMap.put(current, sum);
			}
			current = current.next;
		}
		return head;
	}

	private static void check(ListNode head, ListNode expected) {
		System.out.println("head is: " + (head == null ? null : head.printAll()));
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		ListNode removeZeroSumSublists = removeZeroSumSublists(head);
		System.out.println("removeZeroSumSublists is: "
				+ (removeZeroSumSublists == null ? null : removeZeroSumSublists.printAll()));
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
