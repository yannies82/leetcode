package leetcode.matrix;

import java.util.Arrays;

public class ValidSudoku {

	public static void main(String[] args) {
		char[][] board1 = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
		check(board1, true);
		char[][] board2 = { { '8', '3', '.', '.', '7', '.', '.', '.', '.' },
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
		check(board2, false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/valid-sudoku. This solution
	 * traverses the sudoku board and keeps track of the numbers in the same row,
	 * column and rectangular section. If the same number is encountered twice in
	 * the same row, column or section the board is invalid. Time complexity is O(n
	 * ^ 2) where n is the number of rows and columns (9).
	 * 
	 * @param board
	 * @return
	 */
	public static boolean isValidSudoku(char[][] board) {
		int length = board.length;
		// this array keeps the encountered numbers per row, column and section
		// there are 3 dimensions of tracking (rows, columns, sections), length (9)
		// number of rows, columns and sections and 10 possible numbers for each
		// position (0-9)
		boolean[][][] checks = new boolean[3][length][10];
		// traverse all board rows
		for (int i = 0; i < length; i++) {
			// helps with the calculation of the section index
			int iSection = 3 * (i / 3);
			// traverse all board columns
			for (int j = 0; j < length; j++) {
				if (board[i][j] == '.') {
					// ignore dots
					continue;
				}
				// calculate the index corresponding to the number in the position i, j of the
				// board
				int index = board[i][j] - '0';
				// calculate section index
				int sectionIndex = iSection + j / 3;
				if (checks[0][i][index] || checks[1][j][index] || checks[2][sectionIndex][index]) {
					// return false if this number has appeared again in this row, column or section
					return false;
				}
				// set all 3 indexes (for row, column, section) to true
				checks[0][i][index] = !checks[0][i][index];
				checks[1][j][index] = !checks[1][j][index];
				checks[2][sectionIndex][index] = !checks[2][sectionIndex][index];
			}
		}
		return true;
	}

	private static void check(char[][] board, boolean expectedIsValid) {
		System.out.println("board is: ");
		for (int i = 0; i < board.length; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
		System.out.println("expectedIsValid is: " + expectedIsValid);
		boolean isValid = isValidSudoku(board); // Calls your implementation
		System.out.println("isValid is: " + isValid);
	}
}
