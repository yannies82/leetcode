package leetcode.graphbfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllGroupsOfFarmland {

	public static void main(String[] args) {
		int[][] land1 = { { 1, 0, 0 }, { 0, 1, 1 }, { 0, 1, 1 } };
		int[][] coordinates1 = { { 0, 0, 0, 0 }, { 1, 1, 2, 2 } };
		check(land1, coordinates1);
		int[][] land2 = { { 1, 1 }, { 1, 1 } };
		int[][] coordinates2 = { { 0, 0, 1, 1 } };
		check(land2, coordinates2);
		int[][] land3 = { { 0 } };
		int[][] coordinates3 = {};
		check(land3, coordinates3);

	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/find-all-groups-of-farmland.
	 * This solution traverses all cells of the matrix until it finds the upper left
	 * corner of a farmland. It then attempts to find the lower right corner of the
	 * farmland and puts the coordinates in the results list. Time complexity is O(m
	 * * n) where m is the number of rows and n is the number of columns in the land
	 * array.
	 * 
	 * 
	 * @param land
	 * @return
	 */
	public static int[][] findFarmland(int[][] land) {
		int rowCount = land.length;
		int lastRowIndex = rowCount - 1;
		int columnCount = land[0].length;
		int lastColumnIndex = columnCount - 1;
		// keeps the result coordinates
		List<int[]> results = new ArrayList<>();
		// tracks the visited cells
		boolean[][] visited = new boolean[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (land[i][j] == 1 && !visited[i][j]) {
					// this is the upper left cell of a farmland rectangle
					// find the lower right one
					int endI = i;
					int endJ = j;
					while (endI < lastRowIndex && land[endI + 1][endJ] == 1) {
						endI++;
					}
					while (endJ < lastColumnIndex && land[endI][endJ + 1] == 1) {
						endJ++;
					}
					// mark all farmland rectangle cells as visited
					for (int currI = i; currI <= endI; currI++) {
						for (int currJ = j; currJ <= endJ; currJ++) {
							visited[currI][currJ] = true;
						}
					}
					// add rectangle to the results array
					results.add(new int[] { i, j, endI, endJ });
				}
			}
		}
		return results.toArray(new int[results.size()][]);
	}

	private static void check(int[][] land, int[][] expected) {
		System.out.println("land is: ");
		for (int i = 0; i < land.length; i++) {
			System.out.println(Arrays.toString(land[i]));
		}
		System.out.println("expected is: ");
		for (int i = 0; i < expected.length; i++) {
			System.out.println(Arrays.toString(expected[i]));
		}
		int[][] findFarmland = findFarmland(land); // Calls your implementation
		System.out.println("findFarmland is: ");
		for (int i = 0; i < findFarmland.length; i++) {
			System.out.println(Arrays.toString(findFarmland[i]));
		}
	}
}
