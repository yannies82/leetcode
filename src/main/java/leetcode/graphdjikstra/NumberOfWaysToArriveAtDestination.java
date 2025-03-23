package leetcode.graphdjikstra;

import java.util.*;

public class NumberOfWaysToArriveAtDestination {

    public static void main(String[] args) {
        check(7, new int[][]{{0, 6, 7}, {0, 1, 2}, {1, 2, 3}, {1, 3, 3}, {6, 3, 3}, {3, 5, 1}, {6, 5, 1}, {2, 5, 1}, {0, 4, 5}, {4, 6, 2}}, 4);
        check(2, new int[][]{{1, 0, 10}}, 1);
    }

    private static int MOD = 1_000_000_007;
    private static long INFINITY = Long.MAX_VALUE >>> 1;

    /**
     * Leetcode problem: https://leetcode.com/problems/number-of-ways-to-arrive-at-destination.
     * This solution uses the Djikstra algorithm to calculate the shortest paths to all nodes.
     * Time complexity is O((n+e)logn) where e is the length of the roads array
     * (number of edges in the graph).
     *
     * @param n
     * @param roads
     * @return
     */
    public static int countPaths(int n, int[][] roads) {
        // initialize and populate adjacency lists
        List<List<int[]>> adjacencyLists = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyLists.add(new ArrayList<>());
        }
        for (int i = 0; i < roads.length; i++) {
            adjacencyLists.get(roads[i][0]).add(new int[]{roads[i][1], roads[i][2]});
            adjacencyLists.get(roads[i][1]).add(new int[]{roads[i][0], roads[i][2]});
        }
        // priority queue to be used as min heap for Djikstra algorithm
        Queue<long[]> minHeap = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        // keep the min time to each node, by default it is set to infinite for all nodes
        // except for node 0 for which the time is 0
        long[] minTime = new long[n];
        Arrays.fill(minTime, 1, n, Long.MAX_VALUE);
        // keep the number of paths to reach each node in min time, by default it is set to 0 for all nodes
        // except for node 0 for which there is 1 path
        int[] numPaths = new int[n];
        numPaths[0] = 1;
        // offer the starting node 0 to the min heap
        minHeap.offer(new long[]{0, 0});
        // perform Djikstra algorithm
        while (!minHeap.isEmpty()) {
            long[] current = minHeap.poll();
            long currentTime = current[0];
            int currentNode = (int) current[1];
            // ignore connections with time less than optimal time
            if (currentTime > minTime[currentNode]) {
                continue;
            }
            for (int[] neighbor : adjacencyLists.get(currentNode)) {
                int neighborNode = neighbor[0];
                int time = neighbor[1];
                // calculate time to the neighbor node
                long newTime = currentTime + time;
                if (newTime < minTime[neighborNode]) {
                    // the new time to neighbor node is better than the current min time
                    // update the min time and the number of paths with the min time
                    minTime[neighborNode] = newTime;
                    numPaths[neighborNode] = numPaths[currentNode];
                    // also add the node to the min heap
                    minHeap.offer(new long[]{newTime, neighborNode});
                } else if (newTime == minTime[neighborNode]) {
                    // the new time to neighbor node is the same as the current min time
                    // add to the current number of paths with min time
                    numPaths[neighborNode] = (numPaths[neighborNode] + numPaths[currentNode]) % MOD;
                }
            }
        }
        return numPaths[n - 1];
    }

    /**
     * Alternative solution.
     * Time complexity is O(n^2 + m) where m is the length of the roads array.
     *
     * @param n
     * @param roads
     * @return
     */
    public static int countPaths2(int n, int[][] roads) {
        long[][] graph = new long[n][n];
        long[] minTime = new long[n];
        long[] numPaths = new long[n];
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], INFINITY);
        }
        Arrays.fill(minTime, 1, n, INFINITY);
        for (int[] road : roads) {
            graph[road[0]][road[1]] = road[2];
            graph[road[1]][road[0]] = road[2];
        }
        graph[0][0] = 0;
        numPaths[0] = 1;
        for (int i = 0; i < n; i++) {
            int currentNode = -1;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && (currentNode == -1 || minTime[j] < minTime[currentNode])) {
                    currentNode = j;
                }
            }
            visited[currentNode] = true;
            for (int j = 0; j < n; j++) {
                if (j == currentNode) {
                    continue;
                }
                long newTime = minTime[currentNode] + graph[currentNode][j];
                if (newTime < minTime[j]) {
                    minTime[j] = newTime;
                    numPaths[j] = numPaths[currentNode];
                } else if (newTime == minTime[j]) {
                    numPaths[j] = (numPaths[j] + numPaths[currentNode]) % MOD;
                }
            }
        }
        return (int) numPaths[n - 1];
    }

    private static void check(int n, int[][] roads, int expected) {
        System.out.println("n is: " + n);
        System.out.println("roads is: ");
        for (int[] road : roads) {
            System.out.println(Arrays.toString(road));
        }
        System.out.println("expected is: " + expected);
        int countPaths = countPaths(n, roads); // Calls your implementation
        System.out.println("countPaths is: " + countPaths);
    }
}
