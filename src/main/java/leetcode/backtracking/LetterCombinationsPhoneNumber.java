package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LetterCombinationsPhoneNumber {

	public static void main(String[] args) {
		String digits = "23";
		List<String> expected = List.of("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
		check(digits, expected);
	}

	// mapping of digits 2 - 9 to characters using an array of char arrays
	private static final char[][] DIGITS_ARRAY = { { 'a', 'b', 'c' }, { 'd', 'e', 'f' }, { 'g', 'h', 'i' },
			{ 'j', 'k', 'l' }, { 'm', 'n', 'o' }, { 'p', 'q', 'r', 's' }, { 't', 'u', 'v' }, { 'w', 'x', 'y', 'z' } };

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/letter-combinations-of-a-phone-number. This
	 * solution traverses the given digits starting from index 0 and recursively
	 * applies all combinations, increasing the index between levels. Time
	 * complexity is O(N^M) where N is the number of characters per digit (3 or 4)
	 * and M is the digits string length.
	 * 
	 * @param digits
	 * @return
	 */
	public static List<String> letterCombinations(String digits) {
		// early exit if digits string is empty
		if (digits.isEmpty()) {
			return Collections.emptyList();
		}
		// keeps the results to return
		List<String> results = new ArrayList<>();
		// builds the result strings to be added to the results
		StringBuilder builder = new StringBuilder();
		// recursively traverse the digits string starting from index 0
		// and fill the results list
		combineRecursive(digits, 0, builder, results);
		return results;
	}

	private static void combineRecursive(String digits, int i, StringBuilder builder, List<String> results) {
		int length = digits.length();
		boolean isLast = i == length - 1;
		// resolve the digits string char at this specific position to the respective
		// array of chars
		char[] possibleChars = DIGITS_ARRAY[digits.charAt(i) - '2'];
		// traverse all possible chars
		for (int j = 0; j < possibleChars.length; j++) {
			// append each char to the builder before advancing to the next level
			builder.append(possibleChars[j]);
			if (isLast) {
				// if this is the last character of the digits string build the result
				// and add it to the results list
				results.add(builder.toString());
			} else {
				// this is not the last character of the digits string
				// advance to the next level by increasing the index
				// and performing a recursive call
				combineRecursive(digits, i + 1, builder, results);
			}
			// remove the appended char from the builder so that the builder can be reused
			// for the next possible sibling character
			builder.deleteCharAt(builder.length() - 1);
		}
	}

	private static void check(String digits, List<String> expected) {
		System.out.println("digits is: " + digits);
		System.out.println("expected is: " + expected);
		List<String> letterCombinations = letterCombinations(digits); // Calls your implementation
		System.out.println("letterCombinations is: " + letterCombinations);
	}
}
