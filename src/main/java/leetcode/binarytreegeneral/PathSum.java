package leetcode.binarytreegeneral;

public class PathSum {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(1, null, new TreeNode(2));
		check(tree1, 1, false);
		tree1 = new TreeNode(5, new TreeNode(4, new TreeNode(11, new TreeNode(7), new TreeNode(2)), null),
				new TreeNode(8, new TreeNode(13), new TreeNode(4, null, new TreeNode(1))));
		check(tree1, 22, true);
		tree1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
		check(tree1, 5, false);
		check(null, 0, false);
	}

	/**
	 * Recursive implementation.
	 * 
	 * @param root
	 * @return
	 */
	public static boolean hasPathSum(TreeNode root, int targetSum) {
		// early exit for empty tree
		if (root == null) {
			return false;
		}
		// first element of the array keeps the current sum
		int sum = 0;
		// traverse the tree recursively
		return traverse(root, sum, targetSum);
	}

	private static boolean traverse(TreeNode node, int sum, int targetSum) {
		// add node value to the sum
		sum += node.val;
		// if it is a leaf node check if the sum is equal to the target sum
		if (node.left == null && node.right == null) {
			if (sum == targetSum) {
				return true;
			} else {
				return false;
			}
		}
		boolean result = false;
		if (node.left != null) {
			// traverse left node recursively
			result = traverse(node.left, sum, targetSum);
		}
		if (node.right != null) {
			// traverse right node recursively
			result = result || traverse(node.right, sum, targetSum);
		}
		return result;
	}

	private static void check(TreeNode root, int targetSum, boolean expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("targetSum is: " + targetSum);
		System.out.println("expected is: " + expected);
		boolean hasPathSum = hasPathSum(root, targetSum);
		System.out.println("hasPathSum is: " + hasPathSum);
	}

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}

		String printAll() {
			TreeNode current = this;
			StringBuilder result = new StringBuilder();
			print(current, result);
			return result.toString();
		}

		void print(TreeNode node, StringBuilder builder) {
			if (!builder.isEmpty()) {
				builder.append(",");
			}
			builder.append(node.val);
			if (node.left != null) {
				print(node.left, builder);
			}
			if (node.right != null) {
				print(node.right, builder);
			}
		}

	}

}
