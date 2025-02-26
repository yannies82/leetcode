package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaximumAbsoluteSumOfAnySubarray {

	public static void main(String[] args) {
		check(new int[] { 1, -3, 2, 3, -4 }, 5);
		check(new int[] { 2, -5, 1, -4, 3, -2 }, 8);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray. Time
	 * complexity is O(n) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int maxAbsoluteSum(int[] nums) {
		int minSum = 0;
		int maxSum = 0;
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			minSum = Math.min(minSum, sum);
			maxSum = Math.max(maxSum, sum);
		}
		return Math.abs(maxSum - minSum);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/continuous-subarray-sum. This
	 * solution adds all numbers and keeps the mod by k for each step. If the mod by
	 * k is equal for 2 indexes, this means that the numbers between are a multiple
	 * of k. Time complexity is O(n) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static boolean checkSubarraySum(int[] nums, int k) {
		if (nums.length < 2) {
			return false;
		}
		int sum = 0;
		Map<Integer, Integer> modMap = new HashMap<>();
		modMap.put(0, -1);
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			int mod = sum % k;
			Integer existingModIndex = modMap.get(mod);
			if (existingModIndex == null) {
				modMap.put(mod, i);
			} else if (i > existingModIndex + 1) {
				return true;
			}

		}
		return false;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int maxAbsoluteSum = maxAbsoluteSum(nums); // Calls your implementation
		System.out.println("maxAbsoluteSum is: " + maxAbsoluteSum);
	}
}
