package leetcode.binarytreegeneral;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class PopulatingNextRightPointersInEachNode2 {

	public static void main(String[] args) {
		Node tree1 = new Node(1, new Node(2, new Node(4), new Node(5)), new Node(3, null, new Node(7)));
		String expected = "[1, null],[2, 3],[4, 5],[5, 7],[3, null],[7, null]";
		check(tree1, expected);
		tree1 = null;
		expected = null;
		check(tree1, expected);
	}

	public static Node connect(Node root) {
		connectR(root);
		return root;
	}

	public static void connectR(Node node) {
		// early exit
		if (node == null)
			return;

		/*
		 * Before setting next of left and right children, set next of children of other
		 * nodes at same level (because we can access children of other nodes using p's
		 * next only)
		 */
		if (node.next != null) {
			connectR(node.next);
		}

		/* Set the next pointer for p's left child */
		if (node.left != null) {
			if (node.right != null) {
				node.left.next = node.right;
				node.right.next = getNext(node);
			} else {
				node.left.next = getNext(node);
			}

			/*
			 * Recursively call for next level nodes. Note that we call only for left child.
			 * The call for left child will call for right child
			 */
			connectR(node.left);
		}

		/*
		 * If left child is NULL then first node of next level will either be p->right
		 * or getNextRight(p)
		 */
		else if (node.right != null) {
			node.right.next = getNext(node);
			connectR(node.right);
		} else {
			connectR(getNext(node));
		}
	}

	/*
	 * This function returns the leftmost child of nodes at the same level as p.
	 * This function is used to getNExt right of p's right child If right child of p
	 * is NULL then this can also be used for the left child
	 */
	private static Node getNext(Node p) {
		/*
		 * Traverse nodes at p's level and find and return the first node's first child
		 */
		Node temp = p;
		while ((temp = temp.next) != null) {
			if (temp.left != null)
				return temp.left;
			if (temp.right != null)
				return temp.right;
		}

		// If all the nodes at p's level are leaf nodes then return NULL
		return null;
	}

	/**
	 * Recursive solution using a map and postorder traversal.
	 * 
	 * @param root
	 * @return
	 */
	public static Node connect2(Node root) {
		if (root == null) {
			return null;
		}
		// keep the current next right node per level
		Map<Integer, Node> levelsMap = new HashMap<>();
		// traverse the tree post order
		traversePostOrder(root, 1, levelsMap);
		return root;
	}

	private static void traversePostOrder(Node node, int level, Map<Integer, Node> levelsMap) {
		// set the next element of the node to the one stored in the map for this level
		node.next = levelsMap.get(level);
		// update the map for this level with the current node
		levelsMap.put(level, node);
		if (node.right != null) {
			// traverse right node recursively, increase level
			traversePostOrder(node.right, level + 1, levelsMap);
		}
		if (node.left != null) {
			// traverse left node recursively, increase level
			traversePostOrder(node.left, level + 1, levelsMap);
		}
	}

	/**
	 * Iterative solution using breadth first search and a queue.
	 * 
	 * @param root
	 * @return
	 */
	public static Node connect3(Node root) {
		if (root == null) {
			return null;
		}
		// keep nodes while traversing
		Queue<Node> nodesQueue = new ArrayDeque<>();
		nodesQueue.offer(root);
		Node prev = null;
		while (!nodesQueue.isEmpty()) {
			// in each iteration only process the nodes of the current level
			// nodes are added to the queue left to right and are processed in the same
			// manner
			int levelSize = nodesQueue.size();
			for (int i = 0; i < levelSize; i++) {
				Node current = nodesQueue.poll();
				if (i > 0) {
					// for the first element the prev points
					// to the last element of the previous level
					// so skip setting the next pointer
					prev.next = current;
				}
				if (current.left != null) {
					nodesQueue.offer(current.left);
				}
				if (current.right != null) {
					nodesQueue.offer(current.right);
				}
				prev = current;
			}
		}
		return root;
	}

	private static void check(Node root, String expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + expected);
		Node connect = connect(root);
		System.out.println("connect is: " + (connect == null ? null : connect.printAll()));
	}

	private static class Node {
		public int val;
		public Node left;
		public Node right;
		public Node next;

		public Node(int _val) {
			val = _val;
		}

		public Node(int _val, Node _left, Node _right) {
			val = _val;
			left = _left;
			right = _right;
		}

		String printAll() {
			Node current = this;
			StringBuilder result = new StringBuilder();
			print(current, result);
			return result.toString();
		}

		void print(Node node, StringBuilder builder) {
			if (!builder.isEmpty()) {
				builder.append(",");
			}
			builder.append("[");
			builder.append(node.val);
			if (node.next == null) {
				builder.append(", null");
			} else {
				builder.append(", ").append(node.next.val);
			}
			builder.append("]");
			if (node.left != null) {
				print(node.left, builder);
			}
			if (node.right != null) {
				print(node.right, builder);
			}
		}
	};

}
