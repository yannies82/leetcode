package leetcode.slidingwindow;

import java.util.Arrays;

public class SubarrayProductLessThanK {

	public static void main(String[] args) {
		check(new int[] { 10, 5, 2, 6 }, 100, 8);
		check(new int[] { 1, 2, 3 }, 0, 0);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/subarray-product-less-than-k.
	 * This solution uses a sliding window and calculates the count of all subarrays
	 * with product less than k. Time complexity is O(n) where n is the length of
	 * the nums array.
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int numSubarrayProductLessThanK(int[] nums, int k) {
		int mult = 1;
		int start = 0;
		int result = 0;
		// iterate all numbers
		for (int i = 0; i < nums.length; i++) {
			mult *= nums[i];
			while (mult >= k && start <= i) {
				// shrink window until mult < k
				mult /= nums[start++];
			}
			// add to the result the number of windows with mult up to k
			// that end at i
			result += i - start + 1;
		}
		return result;
	}

	private static void check(int[] nums, int k, int expected) {
		System.out.println("gas is: " + Arrays.toString(nums));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		int numSubarrayProductLessThanK = numSubarrayProductLessThanK(nums, k); // Calls your implementation
		System.out.println("numSubarrayProductLessThanK is: " + numSubarrayProductLessThanK);
	}
}
