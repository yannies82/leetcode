package leetcode.slidingwindow;

import java.util.Arrays;

public class BinarySubarraysWithSum {

	public static void main(String[] args) {
		check(new int[] { 0, 0, 0, 0, 0 }, 0, 15);
		check(new int[] { 1, 0, 1, 0, 1 }, 2, 4);
		check(new int[] { 0, 1, 1, 1, 1 }, 3, 3);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/binary-subarrays-with-sum.
	 * This solution uses a sliding window and calculates the count of all subarrays
	 * with sum up to goal and all subarrays with sum up to goal - 1. It then
	 * subtracts these results to find the count of all subarrays with sum == goal.
	 * Time complexity is O(n).
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int numSubarraysWithSum(int[] nums, int goal) {
		// count the number of subarrays with sum up to goal -1
		// and subtract from the number of subarrays with sum up to goal
		// to get the final result
		return numSubarraysWithSumUpTo(nums, goal) - numSubarraysWithSumUpTo(nums, goal - 1);
	}

	/**
	 * Calculates the count of subarrays with sum up to goal.
	 * 
	 * @param nums
	 * @param goal
	 * @return
	 */
	private static int numSubarraysWithSumUpTo(int[] nums, int goal) {
		if (goal < 0) {
			// early exit if goal < 0
			return 0;
		}
		int sum = 0;
		int start = 0;
		int result = 0;
		// iterate all numbers
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			while (sum > goal) {
				// shrink window until sum <= goal
				sum -= nums[start++];
			}
			// add to the result the number of windows with sum up to goal
			// that end at i
			result += i - start + 1;
		}
		return result;
	}

	/**
	 * Alternate solution with O(n^2) time complexity.
	 * 
	 * @param nums
	 * @param goal
	 * @return
	 */
	public static int numSubarraysWithSum2(int[] nums, int goal) {
		int sum = 0;
		int end = 0;
		int result = 0;
		while (end < nums.length) {
			sum += nums[end++];
			if (sum >= goal) {
				if (sum == goal) {
					result++;
				}
				int start = 0;
				int subSum = sum;
				while (subSum >= goal && start < end - 1) {
					subSum -= nums[start++];
					if (subSum == goal) {
						result++;
					}
				}
			}
		}
		return result;
	}

	private static void check(int[] nums, int goal, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("goal is: " + goal);
		System.out.println("expected is: " + expected);
		int numSubarraysWithSum = numSubarraysWithSum(nums, goal); // Calls your implementation
		System.out.println("numSubarraysWithSum is: " + numSubarraysWithSum);
	}
}
