package leetcode.multidimensionaldynamicprogramming;

public class LongestPalindromicSubstring {

	public static void main(String[] args) {
		check("cbbd", "bb");
		check("bacabab", "bacab");
		check("babad", "bab");
	}

	/**
	 * This solution uses all positions of string s as starting positions and
	 * searches for palindrome strings by decreasing the start index and increasing
	 * the end index. Sequences of the same character are inherently palindrome and
	 * are searched only once for the full sequence. Time complexity is O(n^2) where
	 * n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static String longestPalindrome(String s) {
		int length = s.length();
		char[] chars = s.toCharArray();
		// keep the start position, end position and length of the max palindrome
		// substring
		int maxStart = 0;
		int maxEnd = 1;
		int maxLength = 1;
		// use all characters of the string s as starting positions
		// start is inclusive and end is exclusive
		for (int i = 0; i <= length; i++) {
			int start = i;
			int end = i + 1;

			// increase end index until the character there is different than the one at
			// start index
			while (end < length && chars[start] == chars[end]) {
				end++;
			}
			// increase i so that we skip checking for intermediate start indexes for the
			// next iterations
			i = end - 1;

			// decrease start and increase end while the string is palindromic
			while (start > 0 && end < length && chars[start - 1] == chars[end]) {
				start--;
				end++;
			}

			// if the palindrome string length is greater than the current max one
			// update the max start position, end position and length
			if (end - start > maxLength) {
				maxStart = start;
				maxEnd = end;
				maxLength = end - start;
			}
		}
		// return the max palindrome substring
		return s.substring(maxStart, maxEnd);
	}

	/**
	 * This solution iterates the possible substrings of string s to find
	 * palindromes, starting from the ones with the greatest length. If a palindrome
	 * is found then substrings with shorter length are not checked. Time complexity
	 * is O(n^3) where n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static String longestPalindrome2(String s) {
		int length = s.length();
		char[] chars = s.toCharArray();
		// keep the start position, end position and length of the max palindrome
		// substring
		int maxStart = 0;
		int maxEnd = 1;
		int maxLength = 1;
		// iterate all substrings starting from the greater ones
		// i is the start index (inclusive) and j is the end index (exclusive)
		for (int i = 0; i <= length - maxLength - 1; i++) {
			// for this iteration we will only check substrings with length greater than
			// maxLength
			int nextLimit = i + maxLength;
			for (int j = length; j > nextLimit; j--) {
				if (isPalindrome(chars, i, j)) {
					// if the substring is palindrome, update the start position, end position and
					// length of the max palindrome substring and exit the inner loop, since j is
					// decreasing all subsequent substrings will be of smaller length than maxLength
					maxStart = i;
					maxEnd = j;
					maxLength = j - i;
					break;
				}
			}
		}
		// return the max palindrome substring
		return s.substring(maxStart, maxEnd);
	}

	private static boolean isPalindrome(char[] chars, int start, int end) {
		int mid = (start + end) / 2;
		for (int i = start; i < mid; i++) {
			if (chars[i] != chars[end + start - i - 1]) {
				return false;
			}
		}
		return true;
	}

	private static void check(String s, String expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		String longestPalindrome = longestPalindrome(s); // Calls your implementation
		System.out.println("longestPalindrome is: " + longestPalindrome);
	}

}
