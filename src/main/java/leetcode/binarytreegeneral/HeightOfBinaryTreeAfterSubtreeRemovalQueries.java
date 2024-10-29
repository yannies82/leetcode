package leetcode.binarytreegeneral;

import java.util.Arrays;

public class HeightOfBinaryTreeAfterSubtreeRemovalQueries {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(1, new TreeNode(3, new TreeNode(2), null),
				new TreeNode(4, new TreeNode(6), new TreeNode(5, null, new TreeNode(7))));
		check(tree1, new int[] { 4 }, new int[] { 2 });
		tree1 = new TreeNode(5, new TreeNode(8, new TreeNode(2, new TreeNode(4), new TreeNode(6)), new TreeNode(1)),
				new TreeNode(9, new TreeNode(3), new TreeNode(7)));
		check(tree1, new int[] { 3, 2, 4, 8 }, new int[] { 3, 2, 3, 2 });
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries.
	 * This solution traverses the tree preorder, assigning incrementing ids to the
	 * nodes and keeping their distance from their root and the subtree heights to
	 * calculate the queries in O(1) time. Time complexity is O(n) where n is the
	 * number of nodes in the tree.
	 * 
	 * @param root
	 * @param queries
	 * @return
	 */
	public static int[] treeQueries(TreeNode root, int[] queries) {
		// maps an id to the node with the node value as key
		int[] idMap = new int[100001];
		// maps the node distance from the root to the node with this id as key
		int[] distanceFromRootMap = new int[100001];
		// maps the subtree node count to the node with this id as key
		int[] subTreeNodeCountMap = new int[100001];
		// this wrapper array is used to provide id values to nodes as they are
		// traversed preorder, this way a node and its children get sequential ids
		int[] idWrapper = { 0 };
		// traverse the tree preorder and fill the node height info
		traverse(root, 0, idMap, distanceFromRootMap, subTreeNodeCountMap, idWrapper);
		int id = idWrapper[0];
		// left[i] keeps the max tree height for all nodes with ids <= i
		int[] left = new int[id];
		left[0] = distanceFromRootMap[0];
		for (int i = 1; i < id; i++) {
			left[i] = Math.max(left[i - 1], distanceFromRootMap[i]);
		}
		// right[i] keeps the max tree height for all nodes with ids >= i
		int[] right = new int[id];
		right[id - 1] = distanceFromRootMap[id - 1];
		for (int i = id - 2; i >= 0; i--) {
			right[i] = Math.max(right[i + 1], distanceFromRootMap[i]);
		}
		// process queries
		int[] result = new int[queries.length];
		for (int i = 0; i < queries.length; i++) {
			int startIndex = idMap[queries[i]];
			// since the ids were given sequentially while traversing the tree preorder,
			// excluding node q[i] is equal to disregarding the ids from startIndex to
			// startIndex + subTreeHeight - 1
			int endIndex = startIndex + subTreeNodeCountMap[startIndex] - 1;
			// the result for this query is the bigger value between the height
			// of the highest tree from the left with id less than startIndex and the height
			// of the highest tree from the right with id greater than endIndex
			int height = left[startIndex - 1];
			if (endIndex < id - 1) {
				height = Math.max(height, right[endIndex + 1]);
			}
			result[i] = height;
		}
		return result;
	}

	private static int traverse(TreeNode root, int height, int[] idMap, int[] distanceFromRootMap,
			int[] subTreeNodeCountMap, int[] idWrapper) {
		if (root == null) {
			// early exit in case of empty node
			return 0;
		}
		// get the next incremental id
		int id = idWrapper[0]++;
		// assign the id to the node value
		idMap[root.val] = id;
		// assign the height (distance) from the root to this id
		distanceFromRootMap[id] = height;
		int leftSubTreeNodeCount = traverse(root.left, height + 1, idMap, distanceFromRootMap, subTreeNodeCountMap,
				idWrapper);
		int rightSubTreeNodeCount = traverse(root.right, height + 1, idMap, distanceFromRootMap, subTreeNodeCountMap,
				idWrapper);
		int nodeCount = 1 + leftSubTreeNodeCount + rightSubTreeNodeCount;
		subTreeNodeCountMap[id] = nodeCount;
		return nodeCount;
	}

	private static void check(TreeNode root, int[] queries, int[] expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("queries is: " + Arrays.toString(queries));
		System.out.println("expected is: " + Arrays.toString(expected));
		int[] treeQueries = treeQueries(root, queries);
		System.out.println("treeQueries is: " + Arrays.toString(treeQueries));
	}

}
