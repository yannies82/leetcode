package leetcode.divideandconquer;

import java.util.ArrayList;
import java.util.List;

public class DifferentWaysToAddParentheses {

	public static void main(String[] args) {
		check("2-1-1", List.of(2, 0));
		check("2*3-4*5", List.of(-34, -10, -14, -10, 10));
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/different-ways-to-add-parentheses. This
	 * solution uses a divide and conquer approach, separately calculating the
	 * results on the left and right of each operator and combining them to get all
	 * results. Time complexity is 2^n where n is the length of the expression
	 * string.
	 * 
	 * @param expression
	 * @return
	 */
	public static List<Integer> diffWaysToCompute(String expression) {
		char[] chars = expression.toCharArray();
		return diffWaysToComputeRecursive(chars, 0, chars.length);
	}

	private static List<Integer> diffWaysToComputeRecursive(char[] chars, int start, int end) {
		List<Integer> results = new ArrayList<>();
		for (int i = start; i < end; ++i) {
			if (chars[i] == '+' || chars[i] == '-' || chars[i] == '*') {
				// every time an operator is encountered use divide and conquer strategy
				// to calculate the results for the left and right side of the operator
				List<Integer> left = diffWaysToComputeRecursive(chars, start, i);
				List<Integer> right = diffWaysToComputeRecursive(chars, i + 1, end);
				int leftSize = left.size();
				int rightSize = right.size();
				// add all permutations of left and right results using the operator
				for (int j = 0; j < leftSize; j++) {
					int currentLeft = left.get(j);
					for (int k = 0; k < rightSize; k++) {
						results.add(switch (chars[i]) {
						case '+' -> currentLeft + right.get(k);
						case '-' -> currentLeft - right.get(k);
						default -> currentLeft * right.get(k);
						});
					}
				}
			}
		}
		if (results.isEmpty()) {
			// this part does not contain any operators, only a single number
			// calculate it and return it
			int num = 0;
			for (int i = start; i < end; i++) {
				num = num * 10 + (chars[i] - '0');
			}
			results.add(num);
		}
		return results;
	}

	private static void check(String expression, List<Integer> expected) {
		System.out.println("expression is: " + expression);
		System.out.println("expected is: " + expected);
		List<Integer> diffWaysToCompute = diffWaysToCompute(expression); // Calls your implementation
		System.out.println("diffWaysToCompute is: " + diffWaysToCompute);
	}
}
