package leetcode.linkedlists;

public class ReverseNodesInKGroup {

	public static void main(String[] args) {
		ListNode list1 = new ListNode(1, new ListNode(2));
		ListNode expectedReversedList1 = new ListNode(2, new ListNode(1));
		check(list1, 2, expectedReversedList1);
		list1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
		expectedReversedList1 = new ListNode(2, new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(5)))));
		check(list1, 2, expectedReversedList1);
		list1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
		expectedReversedList1 = new ListNode(3, new ListNode(2, new ListNode(1, new ListNode(4, new ListNode(5)))));
		check(list1, 3, expectedReversedList1);
	}

	public static ListNode reverseKGroup(ListNode head, int k) {
		ListNode finalHead = null;
		ListNode current = null;
		ListNode end = null;
		ListNode next = head;
		while (next != null) {
			int index = 1;
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
				index++;
			}
			if (index <= k) {
				// the last subList length was less than k
				// restore it by re-reversing it
				// reset index
				index = 1;
				// set last element next to null
				end.next = null;
				// reset next
				next = current;
				// this is the first element of the subList to be reversed
				// it will be the last element of the reversed subList
				end = next;
				// iterate until we reach the element at the right index
				while (next != null) {
					ListNode previous = current;
					current = next;
					next = next.next;
					// construct the subList beginning from the tail element at index left
					// and ending at the head element at index right, resulting in the reversed
					// subList
					// after the iteration the subCurrent element will be the head of the reversed
					// subList
					current.next = previous;
					index++;
				}
			}
			if (finalHead == null) {
				// this is the first subList that was reversed
				// the head of the final list will be the end of the first reversed subList
				finalHead = current;
			} else {
				// every subsequent reversed subList should be concatenated to the end of the
				// previous
				// reversed subList
				previousEnd.next = current;
			}
			// the last element of the reversed subList should be concatenated to the next
			// element
			end.next = next;
		}
		return finalHead;
	}

	public static ListNode reverseKGroup2(ListNode head, int k) {
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
