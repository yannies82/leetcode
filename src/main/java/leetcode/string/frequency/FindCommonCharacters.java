package leetcode.string.frequency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindCommonCharacters {

	public static void main(String[] args) {
		check(new String[] { "bella", "label", "roller" }, List.of("e", "l", "l"));
		check(new String[] { "cool", "lock", "cook" }, List.of("c", "o"));
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/find-common-characters. Time
	 * complexity is O(n*m) where n is the length of each word and m is the number
	 * of words.
	 * 
	 * @param s
	 */
	public static List<String> commonChars(String[] words) {
		// initialize result chars array to max values
		int[] finalChars = new int[26];
		Arrays.fill(finalChars, Integer.MAX_VALUE);

		// iterate all words
		for (int i = 0; i < words.length; i++) {
			// calculate the frequency of each letter in each word
			int[] frequency = new int[26];
			char[] chars = words[i].toCharArray();
			for (int j = 0; j < chars.length; j++) {
				frequency[chars[j] - 'a']++;
			}
			// update the finalChars array with the min frequency
			// of each character
			for (int j = 0; j < finalChars.length; j++) {
				finalChars[j] = Math.min(finalChars[j], frequency[j]);
			}
		}
		// convert the finalChars array to a list of strings
		List<String> result = new ArrayList<>();
		for (int i = 0; i < finalChars.length; i++) {
			String sChar = Character.toString((char) ('a' + i));
			while (finalChars[i]-- > 0) {
				result.add(sChar);
			}
		}
		return result;
	}

	private static void check(String[] words, List<String> expected) {
		System.out.println("words is: " + Arrays.toString(words));
		System.out.println("expected is: " + expected);
		List<String> commonChars = commonChars(words); // Calls your implementation
		System.out.println("commonChars is: " + commonChars);
	}
}
