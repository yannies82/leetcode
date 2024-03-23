package leetcode.linkedlists;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {

	public static void main(String[] args) {
		Node node1 = new Node(7);
		Node node2 = new Node(13);
		Node node3 = new Node(11);
		Node node4 = new Node(10);
		Node node5 = new Node(1);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node2.random = node1;
		node3.random = node5;
		node4.random = node3;
		node5.random = node1;
		check(node1, node1);
		node1 = new Node(1);
		node2 = new Node(2);
		node1.next = node2;
		node1.random = node2;
		node2.random = node2;
		check(node1, node1);
		node1 = new Node(3);
		node2 = new Node(3);
		node3 = new Node(3);
		node1.next = node2;
		node2.next = node3;
		node2.random = node1;
		check(node1, node1);
	}

	/**
	 * Leetcode problem:
	 * https://leetcode.com/problems/copy-list-with-random-pointer. This solution
	 * copies all nodes, mapping old nodes to the respective new nodes and keeping
	 * the original random references to the initial list. It then iterates the new
	 * list and uses the map to replace references to original nodes with references
	 * to new nodes. Time complexity is O(n) where n is the number of nodes in the
	 * list.
	 * 
	 * @param head
	 * @return
	 */
	public static Node copyRandomList(Node head) {
		Node copyStart = new Node(0);
		Node current = copyStart;
		// map source nodes to copied nodes
		Map<Node, Node> nodeMap = new HashMap<>();
		// iterate source list, copy all nodes,
		// random references still point to source list nodes,
		// populate nodesMap
		while (head != null) {
			Node next = new Node(head.val);
			next.random = head.random;
			nodeMap.put(head, next);
			head = head.next;
			current.next = next;
			current = next;
		}
		// iterate the copied list and replace random references of
		// source list nodes with references of copied nodes,
		// using the nodesMap
		current = copyStart.next;
		while (current != null) {
			current.random = nodeMap.get(current.random);
			current = current.next;
		}
		return copyStart.next;
	}

	private static void check(Node head, Node expected) {
		System.out.println("head is: " + (head == null ? null : head.printAll()));
		System.out.println("expected is: " + (expected == null ? null : expected.printAll()));
		Node copyRandomList = copyRandomList(head);
		System.out.println("copyRandomList is: " + (copyRandomList == null ? null : copyRandomList.printAll()));
	}

	private static class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}

		String printAll() {
			Node current = this;
			StringBuilder result = new StringBuilder();
			do {
				if (!result.isEmpty()) {
					result.append(",");
				}
				result.append("[").append(current.val).append(",")
						.append(current.random == null ? null : current.random.val).append("]");
				current = current.next;
			} while (current != null);
			return result.toString();
		}

	}
}
