package leetcode.hashmap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IsomorphicStrings {

	public static void main(String[] args) {
		check("badc", "baba", false);
		check("bbbaaaba", "aaabbbba", false);
		check("egg", "add", true);
		check("foo", "bar", false);
		check("paper", "title", true);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/isomorphic-strings. This
	 * solution maps each character of string s to the respective character of
	 * string t and also tracks which characters of string t have already been
	 * mapped. Time complexity is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public static boolean isIsomorphic(String s, String t) {
		int sLength = s.length();
		if (sLength != t.length()) {
			// early exit if the strings have different lengths
			return false;
		}
		// maps characters of string s to characters of string t
		char[] charMap = new char[128];
		// keeps track of string t characters which have been mapped
		boolean[] tCharSet = new boolean[128];
		// iterate all characters
		for (int i = 0; i < sLength; i++) {
			if (charMap[s.charAt(i)] == 0) {
				// the character has not been mapped yet
				if (tCharSet[t.charAt(i)]) {
					// the s character has not been mapped but the t character has been mapped
					return false;
				}
				// map character of string s to string t
				charMap[s.charAt(i)] = t.charAt(i);
				tCharSet[t.charAt(i)] = true;
			} else if (charMap[s.charAt(i)] != t.charAt(i)) {
				// the character of string s has been mapped to a different character of string
				// t
				return false;
			}
		}
		return true;
	}

	public static boolean isIsomorphic2(String s, String t) {
		int sLength = s.length();
		if (sLength != t.length()) {
			return false;
		}
		char[] sCharMap = new char[128];
		char[] tCharMap = new char[128];
		for (int i = 0; i < sLength; i++) {
			if ((sCharMap[s.charAt(i)] == 0 && tCharMap[t.charAt(i)] == 0)) {
				sCharMap[s.charAt(i)] = t.charAt(i);
				tCharMap[t.charAt(i)] = s.charAt(i);
			} else if (sCharMap[s.charAt(i)] != t.charAt(i) || tCharMap[t.charAt(i)] != s.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isIsomorphic3(String s, String t) {
		int sLength = s.length();
		if (sLength != t.length()) {
			return false;
		}
		Map<Character, Character> charMap = new HashMap<>();
		Set<Character> tCharSet = new HashSet<>();
		for (int i = 0; i < sLength; i++) {
			if (charMap.get(s.charAt(i)) == null) {
				if (tCharSet.contains(t.charAt(i))) {
					return false;
				}
				charMap.put(s.charAt(i), t.charAt(i));
				tCharSet.add(t.charAt(i));
			} else if (charMap.get(s.charAt(i)) != t.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	private static void check(String s, String t, boolean expectedIsIsomorphic) {
		System.out.println("s is: " + s);
		System.out.println("t is: " + t);
		System.out.println("expectedIsIsomorphic is: " + expectedIsIsomorphic);
		boolean isIsomorphic = isIsomorphic(s, t); // Calls your implementation
		System.out.println("isIsomorphic is: " + isIsomorphic);
	}
}
