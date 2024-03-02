package leetcode.binarytreegeneral;

public class DiameterOfBinaryTree {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3));
		check(tree1, 3);
		tree1 = new TreeNode(1, new TreeNode(2), null);
		check(tree1, 1);
	}

	/**
	 * This solution recursively calculates the heights of the left and right
	 * subtrees and updates the max diameter accordingly. Time complexity is O(n)
	 * where n is the number of nodes in the tree.
	 * 
	 * @param root
	 * @return
	 */
	public static int diameterOfBinaryTree(TreeNode root) {
		// keeps the max diameter
		int[] maxDiameter = { 0 };
		// recursively calculates the heights of all subtrees and updates max diameter
		findHeight(root, maxDiameter);
		return maxDiameter[0];
	}

	private static int findHeight(TreeNode node, int[] maxDiameter) {
		// early exit if node is null
		if (node == null) {
			return 0;
		}
		// calculate left subtree height
		int leftSubtreeHeight = findHeight(node.left, maxDiameter);
		// calculate right subtree height
		int rightSubtreeHeight = findHeight(node.right, maxDiameter);
		// calculate diametewr
		int diameter = leftSubtreeHeight + rightSubtreeHeight;
		// update max diameter
		if (diameter > maxDiameter[0]) {
			maxDiameter[0] = diameter;
		}
		// calculate and return height
		int height = Math.max(leftSubtreeHeight, rightSubtreeHeight) + 1;
		return height;
	}

	private static void check(TreeNode root, int expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + expected);
		int diameterOfBinaryTree = diameterOfBinaryTree(root);
		System.out.println("diameterOfBinaryTree is: " + diameterOfBinaryTree);
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
