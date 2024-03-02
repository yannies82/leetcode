package leetcode.hashmap;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {

	public static void main(String[] args) {
		check("anagram", "nagaram", true);
		check("rat", "car", false);
	}

	public static boolean isAnagram(String s, String t) {
		int sLength = s.length();
		if (sLength != t.length()) {
			return false;
		}
		int[] charMap = new int[26];
		for (int i = 0; i < sLength; i++) {
			charMap[s.charAt(i) - 'a']++;
			charMap[t.charAt(i) - 'a']--;
		}
		for (int i = 0; i < 26; i++) {
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
