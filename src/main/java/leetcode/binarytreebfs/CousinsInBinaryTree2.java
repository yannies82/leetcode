package leetcode.binarytreebfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class CousinsInBinaryTree2 {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(5, new TreeNode(4, new TreeNode(1), new TreeNode(10)),
				new TreeNode(9, null, new TreeNode(7)));
		TreeNode expected1 = new TreeNode(0, new TreeNode(0, new TreeNode(7), new TreeNode(7)),
				new TreeNode(0, null, new TreeNode(11)));
		check(tree1, expected1);
		tree1 = new TreeNode(3, new TreeNode(1), new TreeNode(2));
		expected1 = new TreeNode(0, new TreeNode(0), new TreeNode(0));
		check(tree1, expected1);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/cousins-in-binary-tree-ii.
	 * This solution performs BFS traversal of the tree and calcultes the level sum
	 * and the sibling sum for each element, before adding it to the BFS queue. Time
	 * complexity is O(n) where n is the number of nodes in the tree.
	 * 
	 * @param root
	 * @return
	 */
	public static TreeNode replaceValueInTree(TreeNode root) {
		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		root.val = 0;
		// perform BFS traversal of the tree
		int levelSum = 0;
		while (!queue.isEmpty()) {
			int length = queue.size();
			// keeps the sum of the level nodes
			int nextLevelSum = 0;
			for (int i = 0; i < length; i++) {
				TreeNode current = queue.poll();
				current.val = levelSum - current.val;
				if (current.left != null) {
					nextLevelSum += current.left.val;
					queue.offer(current.left);
				}
				if (current.right != null) {
					nextLevelSum += current.right.val;
					queue.offer(current.right);
				}
				if (current.left != null && current.right != null) {
					int siblingSum = current.left.val + current.right.val;
					current.left.val = siblingSum;
					current.right.val = siblingSum;
				}
			}
			levelSum = nextLevelSum;
		}
		return root;
	}

	/**
	 * Similar solution to the first one. Time complexity is O(n) where n is the
	 * number of nodes in the tree.
	 * 
	 * @param root
	 * @return
	 */
	public static TreeNode replaceValueInTree2(TreeNode root) {
		Queue<NodeWrapper> queue = new ArrayDeque<>();
		queue.offer(new NodeWrapper(root, 0));
		// perform BFS traversal of the tree
		int levelSum = 0;
		while (!queue.isEmpty()) {
			int length = queue.size();
			// keeps the sum of the level nodes
			int nextLevelSum = 0;
			for (int i = 0; i < length; i++) {
				NodeWrapper current = queue.poll();
				current.node.val = levelSum - current.siblingSum;
				int siblingSum = 0;
				if (current.node.left != null) {
					siblingSum += current.node.left.val;
				}
				if (current.node.right != null) {
					siblingSum += current.node.right.val;
				}
				if (current.node.left != null) {
					queue.offer(new NodeWrapper(current.node.left, siblingSum));
				}
				if (current.node.right != null) {
					queue.offer(new NodeWrapper(current.node.right, siblingSum));
				}
				nextLevelSum += siblingSum;
			}
			levelSum = nextLevelSum;
		}
		return root;
	}

	private static class NodeWrapper {
		TreeNode node;
		int siblingSum;

		public NodeWrapper(TreeNode node, int siblingSum) {
			super();
			this.node = node;
			this.siblingSum = siblingSum;
		}
	}

	private static void check(TreeNode root, TreeNode expected) {
		System.out.println("root is: " + root.printAll());
		System.out.println("expected is: " + expected.printAll());
		TreeNode replaceValueInTree = replaceValueInTree(root);
		System.out.println("replaceValueInTree is: " + replaceValueInTree.printAll());
	}

}
