package leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinimumStringLengthAfterRemovingSubstrings {

	public static void main(String[] args) {
		check("ABFCACDB", 2);
		check("ACBBD", 5);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-string-length-after-removing-substrings.
	 * Optimized solution using a fixed size array as a stack to keep possible
	 * nested sequences that can be removed. Time complexity is O(n) where n is the
	 * length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int minLength(String s) {
		char[] chars = s.toCharArray();
		char[] stack = new char[chars.length + 1];
		int index = 0;
		int length = s.length();
		int count = 0;
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == 'A' || chars[i] == 'C') {
				stack[index++] = chars[i];
			} else if (index > 0) {
				if ((chars[i] == 'B' && stack[--index] == 'A') || (chars[i] == 'D' && stack[--index] == 'C')) {
					count += 2;
				} else {
					index = 0;
				}
			}
		}
		return length - count;
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-string-length-after-removing-substrings.
	 * This solution uses a deque as a stack to keep possible nested sequences that
	 * can be removed. Time complexity is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int minLength2(String s) {
		Deque<Character> stack = new ArrayDeque<>();
		int length = s.length();
		int count = 0;
		for (int i = 0; i < length; i++) {
			char current = s.charAt(i);
			if (current == 'A' || current == 'C') {
				stack.offerFirst(current);
			} else if (!stack.isEmpty()) {
				if ((current == 'B' && stack.poll() == 'A') || (current == 'D' && stack.poll() == 'C')) {
					count += 2;
				} else {
					stack.clear();
				}
			}
		}
		return length - count;
	}

	private static void check(String s, int expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		int minLength = minLength(s); // Calls your implementation
		System.out.println("minLength is: " + minLength);
	}
}
