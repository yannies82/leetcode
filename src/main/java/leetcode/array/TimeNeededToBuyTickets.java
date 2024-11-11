package leetcode.array;

import java.util.Arrays;

public class TimeNeededToBuyTickets {

	public static void main(String[] args) {
		check(new int[] { 2, 3, 2 }, 2, 6);
		check(new int[] { 5, 1, 1, 1 }, 0, 8);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/time-needed-to-buy-tickets.
	 * Time complexity is O(n) where n is the length of the tickets array.
	 * 
	 * @param tickets
	 * @param k
	 * @return
	 */
	public static int timeRequiredToBuy(int[] tickets, int k) {
		int target = tickets[k];
		int targetMinus1 = target - 1;
		int total = 0;
		// add to the total time for indexes up to k
		for (int i = 0; i <= k; i++) {
			total += Math.min(tickets[i], target);
		}
		// add to the total time for indexes after k
		for (int i = k + 1; i < tickets.length; i++) {
			total += Math.min(tickets[i], targetMinus1);
		}
		return total;
	}

	private static void check(int[] tickets, int k, int expected) {
		System.out.println("tickets is: " + Arrays.toString(tickets));
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		int timeRequiredToBuy = timeRequiredToBuy(tickets, k); // Calls your implementation
		System.out.println("timeRequiredToBuy is: " + timeRequiredToBuy);
	}
}
