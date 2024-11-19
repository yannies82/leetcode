package leetcode.slidingwindow;

import java.util.Arrays;

public class MaximumSumOfDistinctSubarraysWithLengthK {

	public static void main(String[] args) {
		check(new int[] { 1, 5, 4, 2, 9, 9, 9 }, 3, 15);
		check(new int[] { 4, 4, 4 }, 3, 0);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k.
	 * This solution uses a sliding window of fixed size to calculate the sum of the
	 * current window and the count of duplicate numbers. Time complexity is O(n)
	 * where n is the length of the nums array.
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static long maximumSubarraySum(int[] nums, int k) {
		// keeps frequency of subarray numbers
		int[] frequency = new int[100001];
		int duplicateCount = 0;
		long sum = 0;
		for (int i = 0; i < k; i++) {
			frequency[nums[i]]++;
			// add 1 if frequency[nums[i]] > 1
			duplicateCount += (1 - frequency[nums[i]]) >>> 31;
			sum += nums[i];
		}
		long maxSum = 0;
		if (duplicateCount == 0) {
			maxSum = sum;
		}
		for (int i = k; i < nums.length; i++) {
			frequency[nums[i]]++;
			// add 1 if frequency[nums[i]] > 1
			duplicateCount += (1 - frequency[nums[i]]) >>> 31;
			int indexToRemove = i - k;
			frequency[nums[indexToRemove]]--;
			// subtract 1 if frequency[nums[i]] >= 1
			duplicateCount -= (-frequency[nums[indexToRemove]]) >>> 31;
			sum += nums[i] - nums[indexToRemove];
			if (duplicateCount == 0 && sum > maxSum) {
				maxSum = sum;
			}
		}
		return maxSum;
	}

	private static void check(int[] nums, int k, long expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		long maximumSubarraySum = maximumSubarraySum(nums, k); // Calls your implementation
		System.out.println("maximumSubarraySum is: " + maximumSubarraySum);
	}
}
