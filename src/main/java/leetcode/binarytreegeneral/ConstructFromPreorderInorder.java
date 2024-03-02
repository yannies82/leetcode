package leetcode.binarytreegeneral;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ConstructFromPreorderInorder {

	public static void main(String[] args) {
		int[] preorder = { 1, 2, 3 };
		int[] inorder = { 3, 2, 1 };
		TreeNode expected = new TreeNode(1, new TreeNode(2, new TreeNode(3), null), null);
		check(preorder, inorder, expected);
		preorder = new int[] { 3, 9, 20, 15, 7 };
		inorder = new int[] { 9, 3, 15, 20, 7 };
		expected = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
		check(preorder, inorder, expected);
		preorder = new int[] { -1 };
		inorder = new int[] { -1 };
		expected = new TreeNode(-1);
		check(preorder, inorder, expected);
	}

	/**
	 * Recursive solution with cache.
	 * 
	 * @param preorder
	 * @param inorder
	 * @return
	 */
	public static TreeNode buildTree(int[] preorder, int[] inorder) {
		// use cache for O(1) searches of values in inorder
		Map<Integer, Integer> cache = new HashMap<>();
		for (int i = 0; i < inorder.length; i++) {
			cache.put(inorder[i], i);
		}
		// recursively solve the tree
		TreeNode root = solve(preorder, 0, preorder.length, inorder, 0, inorder.length, cache);
		return root;
	}

	/**
	 * The concept is that the problem can be split into smaller similar subproblems
	 * that can be solved recursively. The first element of preorder is the tree
	 * root. Every element of inorder with index less than the index of the root
	 * element belongs to the left subtree. Every element of inorder with index
	 * greater than the index of the root element belongs to the right subtree. We
	 * solve recursively until the arrays contain a single element or no element at
	 * all. Uses a cache for fast O(1) searches for a value in inorder array.
	 * 
	 * @param preorder
	 * @param pStart
	 * @param pEnd
	 * @param inorder
	 * @param iStart
	 * @param iEnd
	 * @return
	 */
	private static TreeNode solve(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd,
			Map<Integer, Integer> cache) {
		if (pStart == pEnd) {
			return null;
		}
		TreeNode root = new TreeNode(preorder[pStart]);
		int offset = cache.get(root.val) - iStart;
		// solve left subtree
		root.left = solve(preorder, pStart + 1, pStart + offset + 1, inorder, iStart, iStart + offset, cache);
		// solve right subtree
		root.right = solve(preorder, pStart + offset + 1, pEnd, inorder, iStart + offset + 1, iEnd, cache);
		return root;
	}

	/**
	 * Recursive solution without cache.
	 * 
	 * @param preorder
	 * @param inorder
	 * @return
	 */
	public static TreeNode buildTree2(int[] preorder, int[] inorder) {
		// recursively solve the tree
		TreeNode root = solve2(preorder, 0, preorder.length, inorder, 0, inorder.length);
		return root;
	}

	/**
	 * The concept is that the problem can be split into smaller similar subproblems
	 * that can be solved recursively. The first element of preorder is the tree
	 * root. Every element of inorder with index less than the index of the root
	 * element belongs to the left subtree. Every element of inorder with index
	 * greater than the index of the root element belongs to the right subtree. We
	 * solve recursively until the arrays contain a single element or no element at
	 * all.
	 * 
	 * @param preorder
	 * @param pStart
	 * @param pEnd
	 * @param inorder
	 * @param iStart
	 * @param iEnd
	 * @return
	 */
	private static TreeNode solve2(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
		if (pStart == pEnd) {
			return null;
		}
		TreeNode root = new TreeNode(preorder[pStart]);
		int offset = 0;
		// find index of root element in inorder
		while (inorder[iStart + offset] != root.val) {
			offset++;
		}
		// solve left subtree
		root.left = solve2(preorder, pStart + 1, pStart + offset + 1, inorder, iStart, iStart + offset);
		// solve right subtree
		root.right = solve2(preorder, pStart + offset + 1, pEnd, inorder, iStart + offset + 1, iEnd);
		return root;
	}

	private static void check(int[] preorder, int[] inorder, TreeNode expected) {
		System.out.println("preorder is: " + Arrays.toString(preorder));
		System.out.println("inorder is: " + Arrays.toString(inorder));
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		TreeNode buildTree = buildTree(preorder, inorder);
		System.out.println("buildTree is: " + (buildTree == null ? null : buildTree.printAll()));
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
