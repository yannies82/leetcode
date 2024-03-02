package leetcode.graphbfs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class SnakesAndLadders {

	public static void main(String[] args) {
		int[][] board0 = { { -1, -1, -1 }, { -1, 9, 8 }, { -1, 8, 9 } };
		check(board0, 1);
		int[][] board1 = { { -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 },
				{ -1, 35, -1, -1, 13, -1 }, { -1, -1, -1, -1, -1, -1 }, { -1, 15, -1, -1, -1, -1 } };
		check(board1, 4);
		int[][] board2 = { { 1, 1, -1 }, { 1, 1, 1 }, { -1, 1, 1 } };
		check(board2, -1);
		int[][] board3 = { { -1, 1, 1, 1 }, { -1, 7, 1, 1 }, { 1, 1, 1, 1 }, { -1, 1, 9, 1 } };
		check(board3, -1);
		int[][] board4 = { { -1, 1, 1, 1 }, { -1, 7, 1, 1 }, { 16, 1, 1, 1 }, { -1, 1, 9, 1 } };
		check(board4, 3);
	}

	/**
	 * This solution uses a map for storing the indexes of the snakes and ladders
	 * and their target values and BFS traversal until the target index is reached.
	 * 
	 * @param board
	 * @return
	 */
	public static int snakesAndLadders(int[][] board) {
		int length = board.length;
		// construct and populate a map which contains as keys
		// the indexes of snakes or ladders and as values the indexes they lead to
		Map<Integer, Integer> snakesAndLaddersMap = new HashMap<>();
		for (int i = length - 1; i >= 0; i--) {
			int realRow = length - 1 - i;
			for (int j = 0; j < length; j++) {
				if (board[i][j] != -1) {
					// if the board contains a snake or ladder at this coordinate
					// calculate the real index and put it in the map
					// along with the target index
					int realColumn = realRow % 2 == 0 ? j : length - 1 - j;
					int realIndex = realRow * length + realColumn + 1;
					snakesAndLaddersMap.put(realIndex, board[i][j]);
				}
			}
		}
		// perform BFS and return the result
		return performBFS(snakesAndLaddersMap, length * length);
	}

	private static int performBFS(Map<Integer, Integer> snakesAndLaddersMap, int targetPosition) {
		// initialize positions queue and add the first position
		Queue<Integer> positions = new ArrayDeque<>();
		positions.offer(1);
		// initialize moves count
		int movesCount = 0;
		// initialize an array which keeps the visited positions
		// so that they are not added to the queue again
		boolean[] visited = new boolean[targetPosition + 1];
		visited[1] = true;
		while (!positions.isEmpty()) {
			// increase moves count for the nth possible move
			movesCount++;
			// remove all nth positions and put in the queue all possible n + 1 level
			// positions
			int positionsCount = positions.size();
			for (int i = 0; i < positionsCount; i++) {
				// remove possible nth level position from the queue
				int currentPosition = positions.poll();
				// examine the (up to) 6 next positions
				int nextPosition = currentPosition;
				for (int j = 1; j <= 6; j++) {
					// calculate the possible next position, if there is a snake or ladder
					// there get the final position
					int possiblePosition = currentPosition + j;
					boolean isSnakeOrLadder = snakesAndLaddersMap.containsKey(possiblePosition);
					if (isSnakeOrLadder) {
						possiblePosition = snakesAndLaddersMap.get(possiblePosition);
					}
					// do not put visited positions in the queue again
					if (visited[possiblePosition]) {
						continue;
					}
					if (possiblePosition == targetPosition) {
						// end game
						return movesCount;
					} else if (isSnakeOrLadder) {
						// if the next possible position is a snake or ladder, put it in the queue
						positions.offer(possiblePosition);
					} else {
						// avoid putting all of the possible next positions in the queue
						// instead put all snakes and ladders and the greatest one of the rest
						nextPosition = possiblePosition;
					}
					// consider the position as visited so that it is not put in the queue again
					visited[possiblePosition] = true;
				}
				if (nextPosition != currentPosition) {
					// put the greatest non snake or ladder position in the queue
					positions.offer(nextPosition);
				}
			}
		}
		// we are out of positions, the game cannot be ended
		return -1;
	}

	private static void check(int[][] board, int expected) {
		System.out.println("board is: ");
		for (int i = 0; i < board.length; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
		System.out.println("expected is: " + expected);
		int snakesAndLadders = snakesAndLadders(board); // Calls your implementation
		System.out.println("snakesAndLadders is: " + snakesAndLadders);
	}
}
