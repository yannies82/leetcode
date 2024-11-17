package leetcode.matrix;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CreateValidSudoku {

	public static void main(String[] args) {
		check();
	}

	/**
	 * This algorithm creates a valid Sudoku board.
	 * 
	 * @return
	 */
	public static char[][] createValidSudoku() {
		List<Character> chars = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9');
		// add some randomness - by yannies!
		Collections.shuffle(chars);
		int length = chars.size();
		char[][] board = new char[length][length];
		for (int i = 0; i < length; i++) {
			int charOffsetRow = i / 3;
			int charOffsetColumn = i % 3;
			for (int j = 0; j < 3; j++) {
				int sectionOffsetRow = 3 * j;
				for (int k = 0; k < 3; k++) {
					int sectionOffsetColumn = 3 * k;
					int positionOffset = (j + k) % 3;
					board[sectionOffsetRow + (charOffsetRow + positionOffset) % 3][sectionOffsetColumn
							+ (charOffsetColumn + positionOffset) % 3] = chars.get(i);
				}
			}
		}
		return board;
	}

	public static char[][] createValidSudoku2() {
		char[] chars = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		char[][] board = new char[chars.length][chars.length];
		for (int i = 0; i < chars.length; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					board[3 * j + (i / 3 + (j + k) % 3) % 3][3 * k + (i % 3 + (j + k) % 3) % 3] = chars[i];
				}
			}
		}
		return board;
	}

	private static void check() {
		char[][] createValidSudoku = createValidSudoku(); // Calls your implementation
		System.out.println("createValidSudoku is: ");
		for (int i = 0; i < createValidSudoku.length; i++) {
			System.out.println(Arrays.toString(createValidSudoku[i]));
		}
		System.out.println("isValidSudoku is: " + ValidSudoku.isValidSudoku(createValidSudoku));
		char[][] createValidSudoku2 = createValidSudoku2(); // Calls your implementation
		System.out.println("createValidSudoku2 is: ");
		for (int i = 0; i < createValidSudoku2.length; i++) {
			System.out.println(Arrays.toString(createValidSudoku2[i]));
		}
		System.out.println("isValidSudoku2 is: " + ValidSudoku.isValidSudoku(createValidSudoku2));
	}
}
