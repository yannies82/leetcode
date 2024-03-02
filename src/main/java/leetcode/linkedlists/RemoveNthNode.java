package leetcode.linkedlists;

public class RemoveNthNode {

	public static void main(String[] args) {
		ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
		ListNode expectedList1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(5))));
		check(list1, 2, expectedList1);
		list1 = new ListNode(1);
		expectedList1 = null;
		check(list1, 1, expectedList1);
		list1 = new ListNode(1, new ListNode(2));
		expectedList1 = new ListNode(1);
		check(list1, 1, expectedList1);
	}

	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode prev = null;
		ListNode target = null;
		ListNode next = head;
		int count = 1;
		// iterate the list, maintain a target element to be n nodes
		// behind the next element while iterating
		while (next != null) {
			if (count == n) {
				target = head;
			} else if (count > n) {
				prev = target;
				target = target.next;
			}
			next = next.next;
			count++;
		}
		if (prev == null) {
			if (target == null || target.next == null) {
				// the element to be removed is the only element in the list
				// return empty list
				head = null;
			} else {
				// the element to be removed is the first element of the list
				// move the list head to the next element
				head = target.next;
			}
		} else {
			// the element to be removed is not the first of the list
			// bypass it by setting the prev element's next pointer accordingly
			prev.next = target.next;
		}
		return head;
	}

	private static void check(ListNode head, int n, ListNode expected) {
		System.out.println("head is: " + (head == null ? null : head.printAll()));
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		ListNode removeNthFromEnd = removeNthFromEnd(head, n);
		System.out.println("removeNthFromEnd is: " + (removeNthFromEnd == null ? null : removeNthFromEnd.printAll()));
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
