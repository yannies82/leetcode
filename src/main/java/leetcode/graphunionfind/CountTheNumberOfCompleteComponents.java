package leetcode.graphunionfind;

import java.util.*;

public class CountTheNumberOfCompleteComponents {

    public static void main(String[] args) {
        check(6, new int[][]{{0, 1}, {0, 2}, {1, 2}, {3, 4}}, 3);
        check(6, new int[][]{{0, 1}, {0, 2}, {1, 2}, {3, 4}, {3, 5}}, 1);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/count-the-number-of-complete-components.
     * This solution uses union find to find a root parent for each island and count the number of
     * nodes and edges per island. Time complexity is O(n+m) where m is the length of the edges array.
     *
     * @param n
     * @param edges
     * @return
     */
    public static int countCompleteComponents(int n, int[][] edges) {
        // initialize parent for each node
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        // iterate the edges to calculate the parent for each node
        for (int[] edge : edges) {
            int parentA = findParent(parent, edge[0]);
            int parentB = findParent(parent, edge[1]);
            // assign common parent to both nodes since they are connected
            parent[parentA] = parentB;
        }
        int[] nodeCount = new int[n];
        int[] edgeCount = new int[n];
        // count the nodes on each island using the parent array
        for (int i = 0; i < n; i++) {
            nodeCount[findParent(parent, i)]++;
        }
        // count the edges on each island using the parent array
        for (int[] edge : edges) {
            edgeCount[findParent(parent, edge[0])]++;
        }
        int connectedComplete = 0;
        for (int i = 0; i < n; i++) {
            if (nodeCount[i] == 0) {
                // a single root parent node per island has nodeCount[i] > 0, skip the rest
                continue;
            }
            // if complete connected then edges == nodes * (nodes - 1) / 2
            int expectedEdgeCount = (nodeCount[i] * (nodeCount[i] - 1)) >>> 1;
            if (expectedEdgeCount == edgeCount[i]) {
                connectedComplete++;
            }
        }
        return connectedComplete;
    }

    private static int findParent(int[] parent, int node) {
        int currentParent = parent[node];
        if (currentParent == node) {
            return node;
        }
        int realParent = findParent(parent, currentParent);
        parent[currentParent] = realParent;
        return realParent;
    }

    /**
     * This solution uses DFS graph traversal to find all islands and count their nodes and edges.
     * Time complexity is O(n+m) where m is the length of the edges array.
     *
     * @param n
     * @param edges
     * @return
     */
    public static int countCompleteComponents2(int n, int[][] edges) {
        List<Map<Integer, Integer>> adjacencyLists = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyLists.add(new HashMap<>());
        }
        for (int i = 0; i < edges.length; i++) {
            adjacencyLists.get(edges[i][0]).put(edges[i][1], i);
            adjacencyLists.get(edges[i][1]).put(edges[i][0], i);
        }
        int islandCount = 0;
        // keep track of visited nodes and edges
        boolean[] visitedNodes = new boolean[n];
        boolean[] visitedEdges = new boolean[edges.length];
        for (int i = 0; i < n; i++) {
            // result is updated with the count of nodes and edges in the island
            int[] result = {0, 0};
            performDfs(adjacencyLists, visitedNodes, visitedEdges, i, result);
            // for the island to be connected the number of edges should be equal to k * (k-1) / 2
            // where k is the number of nodes in the island
            if (result[0] > 0 && result[1] == ((result[0] * (result[0] - 1)) >>> 1)) {
                islandCount++;
            }
        }
        return islandCount;
    }

    private static void performDfs(List<Map<Integer, Integer>> adjacencyLists, boolean[] visitedNodes, boolean[] visitedEdges,
                                   int node, int[] result) {
        if (visitedNodes[node]) {
            return;
        }
        // increase node count
        result[0]++;
        visitedNodes[node] = true;
        for (Map.Entry<Integer, Integer> neighbor : adjacencyLists.get(node).entrySet()) {
            if (!visitedEdges[neighbor.getValue()]) {
                // edge has not been visited yet, increase edge count
                result[1]++;
                visitedEdges[neighbor.getValue()] = true;
            }
            performDfs(adjacencyLists, visitedNodes, visitedEdges, neighbor.getKey(), result);
        }
    }

    private static void check(int n, int[][] edges, int expected) {
        System.out.println("n is: " + n);
        System.out.println("edges is: ");
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
        System.out.println("expected is: " + expected);
        int countCompleteComponents = countCompleteComponents(n, edges); // Calls your implementation
        System.out.println("countCompleteComponents is: " + countCompleteComponents);
    }
}
