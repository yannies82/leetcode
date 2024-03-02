package leetcode.graphgeneral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneGraph {

	public static void main(String[] args) {
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		node1.neighbors = List.of(node2, node4);
		node2.neighbors = List.of(node1, node3);
		node3.neighbors = List.of(node2, node4);
		node4.neighbors = List.of(node1, node3);
		check(node1, node1);
		check(null, null);

	}

	/**
	 * Solution using a map and recursive DFS traversal of the graph.
	 * 
	 * @param node
	 * @return
	 */
	public static Node cloneGraph(Node node) {
		// early exit in case of empty graph
		if (node == null) {
			return null;
		}
		// keeps the pair of node value as key and cloned node as value
		Map<Integer, Node> visited = new HashMap<>();
		// traverse the graph and perform recursive clones
		// the returned one will be the clone of the input node
		return performDfsRecursive(node, visited);
	}

	/**
	 * Solution with recursive DFS graph traversal.
	 * 
	 * @param grid
	 * @return
	 */
	private static Node performDfsRecursive(Node node, Map<Integer, Node> visited) {
		// clone the node and put it in the map
		Node copy = new Node(node.val);
		visited.put(node.val, copy);
		// perform DFS for each of the adjacent nodes
		for (Node neighbour : node.neighbors) {
			// get node from the map if it is already cloned
			// otherwise clone now
			Node cachedNode = visited.get(neighbour.val);
			if (cachedNode == null) {
				cachedNode = performDfsRecursive(neighbour, visited);
			}
			// add copied neighbour to the copy's adjacency list
			copy.neighbors.add(cachedNode);
		}
		return copy;
	}

	private static class Node {
		public int val;
		public List<Node> neighbors;

		public Node(int _val) {
			val = _val;
			neighbors = new ArrayList<Node>();
		}

		void printAll() {
			Map<Integer, Node> visited = new HashMap<>();
			print(this, visited);
		}

		void print(Node node, Map<Integer, Node> visited) {
			System.out.println("Node value: " + node.val + ", neighbours: " + node.neighbors);
			visited.put(node.val, node);
			for (Node neighbour : node.neighbors) {
				Node cachedNode = visited.get(neighbour.val);
				if (cachedNode == null) {
					print(neighbour, visited);
				}
			}
		}

		@Override
		public String toString() {
			return this.val + "";
		}

	}

	private static void check(Node node, Node expected) {
		System.out.println("input graph is: ");
		if (node != null) {
			node.printAll();
			System.out.println();
		}
		System.out.println("expected graph is: ");
		if (node != null) {
			expected.printAll();
			System.out.println();
		}
		Node cloneGraph = cloneGraph(node); // Calls your implementation
		System.out.println("cloneGraph is: ");
		if (node != null) {
			cloneGraph.printAll();
			System.out.println();
		}
	}
}
