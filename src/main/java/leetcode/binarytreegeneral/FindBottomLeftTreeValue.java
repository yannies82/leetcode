package leetcode.binarytreegeneral;

import java.util.ArrayDeque;
import java.util.Queue;

public class FindBottomLeftTreeValue {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
		check(tree1, 1);
		TreeNode tree2 = new TreeNode(1, new TreeNode(2, new TreeNode(4), null),
				new TreeNode(3, new TreeNode(5, new TreeNode(7), null), new TreeNode(6)));
		check(tree2, 7);
	}

	/**
	 * This solution uses DFS traversal, increasing a counter for every level and
	 * setting the value of the first element of the new level as the target value.
	 * Time complexity is O(n) where n is the number of nodes in the tree.
	 * 
	 * @param root
	 * @return
	 */
	public static int findBottomLeftValue(TreeNode root) {
		// the first element of the array keeps the max depth and
		// the second element keeps the value of the first element
		// of the last level
		int[] maxDepth = { 0, root.val };
		// DFS traverse the tree
		visit(root, 0, maxDepth);
		return maxDepth[1];
	}

	private static void visit(TreeNode node, int depth, int[] maxDepth) {
		if (node == null) {
			return;
		}
		// update the maxDepth and the target element value if the current depth is
		// greater
		if (depth > maxDepth[0]) {
			maxDepth[0] = depth;
			maxDepth[1] = node.val;
		}
		visit(node.left, depth + 1, maxDepth);
		visit(node.right, depth + 1, maxDepth);
	}

	/**
	 * This solution uses BFS traversal and keeps the value of the first element of
	 * the new level. Time complexity is O(n) where n is the number of nodes in the
	 * tree.
	 * 
	 * @param root
	 * @return
	 */
	public static int findBottomLeftValue2(TreeNode root) {
		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		int result = root.val;
		while (!queue.isEmpty()) {
			int length = queue.size();
			result = queue.peek().val;
			for (int i = 0; i < length; i++) {
				TreeNode current = queue.poll();
				if (current.left != null) {
					queue.offer(current.left);
				}
				if (current.right != null) {
					queue.offer(current.right);
				}
			}
		}
		return result;
	}

	private static void check(TreeNode root, int expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + expected);
		int findBottomLeftValue = findBottomLeftValue(root);
		System.out.println("findBottomLeftValue is: " + findBottomLeftValue);
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
