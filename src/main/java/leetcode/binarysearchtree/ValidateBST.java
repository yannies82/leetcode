package leetcode.binarysearchtree;

public class ValidateBST {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
		check(tree1, true);
		tree1 = new TreeNode(5, new TreeNode(1), new TreeNode(4, new TreeNode(3), new TreeNode(6)));
		check(tree1, false);
	}

	/**
	 * Recursive solution, using inorder traversal.
	 * 
	 * @param root
	 * @return
	 */
	public static boolean isValidBST(TreeNode root) {
		// first element of the array is the previous one
		TreeNode[] arr = { null };
		// traverse the tree inorder
		return traverse(root, arr);
	}

	private static boolean traverse(TreeNode node, TreeNode[] arr) {
		boolean result = true;
		if (node.left != null) {
			result = traverse(node.left, arr);
		}
		// compare element value to the previous one
		result = result && (arr[0] == null || node.val > arr[0].val);
		// update the element
		arr[0] = node;
		if (node.right != null) {
			result = result && traverse(node.right, arr);
		}
		return result;
	}

	private static void check(TreeNode root, boolean expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + expected);
		boolean isValidBST = isValidBST(root);
		System.out.println("isValidBST is: " + isValidBST);
	}

}
