package leetcode.kadanealgorithm;

import java.util.Arrays;

public class MaximumSubarray {

	public static void main(String[] args) {
		int[] nums0 = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		check(nums0, 6);
		int[] nums1 = { 1 };
		check(nums1, 1);
		int[] nums2 = { 5, 4, -1, 7, 8 };
		check(nums2, 23);
	}

	/**
	 * This solution uses dynamic programming. It calculates the max sum until index
	 * i and uses this result for the calculation of index i+1. The max of the max
	 * sums is the result. Time complexity is O(N) where N is the length of the array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int maxSubArray(int[] nums) {
		int length = nums.length;
		// array which stores the results of dynamic programming calculation per index
		int[] dp = new int[length];
		// max dynamic programming value for index 0 is equal to the first number
		dp[0] = nums[0];
		// initialize the result
		int result = dp[0];
		for (int i = 1; i < length; i++) {
			// the dynamic programming calculation for index i
			// is either the sum of the current number with the dp[i-1]
			// or the current number itself if dp[i-1] < 0
			dp[i] = dp[i - 1] > 0 ? nums[i] + dp[i - 1] : nums[i];
			// update the result if needed
			result = Math.max(result, dp[i]);
		}
		return result;
	}

	/**
	 * This solution iterates all numbers in the array and updates the maxSum. Every
	 * time that the sum gets less than 0 it is reset to 0 and the calculation
	 * resumes from the next index. Time complexity is O(N) where N is the length of
	 * the array. (Kadane's algorithm)
	 * 
	 * @param nums
	 * @return
	 */
	public static int maxSubArray2(int[] nums) {
		// keeps the current sum
		int sum = 0;
		// keeps the maximum sum
		int maxSum = Integer.MIN_VALUE;
		// iterate all numbers
		for (int i = 0; i < nums.length; i++) {
			// add number to the sum
			sum += nums[i];
			// update max sum if needed
			if (sum > maxSum) {
				maxSum = sum;
			}
			// if sum is less than 0 reset it
			if (sum < 0) {
				sum = 0;
			}
		}
		return maxSum;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int maxSubArray = maxSubArray(nums); // Calls your implementation
		System.out.println("maxSubArray is: " + maxSubArray);
	}
}
