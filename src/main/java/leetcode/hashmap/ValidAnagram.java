package leetcode.hashmap;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {

	public static void main(String[] args) {
		check("anagram", "nagaram", true);
		check("rat", "car", false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/valid-anagram. This solution
	 * keeps the count of each character is string s and checks if the count of
	 * characters in string t is exactly the same. Time complexity is O(n) where n
	 * is the length of string s.
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public static boolean isAnagram(String s, String t) {
		int sLength = s.length();
		if (sLength != t.length()) {
			// early exit if length of string s is different from length of string t
			return false;
		}
		// keeps count of characters
		int[] charMap = new int[26];
		// iterate all characters
		for (int i = 0; i < sLength; i++) {
			// add to count for characters of string s, subtract for characters of string t
			charMap[s.charAt(i) - 'a']++;
			charMap[t.charAt(i) - 'a']--;
		}
		for (int i = 0; i < 26; i++) {
			// check that count is 0 for all characters, which would mean that s has the
			// same characters as t
			if (charMap[i] != 0) {
				return false;
			}
		}
		return true;
	}

	public static boolean isAnagram2(String s, String t) {
		int sLength = s.length();
		if (sLength != t.length()) {
			return false;
		}
		int[] charMap = new int[26];
		for (int i = 0; i < sLength; i++) {
			charMap[s.charAt(i) - 'a']++;
		}
		for (int i = 0; i < sLength; i++) {
			if (--charMap[t.charAt(i) - 'a'] < 0) {
				return false;
			}
		}
		return true;
	}

	public static boolean isAnagram3(String s, String t) {
		int sLength = s.length();
		if (sLength != t.length()) {
			return false;
		}
		Map<Character, Integer> charMap = new HashMap<>();
		for (int i = 0; i < sLength; i++) {
			charMap.put(s.charAt(i), charMap.getOrDefault(s.charAt(i), 0) + 1);
			charMap.put(t.charAt(i), charMap.getOrDefault(t.charAt(i), 0) - 1);
		}
		for (Character ch : charMap.keySet()) {
			if (charMap.get(ch) != 0) {
				return false;
			}
		}
		return true;
	}

	private static void check(String s, String t, boolean expectedValidAnagram) {
		System.out.println("s is: " + s);
		System.out.println("t is: " + t);
		System.out.println("expectedValidAnagram is: " + expectedValidAnagram);
		boolean isAnagram = isAnagram(s, t); // Calls your implementation
		System.out.println("isAnagram is: " + isAnagram);
	}
}
