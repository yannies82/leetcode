package leetcode.bitmanipulation;

import java.util.Arrays;

public class LargestCombinationWithBitwiseAndGreaterThanZero {

	public static void main(String[] args) {
		check(new int[] { 16, 17, 71, 62, 12, 24, 14 }, 4);
		check(new int[] { 8, 8 }, 2);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/largest-combination-with-bitwise-and-greater-than-zero.
	 * This solution exploits the fact that the largest combination of numbers needs
	 * to have the same bit set. Therefore, we should check how many numbers in the
	 * array have each bit set and return the max count as the result. Time
	 * complexity is O(n) where n is the length of the candidates array.
	 * 
	 * @param arr
	 * @return
	 */
	public static int largestCombination(int[] candidates) {
		// upper limit for candidates[i] is 10^7 which is 23 bits long
		int[] counts = new int[24];
		int maxCount = 0;
		for (int i = 0; i < counts.length; i++) {
			for (int j = 0; j < candidates.length; j++) {
				// apply a mask to get the jth bit of candidates[i] and add to the respective
				// count
				counts[i] += (candidates[j] >>> i) & 1;
			}
			// find the max value of the counts per bit
			maxCount = Math.max(maxCount, counts[i]);
		}
		return maxCount;
	}

	/**
	 * This solution uses backtracking. Time complexity is O(2^n) where n is the
	 * length of the candidates array.
	 * 
	 * @param candidates
	 * @return
	 */
	public static int largestCombination2(int[] candidates) {
		return solve(candidates, Integer.MAX_VALUE, 0, 0);
	}

	private static int solve(int[] candidates, int current, int index, int length) {
		if (current == 0) {
			return 0;
		}
		if (index == candidates.length) {
			return length;
		}
		int noSelection = solve(candidates, current, index + 1, length);
		int selection = solve(candidates, current & candidates[index], index + 1, length + 1);
		return Math.max(noSelection, selection);
	}

	private static void check(int[] candidates, int expected) {
		System.out.println("candidates is: " + Arrays.toString(candidates));
		System.out.println("expected is: " + expected);
		int largestCombination = largestCombination(candidates); // Calls your implementation
		System.out.println("largestCombination is: " + largestCombination);
	}
}
