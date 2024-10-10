package leetcode.arraystring;

import java.util.Arrays;

public class MaximumWidthRamp {

	public static void main(String[] args) {
		check(new int[] { 5, 4, 3, 2, 1, 3 }, 3);
		check(new int[] { 6, 0, 8, 2, 1, 5 }, 4);
		check(new int[] { 9, 8, 1, 0, 1, 9, 4, 0, 4, 1 }, 7);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/maximum-width-ramp. This
	 * solutioin finds the decreasing numbers in the nums array and keeps their
	 * indexes in a stack. It then compares the nums array numbers from last to
	 * first against the numbers referenced by the stack indexes in order to
	 * calculate the result. Time complexity is O(n) where n is the length of the
	 * nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int maxWidthRamp(int[] nums) {
		// use an array as a stack
		int[] stack = new int[nums.length];
		// points to the last element in the stack
		int index = 0;
		// add to the stack all decreasing indexes in the nums array
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] < nums[stack[index]]) {
				stack[++index] = i;
			}
		}
		int maxRamp = 0;
		// iterate all numbers from last to first and compare them with the
		// decreasing numbers referenced by the stack indexes, adjusting
		// maxRamp if required
		for (int j = nums.length - 1; j >= 0; j--) {
			while (index >= 0 && nums[j] >= nums[stack[index]]) {
				maxRamp = Math.max(maxRamp, j - stack[index--]);
			}
		}
		return maxRamp;
	}

	/**
	 * Simple solution with O(n^2) time complexity where n is the length of the nums
	 * array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int maxWidthRamp2(int[] nums) {
		int maxRamp = 0;
		for (int i = 0; i < nums.length - maxRamp; i++) {
			int limit = i + maxRamp;
			for (int j = nums.length - 1; j > limit; j--) {
				if (nums[i] <= nums[j]) {
					int ramp = j - i;
					if (ramp > maxRamp) {
						maxRamp = ramp;
					}
					break;
				}
			}
		}
		return maxRamp;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int maxWidthRamp = maxWidthRamp(nums); // Calls your implementation
		System.out.println("maxWidthRamp is: " + maxWidthRamp);
	}
}
