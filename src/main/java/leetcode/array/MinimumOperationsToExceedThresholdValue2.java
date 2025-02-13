package leetcode.array;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinimumOperationsToExceedThresholdValue2 {

	public static void main(String[] args) {
		check(new int[] { 2, 11, 10, 1, 3 }, 10, 2);
		check(new int[] { 1, 1, 2, 4, 9 }, 20, 4);
		check(new int[] { 999999999, 999999999, 999999999 }, 1000000000, 2);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-operations-to-exceed-threshold-value-ii.
	 * This solution uses a priority queue to keep all numbers smaller than k, while
	 * performing the operations. Time complexity is O(nlogn) where n is the length
	 * of the nums array.
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int minOperations(int[] nums, int k) {
		Queue<Integer> queue = new PriorityQueue<>();
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] < k) {
				// only add numbers less than k to the queue
				queue.offer(nums[i]);
			}
		}
		int operations = 0;
		while (!queue.isEmpty()) {
			Integer first = queue.poll();
			Integer second = queue.poll();
			if (second == null) {
				// second number was greater then k and was not added, on the next operation
				// the first number will be greater than k too
				return operations + 1;
			}
			int newNumber = (first << 1) + second;
			// consider integer overflow, check > 0
			if (newNumber > 0 && newNumber < k) {
				queue.offer(newNumber);
			}
			operations++;
		}
		return operations;
	}

	private static void check(int[] nums, int k, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int minOperations = minOperations(nums, k); // Calls your implementation
		System.out.println("minOperations is: " + minOperations);
	}
}
