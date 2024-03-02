package leetcode.binarytreebfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class AverageLevelsInBinaryTree {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(3, new TreeNode(9, new TreeNode(15), new TreeNode(7)), new TreeNode(20));
		List<Double> expected = List.of(3.00000, 14.50000, 11.00000);
		check(tree1, expected);
		tree1 = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
		expected = List.of(3.00000, 14.50000, 11.00000);
		check(tree1, expected);
	}

	/**
	 * Iterative solution using BFS traversal.
	 * 
	 * @param root
	 * @return
	 */
	public static List<Double> averageOfLevels(TreeNode root) {
		// early exit if root is null
		if (root == null) {
			return Collections.emptyList();
		}
		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		// traverse tree bfs using a queue
		List<Double> result = new ArrayList<>();
		while (!queue.isEmpty()) {
			int levelSize = queue.size();
			double levelSum = 0;
			for (int i = 0; i < levelSize; i++) {
				TreeNode current = queue.poll();
				// add node value to the current level sum
				levelSum += current.val;
				if (current.left != null) {
					queue.offer(current.left);
				}
				if (current.right != null) {
					queue.offer(current.right);
				}
			}
			// add average of level to the list
			result.add(levelSum / levelSize);
		}
		return result;
	}

	private static void check(TreeNode root, List<Double> expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + expected);
		List<Double> averageOfLevels = averageOfLevels(root);
		System.out.println("averageOfLevels is: " + averageOfLevels);
	}

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}

		String printAll() {
			TreeNode current = this;
			StringBuilder result = new StringBuilder();
			print(current, result);
			return result.toString();
		}

		void print(TreeNode node, StringBuilder builder) {
			if (!builder.isEmpty()) {
				builder.append(",");
			}
			builder.append(node.val);
			if (node.left == null) {
				builder.append(", null");
			} else {
				print(node.left, builder);
			}
			if (node.right == null) {
				builder.append(", null");
			} else {
				print(node.right, builder);
			}
		}

	}

}
