package leetcode.dynamicprogramming;

import java.util.Arrays;

public class MinimumCostForTickets {

	public static void main(String[] args) {
		check(new int[] { 1, 4, 6, 7, 8, 20 }, new int[] { 2, 7, 15 }, 11);
		check(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31 }, new int[] { 2, 7, 15 }, 17);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/minimum-cost-for-tickets.
	 * This solution uses top down dynamic programming to calculate the result. Time
	 * complexity is O(n + m) where n is the length of the days array and m is the
	 * last day in the days array.
	 * 
	 * @param days
	 * @param costs
	 * @return
	 */
	public static int mincostTickets(int[] days, int[] costs) {
		int lastDay = days[days.length - 1];
		// keeps the solution for i days
		int[] dpArray = new int[lastDay + 1];
		// marks the days on which travels occur
		boolean[] travels = new boolean[lastDay + 1];
		for (int i = 0; i < days.length; i++) {
			travels[days[i]] = true;
		}
		// recursively populate dpArray
		dp(days, costs, travels, dpArray, lastDay);
		return dpArray[lastDay];
	}

	private static int dp(int[] days, int[] costs, boolean[] travels, int[] dpArray, int day) {
		if (day < days[0]) {
			return 0;
		}
		if (dpArray[day] > 0) {
			// value has already been calculated, return it
			return dpArray[day];
		}
		if (!travels[day]) {
			// travel does not occur on this day, return value for previous day
			return dp(days, costs, travels, dpArray, day - 1);
		}
		// return min value between using a 1 day, 7 days or 30 days ticket
		int using1Day = dp(days, costs, travels, dpArray, day - 1) + costs[0];
		int using7Days = dp(days, costs, travels, dpArray, day - 7) + costs[1];
		int using30Days = dp(days, costs, travels, dpArray, day - 30) + costs[2];
		dpArray[day] = Math.min(Math.min(using1Day, using7Days), using30Days);
		return dpArray[day];
	}

	/**
	 * This solution uses bottom up dynamic programming to calculate the result.
	 * Time complexity is O(n + m) where n is the length of the days array and m is
	 * the last day in the days array.
	 * 
	 * @param days
	 * @param costs
	 * @return
	 */
	public static int mincostTickets2(int[] days, int[] costs) {
		int lastDay = days[days.length - 1];
		int[] dpArray = new int[lastDay + 1];
		int dpArrayIndex = 0;
		for (int i = 0; i < days.length; i++) {
			int initialDpArrayIndex = dpArrayIndex;
			while (dpArrayIndex < days[i]) {
				// update dpArray values while no travel occurs
				dpArray[++dpArrayIndex] = dpArray[initialDpArrayIndex];
			}
			// return min value between using a 1 day, 7 days or 30 days ticket
			int using1Day = dpArray[dpArrayIndex - 1] + costs[0];
			int using7Days = dpArray[Math.max(dpArrayIndex - 7, 0)] + costs[1];
			int using30Days = dpArray[Math.max(dpArrayIndex - 30, 0)] + costs[2];
			dpArray[dpArrayIndex] = Math.min(Math.min(using1Day, using7Days), using30Days);
		}
		return dpArray[lastDay];
	}

	/**
	 * This solution uses bottom up dynamic programming to calculate the result,
	 * slightly different logic than the previous one. Time complexity is O(n + m)
	 * where n is the length of the days array and m is the last day in the days
	 * array.
	 * 
	 * @param days
	 * @param costs
	 * @return
	 */
	public static int mincostTickets3(int[] days, int[] costs) {
		int lastDay = days[days.length - 1];
		int[] dpArray = new int[lastDay + 1];
		int daysIndex = 0;
		for (int i = 1; i < dpArray.length; i++) {
			if (i == days[daysIndex]) {
				// travel occurs on this day
				// return min value between using a 1 day, 7 days or 30 days ticket
				int using1Day = dpArray[i - 1] + costs[0];
				int using7Days = dpArray[Math.max(i - 7, 0)] + costs[1];
				int using30Days = dpArray[Math.max(i - 30, 0)] + costs[2];
				dpArray[i] = Math.min(Math.min(using1Day, using7Days), using30Days);
				daysIndex++;
			} else {
				// no travel occurs on this day
				dpArray[i] = dpArray[i - 1];
			}
		}
		return dpArray[lastDay];
	}

	private static void check(int[] days, int[] costs, int expected) {
		System.out.println("days is: " + Arrays.toString(days));
		System.out.println("costs is: " + Arrays.toString(costs));
		System.out.println("expected is: " + expected);
		int mincostTickets = mincostTickets(days, costs); // Calls your implementation
		System.out.println("mincostTickets is: " + mincostTickets);
	}
}
