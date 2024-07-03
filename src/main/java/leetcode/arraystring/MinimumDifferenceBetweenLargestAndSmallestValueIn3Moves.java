package leetcode.arraystring;

import java.util.Arrays;

public class MinimumDifferenceBetweenLargestAndSmallestValueIn3Moves {

	public static void main(String[] args) {
		check(new int[] { 5, 3, 2, 4 }, 0);
		check(new int[] { 1, 5, 0, 10, 14 }, 1);
		check(new int[] { 3, 100, 20 }, 0);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves.
	 * This solution sorts the input array, then compares the possible diffs between
	 * the numbers to find the minimum. Time complexity is O(nlogn) where n is the
	 * length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int minDifference(int[] nums) {
		if (nums.length <= 4) {
			// early exit if array length is less than 5
			return 0;
		}
		Arrays.sort(nums);
		int lastIndex = nums.length - 1;
		int min = Integer.MAX_VALUE;
		// try all possible combinations after sorting the array and update the min
		for (int i = 0; i <= 3; i++) {
			min = Math.min(min, nums[lastIndex + i - 3] - nums[i]);
		}
		return min;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int minDifference = minDifference(nums); // Calls your implementation
		System.out.println("minDifference is: " + minDifference);
	}
}
