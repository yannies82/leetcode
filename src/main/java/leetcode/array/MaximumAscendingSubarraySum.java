package leetcode.array;

import java.util.Arrays;

public class MaximumAscendingSubarraySum {

	public static void main(String[] args) {
		check(new int[] {10,20,30,5,10,50}, 65);
		check(new int[] {10,20,30,40,50}, 150);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/maximum-ascending-subarray-sum. Branchless
	 * solution. Time complexity is O(n) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int maxAscendingSum(int[] nums) {
		int sum = nums[0];
		int maxSum = sum;
		for (int i = 1; i < nums.length; i++) {
			sum = ((-((nums[i - 1] - nums[i]) >>> 31)) & sum) + nums[i];
			maxSum = Math.max(maxSum, sum);
		}
		return maxSum;
	}
	
	public static int maxAscendingSum2(int[] nums) {
        int sum = nums[0];
        int maxSum = sum;
        for (int i = 1; i < nums.length; i++) {
            sum = ((nums[i-1] - nums[i]) >>> 31) * sum + nums[i];
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int maxAscendingSum = maxAscendingSum(nums); // Calls your implementation
		System.out.println("maxAscendingSum is: " + maxAscendingSum);
	}
}
