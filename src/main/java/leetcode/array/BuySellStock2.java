package leetcode.array;

import java.util.Arrays;

public class BuySellStock2 {

	public static void main(String[] args) {
		check(new int[] { 7, 1, 5, 3, 6, 4 }, 7);
		check(new int[] { 1, 2, 3, 4, 5 }, 4);
		check(new int[] { 7, 6, 4, 3, 1 }, 0);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii. This
	 * solution compares each price with the previous one and if it is greater it
	 * adds the difference to the total profit. Time complexity is O(n) where n is
	 * the length of the prices array.
	 * 
	 * @param prices
	 * @return
	 */
	public static int maxProfit(int[] prices) {
		int length = prices.length;
		int totalProfit = 0;
		for (int i = 1; i < length; i++) {
			if (prices[i] > prices[i - 1]) {
				totalProfit += prices[i] - prices[i - 1];
			}
		}
		return totalProfit;
	}

	/**
	 * This is the same solution as the above one except for it uses a bit
	 * manipulation hack to avoid branching when adding to the total profit.
	 * 
	 * @param prices
	 * @return
	 */
	public static int maxProfit2(int[] prices) {
		int length = prices.length;
		int totalProfit = 0;
		for (int i = 1; i < length; i++) {
			int diff = prices[i] - prices[i - 1];
			// if diff is negative or 0, 0 will be added to the total profit
			totalProfit += -(diff >>> 31) * diff + diff;
		}
		return totalProfit;
	}

	private static void check(int[] nums, int expectedMaxProfit) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expectedMaxProfit is: " + expectedMaxProfit);
		int maxProfit = maxProfit(nums); // Calls your implementation
		System.out.println("maxProfit is: " + maxProfit);
	}
}
