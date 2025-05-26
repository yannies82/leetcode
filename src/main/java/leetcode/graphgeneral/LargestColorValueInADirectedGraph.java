package leetcode.graphgeneral;

import java.util.*;

public class LargestColorValueInADirectedGraph {

    public static void main(String[] args) {
        check("abaca", new int[][]{{0, 1}, {0, 2}, {2, 3}, {3, 4}}, 3);
        check("a", new int[][]{{0, 0}}, -1);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/largest-color-value-in-a-directed-graph.
     * This solution performs DFS from each node to find the maximum color occurrences in a single path.
     *
     * @param colors
     * @param edges
     * @return
     */
    public static int largestPathValue(String colors, int[][] edges) {
        int nodesCount = colors.length();
        // create and populate adjacency list
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < nodesCount; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
        }
        // keeps count of colors per node
        int[][] count = new int[nodesCount][26];
        // keeps visited state for each node
        int[] visited = new int[nodesCount];
        int result = 0;
        // perform DFS from each node to find the max occurrences
        for (int i = 0; i < nodesCount && result != Integer.MAX_VALUE; i++) {
            result = Math.max(result, dfs(i, colors, adjacencyList, count, visited));
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private static int dfs(int node, String colors, List<List<Integer>> adjacencyList, int[][] count, int[] visited) {
        if (visited[node] == 1) {
            // this node is currently being visited and it was encountered again, there is a cycle
            return Integer.MAX_VALUE;
        }
        int colorIndex = colors.charAt(node) - 'a';
        if (visited[node] == 2) {
            // node has been completely visited and max occurrences for it have already been counted
            return count[node][colorIndex];
        }
        // mark node as currently visiting
        visited[node] = 1;
        for (int neighbor : adjacencyList.get(node)) {
            int res = dfs(neighbor, colors, adjacencyList, count, visited);
            if (res == Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            for (int color = 0; color < 26; color++) {
                count[node][color] = Math.max(count[node][color], count[neighbor][color]);
            }
        }
        count[node][colorIndex]++;
        // mark node as fully visited
        visited[node] = 2;
        return count[node][colorIndex];
    }

    private static void check(String colors, int[][] edges, int expected) {
        System.out.println("colors is: " + colors);
        System.out.println("edges is: ");
        for (int i = 0; i < edges.length; i++) {
            System.out.println(Arrays.toString(edges[i]));
        }
        System.out.println("expected is: " + expected);
        int largestPathValue = largestPathValue(colors, edges); // Calls your implementation
        System.out.println("largestPathValue is: " + largestPathValue);
    }
}
