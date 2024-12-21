package leetcode.binarytreebfs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class ReverseOddLevelsOfBinaryTree {

	public static void main(String[] args) {
		TreeNode tree1 = new TreeNode(2, new TreeNode(3, new TreeNode(8), new TreeNode(13)),
				new TreeNode(5, new TreeNode(21), new TreeNode(34)));
		TreeNode expected = new TreeNode(2, new TreeNode(5, new TreeNode(8), new TreeNode(13)),
				new TreeNode(3, new TreeNode(21), new TreeNode(34)));
		check(tree1, expected);
		tree1 = new TreeNode(7, new TreeNode(13), new TreeNode(11));
		expected = new TreeNode(7, new TreeNode(11), new TreeNode(13));
		check(tree1, expected);
		tree1 = new TreeNode(0,
				new TreeNode(1, new TreeNode(0, new TreeNode(1), new TreeNode(1)),
						new TreeNode(0, new TreeNode(1), new TreeNode(1))),
				new TreeNode(2, new TreeNode(0, new TreeNode(2), new TreeNode(2)),
						new TreeNode(0, new TreeNode(2), new TreeNode(2))));
		expected = new TreeNode(0,
				new TreeNode(2, new TreeNode(0, new TreeNode(2), new TreeNode(2)),
						new TreeNode(0, new TreeNode(2), new TreeNode(2))),
				new TreeNode(1, new TreeNode(0, new TreeNode(1), new TreeNode(1)),
						new TreeNode(0, new TreeNode(1), new TreeNode(1))));
		check(tree1, expected);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/reverse-odd-levels-of-binary-tree. This
	 * solution uses DFS traversal and traverses two nodes at the same time from
	 * opposite sides of the tree and performs a swap if necessary. Time complexity
	 * is O(n/2) where n is the number of nodes in the tree.
	 * 
	 * 
	 * @param root
	 * @return
	 */
	public static TreeNode reverseOddLevels(TreeNode root) {
		traverse(root.left, root.right, 0);
		return root;
	}

	private static void traverse(TreeNode left, TreeNode right, int level) {
		if (left == null) {
			return;
		}
		if ((level & 1) == 0) {
			int temp = left.val;
			left.val = right.val;
			right.val = temp;
		}
		traverse(left.left, right.right, level + 1);
		traverse(left.right, right.left, level + 1);
	}

	/**
	 * Optimized (but less readable) BFS traversal solution which uses a customized
	 * queue and stack after probing the tree to find the number of elements. Time
	 * complexity is O(n) where n is the number of nodes in the tree.
	 * 
	 * @param root
	 * @return
	 */
	public static TreeNode reverseOddLevels2(TreeNode root) {
		int totalSize = 2;
		// probe the tree to find its total size
		TreeNode probe = root;
		while ((probe = probe.left) != null) {
			totalSize <<= 1;
		}
		TreeNode[] queue = new TreeNode[totalSize];
		int queueHead = 0;
		int queueTail = 0;
		int[] stack = new int[totalSize];
		int stackIndex = -1;
		queue[queueHead] = root;
		int level = 0;
		while (queueHead >= queueTail) {
			int size = queueHead - queueTail + 1;
			boolean isOddLevel = (level & 1) == 1;
			for (int i = 0; i < size; i++) {
				TreeNode current = queue[queueTail++];
				if (current.left != null) {
					queue[++queueHead] = current.left;
					if (!isOddLevel) {
						stack[++stackIndex] = current.left.val;
					}
				}
				if (current.right != null) {
					queue[++queueHead] = current.right;
					if (!isOddLevel) {
						stack[++stackIndex] = current.right.val;
					}
				}
				if (isOddLevel) {
					current.val = stack[stackIndex--];
				}
			}
			level++;
		}
		return root;
	}

	/**
	 * This solution uses a queue for BFS traversal and a stack to keep the values
	 * of the next level nodes. Time complexity is O(n) where n is the number of
	 * nodes in the tree.
	 * 
	 * @param root
	 * @return
	 */
	public static TreeNode reverseOddLevels3(TreeNode root) {
		Queue<TreeNode> queue = new ArrayDeque<>();
		Deque<Integer> stack = new ArrayDeque<>();
		queue.add(root);
		int level = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			boolean isOddLevel = (level & 1) == 1;
			for (int i = 0; i < size; i++) {
				TreeNode current = queue.poll();
				if (current.left != null) {
					queue.offer(current.left);
					if (!isOddLevel) {
						stack.offerFirst(current.left.val);
					}
				}
				if (current.right != null) {
					queue.offer(current.right);
					if (!isOddLevel) {
						stack.offerFirst(current.right.val);
					}
				}
				if (isOddLevel) {
					current.val = stack.poll();
				}
			}
			level++;
		}
		return root;
	}

	private static void check(TreeNode root, TreeNode expected) {
		System.out.println("root is: " + root.printAll());
		System.out.println("expected is: " + expected.printAll());
		TreeNode reverseOddLevels = reverseOddLevels(root);
		System.out.println("reverseOddLevels is: " + reverseOddLevels.printAll());
	}

}
