package leetcode.array;

import java.util.Arrays;

public class ApplyOperationsToAnArray {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 2, 1, 1, 0 }, new int[] { 1, 4, 2, 0, 0, 0 });
		check(new int[] { 0, 1 }, new int[] { 1, 0 });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/apply-operations-to-an-array.
	 * Time complexity is O(n) where n is the length of the nums array.
	 * 
	 * 
	 * @param nums
	 * @return
	 */
	public static int[] applyOperations(int[] nums) {
		int[] result = new int[nums.length];
		int index = 0;
		for (int i = 1; i < nums.length; i++) {
			int prevIndex = i - 1;
			if (nums[prevIndex] == nums[i]) {
				nums[prevIndex] <<= 1;
				nums[i] = 0;
			}
			if (nums[prevIndex] > 0) {
				result[index++] = nums[prevIndex];
			}
		}
		if (nums[nums.length - 1] > 0) {
			result[index++] = nums[nums.length - 1];
		}
		return result;
	}

	private static void check(int[] nums, int[] expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] applyOperations = applyOperations(nums); // Calls your implementation
		System.out.println("applyOperations is: " + Arrays.toString(applyOperations));
	}
}
