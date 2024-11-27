package leetcode.matrix;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SlidingPuzzle {

	public static void main(String[] args) {
		int[][] board1 = { { 1, 2, 3 }, { 4, 0, 5 } };
		check(board1, 1);
		int[][] board2 = { { 1, 2, 3 }, { 5, 4, 0 } };
		check(board2, -1);
		int[][] board3 = { { 4, 1, 2 }, { 5, 0, 3 } };
		check(board3, 5);
	}

	// all possible moves for every position of 0
	private static int[][] ALLOWED_MOVES = { { 1, 3 }, { 0, 2, 4 }, { 1, 5 }, { 0, 4 }, { 1, 3, 5 }, { 2, 4 } };
	// factors for for every position of 0
	private static int[] FACTORS = { 100000, 10000, 1000, 100, 10, 1 };
	// the target number to be reached
	private static int TARGET = 123450;

	/**
	 * Leetcode problem: https://leetcode.com/problems/sliding-puzzle. This solution
	 * considers the board as an initial state represented by an integer and
	 * performs BFS until the target integer is reached or no moves are available.
	 * Time complexity is O((m*n)!) where m is the number of rows and n is the
	 * number of columns in the board.
	 * 
	 * @param board
	 * @return
	 */
	public static int slidingPuzzle(int[][] board) {
		// keeps visited numbers so that they are not added to the queue again
		Set<Integer> visited = new HashSet<>();
		// custom queue to keep all possible combinations along with the position of 0
		// all possible combinations are 720 = 6! since the board has 6 positions
		int[][] queue = new int[720][2];
		int tail = queue.length - 1;
		int head = tail;
		// convert board to integer, keep the initial position of 0
		int zeroIndex = -1;
		int start = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				start = start * 10 + board[i][j];
				if (board[i][j] == 0) {
					zeroIndex = i * board[0].length + j;
				}
			}
		}
		queue[tail][0] = start;
		queue[tail][1] = zeroIndex;
		visited.add(start);
		int count = 0;

		// perform BFS until target is reached or we are out of moves
		while (tail <= head) {
			int size = head - tail + 1;
			for (int i = 0; i < size; i++) {
				// pop the first element from the queue head
				int[] current = queue[head--];
				if (current[0] == TARGET) {
					// target number is reached
					return count;
				}
				zeroIndex = current[1];
				// iterate next moves for current index of 0 and add to queue
				for (int j = 0; j < ALLOWED_MOVES[zeroIndex].length; j++) {
					int nextIndex = ALLOWED_MOVES[zeroIndex][j];
					// given the next position of 0 generate the next number from the current one
					int nextDigit = (current[0] / FACTORS[nextIndex]) % 10;
					int nextNumber = current[0] + nextDigit * (FACTORS[zeroIndex] - FACTORS[nextIndex]);
					if (!visited.contains(nextNumber)) {
						// only add to the queue numbers which are not visited yet
						visited.add(nextNumber);
						queue[--tail][0] = nextNumber;
						queue[tail][1] = nextIndex;
					}
				}
			}
			// increase number of moves
			count++;
		}
		// it is not possible to reach the target
		return -1;
	}

	private static void check(int[][] board, int expected) {
		System.out.println("board is: ");
		for (int i = 0; i < board.length; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
		System.out.println("expected is: " + expected);
		int slidingPuzzle = slidingPuzzle(board); // Calls your implementation
		System.out.println("slidingPuzzle is: " + slidingPuzzle);
	}
}
