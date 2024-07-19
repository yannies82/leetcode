package leetcode.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LuckyNumbersInAMatrix {

	public static void main(String[] args) {
		check(new int[][] { { 3, 7, 8 }, { 9, 11, 13 }, { 15, 16, 17 } }, List.of(15));
		check(new int[][] { { 1, 10, 4, 2 }, { 9, 3, 8, 7 }, { 15, 16, 17, 12 } }, List.of(12));
		check(new int[][] { { 7, 8 }, { 1, 2 } }, List.of(7));
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/lucky-numbers-in-a-matrix.
	 * Time complexity is O(n*m) where n is the number of rows and m is the number
	 * of columns.
	 * 
	 * @param matrix
	 * @return
	 */
	public static List<Integer> luckyNumbers(int[][] matrix) {
		int[] minPerRow = new int[matrix.length];
		int[] maxPerColumn = new int[matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			minPerRow[i] = Integer.MAX_VALUE;
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] < minPerRow[i]) {
					minPerRow[i] = matrix[i][j];
				}
				if (matrix[i][j] > maxPerColumn[j]) {
					maxPerColumn[j] = matrix[i][j];
				}
			}
		}
		List<Integer> result = new ArrayList<>();
		Set<Integer> minSet = new HashSet<>();
		for (int i = 0; i < minPerRow.length; i++) {
			minSet.add(minPerRow[i]);
		}
		for (int j = 0; j < maxPerColumn.length; j++) {
			if (minSet.contains(maxPerColumn[j])) {
				result.add(maxPerColumn[j]);
			}
		}
		return result;
	}

	private static void check(int[][] matrix, List<Integer> expected) {
		System.out.println("grid is: ");
		for (int i = 0; i < matrix.length; i++) {
			System.out.println(Arrays.toString(matrix[i]));
		}
		System.out.println("expected is: " + expected);
		List<Integer> luckyNumbers = luckyNumbers(matrix); // Calls your implementation
		System.out.println("luckyNumbers is: " + luckyNumbers);
	}
}
