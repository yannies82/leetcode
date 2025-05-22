package leetcode.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class ZeroArrayTransformation3 {

    public static void main(String[] args) {
        check(new int[]{2, 0, 2}, new int[][]{{0, 2}, {0, 2}, {1, 1}}, 1);
        check(new int[]{1, 1, 1, 1}, new int[][]{{1, 3}, {0, 2}, {1, 3}, {1, 2}}, 2);
        check(new int[]{1, 2, 3, 4}, new int[][]{{0, 3}}, -1);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/zero-array-transformation-iii.
     * Time complexity is O(n+mlogm) where n is the length of the nums array and m is
     * the length of the queries array.
     *
     * @param nums
     * @param queries
     * @return
     */
    public static int maxRemoval(int[] nums, int[][] queries) {
        Arrays.sort(queries, (a, b) -> a[0] - b[0]);
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        // keeps the range for the applied queries
        int[] range = new int[nums.length + 1];
        for (int i = 0, j = 0; i < nums.length; i++) {
            // calculate the current range and diff to be reduced
            range[i] += (i == 0) ? 0 : range[i - 1];
            int diff = nums[i] - range[i];
            while (j < queries.length && queries[j][0] == i) {
                // add to the queue all queries which can decrease the diff
                queue.offer(queries[j++][1]);
            }
            while (diff > 0) {
                if (queue.isEmpty()) {
                    // no available queries to reduce the diff
                    return -1;
                }
                int limit = queue.remove();
                if (limit >= i) {
                    // limit is greater than or equal to i, diff can be reduced
                    diff--;
                    // mark the appropriate range indexes with the effects of applying the query
                    range[i]++;
                    range[limit + 1]--;
                }
            }
        }
        return queue.size();
    }

    private static void check(int[] nums, int[][] queries, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("queries is: ");
        for (int[] query : queries) {
            System.out.println(Arrays.toString(query));
        }
        System.out.println("expected is: " + expected);
        int maxRemoval = maxRemoval(nums, queries); // Calls your implementation
        System.out.println("maxRemoval is: " + maxRemoval);
    }
}
