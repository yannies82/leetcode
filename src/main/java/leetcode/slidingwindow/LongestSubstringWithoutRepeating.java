package leetcode.slidingwindow;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeating {

	public static void main(String[] args) {
		check("dvdf", 3);
		check("abcabcbb", 3);
		check("bbbbb", 1);
		check("pwwkew", 3);
	}

	public static int lengthOfLongestSubstring(String s) {
		int length = s.length();
		if (length == 0)
			return 0;
		if (length == 1)
			return 1;
		int start = 0;
		int end = 0;
		int maxLength = 0;
		boolean[] found = new boolean[256];
		while (end < length) {
			while (end < length && !found[s.charAt(end)]) {
				found[s.charAt(end++)] = true;
			}
			if (end - start > maxLength) {
				maxLength = end - start;
			}
			while (end < length && s.charAt(start) != s.charAt(end)) {
				found[s.charAt(start++)] = false;
			}
			found[s.charAt(start++)] = false;
		}
		return maxLength == 0 ? length : maxLength;
	}

	public static int lengthOfLongestSubstring2(String s) {
		int length = s.length();
		if (length == 0)
			return 0;
		if (length == 1)
			return 1;
		int start = 0;
		int end = 0;
		int maxLength = 0;
		Set<Character> charSet = new HashSet<>();
		while (end < length) {
			while (end < length && !charSet.contains(s.charAt(end))) {
				charSet.add(s.charAt(end++));
			}
			if (end - start > maxLength) {
				maxLength = end - start;
			}
			while (end < length && s.charAt(start) != s.charAt(end)) {
				charSet.remove(s.charAt(start++));
			}
			charSet.remove(s.charAt(start++));
		}
		return maxLength == 0 ? length : maxLength;
	}

	private static void check(String s, int expectedLength) {
		System.out.println("s is: " + s);
		System.out.println("expectedLength is: " + expectedLength);
		int length = lengthOfLongestSubstring(s); // Calls your implementation
		System.out.println("length is: " + length);
	}
}
