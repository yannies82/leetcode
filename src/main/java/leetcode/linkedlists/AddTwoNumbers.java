package leetcode.linkedlists;

public class AddTwoNumbers {

	public static void main(String[] args) {
		ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
		ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
		printOutput(l1, l2);

		l1 = new ListNode(0);
		l2 = new ListNode(0);
		printOutput(l1, l2);

		l1 = new ListNode(9,
				new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))))));
		l2 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))));
		printOutput(l1, l2);
	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode head = new ListNode(0);
		ListNode current = head;
		while (current != null) {
			int sum = current.val;
			if (l1 != null) {
				sum += l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				sum += l2.val;
				l2 = l2.next;
			}
			current.val = (sum % 10);
			int remainder = sum / 10;
			if (l1 != null || l2 != null || remainder > 0) {
				current.next = new ListNode(remainder);
			}
			current = current.next;
		}
		return head;
	}

	private static void printOutput(ListNode l1, ListNode l2) {
		System.out.println("l1 is: " + l1.printAll());
		System.out.println("l2 is: " + l2.printAll());
		System.out.println("l1 + l2 is: " + addTwoNumbers(l1, l2).printAll());
		System.out.println("l1 decimal is: " + l1.getNumber());
		System.out.println("l2 decimal is: " + l2.getNumber());
		System.out.println("l1 + l2 decimal is: " + addTwoNumbers(l1, l2).getNumber());
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

		int getNumber() {
			int pow = 1;
			int result = 0;
			ListNode current = this;
			do {
				result += (current.val * pow);
				pow *= 10;
				current = current.next;
			} while (current != null);
			return result;
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
