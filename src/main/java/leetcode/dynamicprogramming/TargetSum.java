package leetcode.dynamicprogramming;

import java.util.Arrays;

public class TargetSum {

	public static void main(String[] args) {
		check(new int[] { 1, 1, 1, 1, 1 }, 3, 5);
		check(new int[] { 1 }, 1, 1);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/target-sum. This solution
	 * uses dynamic programming. Time complexity is O(n) where n is the length of
	 * the nums array.
	 * 
	 * @param nums
	 * @param n
	 * @return
	 */
	public static int findTargetSumWays(int[] nums, int target) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}

		// target is not achievable
		if (sum < target || -sum > target) {
			return 0;
		}

		// sumPositive - sumNegative = target
		// sumPositive + sumNegative = sum
		// 2 * sumPositive = target + sum
		// sumPositive = (target + sum) / 2

		int total = target + sum;
		if ((total & 1) == 1) {
			// numbers cannot exactly add up to target
			return 0;
		}
		int sumPositive = total >>> 1;
		if (sumPositive < 0) {
			return 0;
		}
		// dpArray[j] represents number of ways to achieve sumPositive j
		int[] dpArray = new int[sumPositive + 1];
		// there is only one way to acieve sumPositive 0, this is by setting
		// all numbers to be negative
		dpArray[0] = 1;

		for (int i = 0; i < nums.length; i++) {
			// every number adds new ways to achieve sumPositive j
			for (int j = sumPositive; j >= nums[i]; j--) {
				// with the new nums[i] it becomes possible to achieve sumPositive j
				// in an extra as many ways as it was possible to achieve sumPositive
				// j - nums[i]
				dpArray[j] += dpArray[j - nums[i]];
			}
		}
		return dpArray[sumPositive];
	}

	public static int findTargetSumWays2(int[] nums, int target) {
		int[] result = new int[] { 0, 0 };
		combine(nums, 0, target, result);
		return result[1];
	}

	private static void combine(int[] nums, int i, int target, int[] result) {
		if (i == nums.length) {
			if (result[0] == target) {
				result[1]++;
			}
			return;
		}
		// add positive value
		result[0] += nums[i];
		combine(nums, i + 1, target, result);
		result[0] -= nums[i];

		// add negative value
		result[0] -= nums[i];
		combine(nums, i + 1, target, result);
		result[0] += nums[i];
	}

	private static void check(int[] nums, int target, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("target is: " + target);
		System.out.println("expected is: " + expected);
		int findTargetSumWays = findTargetSumWays(nums, target); // Calls your implementation
		System.out.println("findTargetSumWays is: " + findTargetSumWays);
	}
}
