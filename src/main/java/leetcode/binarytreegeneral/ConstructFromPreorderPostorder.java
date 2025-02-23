package leetcode.binarytreegeneral;

import java.util.Arrays;

public class ConstructFromPreorderPostorder {

	public static void main(String[] args) {
		check(new int[] { 1, 2, 4, 5, 3, 6, 7 }, new int[] { 4, 5, 2, 6, 7, 3, 1 }, new TreeNode(1,
				new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3, new TreeNode(6), new TreeNode(7))));
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal.
	 * This solution uses the preorder array to create the root of the left
	 * subtrees, then locates it in the postorder array to recursively create the
	 * left and right subtree. Time complexity is O(n) where n is the length of the
	 * preorder and postorder arrays.
	 * 
	 * @param preorder
	 * @param postorder
	 * @return
	 */
	public static TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
		// use cache for O(1) searches of values in inorder
		int[] cache = new int[31];
		for (int i = 0; i < postorder.length; i++) {
			cache[postorder[i]] = i;
		}
		// recursively solve the tree
		TreeNode root = solve(preorder, 0, preorder.length - 1, postorder, 0, postorder.length - 1, cache);
		return root;
	}

	private static TreeNode solve(int[] preorder, int pStart, int pEnd, int[] postorder, int iStart, int iEnd,
			int[] cache) {
		if (pStart > pEnd) {
			// no nodes left, return null
			return null;
		}
		// create root from first preorder node, it should be the last in the postorder
		// subarray
		TreeNode root = new TreeNode(preorder[pStart]);
		int nextPreIndex = pStart + 1;
		if (nextPreIndex > pEnd) {
			return root;
		}
		// the next node from the root in the preorder array is the root of the left
		// subtree, locate it in the postorder array
		int postEndIndex = cache[preorder[nextPreIndex]];
		int offset = postEndIndex - iStart;
		// solve left subtree
		root.left = solve(preorder, nextPreIndex, nextPreIndex + offset, postorder, iStart, postEndIndex, cache);
		// solve right subtree
		root.right = solve(preorder, nextPreIndex + offset + 1, pEnd, postorder, postEndIndex + 1, iEnd - 1, cache);
		return root;
	}

	private static void check(int[] preorder, int[] postorder, TreeNode expected) {
		System.out.println("preorder is: " + Arrays.toString(preorder));
		System.out.println("postorder is: " + Arrays.toString(postorder));
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		TreeNode constructFromPrePost = constructFromPrePost(preorder, postorder);
		System.out.println(
				"constructFromPrePost is: " + (constructFromPrePost == null ? null : constructFromPrePost.printAll()));
	}

}
