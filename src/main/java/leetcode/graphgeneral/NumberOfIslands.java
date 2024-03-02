package leetcode.graphgeneral;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class NumberOfIslands {

	public static void main(String[] args) {
		char[][] grid1 = { { '1', '1', '1', '1', '0' }, { '1', '1', '0', '1', '0' }, { '1', '1', '0', '0', '0' },
				{ '0', '0', '0', '0', '0' } };
		check(grid1, 1);
		char[][] grid2 = { { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' }, { '0', '0', '1', '0', '0' },
				{ '0', '0', '0', '1', '1' } };
		check(grid2, 3);
	}

	/**
	 * Solution with recursive DFS graph traversal.
	 * 
	 * @param grid
	 * @return
	 */
	public static int numIslands(char[][] grid) {
		int rowCount = grid.length;
		int columnCount = grid[0].length;
		// keeps track of the visited nodes
		boolean[][] visited = new boolean[rowCount][columnCount];
		// keeps count of the different islands
		int count = 0;
		// traverse all nodes
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				// for every '1' node that is not visited yet
				// increase the islands count and perform DFS
				// in order to mark adjacent '1' nodes as visited
				if (grid[i][j] == '1' && !visited[i][j]) {
					count++;
					performDfsRecursive(grid, visited, i, j);
				}
			}
		}
		return count;
	}

	private static void performDfsRecursive(char[][] grid, boolean[][] visited, int i, int j) {
		// mark current node as visited
		visited[i][j] = true;
		// perform DFS on the above node if it contains '1' and is not visited yet
		if (i > 0 && grid[i - 1][j] == '1' && !visited[i - 1][j]) {
			performDfsRecursive(grid, visited, i - 1, j);
		}
		// perform DFS on the below node if it contains '1' and is not visited yet
		if (i < grid.length - 1 && grid[i + 1][j] == '1' && !visited[i + 1][j]) {
			performDfsRecursive(grid, visited, i + 1, j);
		}
		// perform DFS on the left node if it contains '1' and is not visited yet
		if (j > 0 && grid[i][j - 1] == '1' && !visited[i][j - 1]) {
			performDfsRecursive(grid, visited, i, j - 1);
		}
		// perform DFS on the right node if it contains '1' and is not visited yet
		if (j < grid[0].length - 1 && grid[i][j + 1] == '1' && !visited[i][j + 1]) {
			performDfsRecursive(grid, visited, i, j + 1);
		}
	}

	/**
	 * Solution with iterative DFS graph traversal.
	 * 
	 * @param grid
	 * @return
	 */
	public static int numIslands2(char[][] grid) {
		int rowCount = grid.length;
		int columnCount = grid[0].length;
		// keeps track of the visited nodes
		boolean[][] visited = new boolean[rowCount][columnCount];
		// keeps the nodes for DFS traversal of the graph
		Deque<Node> stack = new ArrayDeque<>();
		// keeps count of the different islands
		int count = 0;
		// traverse all nodes
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				// for every '1' node that is not visited yet
				// increase the islands count and perform DFS
				// in order to mark adjacent '1' nodes as visited
				if (grid[i][j] == '1' && !visited[i][j]) {
					count++;
					performDfs(grid, visited, stack, i, j);
				}
			}
		}
		return count;
	}

	private static void performDfs(char[][] grid, boolean[][] visited, Deque<Node> stack, int i, int j) {
		stack.offerFirst(new Node(i, j));
		while (!stack.isEmpty()) {
			Node current = stack.poll();
			// mark current node as visited
			visited[current.row][current.column] = true;
			// put above node in stack if it contains '1' and is not visited yet
			if (current.row > 0 && grid[current.row - 1][current.column] == '1'
					&& !visited[current.row - 1][current.column]) {
				stack.offerFirst(new Node(current.row - 1, current.column));
			}
			// put below node in stack if it contains '1' and is not visited yet
			if (current.row < grid.length - 1 && grid[current.row + 1][current.column] == '1'
					&& !visited[current.row + 1][current.column]) {
				stack.offerFirst(new Node(current.row + 1, current.column));
			}
			// put left node in stack if it contains '1' and is not visited yet
			if (current.column > 0 && grid[current.row][current.column - 1] == '1'
					&& !visited[current.row][current.column - 1]) {
				stack.offerFirst(new Node(current.row, current.column - 1));
			}
			// put right node in stack if it contains '1' and is not visited yet
			if (current.column < grid[0].length - 1 && grid[current.row][current.column + 1] == '1'
					&& !visited[current.row][current.column + 1]) {
				stack.offerFirst(new Node(current.row, current.column + 1));
			}
		}
	}

	private static class Node {
		int row;
		int column;

		public Node(int row, int column) {
			super();
			this.row = row;
			this.column = column;
		}
	}

	private static void check(char[][] grid, int expected) {
		System.out.println("grid is: ");
		for (int i = 0; i < grid.length; i++) {
			System.out.println(Arrays.toString(grid[i]));
		}
		System.out.println("expected is: " + expected);
		int numIslands = numIslands(grid); // Calls your implementation
		System.out.println("numIslands is: " + numIslands);
	}
}
