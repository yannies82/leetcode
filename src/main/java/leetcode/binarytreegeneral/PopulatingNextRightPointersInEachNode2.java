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

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii.
	 * This solution recursively calculates the next element for the children of
	 * each node, starting with the rightmost nodes of each level. Time complexity
	 * is O(n) where n is the number of nodes in the tree.
	 * 
	 * @param root
	 * @return
	 */
	public static Node connect(Node root) {
		connectR(root);
		return root;
	}

	public static void connectR(Node node) {
		// early exit
		if (node == null)
			return;

		// if this node has a next element calculate the next pointers of this next
		// element's children first, they are required for the calculation
		// of the next pointers of this element's children
		if (node.next != null) {
			connectR(node.next);
		}

		if (node.left != null) {
			// calculate the next pointer for the left child
			if (node.right != null) {
				// if the element also has a right child then it is the next element of the left
				// child
				node.left.next = node.right;
				// calculate the next element of the right child
				node.right.next = getNext(node);
			} else {
				// the element does not have a right child calculate the next element of the
				// left child
				node.left.next = getNext(node);
			}

			// calculate recursively for the next level, we only need to invoke the call for
			// the left child
			// it will also invoke the call for the right child through its next pointer
			connectR(node.left);
		} else if (node.right != null) {
			// the left child is null and the right is not, calculate the next element of
			// the right child
			node.right.next = getNext(node);
			// calculate recursively for the next level
			connectR(node.right);
		} else {
			// find the leftmost child of nodes at the same level and calculate recursively
			// for the next level
			connectR(getNext(node));
		}
	}

	/**
	 * This function returns the leftmost child of nodes at the same level as p. It
	 * assumes that all next pointers of elements on the right have been calculated.
	 * 
	 * @param p
	 * @return
	 */
	private static Node getNext(Node p) {
		Node temp = p;
		while ((temp = temp.next) != null) {
			if (temp.left != null) {
				return temp.left;
			}
			if (temp.right != null) {
				return temp.right;
			}
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
