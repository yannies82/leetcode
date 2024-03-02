package leetcode.multidimensionaldynamicprogramming;

import java.util.Arrays;

public class EditDistance {

	public static void main(String[] args) {
		check("horse", "ros", 3);
		check("intention", "execution", 5);
	}

	/**
	 * This solution uses top down dynamic programming to calculate solutions to
	 * subproblems and return the final solution. Time complexity is O(m*n) where m
	 * is the length of word1 and n is the length of word2.
	 * 
	 * @param s1
	 * @param s2
	 * @param s3
	 * @return
	 */
	public static int minDistance(String word1, String word2) {
		// early exit if word1 is equal to word2
		if (word1.equals(word2)) {
			return 0;
		}
		char[] chars1 = word1.toCharArray();
		char[] chars2 = word2.toCharArray();
		// this 2D array will keep the solutions to the subproblems
		// the first dimension is the length of word1 and the second dimension is the
		// length of word2
		// the final solution is dpArray[chars1.length][chars2.length]
		int[][] dpArray = new int[chars1.length + 1][chars2.length + 1];
		for (int i = 0; i < dpArray.length; i++) {
			Arrays.fill(dpArray[i], -1);
		}
		dpArray[0][0] = 0;
		return dp(chars1, chars2, chars1.length, chars2.length, dpArray);
	}

	private static int dp(char[] chars1, char[] chars2, int index1, int index2, int[][] dpArray) {
		// keep initial values of index1 and index2
		int initialIndex1 = index1;
		int initialIndex2 = index2;
		// skip equal characters between the two strings, no operation will be
		// performed for them
		while (index1 > 0 && index2 > 0 && chars1[index1 - 1] == chars2[index2 - 1]) {
			index1--;
			index2--;
		}
		// if the subproblem has already been solved for word lengths index1 and index2
		// return the cached solution
		if (dpArray[index1][index2] != -1) {
			// subproblem is already solved
			return dpArray[index1][index2];
		}
		// calculate the solution for word lengths index1 and index2
		// assume transition by delete, calculate the solution for the previous state
		// deletion to word1 equals to advancing index1 by 1, therefore the previous
		// state should have a word1 length of index1 - 1
		int beforeDelete = index1 > 0 ? dp(chars1, chars2, index1 - 1, index2, dpArray) : Integer.MAX_VALUE;
		// assume transition by insert, calculate the solution for the previous state
		// insertion to word1 equals advancing index2 by 1 (since we insert a character
		// of word2 to word1), therefore the previous state should have a word1 length
		// of index2 - 1
		int beforeInsert = index2 > 0 ? dp(chars1, chars2, index1, index2 - 1, dpArray) : Integer.MAX_VALUE;
		// assume transition by replace, calculate the solution for the previous state
		// replacing a char of word1 by a char from word2 equals to advancing both
		// indexes by 1, therefore the previous state should have a word lengths of
		// index1 - 1 and index2 - 1 respectively
		int beforeReplace = index1 > 0 && index2 > 0 ? dp(chars1, chars2, index1 - 1, index2 - 1, dpArray)
				: Integer.MAX_VALUE;
		// calculate the min distance for lengths index1 and index2, it is equal to the
		// min of the 3 possible previous states plus 1
		int minDistance = Math.min(Math.min(beforeInsert, beforeDelete), beforeReplace) + 1;
		// cache the solution of the subproblem, the same solution applies to all
		// intermediate indexes if the words had a range of equal characters and
		// initialIndex1 != index1
		int diff = initialIndex1 - index1;
		for (int i = 0; i <= diff; i++) {
			dpArray[initialIndex1 - i][initialIndex2 - i] = minDistance;
		}
		return minDistance;
	}

	private static void check(String word1, String word2, int expected) {
		System.out.println("word1 is: " + word1);
		System.out.println("word2 is: " + word2);
		System.out.println("expected is: " + expected);
		int minDistance = minDistance(word1, word2); // Calls your implementation
		System.out.println("minDistance is: " + minDistance);
	}

}
