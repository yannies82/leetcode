package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

	public static void main(String[] args) {
		check(3, List.of("((()))", "(()())", "(())()", "()(())", "()()()"));
		check(1, List.of("()"));
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/generate-parentheses. This
	 * solution uses backtracking. Time complexity is O(2^n).
	 * 
	 * @param n
	 * @return
	 */
	public static List<String> generateParenthesis(int n) {
		// keeps the results to return
		List<String> results = new ArrayList<>();
		// builds the result strings to be added to the results
		StringBuilder builder = new StringBuilder();
		// first parenthesis should always be an opening one, therefore
		// start with i = 0
		combineRecursive(n, 0, 0, 0, builder, results);
		return results;
	}

	private static void combineRecursive(int n, int open, int closed, int i, StringBuilder builder,
			List<String> results) {
		if (i == 0) {
			// i = 0 denotes an opening parenthesis, append it to the builder and increase
			// count
			builder.append("(");
			open++;
		} else {
			// i = 1 denotes a closing parenthesis, append it to the builder and increase
			// count
			builder.append(")");
			closed++;
		}
		if (open == n) {
			// the number of open parentheses is n, append the remaining closing parentheses
			// and add to the results list
			for (int j = closed; j < n; j++) {
				builder.append(")");
			}
			results.add(builder.toString());
			// remove the appended extra closing parentheses so that the builder can be
			// reused
			for (int j = closed; j < n; j++) {
				builder.deleteCharAt(builder.length() - 1);
			}
		} else {
			// the number of open parentheses is less than n
			// continue recursively with i = 0
			combineRecursive(n, open, closed, 0, builder, results);
			if (closed < open) {
				// if the number of closing parentheses is less than the number of opening ones
				// also continue recursively with i = 1
				combineRecursive(n, open, closed, 1, builder, results);
			}
		}
		// remove appended parenthesis when backtracking so that the builder can be
		// reused
		builder.deleteCharAt(builder.length() - 1);
	}

	private static void check(int n, List<String> expected) {
		System.out.println("n is: " + n);
		System.out.println("expected is: " + expected);
		List<String> generateParenthesis = generateParenthesis(n); // Calls your implementation
		System.out.println("generateParenthesis is: " + generateParenthesis);
	}
}
