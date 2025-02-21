package leetcode.binarysearchtree;

public class FindElementsInAContaminatedBinaryTree {

	public static void main(String[] args) {
		FindElements findElements = new FindElements(
				new TreeNode(-1, new TreeNode(-1, new TreeNode(-1), new TreeNode(-1)), null));
		check(findElements, 0, true);
		check(findElements, 1, true);
		check(findElements, 2, false);
		check(findElements, 3, true);
		check(findElements, 4, true);
		check(findElements, 5, false);
		check(findElements, 8, false);
		findElements = new FindElements(new TreeNode(-1, null, new TreeNode(-1)));
		check(findElements, 0, true);
		check(findElements, 1, false);
		check(findElements, 2, true);
		findElements = new FindElements(
				new TreeNode(-1, new TreeNode(-1, new TreeNode(-1), new TreeNode(-1)), new TreeNode(-1)));
		check(findElements, 0, true);
		check(findElements, 1, true);
		check(findElements, 3, true);
		check(findElements, 5, false);
		findElements = new FindElements(
				new TreeNode(-1, null, new TreeNode(-1, new TreeNode(-1, new TreeNode(-1), null), new TreeNode(-1))));
		check(findElements, 2, true);
		check(findElements, 3, false);
		check(findElements, 4, false);
		check(findElements, 5, true);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/find-elements-in-a-contaminated-binary-tree.
	 * This solution considers the tree to be a binary search tree and performs a
	 * binary search in order to find the target value. Time complexity of find is
	 * O(n) where n is the height of the tree.
	 * 
	 * @author yanni
	 *
	 */
	public static class FindElements {

		private TreeNode root;

		public FindElements(TreeNode root) {
			this.root = root;
		}

		public boolean find(int target) {
			// increase the target by 1 so that the search is equivalent to finding target+1
			// in a binary search tree with 1 as the root node
			// the level of the target in the tree is finalTarget / 2 and the index of the
			// target in the level is finalTarget mod 2
			int finalTarget = target + 1;
			// find the level (highest order bit) of finalTarget
			int level = findLevel(finalTarget);
			// find the index of finalTarget in its level
			int index = finalTarget % (1 << level);
			return findRecursive(this.root, level, index);
		}

		private int findLevel(int finalTarget) {
			int level = 0;
			while ((finalTarget = finalTarget >>> 1) > 0) {
				level++;
			}
			return level;
		}

		private boolean findRecursive(TreeNode current, int level, int index) {
			if (current == null) {
				// current level does not exist, therefore the target does not exist in the tree
				return false;
			}
			if (level == 0) {
				// we have reached the target node, it exists
				return true;
			}
			// we have not reached the target node yet, search in the left or right subtree
			// determined by the index of the target in the level compared to the middle
			// index of the level
			int nextLevel = level - 1;
			int midIndex = 1 << nextLevel;
			if (index >= midIndex) {
				// target exists on the right subtree, subtract midIndex from index and search
				return findRecursive(current.right, nextLevel, index - midIndex);
			}
			// target exists on the left subtree, search there
			return findRecursive(current.left, nextLevel, index);
		}
	}

	private static void check(FindElements findElements, int target, boolean expected) {
		System.out.println("find(" + target + ") is: " + findElements.find(target) + ", expected is: " + expected);
	}

}
