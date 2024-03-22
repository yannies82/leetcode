package leetcode.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class EvaluateReversePolishNotation {

	public static void main(String[] args) {
		check(new String[] { "2", "1", "+", "3", "*" }, 9);
		check(new String[] { "4", "13", "5", "/", "+" }, 6);
		check(new String[] { "10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+" }, 22);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/evaluate-reverse-polish-notation. This solution
	 * uses a stack to keep the encountered number tokens. Whenever an operand token
	 * is encountered, the last 2 numbers are removed from the stack, the operand is
	 * applied and the result is stored back to the stack. Time complexity is O(n)
	 * where n is the length of the tokens array.
	 * 
	 * @param tokens
	 * @return
	 */
	public static int evalRPN(String[] tokens) {
		Element tokensHead = null;
		for (String token : tokens) {
			switch (token) {
			case "+":
				tokensHead = new Element(tokensHead.next.value + tokensHead.value, tokensHead.next.next);
				break;
			case "-":
				tokensHead = new Element(tokensHead.next.value - tokensHead.value, tokensHead.next.next);
				break;
			case "*":
				tokensHead = new Element(tokensHead.next.value * tokensHead.value, tokensHead.next.next);
				break;
			case "/":
				tokensHead = new Element(tokensHead.next.value / tokensHead.value, tokensHead.next.next);
				break;
			default:
				tokensHead = new Element(Integer.parseInt(token), tokensHead);
			}
		}
		return tokensHead.value;
	}

	public static int evalRPN2(String[] tokens) {
		Deque<String> tokensStack = new ArrayDeque<>();
		for (String token : tokens) {
			switch (token) {
			case "+":
				int sum = Integer.parseInt(tokensStack.poll()) + Integer.parseInt(tokensStack.poll());
				tokensStack.offerFirst(Integer.toString(sum));
				break;
			case "-":
				int second = Integer.parseInt(tokensStack.poll());
				int first = Integer.parseInt(tokensStack.poll());
				tokensStack.offerFirst(Integer.toString(first - second));
				break;
			case "*":
				int multi = Integer.parseInt(tokensStack.poll()) * Integer.parseInt(tokensStack.poll());
				tokensStack.offerFirst(Integer.toString(multi));
				break;
			case "/":
				int divider = Integer.parseInt(tokensStack.poll());
				int divident = Integer.parseInt(tokensStack.poll());
				tokensStack.offerFirst(Integer.toString(divident / divider));
				break;
			default:
				tokensStack.offerFirst(token);
			}
		}
		return Integer.parseInt(tokensStack.poll());
	}

	private static class Element {
		int value;
		Element next;

		public Element(int value, Element next) {
			super();
			this.value = value;
			this.next = next;
		}

	}

	private static void check(String[] tokens, int expectedResult) {
		System.out.println("tokens is: " + Arrays.toString(tokens));
		System.out.println("expectedResult is: " + expectedResult);
		int evalRPN = evalRPN(tokens); // Calls your implementation
		System.out.println("evalRPN is: " + evalRPN);
	}
}
