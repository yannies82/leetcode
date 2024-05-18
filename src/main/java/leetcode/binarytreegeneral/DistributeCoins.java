package leetcode.binarytreegeneral;

public class DistributeCoins {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(4, new TreeNode(0, null, new TreeNode(0, null, new TreeNode(0))), null);
		check(tree1, 6);
		tree1 = new TreeNode(1, new TreeNode(0, null, new TreeNode(3)), new TreeNode(0));
		check(tree1, 4);
		tree1 = new TreeNode(3, new TreeNode(0), new TreeNode(0));
		check(tree1, 2);
		tree1 = new TreeNode(0, new TreeNode(3), new TreeNode(0));
		check(tree1, 3);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/distribute-coins-in-binary-tree. This solution
	 * traverses the tree postorder in order to calculate the number of coins that
	 * each leaf node needs or has to give to its parent. For each node it updates
	 * the required number of moves. Time complexity is O(n) where n is the number
	 * of nodes in the tree.
	 * 
	 * @param root
	 * @return
	 */
	public static int distributeCoins(TreeNode root) {
		// this array keeps the number of total moves
		int[] totalMoves = new int[1];
		// traverse the tree postorder and update the total moves count
		distributeCoinsPostOrder(root, totalMoves);
		return totalMoves[0];
	}

	private static int distributeCoinsPostOrder(TreeNode root, int[] totalMoves) {
		if (root == null) {
			// early exit
			return 0;
		}
		// count the number of coins that the left subtree needs or has to give to the
		// parent
		int leftMoves = distributeCoinsPostOrder(root.left, totalMoves);
		// count the number of coins that the right subtree needs or has to give to the
		// parent
		int rightMoves = distributeCoinsPostOrder(root.right, totalMoves);
		// add left and right subtree coins to the total number of moves
		totalMoves[0] += Math.abs(leftMoves) + Math.abs(rightMoves);
		// calculate and return the number of coins that this node needs or has to give
		// to the parent
		return root.val + leftMoves + rightMoves - 1;
	}

	private static void check(TreeNode root, int expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + expected);
		int distributeCoins = distributeCoins(root);
		System.out.println("distributeCoins is: " + distributeCoins);
	}

}
