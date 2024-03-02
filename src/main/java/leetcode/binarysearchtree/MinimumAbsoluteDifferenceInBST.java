package leetcode.binarysearchtree;

public class MinimumAbsoluteDifferenceInBST {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(6));
		check(tree1, 1);
		tree1 = new TreeNode(1, new TreeNode(0), new TreeNode(48, new TreeNode(12), new TreeNode(49)));
		check(tree1, 1);
	}

	/**
	 * Recursive solution, using inorder traversal.
	 * 
	 * @param root
	 * @return
	 */
	public static int getMinimumDifference(TreeNode root) {
		// first element of the array is the previous element
		// second element of the array is the minDiff
		int[] arr = { -1000000, Integer.MAX_VALUE };
		// traverse the tree inorder
		traverse(root, arr);
		return arr[1];
	}

	private static void traverse(TreeNode node, int[] arr) {
		if (node.left != null) {
			traverse(node.left, arr);
		}
		// calculate the difference, set it as min difference
		// if it is less than the current minDiff
		int diff = node.val - arr[0];
		if (diff < arr[1]) {
			arr[1] = diff;
		}
		arr[0] = node.val;
		if (node.right != null) {
			traverse(node.right, arr);
		}
	}

	private static void check(TreeNode root, int expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + expected);
		int getMinimumDifference = getMinimumDifference(root);
		System.out.println("getMinimumDifference is: " + getMinimumDifference);
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
			if (node.left == null) {
				builder.append(", null");
			} else {
				print(node.left, builder);
			}
			if (node.right == null) {
				builder.append(", null");
			} else {
				print(node.right, builder);
			}
		}

	}

}
