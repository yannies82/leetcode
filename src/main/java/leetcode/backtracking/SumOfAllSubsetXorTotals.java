package leetcode.backtracking;

import java.util.Arrays;

public class SumOfAllSubsetXorTotals {

	public static void main(String[] args) {
		check(new int[] { 1, 3 }, 6);
		check(new int[] { 5, 1, 6 }, 28);
		check(new int[] { 3, 4, 5, 6, 7, 8 }, 480);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/sum-of-all-subset-xor-totals.
	 * This solution uses recursion. Time complexity is O(2^n).
	 * 
	 * @param nums
	 * @return
	 */
	public static int subsetXORSum(int[] nums) {
		return recurse(nums, 0, 0);
	}

	private static int recurse(int[] nums, int index, int currValue) {
		if (index == nums.length) {
			return currValue;
		}
		return recurse(nums, index + 1, currValue ^ nums[index]) + recurse(nums, index + 1, currValue);
	}

	/**
	 * Iterative solution. Considering the length of the nums array as n, this
	 * solution uses all numbers from 0 to 2^n as bit masks in order to generate
	 * alla possible combinations. Time complexity is O(n*2^n).
	 * 
	 * @param nums
	 * @return
	 */
	public static int subsetXORSum2(int[] nums) {
		int n = nums.length;
		int limit = 1 << n; // 2^n

		int sum = 0;
		// iterate all numbers from 0 to 2^n
		for (int i = 0; i < limit; i++) {
			int currentValue = 0;
			// traverse all numbers from 0 to n - 1
			// pick num at index j if the jth bit of i is 1
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) > 0) {
					// pick num at index j because the jth bit of i is 1
					// xor with the current value
					currentValue ^= nums[j];
				}
			}
			// add to the total sum
			sum += currentValue;
		}
		return sum;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int subsetXORSum = subsetXORSum(nums); // Calls your implementation
		System.out.println("subsetXORSum is: " + subsetXORSum);
	}
}
