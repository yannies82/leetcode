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

	public static int calculate(String s) {
		int length = s.length();
		Element sumHead = new Element(0, 1, null);
		int currentNumber = 0;
		int currentSign = 1;
		for (int i = 0; i < length; i++) {
			char ch;
			switch (ch = s.charAt(i)) {
			case '+':
				currentNumber = 0;
				currentSign = 1;
				break;
			case '-':
				currentNumber = 0;
				currentSign = -1;
				break;
			case '(':
				sumHead = new Element(0, currentSign, sumHead);
				currentNumber = 0;
				currentSign = 1;
				break;
			case ')':
				sumHead.next.sum = sumHead.next.sum + sumHead.sign * sumHead.sum;
				sumHead = sumHead.next;
				break;
			case ' ':
				break;
			default:
				int sum = sumHead.sum - currentSign * currentNumber;
				currentNumber = currentNumber * 10 + ch - '0';
				sumHead.sum = sum + currentSign * currentNumber;
			}
		}
		return sumHead.sign * sumHead.sum;
	}

	public static int calculate2(String s) {
		int length = s.length();
		Deque<Sum> sumsStack = new ArrayDeque<>();
		sumsStack.offerFirst(new Sum(0, 1));
		int currentNumber = 0;
		int currentSign = 1;
		for (int i = 0; i < length; i++) {
			char ch;
			switch (ch = s.charAt(i)) {
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
