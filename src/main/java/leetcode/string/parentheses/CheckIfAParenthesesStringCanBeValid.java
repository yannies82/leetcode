package leetcode.string.parentheses;

public class CheckIfAParenthesesStringCanBeValid {

	public static void main(String[] args) {
		check("))()))", "010100", true);
		check("()()", "0000", true);
		check("(", "0", false);
		check(")(", "00", true);
		check("())()))()(()(((())(()()))))((((()())(())", "1011101100010001001011000000110010100101", true);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid.
	 * This solution counts the max and min possible open parentheses. Time
	 * complexity is O(n) where n is the length of string s.
	 * 
	 * @param s
	 * @param locked
	 * @return
	 */
	public static boolean canBeValid(String s, String locked) {
		int length = s.length();
		if ((length & 1) == 1) {
			return false;
		}
		int maxOpenCount = 0;
		int minOpenCount = 0;
		for (int i = 0; i < length; i++) {
			if (locked.charAt(i) == '1') {
				int offset = (('(' - s.charAt(i)) << 1) + 1;
				maxOpenCount += offset;
				minOpenCount += offset;
			} else {
				maxOpenCount++;
				minOpenCount--;
			}
			if (minOpenCount < 0) {
				minOpenCount += 2;
			}
			if (maxOpenCount < 0) {
				// the max possible number off open parentheses at this index is less
				// than the max number of closing parentheses
				return false;
			}
		}
		return minOpenCount == 0;
	}

	/**
	 * Alternative solution which performs two passes. Time complexity is O(n) where
	 * n is the length of string s.
	 * 
	 * @param s
	 * @param locked
	 * @return
	 */
	public static boolean canBeValid2(String s, String locked) {
		int length = s.length();
		char[] sChars = s.toCharArray();
		char[] lockedChars = locked.toCharArray();
		if ((length & 1) == 1) {
			return false;
		}
		int openCount = 0;
		int[] closedWhichCanChange = new int[length];
		int changeIndexStart = 0;
		int changeIndexEnd = -1;
		for (int i = 0; i < length; i++) {
			if (sChars[i] == '(') {
				openCount++;
			} else {
				openCount--;
				if (lockedChars[i] == '0') {
					closedWhichCanChange[++changeIndexEnd] = i;
				}
			}
			if (openCount < 0) {
				if (changeIndexStart > changeIndexEnd) {
					return false;
				} else {
					openCount += 2;
					int indexToChange = closedWhichCanChange[changeIndexStart++];
					sChars[indexToChange] = '(';
					lockedChars[indexToChange] = '1';
				}
			}
		}
		int closedCount = 0;
		int openWhichCanChangeCount = 0;
		for (int i = length - 1; i >= 0; i--) {
			if (sChars[i] == ')') {
				closedCount++;
			} else {
				closedCount--;
				openWhichCanChangeCount += '1' - lockedChars[i];
			}
			if (closedCount < 0) {
				if (openWhichCanChangeCount == 0) {
					return false;
				} else {
					closedCount += 2;
					openWhichCanChangeCount--;
				}
			}
		}
		return true;
	}

	private static void check(String s, String locked, boolean expected) {
		System.out.println("s is: " + s);
		System.out.println("locked is: " + locked);
		System.out.println("expected is: " + expected);
		boolean canBeValid = canBeValid(s, locked); // Calls your implementation
		System.out.println("canBeValid is: " + canBeValid);
	}
}
