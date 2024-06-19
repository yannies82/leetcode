package leetcode.binarysearch;

import java.util.Arrays;

public class MinimumNumberOfDaysToMakeMBuquets {

	public static void main(String[] args) {
		check(new int[] { 5, 37, 55, 92, 22, 52, 31, 62, 99, 64, 92, 53, 34, 84, 93, 50, 28 }, 8, 2, 93);
		check(new int[] { 1, 10, 3, 10, 2 }, 3, 1, 3);
		check(new int[] { 1, 10, 3, 10, 2 }, 3, 2, -1);
		check(new int[] { 7, 7, 7, 7, 12, 7, 7 }, 2, 3, 12);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets. This
	 * solution uses binary search to find the min number of days. Time complexity
	 * is O(nlogn) where n is the length of the bloomDay array.
	 * 
	 * @param bloomDay
	 * @param m
	 * @param k
	 * @return
	 */
	public static int minDays(int[] bloomDay, int m, int k) {
		if (m * k > bloomDay.length) {
			return -1;
		}
		int low = 1;
		// find the upper limit of the answer, i.e. the largest value in the bloomDay
		// array
		int high = -1;
		for (int i = 0; i < bloomDay.length; i++) {
			high = Math.max(high, bloomDay[i]);
		}
		int result = -1;
		// perform binary search to find the min num of days for which
		// at least m bouquets can be made with the blossomed flowers
		while (low <= high) {
			int mid = (low + high) / 2;
			int numOfBouquets = 0;
			int adjacentFlowers = 0;
			for (int i = 0; i < bloomDay.length && numOfBouquets < m; i++) {
				if (bloomDay[i] <= mid) {
					adjacentFlowers++;
					if (adjacentFlowers == k) {
						numOfBouquets++;
						adjacentFlowers = 0;
					}
				} else {
					adjacentFlowers = 0;
				}
			}
			if (numOfBouquets >= m) {
				result = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return result;
	}

	private static void check(int[] bloomDay, int m, int k, int expected) {
		System.out.println("ratings is: " + Arrays.toString(bloomDay));
		System.out.println("m is: " + m);
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		int minDays = minDays(bloomDay, m, k); // Calls your implementation
		System.out.println("minDays is: " + minDays);
	}
}
