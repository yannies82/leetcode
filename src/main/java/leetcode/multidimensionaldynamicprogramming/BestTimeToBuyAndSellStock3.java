package leetcode.multidimensionaldynamicprogramming;

import java.util.Arrays;

public class BestTimeToBuyAndSellStock3 {

	public static void main(String[] args) {
		check(new int[] { 3, 3, 5, 0, 0, 3, 1, 4 }, 6);
		check(new int[] { 1, 2, 4, 2, 5, 7, 2, 4, 9, 0 }, 13);
		check(new int[] { 1, 2, 3, 4, 5 }, 4);
		check(new int[] { 7, 6, 4, 3, 1 }, 0);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii. This
	 * solution divides the problem into two subproblems (find the maxProfit when
	 * only one transaction is allowed) and uses bottom up dynamic programming to
	 * calculate all intermediate solutions to the two subproblems. The final
	 * solution will occur at the index where the sum of the subproblem solutions is
	 * maximized. Time complexity is O(n) where n is the prices array length.
	 * 
	 * @param prices
	 * @return
	 */
	public static int maxProfit(int[] prices) {
		int length = prices.length;
		int lastIndex = length - 1;
		// keeps the solutions for the first subproblem, find the max profit from 0 to i
		// using only one transaction
		int[] dpLeft = new int[prices.length];
		// keeps the solutions for the second subproblem, find the max profit from
		// length to i
		// using only one transaction
		int[] dpRight = new int[prices.length];
		// max profits for the sub problem starting points are 0
		dpLeft[0] = 0;
		dpRight[lastIndex] = 0;
		// keep the min value for the first subproblem in order to greedily calculate
		// and update
		// the max profit
		int minLeft = prices[0];
		// keep the max value for the second subproblem in order to greedily calculate
		// and update
		// the max profit
		int maxRight = prices[lastIndex];
		// iterate all prices except for the starting point
		for (int i = 1; i < length; i++) {
			// solution for the first subproblem is calculated greedily
			// we buy at a price and sell at another price so we keep a min value
			// and try to find the max profit by subtracting it from every price
			// update the max profit if the current one is greater or keep the previous max
			// profit
			dpLeft[i] = Math.max(dpLeft[i - 1], prices[i] - minLeft);
			// update the min price
			minLeft = Math.min(minLeft, prices[i]);
			// solution for the second subproblem is calculated greedily
			// we sell at a price after having bought at another price so we keep a max
			// value and try to subtract every price from it to find the max profit
			// this is because for this subproblem we traverse the array in reverse order
			// update the max profit if the current one is greater or keep the previous max
			// profit
			dpRight[lastIndex - i] = Math.max(dpRight[lastIndex - i + 1], maxRight - prices[lastIndex - i]);
			// update the max price
			maxRight = Math.max(maxRight, prices[lastIndex - i]);
		}
		int maxProfit = 0;
		// find the index where the sum of the max profits from both subproblem
		// solutions is maximized and update the max profit
		for (int i = 0; i < length; i++) {
			int currentProfit;
			if ((currentProfit = dpLeft[i] + dpRight[i]) > maxProfit) {
				maxProfit = currentProfit;
			}
		}
		return maxProfit;
	}

	private static void check(int[] prices, int expected) {
		System.out.println("prices is: " + Arrays.toString(prices));
		System.out.println("expected is: " + expected);
		int maxProfit = maxProfit(prices); // Calls your implementation
		System.out.println("maxProfit is: " + maxProfit);
	}

}
