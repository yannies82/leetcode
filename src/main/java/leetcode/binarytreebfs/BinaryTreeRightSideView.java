package leetcode.binarytreebfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(1, new TreeNode(2, null, new TreeNode(5)),
				new TreeNode(3, null, new TreeNode(4)));
		List<Integer> expected = List.of(1, 3, 4);
		check(tree1, expected);
		tree1 = new TreeNode(1, null, new TreeNode(3));
		expected = List.of(1, 3);
		check(tree1, expected);
		tree1 = null;
		expected = List.of();
		check(tree1, expected);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/binary-tree-right-side-view.
	 * Recursive solution using postorder traversal and putting in the result list
	 * the first node for each new level. Time complexity is O(n) where n is the
	 * number of nodes in the tree.
	 * 
	 * @param root
	 * @return
	 */
	public static List<Integer> rightSideView(TreeNode root) {
		if (root == null) {
			return Collections.emptyList();
		}
		// keeps the max level
		int[] maxLevel = { 0 };
		// keeps the result list values
		List<Integer> result = new ArrayList<>();
		// traverse the tree post order, add to the result list
		// the first element for each new level which will be
		// the rightmost one
		traversePostOrder(root, 1, maxLevel, result);
		return result;
	}

	private static void traversePostOrder(TreeNode node, int level, int[] maxLevel, List<Integer> rightView) {

		if (level > maxLevel[0]) {
			// the current level is greater then the max level
			// add it to the list and update the max level
			rightView.add(node.val);
			maxLevel[0] = level;
		}
		if (node.right != null) {
			// traverse right node recursively, increase level
			traversePostOrder(node.right, level + 1, maxLevel, rightView);
		}
		if (node.left != null) {
			// traverse left node recursively, increase level
			traversePostOrder(node.left, level + 1, maxLevel, rightView);
		}
	}

	/**
	 * Iterative solution using BFS traversal and putting the last element of each
	 * level in the result list.
	 * 
	 * @param root
	 * @return
	 */
	public static List<Integer> rightSideView2(TreeNode root) {
		// early exit if root is null
		if (root == null) {
			return Collections.emptyList();
		}
		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		// traverse tree bfs using a queue
		List<Integer> result = new ArrayList<>();
		while (!queue.isEmpty()) {
			int levelSize = queue.size();
			TreeNode current = null;
			for (int i = 0; i < levelSize; i++) {
				current = queue.poll();
				if (current.left != null) {
					queue.offer(current.left);
				}
				if (current.right != null) {
					queue.offer(current.right);
				}
			}
			// add last element of level to the list
			result.add(current.val);
		}
		return result;
	}

	private static void check(TreeNode root, List<Integer> expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + expected);
		List<Integer> rightSideView = rightSideView(root);
		System.out.println("rightSideView is: " + rightSideView);
	}

}
