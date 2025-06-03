package leetcode.graphgeneral;

import java.util.*;

public class MaximumCandiesYouCanGetFromBoxes {

    public static void main(String[] args) {
        check(new int[]{1, 0, 1, 0}, new int[]{7, 5, 4, 100}, new int[][]{{}, {}, {1}, {}}, new int[][]{{1, 2}, {3}, {}, {}}, new int[]{0}, 16);
        check(new int[]{1, 0, 0, 0, 0, 0}, new int[]{1, 1, 1, 1, 1, 1}, new int[][]{{1, 2, 3, 4, 5}, {}, {}, {}, {}, {}}, new int[][]{{1, 2, 3, 4, 5}, {}, {}, {}, {}, {}}, new int[]{0}, 6);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/maximum-candies-you-can-get-from-boxes.
     * This solution uses DFS and updates the status array every time a key is found. Time complexity is O(n)
     * where n is the length of the status array.
     *
     * @param status
     * @param candies
     * @param keys
     * @param containedBoxes
     * @param initialBoxes
     * @return
     */
    public static int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int candiesCount = 0;
        boolean[] visited = new boolean[status.length];
        for (int box : initialBoxes) {
            dfs(box, status, keys, containedBoxes, visited);
        }
        for (int i = 0; i < candies.length; i++) {
            if (visited[i] && status[i] == 1) {
                // do not count boxes which were locked when visited
                candiesCount += candies[i];
            }
        }
        return candiesCount;
    }


    private static void dfs(int box, int[] status, int[][] keys, int[][] containedBoxes, boolean[] visited) {
        visited[box] = true;
        for (int key : keys[box]) {
            if (key != box) {
                status[key] = 1;
            }
        }
        for (int containedBox : containedBoxes[box]) {
            if (!visited[containedBox]) {
                dfs(containedBox, status, keys, containedBoxes, visited);
            }
        }
    }

    /**
     * This solution uses BFS.
     *
     * @param status
     * @param candies
     * @param keys
     * @param containedBoxes
     * @param initialBoxes
     * @return
     */
    public static int maxCandies2(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> currentKeys = new HashSet<>();
        Set<Integer> lockedBoxes = new HashSet<>();
        for (int box : initialBoxes) {
            if (status[box] == 1) {
                queue.add(box);
            } else {
                lockedBoxes.add(box);
            }
        }
        int totalCandies = 0;
        while (!queue.isEmpty()) {
            int length = queue.size();
            for (int i = 0; i < length; i++) {
                int box = queue.poll();
                totalCandies += candies[box];
                for (int key : keys[box]) {
                    currentKeys.add(key);
                }
                for (Iterator<Integer> iter = lockedBoxes.iterator(); iter.hasNext(); ) {
                    int lockedBox = iter.next();
                    if (currentKeys.contains(lockedBox)) {
                        queue.add(lockedBox);
                        iter.remove();
                    }
                }
                for (int containedBox : containedBoxes[box]) {
                    if (status[containedBox] == 1 || currentKeys.contains(containedBox)) {
                        queue.add(containedBox);
                    } else {
                        lockedBoxes.add(containedBox);
                    }
                }

            }
        }
        return totalCandies;
    }

    private static void check(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes, int expected) {
        System.out.println("status is: " + Arrays.toString(status));
        System.out.println("candies is: " + Arrays.toString(candies));
        System.out.println("keys is: ");
        for (int[] key : keys) {
            System.out.println(Arrays.toString(key));
        }
        System.out.println("containedBoxes is: ");
        for (int[] box : containedBoxes) {
            System.out.println(Arrays.toString(box));
        }
        System.out.println("initialBoxes is: " + Arrays.toString(initialBoxes));
        System.out.println("expected is: " + expected);
        int maxCandies = maxCandies(status, candies, keys, containedBoxes, initialBoxes); // Calls your implementation
        System.out.println("maxCandies is: " + maxCandies);
    }
}
