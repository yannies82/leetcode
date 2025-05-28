package leetcode.graphgeneral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximizeTheNumberOfTargetNodesAfterConnectingTrees {

    public static void main(String[] args) {
        check(new int[][]{{0, 1}, {0, 2}, {2, 3}, {2, 4}},
                new int[][]{{0, 1}, {0, 2}, {0, 3}, {2, 7}, {1, 4}, {4, 5}, {4, 6}},
                2, new int[]{9, 7, 9, 8, 8});
        check(new int[][]{{0, 1}, {0, 2}, {0, 3}, {0, 4}}, new int[][]{{0, 1}, {1, 2}, {2, 3}},
                1, new int[]{6, 3, 3, 3, 3});
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-i.
     * This solution uses DFS from each node in order to calculate the result.
     *
     * @param edges1
     * @param edges2
     * @param k
     * @return
     */
    public static int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        // calculate adjacency list for the first tree
        List<List<Integer>> adjacencyList1 = buildAdjacencyList(edges1);
        int n = adjacencyList1.size();
        int[] result = new int[n];
        if (k == 0) {
            // no other nodes are reachable, return 1 for each node
            Arrays.fill(result, 1);
            return result;
        }
        // calculate adjacency list for the second tree
        List<List<Integer>> adjacencyList2 = buildAdjacencyList(edges2);
        int m = adjacencyList2.size();
        // perform DFS on the second tree for each node to find the one with the
        // max number of reachable nodes within k - 1 steps
        int maxReachableFromB = 0;
        for (int i = 0; i < m; i++) {
            maxReachableFromB = Math.max(maxReachableFromB, dfs(adjacencyList2, i, -1, k - 1));
        }
        // perform DFS for each node on the first tree to find the number of reachable nodes
        // within k steps, add maxReachableFromB to also take into consideration the reachable
        // nodes from the second tree after connecting the two trees
        for (int i = 0; i < n; i++) {
            result[i] = dfs(adjacencyList1, i, -1, k) + maxReachableFromB;
        }
        return result;
    }

    private static List<List<Integer>> buildAdjacencyList(int[][] edges) {
        int n = edges.length + 1;
        List<List<Integer>> adjacencyList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
        return adjacencyList;
    }

    private static int dfs(List<List<Integer>> adjacencyList, int node, int parent, int k) {
        if (k == 0) {
            // no other steps left, just this node is reachable
            return 1;
        }
        int count = 1;
        for (int neighbor : adjacencyList.get(node)) {
            if (neighbor != parent) {
                // there are no cycles in the trees, it is enough to avoid visiting the parent
                // add reachable nodes of each neighbor
                count += dfs(adjacencyList, neighbor, node, k - 1);
            }
        }
        return count;
    }

    private static void check(int[][] edges1, int[][] edges2, int k, int[] expected) {
        for (int i = 0; i < edges1.length; i++) {
            System.out.println(Arrays.toString(edges1[i]));
        }
        for (int i = 0; i < edges2.length; i++) {
            System.out.println(Arrays.toString(edges2[i]));
        }
        System.out.println("k is: " + k);
        System.out.println("expected is: " + Arrays.toString(expected));
        int[] maxTargetNodes = maxTargetNodes(edges1, edges2, k); // Calls your implementation
        System.out.println("maxTargetNodes is: " + Arrays.toString(maxTargetNodes));
    }
}
