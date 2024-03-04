package leetcode.twopointers;

import java.util.Arrays;

public class BagOfTokens {

	public static void main(String[] args) {
		check(new int[] { 100 }, 50, 0);
		check(new int[] { 200, 100 }, 150, 1);
		check(new int[] { 100, 200, 300, 400 }, 200, 2);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/bag-of-tokens. This solution
	 * sorts the tokens array and then uses two indexes. The start index is used for
	 * converting low power tokens to score and the end index is used for consuming
	 * score to acquire high power tokens. Time complexity is O(nlogn) where n is
	 * the length of the tokens array.
	 * 
	 * @param tokens
	 * @param power
	 * @return
	 */
	public static int bagOfTokensScore(int[] tokens, int power) {
		if (tokens.length == 0) {
			// early exit in case of empty array
			return 0;
		}
		// sort the tokens array
		Arrays.sort(tokens);
		int start = 0;
		int end = tokens.length - 1;
		int score = 0;
		int maxScore = 0;
		// iterate from both sides until start index is greater than end index
		while (start <= end) {
			if (power >= tokens[start]) {
				// we have enough power to convert cheap tokens to score
				power -= tokens[start++];
				score++;
				// update maxScore
				if (score > maxScore) {
					maxScore = score;
				}
			} else if (score > 0) {
				// we don't have enough power to convert cheap tokens to score
				// but we have enough score to consume expensive tokens and turn them to power
				power += tokens[end--];
				score--;
			} else {
				// no action can be performed with our current score and power
				// return maxScore
				return maxScore;
			}
		}
		return maxScore;
	}

	private static void check(int[] tokens, int power, int expected) {
		System.out.println("numbers is: " + Arrays.toString(tokens));
		System.out.println("power is: " + power);
		System.out.println("expected is: " + expected);
		int bagOfTokensScore = bagOfTokensScore(tokens, power); // Calls your implementation
		System.out.println("bagOfTokensScore is: " + bagOfTokensScore);
	}
}
