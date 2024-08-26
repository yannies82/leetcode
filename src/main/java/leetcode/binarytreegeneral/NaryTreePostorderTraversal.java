package leetcode.binarytreegeneral;

import java.util.ArrayList;
import java.util.List;

public class NaryTreePostorderTraversal {

	public static void main(String[] args) {
		Node node = new Node(1, List.of(new Node(3, List.of(new Node(5), new Node(6))), new Node(2), new Node(4)));
		check(node, List.of(5, 6, 3, 2, 4, 1));
		node = new Node(1,
				List.of(new Node(2),
						new Node(3, List.of(new Node(6), new Node(7, List.of(new Node(11, List.of(new Node(14))))))),
						new Node(4, List.of(new Node(8, List.of(new Node(12))))),
						new Node(5, List.of(new Node(9, List.of(new Node(13))), new Node(10)))));
		check(node, List.of(2, 6, 14, 11, 7, 3, 12, 8, 4, 13, 9, 10, 5, 1));
	}

	/**
	 * Leetcode problem;
	 * https://leetcode.com/problems/n-ary-tree-postorder-traversal. Time complexity
	 * is O(n) where n is the number of nodes in the tree.
	 * 
	 * @param root
	 * @return
	 */
	public static List<Integer> postorder(Node root) {
		List<Integer> result = new ArrayList<>();
		traverse(root, result);
		return result;
	}

	private static void traverse(Node node, List<Integer> result) {
		if (node == null) {
			return;
		}
		int childrenSize = node.children.size();
		for (int i = 0; i < childrenSize; i++) {
			traverse(node.children.get(i), result);
		}
		result.add(node.val);
	}

	private static class Node {
		public int val;
		public List<Node> children = List.of();

		public Node(int _val) {
			val = _val;
		}

		public Node(int _val, List<Node> _children) {
			val = _val;
			children = _children;
		}

		String printAll() {
			Node current = this;
			StringBuilder result = new StringBuilder();
			print(current, result);
			return result.toString();
		}

		private void print(Node node, StringBuilder builder) {
			if (!builder.isEmpty()) {
				builder.append(",");
			}
			builder.append(node.val);
			int childrenSize = node.children.size();
			for (int i = 0; i < childrenSize; i++) {
				print(node.children.get(i), builder);
			}
		}
	};

	private static void check(Node root, List<Integer> expected) {
		System.out.println("root is: " + (root == null ? null : root.printAll()));
		System.out.println("expected is: " + expected);
		List<Integer> postorder = postorder(root);
		System.out.println("postorder is: " + postorder);
	}

}
