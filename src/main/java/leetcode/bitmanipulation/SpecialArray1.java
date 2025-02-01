package leetcode.bitmanipulation;

import java.util.Arrays;

public class SpecialArray1 {

	public static void main(String[] args) {
		check(new int[] { 1 }, true);
		check(new int[] { 2, 1, 4 }, true);
		check(new int[] { 4, 3, 1, 6 }, false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/special-array-i. Time
	 * complexity is O(n) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static boolean isArraySpecial(int[] nums) {
		int lastIndex = nums.length - 1;
		for (int i = 0; i < lastIndex; i++) {
			if (((nums[i] & 1) ^ (nums[i + 1] & 1)) == 0) {
				return false;
			}
		}
		return true;
	}

	private static void check(int[] nums, boolean expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		boolean isArraySpecial = isArraySpecial(nums); // Calls your implementation
		System.out.println("isArraySpecial is: " + isArraySpecial);
	}

}
