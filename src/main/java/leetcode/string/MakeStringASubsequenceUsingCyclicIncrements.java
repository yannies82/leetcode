package leetcode.string;

public class MakeStringASubsequenceUsingCyclicIncrements {

	public static void main(String[] args) {
		check("abc", "ad", true);
		check("zc", "ad", true);
		check("ab", "d", false);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/make-string-a-subsequence-using-cyclic-increments.
	 * This solution tries to greedily match the characters in the two strings. Time
	 * complexity is O(n) where n is the length of str1.
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean canMakeSubsequence(String str1, String str2) {
		char[] str1Chars = str1.toCharArray();
		char[] str2Chars = str2.toCharArray();
		int limit = str1Chars.length - str2Chars.length;
		for (int i = 0; i <= limit; i++) {
			if (str1Chars[i] == str2Chars[0] || getNextChar(str1Chars[i]) == str2Chars[0]) {
				if (str2Chars.length == 1) {
					return true;
				}
				int index = 1;
				for (int j = i + 1; j < str1Chars.length; j++) {
					if (str1Chars[j] == str2Chars[index] || getNextChar(str1Chars[j]) == str2Chars[index]) {
						index++;
					}
					if (index == str2Chars.length) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private static char getNextChar(char c) {
		if (c == 'z') {
			return 'a';
		}
		return (char) (c + 1);
	}

	private static void check(String str1, String str2, boolean expected) {
		System.out.println("str1 is: " + str1);
		System.out.println("str2 is: " + str2);
		System.out.println("expected is: " + expected);
		boolean canMakeSubsequence = canMakeSubsequence(str1, str2); // Calls your implementation
		System.out.println("canMakeSubsequence is: " + canMakeSubsequence);
	}
}
