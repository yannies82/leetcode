package leetcode.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthLargestElement {

	public static void main(String[] args) {
		int[] nums0 = { 3, 2, 1, 5, 6, 4 };
		check(nums0, 2, 5);
		int[] nums1 = { 3, 2, 3, 1, 2, 4, 5, 5, 6 };
		check(nums1, 4, 4);
	}

	/**
	 * This solution uses a priority queue as a max heap in order to keep the k
	 * largest elements at all times. If the queue has k elements and the next array
	 * element is greater than the smallest in the queue, then it replaces it. Worst
	 * case time complexity is O(n * logk) where n is the array length.
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int findKthLargest(int[] nums, int k) {
		// initialize the priority queue with default ordering
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
		// first q numbers are always added to the queue
		for (int i = 0; i < k; i++) {
			priorityQueue.add(nums[i]);
		}
		// the smallest element in the queue is kth largest
		int kthLargest = priorityQueue.peek();
		for (int i = k; i < nums.length; i++) {
			if (nums[i] > kthLargest) {
				// the next number is greater than the kth largest
				// remove kth largest and add the number to the queue
				priorityQueue.poll();
				priorityQueue.offer(nums[i]);
				// update the kth largest element
				kthLargest = priorityQueue.peek();
			}
		}
		return kthLargest;
	}

	private static void check(int[] nums, int k, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		int findKthLargest = findKthLargest(nums, k); // Calls your implementation
		System.out.println("findKthLargest is: " + findKthLargest);
	}
}
