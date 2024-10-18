package leetcode.backtracking;

import java.util.Arrays;

public class CountNumberOfMaximumBitwiseOrSubsets {

	public static void main(String[] args) {
		check(new int[] { 3, 1 }, 2);
		check(new int[] { 2, 2, 2 }, 7);
		check(new int[] { 3, 2, 1, 5 }, 6);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets.
	 * This solution calculates the max OR value and then uses backtracking to find
	 * the subarrays with the same OR value. Time complexity is O(2^n) where n is
	 * the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int countMaxOrSubsets(int[] nums) {
		// calculate the max OR value for the array, which is the OR of all elements
		int maxOr = nums[0];
		for (int i = 1; i < nums.length; i++) {
			maxOr |= nums[i];
		}
		// recursively search for subarrays with the maxOr value
		return search(nums, maxOr, 0, 0);
	}

	private static int search(int[] nums, int maxOr, int i, int currentOr) {
		if (i == nums.length) {
			// we have reached the end of the array
			// return 1 if currentOr == maxOr, 0 otherwise
			return 1 - ((currentOr - maxOr) >>> 31);
		}
		// continue to the next index, add the results of selecting the current element
		// and not selecting the current element for the OR calculation
		return search(nums, maxOr, i + 1, currentOr) + search(nums, maxOr, i + 1, currentOr | nums[i]);
	}

	/**
	 * Similar solution which passes an array reference to store the count. Time
	 * complexity is O(2^n) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int countMaxOrSubsets2(int[] nums) {
		// calculate the max OR value for the array, which is the OR of all elements
		int maxOr = nums[0];
		for (int i = 1; i < nums.length; i++) {
			maxOr |= nums[i];
		}
		// keeps the number of subarrays with the maxOr value
		int[] count = { 0 };
		// recursively search for subarrays with the maxOr value
		search2(nums, maxOr, 0, 0, count);
		return count[0];
	}

	private static void search2(int[] nums, int maxOr, int i, int currentOr, int[] count) {
		if (i == nums.length) {
			// we have reached the end of the array
			if (currentOr == maxOr) {
				// increase result count if the subarray OR is equal to the max OR
				count[0]++;
			}
			return;
		}
		// continue to the next index, do not select the current element for OR
		// calculation
		search2(nums, maxOr, i + 1, currentOr, count);
		// continue to the next index, select the current element for OR calculation
		search2(nums, maxOr, i + 1, currentOr | nums[i], count);
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int countMaxOrSubsets = countMaxOrSubsets(nums); // Calls your implementation
		System.out.println("countMaxOrSubsets is: " + countMaxOrSubsets);
	}

}
