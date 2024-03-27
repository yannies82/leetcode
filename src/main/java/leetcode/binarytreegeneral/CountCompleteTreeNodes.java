package leetcode.binarytreegeneral;

public class CountCompleteTreeNodes {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)),
				new TreeNode(3, new TreeNode(6), new TreeNode(7)));
		check(tree1, 7);
		tree1 = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)),
				new TreeNode(3, new TreeNode(6), null));
		check(tree1, 6);
		tree1 = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3, null, null));
		check(tree1, 5);
		tree1 = new TreeNode(1, new TreeNode(2, new TreeNode(4), null), new TreeNode(3, null, null));
		check(tree1, 4);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/count-complete-tree-nodes.
	 * This solution counts the tree height by finding the leftmost node, then
	 * performs a binary search for the index of the last element in the last line.
	 * It probes the possible paths according to the search index. In this way we
	 * avoid a full tree traversal and the time complexity is O(height * logn)
	 * instead of O(logn).
	 * 
	 * @param root
	 * @return
	 */
	public static int countNodes(TreeNode root) {
		// early exit if root is null
		if (root == null) {
			return 0;
		}
		// early exit if height is 1
		if (root.left == null) {
			return 1;
		}
		// find tree height
		int height = 0;
		TreeNode current = root;
		while (current != null) {
			height++;
			current = current.left;
		}
		// find the sum of elements of all lines except for the last
		int sumElements = 1;
		for (int i = 1; i < height - 1; i++) {
			sumElements += 2 << i - 1;
		}
		// perform a binary search in the last line to find the
		// index of the last element (1-based)
		int lastLinePossibleElements = 2 << height - 2;
		int low = 1;
		int high = lastLinePossibleElements;
		int mid;
		// repeat the search by narrowing down the range until mid == low
		while ((mid = (low + high + 1) >> 1) > low) {
			// find the tree height for last line index mid
			int count = probe(root, lastLinePossibleElements, mid);
			if (count < height) {
				// if the tree height for index mid is less than the actual tree height
				// this means that the element at this index does not exist
				// next time search the bottom half
				high = mid - 1;
			} else {
				// if the tree height for index mid is equal to the actual tree height
				// this means that the element at this index exists
				// next time search the upper half
				low = mid;
			}
		}
		// add the index of the last existing element to the sum of elements
		sumElements += low;

		return sumElements;
	}

	private static int probe(TreeNode root, int lastLinePossibleElements, int searchIndex) {
		int count = 0;
		TreeNode current = root;
		while (current != null) {
			count++;
			if (searchIndex <= (lastLinePossibleElements = lastLinePossibleElements >> 1)) {
				current = current.left;
			} else {
				current = current.right;
				searchIndex -= lastLinePossibleElements;
			}
		}
		return count;
	}

	/**
	 * Simple recursive solution with O(n) time complexity.
	 * 
	 * @param root
	 * @return
	 */
	public static int countNodes2(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return countNodes(root.left) + countNodes(root.right) + 1;
	}

	private static void check(TreeNode root, int expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + expected);
		int countNodes = countNodes(root);
		System.out.println("countNodes is: " + countNodes);
	}

}
