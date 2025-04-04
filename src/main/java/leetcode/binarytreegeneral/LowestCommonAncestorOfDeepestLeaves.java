package leetcode.binarytreegeneral;

public class LowestCommonAncestorOfDeepestLeaves {

	public static void main(String[] args) {
		check(new TreeNode(3, new TreeNode(5, new TreeNode(6), new TreeNode(2, new TreeNode(7),
				new TreeNode(4))), new TreeNode(1, new TreeNode(0), new TreeNode(8))),
				new TreeNode(2, new TreeNode(7), new TreeNode(4)));
		check(new TreeNode(1), new TreeNode(1));
		check(new TreeNode(0, new TreeNode(1), new TreeNode(3, null, new TreeNode(2))), new TreeNode(2));
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves.
	 * This solution performs a postorder traversal of the tree in order to update the common ancestor.
	 * Time complexity is O(n) where n is the number of nodes in the tree.
	 * 
	 * @param root
	 * @return
	 */
	public static TreeNode lcaDeepestLeaves(TreeNode root) {
		int[] deepestLevel = {0};
		TreeNode[] deepestNode = {root};
		traverse(root, 0, deepestLevel, deepestNode);
		return deepestNode[0];
	}

	private static int traverse(TreeNode node, int level, int[] deepestLevel, TreeNode[] deepestNode) {
		if (node == null) {
			return level - 1;
		}
		int leftLevel = traverse(node.left, level + 1, deepestLevel, deepestNode);
		int rightLevel = traverse(node.right, level + 1, deepestLevel, deepestNode);
		int maxLevel = Math.max(leftLevel, rightLevel);
		deepestLevel[0] = Math.max(deepestLevel[0], maxLevel);
		if (leftLevel == deepestLevel[0] && rightLevel == deepestLevel[0]) {
			deepestNode[0] = node;
		}
		return maxLevel;
	}

	private static void check(TreeNode root, TreeNode expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + expected.printAll());
		TreeNode lcaDeepestLeaves = lcaDeepestLeaves(root);
		System.out.println("lcaDeepestLeaves is: " + lcaDeepestLeaves.printAll());
	}

}
