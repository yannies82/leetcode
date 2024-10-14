package leetcode.intervals;

import java.util.Arrays;

public class AverageWaitingTime {

	public static void main(String[] args) {
		check(new int[][] { { 1, 2 }, { 2, 5 }, { 4, 3 } }, 5);
		check(new int[][] { { 5, 2 }, { 5, 4 }, { 10, 3 }, { 20, 1 } }, 3.25d);

	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/average-waiting-time. This
	 * solution iterates the customer array and greedily calculates the
	 * nextAvailableTime and the totalWaitingTime. Time complexity is O(n) where n
	 * is the length of the customers array.
	 * 
	 * @param customers
	 * @return
	 */
	public static double averageWaitingTime(int[][] customers) {
		double totalWaitingTime = 0;
		int nextAvailableTime = 0;
		for (int i = 0; i < customers.length; i++) {
			nextAvailableTime = Math.max(customers[i][0], nextAvailableTime) + customers[i][1];
			totalWaitingTime += nextAvailableTime - customers[i][0];
		}
		return totalWaitingTime / customers.length;
	}

	private static void check(int[][] customers, double expected) {
		System.out.println("customers is: ");
		for (int[] customer : customers) {
			System.out.println(Arrays.toString(customer));
		}
		System.out.println("expected is: " + expected);
		double averageWaitingTime = averageWaitingTime(customers); // Calls your implementation
		System.out.println("averageWaitingTime is: " + averageWaitingTime);
	}
}
