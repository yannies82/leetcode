package leetcode.string;

import java.util.Arrays;

public class CountingWordsWithAGivenPrefix {

	public static void main(String[] args) {
		check(new String[] { "pay", "attention", "practice", "attend" }, "at", 2);
		check(new String[] { "leetcode", "win", "loops", "success" }, "code", 0);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/counting-words-with-a-given-prefix. Time
	 * complexity is O(m*n) where m is the length of the words array and n is the
	 * length of the pref string.
	 * 
	 * @param words
	 * @param pref
	 * @return
	 */
	public static int prefixCount(String[] words, String pref) {
		int count = 0;
		for (int i = 0; i < words.length; i++) {
			if (words[i].startsWith(pref)) {
				count++;
			}
		}
		return count;
	}

	private static void check(String[] words, String pref, int expected) {
		System.out.println("words is: " + Arrays.toString(words));
		System.out.println("pref is: " + pref);
		System.out.println("expected is: " + expected);
		int prefixCount = prefixCount(words, pref); // Calls your implementation
		System.out.println("prefixCount is: " + prefixCount);
	}
}
