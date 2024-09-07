package leetcode.binarytreegeneral;

/**
 * Common ListNode class used in linked list problems.
 * 
 * @author yanni
 *
 */
class ListNode {
	int val;
	ListNode next;

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		super();
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

	static ListNode createList(int... values) {
		ListNode start = new ListNode(0);
		ListNode current = start;
		for (int val : values) {
			current.next = new ListNode(val);
			current = current.next;
		}
		return start.next;
	}
}
