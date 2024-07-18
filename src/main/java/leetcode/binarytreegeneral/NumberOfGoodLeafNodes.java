package leetcode.binarytreegeneral;

import java.util.ArrayList;
import java.util.List;

public class NumberOfGoodLeafNodes {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(1, new TreeNode(2, null, new TreeNode(4)), new TreeNode(3));
		check(tree1, 3, 1);
		TreeNode tree2 = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)),
				new TreeNode(3, new TreeNode(6), new TreeNode(7)));
		check(tree2, 3, 2);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/number-of-good-leaf-nodes-pairs. Time
	 * complexity is O(n^2) where n is the number of nodes in the tree.
	 * 
	 * @param root
	 * @param distance
	 * @return
	 */
	public static int countPairs(TreeNode root, int distance) {
		// builds the pats to the leaf nodes
		StringBuilder builder = new StringBuilder();
		// stores the paths to the leaf nodes
		List<String> leafPaths = new ArrayList<>();
		// traverse the tree preorder and populate paths
		findLeafPaths(root, builder, leafPaths);
		int size = leafPaths.size();
		int count = 0;
		// compare all paths to leaf nodes in pairs
		for (int i = 0; i < size; i++) {
			String pathI = leafPaths.get(i);
			int pathILength = pathI.length();
			for (int j = i + 1; j < size; j++) {
				String pathJ = leafPaths.get(j);
				int pathJLength = pathJ.length();
				int minLength = Math.min(pathILength, pathJLength);
				int common = 0;
				for (int k = 0; k < minLength; k++) {
					// ignore common part of path, find indexx of first difference
					if (pathI.charAt(k) != pathJ.charAt(k)) {
						common = k;
						break;
					}
				}
				// calculate the distance between the leaf nodes and increase count 
				// if it is <= distance
				int currentDistance = pathILength + pathJLength - (2 * common);
				if (currentDistance <= distance) {
					count++;
				}
			}
		}
		return count;
	}

	private static void findLeafPaths(TreeNode node, StringBuilder builder, List<String> leafPaths) {
		if (node == null) {
			return;
		} else if (node.left == null && node.right == null) {
			leafPaths.add(builder.toString());
			return;
		}
		builder.append("L");
		findLeafPaths(node.left, builder, leafPaths);
		builder.deleteCharAt(builder.length() - 1);
		builder.append("R");
		findLeafPaths(node.right, builder, leafPaths);
		builder.deleteCharAt(builder.length() - 1);

	}

	private static void check(TreeNode root, int distance, int expected) {
		System.out.println("root is: " + root.printAll());
		System.out.println("distance is: " + distance);
		System.out.println("expected is: " + expected);
		int countPairs = countPairs(root, distance);
		System.out.println("countPairs is: " + countPairs);
	}

}
