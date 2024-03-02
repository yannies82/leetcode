package leetcode.binarytreegeneral;

import java.util.ArrayDeque;
import java.util.Deque;

public class InvertBinaryTree {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)),
				new TreeNode(7, new TreeNode(6), new TreeNode(9)));
		TreeNode tree2 = new TreeNode(4, new TreeNode(7, new TreeNode(9), new TreeNode(6)),
				new TreeNode(2, new TreeNode(3), new TreeNode(1)));
		check(tree1, tree2);
		tree1 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
		tree2 = new TreeNode(2, new TreeNode(3), new TreeNode(1));
		check(tree1, tree2);
		tree1 = null;
		tree2 = null;
		check(tree1, tree2);
	}

	public static TreeNode invertTree(TreeNode root) {
		// early exit if root is null
		if (root == null) {
			return null;
		}
		Deque<TreeNode> stack = new ArrayDeque<>();
		stack.offerFirst(root);
		// traverse tree iteratively preorder using a stack
		while (!stack.isEmpty()) {
			TreeNode current = stack.poll();
			// exchange left and right nodes
			TreeNode temp = current.left;
			current.left = current.right;
			current.right = temp;
			if (current.right != null) {
				// add right children node to the stack
				stack.offerFirst(current.right);
			}
			if (current.left != null) {
				// add left children node to the stack
				stack.offerFirst(current.left);
			}
		}
		return root;
	}

	private static void check(TreeNode root, TreeNode expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		TreeNode invertTree = invertTree(root);
		System.out.println("invertTree is: " + (invertTree == null ? null : invertTree.printAll()));
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
