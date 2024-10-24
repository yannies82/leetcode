package leetcode.binarytreegeneral;

public class FlipEquivalentBinaryTrees {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(1,
				new TreeNode(2, new TreeNode(4), new TreeNode(5, new TreeNode(7), new TreeNode(8))),
				new TreeNode(3, new TreeNode(6), null));
		TreeNode tree2 = new TreeNode(1, new TreeNode(3, null, new TreeNode(6)),
				new TreeNode(2, new TreeNode(4), new TreeNode(5, new TreeNode(8), new TreeNode(7))));
		check(tree1, tree2, true);
		check(null, null, true);
		check(null, new TreeNode(1), false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/flip-equivalent-binary-trees.
	 * This solution traverses both trees preorder, checking if every node from one
	 * tree has children with the same values as the respective node of the other
	 * tree. Time complexity is O(n) where n is the number of nodes in the tree.
	 * 
	 * @param root1
	 * @param root2
	 * @return
	 */
	public static boolean flipEquiv(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		}
		if (root1 == null || root2 == null || root1.val != root2.val) {
			return false;
		}
		return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right))
				|| (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
	}

	private static void check(TreeNode root1, TreeNode root2, boolean expected) {
		System.out.println("root is: " + (root1 == null ? null : root1.printAll()));
		System.out.println("root is: " + (root2 == null ? null : root2.printAll()));
		System.out.println("expected is: " + expected);
		boolean flipEquiv = flipEquiv(root1, root2);
		System.out.println("flipEquiv is: " + flipEquiv);
	}

}
