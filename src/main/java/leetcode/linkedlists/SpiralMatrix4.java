package leetcode.linkedlists;

import java.util.Arrays;

public class SpiralMatrix4 {

	public static void main(String[] args) {
		check(3, 5, ListNode.createList(3, 0, 2, 6, 8, 1, 7, 9, 4, 2, 5, 5, 0),
				new int[][] { { 3, 0, 2, 6, 8 }, { 5, 0, -1, -1, 1 }, { 5, 2, 4, 9, 7 } });
		check(1, 4, ListNode.createList(0, 1, 2), new int[][] { { 0, 1, 2, -1 } });
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/spiral-matrix-iv. This
	 * solution iterates the result matrix cells in a spiral fashion and sets the
	 * appropriate list value to each one. Time complexity is O(m*n).
	 * 
	 * @param m
	 * @param n
	 * @param head
	 * @return
	 */
	public static int[][] spiralMatrix(int m, int n, ListNode head) {
		int[][] result = new int[m][n];
		for (int[] row : result) {
			Arrays.fill(row, -1);
		}
		ListNode current = head;
		int hStart = 0;
		int hEnd = m - 1;
		int vStart = 0;
		int vEnd = n - 1;
		while (hStart <= hEnd && vStart <= vEnd) {
			// traverse positions of top row
			for (int i = vStart; i <= vEnd && current != null; i++, current = current.next) {
				result[hStart][i] = current.val;
			}
			// traverse positions of last column, excluding the common
			// position with the first row
			for (int i = hStart + 1; i <= hEnd && current != null; i++, current = current.next) {
				result[i][vEnd] = current.val;
			}
			// traverse positions of last row if one exists, excluding the common position
			// with the last column
			if (hStart < hEnd) {
				for (int i = vEnd - 1; i >= vStart && current != null; i--, current = current.next) {
					result[hEnd][i] = current.val;
				}
			}
			// traverse positions of first column if one exists, excluding the common
			// positions
			// with the last and first rows
			if (vStart < vEnd) {
				for (int i = hEnd - 1; i > hStart && current != null; i--, current = current.next) {
					result[i][vStart] = current.val;
				}
			}
			// increase index of first row and column, decrease index of last row and column
			// so that the next iteration continues with the next layer of positions
			hStart++;
			hEnd--;
			vStart++;
			vEnd--;
		}
		return result;
	}

	private static void check(int m, int n, ListNode head, int[][] expected) {
		System.out.println("m is: " + m);
		System.out.println("n is: " + n);
		System.out.println("head is: " + head.printAll());
		System.out.println("expected is: ");
		for (int i = 0; i < expected.length; i++) {
			System.out.println(Arrays.toString(expected[i]));
		}
		int[][] spiralMatrix = spiralMatrix(m, n, head); // Calls your implementation
		System.out.println("spiralMatrix is: ");
		for (int i = 0; i < spiralMatrix.length; i++) {
			System.out.println(Arrays.toString(spiralMatrix[i]));
		}
	}
}
