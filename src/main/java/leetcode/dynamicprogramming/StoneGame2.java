package leetcode.dynamicprogramming;

import java.util.Arrays;

public class StoneGame2 {

	public static void main(String[] args) {
		check(new int[] { 1 }, 1);
		check(new int[] { 2, 7, 9, 4, 4 }, 10);
		check(new int[] { 1, 2, 3, 4, 5, 100 }, 104);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/stone-game-ii. This solution
	 * uses top down dynamic programming to calculate the result. Time complexity is
	 * O(n^2) where n is the length of the piles array.
	 * 
	 * @param piles
	 * @return
	 */
	public static int stoneGameII(int[] piles) {
		if (piles == null || piles.length == 0) {
			// early exit
			return 0;
		}

		int n = piles.length;
		// this array keeps the total stones from the current pile until the last pile
		// populate it
		int[] suffixSums = new int[n];
		suffixSums[n - 1] = piles[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			suffixSums[i] = suffixSums[i + 1] + piles[i];
		}
		// this array keeps solutions to subproblems
		// dpArray[i][m] contains the solution starting from pile i and using value m
		int[][] dpArray = new int[n][n];
		// our answer is dpArray[0][1], the solution starting from pile 0 and with m = 1
		// we calculate it recursively using top down dynamic programming
		return calculate(piles, 0, 1, suffixSums, dpArray);
	}

	private static int calculate(int[] piles, int i, int m, int[] suffixSums, int[][] dpArray) {
		if (i == piles.length) {
			return 0;
		}
		int doubleM = m << 1;
		if (doubleM >= piles.length - i) {
			// alice can take all of the remaining piles since they are less than 2 * m
			return suffixSums[i];
		}
		if (dpArray[i][m] != 0) {
			// solution to this subproblem has already been calculated, return it
			return dpArray[i][m];
		}

		// in order to find the best solution dpArray[i][m] for Alice the aim is to find
		// the min solution for i + 1 up to i + 2m (the worst solution that is left for
		// Bob) and subtract it from suffixSums[i]
		int min = Integer.MAX_VALUE;
		for (int j = 1; j <= doubleM; j++) {
			min = Math.min(min, calculate(piles, i + j, Math.max(m, j), suffixSums, dpArray));
		}

		dpArray[i][m] = suffixSums[i] - min;
		return dpArray[i][m];
	}

	private static void check(int[] piles, int expected) {
		System.out.println("piles is: " + Arrays.toString(piles));
		System.out.println("expected is: " + expected);
		int stoneGameII = stoneGameII(piles); // Calls your implementation
		System.out.println("stoneGameII is: " + stoneGameII);
	}
}
