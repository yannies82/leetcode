package leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class ReverseParentheses {

	public static void main(String[] args) {
		check("(abcd)", "dcba");
		check("(u(love)i)", "iloveu");
		check("(ed(et(oc))el)", "leetcode");
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses.
	 * This solution uses a stack to keep positions of opening parentheses and a
	 * StringBuilder to build the final string. Time complexity is O(n) where n is
	 * the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static String reverseParentheses(String s) {
		char[] chars = s.toCharArray();
		int length = s.length();
		Deque<Integer> stack = new ArrayDeque<>();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			switch (chars[i]) {
			case '(':
				// keep the position of the opening parenthesis in the string builder
				stack.offerFirst(builder.length());
				break;
			case ')':
				// if the next character is a closing parenthesis, reverse the string
				// since the last opening parenthesis position
				reverseChars(builder, stack.poll(), builder.length());
				break;
			default:
				builder.append(chars[i]);
			}
		}
		return builder.toString();
	}

	private static void reverseChars(StringBuilder builder, int start, int end) {
		int mid = (start + end) / 2;
		int last = end - 1;
		for (int i = start; i < mid; i++) {
			char temp = builder.charAt(i);
			builder.setCharAt(i, builder.charAt(last + start - i));
			builder.setCharAt(last + start - i, temp);
		}
	}

	private static void check(String s, String expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		String reverseParentheses = reverseParentheses(s); // Calls your implementation
		System.out.println("reverseParentheses is: " + reverseParentheses);
	}
}
