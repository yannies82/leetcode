package leetcode.arraystring;

public class MakeTheStringGreat {

	public static void main(String[] args) {
		check("leEeetcode", "leetcode");
		check("abBAcC", "");
		check("s", "s");
		check("CMutaiPrWBbwRpIATUmcoO", "");
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/make-the-string-great. Time
	 * complexity is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static String makeGood(String s) {
		if (s.length() < 2) {
			// early exit in case of string length less than 2
			return s;
		}
		// initialize builder and place an element with a code that is out of range as
		// the first element, this way the builder will never be empty
		StringBuilder builder = new StringBuilder();
		builder.append(" ");
		int lastIndex = s.length() - 1;
		// iterate all string characters
		for (int i = 0; i <= lastIndex; i++) {
			char ith = s.charAt(i);
			int lastResultIndex = builder.length() - 1;
			char previous = builder.charAt(lastResultIndex);
			// compare the current string character with the last character of the result
			// string builder
			if (ith != previous + 32 && ith != previous - 32) {
				// if the character codes do not differ by 32 then they are not the
				// same letter, therefore append the new character to the end of the builder
				builder.append(ith);
			} else {
				// if the character codes differ by 32 they are the same letter
				// do not append new character, also remove the last character from the builder
				builder.deleteCharAt(lastResultIndex);
			}
		}
		// return the result builder except for the first character
		return builder.substring(1);
	}

	private static void check(String s, String expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		String makeGood = makeGood(s); // Calls your implementation
		System.out.println("makeGood is: " + makeGood);
	}
}
