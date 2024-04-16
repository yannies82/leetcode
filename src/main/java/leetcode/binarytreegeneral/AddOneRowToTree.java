package leetcode.binarytreegeneral;

public class AddOneRowToTree {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(4, new TreeNode(2, new TreeNode(3), new TreeNode(1)),
				new TreeNode(6, new TreeNode(5), null));
		TreeNode expected1 = new TreeNode(4, new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(1)), null),
				new TreeNode(1, null, new TreeNode(6, new TreeNode(5), null)));
		check(tree1, 1, 2, expected1);
		tree1 = new TreeNode(4, new TreeNode(2, new TreeNode(3), new TreeNode(1)), null);
		expected1 = new TreeNode(4,
				new TreeNode(2, new TreeNode(1, new TreeNode(3), null), new TreeNode(1, null, new TreeNode(1))), null);
		check(tree1, 1, 3, expected1);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/add-one-row-to-tree. This
	 * solution uses recursive DFS and adds the new nodes at the appropriate depth.
	 * Time complexity is O(n) where n is the depth of the tree.
	 * 
	 * @param root
	 * @param val
	 * @param depth
	 * @return
	 */
	public static TreeNode addOneRow(TreeNode root, int val, int depth) {
		if (depth == 1) {
			// early exit in case of depth == 1 which means
			// that the new node will be a parent of the root
			TreeNode prev = new TreeNode(val, root, null);
			return prev;
		}
		// traverse the tree preorder recursively
		solve(root, 1, val, depth);
		return root;
	}

	private static void solve(TreeNode node, int currentDepth, int val, int targetDepth) {
		if (node == null) {
			// early exit
			return;
		}
		if (currentDepth == targetDepth - 1) {
			// we are at the appropriate depth where the new nodes will be inserted
			// as children, insert the nodes and return
			node.left = new TreeNode(val, node.left, null);
			node.right = new TreeNode(val, null, node.right);
			return;
		}
		solve(node.left, currentDepth + 1, val, targetDepth);
		solve(node.right, currentDepth + 1, val, targetDepth);
	}

	private static void check(TreeNode root, int val, int depth, TreeNode expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("val is: " + val);
		System.out.println("depth is: " + depth);
		System.out.println("expected is: " + expected.printAll());
		TreeNode addOneRow = addOneRow(root, val, depth);
		System.out.println("addOneRow is: " + addOneRow.printAll());
	}

}
