package leetcode.binarysearchtree;

public class BinarySearchTreeToGreaterSumTree {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(4, new TreeNode(1, new TreeNode(0), new TreeNode(2, null, new TreeNode(3))),
				new TreeNode(6, new TreeNode(5), new TreeNode(7, null, new TreeNode(8))));
		TreeNode expected1 = new TreeNode(30,
				new TreeNode(36, new TreeNode(36), new TreeNode(35, null, new TreeNode(33))),
				new TreeNode(21, new TreeNode(26), new TreeNode(15, null, new TreeNode(8))));
		check(tree1, expected1);
		tree1 = new TreeNode(0, null, new TreeNode(1));
		expected1 = new TreeNode(1, null, new TreeNode(1));
		check(tree1, expected1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree. This
	 * solution traverses the tree in reverse inorder, right to left and increases
	 * the value of each node appropriately. Time complexity is O(n) where n is the
	 * number of nodes in the tree.
	 * 
	 * @param root
	 * @return
	 */
	public static TreeNode bstToGst(TreeNode root) {
		int[] sum = new int[1];
		visitInorder(root, sum);
		return root;
	}

	private static void visitInorder(TreeNode node, int[] sum) {
		if (node == null) {
			return;
		}
		visitInorder(node.right, sum);
		int val = node.val;
		node.val += sum[0];
		sum[0] += val;
		visitInorder(node.left, sum);
	}

	private static void check(TreeNode root, TreeNode expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + expected.printAll());
		TreeNode bstToGst = bstToGst(root);
		System.out.println("bstToGst is: " + bstToGst.printAll());
	}

}
