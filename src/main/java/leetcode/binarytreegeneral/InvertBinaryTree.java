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

	/**
	 * Leetcode problem: https://leetcode.com/problems/invert-binary-tree. This
	 * solution traverses the tree using recursive DFS and exchanges the left and
	 * right nodes. Time complexity is O(n) where n is the number of nodes in the
	 * tree.
	 * 
	 * @param root
	 * @return
	 */
	public static TreeNode invertTree(TreeNode root) {
		// early exit for empty tree
		if (root == null) {
			return null;
		}
		// apply recursively to left and right nodes
		// exchange left and right nodes
		TreeNode temp = invertTree(root.left);
		root.left = invertTree(root.right);
		root.right = temp;
		return root;
	}

	/**
	 * Alternate solution using iterative DFS.
	 * 
	 * @param root
	 * @return
	 */
	public static TreeNode invertTree2(TreeNode root) {
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

}
