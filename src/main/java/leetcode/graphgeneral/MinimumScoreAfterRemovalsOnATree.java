package leetcode.graphgeneral;

import java.util.*;

public class MinimumScoreAfterRemovalsOnATree {

    public static void main(String[] args) {
        check(new int[]{1, 5, 5, 4, 11}, new int[][]{{0, 1}, {1, 2}, {1, 3}, {3, 4}}, 9);
        check(new int[]{5, 5, 2, 4, 4, 2}, new int[][]{{0, 1}, {1, 2}, {5, 2}, {4, 3}, {1, 3}}, 0);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/minimum-score-after-removals-on-a-tree.
     * This solution uses DFS to precalculate the subtree XORs. Time complexity is O(n^2) where
     * n is the number of nides in the tree (length of the nums array).
     *
     * @param nums
     * @param edges
     * @return
     */
    public static int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        List<Integer>[] adjacencyList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adjacencyList[edge[0]].add(edge[1]);
            adjacencyList[edge[1]].add(edge[0]);
        }
        // will keep the precalculated xor for all subtrees
        int[] subtreeXor = new int[n];
        // keeps the descendants of each node
        Set<Integer>[] descendants = new HashSet[n];
        for (int i = 0; i < n; i++) {
            descendants[i] = new HashSet<>();
        }
        // perform dfs to calculate subtree xors starting from node 0
        dfs(0, -1, nums, adjacencyList, subtreeXor, descendants);

        int totalXor = subtreeXor[0];
        int minScore = Integer.MAX_VALUE;
        // try removing all pairs of edges and calculate the 3 xors using the precalculated results
        int[] xor = new int[3];
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int xorI = subtreeXor[i];
                int xorJ = subtreeXor[j];
                if (descendants[i].contains(j)) { // j is in i's subtree
                    xor[0] = xorJ;
                    xor[1] = xorI ^ xorJ;
                    xor[2] = totalXor ^ xorI;
                } else if (descendants[j].contains(i)) { // i is in j's subtree
                    xor[0] = xorI;
                    xor[1] = xorJ ^ xorI;
                    xor[2] = totalXor ^ xorJ;
                } else { // Independent subtrees
                    xor[0] = xorI;
                    xor[1] = xorJ;
                    xor[2] = totalXor ^ xorI ^ xorJ;
                }
                int maxVal = Math.max(xor[0], Math.max(xor[1], xor[2]));
                int minVal = Math.min(xor[0], Math.min(xor[1], xor[2]));
                minScore = Math.min(minScore, maxVal - minVal);
            }
        }
        return minScore;
    }

    private static void dfs(int node, int parent, int[] nums, List<Integer>[] adjacencyList, int[] subtreeXor, Set<Integer>[] descendants) {
        subtreeXor[node] = nums[node];
        descendants[node].add(node);

        for (int neighbor : adjacencyList[node]) {
            if (neighbor != parent) {
                dfs(neighbor, node, nums, adjacencyList, subtreeXor, descendants);
                subtreeXor[node] ^= subtreeXor[neighbor];
                descendants[node].addAll(descendants[neighbor]);
            }
        }
    }

    private static void check(int[] nums, int[][] edges, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("edges is: ");
        for (int i = 0; i < edges.length; i++) {
            System.out.println(Arrays.toString(edges[i]));
        }
        System.out.println("expected is: " + expected);
        int minimumScore = minimumScore(nums, edges); // Calls your implementation
        System.out.println("minimumScore is: " + minimumScore);
    }
}
