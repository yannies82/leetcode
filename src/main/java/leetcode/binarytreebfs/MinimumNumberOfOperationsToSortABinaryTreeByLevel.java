package leetcode.binarytreebfs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class MinimumNumberOfOperationsToSortABinaryTreeByLevel {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(1, new TreeNode(4, new TreeNode(7), new TreeNode(6)),
				new TreeNode(3, new TreeNode(8, new TreeNode(9), null), new TreeNode(5, new TreeNode(10), null)));
		check(tree1, 3);
		tree1 = new TreeNode(1, new TreeNode(3, new TreeNode(7), new TreeNode(6)),
				new TreeNode(2, new TreeNode(5), new TreeNode(4)));
		check(tree1, 3);
		tree1 = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)),
				new TreeNode(3, new TreeNode(6), null));
		check(tree1, 0);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/minimum-number-of-operations-to-sort-a-binary-tree-by-level.
	 * This solution performs BFS traversal of the tree and keeps an array of the
	 * node values along with their indexes in the level encoded as longs. Time
	 * complexity is O(nlogn) where n is the number of nodes in the tree.
	 * 
	 * @param root
	 * @return
	 */
	public static int minimumOperations(TreeNode root) {
		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.add(root);
		int result = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			// keeps the node values of the current level along with their index as a long
			long[] levelNodes = new long[size];
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				// node value is stored at higher 32 bits and index is stored at lower 32 bits
				levelNodes[i] = ((long) node.val << 32) + i;
				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}
			}

			// sort nodes of this level by their values
			Arrays.sort(levelNodes);

			// compare indexes of the sorted nodes with the original indexes
			// and perform swaps
			int i = 0;
			while (i < size) {
				// get the original index from the lower 32 bits
				int originalIndex = (int) (levelNodes[i] & 0xFFFFFFFF);
				if (originalIndex != i) {
					// original index is different from sorted index
					// do the swap and check this position again
					long temp = levelNodes[i];
					levelNodes[i] = levelNodes[originalIndex];
					levelNodes[originalIndex] = temp;
					result++;
				} else {
					// sorted index is the same as the original index, no need
					// to swap, advance to next position
					i++;
				}
			}
		}
		return result;
	}

	/**
	 * Similar solution which performs BFS traversal of the tree and keeps an array
	 * of the node values along with their indexes in the level. Time complexity is
	 * O(nlogn) where n is the number of nodes in the tree.
	 * 
	 * @param root
	 * @return
	 */
	public static int minimumOperations2(TreeNode root) {
		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.add(root);
		int result = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			// keeps the nodes of the current level along with their index
			int[][] levelNodes = new int[size][2];
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				levelNodes[i][0] = node.val;
				levelNodes[i][1] = i;
				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}
			}

			// sort nodes of this level by their values
			Arrays.sort(levelNodes, (a, b) -> a[0] - b[0]);

			// compare indexes of the sorted nodes with the original indexes
			// and perform swaps
			int i = 0;
			while (i < size) {
				int originalIndex = levelNodes[i][1];
				if (originalIndex != i) {
					// original index is different from sorted index
					// do the swap and check this position again
					int[] temp = levelNodes[i];
					levelNodes[i] = levelNodes[originalIndex];
					levelNodes[originalIndex] = temp;
					result++;
				} else {
					// sorted index is the same as the original index, no need
					// to swap, advance to next position
					i++;
				}
			}
		}
		return result;
	}

	private static void check(TreeNode root, int expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + expected);
		int minimumOperations = minimumOperations(root);
		System.out.println("minimumOperations is: " + minimumOperations);
	}

}
