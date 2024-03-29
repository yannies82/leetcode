package leetcode.graphgeneral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SurroundedRegions {

	public static void main(String[] args) {
		char[][] board1 = { { 'X', 'X', 'X', 'X', 'O', 'X' }, { 'O', 'X', 'X', 'O', 'O', 'X' },
				{ 'X', 'O', 'X', 'O', 'O', 'O' }, { 'X', 'O', 'O', 'O', 'X', 'O' }, { 'O', 'O', 'X', 'X', 'O', 'X' },
				{ 'X', 'O', 'X', 'O', 'X', 'X' } };
		char[][] expected1 = { { 'X', 'X', 'X', 'X', 'O', 'X' }, { 'O', 'X', 'X', 'O', 'O', 'X' },
				{ 'X', 'O', 'X', 'O', 'O', 'O' }, { 'X', 'O', 'O', 'O', 'X', 'O' }, { 'O', 'O', 'X', 'X', 'O', 'X' },
				{ 'X', 'O', 'X', 'O', 'X', 'X' } };
		check(board1, expected1);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/surrounded-regions. This
	 * solution performs flood fill and DFS. Time complexity is O(m * n) where m is
	 * the number of rows and n is the number of columns in the grid.
	 * 
	 * @param board
	 */
	public static void solve(char[][] board) {
		int rowCount = board.length;
		int columnCount = board[0].length;
		// replace all 'O' nodes with intermediate char 'Y
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (board[i][j] == 'O') {
					board[i][j] = 'Y';
				}
			}
		}
		// if the outer nodes contain the intermediate char, perform DFS and replace
		// with 'O'
		for (int i = 0; i < rowCount; i++) {
			if (board[i][0] == 'Y') {
				performFill(board, i, 0);
			}
			if (board[i][columnCount - 1] == 'Y') {
				performFill(board, i, columnCount - 1);
			}
		}
		for (int j = 1; j < columnCount - 1; j++) {
			if (board[0][j] == 'Y') {
				performFill(board, 0, j);
			}
			if (board[rowCount - 1][j] == 'Y') {
				performFill(board, rowCount - 1, j);
			}
		}
		// replace all 'Y' nodes with 'X'
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (board[i][j] == 'Y') {
					board[i][j] = 'X';
				}
			}
		}
	}

	private static void performFill(char[][] board, int i, int j) {
		board[i][j] = 'O';
		// perform DFS on the above node if it contains 'Y'
		if (i > 0 && board[i - 1][j] == 'Y') {
			performFill(board, i - 1, j);
		}
		// perform DFS on the below node if it contains 'Y'
		if (i < board.length - 1 && board[i + 1][j] == 'Y') {
			performFill(board, i + 1, j);
		}
		// perform DFS on the left node if it contains 'Y'
		if (j > 0 && board[i][j - 1] == 'Y') {
			performFill(board, i, j - 1);
		}
		// perform DFS on the right node if it contains 'Y'
		if (j < board[0].length - 1 && board[i][j + 1] == 'Y') {
			performFill(board, i, j + 1);
		}
	}

	/**
	 * Solution with recursive DFS graph traversal. Finds all 'O' islands which do
	 * not have outer nodes and replaces their nodes with 'X'.
	 * 
	 * @param grid
	 * @return
	 */
	public static void solve2(char[][] board) {
		int rowCount = board.length;
		int columnCount = board[0].length;
		// keeps track of the visited nodes
		boolean[][] visited = new boolean[rowCount][columnCount];
		List<Integer> regionRowIndexes = new ArrayList<>();
		List<Integer> regionColumnIndexes = new ArrayList<>();
		// traverse all nodes except for outer ones
		for (int i = 1; i < rowCount - 1; i++) {
			for (int j = 1; j < columnCount - 1; j++) {
				// for every 'O' node that is not visited yet
				// perform DFS in order to check if adjacent
				// 'O' nodes as inner or outer
				if (board[i][j] == 'O' && !visited[i][j]) {
					regionRowIndexes.clear();
					regionColumnIndexes.clear();
					// returns true if the region does not contain outer nodes and should be flipped
					if (performDfsRecursive(board, visited, i, j, true, regionRowIndexes, regionColumnIndexes)) {
						for (int k = 0; k < regionRowIndexes.size(); k++) {
							board[regionRowIndexes.get(k)][regionColumnIndexes.get(k)] = 'X';
						}
					}
				}
			}
		}
	}

	private static boolean performDfsRecursive(char[][] board, boolean[][] visited, int i, int j, boolean currentResult,
			List<Integer> regionRowIndexes, List<Integer> regionColumnIndexes) {
		if (i == 0 || i == board.length - 1 || j == 0 || j == board[0].length - 1) {
			// an outer node was found for this region, the region is
			// not surrounded
			return false;
		}
		// mark current node as visited
		visited[i][j] = true;
		boolean result = currentResult;
		// perform DFS on the above node if it contains 'O' and is not visited yet
		if (board[i - 1][j] == 'O' && !visited[i - 1][j]) {
			result = performDfsRecursive(board, visited, i - 1, j, result, regionRowIndexes, regionColumnIndexes)
					&& result;
		}
		// perform DFS on the below node if it contains 'O' and is not visited yet
		if (board[i + 1][j] == 'O' && !visited[i + 1][j]) {
			result = performDfsRecursive(board, visited, i + 1, j, result, regionRowIndexes, regionColumnIndexes)
					&& result;
		}
		// perform DFS on the left node if it contains '1' and is not visited yet
		if (board[i][j - 1] == 'O' && !visited[i][j - 1]) {
			result = performDfsRecursive(board, visited, i, j - 1, result, regionRowIndexes, regionColumnIndexes)
					&& result;
		}
		// perform DFS on the right node if it contains '1' and is not visited yet
		if (board[i][j + 1] == 'O' && !visited[i][j + 1]) {
			result = performDfsRecursive(board, visited, i, j + 1, result, regionRowIndexes, regionColumnIndexes)
					&& result;
		}
		if (result) {
			// keep current region node coordinates in list
			regionRowIndexes.add(i);
			regionColumnIndexes.add(j);
		}
		return result;
	}

	private static void check(char[][] board, char[][] expected) {
		System.out.println("board is: ");
		for (int i = 0; i < board.length; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
		System.out.println("expected is: ");
		for (int i = 0; i < expected.length; i++) {
			System.out.println(Arrays.toString(expected[i]));
		}
		solve(board); // Calls your implementation
		System.out.println("solve is: ");
		for (int i = 0; i < board.length; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
	}
}
