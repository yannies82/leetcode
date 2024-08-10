package leetcode.graphgeneral;

public class RegionsCutBySlashes {

	public static void main(String[] args) {
		check(new String[] { " /", "/ " }, 2);
		check(new String[] { " /", "  " }, 1);
		check(new String[] { "/\\", "\\/" }, 5);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/regions-cut-by-slashes. This
	 * solution uses the union-find approach to find the connected dots and count
	 * the regions. Time complexity is O(n^2) where n is the length of the grid
	 * array.
	 * 
	 * @param grid
	 * @return
	 */
	public static int regionsBySlashes(String[] grid) {
		int n = grid.length;
		int dots = n + 1;
		// keeps the parent of each dot
		int[] parent = new int[dots * dots];
		// keeps the rank of each dot
		int[] rank = new int[dots * dots];
		// keeps the region count
		int[] count = { 0 };
		// initialize parent and rank arrays
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
			rank[i] = 1;
		}
		// union find in the first row of dots
		for (int j = 0; j < dots; j++) {
			union(0, j, parent, rank, count);
		}
		// union find in the last row of dots
		int lastRowOffset = (dots - 1) * dots;
		for (int j = 0; j < dots; j++) {
			union(0, lastRowOffset + j, parent, rank, count);
		}
		// union find in the first column of dots
		for (int i = 1; i < n; i++) {
			union(0, i * dots, parent, rank, count);
		}
		// union find in the first last column of dots
		for (int i = 1; i < n; i++) {
			union(0, i * dots + n, parent, rank, count);
		}
		// perform union find for all edges which connect dots ('/' or '\' characters in
		// grid)
		for (int i = 0; i < n; i++) {
			char[] ch = grid[i].toCharArray();
			int rowOffset = i * dots;
			int nextRowOffset = rowOffset + dots;
			for (int j = 0; j < ch.length; j++) {
				if (ch[j] == '\\') {
					// a '\' character in the i, j position of the grid connects dot i, j to dot
					// i+1, j+1
					int cell1 = rowOffset + j;
					int cell2 = nextRowOffset + (j + 1);
					union(cell1, cell2, parent, rank, count);
				} else if (ch[j] == '/') {
					// a '/' character in the i, j position of the grid connects dot i+1, j to dot
					// i, j+1
					int cell1 = nextRowOffset + j;
					int cell2 = rowOffset + (j + 1);
					union(cell1, cell2, parent, rank, count);
				}
			}
		}
		return count[0];
	}

	private static void union(int a, int b, int[] parent, int[] rank, int[] count) {
		int parentA = find(a, parent);
		int parentB = find(b, parent);
		if (parentA == parentB) {
			count[0]++;
		} else {
			if (rank[parentA] > rank[parentB]) {
				parent[parentB] = parentA;
			} else if (rank[parentA] < rank[parentB]) {
				parent[parentA] = parentB;
			} else {
				parent[parentB] = parentA;
				rank[parentA]++;
			}
		}
	}

	private static int find(int a, int[] parent) {
		if (parent[a] == a) {
			return a;
		}
		parent[a] = find(parent[a], parent);
		return parent[a];
	}

	private static void check(String[] grid, int expected) {
		System.out.println("grid is: ");
		for (int i = 0; i < grid.length; i++) {
			System.out.println(grid[i]);
		}
		System.out.println("expected is: " + expected);
		int regionsBySlashes = regionsBySlashes(grid); // Calls your implementation
		System.out.println("regionsBySlashes is: " + regionsBySlashes);
	}
}
