package leetcode.binarytreegeneral;

public class SmallestStringStartingFromLeaf {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(0, new TreeNode(1, new TreeNode(3), new TreeNode(4)),
				new TreeNode(2, new TreeNode(3), new TreeNode(4)));
		check(tree1, "dba");
		tree1 = new TreeNode(25, new TreeNode(1, new TreeNode(1), new TreeNode(3)),
				new TreeNode(3, new TreeNode(0), new TreeNode(2)));
		check(tree1, "adz");
		tree1 = new TreeNode(2, new TreeNode(2, null, new TreeNode(1, new TreeNode(0), null)),
				new TreeNode(1, new TreeNode(0), null));
		check(tree1, "abc");
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/smallest-string-starting-from-leaf. Time
	 * complexity is O(n) where n is the number of nodes in the tree.
	 * 
	 * @param root
	 * @return
	 */
	public static String smallestFromLeaf(TreeNode root) {
		// keeps the smallest string
		String[] smallest = new String[] { "" };
		// builds strings to be compared against the smallest
		StringBuilder builder = new StringBuilder();
		// traverse the tree preorder
		solve(root, builder, smallest);
		// the smallest element is stored reversed
		// return it after restoring it
		return new StringBuilder(smallest[0]).reverse().toString();
	}

	private static void solve(TreeNode node, StringBuilder builder, String[] smallest) {
		if (node == null) {
			return;
		}
		// append the node value as a character to the string builder
		builder.append((char) ('a' + node.val));
		if (node.left == null && node.right == null) {
			// this is a leaf node, compare the built string against the smallest one
			// and update the smallest if necessary
			String word = builder.toString();
			if (smallest[0].isEmpty() || compareReversed(word, smallest[0]) < 0) {
				smallest[0] = word;
			}
		} else {
			// this node has children, traverse them as well
			solve(node.left, builder, smallest);
			solve(node.right, builder, smallest);
		}
		// backtrack after traversing this node
		builder.deleteCharAt(builder.length() - 1);
	}

	/**
	 * Compares two reversed string, starting from their last characters.
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	private static int compareReversed(String s1, String s2) {
		int index1 = s1.length() - 1;
		int index2 = s2.length() - 1;
		while (index1 >= 0 && index2 >= 0) {
			if (s1.charAt(index1) != s2.charAt(index2)) {
				return s1.charAt(index1) - s2.charAt(index2);
			}
			index1--;
			index2--;
		}
		if (index1 < 0 && index2 < 0) {
			return 0;
		} else if (index1 < 0) {
			return -1;
		}
		return 1;
	}

	private static void check(TreeNode root, String expected) {
		System.out.println("root is: " + root);
		System.out.println("expected is: " + expected);
		String smallestFromLeaf = smallestFromLeaf(root);
		System.out.println("smallestFromLeaf is: " + smallestFromLeaf);
	}

}
