package leetcode.bitmanipulation;

import java.util.Arrays;

public class MinimumNumberOfKConsecutiveBitFlips {

	public static void main(String[] args) {
		check(new int[] { 0, 1, 0, 0, 1, 0 }, 4, 3);
		check(new int[] { 0, 1, 0 }, 1, 2);
		check(new int[] { 1, 1, 0 }, 2, -1);
		check(new int[] { 0, 0, 0, 1, 0, 1, 1, 0 }, 3, 3);
		check(new int[] { 0, 1, 1 }, 2, -1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips. This
	 * solution uses an array which keeps the flipped state for each position to
	 * mark which positions have been flipped and tries to greedily flip positions
	 * as it iterates the array. Time complexity is O(n) where n is the length of
	 * the nums array.
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int minKBitFlips(int[] nums, int k) {
		int flipped = 0;
		int res = 0;
		int[] isFlipped = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			if (i >= k) {
				flipped ^= isFlipped[i - k];
			}
			if (flipped == nums[i]) {
				if (i + k > nums.length) {
					return -1;
				}
				isFlipped[i] = 1;
				flipped ^= 1;
				res++;
			}
		}
		return res;
	}

	/**
	 * Simple solution which iterates all numbers and greedily flips all subarrays
	 * of size k starting at the current position, if the current bit is 0. Time
	 * complexity is O(n*k) where n is the length of the nums array.
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int minKBitFlips2(int[] nums, int k) {
		int result = 0;
		int limit = nums.length - k;
		for (int i = 0; i <= limit; i++) {
			if (nums[i] == 0) {
				for (int j = 0; j < k; j++) {
					nums[i + j] ^= 1;
				}
				result++;
			}
		}
		for (int i = limit + 1; i < nums.length; i++) {
			if (nums[i] == 0) {
				return -1;
			}
		}
		return result;
	}

	private static void check(int[] nums, int k, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		int minKBitFlips = minKBitFlips(nums, k); // Calls your implementation
		System.out.println("minKBitFlips is: " + minKBitFlips);
	}
}
