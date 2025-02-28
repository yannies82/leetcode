package leetcode.multidimensionaldynamicprogramming;

public class ShortestCommonSupersequence {

	public static void main(String[] args) {
		check("abac", "cab", "cabac");
		check("aaaaaaaa", "aaaaaaaa", "aaaaaaaa");
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/shortest-common-supersequence. This solution
	 * uses dynamic programming to find the longest common subsequence. It then
	 * tries to reconstruct the shortest common subsequence using the dp array. Time
	 * complexity is O(m*n) where m is the length of str1 and n is the length of
	 * str2.
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String shortestCommonSupersequence(String str1, String str2) {
		char[] str1Chars = str1.toCharArray();
		char[] str2Chars = str2.toCharArray();
		// this array keeps the longest common subsequence solution for
		// str1 of length i and str2 of length j
		int[][] dpArray = new int[str1Chars.length + 1][str2Chars.length + 1];
		// find the longest common subsequence using dynamic programming
		for (int i = 1; i <= str1Chars.length; i++) {
			for (int j = 1; j <= str2Chars.length; j++) {
				if (str1Chars[i - 1] == str2Chars[j - 1]) {
					// the two strings have a common character, increase the value
					dpArray[i][j] = 1 + dpArray[i - 1][j - 1];
				} else {
					// the two strings don't have a common character
					dpArray[i][j] = Math.max(dpArray[i - 1][j], dpArray[i][j - 1]);
				}
			}
		}

		// generate the shortest common supersequence
		// start from the bottom right of the dp table
		int i = str1Chars.length - 1;
		int j = str2Chars.length - 1;
		StringBuilder result = new StringBuilder();

		while (i >= 0 && j >= 0) {
			if (str1Chars[i] == str2Chars[j]) {
				// if character in both strings is the same, add it
				result.append(str1Chars[i--]);
				j--;
			} else if (dpArray[i][j + 1] > dpArray[i + 1][j]) {
				// if coming from top has higher value, take character from str1
				result.append(str1Chars[i--]);
			} else {
				// otherwise, take character from str2
				result.append(str2Chars[j--]);
			}
		}

		// add remaining characters from str1 (if any)
		while (i >= 0) {
			result.append(str1Chars[i--]);
		}

		// add remaining characters from str2 (if any)
		while (j >= 0) {
			result.append(str2Chars[j--]);
		}

		// reverse the string to get the result
		return result.reverse().toString();
	}

	private static void check(String str1, String str2, String expected) {
		System.out.println("str1 is: " + str1);
		System.out.println("str2 is: " + str2);
		System.out.println("expected is: " + expected);
		String shortestCommonSupersequence = shortestCommonSupersequence(str1, str2); // Calls your implementation
		System.out.println("shortestCommonSupersequence is: " + shortestCommonSupersequence);
	}
}
