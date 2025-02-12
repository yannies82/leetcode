package leetcode.array;

import java.util.Arrays;

public class MaxSumOfAPairWithEqualSumOfDigits {

	public static void main(String[] args) {
		check(new int[] { 18, 43, 36, 13, 7 }, 54);
		check(new int[] { 10, 12, 19, 14 }, -1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/max-sum-of-a-pair-with-equal-sum-of-digits.
	 * This solution keeps the max sum per index sum in order to calculate the final
	 * result. Time complexity is O(n) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int maximumSum(int[] nums) {
		int maxSum = -1;
		int[] maxPerIndexSum = new int[100];
		for (int i = 0; i < nums.length; i++) {
			int indexSum = calculateIndexSum(nums[i]);
			if (maxPerIndexSum[indexSum] > 0) {
				maxSum = Math.max(maxSum, nums[i] + maxPerIndexSum[indexSum]);
			}
			maxPerIndexSum[indexSum] = Math.max(maxPerIndexSum[indexSum], nums[i]);
		}
		return maxSum;
	}

	private static int calculateIndexSum(int num) {
		int result = 0;
		do {
			result += num % 10;
		} while ((num /= 10) > 0);
		return result;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int maximumSum = maximumSum(nums); // Calls your implementation
		System.out.println("maximumSum is: " + maximumSum);
	}
}
