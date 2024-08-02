package leetcode.slidingwindow;

import java.util.Arrays;

public class MinimumSwapsToGroupAll1sTogether2 {

	public static void main(String[] args) {
		check(new int[] { 0, 1, 0, 1, 1, 0, 0 }, 1);
		check(new int[] { 0, 1, 1, 1, 0, 0, 1, 1, 0 }, 2);
		check(new int[] { 1, 1, 0, 0, 1 }, 0);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together-ii. This
	 * solution calculates the number of 1s in the nums array. Then it checks all
	 * subarrays of size oneCount to find the one with the least number of 0s
	 * (because the num of substitutions required is equal to the number of 0s in
	 * the subarray). It uses an extended array of size nums.length + oneCount in
	 * order to take into the circular property into consideration. Time complexity
	 * is O(n) where n is the length of the nums array.
	 * 
	 * 
	 * @param nums
	 * @return
	 */
	public static int minSwaps(int[] nums) {
		// find the number of ones in the array
		int oneCount = 0;
		for (int i = 0; i < nums.length; i++) {
			oneCount += nums[i];
		}
		// create and extended nums array of size nums.length + oneCount
		// so that we can also check circular subarrays of size oneCount
		int[] extendedNums = new int[nums.length + oneCount];
		System.arraycopy(nums, 0, extendedNums, 0, nums.length);
		System.arraycopy(nums, 0, extendedNums, nums.length, oneCount);
		// count the 0s of the first subarray of size oneCount
		int minSwaps = oneCount;
		int currentZeroCount = 0;
		for (int i = 0; i < oneCount; i++) {
			currentZeroCount += 1 - extendedNums[i];
		}
		// check the number of 0s in all subarrays and update minSwaps accordingly
		// on each iteration move the sliding window by 1
		for (int i = 0; i < nums.length; i++) {
			if (currentZeroCount < minSwaps) {
				minSwaps = currentZeroCount;
			}
			// move the sliding window by 1, disregard the element at index i
			// and take into account the element at index i + oneCount
			currentZeroCount -= 1 - extendedNums[i];
			currentZeroCount += 1 - extendedNums[i + oneCount];
		}
		return minSwaps;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int minSwaps = minSwaps(nums); // Calls your implementation
		System.out.println("minSwaps is: " + minSwaps);
	}
}
