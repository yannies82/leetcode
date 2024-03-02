package leetcode.binarytreegeneral;

public class MaximumDepthOfBinaryTree {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
		check(tree1, 3);
		tree1 = new TreeNode(1, null, new TreeNode(2));
		check(tree1, 2);
	}

	/**
	 * Recursive implementation.
	 * 
	 * @param root
	 * @return
	 */
	public static int maxDepth(TreeNode root) {
		// early exit for empty tree
		if (root == null) {
			return 0;
		}
		// first element of the array keeps the current depth
		// second element keeps the maximum depth
		int[] count = { 0, 0 };
		// traverse the tree recursively
		traverse(root, count);
		return count[1];
	}

	private static void traverse(TreeNode node, int[] count) {
		// increase the depth counter
		count[0]++;
		if (count[1] < count[0]) {
			// increase the max depth counter if applicable
			count[1] = count[0];
		}
		if (node.left != null) {
			// traverse left node recursively
			traverse(node.left, count);
		}
		if (node.right != null) {
			// traverse right node recursively
			traverse(node.right, count);
		}
		// decrease the depth counter
		count[0]--;
	}

	private static void check(TreeNode root, int expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + expected);
		int maxDepth = maxDepth(root);
		System.out.println("maxDepth is: " + maxDepth);
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
