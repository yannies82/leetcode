package leetcode.array;

import java.util.Arrays;

public class MaximumBeautyOfAnArrayAfterApplyingOperation {

	public static void main(String[] args) {
		check(new int[] { 4, 6, 1, 2 }, 2, 3);
		check(new int[] { 1, 1, 1, 1 }, 10, 4);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/maximum-beauty-of-an-array-after-applying-operation.
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int maximumBeauty(int[] nums, int k) {
		if (nums.length == 1) {
			return 1;
		}
		// calculate min and max number
		int min = nums[0];
		int max = nums[0];
		for (int i = 0; i < nums.length; i++) {
			min = Math.min(min, nums[i]);
			max = Math.max(max, nums[i]);
		}
		// create a range array to mark the occurrences of numbers, add an offset to
		// each number range so that we make sure that it is always greater than 0
		int offset = k + k + 1;
		int[] range = new int[max + offset + 1];
		for (int i = 0; i < nums.length; i++) {
			// add 1 at the number where the range starts
			range[nums[i]]++;
			// subtract 1 at the next number from the one that the range ends
			range[nums[i] + offset]--;
		}
		int maxLength = 0;
		int length = 0;
		int rangeStart = Math.max(min - k, 0);
		for (int i = rangeStart; i < range.length; i++) {
			// increase/decrease subarray length by the number of occurences at number i
			length += range[i];
			maxLength = Math.max(length, maxLength);
		}
		return maxLength;
	}

	private static void check(int[] nums, int k, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		int maximumBeauty = maximumBeauty(nums, k); // Calls your implementation
		System.out.println("maximumBeauty is: " + maximumBeauty);
	}
}
