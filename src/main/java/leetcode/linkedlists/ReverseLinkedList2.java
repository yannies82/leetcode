package leetcode.linkedlists;

public class ReverseLinkedList2 {

	public static void main(String[] args) {
		ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
		ListNode expectedReversedList1 = new ListNode(1,
				new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5)))));
		check(list1, 2, 4, expectedReversedList1);
		check(new ListNode(5), 1, 1, new ListNode(5));
		check(new ListNode(3, new ListNode(5)), 1, 2, new ListNode(5, new ListNode(3)));
	}

	public static ListNode reverseBetween(ListNode head, int left, int right) {
		ListNode current = null;
		ListNode next = head;
		int index = 1;
		// iterate until we reach the element at the left index
		while (next != null && index < left) {
			current = next;
			next = next.next;
			index++;
		}
		// this is the element at index left-1, before the subList that will be reversed
		ListNode start = current;
		// this is the element at index left, the first element of the subList to be
		// reversed
		// it will be the last element of the reversed subList
		ListNode reverseEnd = next;
		ListNode subCurrent = null;
		// iterate until we reach the element at the right index
		while (next != null && index <= right) {
			current = next;
			next = next.next;
			// construct the subList beginning from the tail element at index left
			// and ending at the head element at index right, resulting in the reversed
			// subList
			// after the iteration the subCurrent element will be the head of the reversed
			// subList
			current.next = subCurrent;
			subCurrent = current;
			index++;
		}
		if (left == 1) {
			// the subList started at left == 1 where the head element is
			// since the subList was reversed, the head element does no longer point to the
			// head of the final list
			// the final list head should be the head of the reversed subList
			head = subCurrent;
		} else {
			// the subList started at left > 1 and the head of the initial list is still
			// valid
			// the subList should be concatenated to the element at left-1
			start.next = subCurrent;
		}
		// the element at index left is the last element of the reversed subList and
		// should be
		// concatenated to the next element of the list at index right + 1
		reverseEnd.next = next;
		return head;
	}

	private static void check(ListNode head, int left, int right, ListNode expected) {
		System.out.println("head is: " + (head == null ? null : head.printAll()));
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		ListNode reverseBetween = reverseBetween(head, left, right);
		System.out.println("reverseBetween is: " + (reverseBetween == null ? null : reverseBetween.printAll()));
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
