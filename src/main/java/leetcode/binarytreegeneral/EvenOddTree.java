package leetcode.binarytreegeneral;

public class EvenOddTree {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(1, new TreeNode(10, new TreeNode(3, new TreeNode(12), new TreeNode(8)), null),
				new TreeNode(4, new TreeNode(7, new TreeNode(6), null), new TreeNode(9, null, new TreeNode(2))));
		check(tree1, true);
		tree1 = new TreeNode(5, new TreeNode(4, new TreeNode(3), new TreeNode(3)),
				new TreeNode(2, new TreeNode(7), null));
		check(tree1, false);
	}

	/**
	 * This solution uses DFS traversal and checks the required conditions for each
	 * node of each level. Time complexity is O(n) where n is the number of nodes in
	 * the tree.
	 * 
	 * @param root
	 * @return
	 */
	public static boolean isEvenOddTree(TreeNode root) {
		int[] lastPerLevel = new int[100000];
		return checkEvenOdd(root, 0, lastPerLevel);
	}

	private static boolean checkEvenOdd(TreeNode node, int level, int[] lastPerLevel) {
		int mod2 = level & 1;
		if (mod2 == (node.val & 1)) {
			return false;
		}
		if (lastPerLevel[level] == 0 || (mod2 == 0 && node.val > lastPerLevel[level])
				|| (mod2 == 1 && node.val < lastPerLevel[level])) {
			lastPerLevel[level] = node.val;
		} else {
			return false;
		}
		boolean result = true;
		if (node.left != null) {
			result = checkEvenOdd(node.left, level + 1, lastPerLevel);
		}
		if (node.right != null) {
			result = result && checkEvenOdd(node.right, level + 1, lastPerLevel);
		}
		return result;
	}

	private static void check(TreeNode root, boolean expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + expected);
		boolean isEvenOddTree = isEvenOddTree(root);
		System.out.println("isEvenOddTree is: " + isEvenOddTree);
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
