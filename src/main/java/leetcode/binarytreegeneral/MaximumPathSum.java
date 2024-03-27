package leetcode.binarytreegeneral;

public class MaximumPathSum {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(-1, new TreeNode(8), new TreeNode(2));
		check(tree1, 9);
		tree1 = new TreeNode(-2, new TreeNode(-3), new TreeNode(-1));
		check(tree1, -1);
		tree1 = new TreeNode(0, new TreeNode(1), new TreeNode(1));
		check(tree1, 2);
		tree1 = new TreeNode(-3);
		check(tree1, -3);
		tree1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
		check(tree1, 6);
		tree1 = new TreeNode(-10, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
		check(tree1, 42);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/binary-tree-maximum-path-sum.
	 * This solution traverses the tree preorder and for each node calculates the
	 * maximum path sum and returns the expandable max path sum i.e. the max path
	 * sum which can be expanded by the parent nodes. Time complexity is O(n) where
	 * n is the number of nodes in the tree.
	 * 
	 * @param root
	 * @return
	 */
	public static int maxPathSum(TreeNode root) {
		// early exit for empty tree
		if (root == null) {
			return 0;
		}
		// first element of the array keeps the max path sum
		int[] sum = { root.val };
		// traverse the tree recursively
		getMaxPathSum(root, sum);
		return sum[0];
	}

	/**
	 * This method calculates the maximum path sum for the given node and puts it in
	 * sum[0]. It also calculates the expandable path sum for this node (which
	 * always contains the node value and possibly the path sum of the left or the
	 * right subtree) and returns it so that it can recursively be used by parent
	 * nodes to calculate the max path sum.
	 * 
	 * @param node
	 * @param sum
	 * @return
	 */
	private static int getMaxPathSum(TreeNode node, int[] sum) {
		int leftSum = Integer.MIN_VALUE;
		int rightSum = Integer.MIN_VALUE;
		if (node.left == null && node.right == null) {
			// early exit if it is a leaf node
			return node.val;
		}
		if (node.left != null) {
			// get left subtree max path sum
			leftSum = getMaxPathSum(node.left, sum);
		}
		if (node.right != null) {
			// get right subtree max path sum
			rightSum = getMaxPathSum(node.right, sum);
		}
		// calculate the expandable max sum for this node
		// it will always contain the node value and possibly
		// add to it the max path sum of the left or right subtree
		// (whichever is larger and greater than 0)
		int newExpandableMaxSum = node.val;
		if (leftSum > 0 && leftSum >= rightSum) {
			newExpandableMaxSum += leftSum;
		} else if (rightSum > 0) {
			newExpandableMaxSum += rightSum;
		}
		// calculate the max path sum for this node
		// it could be the sum of all three values (node value, left subtree value,
		// right subtree value)
		// the sum of the node value and one of the left or right subtrees
		// or the maximum of the 3 values
		int newMaxSum = 0;
		if (leftSum <= 0 && rightSum <= 0) {
			// both left subtree and right subtree values are less than or equal to zero
			// so the max path will contain just a single value and it will be the max
			// of the three
			newMaxSum = leftSum > rightSum ? leftSum > node.val ? leftSum : node.val
					: rightSum > node.val ? rightSum : node.val;
		} else if (leftSum > 0 && rightSum > 0) {
			// both left subtree and right subtree values are greater than zero
			// so the max path could contain the sum of all three values
			// or the maximum of this sum and the left and right subtree values
			int tempSum = node.val + leftSum + rightSum;
			newMaxSum = leftSum > rightSum ? leftSum > tempSum ? leftSum : tempSum
					: rightSum > tempSum ? rightSum : tempSum;
		} else if (leftSum > 0) {
			// left subtree value is greater than zero and right subtree value is not
			newMaxSum = node.val > 0 ? node.val + leftSum : leftSum;
		} else {
			// right subtree value is greater than zero and left subtree value is not
			newMaxSum = node.val > 0 ? node.val + rightSum : rightSum;
		}
		// update the max path sum if the new one is greater than the existing one
		if (newMaxSum > sum[0]) {
			sum[0] = newMaxSum;
		}
		return newExpandableMaxSum;
	}

	private static void check(TreeNode root, int expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + expected);
		int maxPathSum = maxPathSum(root);
		System.out.println("maxPathSum is: " + maxPathSum);
	}

}
