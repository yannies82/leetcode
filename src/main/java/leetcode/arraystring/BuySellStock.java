package leetcode.arraystring;

import java.util.Arrays;

public class BuySellStock {

	public static void main(String[] args) {
		check(new int[] { 7, 1, 5, 3, 6, 4 }, 5);
		check(new int[] { 7, 6, 4, 3, 1 }, 0);
	}

	public static int maxProfit(int[] prices) {
		int length = prices.length;
		int minFound = prices[0];
		int maxProfit = 0;
		int profit;
		for (int i = 1; i < length; i++) {
			if (prices[i] < minFound) {
				minFound = prices[i];
			} else {
				profit = prices[i] - minFound;
				if (profit > maxProfit) {
					maxProfit = profit;
				}
			}
		}
		return maxProfit;
	}

	private static void check(int[] nums, int expectedMaxProfit) {
		System.out.println("nums is: " + Arrays.toString(nums));
		System.out.println("expectedMaxProfit is: " + expectedMaxProfit);
		int maxProfit = maxProfit(nums); // Calls your implementation
		System.out.println("maxProfit is: " + maxProfit);
	}
}
