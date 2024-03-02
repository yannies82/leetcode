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

	public static boolean isIsomorphic(String s, String t) {
		int sLength = s.length();
		if (sLength != t.length()) {
			return false;
		}
		char[] charMap = new char[128];
		boolean[] tCharSet = new boolean[128];
		for (int i = 0; i < sLength; i++) {
			if (charMap[s.charAt(i)] == 0) {
				if (tCharSet[t.charAt(i)]) {
					return false;
				}
				charMap[s.charAt(i)] = t.charAt(i);
				tCharSet[t.charAt(i)] = true;
			} else if (charMap[s.charAt(i)] != t.charAt(i)) {
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
