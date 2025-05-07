package leetcode.graphdjikstra;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class FindMinimumTimeToReachLastRoom {

    public static void main(String[] args) {
        check(new int[][]{{0, 4}, {4, 4}}, 6);
        check(new int[][]{{0, 0, 0}, {0, 0, 0}}, 3);
        check(new int[][]{{0, 1}, {1, 2}}, 3);
        check(new int[][]{{56, 93}, {3, 38}}, 39);
    }

    private static final int[] OFFSET_X = {1, 0, -1, 0};
    private static final int[] OFFSET_Y = {0, 1, 0, -1};

    /**
     * Leetcode problem: https://leetcode.com/problems/find-minimum-time-to-reach-last-room-i.
     * This solution uses the Djikstra algorithm to traverse the grid, favoring routes with
     * lower time barrier. Time complexity is O(m*n*log(m*n)) where n is the number of rows and
     * n is the number of columns in the grid.
     *
     * @param moveTime
     * @return
     */
    public static int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length;
        int m = moveTime[0].length;
        // keeps the minimum time to visit each cell
        int[][] visited = new int[n][m];
        for (int[] row : visited) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        visited[0][0] = 0;
        // used for traversing the grid with Djikstra algorithm
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        queue.offer(new int[]{0, 0, 0});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int i = current[0];
            int j = current[1];
            int time = current[2];
            if (i == n - 1 && j == m - 1) {
                // we have reached the target
                return time;
            }
            // try all possible moves
            for (int k = 0; k < 4; k++) {
                int newI = i + OFFSET_X[k];
                int newJ = j + OFFSET_Y[k];
                if (newI < 0 || newI >= n || newJ < 0 || newJ >= m) {
                    // move is out of bounds
                    continue;
                }
                int newTime = Math.max(time, moveTime[newI][newJ]) + 1;
                if (newTime < visited[newI][newJ]) {
                    // newTime to visit the cell is better than the current one, update it and add cell to queue
                    visited[newI][newJ] = newTime;
                    queue.offer(new int[]{newI, newJ, newTime});
                }
            }
        }
        return visited[n - 1][m - 1];
    }

    private static void check(int[][] moveTime, int expected) {
        System.out.println("moveTime is: ");
        for (int[] row : moveTime) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("expected is: " + expected);
        int minTimeToReach = minTimeToReach(moveTime); // Calls your implementation
        System.out.println("minTimeToReach is: " + minTimeToReach);
    }
}
