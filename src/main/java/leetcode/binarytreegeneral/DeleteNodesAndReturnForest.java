package leetcode.binarytreegeneral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeleteNodesAndReturnForest {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)),
				new TreeNode(3, new TreeNode(6), new TreeNode(7)));
		List<TreeNode> expected1 = List.of(new TreeNode(1, new TreeNode(2, new TreeNode(4), null), null),
				new TreeNode(6), new TreeNode(7));
		check(tree1, new int[] { 3, 5 }, expected1);
		TreeNode tree2 = new TreeNode(1, new TreeNode(2, new TreeNode(3), null), new TreeNode(4));
		List<TreeNode> expected2 = List.of(new TreeNode(1, new TreeNode(2), new TreeNode(4)));
		check(tree2, new int[] { 3 }, expected2);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/delete-nodes-and-return-forest. This solution
	 * traverses the tree preorder and performs the removal of the target nodes,
	 * also keeping new roots in a result list. Time complexity is O(n) where n is
	 * the number of nodes in the tree.
	 * 
	 * @param root
	 * @param to_delete
	 * @return
	 */
	public static List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
		// keep the values to delete as indexes in a boolean array
		boolean[] deletionArray = new boolean[1001];
		for (int i = 0; i < to_delete.length; i++) {
			deletionArray[to_delete[i]] = true;
		}
		List<TreeNode> result = new ArrayList<>();
		// if the root is not to be deleted, add it to the result
		if (!deletionArray[root.val]) {
			result.add(root);
		}
		// delete nodes recursively and populate the result list
		deleteRecursive(root, null, null, deletionArray, result);
		return result;
	}

	private static void deleteRecursive(TreeNode node, TreeNode leftParent, TreeNode rightParent,
			boolean[] deletionArray, List<TreeNode> result) {
		if (node == null) {
			return;
		}
		TreeNode left = node.left;
		TreeNode right = node.right;
		if (deletionArray[node.val]) {
			// node will be deleted
			if (left != null) {
				// remove reference to left node
				node.left = null;
				if (!deletionArray[left.val]) {
					// if the left node will not be removed as well, it will be a new root
					result.add(left);
				}
			}
			if (right != null) {
				// remove reference to right node
				node.right = null;
				// if the right node will not be removed as well, it will be a new root
				if (!deletionArray[right.val]) {
					result.add(right);
				}
			}
			if (leftParent != null) {
				// remove parent's left reference to this node
				leftParent.left = null;
			} else if (rightParent != null) {
				// remove parent's right reference to this node
				rightParent.right = null;
			}
		}
		deleteRecursive(left, node, null, deletionArray, result);
		deleteRecursive(right, null, node, deletionArray, result);
	}

	private static void check(TreeNode root, int[] to_delete, List<TreeNode> expected) {
		System.out.println("root is: " + root.printAll());
		System.out.println("to_delete is: " + Arrays.toString(to_delete));
		System.out.println("expected is: [");
		for (int i = 0; i < expected.size() - 1; i++) {
			System.out.println("[" + expected.get(i).printAll() + "],");
		}
		System.out.println("[" + expected.get(expected.size() - 1).printAll() + "]");
		List<TreeNode> delNodes = delNodes(root, to_delete);
		System.out.println("delNodes is: [");
		for (int i = 0; i < delNodes.size() - 1; i++) {
			System.out.println("[" + delNodes.get(i).printAll() + "],");
		}
		System.out.println("[" + delNodes.get(delNodes.size() - 1).printAll() + "]");
	}

}
