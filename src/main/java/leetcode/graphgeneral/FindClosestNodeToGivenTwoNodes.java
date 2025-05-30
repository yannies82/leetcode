package leetcode.graphgeneral;

import java.util.Arrays;

public class FindClosestNodeToGivenTwoNodes {

    public static void main(String[] args) {
        check(new int[]{2, 2, 3, -1}, 0, 1, 2);
        check(new int[]{1, 2, -1}, 0, 2, 2);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/find-closest-node-to-given-two-nodes.
     * This solution finds the distances from each node, then compares the distances.
     * Time complexity is O(n) where n is the length of the edges array.
     *
     * @param edges
     * @param node1
     * @param node2
     * @return
     */
    public static int closestMeetingNode(int[] edges, int node1, int node2) {
        int[] distance1 = new int[edges.length];
        Arrays.fill(distance1, Integer.MAX_VALUE);
        int currentDistance = 0;
        while (node1 != -1 && distance1[node1] == Integer.MAX_VALUE) {
            distance1[node1] = currentDistance++;
            node1 = edges[node1];
        }
        int[] distance2 = new int[edges.length];
        Arrays.fill(distance2, Integer.MAX_VALUE);
        currentDistance = 0;
        while (node2 != -1 && distance2[node2] == Integer.MAX_VALUE) {
            distance2[node2] = currentDistance++;
            node2 = edges[node2];
        }
        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < edges.length; i++) {
            int currentMax = Math.max(distance1[i], distance2[i]);
            if (currentMax < minDistance) {
                minDistance = currentMax;
                minIndex = i;
            }
        }
        return minIndex;
    }

    /**
     * This solution uses DFS to find the distances from each node, then compares the distances.
     * Time complexity is O(n) where n is the length of the edges array.
     *
     * @param edges
     * @param node1
     * @param node2
     * @return
     */
    public static int closestMeetingNode2(int[] edges, int node1, int node2) {
        boolean[] visited = new boolean[edges.length];
        int[] distance1 = new int[edges.length];
        Arrays.fill(distance1, Integer.MAX_VALUE);
        dfs(edges, node1, distance1, visited, 0);
        visited = new boolean[edges.length];
        int[] distance2 = new int[edges.length];
        Arrays.fill(distance2, Integer.MAX_VALUE);
        dfs(edges, node2, distance2, visited, 0);
        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < edges.length; i++) {
            int currentMax = Math.max(distance1[i], distance2[i]);
            if (currentMax < minDistance) {
                minDistance = currentMax;
                minIndex = i;
            }
        }
        return minIndex;
    }

    private static void dfs(int[] edges, int node, int[] distance, boolean[] visited, int currentDistance) {
        distance[node] = currentDistance;
        visited[node] = true;
        if (edges[node] >= 0 && !visited[edges[node]]) {
            dfs(edges, edges[node], distance, visited, currentDistance + 1);
        }
    }

    private static void check(int[] edges, int node1, int node2, int expected) {
        System.out.println("edges is: " + Arrays.toString(edges));
        System.out.println("node1 is: " + node1);
        System.out.println("node2 is: " + node2);
        System.out.println("expected is: " + expected);
        int closestMeetingNode = closestMeetingNode(edges, node1, node2); // Calls your implementation
        System.out.println("closestMeetingNode is: " + closestMeetingNode);
    }
}
