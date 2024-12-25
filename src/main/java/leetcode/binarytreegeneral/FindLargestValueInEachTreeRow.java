package leetcode.binarytreegeneral;

import java.util.ArrayList;
import java.util.List;

public class FindLargestValueInEachTreeRow {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(1, new TreeNode(3, new TreeNode(5), new TreeNode(3)),
				new TreeNode(2, new TreeNode(9), null));
		check(tree1, List.of(1, 3, 9));
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/find-largest-value-in-each-tree-row. This
	 * solution traverses the tree DFS preorder and keeps track of the level of each
	 * node in order to compare its value with the appropriate result list element.
	 * Time complexity is O(n) where n is the number of elements in the tree.
	 * 
	 * @param root
	 * @return
	 */
	public static List<Integer> largestValues(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		traverse(root, 0, result);
		return result;
	}

	private static void traverse(TreeNode node, int level, List<Integer> result) {
		if (node == null) {
			return;
		}
		int val = node.val;
		if (level == result.size()) {
			result.add(val);
		} else {
			result.set(level, Math.max(result.get(level), val));
		}
		traverse(node.left, level + 1, result);
		traverse(node.right, level + 1, result);
	}

	private static void check(TreeNode root, List<Integer> expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + expected);
		List<Integer> largestValues = largestValues(root);
		System.out.println("largestValues is: " + largestValues);
	}

}
