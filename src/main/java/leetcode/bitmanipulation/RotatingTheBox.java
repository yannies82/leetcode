package leetcode.bitmanipulation;

import java.util.Arrays;

public class RotatingTheBox {

	public static void main(String[] args) {
		check(new char[][] { { '#', '.', '#' } }, new char[][] { { '.' }, { '#' }, { '#' } });
		check(new char[][] { { '#', '.', '*', '.' }, { '#', '#', '*', '.' } },
				new char[][] { { '#', '.' }, { '#', '#' }, { '*', '*' }, { '.', '.' } });
		check(new char[][] { { '#', '#', '*', '.', '*', '.' }, { '#', '#', '#', '*', '.', '.' },
				{ '#', '#', '#', '.', '#', '.' } },
				new char[][] { { '.', '#', '#' }, { '.', '#', '#' }, { '#', '#', '*' }, { '#', '*', '.' },
						{ '#', '.', '*' }, { '#', '.', '.' } });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/rotating-the-box. Time
	 * complexity is O(m*n) where m is the number of rows and n the number of
	 * columns in box.
	 * 
	 * @param box
	 * @return
	 */
	public static char[][] rotateTheBox(char[][] box) {
		int m = box.length;
		int n = box[0].length;
		int lastRowIndex = m - 1;
		int lastColumnIndex = n - 1;
		char[][] result = new char[n][m];
		for (int i = 0; i < m; i++) {
			int targetColumnIndex = lastRowIndex - i;
			int targetRowIndex = lastColumnIndex;
			for (int j = lastColumnIndex; j >= 0; j--) {
				result[j][targetColumnIndex] = '.';
				if (box[i][j] != '.') {
					targetRowIndex = box[i][j] == '*' ? j : targetRowIndex;
					result[targetRowIndex--][targetColumnIndex] = box[i][j];
				}
			}
		}
		return result;
	}

	private static void check(char[][] box, char[][] expected) {
		System.out.println("box is: ");
		for (char[] row : box) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println("expected is: ");
		for (char[] row : expected) {
			System.out.println(Arrays.toString(row));
		}
		char[][] rotateTheBox = rotateTheBox(box); // Calls your implementation
		System.out.println("rotateTheBox is: ");
		for (char[] row : rotateTheBox) {
			System.out.println(Arrays.toString(row));
		}
	}

}
