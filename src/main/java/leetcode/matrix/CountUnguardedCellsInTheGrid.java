package leetcode.matrix;

import java.util.Arrays;

public class CountUnguardedCellsInTheGrid {

	public static void main(String[] args) {
		check(4, 6, new int[][] { { 0, 0 }, { 1, 1 }, { 2, 3 } }, new int[][] { { 0, 1 }, { 2, 2 }, { 1, 4 } }, 7);
		check(3, 3, new int[][] { { 1, 1 } }, new int[][] { { 0, 1 }, { 1, 0 }, { 2, 1 }, { 1, 2 } }, 4);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/count-unguarded-cells-in-the-grid. This
	 * solution puts all guards and walls in the grid, then marks the positions
	 * covered by the guards. Finally it iterates all cells and counts the ones
	 * which are still 0. Time complexity is O(m * n).
	 * 
	 * @param m
	 * @param n
	 * @param guards
	 * @param walls
	 * @return
	 */
	public static int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
		int[][] grid = new int[m][n];
		// mark guards as 1s
		for (int i = 0; i < guards.length; i++) {
			grid[guards[i][0]][guards[i][1]] = 1;
		}
		// mark walls as 1s
		for (int i = 0; i < walls.length; i++) {
			grid[walls[i][0]][walls[i][1]] = 1;
		}
		// mark positions covered by guards as 3s
		for (int i = 0; i < guards.length; i++) {
			int[] guard = guards[i];
			for (int y = guard[0] - 1; y >= 0 && grid[y][guard[1]] != 1; y--) {
				grid[y][guard[1]] = 3;
			}
			for (int y = guard[0] + 1; y < m && grid[y][guard[1]] != 1; y++) {
				grid[y][guard[1]] = 3;
			}
			for (int x = guard[1] - 1; x >= 0 && grid[guard[0]][x] != 1; x--) {
				grid[guard[0]][x] = 3;
			}
			for (int x = guard[1] + 1; x < n && grid[guard[0]][x] != 1; x++) {
				grid[guard[0]][x] = 3;
			}
		}
		int count = 0;
		// count cells which are still 0 (unguarded)
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				count += 1 - (grid[i][j] & 1);
			}
		}
		return count;
	}

	private static void check(int m, int n, int[][] guards, int[][] walls, int expected) {
		System.out.println("m is: " + m);
		System.out.println("n is: " + n);
		System.out.println("guards is: ");
		for (int i = 0; i < guards.length; i++) {
			System.out.println(Arrays.toString(guards[i]));
		}
		for (int i = 0; i < walls.length; i++) {
			System.out.println(Arrays.toString(walls[i]));
		}
		System.out.println("expected is: " + expected);
		int countUnguarded = countUnguarded(m, n, guards, walls); // Calls your implementation
		System.out.println("countUnguarded is: " + countUnguarded);
	}
}
