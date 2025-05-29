package leetcode.graphgeneral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximizeTheNumberOfTargetNodesAfterConnectingTrees2 {

    public static void main(String[] args) {
        check(new int[][]{{0, 1}, {0, 2}, {2, 3}, {2, 4}},
                new int[][]{{0, 1}, {0, 2}, {0, 3}, {2, 7}, {1, 4}, {4, 5}, {4, 6}},
                new int[]{8, 7, 7, 8, 8});
        check(new int[][]{{0, 1}, {0, 2}, {0, 3}, {0, 4}}, new int[][]{{0, 1}, {1, 2}, {2, 3}},
                new int[]{3, 6, 6, 6, 6});
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-ii.
     *
     * @param edges1
     * @param edges2
     * @return
     */
    public static int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        // calculate adjacency list for the first tree
        List<List<Integer>> adjacencyList1 = buildAdjacencyList(edges1);
        // calculate adjacency list for the second tree
        List<List<Integer>> adjacencyList2 = buildAdjacencyList(edges2);
        int n = adjacencyList1.size();
        int m = adjacencyList2.size();
        // calculate alternating colors for nodes of both trees
        int[] color1 = new int[n];
        int[] color2 = new int[m];
        Arrays.fill(color1, -1);
        Arrays.fill(color2, -1);
        color1[0] = 0;
        int even1 = dfsColor(adjacencyList1, 0, -1, color1);
        int odd1 = n - even1;
        color2[0] = 0;
        int even2 = dfsColor(adjacencyList2, 0, -1, color2);
        int odd2 = m - even2;
        // the max number between even and odd nodes of tree2 will be added to each node of tree1
        int maxColor2 = Math.max(even2, odd2);
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = (color1[i] == 0 ? even1 : odd1) + maxColor2;
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

    private static int dfsColor(List<List<Integer>> adjacencyList, int node, int parent, int[] color) {
        int even = 1 - color[node];
        int nextColor = color[node] ^ 1;
        for (int neighbor : adjacencyList.get(node)) {
            if (neighbor != parent) {
                color[neighbor] = nextColor;
                even += dfsColor(adjacencyList, neighbor, node, color);
            }
        }
        return even;
    }

    /**
     * Alternative solution which counts the number of even reachable nodes for tree1 and the number of odd
     * reachable nodes for tree2.
     *
     * @param edges1
     * @param edges2
     * @return
     */
    public static int[] maxTargetNodes2(int[][] edges1, int[][] edges2) {
        // calculate adjacency list for the first tree
        List<List<Integer>> adjacencyList1 = buildAdjacencyList(edges1);
        // calculate adjacency list for the second tree
        List<List<Integer>> adjacencyList2 = buildAdjacencyList(edges2);
        int n = adjacencyList1.size();
        int[] result = new int[n];
        int m = adjacencyList2.size();
        // perform DFS on the second tree for each node to find the one with the
        // max number of reachable nodes within k - 1 steps
        int maxOddFromB = 0;
        for (int i = 0; i < m; i++) {
            maxOddFromB = Math.max(maxOddFromB, m - dfs(adjacencyList2, i, -1, 1));
        }
        // perform DFS for each node on the first tree to find the number of reachable even nodes
        // add maxOddFromB to also take into consideration the reachable
        // odd nodes from the second tree (which will be even after connecting the two trees)
        for (int i = 0; i < n; i++) {
            result[i] = dfs(adjacencyList1, i, -1, 1) + maxOddFromB;
        }
        return result;
    }

    private static int dfs(List<List<Integer>> adjacencyList, int node, int parent, int increment) {
        int count = increment;
        for (int neighbor : adjacencyList.get(node)) {
            if (neighbor != parent) {
                // there are no cycles in the trees, it is enough to avoid visiting the parent
                // add reachable nodes of each neighbor, switch increment from 0 to 1 and vice versa
                count += dfs(adjacencyList, neighbor, node, increment ^ 1);
            }
        }
        return count;
    }

    private static void check(int[][] edges1, int[][] edges2, int[] expected) {
        for (int i = 0; i < edges1.length; i++) {
            System.out.println(Arrays.toString(edges1[i]));
        }
        for (int i = 0; i < edges2.length; i++) {
            System.out.println(Arrays.toString(edges2[i]));
        }
        System.out.println("expected is: " + Arrays.toString(expected));
        int[] maxTargetNodes = maxTargetNodes(edges1, edges2); // Calls your implementation
        System.out.println("maxTargetNodes is: " + Arrays.toString(maxTargetNodes));
    }
}
