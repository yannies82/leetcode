package leetcode.array;

import java.util.Arrays;

public class BestSightseeingPair {

	public static void main(String[] args) {
		check(new int[] { 8, 1, 5, 2, 6 }, 11);
		check(new int[] { 1, 2 }, 2);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/best-sightseeing-pair. Time
	 * complexity is O(n) where n is the length of the values array.
	 * 
	 * @param values
	 * @return
	 */
	public static int maxScoreSightseeingPair(int[] values) {
		int maxScore = 0;
		int max = values[0] - 1;
		for (int i = 1; i < values.length; i++) {
			int currentValue = max + values[i];
			if (currentValue > maxScore) {
				maxScore = currentValue;
			}
			if (values[i] > max) {
				max = values[i];
			}
			max--;
		}
		return maxScore;
	}

	/**
	 * Similar solution, slightly simpler logic. Time complexity is O(n) where n is
	 * the length of the values array.
	 * 
	 * @param values
	 * @return
	 */
	public static int maxScoreSightseeingPair2(int[] values) {
		int maxScore = 0;
		int max = values[0];
		for (int i = 1; i < values.length; i++) {
			maxScore = Math.max(maxScore, max + values[i] - i);
			max = Math.max(max, values[i] + i);
		}
		return maxScore;
	}

	private static void check(int[] values, int expected) {
		System.out.println("values is: " + Arrays.toString(values));
		System.out.println("expected is: " + expected);
		int maxScoreSightseeingPair = maxScoreSightseeingPair(values); // Calls your implementation
		System.out.println("maxScoreSightseeingPair is: " + maxScoreSightseeingPair);
	}
}
