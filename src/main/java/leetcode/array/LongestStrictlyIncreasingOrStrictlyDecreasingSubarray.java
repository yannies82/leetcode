package leetcode.array;

import java.util.Arrays;

public class LongestStrictlyIncreasingOrStrictlyDecreasingSubarray {

	public static void main(String[] args) {
		check(new int[] { 1, 4, 3, 3, 2 }, 2);
		check(new int[] { 3, 3, 3, 3 }, 1);
		check(new int[] { 3, 2, 1 }, 3);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray.
	 * Time complexity is O(n) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int longestMonotonicSubarray(int[] nums) {
		int maxLength = 1;
		int increasingLength = 1;
		int decreasingLength = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > nums[i - 1]) {
				increasingLength++;
				decreasingLength = 1;
				maxLength = Math.max(maxLength, increasingLength);
			} else if (nums[i] < nums[i - 1]) {
				decreasingLength++;
				increasingLength = 1;
				maxLength = Math.max(maxLength, decreasingLength);
			} else {
				increasingLength = 1;
				decreasingLength = 1;
			}
		}
		return maxLength;
	}

	public static int longestMonotonicSubarray2(int[] nums) {
		int maxLength = 1;
		int length = 1;
		int mode = 0;
		for (int i = 1; i < nums.length; i++) {
			int diff = nums[i] - nums[i - 1];
			if (diff == 0) {
				mode = 0;
				length = 1;
			} else if ((diff > 0 && mode >= 0) || (diff < 0 && mode <= 0)) {
				mode = -(diff >>> 31 << 1) + 1;
				length++;
				maxLength = Math.max(maxLength, length);
			} else {
				mode = -mode;
				length = 2;
			}
		}
		return maxLength;
	}

	public static int longestMonotonicSubarray3(int[] nums) {
		int maxLength = 1;
		int length = 1;
		int mode = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > nums[i - 1]) {
				if (mode == 0) {
					mode = 1;
				}
				if (mode == 1) {
					length++;
					maxLength = Math.max(maxLength, length);
				} else {
					length = 2;
					mode = 1;
				}
			} else if (nums[i] < nums[i - 1]) {
				if (mode == 0) {
					mode = 2;
				}
				if (mode == 2) {
					length++;
					maxLength = Math.max(maxLength, length);
				} else {
					length = 2;
					mode = 2;
				}
			} else {
				mode = 0;
				length = 1;
			}
		}
		return maxLength;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int longestMonotonicSubarray = longestMonotonicSubarray(nums); // Calls your implementation
		System.out.println("longestMonotonicSubarray is: " + longestMonotonicSubarray);
	}
}
