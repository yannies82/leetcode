package leetcode.bitmanipulation;

import java.util.Arrays;

public class MaximumXorForEachQuery {

	public static void main(String[] args) {
		check(new int[] { 0, 1, 1, 3 }, 2, new int[] { 0, 3, 2, 3 });
		check(new int[] { 2, 3, 4, 7 }, 3, new int[] { 5, 2, 6, 5 });
		check(new int[] { 0, 1, 2, 2, 5, 7 }, 3, new int[] { 4, 3, 6, 4, 6, 7 });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/maximum-xor-for-each-query.
	 * Time complexity is O(n) where n is the length of nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int[] getMaximumXor(int[] nums, int maximumBit) {
		// calculate the mask to be equal to 2^maximumBit - 1
		int mask = 1;
		for (int i = 1; i < maximumBit; i++) {
			mask = (mask << 1) + 1;
		}
		// calculate the xor of all array numbers
		int numsXor = nums[0];
		for (int i = 1; i < nums.length; i++) {
			numsXor ^= nums[i];
		}
		int lastIndex = nums.length - 1;
		int[] result = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			// a = numsXor & mask keeps the maximumBit - 1 significant bits of numsXor
			// mask - (numsXor & mask) gives us number b for which a + b == a ^ b == mask
			// and mask is the max possible value for the maximumBit - 1 significant bits
			result[i] = mask - (numsXor & mask);
			// remove the last value from the numsXor calculation by applying xor
			numsXor ^= nums[lastIndex - i];
		}
		return result;
	}

	private static void check(int[] nums, int maximumBit, int[] expected) {
		System.out.println("ratings is: " + Arrays.toString(nums));
		System.out.println("maximumBit is: " + maximumBit);
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] getMaximumXor = getMaximumXor(nums, maximumBit); // Calls your implementation
		System.out.println("getMaximumXor is: " + Arrays.toString(getMaximumXor));
	}
}
