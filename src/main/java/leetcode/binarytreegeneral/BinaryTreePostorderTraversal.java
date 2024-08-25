package leetcode.binarytreegeneral;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePostorderTraversal {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
		check(tree1, List.of(3, 2, 1));
		tree1 = null;
		check(tree1, List.of());
		tree1 = new TreeNode(1);
		check(tree1, List.of(1));
		tree1 = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)),
				new TreeNode(3, new TreeNode(6), new TreeNode(7)));
		check(tree1, List.of(1));
	}

	/**
	 * Leetcode problem;
	 * https://leetcode.com/problems/binary-tree-postorder-traversal. Time
	 * complexity is O(n) where n is the number of nodes in the tree.
	 * 
	 * @param root
	 * @return
	 */
	public static List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		traverse(root, result);
		return result;
	}

	private static void traverse(TreeNode node, List<Integer> result) {
		if (node == null) {
			return;
		}
		traverse(node.left, result);
		traverse(node.right, result);
		result.add(node.val);
	}

	private static void check(TreeNode root, List<Integer> expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + expected);
		List<Integer> postorderTraversal = postorderTraversal(root);
		System.out.println("postorderTraversal is: " + postorderTraversal);
	}

}
