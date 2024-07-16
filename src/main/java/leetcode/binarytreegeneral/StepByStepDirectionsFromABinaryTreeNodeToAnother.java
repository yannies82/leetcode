package leetcode.binarytreegeneral;

public class StepByStepDirectionsFromABinaryTreeNodeToAnother {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(5, new TreeNode(1, new TreeNode(3), null),
				new TreeNode(2, new TreeNode(6), new TreeNode(4)));
		check(tree1, 3, 6, "UURL");
		TreeNode tree2 = new TreeNode(2, new TreeNode(1), null);
		check(tree2, 2, 1, "L");
		TreeNode tree3 = new TreeNode(5, new TreeNode(8, new TreeNode(3, new TreeNode(1), new TreeNode(4)), null),
				null);
		check(tree3, 4, 3, "U");
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another.
	 * This solution traverses the tree preorder and uses a builder to build the
	 * paths to the start and destination nodes It then uses these paths to build
	 * the final output. Time complexity is O(n) where n is the number of nodes in
	 * the tree.
	 * 
	 * @param root
	 * @param startValue
	 * @param destValue
	 * @return
	 */
	public static String getDirections(TreeNode root, int startValue, int destValue) {
		// builds the paths to the start and dest nodes
		StringBuilder builder = new StringBuilder();
		String[] paths = new String[2];
		findPaths(root, startValue, builder);
		paths[0] = builder.reverse().toString();
		builder.setLength(0);
		findPaths(root, destValue, builder);
		paths[1] = builder.reverse().toString();
		builder.setLength(0);
		// skip the path portion which is the same for the start and dest nodes,
		// start from the first common ancestor
		int minLength = Math.min(paths[0].length(), paths[1].length());
		int lastSameIndex = -1;
		for (int i = 0; i < minLength; i++) {
			if (paths[0].charAt(i) == paths[1].charAt(i)) {
				lastSameIndex = i;
			} else {
				break;
			}
		}
		int index = lastSameIndex + 1;
		// for the start node append U as a direction for all remaining steps
		// (we go backwards so that we can reach the common ancestor)
		for (int i = index; i < paths[0].length(); i++) {
			builder.append("U");
		}
		// for the dest node append directions for all remaining steps
		// (so that we can reach the dest node from the common ancestor)
		for (int i = index; i < paths[1].length(); i++) {
			builder.append(paths[1].charAt(i));
		}
		return builder.toString();
	}

	private static boolean findPaths(TreeNode node, int target, StringBuilder builder) {
		if (node == null) {
			// early exit
			return false;
		} else if (node.val == target) {
			return true;
		}
		if (findPaths(node.left, target, builder)) {
			// generated path will be reversed
			builder.append("L");
			return true;
		} else if (findPaths(node.right, target, builder)) {
			// generated path will be reversed
			builder.append("R");
			return true;
		}
		return false;
	}

	/**
	 * Similar solution which uses backtracking in order to iterate the tree only
	 * once. Time complexity is O(n) where n is the number of nodes in the tree.
	 * 
	 * @param root
	 * @param startValue
	 * @param destValue
	 * @return
	 */
	public static String getDirections2(TreeNode root, int startValue, int destValue) {
		// builds the paths to the start and dest nodes
		StringBuilder builder = new StringBuilder();
		// stores the paths to the start and dest nodes
		String[] paths = new String[2];
		// traverse the tree preorder to find and populate the paths
		findPaths(root, startValue, destValue, builder, paths);
		// reset builder so that it can be reused
		builder.setLength(0);
		// skip the path portion which is the same for the start and dest nodes,
		// start from the first common ancestor
		int minLength = Math.min(paths[0].length(), paths[1].length());
		int lastSameIndex = -1;
		for (int i = 0; i < minLength; i++) {
			if (paths[0].charAt(i) == paths[1].charAt(i)) {
				lastSameIndex = i;
			} else {
				break;
			}
		}
		int index = lastSameIndex + 1;
		// for the start node append U as a direction for all remaining steps
		// (we go backwards so that we can reach the common ancestor)
		for (int i = index; i < paths[0].length(); i++) {
			builder.append("U");
		}
		// for the dest node append directions for all remaining steps
		// (so that we can reach the dest node from the common ancestor)
		for (int i = index; i < paths[1].length(); i++) {
			builder.append(paths[1].charAt(i));
		}
		return builder.toString();
	}

	private static void findPaths(TreeNode node, int startValue, int destValue, StringBuilder builder, String[] paths) {
		if (node == null) {
			// early exit
			return;
		} else if (node.val == startValue) {
			// we have reached the start node, store path
			paths[0] = builder.toString();
		} else if (node.val == destValue) {
			// we have reached the dest node, store path
			paths[1] = builder.toString();
		}
		// append to path builder and traverse the left node
		builder.append("L");
		findPaths(node.left, startValue, destValue, builder, paths);
		// remove last direction after traversing
		builder.deleteCharAt(builder.length() - 1);
		// append to path builder and traverse the right node
		builder.append("R");
		findPaths(node.right, startValue, destValue, builder, paths);
		builder.deleteCharAt(builder.length() - 1);
	}

	private static void check(TreeNode root, int startValue, int destValue, String expected) {
		System.out.println("root is: " + root.printAll());
		System.out.println("expected is: " + expected);
		String getDirections = getDirections(root, startValue, destValue);
		System.out.println("getDirections is: " + getDirections);
	}

}
