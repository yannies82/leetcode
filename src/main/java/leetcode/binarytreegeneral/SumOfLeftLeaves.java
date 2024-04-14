package leetcode.binarytreegeneral;

public class SumOfLeftLeaves {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
		check(tree1, 24);
		TreeNode tree2 = new TreeNode(1);
		check(tree2, 0);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/sum-of-left-leaves. This
	 * solution uses recursive DFS preorder traversal and adds to the sum the value
	 * of left leaf nodes. Time complexity is O(n) where n is the number of nodes in
	 * the tree.
	 * 
	 * @param root
	 * @return
	 */
	public static int sumOfLeftLeaves(TreeNode root) {
		int[] result = { 0 };
		traverse(root, result, false);
		return result[0];
	}

	private static void traverse(TreeNode node, int[] result, boolean isLeft) {
		if (node == null) {
			return;
		}
		if (isLeft && node.left == null && node.right == null) {
			result[0] += node.val;
			return;
		}
		traverse(node.left, result, true);
		traverse(node.right, result, false);
	}

	private static void check(TreeNode root, int expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + expected);
		int sumOfLeftLeaves = sumOfLeftLeaves(root);
		System.out.println("sumOfLeftLeaves is: " + sumOfLeftLeaves);
	}

}
