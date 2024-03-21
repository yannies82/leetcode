package leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParentheses {

	public static void main(String[] args) {
		check("]", false);
		check("()", true);
		check("()[]{}", true);
		check("(]", false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/valid-parentheses. This
	 * solution pushes opening parentheses into a stack. For every closing
	 * parenthesis the next value polled from the stack is expected to be the
	 * respective opening parenthesis. Time complexity is O(n) where n is the length
	 * of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isValid(String s) {
		int length = s.length();
		Deque<Character> stack = new ArrayDeque<>();
		for (int i = 0; i < length; i++) {
			char ch;
			Character otherCh;
			switch (ch = s.charAt(i)) {
			case '(':
			case '[':
			case '{':
				stack.offerFirst(ch);
				break;
			case ')':
				if ((otherCh = stack.poll()) == null || otherCh != '(') {
					return false;
				}
				break;
			case ']':
				if ((otherCh = stack.poll()) == null || otherCh != '[') {
					return false;
				}
				break;
			case '}':
				if ((otherCh = stack.poll()) == null || otherCh != '{') {
					return false;
				}
				break;
			}
		}
		return stack.isEmpty();
	}

	private static void check(String s, boolean expectedResult) {
		System.out.println("s is: " + s);
		System.out.println("expectedResult is: " + expectedResult);
		boolean isValid = isValid(s); // Calls your implementation
		System.out.println("isValid is: " + isValid);
	}
}
