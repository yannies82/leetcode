package leetcode.array;

import java.util.Arrays;

public class FinalPricesWithASpecialDiscountInAShop {

	public static void main(String[] args) {
		check(new int[] { 8, 4, 6, 2, 3 }, new int[] { 4, 2, 4, 2, 3 });
		check(new int[] { 1, 2, 3, 4, 5 }, new int[] { 1, 2, 3, 4, 5 });
		check(new int[] { 10, 1, 1, 6 }, new int[] { 9, 0, 1, 6 });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop.
	 * Time complexity is O(n^2) where n is the length of the prices array.
	 * 
	 * @param prices
	 * @return
	 */
	public static int[] finalPrices(int[] prices) {
		for (int i = 0; i < prices.length; i++) {
			for (int j = i + 1; j < prices.length; j++) {
				if (prices[j] <= prices[i]) {
					prices[i] -= prices[j];
					break;
				}
			}
		}
		return prices;
	}

	private static void check(int[] prices, int[] expected) {
		System.out.println("prices is: " + Arrays.toString(prices));
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] finalPrices = finalPrices(prices); // Calls your implementation
		System.out.println("finalPrices is: " + Arrays.toString(finalPrices));
	}
}
