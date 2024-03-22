package leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculator {

	public static void main(String[] args) {
		check("1-(     -2)", 3);
		check("(1+(4+5+2)-3)+(6+8)", 23);
		check("2147483647", 2147483647);
		check("1 + 1", 2);
		check(" 2-1 + 2 ", 3);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/basic-calculator. This
	 * solution uses a linked list as a stack. Time complexity is O(n) where n is
	 * the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int calculate(String s) {
		int length = s.length();
		// this is the head of the stack which keeps nested sums
		// initialize to sum = 0 and sign = 1
		Element sumHead = new Element(0, 1, null);
		// keeps the current number as it is traversed digit by digit
		int currentNumber = 0;
		// keeps the sign of the current number
		int currentSign = 1;
		// traverse all chars of the string
		for (int i = 0; i < length; i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '+':
				// for + operand reset the current number and set the current sign to +1
				currentNumber = 0;
				currentSign = 1;
				break;
			case '-':
				// for - operand reset the current number and set the current sign to +1
				currentNumber = 0;
				currentSign = -1;
				break;
			case '(':
				// an opening parenthesis denotes a nested sum
				// add an element to the stack with sum = 0 and sign = 1 for the nested sum
				sumHead = new Element(0, currentSign, sumHead);
				currentNumber = 0;
				currentSign = 1;
				break;
			case ')':
				// a closing parenthesis denotes the end of a nested sum
				// pop the top stack element after calculating its value and adding it
				// to the next stack element
				sumHead.next.sum = sumHead.next.sum + sumHead.sign * sumHead.sum;
				sumHead = sumHead.next;
				break;
			case ' ':
				// ignore spaces
				break;
			default:
				// a new digit is added to the current number
				// subtract the current number from the current sum, then multiply it by 10 and
				// add the new digit before adding it again to the current sum
				int sum = sumHead.sum - currentSign * currentNumber;
				currentNumber = currentNumber * 10 + ch - '0';
				sumHead.sum = sum + currentSign * currentNumber;
			}
		}
		return sumHead.sign * sumHead.sum;
	}

	/**
	 * This is the same solution but it uses an ArrayDeque as a stack instead of a
	 * linked list.
	 * 
	 * @param s
	 * @return
	 */
	public static int calculate2(String s) {
		int length = s.length();
		Deque<Sum> sumsStack = new ArrayDeque<>();
		sumsStack.offerFirst(new Sum(0, 1));
		int currentNumber = 0;
		int currentSign = 1;
		for (int i = 0; i < length; i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '+':
				currentNumber = 0;
				currentSign = 1;
				break;
			case '-':
				currentNumber = 0;
				currentSign = -1;
				break;
			case '(':
				sumsStack.offerFirst(new Sum(0, currentSign));
				currentNumber = 0;
				currentSign = 1;
				break;
			case ')':
				Sum currentSum = sumsStack.poll();
				sumsStack.peek().sum = sumsStack.peek().sum + currentSum.sign * currentSum.sum;
				break;
			case ' ':
				break;
			default:
				int sum = sumsStack.peek().sum - currentSign * currentNumber;
				currentNumber = currentNumber * 10 + ch - '0';
				sumsStack.peek().sum = sum + currentSign * currentNumber;
			}
		}
		return sumsStack.peek().sign * sumsStack.peek().sum;
	}

	private static class Element {
		int sum;
		int sign;
		Element next;

		public Element(int sum, int sign, Element next) {
			super();
			this.sum = sum;
			this.sign = sign;
			this.next = next;
		}

	}

	private static class Sum {
		int sum;
		int sign;

		public Sum(int sum, int sign) {
			super();
			this.sum = sum;
			this.sign = sign;
		}

	}

	private static void check(String s, int expectedResult) {
		System.out.println("s is: " + s);
		System.out.println("expectedResult is: " + expectedResult);
		int calculate = calculate(s); // Calls your implementation
		System.out.println("calculate is: " + calculate);
	}
}
