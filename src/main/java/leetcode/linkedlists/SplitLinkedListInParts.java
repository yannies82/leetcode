package leetcode.linkedlists;

public class SplitLinkedListInParts {

	public static void main(String[] args) {
		check(ListNode.createList(1, 2, 3), 5,
				new ListNode[] { ListNode.createList(1), ListNode.createList(2), ListNode.createList(3), null, null });
		check(ListNode.createList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 3, new ListNode[] { ListNode.createList(1, 2, 3, 4),
				ListNode.createList(5, 6, 7), ListNode.createList(8, 9, 10) });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/split-linked-list-in-parts.
	 * This solution recursively calculates the number of nodes in the list and the
	 * result array. Time complexity is O(n) where n is the number of nodes in the
	 * list.
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	public static ListNode[] splitListToParts(ListNode head, int k) {
		// initialize an array with the following values
		// countInfo[0]: counter for total elements in the list
		// countInfo[1]: index of current result array element (since we use recursion
		// we start from last element)
		// countInfo[2]: min block size
		// countInfo[3]: number of result lists with max block size
		// countInfo[4]: current block size counter
		int[] countInfo = { 0, k - 1, 0, 0, 0 };
		// initialize result array
		ListNode[] result = new ListNode[k];
		// calculate k-1 last result lists recursively
		splitInternal(head, k, result, countInfo);
		// first result list always starts at head
		result[0] = head;
		return result;
	}

	private static void splitInternal(ListNode node, int k, ListNode[] result, int[] countInfo) {
		if (node == null) {
			// we reached the last node of the input lists, we have the total count of nodes
			// let's calculate now the min block size and the number of result lists with
			// max block size
			countInfo[2] = countInfo[0] / k;
			// calculate the number of result lists with max block size
			countInfo[3] = countInfo[0] % k;
			// last result list always has the min block size
			countInfo[4] = countInfo[2];
			// if the min block size is 0, this means that the input list nodes count is
			// less than k
			// skip the last result lists (leave them to be null) and set the current result
			// list
			// index to nodes count - 1
			if (countInfo[2] == 0) {
				countInfo[1] = countInfo[0] - 1;
				// the remaining result lists will have a size of 1
				countInfo[4] = 1;
			}
			return;
		}
		// increase list elements count
		countInfo[0]++;
		// recursively calculate for the next element
		splitInternal(node.next, k, result, countInfo);
		if (countInfo[4] == 0) {
			// we have reached the end of the current block, which starts at node.next
			// assign to result array and decrease result index
			result[countInfo[1]--] = node.next;
			// the current element is the last element of the new block
			// set its next property to null to separate it from the current block
			node.next = null;
			// determine the new block size counter according to the current result index
			// and the calculated number of result lists with max block size
			if (countInfo[1] < countInfo[3]) {
				// first blocks have max block size
				countInfo[4] = countInfo[2];
			} else {
				// last blocks have min block size
				countInfo[4] = countInfo[2] - 1;
			}
		} else {
			// decrease the current block size counter
			countInfo[4]--;
		}
	}

	/**
	 * Iterative solution. Time complexity is O(n) but two traversals of the list
	 * are performed. Possible slightly slower than the recursive solution but more
	 * space efficient due to the lack of the implicit stack for recursive calls.
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	public static ListNode[] splitListToParts2(ListNode head, int k) {
		ListNode[] result = new ListNode[k];
		if (head == null) {
			// early exit if the list is empty
			return result;
		}
		// first traversal of the list to count the number of nodes
		int count = 1;
		ListNode current = head;
		while ((current = current.next) != null) {
			count++;
		}
		// count the min and max block size and the blocks which will have the max size
		int minBlockSize = count / k;
		int maxBlockSize = minBlockSize + 1;
		int blocksWithMaxSize = count % k;
		// counter for nodes of current sub list
		count = 1;
		// add first list to the results array
		int index = 0;
		result[index++] = head;
		ListNode prev = head;
		current = prev;
		// second iteration to fill the results array with sublists created at the
		// appropriate nodes
		int currentBlockSize = index > blocksWithMaxSize ? minBlockSize : maxBlockSize;
		while ((current = current.next) != null) {
			if (count == currentBlockSize) {
				// this is the first node of a new list, add it to the results array
				result[index++] = current;
				// split the new list from the previous one
				prev.next = null;
				// reset counter for new list nodes
				count = 1;
				// calculate the size of the new list according to its index in the results
				// array and the expected blocksWithMaxSize
				currentBlockSize = index > blocksWithMaxSize ? minBlockSize : maxBlockSize;
			} else {
				// increase counter of current list nodes
				count++;
			}
			prev = current;
		}
		return result;
	}

	private static void check(ListNode head, int k, ListNode[] expected) {
		System.out.println("head is: " + head.printAll());
		System.out.println("k is: " + k);
		System.out.println("expected is: ");
		for (ListNode node : expected) {
			System.out.println(node == null ? null : node.printAll());
		}
		ListNode[] splitListToParts = splitListToParts(head, k);
		System.out.println("splitListToParts is: ");
		for (ListNode node : splitListToParts) {
			System.out.println(node == null ? null : node.printAll());
		}
	}

}
