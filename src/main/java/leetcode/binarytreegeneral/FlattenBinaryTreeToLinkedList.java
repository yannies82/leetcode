package leetcode.binarytreegeneral;

import java.util.ArrayDeque;
import java.util.Deque;

public class FlattenBinaryTreeToLinkedList {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)),
				new TreeNode(5, new TreeNode(6), new TreeNode(7)));
		TreeNode expected = new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3, null,
				new TreeNode(4, null, new TreeNode(5, null, new TreeNode(6, null, new TreeNode(7)))))));
		check(tree1, expected);
		tree1 = null;
		expected = null;
		check(tree1, expected);
		tree1 = new TreeNode(0);
		expected = new TreeNode(0);
		check(tree1, expected);
	}

	/**
	 * Recursive solution.
	 * 
	 * @param root
	 */
	public static void flatten(TreeNode root) {
		// early exit for empty tree
		if (root == null) {
			return;
		}
		// traverse the tree recursively
		// pass the previous node as the second argument
		TreeNode last = traverse(root, null);
		last.left = null;
		return;
	}

	private static TreeNode traverse(TreeNode node, TreeNode prev) {
		// if prev exists set its left pointer to null
		// and its right pointer to the current node
		if (prev != null) {
			prev.left = null;
			prev.right = node;
		}
		// update the prev element to the current node
		// and keep the left and right nodes in variables
		// because node may be modified when the method is
		// called recursively
		prev = node;
		TreeNode left = node.left;
		TreeNode right = node.right;
		if (left != null) {
			// traverse left node recursively
			// and return the last element, set it as prev
			prev = traverse(left, node);
		}
		if (right != null) {
			// traverse right node recursively
			// and return the last element, set it as prev
			prev = traverse(right, prev);
		}
		// return prev which contains the last traversed element
		return prev;
	}

	/**
	 * Iterative solution.
	 * 
	 * @param root
	 */
	public static void flatten2(TreeNode root) {
		// early exit if root is null
		if (root == null) {
			return;
		}
		Deque<TreeNode> stack = new ArrayDeque<>();
		stack.offerFirst(root);
		// traverse tree iteratively preorder using a stack
		TreeNode prev = null;
		while (!stack.isEmpty()) {
			TreeNode current = stack.poll();
			// keep track of the previous element and set its left
			// pointer to null and right pointer to the current element
			if (prev != null) {
				prev.left = null;
				prev.right = current;
			}
			if (current.right != null) {
				// add right children node to the stack
				stack.offerFirst(current.right);
			}
			if (current.left != null) {
				// add left children node to the stack
				stack.offerFirst(current.left);
			}
			prev = current;
		}
		// set the left pointer of the last element to null
		prev.left = null;
		return;
	}

	private static void check(TreeNode root, TreeNode expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		flatten(root);
		System.out.println("flatten is: " + (root == null ? null : root.printAll()));
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
