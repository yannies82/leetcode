package leetcode.arraystring;

public class ReverseWordsInString {

	public static void main(String[] args) {
		check("the sky is blue", "blue is sky the");
		check("  hello world  ", "world hello");
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/reverse-words-in-a-string.
	 * This solution traverses the string from end to start and writes all
	 * encountered words to the result builder. Time complexity is O(n) where n is
	 * the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static String reverseWords(String s) {
		int length = s.length();
		StringBuilder result = new StringBuilder();
		int wordEndIndex = -1;
		// iterate all characters starting from the last one
		for (int i = length - 1; i >= 0; i--) {
			if (s.charAt(i) == ' ') {
				if (wordEndIndex > 0) {
					// the character is space and a is before the start of a word
					// write the word to the result
					for (int j = i + 1; j < wordEndIndex; j++) {
						result.append(s.charAt(j));
					}
					// write single space
					result.append(" ");
					wordEndIndex = -1;
				}
			} else {
				if (wordEndIndex < 0) {
					// mark the end of the word if this is the last word character
					wordEndIndex = i + 1;
				}
			}
		}
		if (wordEndIndex >= 0) {
			// write the last word
			for (int j = 0; j < wordEndIndex; j++) {
				result.append(s.charAt(j));
			}
		} else {
			// no more words exist, delete trailing space
			result.deleteCharAt(result.length() - 1);
		}
		return result.toString();
	}

	public static String reverseWords2(String s) {
		int length = s.length();
		StringBuilder result = new StringBuilder();
		int wordEndIndex = -1;
		for (int i = length - 1; i >= 0; i--) {
			if (s.charAt(i) == ' ') {
				if (wordEndIndex > 0) {
					result.append(s.substring(i + 1, wordEndIndex)).append(" ");
					wordEndIndex = -1;
				}
			} else {
				if (wordEndIndex < 0) {
					wordEndIndex = i + 1;
				}
			}
		}
		if (wordEndIndex >= 0) {
			result.append(s.substring(0, wordEndIndex));
		} else {
			result.deleteCharAt(result.length() - 1);
		}
		return result.toString();
	}

	private static void check(String s, String expectedReversedWords) {
		System.out.println("s is: " + s);
		System.out.println("expectedReversedWords is: " + expectedReversedWords);
		String reverseWords = reverseWords(s); // Calls your implementation
		System.out.println("reverseWords is: " + reverseWords);
	}
}
