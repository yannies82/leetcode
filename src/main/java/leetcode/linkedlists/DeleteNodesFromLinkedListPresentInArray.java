package leetcode.linkedlists;

import java.util.Arrays;

public class DeleteNodesFromLinkedListPresentInArray {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3 }, ListNode.createList(1, 2, 3, 4, 5), ListNode.createList(4, 5));
		check(new int[] { 1 }, ListNode.createList(1, 2, 1, 2, 1, 2), ListNode.createList(2, 2, 2));
		check(new int[] { 5 }, ListNode.createList(1, 2, 3, 4), ListNode.createList(1, 2, 3, 4));
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/delete-nodes-from-linked-list-present-in-array.
	 * Time complexity is O(n+m) where n is the length of the nums array and m is
	 * the number of nodes in the linked list.
	 * 
	 * @param nums
	 * @param head
	 * @return
	 */
	public static ListNode modifiedList(int[] nums, ListNode head) {
		boolean[] exists = new boolean[100001];
		for (int i = 0; i < nums.length; i++) {
			exists[nums[i]] = true;
		}
		// create dummy element before head to assist with removal of head, if needed
		ListNode prevHead = new ListNode(0, head);
		ListNode current = prevHead;
		ListNode prev = current;
		while ((current = current.next) != null) {
			if (exists[current.val]) {
				// remove element by skipping it
				prev.next = current.next;
			} else {
				// do not remove element, advance prev
				prev = current;
			}
		}
		return prevHead.next;
	}

	private static void check(int[] nums, ListNode head, ListNode expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("head is: " + head.printAll());
		System.out.println("expected is: " + expected.printAll());
		ListNode modifiedList = modifiedList(nums, head);
		System.out.println("modifiedList is: " + modifiedList.printAll());
	}

}
