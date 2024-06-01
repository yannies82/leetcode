package leetcode.bitmanipulation;

import java.util.Arrays;

public class SingleNumber3 {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 1, 3, 2, 5 }, new int[] { 5, 3 });
		check(new int[] { -1, 0 }, new int[] { 0, -1 });
		check(new int[] { 0, 1 }, new int[] { 0, 1 });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/single-number-iii. This
	 * solution calculates the XOR of all array elements, then separates the
	 * elements into two partitions according to the first set bit in the result and
	 * calculates the XOR for each partition. Time complexity is O(n) where n is the
	 * length of the nums array.
	 * 
	 * @param nums
	 * @return
	 */
	public static int[] singleNumber(int[] nums) {
		int distinctNumbersXor = 0;
		// calculate the XOR of all numbers, the equal ones are
		// cancelled out and what is left is the XOR between the distinct ones
		for (int i = 0; i < nums.length; i++) {
			distinctNumbersXor = distinctNumbersXor ^ nums[i];
		}
		// find the first bit of the distinctNumbersXor which is set to 1
		int mask = 1;
		while ((distinctNumbersXor & mask) == 0) {
			mask <<= 1;
		}

		// mask contains the first bit where the two distinct numbers differ
		// this means that we can partition the numbers according to
		// this bit and calculate their XOR
		// the result of each partition will be each one of the distinct numbers
		int partition1 = 0;
		int partition2 = 0;
		for (int i = 0; i < nums.length; i++) {
			if ((nums[i] & mask) == 0) {
				partition1 ^= nums[i];
			} else {
				partition2 ^= nums[i];
			}
		}
		return new int[] { partition1, partition2 };
	}

	private static void check(int[] nums, int[] expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] singleNumber = singleNumber(nums); // Calls your implementation
		System.out.println("singleNumber is: " + Arrays.toString(singleNumber));
	}

}
