package leetcode.linkedlists;

import java.util.Arrays;

public class FindTheMinimumAndMaximumNumberOfNodesBetweenCriticalPoints {

	public static void main(String[] args) {
		check(ListNode.createList(3, 1), new int[] { -1, -1 });
		check(ListNode.createList(5, 3, 1, 2, 5, 1, 2), new int[] { 1, 3 });
		check(ListNode.createList(1, 3, 2, 2, 3, 2, 2, 2, 7), new int[] { 3, 3 });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points.
	 * This solution traverses the linked list and keeps two counters for the min
	 * and max distance between critical points. Time complexity is O(n) where n is
	 * the number of elements in the list.
	 * 
	 * @param head
	 * @return
	 */
	public static int[] nodesBetweenCriticalPoints(ListNode head) {
		int minDistance = Integer.MAX_VALUE;
		int maxDistance = -1;
		ListNode prev = head;
		ListNode current = head;
		int countMin = 0;
		int countMax = 0;
		boolean alreadyFoundOne = false;
		while ((current = current.next) != null && current.next != null) {
			if ((current.val > prev.val && current.val > current.next.val)
					|| (current.val < prev.val && current.val < current.next.val)) {
				// we found a critical point, check if this is the first one
				if (alreadyFoundOne) {
					// it is not the first critical point, update min and max distance
					minDistance = Math.min(minDistance, countMin);
					maxDistance = countMax;
				} else {
					// it is the first critical point, mark that we have found one
					alreadyFoundOne = true;
				}
				// reset counter for min distance
				countMin = 0;
			}
			if (alreadyFoundOne) {
				// only count distances if we have found at least one critical point
				countMin++;
				countMax++;
			}
			prev = current;
		}
		return new int[] { minDistance == Integer.MAX_VALUE ? -1 : minDistance, maxDistance };
	}

	private static void check(ListNode head, int[] expected) {
		System.out.println("head is: " + head.printAll());
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] nodesBetweenCriticalPoints = nodesBetweenCriticalPoints(head);
		System.out.println("nodesBetweenCriticalPoints is: " + Arrays.toString(nodesBetweenCriticalPoints));
	}

}
