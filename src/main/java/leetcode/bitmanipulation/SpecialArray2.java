package leetcode.bitmanipulation;

import java.util.Arrays;

public class SpecialArray2 {

	public static void main(String[] args) {
		check(new int[] { 4, 3, 1, 6 }, new int[][] { { 0, 2 }, { 2, 3 } }, new boolean[] { false, true });
		check(new int[] { 3, 4, 1, 2, 6 }, new int[][] { { 0, 4 } }, new boolean[] { false });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/special-array-ii. This
	 * solution keeps an array with the count of the differences in the last digit
	 * between subsequent positions up to index i. A subarray is special if the
	 * number of differences is equal to its length. Time complexity is O(m+n) where
	 * m is the length of the nums array and n is the length of the queries array.
	 * 
	 * @param nums
	 * @param queries
	 * @return
	 */
	public static boolean[] isArraySpecial(int[] nums, int[][] queries) {
		int[] lastDigitDiffs = new int[nums.length];
		for (int i = 1; i < nums.length; i++) {
			int prevI = i - 1;
			lastDigitDiffs[i] = lastDigitDiffs[prevI] + ((nums[i] & 1) ^ (nums[prevI] & 1));
		}
		boolean[] results = new boolean[queries.length];
		for (int i = 0; i < queries.length; i++) {
			int startIndex = queries[i][1];
			int endIndex = queries[i][0];
			results[i] = endIndex - startIndex == lastDigitDiffs[endIndex] - lastDigitDiffs[startIndex];
		}
		return results;
	}

	private static void check(int[] nums, int[][] queries, boolean[] expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("queries is: ");
		for (int[] query : queries) {
			System.out.println(Arrays.toString(query));
		}
		System.out.println("expected is: " + Arrays.toString(expected));
		boolean[] isArraySpecial = isArraySpecial(nums, queries); // Calls your implementation
		System.out.println("isArraySpecial is: " + Arrays.toString(isArraySpecial));
	}
}
