package leetcode.binarytreegeneral;

public class SumRootToLeafNumbers {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
		check(tree1, 25);
		tree1 = new TreeNode(4, new TreeNode(9, new TreeNode(5), new TreeNode(1)), new TreeNode(0));
		check(tree1, 1026);
	}

	/**
	 * Recursive implementation.
	 * 
	 * @param root
	 * @return
	 */
	public static int sumNumbers(TreeNode root) {
		// early exit for empty tree
		if (root == null) {
			return 0;
		}
		// first element of the array keeps the sum
		int[] sum = { 0 };
		// traverse the tree recursively
		traverse(root, sum, 0);
		return sum[0];
	}

	private static void traverse(TreeNode node, int[] sum, int currentNumber) {
		// calculate the current number
		currentNumber = currentNumber * 10 + node.val;
		// if it is a leaf node check add current number to the sum
		if (node.left == null && node.right == null) {
			sum[0] += currentNumber;
			return;
		}
		if (node.left != null) {
			// traverse left node recursively
			traverse(node.left, sum, currentNumber);
		}
		if (node.right != null) {
			// traverse right node recursively
			traverse(node.right, sum, currentNumber);
		}
	}

	private static void check(TreeNode root, int expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + expected);
		int sumNumbers = sumNumbers(root);
		System.out.println("sumNumbers is: " + sumNumbers);
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
