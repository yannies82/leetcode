package leetcode.multidimensionaldynamicprogramming;

import java.util.Arrays;

public class StrangePrinter {

	public static void main(String[] args) {
		check("tbgtgb", 4);
		check("tbgtbg", 5);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/strange-printer. This
	 * solution uses top down dynamic programming to find the result. Time
	 * complexity is O(n^3) where n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int strangePrinter(String s) {
		int n = s.length();
		char[] chars = s.toCharArray();
		int[][] dpArray = new int[chars.length][chars.length];
		for (int i = 0; i < dpArray.length; i++) {
			Arrays.fill(dpArray[i], -1);
		}
		return dp(0, n - 1, chars, dpArray);
	}

	public static int dp(int start, int end, char[] chars, int[][] dpArray) {
		if (start > end) {
			// early exit
			return 0;
		}
		if (dpArray[start][end] != -1) {
			// solution for this subproblem has already been calculated
			return dpArray[start][end];
		}

		int firstLetter = chars[start];
		// best solution in case that the first character is not repeated in the rest of
		// the string
		int answer = 1 + dp(start + 1, end, chars, dpArray);
		for (int k = start + 1; k <= end; k++) {
			// there will be better solutions if the first character is repeated, check them
			if (chars[k] == firstLetter) {
				// splitting from i -> k - 1(remove the last character)
				// and from k + 1 -> j
				int betterAnswer = dp(start, k - 1, chars, dpArray) + dp(k + 1, end, chars, dpArray);
				answer = Math.min(answer, betterAnswer);
			}
		}
		return dpArray[start][end] = answer;
	}

	private static void check(String s, int expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		int strangePrinter = strangePrinter(s); // Calls your implementation
		System.out.println("strangePrinter is: " + strangePrinter);
	}
}
