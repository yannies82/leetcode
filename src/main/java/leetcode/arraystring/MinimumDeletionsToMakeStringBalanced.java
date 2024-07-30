package leetcode.arraystring;

public class MinimumDeletionsToMakeStringBalanced {

	public static void main(String[] args) {
		check("aababbab", 2);
		check("bbaaaaabb", 2);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-deletions-to-make-string-balanced. This
	 * solution counts the number of 'b's before and 'a's after each index and
	 * returns the min sum as the result. Time complexity is O(n) where n is the
	 * length of string s.
	 * 
	 * @param s
	 * @return
	 */
	public static int minimumDeletions(String s) {
		char[] chars = s.toCharArray();
		int[] bsBefore = new int[chars.length];
		int[] asAfter = new int[chars.length];
		for (int i = 1; i < chars.length; i++) {
			int iMinus1 = i - 1;
			int lengthMinusI = chars.length - i;
			int lengthMinusIMinus1 = lengthMinusI - 1;
			;
			bsBefore[i] = bsBefore[iMinus1];
			asAfter[lengthMinusIMinus1] = asAfter[lengthMinusI];
			if (chars[iMinus1] == 'b') {
				bsBefore[i]++;
			}
			if (chars[lengthMinusI] == 'a') {
				asAfter[lengthMinusIMinus1]++;
			}
		}
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < chars.length; i++) {
			int operations = bsBefore[i] + asAfter[i];
			if (operations < result) {
				result = operations;
			}
		}
		return result;
	}

	private static void check(String s, int expected) {
		System.out.println("s is: " + s);
		System.out.println("expected is: " + expected);
		int minimumDeletions = minimumDeletions(s); // Calls your implementation
		System.out.println("minimumDeletions is: " + minimumDeletions);
	}
}
