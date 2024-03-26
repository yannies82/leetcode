package leetcode.binarytreegeneral;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ConstructFromInorderPostorder {

	public static void main(String[] args) {
		int[] inorder = { 9, 3, 15, 20, 7 };
		int[] postorder = { 9, 15, 7, 20, 3 };
		TreeNode expected = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
		check(inorder, postorder, expected);
		inorder = new int[] { -1 };
		postorder = new int[] { -1 };
		expected = new TreeNode(-1);
		check(postorder, inorder, expected);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal.
	 * This solution splits the problem into smaller subproblems and solves them
	 * recursively.For every subproblem a range in the postorder and the inorder
	 * array is provided. The last element of the postorder array is the root
	 * element of the subtree. The elements in the inorder array which are before
	 * the root element belong to the left subtree of the root element and the
	 * elements after the root element belong to the right subtree. The left and
	 * right tree are solved recursively. Time complexity is O(n) where n is the
	 * number of nodes in the tree.
	 * 
	 * @param inorder
	 * @param postorder
	 * @return
	 */
	public static TreeNode buildTree(int[] inorder, int[] postorder) {
		// use cache for O(1) searches of values in inorder
		Map<Integer, Integer> cache = new HashMap<>();
		for (int i = 0; i < inorder.length; i++) {
			cache.put(inorder[i], i);
		}
		// recursively solve the tree
		TreeNode root = solve(postorder, 0, postorder.length, inorder, 0, inorder.length, cache);
		return root;
	}

	/**
	 * The concept is that the problem can be split into smaller similar subproblems
	 * that can be solved recursively. The last element of postorder is the tree
	 * root. Every element of inorder with index greater than the index of the root
	 * element belongs to the right subtree. Every element of inorder with index
	 * less than the index of the root element belongs to the left subtree. We solve
	 * recursively until the arrays contain a single element or no element at all.
	 * Uses a cache for fast O(1) searches for a value in inorder array.
	 * 
	 * @param postorder
	 * @param pStart
	 * @param pEnd
	 * @param inorder
	 * @param iStart
	 * @param iEnd
	 * @return
	 */
	private static TreeNode solve(int[] postorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd,
			Map<Integer, Integer> cache) {
		if (pStart == pEnd) {
			return null;
		}
		TreeNode root = new TreeNode(postorder[pEnd - 1]);
		int offset = iEnd - 1 - cache.get(root.val);
		// solve left subtree
		root.left = solve(postorder, pStart, pEnd - offset - 1, inorder, iStart, iEnd - offset - 1, cache);
		// solve right subtree
		root.right = solve(postorder, pEnd - offset - 1, pEnd - 1, inorder, iEnd - offset, iEnd, cache);
		return root;
	}

	/**
	 * Recursive solution without cache.
	 * 
	 * @param inorder
	 * @param postorder
	 * @return
	 */
	public static TreeNode buildTree2(int[] inorder, int[] postorder) {
		// recursively solve the tree
		TreeNode root = solve2(postorder, 0, postorder.length, inorder, 0, inorder.length);
		return root;
	}

	/**
	 * The concept is that the problem can be split into smaller similar subproblems
	 * that can be solved recursively. The last element of postorder is the tree
	 * root. Every element of inorder with index greater than the index of the root
	 * element belongs to the right subtree. Every element of inorder with index
	 * less than the index of the root element belongs to the left subtree. We solve
	 * recursively until the arrays contain a single element or no element at all.
	 * 
	 * @param postorder
	 * @param pStart
	 * @param pEnd
	 * @param inorder
	 * @param iStart
	 * @param iEnd
	 * @return
	 */
	private static TreeNode solve2(int[] postorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
		if (pStart == pEnd) {
			return null;
		}
		TreeNode root = new TreeNode(postorder[pEnd - 1]);
		int offset = 0;
		// find index of root element in inorder
		while (inorder[iEnd - 1 - offset] != root.val) {
			offset++;
		}
		// solve left subtree
		root.left = solve2(postorder, pStart, pEnd - offset - 1, inorder, iStart, iEnd - offset - 1);
		// solve right subtree
		root.right = solve2(postorder, pEnd - offset - 1, pEnd - 1, inorder, iEnd - offset, iEnd);
		return root;
	}

	private static void check(int[] inorder, int[] postorder, TreeNode expected) {
		System.out.println("inorder is: " + Arrays.toString(inorder));
		System.out.println("postorder is: " + Arrays.toString(postorder));
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		TreeNode buildTree = buildTree(inorder, postorder);
		System.out.println("buildTree is: " + (buildTree == null ? null : buildTree.printAll()));
	}

}
