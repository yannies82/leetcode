package leetcode.linkedlists;

public class ReverseNodesInKGroup {

	public static void main(String[] args) {
		ListNode list1 = ListNode.createList(1, 2);
		ListNode expectedReversedList1 = ListNode.createList(2, 1);
		check(list1, 2, expectedReversedList1);
		list1 = ListNode.createList(1, 2, 3, 4, 5);
		expectedReversedList1 = ListNode.createList(2, 1, 4, 3, 5);
		check(list1, 2, expectedReversedList1);
		list1 = ListNode.createList(1, 2, 3, 4, 5);
		expectedReversedList1 = ListNode.createList(3, 2, 1, 4, 5);
		check(list1, 3, expectedReversedList1);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/reverse-nodes-in-k-group.
	 * This solution reverses all sublists of k nodes until the input list is
	 * exhausted. If the last reversed sublist is less than k nodes long, it is
	 * restored. Time complexity is O(n) where n is the number of nodes in the list.
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	public static ListNode reverseKGroup(ListNode head, int k) {
		// create a dummy node before the list head to assist the reverse operations
		ListNode prevHead = new ListNode(0, head);
		ListNode current = prevHead;
		// reverse sublists starting after the current element
		// until there is none left
		while (current.next != null) {
			int index = 1;
			// this is the element at index left-1, before the subList that will be reversed
			ListNode start = current;
			// this is the element at index left, the first element of the subList to be
			// reversed
			// it will be the last element of the reversed subList
			current = start.next;
			ListNode reverseEnd = current;
			ListNode prev = null;
			// iterate and reverse nodes until we reach the element at the right index
			while (current != null && index <= k) {
				ListNode temp = current;
				current = current.next;
				temp.next = prev;
				prev = temp;
				index++;
			}
			// append the head of the reversed sublist to the element before left index
			start.next = prev;
			// append the element after right index to the tail of the reversed sublist
			reverseEnd.next = current;
			if (index <= k) {
				// the last subList length was less than k
				// restore it by re-reversing it
				current = start.next;
				reverseEnd = current;
				prev = null;
				// iterate and reverse nodes until we reach the element at the right index
				while (current != null) {
					ListNode temp = current;
					current = current.next;
					temp.next = prev;
					prev = temp;
				}
				// append the head of the reversed sublist to the element before left index
				start.next = prev;
				// append the element after right index to the tail of the reversed sublist
				reverseEnd.next = current;
			}
			// set the last element of the reversed list as the current element
			current = reverseEnd;
		}
		return prevHead.next;
	}

	/**
	 * Alternate solution which extracts the reverse sublist functionality into a
	 * separate method. Slower but more readable. Time complexity is O(n) where n is
	 * the number of nodes in the list.
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	public static ListNode reverseKGroup2(ListNode head, int k) {
		// create a dummy node before the list head to assist the reverse operations
		ListNode prevHead = new ListNode(0, head);
		ListNode current = prevHead;
		// reverse sublists starting after the current element
		// until there is none left
		while (current.next != null) {
			// this is the element at index left-1, before the subList that will be reversed
			ListNode start = current;
			// this is the element at index left, the first element of the subList to be
			// reversed
			// it will be the last element of the reversed subList
			ListNode reverseEnd = start.next;
			if (!reverseSublist(start, k)) {
				// the last subList length was less than k
				// restore it by re-reversing it
				// reset index
				reverseSublist(start, k);
			}
			// set the last element of the reversed list as the current element
			current = reverseEnd;
		}
		return prevHead.next;
	}

	/**
	 * This method reverses the subList starting after the start node for k nodes
	 * and returns true if the length of the reversed sublist was k.
	 * 
	 * @param start
	 * @param k
	 * @return
	 */
	private static boolean reverseSublist(ListNode start, int k) {
		int index = 1;
		ListNode current = start.next;
		ListNode reverseEnd = current;
		ListNode prev = null;
		// iterate and reverse nodes until we reach the element at the right index
		while (current != null && index <= k) {
			ListNode temp = current;
			current = current.next;
			temp.next = prev;
			prev = temp;
			index++;
		}
		// append the head of the reversed sublist to the element before left index
		start.next = prev;
		// append the element after right index to the tail of the reversed sublist
		reverseEnd.next = current;
		return index > k;
	}

	/**
	 * Alternate solution which checks if the sublist has at least k nodes before
	 * reversing. Time complexity is O(n) where n is the number of nodes in the
	 * list.
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	public static ListNode reverseKGroup3(ListNode head, int k) {
		ListNode finalHead = null;
		ListNode current = null;
		ListNode end = null;
		ListNode next = head;
		while (next != null) {
			ListNode probe = next;
			int index = 1;
			// iterate to check that at least k nodes remain
			// so that the sublist can be reversed
			while (probe != null && index <= k) {
				probe = probe.next;
				index++;
			}
			if (index <= k) {
				// less than k nodes remain
				// return final list head as is
				return finalHead;
			}
			// reset index
			index = 1;
			// this is the last element of the previous reversed subList
			ListNode previousEnd = end;
			// this is the first element of the subList to be reversed
			// it will be the last element of the reversed subList
			end = next;
			// iterate until we reach the element at the right index
			while (next != null && index <= k) {
				ListNode previous = current;
				current = next;
				next = next.next;
				// construct the subList beginning from the tail element at index left
				// and ending at the head element at index right, resulting in the reversed
				// subList
				// after the iteration the subCurrent element will be the head of the reversed
				// subList
				current.next = previous;
				previous = current;
				index++;
			}
			if (finalHead == null) {
				// this is the first subList that was reversed
				// the head of the final list will be the end of the first reversed subList
				finalHead = current;
			} else {
				// every subsequent reversed subList should be concatenated to the end of the
				// previous reversed subList
				previousEnd.next = current;
			}
			// the last element of the reversed subList should be concatenated to the next
			// element
			end.next = next;
		}
		return finalHead;
	}

	private static void check(ListNode head, int k, ListNode expected) {
		System.out.println("head is: " + (head == null ? null : head.printAll()));
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		ListNode reverseKGroup = reverseKGroup(head, k);
		System.out.println("reverseKGroup is: " + (reverseKGroup == null ? null : reverseKGroup.printAll()));
	}

}
