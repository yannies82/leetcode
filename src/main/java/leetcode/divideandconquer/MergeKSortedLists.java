package leetcode.divideandconquer;

public class MergeKSortedLists {

	public static void main(String[] args) {
		ListNode list0 = new ListNode(2);
		ListNode list1 = null;
		ListNode list2 = new ListNode(-1);
		ListNode expected = new ListNode(-1, new ListNode(2));
		check(new ListNode[] { list0, list1, list2 }, expected);
		list0 = new ListNode(1, new ListNode(4, new ListNode(5)));
		list1 = new ListNode(1, new ListNode(3, new ListNode(4)));
		list2 = new ListNode(2, new ListNode(6));
		expected = new ListNode(1, new ListNode(1,
				new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(4, new ListNode(5, new ListNode(6))))))));
		check(new ListNode[] { list0, list1, list2 }, expected);
	}

	/**
	 * This solution merges the lists in pairs until they are all merged in a single
	 * list. Time complexity is O(N * K * logK) where N is the size of each list and
	 * K is the number of lists to be merged.
	 * 
	 * @param lists
	 * @return
	 */
	public static ListNode mergeKLists(ListNode[] lists) {
		// early exit in case no lists exist
		if (lists.length == 0) {
			return null;
		}
		int length = lists.length;
		// repeat until only 1 list is left
		while (length > 1) {
			int count = 0;
			// merge lists in pairs and put merged lists at the beginning of the array
			for (int i = 0; i < length - 1; i += 2) {
				lists[count++] = mergeSort(lists[i], lists[i + 1]);
			}
			// if the number of lists is odd, also put the final unmerged list in the array
			// to be merged in the next iteration
			if (length % 2 == 1) {
				lists[count++] = lists[length - 1];
			}
			length = count;
		}
		// return the single list that is left
		return lists[0];
	}

	/**
	 * This solution merges the lists one by one and gradually builds the final
	 * list.
	 * 
	 * @param lists
	 * @return
	 */
	public static ListNode mergeKLists2(ListNode[] lists) {
		if (lists.length == 0) {
			return null;
		}
		// merge every list into the previous one, thus accumulating the final list
		// in the final element
		for (int i = 1; i < lists.length; i++) {
			ListNode merged = mergeSort(lists[i - 1], lists[i]);
			lists[i] = merged;
		}
		// return the final element
		return lists[lists.length - 1];
	}

	private static ListNode mergeSort(ListNode first, ListNode second) {
		if (first == null) {
			return second;
		}
		if (second == null) {
			return first;
		}
		// select the smaller of the subList heads as the merged list head
		ListNode head;
		if (first.val > second.val) {
			head = second;
			second = second.next;
		} else {
			head = first;
			first = first.next;
		}
		ListNode current = head;
		// iterate while both lists have elements
		while (first != null && second != null) {
			// for each iteration add the smaller element from the two
			// lists to the merged list and advance the respective head
			if (first.val > second.val) {
				current.next = second;
				second = second.next;
			} else {
				current.next = first;
				first = first.next;
			}
			current = current.next;
		}
		// if the first list still has elements append them
		if (first != null) {
			current.next = first;
		}
		// if the second list still has elements append them
		if (second != null) {
			current.next = second;
		}
		return head;
	}

	private static class ListNode {
		int val;
		ListNode next;

		public ListNode(int val) {
			this.val = val;
		}

		public ListNode(int val, ListNode next) {
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

	private static void check(ListNode[] lists, ListNode expected) {
		System.out.println("lists is: ");
		for (ListNode list : lists) {
			System.out.println(list == null ? null : list.printAll());
		}
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		ListNode mergeKLists = mergeKLists(lists); // Calls your implementation
		System.out.println("mergeKLists is: " + (mergeKLists == null ? null : mergeKLists.printAll()));
	}
}
