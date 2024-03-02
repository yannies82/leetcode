package leetcode.backtracking;

import java.util.Arrays;

public class WordSearch {

	public static void main(String[] args) {
		char[][] board0 = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
		String word0 = "ABCCED";
		check(board0, word0, true);
		char[][] board1 = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
		String word1 = "SEE";
		check(board1, word1, true);
	}

	/**
	 * This solution traverses the board starting from every position and using
	 * backtracking. For backtracking a utility array is used for marking the
	 * visited positions for the current traversal. Time complexity is O(N * M * 4 *
	 * W) where N is the number of board rows, M is the number of board columns and
	 * W is the length of the word to find.
	 * 
	 * @param board
	 * @param word
	 * @return
	 */
	public static boolean exist(char[][] board, String word) {
		// keeps the visited positions for the current traversal
		boolean[][] visited = new boolean[board.length][board[0].length];
		// using every position on the board as starting position
		// recursively search for the word
		boolean result = false;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				result = result || findRecursive(board, word, visited, i, j, 0);
			}
		}
		return result;
	}

	private static boolean findRecursive(char[][] board, String word, boolean[][] visited, int i, int j, int level) {
		// return false if the character at the current position is different from the
		// expected word character
		if (board[i][j] != word.charAt(level)) {
			return false;
		}
		// return true if this is the last level (the last word character matched)
		if (level == word.length() - 1) {
			return true;
		} else {
			// this was not the last character of the word
			// search recursively every available neighbouring position
			// mark position as visited
			visited[i][j] = true;
			if (i > 0 && !visited[i - 1][j] && findRecursive(board, word, visited, i - 1, j, level + 1)) {
				return true;
			}
			if (i < board.length - 1 && !visited[i + 1][j]
					&& findRecursive(board, word, visited, i + 1, j, level + 1)) {
				return true;
			}
			if (j > 0 && !visited[i][j - 1] && findRecursive(board, word, visited, i, j - 1, level + 1)) {
				return true;
			}
			if (j < board[0].length - 1 && !visited[i][j + 1]
					&& findRecursive(board, word, visited, i, j + 1, level + 1)) {
				return true;
			}
			// unmark position from visited for backtracking
			visited[i][j] = false;
		}
		return false;
	}

	private static void check(char[][] board, String word, boolean expected) {
		System.out.println("board is: ");
		for (char[] row : board) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println("word is: " + word);
		System.out.println("expected is: " + expected);
		boolean exist = exist(board, word); // Calls your implementation
		System.out.println("exist is: " + exist);
	}
}
