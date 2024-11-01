package leetcode.string;

import java.util.Arrays;

public class ReverseString {

	public static void main(String[] args) {
		check(new char[] { 'h', 'e', 'l', 'l', 'o' }, new char[] { 'o', 'l', 'l', 'e', 'h' });
		check(new char[] { 'H', 'a', 'n', 'n', 'a', 'h' }, new char[] { 'h', 'a', 'n', 'n', 'a', 'H' });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/reverse-string. Time
	 * complexity is O(n) where n is the length of the char array s.
	 * 
	 * @param s
	 */
	public static void reverseString(char[] s) {
		int mid = s.length / 2;
		int lastIndex = s.length - 1;
		for (int i = 0; i < mid; i++) {
			char temp = s[i];
			int otherIndex = lastIndex - i;
			s[i] = s[otherIndex];
			s[otherIndex] = temp;
		}
	}

	private static void check(char[] s, char[] expected) {
		System.out.println("s is: " + Arrays.toString(s));
		System.out.println("expected is: " + Arrays.toString(expected));
		reverseString(s); // Calls your implementation
		System.out.println("reverseString is: " + Arrays.toString(s));
	}
}
