package leetcode.string.frequency;

public class CustomSortString {

	public static void main(String[] args) {
		check("cba", "abcd", "cbad");
		check("bcafg", "abcd", "bcad");
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/custom-sort-string. This
	 * solution stores the frequencies of characters in string s in an array. It
	 * then traverses the characters of order string and places these characters
	 * first in the result string. Time complexity is O(n+m) where n is the length
	 * of strin order and m is the length of string s.
	 * 
	 * @param order
	 * @param s
	 * @return
	 */
	public static String customSortString(String order, String s) {
		int[] charCount = new int[26];
		for (int i = 0; i < s.length(); i++) {
			charCount[s.charAt(i) - 'a']++;
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < order.length(); i++) {
			char aChar = order.charAt(i);
			int index = aChar - 'a';
			while (charCount[index] > 0) {
				builder.append(aChar);
				charCount[index]--;
			}
		}
		for (int i = 0; i < charCount.length; i++) {
			while (charCount[i] > 0) {
				builder.append((char) ('a' + i));
				charCount[i]--;
			}
		}
		return builder.toString();
	}

	private static void check(String order, String s, String expected) {
		System.out.println("order is: " + order);
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		String customSortString = customSortString(order, s); // Calls your implementation
		System.out.println("customSortString is: " + customSortString);
	}
}
