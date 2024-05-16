package leetcode.binarytreegeneral;

public class EvaluateBooleanBinaryTree {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(2, new TreeNode(1), new TreeNode(3, new TreeNode(0), new TreeNode(1)));
		check(tree1, true);
		tree1 = new TreeNode(0);
		check(tree1, false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/evaluate-boolean-binary-tree.
	 * This solution evaluates the expressions recursively until only leaf nodes are
	 * left. Time complexity is O(n) where n is the number of nodes in the tree.
	 * 
	 * @param root
	 * @return
	 */
	public static boolean evaluateTree(TreeNode root) {
		return switch (root.val) {
		case 0 -> false;
		case 1 -> true;
		case 2 -> evaluateTree(root.left) || evaluateTree(root.right);
		case 3 -> evaluateTree(root.left) && evaluateTree(root.right);
		default -> false;
		};
	}

	private static void check(TreeNode root, boolean expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + expected);
		boolean evaluateTree = evaluateTree(root);
		System.out.println("evaluateTree is: " + evaluateTree);
	}

}
