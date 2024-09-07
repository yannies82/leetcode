package leetcode.binarytreegeneral;

public class LinkedListInBinaryTree {

	public static void main(String[] args) {
		check(ListNode.createList(4, 2, 8),
				new TreeNode(1, new TreeNode(4, null, new TreeNode(2, new TreeNode(1), null)), new TreeNode(4,
						new TreeNode(2, new TreeNode(6), new TreeNode(8, new TreeNode(1), new TreeNode(3))), null)),
				true);
		check(ListNode.createList(1, 4, 2, 6),
				new TreeNode(1, new TreeNode(4, null, new TreeNode(2, new TreeNode(1), null)), new TreeNode(4,
						new TreeNode(2, new TreeNode(6), new TreeNode(8, new TreeNode(1), new TreeNode(3))), null)),
				true);
		check(ListNode.createList(1, 4, 2, 6, 8),
				new TreeNode(1, new TreeNode(4, null, new TreeNode(2, new TreeNode(1), null)), new TreeNode(4,
						new TreeNode(2, new TreeNode(6), new TreeNode(8, new TreeNode(1), new TreeNode(3))), null)),
				false);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/linked-list-in-binary-tree.
	 * This solution traverses the binary tree preorder. Whenever a match is found
	 * between the value of a node and the head of the list, a shallow preorder
	 * traversal of the subtree is performed, which is terminated early if there is
	 * not a match. Time complexity is O(n*m) where n is the number of nodes in the
	 * list and m is the number of nodes in the tree.
	 * 
	 * @param nums
	 * @param head
	 * @return
	 */
	public static boolean isSubPath(ListNode head, TreeNode root) {
		// perform preorder traversal of the tree
		return traverse(root, head, head);
	}

	private static boolean traverse(TreeNode node, ListNode head, ListNode current) {
		if (node == null) {
			// early exit
			return false;
		}
		// perform a shallow traversal from this node and if there is no match continue
		// with the preorder traversal of the tree, terminate traversal if a match is
		// found
		return shallowTraverse(node, head, current) || traverse(node.left, head, head)
				|| traverse(node.right, head, head);
	}

	private static boolean shallowTraverse(TreeNode node, ListNode head, ListNode current) {
		if (node == null || node.val != current.val) {
			// early exit if the list node has a different value from the tree node
			return false;
		}
		// the tree node value matches the list node value, try to advance both nodes
		if (current.next == null) {
			// if the list has no more nodes then we have a match
			return true;
		}
		// the list has more nodes, compare the left and right tree nodes to the next
		// list node
		return shallowTraverse(node.left, head, current.next) || shallowTraverse(node.right, head, current.next);
	}

	private static void check(ListNode head, TreeNode root, boolean expected) {
		System.out.println("head is: " + head.printAll());
		System.out.println("root is: " + root.printAll());
		System.out.println("expected is: " + expected);
		boolean isSubPath = isSubPath(head, root);
		System.out.println("isSubPath is: " + isSubPath);
	}

}
