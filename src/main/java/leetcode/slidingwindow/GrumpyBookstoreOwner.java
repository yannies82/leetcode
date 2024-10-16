package leetcode.slidingwindow;

import java.util.Arrays;

public class GrumpyBookstoreOwner {

	public static void main(String[] args) {
		check(new int[] { 1, 0, 1, 2, 1, 1, 7, 5 }, new int[] { 0, 1, 0, 1, 0, 1, 0, 1 }, 3, 16);
		check(new int[] { 1 }, new int[] { 0 }, 1, 1);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/grumpy-bookstore-owner. This
	 * solution calculates the default happiness of the customers as well as the
	 * diff in happiness for a moving minutes window where the owner will be happy.
	 * Time complexity is O(n) where n is the length of the customers array.
	 * 
	 * @param customers
	 * @param grumpy
	 * @param minutes
	 * @return
	 */
	public static int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
		int windowHappyCustomers = 0;
		int defaultHappyCustomers = 0;
		// iterate customers up to minutes and calculate the default number of happy
		// ones according to the grumpy array values
		// also calculate the ones that would be happy if the owner was happy
		for (int i = 0; i < minutes; i++) {
			defaultHappyCustomers += customers[i] * (1 - grumpy[i]);
			windowHappyCustomers += customers[i] * grumpy[i];
		}
		int maxWindowHappyCustomers = windowHappyCustomers;
		// iterate the rest of the customers and calculate the default number of happy
		// ones according to the grumpy array values
		// also calculate the extra happy customers if the owner's happy window starts
		// at i - minutes and ends at i
		for (int i = minutes; i < customers.length; i++) {
			defaultHappyCustomers += customers[i] * (1 - grumpy[i]);
			int windowStart = i - minutes;
			windowHappyCustomers += customers[i] * grumpy[i] - customers[windowStart] * grumpy[windowStart];
			// update the maxWindowHappyCustomers
			maxWindowHappyCustomers = Math.max(windowHappyCustomers, maxWindowHappyCustomers);
		}
		return defaultHappyCustomers + maxWindowHappyCustomers;
	}

	/**
	 * Alternate solution, single loop. Time complexity is O(n) where n is the
	 * length of the customers array.
	 * 
	 * @param customers
	 * @param grumpy
	 * @param minutes
	 * @return
	 */
	public static int maxSatisfied2(int[] customers, int[] grumpy, int minutes) {
		int maxHappinessDiff = 0;
		int happinessDiff = 0;
		int defaultHappyCustomers = 0;
		// iterate all customers and calculate the default number of happy ones
		// according to the grumpy array values
		// also calculate the diff in happiness if the window in minutes that the owner
		// tries to be happy ends at index i and starts at index i - minutes
		for (int i = 0; i < customers.length; i++) {
			if (grumpy[i] == 0) {
				// the owner is not grumpy, by default the customers will be happy
				defaultHappyCustomers += customers[i];
			} else {
				// the owner is grumpy, the customers will only be happy if
				// they are part of the minutes window where the owner tries
				// to be happy
				happinessDiff += customers[i];
			}
			// check the start of the window
			int windowStart = i - minutes;
			if (windowStart >= 0 && grumpy[windowStart] == 1) {
				// index windowStart is now out of the happy owner window
				// therefore remove the customers from the happiness diff
				happinessDiff -= customers[windowStart];
			}
			// update the maxHappinessDiff
			maxHappinessDiff = Math.max(happinessDiff, maxHappinessDiff);
		}
		return defaultHappyCustomers + maxHappinessDiff;
	}

	private static void check(int[] customers, int[] grumpy, int minutes, int expected) {
		System.out.println("customers is: " + Arrays.toString(customers));
		System.out.println("grumpy is: " + Arrays.toString(grumpy));
		System.out.println("minutes is: " + minutes);
		System.out.println("expected is: " + expected);
		int maxSatisfied = maxSatisfied(customers, grumpy, minutes); // Calls your implementation
		System.out.println("maxSatisfied is: " + maxSatisfied);
	}
}
