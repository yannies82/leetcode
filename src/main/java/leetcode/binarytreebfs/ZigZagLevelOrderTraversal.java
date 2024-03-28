package leetcode.binarytreebfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZigZagLevelOrderTraversal {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
		List<List<Integer>> expected = List.of(List.of(3), List.of(20, 9), List.of(15, 7));
		check(tree1, expected);
		tree1 = new TreeNode(1);
		expected = List.of(List.of(1));
		check(tree1, expected);
		tree1 = null;
		expected = List.of();
		check(tree1, expected);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal.
	 * Iterative solution using BFS traversal. Time complexity is O(n) where n is
	 * the number of nodes in the tree.
	 * 
	 * @param root
	 * @return
	 */
	public static List<List<Integer>> levelOrder(TreeNode root) {
		// early exit if root is null
		if (root == null) {
			return Collections.emptyList();
		}
		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		// traverse tree bfs using a queue
		List<List<Integer>> result = new ArrayList<>();
		int level = 1;
		while (!queue.isEmpty()) {
			int levelSize = queue.size();
			boolean isEven = level % 2 == 0;
			// initialize a list of levelSize length
			List<Integer> levelList = new LinkedList<>();
			for (int i = 0; i < levelSize; i++) {
				TreeNode current = queue.poll();
				// add node value to the level list
				// add to the end of the list for odd levels
				// and to the start of the list for even levels
				if (isEven) {
					levelList.add(0, current.val);
				} else {
					levelList.add(current.val);
				}
				if (current.left != null) {
					queue.offer(current.left);
				}
				if (current.right != null) {
					queue.offer(current.right);
				}
			}
			// add list to the result
			result.add(levelList);
			level++;
		}
		return result;
	}

	private static void check(TreeNode root, List<List<Integer>> expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + expected);
		List<List<Integer>> levelOrder = levelOrder(root);
		System.out.println("levelOrder is: " + levelOrder);
	}

}
