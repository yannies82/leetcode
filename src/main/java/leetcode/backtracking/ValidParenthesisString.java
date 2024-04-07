package leetcode.backtracking;

public class ValidParenthesisString {

	public static void main(String[] args) {
		check("()", true);
		check("(*)", true);
		check("(*))", true);
		check("(*)))", false);
		check("**************************************************))))))))))))))))))))))))))))))))))))))))))))))))))",
				true);
		check("(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())",
				false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/valid-parenthesis-string.
	 * This solution keeps a min and max count of open parentheses while traversing
	 * the characters of string s. Returns false if the max count becomes less than
	 * 0. After all characters are traversed it returns true if min count is equal
	 * to 0. Time complexity is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static boolean checkValidString(String s) {
		char[] chars = s.toCharArray();
		// keeps the min count of open parentheses
		int minCount = 0;
		// keeps the max count of open parentheses
		int maxCount = 0;
		// traverse all characters
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == '*') {
				// every time a '*' character is encountered, the min count is decreased by 1
				// and the max count is increased by 1
				// min count cannot be less than 0
				minCount = Math.max(0, minCount - 1);
				maxCount += 1;
			} else if (chars[i] == '(') {
				// every time a '(' character is encountered, both counters are increased by 1
				minCount += 1;
				maxCount += 1;
			} else {
				// every time a ')' character is encountered, both counters are decreased by 1
				// if max count becomes less than 0 then we return false
				if (maxCount == 0) {
					return false;
				}
				// min count cannot be less than 0
				minCount = Math.max(0, minCount - 1);
				maxCount -= 1;
			}
		}
		return minCount == 0;
	}

	private static void check(String s, boolean expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		boolean checkValidString = checkValidString(s); // Calls your implementation
		System.out.println("checkValidString is: " + checkValidString);
	}
}
