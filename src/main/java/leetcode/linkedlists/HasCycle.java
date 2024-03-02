package leetcode.linkedlists;

public class HasCycle {

	public static void main(String[] args) {
		ListNode l1 = new ListNode(2, null);
		ListNode l2 = new ListNode(5, l1);
		ListNode l3 = new ListNode(3, l2);
		System.out.println("head is: " + l3.printAll());
		check(l3, false);
		l1.next = l3;
		check(l3, true);
	}

	public static boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode nextSlow = head;
        ListNode nextFast = head;
        while (nextSlow.next != null && nextFast.next != null && nextFast.next.next != null) {
            nextSlow = nextSlow.next;
            nextFast = nextFast.next.next;
            if (nextSlow == nextFast) {
                return true;
            }
        }
        return false;
    }

	private static void check(ListNode head, boolean expected) {
		System.out.println("expected is: " + expected);
		boolean hasCycle = hasCycle(head);
		System.out.println("hasCycle is: " + hasCycle);
	}
	
	private static class ListNode {

		int val;
		ListNode next;

		ListNode(int val, ListNode next) {
			super();
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
