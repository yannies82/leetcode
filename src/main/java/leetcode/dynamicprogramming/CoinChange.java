package leetcode.dynamicprogramming;

import java.util.Arrays;

public class CoinChange {

	public static void main(String[] args) {
		check(new int[] { 2 }, 3, -1);
		check(new int[] { 1 }, 2, 2);
		check(new int[] { 1, 2, 5 }, 11, 3);
		check(new int[] { 1 }, 0, 0);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/coin-change. This solution
	 * uses bottom up dynamic programming to calculate the subproblems from 0 to
	 * amount. Time complexity is O(n * m) where n is the coins array length and m
	 * is the amount as integer.
	 * 
	 * @param coins
	 * @param amount
	 * @return
	 */
	public static int coinChange(int[] coins, int amount) {
		// this array will keep the subproblem solutions
		int[] dpArray = new int[amount + 1];
		// initialize values so that we know which subproblems are not calculated yet
		Arrays.fill(dpArray, -1);
		dpArray[0] = 0;
		// calculate all subproblems from 1 to amount
		for (int i = 1; i < dpArray.length; i++) {
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < coins.length; j++) {
				// for each coin the amount index is calculated by subtracting the coin from the
				// current amount
				int amountIndex = i - coins[j];
				// if the amountIndex is greater than 0 and the subproblem for the amountIndex
				// is calculated, use the value to calculate the min
				if (amountIndex >= 0 && dpArray[amountIndex] > -1) {
					min = Math.min(min, dpArray[amountIndex] + 1);
				}
			}
			if (min != Integer.MAX_VALUE) {
				// set the subproblem solution if it is calculated
				dpArray[i] = min;
			}
		}
		return dpArray[amount];
	}

	/**
	 * This solution uses top down dynamic programming to calculate the required
	 * subproblems from amount to 0. Time complexity is O(n * m) where n is the
	 * coins array length and m is the amount as integer.
	 * 
	 * @param coins
	 * @param amount
	 * @return
	 */
	public static int coinChange2(int[] coins, int amount) {
		// this array will keep the subproblem solutions
		int[] dpArray = new int[amount + 1];
		// initialize values so that we know which subproblems are not calculated yet
		Arrays.fill(dpArray, -1);
		dpArray[0] = 0;
		// solve all required subproblems recursively and return dpArray[amount]
		return dp(coins, amount, dpArray);
	}

	private static int dp(int[] coins, int targetAmount, int[] dpArray) {
		if (targetAmount < 0) {
			return -1;
		}
		if (dpArray[targetAmount] != -1) {
			// return cached value, if it exists
			return dpArray[targetAmount];
		}
		int min = Integer.MAX_VALUE;
		// recursively calculate subproblems for all applicable amounts after
		// subtracting all coins
		for (int i = 0; i < coins.length; i++) {
			int dpValue;
			// calculate subproblem and use its value only if the amountIndex is greater
			// than 0
			if ((dpValue = dp(coins, targetAmount - coins[i], dpArray)) > -1) {
				min = Math.min(min, dpValue);
			}
		}
		if (min != Integer.MAX_VALUE) {
			// set the subproblem solution if it is calculated
			dpArray[targetAmount] = min + 1;
		}
		return dpArray[targetAmount];
	}

	private static void check(int[] coins, int amount, int expected) {
		System.out.println("coins is: " + Arrays.toString(coins));
		System.out.println("amount is: " + amount);
		System.out.println("expected is: " + expected);
		int coinChange = coinChange(coins, amount); // Calls your implementation
		System.out.println("coinChange is: " + coinChange);
	}

}
