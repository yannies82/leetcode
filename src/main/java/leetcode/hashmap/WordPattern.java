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

	public static boolean wordPattern(String pattern, String s) {
		int sLength = s.length();
		int patternLength = pattern.length();
		if (sLength < patternLength) {
			return false;
		}
		String[] sCharMap = new String[26];
		Set<String> wordSet = new HashSet<>();
		int pCharIndex;
		String word;
		int count = 0;
		int startIndex = 0;
		for (int i = 0; i < sLength; i++) {
			if (i == sLength - 1 || s.charAt(i + 1) == ' ') {
				if (count == patternLength) {
					return false;
				}
				pCharIndex = pattern.charAt(count) - 'a';
				if (sCharMap[pCharIndex] == null) {
					word = s.substring(startIndex, i + 1);
					if (!wordSet.add(word)) {
						return false;
					}
					sCharMap[pCharIndex] = word;
				} else if (!s.substring(startIndex, i + 1).equals(sCharMap[pCharIndex])) {
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
