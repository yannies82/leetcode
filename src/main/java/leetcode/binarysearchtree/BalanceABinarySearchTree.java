package leetcode.binarysearchtree;

import java.util.ArrayList;
import java.util.List;

public class BalanceABinarySearchTree {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3, null, new TreeNode(4))));
		TreeNode expected1 = new TreeNode(3, new TreeNode(2, new TreeNode(1), null), new TreeNode(4));
		check(tree1, expected1);
		tree1 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
		expected1 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
		check(tree1, expected1);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/balance-a-binary-search-tree.
	 * This solution traverses the given tree inorder and puts its values in an
	 * array, so that they are sorted. It then creates a new tree recursively by
	 * splitting the array range in half at each step. Time complexity is O(n) where
	 * n is the number of nodes in the input BST.
	 * 
	 * @param root
	 * @return
	 */
	public static TreeNode balanceBST(TreeNode root) {
		List<Integer> nodesList = new ArrayList<>();
		traverseInorder(root, nodesList);
		TreeNode newRoot = createBalanced(nodesList, 0, nodesList.size());
		return newRoot;
	}

	private static void traverseInorder(TreeNode node, List<Integer> nodesList) {
		if (node == null) {
			return;
		}
		traverseInorder(node.left, nodesList);
		nodesList.add(node.val);
		traverseInorder(node.right, nodesList);
	}

	private static TreeNode createBalanced(List<Integer> nodesList, int start, int end) {
		if (start >= end) {
			return null;
		}
		int mid = (start + end) / 2;
		TreeNode left = createBalanced(nodesList, start, mid);
		TreeNode right = createBalanced(nodesList, mid + 1, end);
		TreeNode newNode = new TreeNode(nodesList.get(mid), left, right);
		return newNode;
	}

	private static void check(TreeNode root, TreeNode expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + expected.printAll());
		TreeNode balanceBST = balanceBST(root);
		System.out.println("balanceBST is: " + balanceBST.printAll());
	}

}
