package leetcode.slidingwindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SubstringWithConcatenationAllWords {

	public static void main(String[] args) {
		check("wordgoodgoodgoodbestword", new String[] { "word", "good", "best", "good" }, List.of(8));
		check("barfoothefoobarman", new String[] { "foo", "bar" }, List.of(0, 9));
		check("wordgoodgoodgoodbestword", new String[] { "word", "good", "best", "word" }, List.of());
		check("barfoofoobarthefoobarman", new String[] { "bar", "foo", "the" }, List.of(6, 9, 12));
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/substring-with-concatenation-of-all-words. This
	 * solution finds the word length and the expected substring length and checks
	 * all possible substrings of s with the same length to find if they contain all
	 * of the expected words. Time complexity is O(k * n /k) = O(n) where n is the
	 * length of string s and k is the word length.
	 * 
	 * @param s
	 * @param words
	 * @return
	 */
	public static List<Integer> findSubstring(String s, String[] words) {
		int length = s.length();
		int wordsCount = words.length;
		int wordLength = words[0].length();
		int expectedSize = wordLength * wordsCount;
		if (length < expectedSize) {
			// early exit if the expected size of the substring is greater than the length
			// of string s
			return Collections.emptyList();
		}
		Map<String, Integer> wordsMap = new HashMap<>();
		// add all words to a map, along with their occurences in the words array
		for (String word : words) {
			wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
		}
		List<Integer> result = new ArrayList<>();
		int limit = length - wordLength;
		String[] tokens = new String[length];
		// iterate string s from all possible starting positions (equal to word length)
		for (int i = 0; i < wordLength; i++) {
			Map<String, Integer> tempMap = new HashMap<>();
			int start = i;
			int count = 0;
			// iterate string s with word length increments
			for (int j = i; j <= limit; j += wordLength) {
				tokens[j] = s.substring(j, j + wordLength);
				if (wordsMap.containsKey(tokens[j])) {
					// every time an expected token is found add to the temp map and increase count
					tempMap.put(tokens[j], tempMap.getOrDefault(tokens[j], 0) + 1);
					count++;
					if (count == wordsCount) {
						// if the tokens found are equal to the number of expected words compare
						// the temp map with the expected words map and add the start index to the
						// results
						// if they are equal
						if (tempMap.equals(wordsMap)) {
							result.add(start);
						}
						// slide the window by removing an occurence of the token starting at start
						// index
						// and by increasing the start index by word length
						tempMap.put(tokens[start], tempMap.get(tokens[start]) - 1);
						start += wordLength;
						count--;
					}
				} else {
					// a non expected token was found
					// resume start from next token after resetting temp map and count
					start = j + wordLength;
					count = 0;
					tempMap.clear();
				}
			}
		}
		return result;
	}

	public static List<Integer> findSubstring2(String s, String[] words) {
		int length = s.length();
		int wordsCount = words.length;
		int wordLength = words[0].length();
		int expectedSize = wordLength * wordsCount;
		if (length < expectedSize)
			return Collections.emptyList();
		HashMap<String, Integer> wordsMap = new HashMap<>();
		for (String word : words) {
			wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
		}
		List<Integer> result = new ArrayList<>();
		int limit = length - expectedSize;
		for (int i = 0; i <= limit; i++) {
			Map<String, Integer> tempMap = new HashMap<>();
			for (int j = 0; j < expectedSize; j += wordLength) {
				String word = s.substring(i + j, i + j + wordLength);
				tempMap.put(word, tempMap.getOrDefault(word, 0) + 1);
			}
			if (tempMap.equals(wordsMap)) {
				result.add(i);
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static List<Integer> findSubstring3(String s, String[] words) {
		int length = s.length();
		int wordsCount = words.length;
		int wordLength = words[0].length();
		int expectedSize = wordLength * wordsCount;
		if (length < expectedSize)
			return Collections.emptyList();
		HashMap<String, Integer> wordsMap = new HashMap<>();
		for (String word : words) {
			wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
		}
		List<Integer> result = new ArrayList<>();
		int start = 0;
		int startLimit = length - expectedSize;
		HashMap<String, Integer> tempMap;
		String token = "";
		Integer occurrences;
		boolean valid;
		while (start <= startLimit) {
			while (start <= startLimit && wordsMap.get(token = s.substring(start, start + wordLength)) == null) {
				start++;
			}
			if (start <= startLimit) {
				tempMap = (HashMap<String, Integer>) wordsMap.clone();
				tempMap.put(token, tempMap.get(token) - 1);
				valid = true;
				for (int i = 1; i < wordsCount; i++) {
					token = s.substring(start + i * wordLength, start + (i + 1) * wordLength);
					if ((occurrences = tempMap.get(token)) == null || occurrences == 0) {
						valid = false;
						break;
					} else {
						tempMap.put(token, occurrences - 1);
					}
				}
				if (valid) {
					result.add(start);
				}
				start++;
			}
		}
		return result;
	}

	public static List<Integer> findSubstring4(String s, String[] words) {
		int length = s.length();
		int wordsCount = words.length;
		int wordLength = words[0].length();
		int expectedSize = wordLength * wordsCount;
		if (length < expectedSize)
			return Collections.emptyList();
		Arrays.sort(words);
		Set<String> wordsSet = new HashSet<>();
		for (int i = 0; i < wordsCount; i++) {
			wordsSet.add(words[i]);
		}
		List<Integer> result = new ArrayList<>();
		int start = 0;
		int startLimit = length - expectedSize;
		String[] matchArray = new String[wordsCount];
		String token = "";
		boolean valid;
		while (start <= startLimit) {
			while (start <= startLimit && !wordsSet.contains(token = s.substring(start, start + wordLength))) {
				start++;
			}
			if (start <= startLimit) {
				matchArray[0] = token;
				valid = true;
				for (int i = 1; i < wordsCount && valid; i++) {
					token = s.substring(start + i * wordLength, start + (i + 1) * wordLength);
					if (wordsSet.contains(token)) {
						matchArray[i] = token;
					} else {
						valid = false;
					}
				}
				if (valid) {
					Arrays.sort(matchArray);
					for (int i = 0; i < wordsCount && valid; i++) {
						if (!words[i].equals(matchArray[i])) {
							valid = false;
						}
					}
				}
				if (valid) {
					result.add(start);
				}
				start++;
			}
		}
		return result;
	}

	private static void check(String s, String[] words, List<Integer> expectedPositions) {
		System.out.println("s is: " + s);
		System.out.println("words is: " + Arrays.toString(words));
		System.out.println("expectedPositions is: " + expectedPositions);
		List<Integer> positions = findSubstring(s, words); // Calls your implementation
		System.out.println("positions is: " + positions);
	}
}
