package leetcode.string;

import java.util.Arrays;

public class AddingSpacesToAString {

	public static void main(String[] args) {
		check("LeetcodeHelpsMeLearn", new int[] { 8, 13, 15 }, "Leetcode Helps Me Learn");
		check("icodeinpython", new int[] { 1, 5, 7, 9 }, "i code in py thon");
		check("spacing", new int[] { 0, 1, 2, 3, 4, 5, 6 }, " s p a c i n g");
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/adding-spaces-to-a-string.
	 * Time complexity is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @param spaces
	 * @return
	 */
	public static String addSpaces(String s, int[] spaces) {
		if (spaces.length == 0) {
			return s;
		}
		char[] sChars = s.toCharArray();
		char[] chars = new char[sChars.length + spaces.length];
		int startIndex = 0;
		for (int i = 0; i < spaces.length; i++) {
			for (int j = startIndex; j < spaces[i]; j++) {
				chars[j + i] = sChars[j];
			}
			chars[spaces[i] + i] = ' ';
			startIndex = spaces[i];
		}
		for (int j = startIndex; j < sChars.length; j++) {
			chars[j + spaces.length] = sChars[j];
		}
		return new String(chars);
	}

	/**
	 * This solution uses a single while loop. Time complexity is O(n) where n is
	 * the length of string s.
	 * 
	 * @param s
	 * @param spaces
	 * @return
	 */
	public static String addSpaces2(String s, int[] spaces) {
		if (spaces.length == 0) {
			return s;
		}
		char[] sChars = s.toCharArray();
		char[] chars = new char[sChars.length + spaces.length];
		int spacesIndex = 0;
		int sIndex = 0;
		int index = 0;
		while (sIndex < sChars.length && spacesIndex < spaces.length) {
			if (sIndex == spaces[spacesIndex]) {
				chars[index++] = ' ';
				spacesIndex++;
			}
			chars[index++] = sChars[sIndex++];
		}
		for (int i = sIndex; i < sChars.length; i++) {
			chars[index++] = sChars[sIndex++];
		}
		return new String(chars);
	}

	/**
	 * This solution uses System.arraycopy to copy the characters to the output
	 * array. Time complexity is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @param spaces
	 * @return
	 */
	public static String addSpaces3(String s, int[] spaces) {
		if (spaces.length == 0) {
			return s;
		}
		char[] sChars = s.toCharArray();
		char[] chars = new char[sChars.length + spaces.length];
		int startIndex = 0;
		for (int i = 0; i < spaces.length; i++) {
			System.arraycopy(sChars, startIndex, chars, startIndex + i, spaces[i] - startIndex);
			chars[spaces[i] + i] = ' ';
			startIndex = spaces[i];
		}
		System.arraycopy(sChars, startIndex, chars, startIndex + spaces.length, sChars.length - startIndex);
		return new String(chars);
	}

	/**
	 * This solution does not convert the string to a char array. Time complexity is
	 * O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @param spaces
	 * @return
	 */
	public static String addSpaces4(String s, int[] spaces) {
		if (spaces.length == 0) {
			return s;
		}
		int sLength = s.length();
		char[] chars = new char[sLength + spaces.length];
		int spacesIndex = 0;
		int sIndex = 0;
		int index = 0;
		while (sIndex < sLength && spacesIndex < spaces.length) {
			if (sIndex == spaces[spacesIndex]) {
				chars[index++] = ' ';
				spacesIndex++;
			}
			chars[index++] = s.charAt(sIndex++);
		}
		for (int i = sIndex; i < sLength; i++) {
			chars[index++] = s.charAt(sIndex++);
		}
		return new String(chars);
	}

	/**
	 * This solution uses a StringBuilder instead of an array to build the result
	 * string. Time complexity is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @param spaces
	 * @return
	 */
	public static String addSpaces5(String s, int[] spaces) {
		if (spaces.length == 0) {
			return s;
		}
		StringBuilder builder = new StringBuilder();
		int sLength = s.length();
		int spacesIndex = 0;
		int sIndex = 0;
		while (sIndex < sLength && spacesIndex < spaces.length) {
			if (sIndex == spaces[spacesIndex]) {
				builder.append(' ');
				spacesIndex++;
			}
			builder.append(s.charAt(sIndex++));
		}
		for (int i = sIndex; i < sLength; i++) {
			builder.append(s.charAt(sIndex++));
		}
		return builder.toString();
	}

	private static void check(String s, int[] spaces, String expected) {
		System.out.println("s is: " + s);
		System.out.println("spaces is: " + Arrays.toString(spaces));
		System.out.println("expected is: " + expected);
		String addSpaces = addSpaces(s, spaces); // Calls your implementation
		System.out.println("addSpaces is: " + addSpaces);
	}
}
