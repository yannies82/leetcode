package leetcode.bitmanipulation;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FlipColumnsForMaximumNumberOfEqualRows {

	public static void main(String[] args) {
		check(new int[][] { { 0, 0, 0 }, { 0, 0, 1 }, { 1, 1, 0 } }, 2);
		check(new int[][] { { 0, 1 }, { 1, 1 } }, 1);
		check(new int[][] { { 0, 1 }, { 1, 0 } }, 2);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/flip-columns-for-maximum-number-of-equal-rows.
	 * This solution uses a trie to keep the pattern for each row. Time complexity
	 * is O(m*n) where m is the number of rows and n is the number of columns in
	 * matrix.
	 * 
	 * 
	 * @param mat
	 * @return
	 */
	public static int maxEqualRowsAfterFlips(int[][] matrix) {
		Node root = new Node();
		int maxCount = 0;
		for (int i = 0; i < matrix.length; i++) {
			Node current = root;
			for (int j = 0; j < matrix[i].length; j++) {
				int value = matrix[i][j] ^ matrix[i][0];
				if (current.children[value] == null) {
					current.children[value] = new Node();
				}
				current = current.children[value];
			}
			current.count++;
			maxCount = Math.max(current.count, maxCount);
		}
		return maxCount;
	}

	private static class Node {
		Node[] children = new Node[2];
		int count;
	}

	/**
	 * Similar solution which uses a StringBuilder instead of a trie. Time
	 * complexity is O(m*n) where m is the number of rows and n is the number of
	 * columns in matrix.
	 * 
	 * @param matrix
	 * @return
	 */
	public static int maxEqualRowsAfterFlips2(int[][] matrix) {
		Map<String, Integer> patternFrequency = new HashMap<>();
		for (int i = 0; i < matrix.length; i++) {
			StringBuilder patternBuilder = new StringBuilder();
			for (int j = 0; j < matrix[i].length; j++) {
				patternBuilder.append(matrix[i][j] ^ matrix[i][0]);
			}
			String pattern = patternBuilder.toString();
			Integer frequency = patternFrequency.get(pattern);
			if (frequency == null) {
				patternFrequency.put(pattern, 1);
			} else {
				patternFrequency.put(pattern, frequency + 1);
			}
		}
		return Collections.max(patternFrequency.values());
	}

	private static void check(int[][] mat, int expected) {
		System.out.println("mat is: ");
		for (int[] arr : mat) {
			System.out.println(Arrays.toString(arr));
		}
		System.out.println("expected is: " + expected);
		int maxEqualRowsAfterFlips = maxEqualRowsAfterFlips(mat); // Calls your implementation
		System.out.println("maxEqualRowsAfterFlips is: " + maxEqualRowsAfterFlips);
	}

}
