package leetcode.graphgeneral;

import java.util.Arrays;

public class MaximumNumberOfMovesInAGrid {

	public static void main(String[] args) {
		int[][] grid1 = { { 2, 4, 3, 5 }, { 5, 4, 9, 3 }, { 3, 4, 2, 11 }, { 10, 9, 13, 15 } };
		check(grid1, 3);
		int[][] grid2 = { { 3, 2, 4 }, { 2, 1, 9 }, { 1, 1, 7 } };
		check(grid2, 0);
		int[][] grid3 = {
				{ 1000000, 92910, 1021, 1022, 1023, 1024, 1025, 1026, 1027, 1028, 1029, 1030, 1031, 1032, 1033, 1034,
						1035, 1036, 1037, 1038, 1039, 1040, 1041, 1042, 1043, 1044, 1045, 1046, 1047, 1048, 1049, 1050,
						1051, 1052, 1053, 1054, 1055, 1056, 1057, 1058, 1059, 1060, 1061, 1062, 1063, 1064, 1065, 1066,
						1067, 1068 },
				{ 1069, 1070, 1071, 1072, 1073, 1074, 1075, 1076, 1077, 1078, 1079, 1080, 1081, 1082, 1083, 1084, 1085,
						1086, 1087, 1088, 1089, 1090, 1091, 1092, 1093, 1094, 1095, 1096, 1097, 1098, 1099, 1100, 1101,
						1102, 1103, 1104, 1105, 1106, 1107, 1108, 1109, 1110, 1111, 1112, 1113, 1114, 1115, 1116, 1117,
						1118 } };
		check(grid3, 49);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/maximum-number-of-moves-in-a-grid. This
	 * solution uses backtracking to check every possible path and keeps count of
	 * the max number of moves. Time complexity is O(3^n) where n is the number of
	 * columns in the grid.
	 * 
	 * @param grid
	 * @return
	 */
	public static int maxMoves(int[][] grid) {
		int max = 0;
		// keeps track of visited positions in order to avoid calculating the path
		// lengths more than once
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		for (int i = 0; i < grid.length; i++) {
			// try all possible starting positions
			int[] maxMoves = { 0 };
			traverse(grid, i, 0, 0, maxMoves, visited);
			if (maxMoves[0] > max) {
				// update the max value if required
				max = maxMoves[0];
			}
		}
		return max;
	}

	private static void traverse(int[][] grid, int i, int j, int moves, int[] maxMoves, boolean[][] visited) {
		if (moves > maxMoves[0]) {
			// update max value for this starting position
			maxMoves[0] = moves;
		}
		int nextColumn;
		if (visited[i][j] || (nextColumn = j + 1) == grid[0].length) {
			// early exit if the position has already been visited or
			// if this is the last column
			return;
		}
		visited[i][j] = true;
		if (i > 0 && grid[i - 1][nextColumn] > grid[i][j]) {
			traverse(grid, i - 1, nextColumn, moves + 1, maxMoves, visited);
		}
		if (grid[i][nextColumn] > grid[i][j]) {
			traverse(grid, i, nextColumn, moves + 1, maxMoves, visited);
		}
		if (i < grid.length - 1 && grid[i + 1][nextColumn] > grid[i][j]) {
			traverse(grid, i + 1, nextColumn, moves + 1, maxMoves, visited);
		}
	}

	private static void check(int[][] grid, int expected) {
		System.out.println("grid is: ");
		for (int i = 0; i < grid.length; i++) {
			System.out.println(Arrays.toString(grid[i]));
		}
		System.out.println("expected is: " + expected);
		int maxMoves = maxMoves(grid); // Calls your implementation
		System.out.println("maxMoves is: " + maxMoves);
	}
}
