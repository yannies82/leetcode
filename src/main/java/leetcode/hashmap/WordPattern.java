package leetcode.hashmap;

import java.util.HashSet;
import java.util.Set;

public class WordPattern {

	public static void main(String[] args) {
		check("jquery", "jquery", false);
		check("aaa", "aa aa aa aa", false);
		check("abba", "dog cat cat dog", true);
		check("abba", "dog cat cat fish", false);
		check("aaaa", "dog cat cat dog", false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/word-pattern. This solution
	 * maps characters of pattern to words of string s and keeps tracks of string s
	 * words that have already been mapped. Time complexity is O(n) where n is the
	 * length of string s.
	 * 
	 * 
	 * @param pattern
	 * @param s
	 * @return
	 */
	public static boolean wordPattern(String pattern, String s) {
		int sLength = s.length();
		int patternLength = pattern.length();
		if (sLength < patternLength) {
			// early exit if the length of s is less than the length of pattern
			return false;
		}
		// maps characters of pattern to words of string s
		String[] sCharMap = new String[26];
		// keeps track of s words that have already been mapped to string pattern
		// characters
		Set<String> wordSet = new HashSet<>();
		int pCharIndex;
		String word;
		int count = 0;
		int startIndex = 0;
		// iterate all characters of string s
		for (int i = 0; i < sLength; i++) {
			if (i == sLength - 1 || s.charAt(i + 1) == ' ') {
				// we have reached the end of a word in string s
				if (count == patternLength) {
					// s contains more words than the number of characters in pattern
					return false;
				}
				pCharIndex = pattern.charAt(count) - 'a';
				if (sCharMap[pCharIndex] == null) {
					// pattern character has not been mapped to string s word
					word = s.substring(startIndex, i + 1);
					if (!wordSet.add(word)) {
						// string s word has already been mapped by another character of pattern
						return false;
					}
					sCharMap[pCharIndex] = word;
				} else if (!s.substring(startIndex, i + 1).equals(sCharMap[pCharIndex])) {
					// pattern character has been mapped to a different word of string s
					return false;
				}
				startIndex = i + 2;
				count++;
			}
		}
		return count == patternLength;
	}

	private static void check(String pattern, String s, boolean expectedWordPattern) {
		System.out.println("pattern is: " + pattern);
		System.out.println("s is: " + s);
		System.out.println("expectedWordPattern is: " + expectedWordPattern);
		boolean wordPattern = wordPattern(pattern, s); // Calls your implementation
		System.out.println("wordPattern is: " + wordPattern);
	}
}
