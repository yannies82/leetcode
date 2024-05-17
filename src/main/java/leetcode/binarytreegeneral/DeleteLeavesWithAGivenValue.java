package leetcode.binarytreegeneral;

public class DeleteLeavesWithAGivenValue {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(1, new TreeNode(2, new TreeNode(2), null),
				new TreeNode(3, new TreeNode(2), new TreeNode(4)));
		TreeNode expected1 = new TreeNode(1, null, new TreeNode(3, null, new TreeNode(4)));
		check(tree1, 2, expected1);
		tree1 = new TreeNode(1, new TreeNode(3, new TreeNode(3), new TreeNode(2)), new TreeNode(3));
		expected1 = new TreeNode(1, new TreeNode(3, null, new TreeNode(2)), null);
		check(tree1, 3, expected1);
		tree1 = new TreeNode(1, new TreeNode(2, new TreeNode(2, new TreeNode(2), null), null), null);
		expected1 = new TreeNode(1);
		check(tree1, 2, expected1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/delete-leaves-with-a-given-value. This solution
	 * traverses the tree postorder and removes the target elements. Time complexity
	 * is O(n) where n is the number of elements in the tree.
	 * 
	 * @param root
	 * @param target
	 * @return
	 */
	public static TreeNode removeLeafNodes(TreeNode root, int target) {
		if (root.left != null) {
			root.left = removeLeafNodes(root.left, target);
		}
		if (root.right != null) {
			root.right = removeLeafNodes(root.right, target);
		}
		if (root.left == null && root.right == null) {
			return root.val == target ? null : root;
		}
		return root;
	}

	private static void check(TreeNode root, int target, TreeNode expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("target is: " + target);
		System.out.println("expected is: " + expected.printAll());
		TreeNode addOneRow = removeLeafNodes(root, target);
		System.out.println("addOneRow is: " + addOneRow.printAll());
	}

}
