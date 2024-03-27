package leetcode.binarytreegeneral;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class BinarySearchTreeIterator {

	/**
	 * Leetcode problem: https://leetcode.com/problems/binary-search-tree-iterator.
	 * This solution performs iterative inorder traversal. Every time next is
	 * invoked, an iteration of the traversal is performed until the leftmost
	 * element is reached. Then the last element is polled from the stack and
	 * returned. The next time the iteration will resume from the right element of
	 * the polled element. next and hasNext perform in effective O(1) and the stack
	 * contains the minimum amount of nodes required for the iteration O(h) where h
	 * is the height of the binary tree.
	 * 
	 * @author yanni
	 *
	 */
	public static class BSTIterator {

		TreeNode nextElement = null;
		private Deque<TreeNode> stack = new ArrayDeque<>();

		public BSTIterator(TreeNode root) {
			nextElement = root;
		}

		public int next() {
			TreeNode element = nextElement;
			// performs an iteration for the leftmost element
			while (element != null) {
				stack.offerFirst(element);
				element = element.left;
			}
			// polls the last element from the stack and returns it
			element = stack.poll();
			nextElement = element.right;
			return element.val;
		}

		public boolean hasNext() {
			return nextElement != null || !stack.isEmpty();
		}

	}

	/**
	 * This solution performs iterative inorder traversal. When constructed it
	 * traverses the tree until it finds the leftmost element. Then, every time next
	 * is invoked, the last element is polled from the stack and the traversal
	 * resumes from its right element. next and hasNext perform in effective O(1)
	 * and the stack contains the minimum amount of nodes required for the iteration
	 * O(h) where h is the height of the binary tree.
	 * 
	 * @author yanni
	 *
	 */
	public static class BSTIterator2 {

		private Deque<TreeNode> stack = new ArrayDeque<>();

		public BSTIterator2(TreeNode root) {
			TreeNode nextElement = root;
			// iterates until the leftmost element is pushed to the stack
			while (nextElement != null) {
				stack.offerFirst(nextElement);
				nextElement = nextElement.left;
			}
		}

		public int next() {
			// polls the last element from the stack and returns it
			// performs another iteration for its right element
			TreeNode element = stack.poll();
			TreeNode nextElement = element.right;
			while (nextElement != null) {
				stack.offerFirst(nextElement);
				nextElement = nextElement.left;
			}
			return element.val;
		}

		public boolean hasNext() {
			return stack.peek() != null;
		}

	}

	/**
	 * This solution traverses the binary tree and puts its values in a queue.
	 * 
	 * @author yanni
	 *
	 */
	public static class BSTIterator3 {

		private Queue<Integer> queue = new ArrayDeque<>();

		public BSTIterator3(TreeNode root) {
			// traverses the tree inorder and puts the values into a queue
			traverse(root, queue);
		}

		public int next() {
			return queue.poll();
		}

		public boolean hasNext() {
			return queue.peek() != null;
		}

		private void traverse(TreeNode node, Queue<Integer> queue) {
			if (node.left != null) {
				// traverse left node recursively
				// and return the last element, set it as prev
				traverse(node.left, queue);
			}
			// add the value to the queue
			queue.add(node.val);
			if (node.right != null) {
				// traverse right node recursively
				// and return the last element, set it as prev
				traverse(node.right, queue);
			}
		}

	}

	/**
	 * This solution flattens the binary tree into a linked list.
	 * 
	 * @author yanni
	 *
	 */
	public static class BSTIterator4 {

		private TreeNode current;
		private TreeNode next;

		public BSTIterator4(TreeNode root) {
			TreeNode[] smallestElement = { null };
			// traverses the tree inorder and flattens it into a linked list
			// sets the pointer to the smallest element in the smallestElement array
			traverse(root, null, smallestElement);
			next = smallestElement[0];
		}

		public int next() {
			current = next;
			next = current.right;
			return current.val;
		}

		public boolean hasNext() {
			return next != null;
		}

		private TreeNode traverse(TreeNode node, TreeNode prev, TreeNode[] smallestElement) {
			TreeNode left = node.left;
			TreeNode right = node.right;
			if (left != null) {
				// traverse left node recursively
				// and return the last element, set it as prev
				prev = traverse(left, prev, smallestElement);
			}
			if (smallestElement[0] == null) {
				smallestElement[0] = node;
			}
			// if prev exists set its left pointer to null
			// and its right pointer to the current node
			if (prev != null) {
				prev.left = null;
				prev.right = node;
			}
			// update the prev element to the current node
			// and keep the left and right nodes in variables
			// because node may be modified when the method is
			// called recursively
			prev = node;
			if (right != null) {
				// traverse right node recursively
				// and return the last element, set it as prev
				prev = traverse(right, prev, smallestElement);
			}
			// return prev which contains the last traversed element
			return prev;
		}

	}

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

	}
}
