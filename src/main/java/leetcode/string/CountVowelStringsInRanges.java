package leetcode.string;

import java.util.Arrays;

public class CountVowelStringsInRanges {

	public static void main(String[] args) {
		check(new String[] { "aba", "bcb", "ece", "aa", "e" }, new int[][] { { 0, 2 }, { 1, 4 }, { 1, 1 } },
				new int[] { 2, 3, 0 });
		check(new String[] { "a", "e", "i" }, new int[][] { { 0, 2 }, { 0, 1 }, { 2, 2 } }, new int[] { 3, 2, 1 });
	}

	private static final byte[] VOWELS_MAP = initializeVowelsMap();

	private static byte[] initializeVowelsMap() {
		byte[] result = new byte[128];
		result[97] = result[101] = result[105] = result[111] = result[117] = 1;
		return result;
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/count-vowel-strings-in-ranges. Branchless
	 * solution. Time complexity is O(m+n) where m is the length of the words array
	 * and n is the length of the queries array.
	 * 
	 * @param words
	 * @param queries
	 * @return
	 */
	public static int[] vowelStrings(String[] words, int[][] queries) {
		int[] eligibleWords = new int[words.length + 1];
		for (int i = 0; i < words.length; i++) {
			eligibleWords[i + 1] = eligibleWords[i] + calculateEligible(words[i]);
		}
		int[] result = new int[queries.length];
		for (int i = 0; i < queries.length; i++) {
			result[i] = eligibleWords[queries[i][1] + 1] - eligibleWords[queries[i][0]];
		}
		return result;
	}

	private static int calculateEligible(String word) {
		return VOWELS_MAP[word.charAt(0)] & VOWELS_MAP[word.charAt(word.length() - 1)];
	}

	/**
	 * Alternative solution. Time complexity is O(m+n) where m is the length of the
	 * words array and n is the length of the queries array.
	 * 
	 * @param words
	 * @param queries
	 * @return
	 */
	public static int[] vowelStrings2(String[] words, int[][] queries) {
		int[] eligibleWords = new int[words.length + 1];
		for (int i = 0; i < words.length; i++) {
			if (isEligible(words[i])) {
				eligibleWords[i + 1] = eligibleWords[i] + 1;
			} else {
				eligibleWords[i + 1] = eligibleWords[i];
			}
		}
		int[] result = new int[queries.length];
		for (int i = 0; i < queries.length; i++) {
			result[i] = eligibleWords[queries[i][1] + 1] - eligibleWords[queries[i][0]];
		}
		return result;
	}

	private static boolean isEligible(String word) {
		return isVowel(word.charAt(0)) && isVowel(word.charAt(word.length() - 1));
	}

	private static boolean isVowel(char ch) {
		return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
	}

	private static void check(String[] words, int[][] queries, int[] expected) {
		System.out.println("words is: " + Arrays.toString(words));
		System.out.println("queries is: ");
		for (int[] query : queries) {
			System.out.println(Arrays.toString(query));
		}
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] vowelStrings = vowelStrings(words, queries); // Calls your implementation
		System.out.println("vowelStrings is: " + Arrays.toString(vowelStrings));
	}
}
