package leetcode.arraystring;

public class AppendCharactersToStringToMakeSubsequence {

	public static void main(String[] args) {
		check("coaching", "coding", 4);
		check("abcde", "a", 0);
		check("z", "abcde", 5);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/append-characters-to-string-to-make-subsequence.
	 * Time complexity is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public static int appendCharacters(String s, String t) {
		int sLength = s.length();
		int tLength = t.length();
		int tIndex = 0;
		char tChar = t.charAt(tIndex);
		for (int i = 0; i < sLength; i++) {
			if (s.charAt(i) == tChar) {
				tIndex++;
				if (tIndex == tLength) {
					return 0;
				}
				tChar = t.charAt(tIndex);
			}
		}
		return tLength - tIndex;
	}

	public static int appendCharacters2(String s, String t) {
		char[] sChars = s.toCharArray();
		char[] tChars = t.toCharArray();
		int tIndex = 0;
		for (int i = 0; i < sChars.length && tIndex < tChars.length; i++) {
			if (sChars[i] == tChars[tIndex]) {
				tIndex++;
			}
		}
		return tChars.length - tIndex;
	}

	private static void check(String s, String t, int expected) {
		System.out.println("s is: " + s);
		System.out.println("t is: " + t);
		System.out.println("expected is: " + expected);
		int appendCharacters = appendCharacters(s, t); // Calls your implementation
		System.out.println("appendCharacters is: " + appendCharacters);
	}
}
