package leetcode.binarytreebfs;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class KthLargestSumInABinaryTree {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(5,
				new TreeNode(8, new TreeNode(2, new TreeNode(4), new TreeNode(6)), new TreeNode(1)),
				new TreeNode(9, new TreeNode(3), new TreeNode(7)));
		check(tree1, 2, 13);
		tree1 = new TreeNode(1, new TreeNode(2, new TreeNode(3), null), null);
		check(tree1, 1, 3);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/kth-largest-sum-in-a-binary-tree. This solution
	 * uses two queues, one for the BFS traversal of the tree and a priority queue
	 * to keep the k largest sums of the levels at all times. Worst case time
	 * complexity is O(nlogk) where n is the number of nodes in the tree.
	 * 
	 * @param root
	 * @param k
	 * @return
	 */
	public static long kthLargestLevelSum(TreeNode root, int k) {
		Queue<TreeNode> queue = new ArrayDeque<>();
		Queue<Long> levelSumQueue = new PriorityQueue<>();
		queue.offer(root);
		// perform BFS traversal of the tree
		while (!queue.isEmpty()) {
			int length = queue.size();
			// keeps the sum of the level nodes
			long sum = 0;
			for (int i = 0; i < length; i++) {
				TreeNode current = queue.poll();
				sum += current.val;
				if (current.left != null) {
					queue.offer(current.left);
				}
				if (current.right != null) {
					queue.offer(current.right);
				}
			}
			int sumSize = levelSumQueue.size();
			if (sumSize < k) {
				levelSumQueue.offer(sum);
			} else if (sum > levelSumQueue.peek()) {
				// replace the smallest sum in the priority queue
				levelSumQueue.poll();
				levelSumQueue.offer(sum);
			}
		}
		return levelSumQueue.size() == k ? levelSumQueue.peek() : -1;
	}

	private static void check(TreeNode root, int k, long expected) {
		System.out.println("root is: " + root.printAll());
		System.out.println("expected is: " + expected);
		long kthLargestLevelSum = kthLargestLevelSum(root, k);
		System.out.println("kthLargestLevelSum is: " + kthLargestLevelSum);
	}

}
