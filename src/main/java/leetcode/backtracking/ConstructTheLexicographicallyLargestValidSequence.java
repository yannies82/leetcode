package leetcode.backtracking;

import java.util.Arrays;

public class ConstructTheLexicographicallyLargestValidSequence {

	public static void main(String[] args) {
		check(3, new int[] { 3, 1, 2, 3, 2 });
		check(4, new int[] { 4, 2, 3, 2, 4, 3, 1 });
		check(5, new int[] { 5, 3, 1, 4, 3, 5, 2, 4, 2 });
		check(7, new int[] { 7, 5, 3, 6, 4, 3, 5, 7, 4, 6, 2, 1, 2 });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence.
	 * This solution uses backtracking to test all valid combinations, favoring
	 * lexicographically larger solutions first. Time complexity is O(n!).
	 * 
	 * @param n
	 * @return
	 */
	public static int[] constructDistancedSequence(int n) {
		int[] result = new int[(n << 1) - 1];
		boolean[] used = new boolean[n + 1];
		calculateRecursive(result, used, n, 0);
		return result;
	}

	private static boolean calculateRecursive(int[] result, boolean[] used, int n, int index) {
		while (index < result.length && result[index] != 0) {
			// find next result index to be filled
			index++;
		}
		if (index == result.length) {
			// all result indexes have been filled
			return true;
		}
		// iterate available numbers in descending order to favor solutions
		// which are lexicographically larger
		for (int i = n; i >= 1; i--) {
			if (used[i]) {
				// index i has already been used in result
				continue;
			}

			if (i == 1) {
				// set 1 to the result and continue with finding a solution
				result[index] = 1;
				used[1] = true;
				if (calculateRecursive(result, used, n, index + 1)) {
					// it was possible to find a solution, therefore it is the optimal one
					return true;
				}
				// it was not possible to find a solution, backtrack and continue with the next
				// number
				result[index] = 0;
				used[1] = false;
			} else if (index + i < result.length && result[index + i] == 0) {
				// set i to the result in both indexes and continue with finding a solution
				result[index] = i;
				result[index + i] = i;
				used[i] = true;
				if (calculateRecursive(result, used, n, index + 1)) {
					// it was possible to find a solution, therefore it is the optimal one
					return true;
				}
				// it was not possible to find a solution, backtrack and continue with the next
				// number
				result[index] = 0;
				result[index + i] = 0;
				used[i] = false;
			}
		}
		return false;
	}

	/**
	 * Solution which uses a lookup table of precalculated values. Time complexity
	 * is O(1).
	 * 
	 * @param n
	 * @return
	 */
	public static int[] constructDistancedSequence2(int n) {
		return new int[][] { null, { 1 }, { 2, 1, 2 }, { 3, 1, 2, 3, 2 }, { 4, 2, 3, 2, 4, 3, 1 },
				{ 5, 3, 1, 4, 3, 5, 2, 4, 2 }, { 6, 4, 2, 5, 2, 4, 6, 3, 5, 1, 3 },
				{ 7, 5, 3, 6, 4, 3, 5, 7, 4, 6, 2, 1, 2 }, { 8, 6, 4, 2, 7, 2, 4, 6, 8, 5, 3, 7, 1, 3, 5 },
				{ 9, 7, 5, 3, 8, 6, 3, 5, 7, 9, 4, 6, 8, 2, 4, 2, 1 },
				{ 10, 8, 6, 9, 3, 1, 7, 3, 6, 8, 10, 5, 9, 7, 4, 2, 5, 2, 4 },
				{ 11, 9, 10, 6, 4, 1, 7, 8, 4, 6, 9, 11, 10, 7, 5, 8, 2, 3, 2, 5, 3 },
				{ 12, 10, 11, 7, 5, 3, 8, 9, 3, 5, 7, 10, 12, 11, 8, 6, 9, 2, 4, 2, 1, 6, 4 },
				{ 13, 11, 12, 8, 6, 4, 9, 10, 1, 4, 6, 8, 11, 13, 12, 9, 7, 10, 3, 5, 2, 3, 2, 7, 5 },
				{ 14, 12, 13, 9, 7, 11, 4, 1, 10, 8, 4, 7, 9, 12, 14, 13, 11, 8, 10, 6, 3, 5, 2, 3, 2, 6, 5 },
				{ 15, 13, 14, 10, 8, 12, 5, 3, 11, 9, 3, 5, 8, 10, 13, 15, 14, 12, 9, 11, 7, 4, 6, 1, 2, 4, 2, 7, 6 },
				{ 16, 14, 15, 11, 9, 13, 6, 4, 12, 10, 1, 4, 6, 9, 11, 14, 16, 15, 13, 10, 12, 8, 5, 7, 2, 3, 2, 5, 3,
						8, 7 },
				{ 17, 15, 16, 12, 10, 14, 7, 5, 3, 13, 11, 3, 5, 7, 10, 12, 15, 17, 16, 14, 9, 11, 13, 8, 6, 2, 1, 2, 4,
						9, 6, 8, 4 },
				{ 18, 16, 17, 13, 11, 15, 8, 14, 4, 2, 12, 2, 4, 10, 8, 11, 13, 16, 18, 17, 15, 14, 12, 10, 9, 7, 5, 3,
						6, 1, 3, 5, 7, 9, 6 },
				{ 19, 17, 18, 14, 12, 16, 9, 15, 6, 3, 13, 1, 3, 11, 6, 9, 12, 14, 17, 19, 18, 16, 15, 13, 11, 10, 8, 4,
						5, 7, 2, 4, 2, 5, 8, 10, 7 },
				{ 20, 18, 19, 15, 13, 17, 10, 16, 7, 5, 3, 14, 12, 3, 5, 7, 10, 13, 15, 18, 20, 19, 17, 16, 12, 14, 11,
						9, 4, 6, 8, 2, 4, 2, 1, 6, 9, 11, 8 } }[n];
	}

	private static void check(int n, int[] expected) {
		System.out.println("n is: " + n);
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] constructDistancedSequence = constructDistancedSequence(n); // Calls your implementation
		System.out.println("constructDistancedSequence is: " + Arrays.toString(constructDistancedSequence));
	}
}
