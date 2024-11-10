package leetcode.slidingwindow;

import java.util.Arrays;

public class ShortestSubarrayWithOrAtLeastK2 {

	public static void main(String[] args) {
		check(new int[] { 2, 25, 32, 1 }, 59, 3);
		check(new int[] { 1, 2, 3 }, 2, 1);
		check(new int[] { 2, 1, 8 }, 10, 3);
		check(new int[] { 1, 2 }, 0, 1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-ii. This
	 * solution uses a sliding window. Time complexity is O(n) where n is the length
	 * of the nums array.
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int minimumSubarrayLength(int[] nums, int k) {
		// keeps the count of all set bits for the current subarray
		int[] bitCount = new int[31];
		int currentOr = 0;
		int start = 0;
		int end = 0;
		int minLength = Integer.MAX_VALUE;
		// iterate all numbers, increasing the size of the sliding window
		while (true) {
			while (end < nums.length && currentOr < k) {
				currentOr |= nums[end];
				// increase count of the set bits of nums[end]
				for (int bit = 0; bit < 31; bit++) {
					bitCount[bit] += (nums[end] >> bit) & 1;
				}
				end++;
			}
			if (currentOr < k) {
				return minLength == Integer.MAX_VALUE ? -1 : minLength;
			}
			while (start < end && currentOr >= k) {
				// decrease the sliding window by excluding nums[start] and recalculating
				// currentOr
				int updatedOR = 0;
				for (int bit = 0; bit < 31; bit++) {
					// decrease count of the set bits of nums[start]
					bitCount[bit] -= (nums[start] >> bit) & 1;
					if (bitCount[bit] > 0) {
						updatedOR |= (1 << bit);
					}
				}
				currentOr = updatedOR;
				start++;
			}
			minLength = Math.min(minLength, end - start + 1);
			if (minLength == 1) {
				// exit if minLength is 1, it can't get smaller
				return 1;
			}
		}
	}

	/**
	 * Alternative solution, similar to the first one. Time complexity is O(n) where
	 * n is the length of the nums array.
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int minimumSubarrayLength2(int[] nums, int k) {
		// keeps the count of all set bits for the current subarray
		int[] bitCount = new int[31];
		int currentOr = 0;
		int start = 0;
		int minLength = Integer.MAX_VALUE;
		// iterate all numbers, increasing the size of the sliding window
		for (int end = 0; end < nums.length; end++) {
			currentOr |= nums[end];
			// increase count of the set bits of nums[end]
			for (int bit = 0; bit < 31; bit++) {
				bitCount[bit] += (nums[end] >> bit) & 1;
			}
			while (start <= end && currentOr >= k) {
				// if currentOr >= k update minLength
				minLength = Math.min(minLength, end - start + 1);
				if (minLength == 1) {
					// exit if minLength is 1, it can't get smaller
					return 1;
				}
				// decrease the sliding window by excluding nums[start] and recalculating
				// currentOr
				int updatedOR = 0;
				for (int bit = 0; bit < 31; bit++) {
					// decrease count of the set bits of nums[start]
					bitCount[bit] -= (nums[start] >> bit) & 1;
					if (bitCount[bit] > 0) {
						updatedOR |= (1 << bit);
					}
				}
				currentOr = updatedOR;
				start++;
			}
		}
		return minLength == Integer.MAX_VALUE ? -1 : minLength;
	}

	private static void check(int[] nums, int k, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		int minimumSubarrayLength = minimumSubarrayLength(nums, k); // Calls your implementation
		System.out.println("minimumSubarrayLength is: " + minimumSubarrayLength);
	}
}
