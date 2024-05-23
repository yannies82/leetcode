package leetcode.backtracking;

import java.util.Arrays;

public class NumberOfBeautifulSubsets {

	public static void main(String[] args) {
		check(new int[] { 2, 4, 6 }, 2, 4);
		check(new int[] { 1 }, 1, 1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/the-number-of-beautiful-subsets. This solution
	 * uses recursion and backtracking to calculate all available subset and
	 * increase the counter for the eligible ones. Time complexity is O(2^n) where n
	 * is the length of the nums array.
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int beautifulSubsets(int[] nums, int k) {
		// the result array keeps the count of eligible subsets
		int[] result = new int[1];
		// recursively find eligible subsets and increase counter
		beautifulSubsetsRecursive(nums, k, result, new int[1001], 0);
		// subtract 1 from the result (exclude the empty subset)
		return result[0] - 1;
	}

	private static void beautifulSubsetsRecursive(int[] nums, int k, int[] result, int[] existing, int i) {
		if (i == nums.length) {
			// if the array has no more elements it means that the subset is eligible
			// increase counter
			result[0]++;
			return;
		}

		// check if nums[i] - k or nums[i] + k already exists in the subset
		// if it does not, add nums[i] and proceed to the next number recursively
		if ((k > nums[i] || existing[nums[i] - k] == 0) && (k + nums[i] > 1000 || existing[nums[i] + k] == 0)) {
			existing[nums[i]]++;
			beautifulSubsetsRecursive(nums, k, result, existing, i + 1);
			// backtrack by removing nums[i] from the subset
			existing[nums[i]]--;
		}

		// proceed recursively without adding nums[i] to the subset
		beautifulSubsetsRecursive(nums, k, result, existing, i + 1);
	}

	private static void check(int[] nums, int k, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		int beautifulSubsets = beautifulSubsets(nums, k); // Calls your implementation
		System.out.println("beautifulSubsets is: " + beautifulSubsets);
	}
}
