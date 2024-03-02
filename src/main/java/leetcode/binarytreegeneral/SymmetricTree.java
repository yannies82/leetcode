package leetcode.binarytreegeneral;

import java.util.ArrayDeque;
import java.util.Deque;

public class SymmetricTree {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)),
				new TreeNode(2, new TreeNode(4), new TreeNode(3)));
		check(tree1, true);
		tree1 = new TreeNode(1, new TreeNode(2, null, new TreeNode(3)), new TreeNode(2, null, new TreeNode(3)));
		check(tree1, false);
	}

	/**
	 * Recursive solution.
	 * 
	 * @param root
	 * @return
	 */
	public static boolean isSymmetric(TreeNode root) {
		// early exit for empty tree
		if (root == null) {
			return true;
		}
		// early exit if both subtrees are null
		if (root.left == null && root.right == null) {
			return true;
		}
		// early exit if only one of the subtrees is null
		if (root.left == null ^ root.right == null) {
			return false;
		}
		// traverse the subtrees recursively and symmetrically
		// and compare nodes
		return traverse(root.left, root.right);
	}

	private static boolean traverse(TreeNode left, TreeNode right) {
		boolean result = true;
		if (left.val != right.val || left.left == null ^ right.right == null
				|| left.right == null ^ right.left == null) {
			// the trees are not the same if the respective nodes have different value
			// or not symmetric children nodes
			return false;
		}
		if (left.left != null) {
			// traverse left node recursively
			result = traverse(left.left, right.right);
		}
		if (left.right != null) {
			// traverse right node recursively
			result = result && traverse(left.right, right.left);
		}
		return result;
	}

	/**
	 * Iterative solution.
	 * 
	 * @param root
	 * @return
	 */
	public static boolean isSymmetric2(TreeNode root) {
		// early exit if tree is null
		if (root == null) {
			return true;
		}
		// early exit if both subtrees are null
		if (root.left == null && root.right == null) {
			return true;
		}
		// early exit if only one of the subtrees is null
		if (root.left == null ^ root.right == null) {
			return false;
		}
		Deque<TreeNode> stackLeft = new ArrayDeque<>();
		Deque<TreeNode> stackRight = new ArrayDeque<>();
		stackLeft.offerFirst(root.left);
		stackRight.offerFirst(root.right);
		// traverse both trees iteratively preorder using a stack per tree
		while (!stackLeft.isEmpty() && !stackRight.isEmpty()) {
			TreeNode currentLeft = stackLeft.poll();
			TreeNode currentRight = stackRight.poll();
			if (currentLeft.val != currentRight.val || currentLeft.right == null ^ currentRight.left == null
					|| currentLeft.left == null ^ currentRight.right == null) {
				// the trees are not the same if the respective nodes have different value
				// or not symmetric children nodes
				return false;
			}
			if (currentLeft.right != null) {
				// add symmetric children nodes to the stacks
				stackLeft.offerFirst(currentLeft.right);
				stackRight.offerFirst(currentRight.left);
			}
			if (currentLeft.left != null) {
				// add symmetric children nodes to the stacks
				stackLeft.offerFirst(currentLeft.left);
				stackRight.offerFirst(currentRight.right);
			}
		}
		return true;
	}

	private static void check(TreeNode root, boolean expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + expected);
		boolean isSymmetric = isSymmetric(root);
		System.out.println("isSymmetric is: " + isSymmetric);
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
