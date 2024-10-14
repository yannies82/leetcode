package leetcode.array;

import java.util.Arrays;

public class BuySellStock {

	public static void main(String[] args) {
		check(new int[] { 7, 1, 5, 3, 6, 4 }, 5);
		check(new int[] { 7, 6, 4, 3, 1 }, 0);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock. This solution
	 * uses greedy logic to calculate the max profit. It keeps the minimum value and
	 * compares each value to the min. Every time a new min is encountered the
	 * subsequent prices are compared against the new min. Time complexity is O(n)
	 * where n is the length of the prices array.
	 * 
	 * @param prices
	 * @return
	 */
	public static int maxProfit(int[] prices) {
		int length = prices.length;
		int minFound = prices[0];
		int maxProfit = 0;
		for (int i = 1; i < length; i++) {
			if (prices[i] < minFound) {
				minFound = prices[i];
			} else {
				int profit = prices[i] - minFound;
				if (profit > maxProfit) {
					maxProfit = profit;
				}
			}
		}
		return maxProfit;
	}

	private static void check(int[] nums, int expected) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expected is: " + expected);
		int maxProfit = maxProfit(nums); // Calls your implementation
		System.out.println("maxProfit is: " + maxProfit);
	}
}
