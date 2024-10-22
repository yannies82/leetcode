package leetcode.string;

public class MinAddToMakeParenthesesValid {

	public static void main(String[] args) {
		check("())", 1);
		check("(((", 3);
		check(")))(", 4);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-add-to-make-parentheses-valid. This
	 * solution counts the max number of closing parentheses before opening
	 * parentheses and the max number of opening parentheses after closing
	 * parentheses and adds these two numbers. Time complexity is O(n) where n is
	 * the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int minAddToMakeValid(String s) {
		char[] chars = s.toCharArray();
		int count = 0;
		int maxClosedBeforeOpen = 0;
		int maxOpenAfterClose = 0;
		// find the max number of closing parentheses that have no opening parenthesis
		// before them, also find the max number of opening parentheses without closing
		// parentheses
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == ')') {
				count++;
				if (count > maxClosedBeforeOpen) {
					maxClosedBeforeOpen = count;
				}
				if (maxOpenAfterClose > 0) {
					maxOpenAfterClose--;
				}
			} else {
				count--;
				maxOpenAfterClose++;
			}
		}
		return maxClosedBeforeOpen + maxOpenAfterClose;
	}

	private static void check(String s, int expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		int minAddToMakeValid = minAddToMakeValid(s); // Calls your implementation
		System.out.println("minAddToMakeValid is: " + minAddToMakeValid);
	}
}
