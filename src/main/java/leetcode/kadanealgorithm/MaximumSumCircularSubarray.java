package leetcode.kadanealgorithm;

import java.util.Arrays;

public class MaximumSumCircularSubarray {

	public static void main(String[] args) {
		int[] nums0 = { 1, -2, 3, -2 };
		check(nums0, 3);
		int[] nums1 = { 5, -3, 5 };
		check(nums1, 10);
		int[] nums2 = { -3, -2, -3 };
		check(nums2, -2);
		int[] nums4 = { 5, 5, 0, -5, 3, -3, 2 };
		check(nums4, 12);
		int[] nums3 = { -1, 5, -2, 5, -2, -2, -2, 5, -2, 5, -1 };
		check(nums3, 14);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/maximum-sum-circular-subarray. This solution
	 * calculates the sum of all numbers, the maximum sum subarray and the minimum
	 * sum subarray using Kadane's algorithm. Time complexity is O(N) where N is the
	 * length of the array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int maxSubArray(int[] nums) {
		// keeps the current sum
		int sum = nums[0];
		// keeps the sum of the maximum subarray
		int maxSum = nums[0];
		// keeps the sum of the minimum subarray
		int minSum = nums[0];
		// used for calculating the maximum sum subarray
		int currentMaxSum = nums[0];
		// used for calculating the minimum sum subarray
		int currentMinSum = nums[0];
		// iterate all numbers
		for (int i = 1; i < nums.length; i++) {
			// calculate the maximum sum for this index
			currentMaxSum = Math.max(currentMaxSum + nums[i], nums[i]);
			// update the sum of the maximum subarray if needed
			if (currentMaxSum > maxSum) {
				maxSum = currentMaxSum;
			}
			// calculate the minimum sum for this index
			currentMinSum = Math.min(currentMinSum + nums[i], nums[i]);
			// update the sum of the minimum subarray if needed
			if (currentMinSum < minSum) {
				minSum = currentMinSum;
			}
			// add number to the total sum
			sum += nums[i];
		}
		// if the sum of the minimum subarray is equal to the total sum of the numbers
		// this means that all numbers are negative
		// return the maximum of these negative numbers which has been stored in maxSum
		if (sum == minSum) {
			return maxSum;
		}
		// the result will be either the sum of the maximum subarray
		// or the sum of all numbers except for the minimum subarray
		return Math.max(maxSum, sum - minSum);
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int maxSubArray = maxSubArray(nums); // Calls your implementation
		System.out.println("maxSubArray is: " + maxSubArray);
	}
}
