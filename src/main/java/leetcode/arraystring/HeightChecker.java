package leetcode.arraystring;

import java.util.Arrays;

public class HeightChecker {

	public static void main(String[] args) {
		check(new int[] { 1, 1, 4, 2, 1, 3 }, 3);
		check(new int[] { 5, 1, 2, 3, 4 }, 5);
		check(new int[] { 1, 2, 3, 4, 5 }, 0);

	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/height-checker. Time
	 * complexity is O(nlogn) where n is the length of the heights array.
	 * 
	 * @param heights
	 * @return
	 */
	public static int heightChecker(int[] heights) {
		int[] expected = new int[heights.length];
		System.arraycopy(heights, 0, expected, 0, heights.length);
		Arrays.sort(expected);
		int diff = 0;
		for (int i = 0; i < heights.length; i++) {
			if (heights[i] != expected[i]) {
				diff++;
			}
		}
		return diff;
	}

	private static void check(int[] ratings, int expected) {
		System.out.println("ratings is: " + Arrays.toString(ratings));
		System.out.println("expected is: " + expected);
		int heightChecker = heightChecker(ratings); // Calls your implementation
		System.out.println("heightChecker is: " + heightChecker);
	}
}
