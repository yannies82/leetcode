package leetcode.string;

public class ReverseWordsInString {

	public static void main(String[] args) {
		check("the sky is blue", "blue is sky the");
		check("  hello world  ", "world hello");
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/reverse-words-in-a-string.
	 * This solution uses recursion to write the words to the builder in reverse
	 * order. Time complexity is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static String reverseWords(String s) {
		char[] chars = s.toCharArray();
		StringBuilder builder = new StringBuilder();
		// skip all leading spaces
		int start = 0;
		while (start < chars.length && chars[start] == ' ') {
			start++;
		}
		// reverse words recursively
		reverseRecursive(chars, start, builder);
		// delete last trailing space after reverse
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}

	private static void reverseRecursive(char[] chars, int start, StringBuilder builder) {
		if (start == chars.length) {
			// no more words, return
			return;
		}
		int i = start;
		// skip until a space is encountered
		while (i < chars.length && chars[i] != ' ') {
			i++;
		}
		// mark the end of the word and skip the rest of the spaces
		int end = i;
		while (i < chars.length && chars[i] == ' ') {
			i++;
		}
		// recursively append the next words
		reverseRecursive(chars, i, builder);
		// append this word after the next ones have been appended
		for (int j = start; j < end; j++) {
			builder.append(chars[j]);
		}
		builder.append(' ');
	}

	/**
	 * This solution traverses the string from end to start and writes all
	 * encountered words to the result builder. Time complexity is O(n) where n is
	 * the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static String reverseWords2(String s) {
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

	public static String reverseWords3(String s) {
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
