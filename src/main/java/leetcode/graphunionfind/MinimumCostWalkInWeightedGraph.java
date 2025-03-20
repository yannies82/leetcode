package leetcode.graphunionfind;

import java.util.*;

public class MinimumCostWalkInWeightedGraph {

    public static void main(String[] args) {
        check(5, new int[][]{{0, 1, 7}, {1, 3, 7}, {1, 2, 1}}, new int[][]{{0, 3}, {3, 4}}, new int[]{1, -1});
        check(3, new int[][]{{0, 2, 7}, {0, 1, 15}, {1, 2, 6}, {1, 2, 1}}, new int[][]{{1, 2}}, new int[]{0});
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/minimum-cost-walk-in-weighted-graph. This solution
     * uses uses the union find algorithm. Time complexity is O(n+m+o) where m is the number of edges and o
     * is the number of queries.
     *
     * @param n
     * @param edges
     * @param query
     * @return
     */
    public static int[] minimumCost(int n, int[][] edges, int[][] query) {
        int[] rank = new int[n];
        int[] parent = new int[n];
        int[] val = new int[n];
        // initialize arrays
        for (int i = 0; i < n; i++) {
            rank[i] = 1;
            parent[i] = i;
            val[i] = -1;
        }
        for (int i = 0; i < edges.length; i++) {
            int node1 = edges[i][0];
            int node2 = edges[i][1];
            int weight = edges[i][2];
            int parent1 = find(parent, node1);
            int parent2 = find(parent, node2);
            if (parent1 != parent2) {
                // calculate the common parent with the highest rank for the two nodes
                union(parent, rank, val, parent1, parent2);
                // update the common parent
                parent1 = find(parent, node1);
                find(parent, node2);
            }
            // append to the value for the common parent of the two nodes
            val[parent1] = val[parent1] & weight;
        }
        int[] result = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            int node1 = query[i][0];
            int node2 = query[i][1];
            int parent1 = find(parent, node1);
            int parent2 = find(parent, node2);
            if (node1 == node2) {
                parent[i] = 0;
            } else if (parent1 != parent2) {
                // the nodes have no common parent, they are not connected
                result[i] = -1;
            } else {
                // the nodes have a common parent, return the value for this parent
                result[i] = val[parent1];
            }
        }
        return result;
    }

    public static int find(int[] parent, int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent, parent[x]);
    }

    public static void union(int[] parent, int[] rank, int[] val, int parent1, int parent2) {
        if (rank[parent1] > rank[parent2]) {
            val[parent1] &= val[parent2];
            parent[parent2] = parent1;
        } else if (rank[parent1] < rank[parent2]) {
            val[parent2] &= val[parent1];
            parent[parent1] = parent2;
        } else {
            val[parent1] &= val[parent2];
            parent[parent2] = parent1;
            rank[parent1]++;
        }
    }

    /**
     * This solution uses an adjacency list and DFS traversal to find islands of nodes and the minimum AND value between them.
     *
     * @param n
     * @param edges
     * @param query
     * @return
     */
    public static int[] minimumCost2(int n, int[][] edges, int[][] query) {
        List<Map<Integer, Integer>> adjacencyLists = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyLists.add(new HashMap<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int currentA = adjacencyLists.get(edges[i][0]).getOrDefault(edges[i][1], edges[i][2]);
            adjacencyLists.get(edges[i][0]).put(edges[i][1], edges[i][2] & currentA);
            int currentB = adjacencyLists.get(edges[i][1]).getOrDefault(edges[i][0], edges[i][2]);
            adjacencyLists.get(edges[i][1]).put(edges[i][0], edges[i][2] & currentB);
        }
        int currentIslandIndex = 1;
        int[] islandIndexes = new int[n];
        int[] minCosts = new int[n];
        int[] result = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            if (islandIndexes[query[i][0]] == 0 && islandIndexes[query[i][1]] == 0) {
                Set<Integer> visited = new HashSet<>();
                int[] value = {-1};
                performDfs(adjacencyLists, visited, query[i][0], value);
                for (int node : visited) {
                    islandIndexes[node] = currentIslandIndex;
                    minCosts[node] = value[0];
                }
                currentIslandIndex++;
            }
            if (islandIndexes[query[i][0]] == islandIndexes[query[i][1]]) {
                result[i] = minCosts[query[i][0]];
            } else {
                result[i] = -1;
            }
        }
        return result;
    }

    private static void performDfs(List<Map<Integer, Integer>> adjacencyLists, Set<Integer> visited, int node, int[] value) {
        visited.add(node);
        for (Map.Entry<Integer, Integer> neighbor : adjacencyLists.get(node).entrySet()) {
            value[0] &= neighbor.getValue();
            if (!visited.contains(neighbor.getKey())) {
                performDfs(adjacencyLists, visited, neighbor.getKey(), value);
            }
        }
    }

    private static void check(int n, int[][] edges, int[][] query, int[] expected) {
        System.out.println("n is: " + n);
        System.out.println("edges is: ");
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
        System.out.println("query is: ");
        for (int[] aQuery : query) {
            System.out.println(Arrays.toString(aQuery));
        }
        System.out.println("expected is: " + Arrays.toString(expected));
        int[] minimumCost = minimumCost(n, edges, query); // Calls your implementation
        System.out.println("minimumCost is: " + Arrays.toString(minimumCost));
    }
}
