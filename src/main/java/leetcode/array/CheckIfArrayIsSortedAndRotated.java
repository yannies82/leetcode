package leetcode.array;

import java.util.Arrays;

public class CheckIfArrayIsSortedAndRotated {

	public static void main(String[] args) {
		check(new int[] { 3, 4, 5, 1, 2 }, true);
		check(new int[] { 2, 1, 3, 4 }, false);
		check(new int[] { 1, 2, 3 }, true);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/check-if-array-is-sorted-and-rotated. Time
	 * complexity is O(n) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static boolean check(int[] nums) {
		int count = 0;
		for (int i = 1; i < nums.length; i++) {
			count += (nums[i] - nums[i - 1]) >>> 31;
		}
		count += (nums[0] - nums[nums.length - 1]) >>> 31;
		return count <= 1;
	}

	private static void check(int[] nums, boolean expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		boolean check = check(nums); // Calls your implementation
		System.out.println("check is: " + check);
	}
}
