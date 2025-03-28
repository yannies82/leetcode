package leetcode.graphdjikstra;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

public class MaximumNumberOfPointsFromGridQueries {

    public static void main(String[] args) {
        check(new int[][]{{1, 2, 3}, {2, 5, 7}, {3, 5, 1}}, new int[]{5, 6, 2}, new int[]{5, 8, 1});
        check(new int[][]{{5, 2, 1}, {1, 1, 2}}, new int[]{3}, new int[]{0});
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/maximum-number-of-points-from-grid-queries.
     * This solution uses Djikstra algorithm and binary search to answer the queries. Time complexity
     * is O((n*m+k)*log(m*n)) where m is the number of rows and n is the number of columns in the grid
     * and k is the number of queries.
     *
     * @param grid
     * @param queries
     * @return
     */
    public static int[] maxPoints(int[][] grid, int[] queries) {
        // keeps the limits required to reach each position
        int[][] limitsPerPosition = new int[grid.length][grid[0].length];
        for (int[] row : limitsPerPosition) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        limitsPerPosition[0][0] = grid[0][0];
        int totalCells = grid.length * grid[0].length;
        // keep the limits for all possible points (max possible points are totalCells + 1)
        int[] limitForPoints = new int[totalCells + 1];
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        queue.offer(new int[]{0, 0, grid[0][0]});
        int points = 0;
        // perform Djikstra algorithm using the priority queue
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int i = current[0];
            int j = current[1];
            int minValue = current[2];
            // a query value greater than minValue is required in order to gather these points
            limitForPoints[++points] = minValue;
            // check all next possible positions
            if (i < grid.length - 1 && limitsPerPosition[i + 1][j] == Integer.MAX_VALUE) {
                // position has not been visited yet, update its limit
                int newMinValue = Math.max(minValue, grid[i + 1][j]);
                limitsPerPosition[i + 1][j] = newMinValue;
                queue.offer(new int[]{i + 1, j, newMinValue});
            }
            if (j < grid[0].length - 1 && limitsPerPosition[i][j + 1] == Integer.MAX_VALUE) {
                // position has not been visited yet, update its limit
                int newMinValue = Math.max(minValue, grid[i][j + 1]);
                limitsPerPosition[i][j + 1] = newMinValue;
                queue.offer(new int[]{i, j + 1, newMinValue});
            }
            if (i > 0 && limitsPerPosition[i - 1][j] == Integer.MAX_VALUE) {
                // position has not been visited yet, update its limit
                int newMinValue = Math.max(minValue, grid[i - 1][j]);
                limitsPerPosition[i - 1][j] = newMinValue;
                queue.offer(new int[]{i - 1, j, newMinValue});
            }
            if (j > 0 && limitsPerPosition[i][j - 1] == Integer.MAX_VALUE) {
                // position has not been visited yet, update its limit
                int newMinValue = Math.max(minValue, grid[i][j - 1]);
                limitsPerPosition[i][j - 1] = newMinValue;
                queue.offer(new int[]{i, j - 1, newMinValue});
            }
        }
        // limitForPoints contains a non decreasing relationship of points to values for which these points can be collected
        // perform binary search for each query to find the max number of points that can be collected within each query value
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int threshold = queries[i];
            int left = 0;
            int right = totalCells + 1;
            int maxPoints = 0;
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (limitForPoints[mid] < threshold) {
                    maxPoints = mid;
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            result[i] = maxPoints;
        }
        return result;
    }

    /**
     * This solution uses Djikstra algorithm to traverse the positions, so that paths with smaller
     * value threshold are favored. It keeps points in a treemap and aggregates them.
     *
     * @param grid
     * @param queries
     * @return
     */
    public static int[] maxPoints2(int[][] grid, int[] queries) {
        TreeMap<Integer, Integer> sortedMap = new TreeMap<>();
        int[][] visited = new int[grid.length][grid[0].length];
        for (int[] row : visited) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        queue.offer(new int[]{0, 0, grid[0][0] + 1});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int i = current[0];
            int j = current[1];
            int minValue = current[2];
            if (minValue >= visited[i][j]) {
                continue;
            }
            visited[i][j] = minValue;
            sortedMap.put(minValue, sortedMap.getOrDefault(minValue, 0) + 1);
            if (i < grid.length - 1) {
                int newMinValue = Math.max(minValue, grid[i + 1][j] + 1);
                if (newMinValue < visited[i + 1][j]) {
                    queue.offer(new int[]{i + 1, j, newMinValue});
                }
            }
            if (j < grid[0].length - 1) {
                int newMinValue = Math.max(minValue, grid[i][j + 1] + 1);
                if (newMinValue < visited[i][j + 1]) {
                    queue.offer(new int[]{i, j + 1, newMinValue});
                }
            }
            if (i > 0) {
                int newMinValue = Math.max(minValue, grid[i - 1][j] + 1);
                if (newMinValue < visited[i - 1][j]) {
                    queue.offer(new int[]{i - 1, j, newMinValue});
                }
            }
            if (j > 0) {
                int newMinValue = Math.max(minValue, grid[i][j - 1] + 1);
                if (newMinValue < visited[i][j - 1]) {
                    queue.offer(new int[]{i, j - 1, newMinValue});
                }
            }
        }
        int prevPoints = 0;
        for (Integer key : sortedMap.keySet()) {
            int newPoints = sortedMap.get(key) + prevPoints;
            sortedMap.put(key, newPoints);
            prevPoints = newPoints;
        }
        sortedMap.put(0, 0);
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            Integer targetKey = sortedMap.floorKey(queries[i]);
            result[i] = sortedMap.get(targetKey);
        }
        return result;
    }

    /**
     * Solution which uses DFS. Slow because positions can be visited multiple times.
     *
     * @param grid
     * @param queries
     * @return
     */
    public static int[] maxPoints3(int[][] grid, int[] queries) {
        TreeMap<Integer, Integer> sortedMap = new TreeMap<>();
        int[][] visited = new int[grid.length][grid[0].length];
        performDfs(grid, 0, 0, grid[0][0] + 1, visited, sortedMap);
        int prevPoints = 0;
        for (Integer key : sortedMap.keySet()) {
            int newPoints = sortedMap.get(key) + prevPoints;
            sortedMap.put(key, newPoints);
            prevPoints = newPoints;
        }
        sortedMap.put(0, 0);
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            Integer targetKey = sortedMap.floorKey(queries[i]);
            result[i] = sortedMap.get(targetKey);
        }
        return result;
    }

    private static void performDfs(int[][] grid, int i, int j, int minValue, int[][] visited, TreeMap<Integer, Integer> sortedMap) {
        if (i < 0 || j < 0 || i == grid.length || j == grid[0].length) {
            return;
        }
        if (minValue <= grid[i][j]) {
            minValue = grid[i][j] + 1;
        }
        if (visited[i][j] > 0) {
            if (minValue >= visited[i][j]) {
                return;
            }
            sortedMap.put(visited[i][j], sortedMap.getOrDefault(visited[i][j], 0) - 1);
        }
        visited[i][j] = minValue;
        sortedMap.put(minValue, sortedMap.getOrDefault(minValue, 0) + 1);
        performDfs(grid, i + 1, j, minValue, visited, sortedMap);
        performDfs(grid, i, j + 1, minValue, visited, sortedMap);
        performDfs(grid, i - 1, j, minValue, visited, sortedMap);
        performDfs(grid, i, j - 1, minValue, visited, sortedMap);
    }

    private static void check(int[][] grid, int[] queries, int[] expected) {
        System.out.println("grid is: ");
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("queries is: " + Arrays.toString(queries));
        System.out.println("expected is: " + Arrays.toString(expected));
        int[] maxPoints = maxPoints(grid, queries); // Calls your implementation
        System.out.println("maxPoints is: " + Arrays.toString(maxPoints));
    }
}
