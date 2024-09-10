package leetcode.linkedlists;

public class InsertGreatestCommonDivisorsInLinkedList {

	public static void main(String[] args) {
		check(ListNode.createList(18, 6, 10, 3), ListNode.createList(18, 6, 6, 2, 10, 1, 3));
		check(ListNode.createList(7), ListNode.createList(7));
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/insert-greatest-common-divisors-in-linked-list.
	 * Time complexity is O(n*m) where n is the number of nodes in the list and m is
	 * the complexity of the gcd euclidian algorithm which is O(log(min(a,b)).
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode insertGreatestCommonDivisors(ListNode head) {
		ListNode prev = head;
		ListNode current = head;
		while ((current = current.next) != null) {
			int gcd = calculateGcd(prev.val, current.val);
			ListNode newNode = new ListNode(gcd, current);
			prev.next = newNode;
			prev = current;
		}
		return head;
	}

	private static int calculateGcd(int num1, int num2) {
		if (num1 == 1 || num2 == 1) {
			return 1;
		}
		while (num2 != 0) {
			int rem = num1 % num2;
			num1 = num2;
			num2 = rem;
		}
		return num1;
	}

	private static void check(ListNode head, ListNode expected) {
		System.out.println("head is: " + head.printAll());
		System.out.println("expected is: " + expected.printAll());
		ListNode insertGreatestCommonDivisors = insertGreatestCommonDivisors(head); // Calls your implementation
		System.out.println("insertGreatestCommonDivisors is: " + insertGreatestCommonDivisors.printAll());
	}
}
