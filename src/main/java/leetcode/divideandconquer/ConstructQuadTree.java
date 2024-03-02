package leetcode.divideandconquer;

import java.util.Arrays;

public class ConstructQuadTree {

	public static void main(String[] args) {
		int[][] grid0 = { { 0, 1 }, { 1, 0 } };
		Node expected = new Node(false, false, new Node(false, true), new Node(true, true), new Node(true, true),
				new Node(false, true));
		check(grid0, expected);
		int[][] grid1 = { { 1, 1, 1, 1, 0, 0, 0, 0 }, { 1, 1, 1, 1, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 0, 0, 0, 0 }, { 1, 1, 1, 1, 0, 0, 0, 0 },
				{ 1, 1, 1, 1, 0, 0, 0, 0 }, { 1, 1, 1, 1, 0, 0, 0, 0 } };
		expected = new Node(false, false, new Node(true, true), new Node(false, false, new Node(false, true),
				new Node(false, true), new Node(true, true), new Node(true, true)), new Node(true, true),
				new Node(false, true));
		check(grid1, expected);
	}

	/**
	 * This solution uses divide and conquer strategy for recursively mapping quads
	 * into nodes until the quad is uniform. Time complexity is O(N^2*logN) where N
	 * is the length of the grid array.
	 * 
	 * @param grid
	 * @return
	 */
	public static Node construct(int[][] grid) {
		return constructRecursive(grid, 0, grid.length, 0, grid.length);
	}

	private static Node constructRecursive(int[][] grid, int rowStart, int rowEnd, int columnStart, int columnEnd) {
		int firstValue = grid[rowStart][columnStart];
		// early exit if the quad contains a single cell, return leaf node
		if (rowEnd - rowStart == 1) {
			return new Node(firstValue == 1, true);
		}
		// check if quad has uniform values
		boolean isUniform = true;
		outer: for (int i = rowStart; i < rowEnd; i++) {
			for (int j = columnStart; j < columnEnd; j++) {
				if (grid[i][j] != firstValue) {
					isUniform = false;
					break outer;
				}
			}
		}
		// the quad is uniform, return single node
		if (isUniform) {
			return new Node(firstValue == 1, true);
		} else {
			// the quad is not uniform, divide in 4 sub quads and calculate recursively
			int midRow = (rowStart + rowEnd) >> 1;
			int midColumn = (columnStart + columnEnd) >> 1;
			Node node = new Node();
			node.topLeft = constructRecursive(grid, rowStart, midRow, columnStart, midColumn);
			node.topRight = constructRecursive(grid, rowStart, midRow, midColumn, columnEnd);
			node.bottomLeft = constructRecursive(grid, midRow, rowEnd, columnStart, midColumn);
			node.bottomRight = constructRecursive(grid, midRow, rowEnd, midColumn, columnEnd);
			return node;
		}
	}

	// Definition for a QuadTree node.
	private static class Node {
		public boolean val;
		public boolean isLeaf;
		public Node topLeft;
		public Node topRight;
		public Node bottomLeft;
		public Node bottomRight;

		public Node() {
			this.val = false;
			this.isLeaf = false;
			this.topLeft = null;
			this.topRight = null;
			this.bottomLeft = null;
			this.bottomRight = null;
		}

		public Node(boolean val, boolean isLeaf) {
			this.val = val;
			this.isLeaf = isLeaf;
			this.topLeft = null;
			this.topRight = null;
			this.bottomLeft = null;
			this.bottomRight = null;
		}

		public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
			this.val = val;
			this.isLeaf = isLeaf;
			this.topLeft = topLeft;
			this.topRight = topRight;
			this.bottomLeft = bottomLeft;
			this.bottomRight = bottomRight;
		}

		public String printAll() {
			StringBuilder builder = new StringBuilder();
			print(this, builder);
			builder.insert(0, '[');
			builder.append("]");
			return builder.toString();
		}

		private void print(Node node, StringBuilder builder) {
			if (!builder.isEmpty()) {
				builder.append(",");
			}
			builder.append("[").append(node.isLeaf ? 1 : 0).append(",").append(node.val ? 1 : 0).append("]");
			if (!node.isLeaf) {
				print(node.topLeft, builder);
				print(node.topRight, builder);
				print(node.bottomLeft, builder);
				print(node.bottomRight, builder);
			}
		}
	}

	private static void check(int[][] grid, Node expected) {
		System.out.println("grid is: ");
		for (int i = 0; i < grid.length; i++) {
			System.out.println(Arrays.toString(grid[i]));
		}
		System.out.println("expected is: " + expected.printAll());
		Node construct = construct(grid); // Calls your implementation
		System.out.println("construct is: " + construct.printAll());
	}
}
