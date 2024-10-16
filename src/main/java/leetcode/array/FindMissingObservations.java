package leetcode.array;

import java.util.Arrays;

public class FindMissingObservations {

	public static void main(String[] args) {
		check(new int[] { 3, 2, 4, 3 }, 4, 2, new int[] { 6, 6 });
		check(new int[] { 1, 5, 6 }, 3, 4, new int[] { 3, 2, 2, 2 });
		check(new int[] { 1, 2, 3, 4 }, 6, 4, new int[] {});
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/find-missing-observations.
	 * Time complexity is O(m+n) where m is the length of the rolls array.
	 * 
	 * @param rolls
	 * @param mean
	 * @param n
	 * @return
	 */
	public static int[] missingRolls(int[] rolls, int mean, int n) {
		// calculate the sum of all rolls
		int sum = 0;
		for (int i = 0; i < rolls.length; i++) {
			sum += rolls[i];
		}
		// calculate the expected total sum based on the total count and mean
		int totalCount = rolls.length + n;
		int totalSum = totalCount * mean;
		// calculate the difference between the expected total sum and the actual
		// one from the m rolls
		int diff = totalSum - sum;
		if (diff < n || diff > n * 6) {
			// since each missing roll can be from 1 to 6 return empty array if it
			// is not possible for a solution to exist
			return new int[] {};
		}
		// calculate the expected mean for the missing n rolls
		int meanForN = diff / n;
		int meanForNPlus1 = meanForN + 1;
		// calculate the remaining diff to be added to some of the rolls
		int remainingDiff = diff % n;
		int[] result = new int[n];
		for (int i = 0; i < remainingDiff; i++) {
			result[i] = meanForNPlus1;
		}
		for (int i = remainingDiff; i < n; i++) {
			result[i] = meanForN;
		}
		return result;
	}

	private static void check(int[] rolls, int mean, int n, int[] expected) {
		System.out.println("rolls is: " + Arrays.toString(rolls));
		System.out.println("mean is: " + mean);
		System.out.println("n is: " + n);
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] missingRolls = missingRolls(rolls, mean, n); // Calls your implementation
		System.out.println("missingRolls is: " + Arrays.toString(missingRolls));
	}
}
