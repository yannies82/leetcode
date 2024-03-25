package leetcode.binarytreegeneral;

import java.util.ArrayDeque;
import java.util.Deque;

public class SameTree {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
		TreeNode tree2 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
		check(tree1, tree2, true);
		tree1 = new TreeNode(1, new TreeNode(2), null);
		tree2 = new TreeNode(1, null, new TreeNode(2));
		check(tree1, tree2, false);
		tree1 = new TreeNode(1, new TreeNode(2), new TreeNode(1));
		tree2 = new TreeNode(1, new TreeNode(1), new TreeNode(2));
		check(tree1, tree2, false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/same-tree. This solution uses
	 * iterative DFS traversal on both trees and compares the value of each node.
	 * Time complexity is O(n) where n is the number of nodes in each tree.
	 * 
	 * @param p
	 * @param q
	 * @return
	 */
	public static boolean isSameTree(TreeNode p, TreeNode q) {
		// early exit if both trees are null
		if (p == null && q == null) {
			return true;
		}
		// early exit if one of the two trees is empty and the other is not
		if (p == null ^ q == null) {
			return false;
		}
		Deque<TreeNode> stackP = new ArrayDeque<>();
		Deque<TreeNode> stackQ = new ArrayDeque<>();
		stackP.offerFirst(p);
		stackQ.offerFirst(q);
		// traverse both trees iteratively preorder using a stack per tree
		while (!stackP.isEmpty() && !stackQ.isEmpty()) {
			TreeNode currentP = stackP.poll();
			TreeNode currentQ = stackQ.poll();
			if (currentP.val != currentQ.val || currentP.right == null ^ currentQ.right == null
					|| currentP.left == null ^ currentQ.left == null) {
				// the trees are not the same if the respective nodes have different value
				// or different children nodes
				return false;
			}
			if (currentP.right != null) {
				// add right children nodes to the stacks
				stackP.offerFirst(currentP.right);
				stackQ.offerFirst(currentQ.right);
			}
			if (currentP.left != null) {
				// add right children nodes to the stacks
				stackP.offerFirst(currentP.left);
				stackQ.offerFirst(currentQ.left);
			}
		}
		return true;
	}

	private static void check(TreeNode p, TreeNode q, boolean expected) {
		System.out.println("p is: " + (p == null ? null : p.printAll()));
		System.out.println("q is: " + (q == null ? null : q.printAll()));
		System.out.println("expected is: " + expected);
		boolean isSameTree = isSameTree(p, q);
		System.out.println("isSameTree is: " + isSameTree);
	}

}
