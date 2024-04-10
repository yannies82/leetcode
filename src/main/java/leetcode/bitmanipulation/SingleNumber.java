package leetcode.bitmanipulation;

import java.util.Arrays;

public class SingleNumber {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 3, 2, 1 }, 3);
		check(new int[] { 4, 8, 7, 4, 8, 2, 7 }, 2);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/single-number. Performs the
	 * calculation by applying xor to all numbers. Xor between 2 same numbers
	 * returns zero, therefore the duplicate numbers are cancelled out and the
	 * single one is what remains as a result.
	 * 
	 * @param n
	 * @return
	 */
	public static int singleNumber(int[] nums) {
		int result = 0;
		for (int i = 0; i < nums.length; i++) {
			result = result ^ nums[i];
		}
		return result;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int singleNumber = singleNumber(nums); // Calls your implementation
		System.out.println("singleNumber is: " + singleNumber);
	}

}
