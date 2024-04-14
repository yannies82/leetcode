package leetcode.multidimensionaldynamicprogramming;

public class InterleavingString {

	public static void main(String[] args) {
		check("aabcc", "dbbca", "aadbbcbcac", true);
		check("aabcc", "dbbca", "aadbbbaccc", false);
		check("", "", "", true);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/interleaving-string. This
	 * solution uses top down dynamic programming to calculate solutions to
	 * subproblems and return the final solution. Time complexity is O(m*n) where m
	 * is the length of s1 and n is the length of s2.
	 * 
	 * @param s1
	 * @param s2
	 * @param s3
	 * @return
	 */
	public static boolean isInterleave(String s1, String s2, String s3) {
		// early exit if s1 and s2 lengths do not add up to s3 length
		if (s1.length() + s2.length() != s3.length()) {
			return false;
		}
		char[] chars1 = s1.toCharArray();
		char[] chars2 = s2.toCharArray();
		char[] chars3 = s3.toCharArray();
		// this 2D array will keep the solutions to the subproblems
		// the first dimension is the length of s1 and the second dimension is the
		// length of s2
		// the final solution is dpArray[chars1.length][chars2.length]
		// in order to skip the array initialization to a value like -1, by convention
		// 0 = unsolved problem
		// 1 = solved problem, false
		// 2 = solved problem, value true
		int[][] dpArray = new int[chars1.length + 1][chars2.length + 1];
		return dp(chars1, chars2, chars3, chars1.length, chars2.length, dpArray);
	}

	private static boolean dp(char[] chars1, char[] chars2, char[] chars3, int index1, int index2, int[][] dpArray) {
		// early exit if no chars are left
		if (index1 == 0 && index2 == 0) {
			return true;
		}
		if (dpArray[index1][index2] != 0) {
			// subproblem is already solved, by convention return true if value is 2
			return dpArray[index1][index2] == 2;
		}
		// calculate the index for s3 based on index1 and index2
		int index3 = index1 + index2 - 1;
		// calculate the subproblem solution, it is true if the last remaining character
		// of s1 or the last remaining character of s2 is equal to the last remaining
		// character of s3 and the respective subproblem solution when excluding this
		// character is also true
		boolean result = (index1 > 0 && chars1[index1 - 1] == chars3[index3]
				&& dp(chars1, chars2, chars3, index1 - 1, index2, dpArray))
				|| (index2 > 0 && chars2[index2 - 1] == chars3[index3]
						&& dp(chars1, chars2, chars3, index1, index2 - 1, dpArray));
		// by convention 2 for true, 1 for false
		dpArray[index1][index2] = result ? 2 : 1;
		return result;
	}

	private static void check(String s1, String s2, String s3, boolean expected) {
		System.out.println("s1 is: " + s1);
		System.out.println("s2 is: " + s2);
		System.out.println("s3 is: " + s3);
		System.out.println("expected is: " + expected);
		boolean isInterleave = isInterleave(s1, s2, s3); // Calls your implementation
		System.out.println("isInterleave is: " + isInterleave);
	}

}
