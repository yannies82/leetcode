package leetcode.string.frequency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordSubsets {

	public static void main(String[] args) {
		check(new String[] { "amazon", "apple", "facebook", "google", "leetcode" }, new String[] { "e", "o" },
				List.of("facebook", "google", "leetcode"));
		check(new String[] { "amazon", "apple", "facebook", "google", "leetcode" }, new String[] { "l", "e" },
				List.of("apple", "google", "leetcode"));
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/word-subsets. This solution
	 * calculates the max frequency of each character in the words2 strings and
	 * checks that words1 strings have at least as many characters as the max
	 * frequency. Time complexity is O(m*k+n*l) where m is the length of the words1
	 * array, k is the average length of the words1 strings, n is the length of the
	 * words2 array and l is the average length of the words2 strings.
	 * 
	 * @param words1
	 * @param words2
	 * @return
	 */
	public static List<String> wordSubsets(String[] words1, String[] words2) {
		int[] maxCharsFrequency = new int[26];
		for (int i = 0; i < words2.length; i++) {
			int[] frequency = new int[26];
			int length = words2[i].length();
			for (int j = 0; j < length; j++) {
				int index = words2[i].charAt(j) - 'a';
				frequency[index]++;
				maxCharsFrequency[index] = Math.max(maxCharsFrequency[index], frequency[index]);
			}
		}
		List<String> result = new ArrayList<>();
		for (int i = 0; i < words1.length; i++) {
			int[] frequency = new int[26];
			int length = words1[i].length();
			for (int j = 0; j < length; j++) {
				frequency[words1[i].charAt(j) - 'a']++;
			}
			boolean isUniversal = true;
			for (int j = 0; j < 26; j++) {
				if (frequency[j] < maxCharsFrequency[j]) {
					isUniversal = false;
					break;
				}
			}
			if (isUniversal) {
				result.add(words1[i]);
			}
		}
		return result;
	}

	private static void check(String[] words1, String[] words2, List<String> expected) {
		System.out.println("words1 is: " + Arrays.toString(words1));
		System.out.println("words2 is: " + Arrays.toString(words2));
		System.out.println("expected is: " + expected);
		List<String> wordSubsets = wordSubsets(words1, words2); // Calls your implementation
		System.out.println("wordSubsets is: " + wordSubsets);
	}
}
