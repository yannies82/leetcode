package leetcode.binarytreegeneral;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CreateBinaryTreeFromDescriptions {

	public static void main(String[] args) {
		int[][] descriptions = new int[][] { { 20, 15, 1 }, { 20, 17, 0 }, { 50, 20, 1 }, { 50, 80, 0 },
				{ 80, 19, 1 } };
		TreeNode expected = new TreeNode(50, new TreeNode(20, new TreeNode(15), new TreeNode(17)),
				new TreeNode(80, new TreeNode(19), null));
		check(descriptions, expected);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/create-binary-tree-from-descriptions. This
	 * solution iterates the descriptions array to construct the tree and selects as
	 * parent the node that is never a child. Time complexity is O(n) where n is the
	 * length of the descriptions array.
	 * 
	 * @param descriptions
	 * @return
	 */
	public static TreeNode createBinaryTree(int[][] descriptions) {
		// keeps all nodes with their value as key
		Map<Integer, TreeNode> nodeMap = new HashMap<>();
		// keeps the values of all children
		Set<Integer> children = new HashSet<>();
		for (int i = 0; i < descriptions.length; i++) {
			// get the child node from the map or create it if it does not exist yet
			TreeNode child = nodeMap.computeIfAbsent(descriptions[i][1], TreeNode::new);
			children.add(descriptions[i][1]);
			// get the parent node from the map or create it if it does not exist yet
			TreeNode parent = nodeMap.computeIfAbsent(descriptions[i][0], TreeNode::new);
			if (descriptions[i][2] == 1) {
				parent.left = child;
			} else {
				parent.right = child;
			}
		}
		// find the node that is never a child
		for (Integer key : nodeMap.keySet()) {
			if (!children.contains(key)) {
				return nodeMap.get(key);
			}
		}
		return null;
	}

	private static void check(int[][] descriptions, TreeNode expected) {
		System.out.println("descriptions is: ");
		for (int[] description : descriptions) {
			System.out.println(Arrays.toString(description));
		}
		System.out.println("expected is: " + expected.printAll());
		TreeNode createBinaryTree = createBinaryTree(descriptions);
		System.out.println("createBinaryTree is: " + createBinaryTree.printAll());
	}

}
