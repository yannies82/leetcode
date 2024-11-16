package leetcode.slidingwindow;

import java.util.Arrays;

public class FindThePowerOfKSizeSubarrays {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3, 4, 3, 2, 5 }, 3, new int[] { 3, 4, -1, -1, -1 });
		check(new int[] { 2, 2, 2, 2, 2 }, 4, new int[] { -1, -1 });
		check(new int[] { 3, 2, 3, 2, 3, 2 }, 2, new int[] { -1, 3, -1, 3, -1 });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/find-the-power-of-k-size-subarrays-i. This
	 * solution uses a sliding window of size k and checks if each subarray contains
	 * a number which does not conform to the expected condition. Time complexity is
	 * O(n) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int[] resultsArray(int[] nums, int k) {
		if (k == 1) {
			// early exit, each subarray contains a single number
			return nums;
		}
		// keeps the index of the last number which is not consecutive and ascending
		int lastBadIndex = -1;
		int limit = k - 1;
		for (int i = 1; i < limit; i++) {
			if (nums[i] != nums[i - 1] + 1) {
				lastBadIndex = i;
			}
		}
		// check all subarrays of size k
		int[] result = new int[nums.length - k + 1];
		for (int i = limit; i < nums.length; i++) {
			if (nums[i] != nums[i - 1] + 1) {
				lastBadIndex = i;
			}
			int startIndex = i - k + 1;
			if (lastBadIndex > startIndex) {
				// lastBadIndex is contained in the subarray
				result[startIndex] = -1;
			} else {
				// lastBadIndex is not contained by the subarray or is the first element
				result[startIndex] = nums[i];
			}
		}
		return result;
	}

	private static void check(int[] nums, int k, int[] expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] resultsArray = resultsArray(nums, k); // Calls your implementation
		System.out.println("resultsArray is: " + Arrays.toString(resultsArray));
	}
}
