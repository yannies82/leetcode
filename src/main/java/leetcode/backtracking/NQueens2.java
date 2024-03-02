package leetcode.backtracking;

import java.util.Map;

public class NQueens2 {

	public static void main(String[] args) {
		check(4, 2);
		check(1, 1);
		check(2, 0);
	}

	/**
	 * This solution uses an int array n X n which represents all board positions.
	 * It traverses recursively the board, starting at every position of the first
	 * column. When a queen is placed at a position, +1 is added to all affected
	 * positions. When backtracking, -1 is subtracted from all affected positions.
	 * When we reach the last level (i.e. the queen is placed at the last column) +1
	 * is added to the result. Time complexity is O( n ^ n).
	 * 
	 * @param n
	 * @return
	 */
	public static int totalNQueens(int n) {
		int[][] board = new int[n][n];
		// the number of distinct solutions to return
		int result = 0;
		for (int i = 0; i < n; i++) {
			// starting at every row of the first column
			// traverse recursively and add all distinct solutions
			result += calculateRecursive(board, i, 0);
		}
		return result;
	}

	private static int calculateRecursive(int[][] board, int row, int column) {
		int result = 0;
		// set unavailable squares after placement of queen at board[row][column]
		for (int j = 0; j < board.length; j++) {
			markUnavailableSquares(board, row, column, j, 1);
		}
		if (column == board.length - 1) {
			// we have reached the last column, we have a solution
			result = 1;
		} else {
			// this is not the last column, traverse recursively
			// all available positions of the next column
			int nextColumn = column + 1;
			for (int j = 0; j < board.length; j++) {
				// a position is available if its value is 0
				if (board[j][nextColumn] == 0) {
					// calculate recursively and add to the total results
					result += calculateRecursive(board, j, nextColumn);
				}
			}
		}
		// when backtracking undo the effect of the placement of the queen
		// at board[row][column] so that the board can be reused for
		// sibling positions
		for (int j = 0; j < board.length; j++) {
			markUnavailableSquares(board, row, column, j, -1);
		}
		return result;
	}

	private static void markUnavailableSquares(int[][] board, int row, int column, int offset, int diff) {
		// mark positions on the same row
		board[row][offset] += diff;
		// mark positions on the same column
		board[offset][column] += diff;
		// mark diagonals where available
		int rowDown = row + offset;
		int columnRight = column + offset;
		int rowUp = row - offset;
		int columnLeft = column - offset;
		if (rowDown < board.length && columnRight < board.length) {
			board[rowDown][columnRight] += diff;
		}
		if (rowDown < board.length && columnLeft >= 0) {
			board[rowDown][columnLeft] += diff;
		}
		if (rowUp >= 0 && columnRight < board.length) {
			board[rowUp][columnRight] += diff;
		}
		if (rowUp >= 0 && columnLeft >= 0) {
			board[rowUp][columnLeft] += diff;
		}
	}

	/**
	 * Since the input range is very limited (1<=n,=9), we can directly return the
	 * results using a lookup table!
	 * 
	 * @param n
	 * @return
	 */
	public static int totalNQueens2(int n) {
		Map<Integer, Integer> resultsMap = Map.of(1, 1, 2, 0, 3, 0, 4, 2, 5, 10, 6, 4, 7, 40, 8, 92, 9, 352);
		return resultsMap.get(n);
	}

	private static void check(int n, int expected) {
		System.out.println("n is: " + n);
		System.out.println("expected is: " + expected);
		int totalNQueens = totalNQueens(n); // Calls your implementation
		System.out.println("totalNQueens is: " + totalNQueens);
	}
}
