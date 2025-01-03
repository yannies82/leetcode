package leetcode.array;

import java.util.Arrays;

public class NumberOfWaysToSplitArray {

	public static void main(String[] args) {
		check(new int[] { 10, 4, -8, 7 }, 2);
		check(new int[] { 2, 3, 1, 0 }, 2);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/number-of-ways-to-split-array. Time complexity
	 * is O(n) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int waysToSplitArray(int[] nums) {
		long sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		long threshold = (sum + 1) >> 1;
		long currentSum = 0;
		int lastIndex = nums.length - 1;
		int result = 0;
		for (int i = 0; i < lastIndex; i++) {
			currentSum += nums[i];
			if (currentSum >= threshold) {
				result++;
			}
		}
		return result;
	}

	/**
	 * Alternative, branchless solution. Time complexity is O(n) where n is the
	 * length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int waysToSplitArray2(int[] nums) {
		long sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		long threshold = ((sum + 1) >> 1) - 1;
		long currentSum = 0;
		int lastIndex = nums.length - 1;
		int result = 0;
		for (int i = 0; i < lastIndex; i++) {
			currentSum += nums[i];
			result += ((threshold - currentSum) >>> 63) & 1;
		}
		return result;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int waysToSplitArray = waysToSplitArray(nums); // Calls your implementation
		System.out.println("waysToSplitArray is: " + waysToSplitArray);
	}
}
