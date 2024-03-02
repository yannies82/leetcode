package leetcode.matrix;

import java.util.Arrays;

public class GameOfLife {

	public static void main(String[] args) {
		check(new int[][] { { 0, 1, 0 }, { 0, 0, 1 }, { 1, 1, 1 }, { 0, 0, 0 } },
				new int[][] { { 0, 0, 0 }, { 1, 0, 1 }, { 0, 1, 1 }, { 0, 1, 0 } });
		check(new int[][] { { 1, 1 }, { 1, 0 } }, new int[][] { { 1, 1 }, { 1, 1 } });
	}

	public static void gameOfLife(int[][] board) {
		int rowCount = board.length;
		int columnCount = board[0].length;
		int[][] boardBuffer = new int[2][columnCount];
		int state;
		int liveCells;
		int bufferIndex;
		for (int i = 0; i < rowCount; i++) {
			bufferIndex = i % 2;
			for (int j = 0; j < columnCount; j++) {
				state = board[i][j];
				liveCells = 0;
				if (j > 0) {
					liveCells += board[i][j - 1];
				}
				if (j < columnCount - 1) {
					liveCells += board[i][j + 1];
				}
				if (i > 0) {
					liveCells += board[i - 1][j];
				}
				if (i < rowCount - 1) {
					liveCells += board[i + 1][j];
				}
				if (j > 0 && i > 0) {
					liveCells += board[i - 1][j - 1];
				}
				if (j > 0 && i < rowCount - 1) {
					liveCells += board[i + 1][j - 1];
				}
				if (j < columnCount - 1 && i > 0) {
					liveCells += board[i - 1][j + 1];
				}
				if (j < columnCount - 1 && i < rowCount - 1) {
					liveCells += board[i + 1][j + 1];
				}
				if (state == 0) {
					if (liveCells == 3) {
						state = 1;
					}
				} else if (liveCells < 2 || liveCells > 3) {
					state = 0;
				}
				if (i >= 2) {
					board[i - 2][j] = boardBuffer[bufferIndex][j];
				}
				boardBuffer[bufferIndex][j] = state;
			}
		}
		board[rowCount - 1] = boardBuffer[(rowCount + 1) % 2];
		if (rowCount > 1) {
			board[rowCount - 2] = boardBuffer[(rowCount) % 2];
		}
	}

	public static void gameOfLife2(int[][] board) {
		int rowCount = board.length;
		int columnCount = board[0].length;
		int[][] boardCopy = new int[rowCount][];
		for (int i = 0; i < rowCount; i++) {
			boardCopy[i] = new int[columnCount];
			System.arraycopy(board[i], 0, boardCopy[i], 0, columnCount);
		}
		int state;
		int liveCells;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				state = boardCopy[i][j];
				liveCells = 0;
				if (j > 0) {
					liveCells += boardCopy[i][j - 1];
				}
				if (j < columnCount - 1) {
					liveCells += boardCopy[i][j + 1];
				}
				if (i > 0) {
					liveCells += boardCopy[i - 1][j];
				}
				if (i < rowCount - 1) {
					liveCells += boardCopy[i + 1][j];
				}
				if (j > 0 && i > 0) {
					liveCells += boardCopy[i - 1][j - 1];
				}
				if (j > 0 && i < rowCount - 1) {
					liveCells += boardCopy[i + 1][j - 1];
				}
				if (j < columnCount - 1 && i > 0) {
					liveCells += boardCopy[i - 1][j + 1];
				}
				if (j < columnCount - 1 && i < rowCount - 1) {
					liveCells += boardCopy[i + 1][j + 1];
				}
				if (state == 0) {
					if (liveCells == 3) {
						board[i][j] = 1;
					}
				} else if (liveCells < 2 || liveCells > 3) {
					board[i][j] = 0;
				}
			}
		}
	}

	public static void gameOfLife3(int[][] board) {
		int rowCount = board.length;
		int columnCount = board[0].length;
		int[][] boardBuffer = new int[2][columnCount];
		int state;
		int liveCells;
		int bufferIndex;
		for (int i = 0; i < rowCount; i++) {
			bufferIndex = i % 2;
			for (int j = 0; j < columnCount; j++) {
				state = board[i][j];
				liveCells = -state;
				for (int k = -1; k <= 1; k++) {
					for (int l = -1; l <= 1; l++) {
						if (i + k >= 0 && i + k < rowCount && j + l >= 0 && j + l < columnCount) {
							liveCells += board[i + k][j + l];
						}
					}
				}
				if (state == 0) {
					if (liveCells == 3) {
						state = 1;
					}
				} else if (liveCells < 2 || liveCells > 3) {
					state = 0;
				}
				if (i >= 2) {
					board[i - 2][j] = boardBuffer[bufferIndex][j];
				}
				boardBuffer[bufferIndex][j] = state;
			}
		}
		board[rowCount - 1] = boardBuffer[(rowCount + 1) % 2];
		if (rowCount > 1) {
			board[rowCount - 2] = boardBuffer[(rowCount) % 2];
		}
	}

	private static void check(int[][] board, int[][] expectedBoard) {
		System.out.println("board is: ");
		for (int i = 0; i < board.length; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
		System.out.println("expectedBoard is: ");
		for (int i = 0; i < expectedBoard.length; i++) {
			System.out.println(Arrays.toString(expectedBoard[i]));
		}
		gameOfLife(board); // Calls your implementation
		System.out.println("gameOfLife is: ");
		for (int i = 0; i < board.length; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
	}
}
