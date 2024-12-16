package leetcode.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class FinalArrayStateAfterKMultiplicationOperations1 {

	public static void main(String[] args) {
		check(new int[] { 2, 1, 3, 5, 6 }, 5, 2, new int[] { 8, 4, 6, 5, 6 });
		check(new int[] { 1, 2 }, 3, 4, new int[] { 16, 8 });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/final-array-state-after-k-multiplication-operations-i.
	 * Simple solution which performs the operation k times, finding the minimum of
	 * the array elements each time. Time complexity is O(k*n) where n is the length
	 * of the nums array.
	 * 
	 * @param nums
	 * @param k
	 * @param multiplier
	 * @return
	 */
	public static int[] getFinalState(int[] nums, int k, int multiplier) {
		while (k-- > 0) {
			int minIndex = 0;
			for (int i = 1; i < nums.length; i++) {
				if (nums[i] < nums[minIndex]) {
					minIndex = i;
				}
			}
			nums[minIndex] *= multiplier;
		}
		return nums;
	}

	/**
	 * This solution uses a priority queue to always select the smallest number.
	 * Time complexity is O((n+k)logn) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @param k
	 * @param multiplier
	 * @return
	 */
	public static int[] getFinalState2(int[] nums, int k, int multiplier) {
		Queue<Integer> queue = new PriorityQueue<>((a, b) -> {
			int diff = nums[a] - nums[b];
			return diff == 0 ? a - b : diff;
		});
		for (int i = 0; i < nums.length; i++) {
			queue.offer(i);
		}
		while (k-- > 0) {
			int current = queue.poll();
			nums[current] *= multiplier;
			queue.offer(current);
		}
		return nums;
	}

	private static void check(int[] nums, int k, int multiplier, int[] expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] getFinalState = getFinalState(nums, k, multiplier); // Calls your implementation
		System.out.println("getFinalState is: " + Arrays.toString(getFinalState));
	}
}
