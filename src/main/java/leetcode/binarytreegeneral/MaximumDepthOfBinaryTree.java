package leetcode.binarytreegeneral;

public class MaximumDepthOfBinaryTree {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
		check(tree1, 3);
		tree1 = new TreeNode(1, null, new TreeNode(2));
		check(tree1, 2);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/maximum-depth-of-binary-tree.
	 * This solution traverses the tree, increasing the depth for each level and
	 * keeps track of the maximum depth. Time complexity is O(n) where n is the
	 * number of nodes in the tree.
	 * 
	 * @param root
	 * @return
	 */
	public static int maxDepth(TreeNode root) {
		// early exit for empty tree
		if (root == null) {
			return 0;
		}
		// keeps the maximum depth
		int[] count = { 0 };
		// traverse the tree recursively
		traverse(root, 1, count);
		return count[0];
	}

	private static void traverse(TreeNode node, int depth, int[] count) {
		if (count[0] < depth) {
			// increase the max depth counter if applicable
			count[0] = depth;
		}
		if (node.left != null) {
			// traverse left node recursively
			traverse(node.left, depth + 1, count);
		}
		if (node.right != null) {
			// traverse right node recursively
			traverse(node.right, depth + 1, count);
		}
	}

	private static void check(TreeNode root, int expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + expected);
		int maxDepth = maxDepth(root);
		System.out.println("maxDepth is: " + maxDepth);
	}

}
