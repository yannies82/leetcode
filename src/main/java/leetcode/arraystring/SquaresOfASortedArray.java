package leetcode.arraystring;

import java.util.Arrays;

public class SquaresOfASortedArray {

	public static void main(String[] args) {
		check(new int[] { -4, -1, 0, 3, 10 }, new int[] { 0, 1, 9, 16, 100 });
		check(new int[] { -7, -3, 2, 3, 11 }, new int[] { 4, 9, 9, 49, 121 });
	}

	/**
	 * Leetcode problem link:
	 * https://leetcode.com/problems/squares-of-a-sorted-array. This solution splits
	 * the array into two subarrays, one with elements less than 0 and one with
	 * elements greater than 0. Then, it calculates their squares and performs merge
	 * sort to get the final array. Time complexity is O(n) where n is the nums
	 * array length.
	 * 
	 * @param nums
	 * @return
	 */
	public static int[] sortedSquares(int[] nums) {
		int[] lessThanZero = new int[nums.length];
		int[] greaterThanZero = new int[nums.length];
		int lessThanZeroIndex = 0;
		int greaterThanZeroIndex = 0;
		// split the array into two subarrays and calculate the squares of each element
		// at the same time
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] < 0) {
				lessThanZero[lessThanZeroIndex++] = nums[i] * nums[i];
			} else {
				greaterThanZero[greaterThanZeroIndex++] = nums[i] * nums[i];
			}
		}
		// lessThanZero array is descending while greaterThanZero array is ascending
		int lesserIndex = lessThanZeroIndex - 1;
		int greaterIndex = 0;
		int finalIndex = 0;
		// perform merge sort
		while (lesserIndex >= 0 && greaterIndex < greaterThanZeroIndex) {
			if (lessThanZero[lesserIndex] < greaterThanZero[greaterIndex]) {
				nums[finalIndex++] = lessThanZero[lesserIndex--];
			} else {
				nums[finalIndex++] = greaterThanZero[greaterIndex++];
			}
		}
		while (lesserIndex >= 0) {
			nums[finalIndex++] = lessThanZero[lesserIndex--];
		}
		while (greaterIndex < greaterThanZeroIndex) {
			nums[finalIndex++] = greaterThanZero[greaterIndex++];
		}
		return nums;
	}

	private static void check(int[] nums, int[] expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] sortedSquares = sortedSquares(nums); // Calls your implementation
		System.out.println("sortedSquares is: " + Arrays.toString(sortedSquares));
	}
}
