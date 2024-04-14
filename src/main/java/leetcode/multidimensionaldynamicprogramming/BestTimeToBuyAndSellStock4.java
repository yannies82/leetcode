package leetcode.multidimensionaldynamicprogramming;

import java.util.Arrays;

public class BestTimeToBuyAndSellStock4 {

	public static void main(String[] args) {
		check(2, new int[] { 3, 2, 6, 5, 0, 3, 4, 2, 8, 5 }, 12);
		check(3, new int[] { 3, 2, 6, 5, 0, 3, 4, 2, 8, 5 }, 14);
		check(2, new int[] { 2, 4, 1 }, 2);
		check(2, new int[] { 3, 2, 6, 5, 0, 3 }, 7);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv. This
	 * solution uses bottom up dynamic programming and a 2D array to store the
	 * results of all subproblems for transactionCount 0 to k and for days 0 to
	 * prices.length. Time complexity is O(k*n) where n is the length of the prices
	 * array.
	 * 
	 * @param k
	 * @param prices
	 * @return
	 */
	public static int maxProfit(int k, int[] prices) {
		int length = prices.length;
		// this 2D array stores the subproblem solutions
		// the first dimension is the number of transactions
		// and the second dimension is the length of the array
		int dpArray[][] = new int[k + 1][length + 1];

		// traverse the prices array and solve the subproblems bottom up
		for (int i = 1; i <= k; i++) {
			int prevDiff = Integer.MIN_VALUE;
			for (int j = 1; j < length; j++) {
				prevDiff = Math.max(prevDiff, dpArray[i - 1][j - 1] - prices[j - 1]);
				dpArray[i][j] = Math.max(dpArray[i][j - 1], prices[j] + prevDiff);
			}
		}
		return dpArray[k][length - 1];
	}

	private static void check(int k, int[] prices, int expected) {
		System.out.println("k is: " + k);
		System.out.println("prices is: " + Arrays.toString(prices));
		System.out.println("expected is: " + expected);
		int maxProfit = maxProfit(k, prices); // Calls your implementation
		System.out.println("maxProfit is: " + maxProfit);
	}

}
