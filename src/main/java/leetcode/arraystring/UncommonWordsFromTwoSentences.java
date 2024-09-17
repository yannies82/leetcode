package leetcode.arraystring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UncommonWordsFromTwoSentences {

	public static void main(String[] args) {
		check("this apple is sweet", "this apple is sour", new String[] { "sweet", "sour" });
		check("apple apple", "banana", new String[] { "banana" });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/uncommon-words-from-two-sentences. This
	 * solution keeps count of each word from both strings and returns only the
	 * words which appear once. Time complexity is O(m+n) where m is the length of
	 * string s1 and n is the length of string s2.
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static String[] uncommonFromSentences(String s1, String s2) {
		Map<String, Integer> occurrencesMap = new HashMap<>();
		addOccurrences(occurrencesMap, s1);
		addOccurrences(occurrencesMap, s2);
		List<String> resultList = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : occurrencesMap.entrySet()) {
			if (entry.getValue() == 1) {
				resultList.add(entry.getKey());
			}
		}
		return resultList.toArray(new String[0]);
	}

	private static void addOccurrences(Map<String, Integer> occurrencesMap, String s) {
		StringBuilder builder = new StringBuilder();
		int sLength = s.length();
		for (int i = 0; i < sLength; i++) {
			char currentChar = s.charAt(i);
			if (currentChar == ' ') {
				addWord(occurrencesMap, builder.toString());
				builder.setLength(0);
			} else {
				builder.append(currentChar);
			}
		}
		// add last word
		addWord(occurrencesMap, builder.toString());
	}

	private static void addWord(Map<String, Integer> occurrencesMap, String word) {
		Integer occurrences = occurrencesMap.get(word);
		if (occurrences == null) {
			occurrencesMap.put(word, 1);
		} else {
			occurrencesMap.put(word, occurrences + 1);
		}
	}

	private static void check(String s1, String s2, String[] expected) {
		System.out.println("s1 is: " + s1);
		System.out.println("s2 is: " + s2);
		System.out.println("expected is: " + Arrays.toString(expected));
		String[] uncommonFromSentences = uncommonFromSentences(s1, s2); // Calls your implementation
		System.out.println("uncommonFromSentences is: " + Arrays.toString(uncommonFromSentences));
	}
}
