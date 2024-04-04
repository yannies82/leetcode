package leetcode.arraystring;

public class MaximumNestingDepthOfTheParentheses {

	public static void main(String[] args) {
		check("(1+(2*3)+((8)/4))+1", 3);
		check("(1)+((2))+(((3)))", 3);
	}

	/**
	 * Leetcode problem: This solution uses a counter which increases every time a
	 * parenthesis is opened and decreases every time a parenthesis is closed. Time
	 * complexity is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int maxDepth(String s) {
		int depth = 0;
		int maxDepth = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				depth++;
				if (depth > maxDepth) {
					maxDepth = depth;
				}
			} else if (s.charAt(i) == ')') {
				depth--;
			}
		}
		return maxDepth;
	}

	private static void check(String s, int expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		int maxDepth = maxDepth(s); // Calls your implementation
		System.out.println("maxDepth is: " + maxDepth);
	}
}
