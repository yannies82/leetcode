package leetcode.binarytreebfs;

/**
 * Common TreeNode class used in problems.
 * 
 * @author yanni
 *
 */
class TreeNode {
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

	private void print(TreeNode node, StringBuilder builder) {
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
