package leetcode.string;

import java.util.Arrays;

public class CountPrefixAndSuffixPairs {

	public static void main(String[] args) {
		check(new String[] { "a", "aba", "ababa", "aa" }, 4);
		check(new String[] { "pa", "papa", "ma", "mama" }, 2);
		check(new String[] { "abab", "ab" }, 0);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/count-prefix-and-suffix-pairs-i. Time
	 * complexity is O(n^2) where n is the length of the words array.
	 * 
	 * @param words
	 * @return
	 */
	public static int countPrefixSuffixPairs(String[] words) {
		int result = 0;
		int lastIndex = words.length - 1;
		for (int i = 0; i < lastIndex; i++) {
			for (int j = i + 1; j < words.length; j++) {
				if (isPrefixAndSuffix(words[i], words[j])) {
					result++;
				}
			}
		}
		return result;
	}

	private static boolean isPrefixAndSuffix(String str1, String str2) {
		return str2.startsWith(str1) && str2.endsWith(str1);
	}

	private static void check(String[] words, int expected) {
		System.out.println("words is: " + Arrays.toString(words));
		System.out.println("expected is: " + expected);
		int countPrefixSuffixPairs = countPrefixSuffixPairs(words); // Calls your implementation
		System.out.println("countPrefixSuffixPairs is: " + countPrefixSuffixPairs);
	}
}
