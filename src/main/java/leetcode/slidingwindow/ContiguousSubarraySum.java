package leetcode.slidingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ContiguousSubarraySum {

	public static void main(String[] args) {
		check(new int[] { 23, 2, 4, 6, 7 }, 6, true);
		check(new int[] { 23, 2, 6, 4, 7 }, 6, true);
		check(new int[] { 23, 2, 6, 4, 7 }, 13, false);
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

	private static void check(int[] nums, int k, boolean expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		boolean checkSubarraySum = checkSubarraySum(nums, k); // Calls your implementation
		System.out.println("checkSubarraySum is: " + checkSubarraySum);
	}
}
