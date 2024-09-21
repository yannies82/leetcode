package leetcode.arraystring;

public class ShortestPalindrome {

	public static void main(String[] args) {
		check("aacecaaa", "aaacecaaa");
		check("abcd", "dcbabcd");
		check("abababababfe", "efbabababababfe");
		check("aabba", "abbaabba");
		check("aba", "aba");
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/shortest-palindrome.
	 * Optimized solution which minimizes the searches for palindromic sequences.
	 * Worst case time complexity is O(n^2) where n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static String shortestPalindrome(String s) {
		char[] chars = s.toCharArray();
		if (isPalindrome(chars, 0, chars.length)) {
			// early exit if s is already palindrome
			return s;
		}
		// this is the index where the largest palindromic subsequence from the
		// beginning of the string ends, worst case scenario is index 1 because the
		// string can always be palindromic around the first character
		int palindromeIndex = 1;
		// search for the largest sequence starting from the character before the middle
		// of the string because the palindromic sequence has to include the first
		// character, since we can only append characters to the start of the string
		int currentIndex = (chars.length / 2) - 1;
		while (currentIndex >= 0) {
			int endIndex = currentIndex;
			char mid = chars[currentIndex];
			// decrease currentIndex while the character remains the same
			while (currentIndex >= 0 && chars[currentIndex] == mid) {
				currentIndex--;
			}
			// decrease endIndex while the character remains the same
			while (endIndex < chars.length && chars[endIndex] == mid) {
				endIndex++;
			}
			if (currentIndex < 0) {
				// sequence is palindromic, set the index where it ends
				palindromeIndex = endIndex;
				break;
			}
			int startIndex = currentIndex;
			// skip searching if the palindromic string won't cover the first character
			if (startIndex <= chars.length - endIndex - 1) {
				// check all characters until the start of the string
				while (startIndex >= 0 && chars[startIndex] == chars[endIndex]) {
					startIndex--;
					endIndex++;
				}
				if (startIndex < 0) {
					// sequence is palindromic, set the index where it ends
					palindromeIndex = endIndex;
					break;
				}
			}
		}

		// append non palindromic characters from the end of string s to the beginning
		StringBuilder builder = new StringBuilder();
		for (int i = chars.length - 1; i >= palindromeIndex; i--) {
			builder.append(chars[i]);
		}
		// append string s
		builder.append(s);
		return builder.toString();
	}

	/**
	 * Simple solution which checks all subsequences ending at the first character.
	 * Time complexity is O(n^2) where n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static String shortestPalindrome2(String s) {
		char[] chars = s.toCharArray();
		// check to find palindrome substrings of string s, largest to smallest
		// starting from the beginning of the string
		int palindromeIndex = 1;
		for (int i = chars.length; i > 1; i--) {
			if (isPalindrome(chars, 0, i)) {
				palindromeIndex = i;
				break;
			}
		}
		// append non palindrome characters at end of string s to the beginning
		StringBuilder builder = new StringBuilder();
		for (int i = chars.length - 1; i >= palindromeIndex; i--) {
			builder.append(chars[i]);
		}
		// append string s
		builder.append(s);
		return builder.toString();
	}

	private static boolean isPalindrome(char[] chars, int start, int end) {
		int mid = (start + end) / 2;
		int lastIndex = end - 1;
		for (int i = start; i < mid; i++) {
			if (chars[i] != chars[lastIndex - i]) {
				return false;
			}
		}
		return true;
	}

	private static void check(String s, String expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		String shortestPalindrome = shortestPalindrome2(s); // Calls your implementation
		System.out.println("shortestPalindrome is: " + shortestPalindrome);
	}
}
