package leetcode.string;

import java.util.Arrays;

public class CountTheNumbersOfConsistentStrings {

	public static void main(String[] args) {
		check("ab", new String[] { "ad", "bd", "aaab", "baa", "badab" }, 2);
		check("abc", new String[] { "a", "b", "c", "ab", "ac", "bc", "abc" }, 7);
		check("cad", new String[] { "cc", "acd", "b", "ba", "bac", "bad", "ac", "d" }, 4);

	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/count-the-number-of-consistent-strings. Time
	 * complexity is O(m*n) where m is the length of the words array and n is the
	 * average length of each word.
	 * 
	 * @param allowed
	 * @param words
	 * @return
	 */
	public static int countConsistentStrings(String allowed, String[] words) {
		int count = 0;
		boolean[] exists = new boolean[26];
		int allowedLength = allowed.length();
		for (int i = 0; i < allowedLength; i++) {
			exists[allowed.charAt(i) - 'a'] = true;
		}
		outer: for (int i = 0; i < words.length; i++) {
			int wordLength = words[i].length();
			for (int j = 0; j < wordLength; j++) {
				if (!exists[words[i].charAt(j) - 'a']) {
					continue outer;
				}
			}
			count++;
		}
		return count;
	}

	public static int countConsistentStrings2(String allowed, String[] words) {
		int count = 0;
		boolean[] exists = new boolean[26];
		char[] chars = allowed.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			exists[chars[i] - 'a'] = true;
		}
		outer: for (int i = 0; i < words.length; i++) {
			char[] wordChars = words[i].toCharArray();
			for (int j = 0; j < wordChars.length; j++) {
				if (!exists[wordChars[j] - 'a']) {
					continue outer;
				}
			}
			count++;
		}
		return count;
	}

	private static void check(String allowed, String[] words, int expected) {
		System.out.println("allowed is: " + allowed);
		System.out.println("words is: " + Arrays.toString(words));
		System.out.println("expected is: " + expected);
		int countConsistentStrings = countConsistentStrings(allowed, words); // Calls your implementation
		System.out.println("countConsistentStrings is: " + countConsistentStrings);
	}
}
