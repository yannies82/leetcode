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

	public static boolean isValidSudoku(char[][] board) {
		int length = board.length;
		boolean[][][] checks = new boolean[3][length][10];
		int index;
		int iSection;
		int sectionIndex;
		for (int i = 0; i < length; i++) {
			iSection = 3 * (i / 3);
			for (int j = 0; j < length; j++) {
				if (board[i][j] == '.') {
					continue;
				}
				index = board[i][j] - '0';
				sectionIndex = iSection + j / 3;
				if (checks[0][i][index] || checks[1][j][index] || checks[2][sectionIndex][index]) {
					return false;
				}
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
