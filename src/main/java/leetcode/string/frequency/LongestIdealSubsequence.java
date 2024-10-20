package leetcode.string.frequency;

import java.util.Arrays;

public class LongestIdealSubsequence {

	public static void main(String[] args) {
		check("acfgbd", 2, 4);
		check("pvjcci", 4, 2);
		check("ab", 2, 2);
		check("abcd", 3, 4);
	}

	/**
	 * This solution keeps an array with the counts for all lowercase english
	 * characters and updates it for every new character. Time complexity is O(n *
	 * k) where n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int longestIdealString(String s, int k) {
		char[] chars = s.toCharArray();
		// keeps the char count for every character encountered in the string
		int[] charCount = new int[26];
		// iterate all characters
		for (int i = 0; i < chars.length; i++) {
			int index = chars[i] - 'a';
			int start = Math.max(index - k, 0);
			int end = Math.min(index + k, 25);
			// iterate all characters from k positions before till k positions
			// after the current character and find the one with the highest count
			int maxCount = charCount[start];
			for (int j = start + 1; j <= end; j++) {
				if (charCount[j] > maxCount) {
					maxCount = charCount[j];
				}
			}
			// the count for the current character will be maxCount + 1
			charCount[index] = maxCount + 1;
		}
		// find and return the max of the char counts
		int max = charCount[0];
		for (int i = 1; i < charCount.length; i++) {
			if (max < charCount[i]) {
				max = charCount[i];
			}
		}
		return max;
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/longest-ideal-subsequence.
	 * This solution uses bottom up dynamic programming, trying to find the largest
	 * ideal subsequence up to index i. Time complexity is O(n^2).
	 * 
	 * @param s
	 * @return
	 */
	public static int longestIdealString3(String s, int k) {
		char[] chars = s.toCharArray();
		int[] dpArray = new int[chars.length];
		Arrays.fill(dpArray, 1);
		for (int i = 1; i < chars.length; i++) {
			for (int j = 0; j < i; j++) {
				if (Math.abs(chars[i] - chars[j]) <= k && dpArray[i] < dpArray[j] + 1) {
					dpArray[i] = dpArray[j] + 1;
				}
			}
		}
		int max = dpArray[0];
		for (int i = 1; i < dpArray.length; i++) {
			if (max < dpArray[i]) {
				max = dpArray[i];
			}
		}
		return max;
	}

	private static void check(String s, int k, int expected) {
		System.out.println("s is: " + s);
		System.out.println("k is: " + k);
		System.out.println("expected is: " + expected);
		int longestIdealString = longestIdealString(s, k); // Calls your implementation
		System.out.println("longestIdealString is: " + longestIdealString);
	}
}
