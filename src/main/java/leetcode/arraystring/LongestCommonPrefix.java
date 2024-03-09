package leetcode.arraystring;

import java.util.Arrays;

public class LongestCommonPrefix {

	public static void main(String[] args) {
		check(new String[] { "flower", "flow", "flight" }, "fl");
		check(new String[] { "dog", "racecar", "car" }, "");
		check(new String[] { "a", "ac" }, "a");
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/longest-common-prefix. This
	 * solution compares the letters of all words at the same index until one of
	 * them is different. Time complexity is O(n * m) where n is the min word length
	 * and m is the number of strings int the array.
	 * 
	 * @param strs
	 * @return
	 */
	public static String longestCommonPrefix(String[] strs) {
		int length = strs.length;
		String firstWord = strs[0];
		int firstLength = firstWord.length();
		for (int j = 0; j < firstLength; j++) {
			for (int i = 1; i < length; i++) {
				char firstWordChar = firstWord.charAt(j);
				if (strs[i].length() <= j || strs[i].charAt(j) != firstWordChar) {
					return firstWord.substring(0, j);
				}
			}
		}
		return firstWord.substring(0, firstLength);
	}

	/**
	 * Alternate implementation, loops are reversed. Time complexity is O(m * n)
	 * where n is the min word length and m is the number of strings int the array.
	 * 
	 * @param strs
	 * @return
	 */
	public static String longestCommonPrefix2(String[] strs) {
		int length = strs.length;
		String commonPrefix = strs[0];
		int commonPrefixLength = commonPrefix.length();
		for (int i = 1; i < length && commonPrefixLength > 0; i++) {
			int stringLength = strs[i].length();
			if (commonPrefixLength > stringLength) {
				commonPrefixLength = stringLength;
			}
			for (int j = 0; j < commonPrefixLength; j++) {
				if (commonPrefix.charAt(j) != strs[i].charAt(j)) {
					commonPrefixLength = j;
					break;
				}
			}
		}
		return commonPrefix.substring(0, commonPrefixLength);
	}

	private static void check(String[] strs, String expectedLongestCommonPrefix) {
		System.out.println("strs is: " + Arrays.toString(strs));
		System.out.println("expectedLongestCommonPrefix is: " + expectedLongestCommonPrefix);
		String longestCommonPrefix = longestCommonPrefix(strs); // Calls your implementation
		System.out.println("longestCommonPrefix is: " + longestCommonPrefix);
	}
}
