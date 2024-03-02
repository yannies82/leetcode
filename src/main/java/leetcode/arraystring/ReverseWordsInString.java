package leetcode.arraystring;

public class ReverseWordsInString {

	public static void main(String[] args) {
		check("the sky is blue", "blue is sky the");
		check("  hello world  ", "world hello");
	}

	public static String reverseWords(String s) {
		int length = s.length();
		StringBuilder result = new StringBuilder();
		int wordEndIndex = -1;
		for (int i = length - 1; i >= 0; i--) {
			if (s.charAt(i) == ' ') {
				if (wordEndIndex > 0) {
					for (int j = i + 1; j < wordEndIndex; j++) {
						result.append(s.charAt(j));
					}
					result.append(" ");
					wordEndIndex = -1;
				}
			} else {
				if (wordEndIndex < 0) {
					wordEndIndex = i + 1;
				}
			}
		}
		if (wordEndIndex >= 0) {
			for (int j = 0; j < wordEndIndex; j++) {
				result.append(s.charAt(j));
			}
		} else {
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
