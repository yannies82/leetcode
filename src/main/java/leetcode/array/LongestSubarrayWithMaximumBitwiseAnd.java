package leetcode.array;

import java.util.Arrays;

public class LongestSubarrayWithMaximumBitwiseAnd {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3, 3, 2, 2 }, 2);
		check(new int[] { 1, 2, 3, 4 }, 1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/longest-subarray-with-maximum-bitwise-and. This
	 * problem can be described in much simpler terms: Given an array of integers
	 * nums, find the max number in nums and the length of the longest subarray
	 * containing only the max number. Time complexity is O(n) where n is the length
	 * of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int longestSubarray(int[] nums) {
		// find the max number in nums
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > max) {
				max = nums[i];
			}
		}
		// find the length of the longest subarray containing only the max number
		int maxLength = 0;
		int currentLength = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == max) {
				currentLength++;
				if (currentLength > maxLength) {
					maxLength = currentLength;
				}
			} else {
				currentLength = 0;
			}
		}
		return maxLength;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int longestSubarray = longestSubarray(nums); // Calls your implementation
		System.out.println("longestSubarray is: " + longestSubarray);
	}
}
