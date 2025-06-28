package leetcode.heap;

import java.util.*;

public class FindSubsequenceOfLengthKWithLargestSum {

    public static void main(String[] args) {
        check(new int[]{2, 1, 3, 3}, 2, new int[]{3, 3});
        check(new int[]{-1, -2, 3, 4}, 3, new int[]{-1, 3, 4});
        check(new int[]{3, 4, 3, 3}, 2, new int[]{3, 4});
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum.
     * Time complexity is O(nlogn) where n is the length of the nums array.
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSubsequence(int[] nums, int k) {
        Queue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> nums[a] - nums[b]);
        Set<Integer> indexes = new LinkedHashSet<>();
        for (int i = 0; i < k; i++) {
            priorityQueue.offer(i);
            indexes.add(i);
        }
        int smallestIndex = priorityQueue.peek();
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > nums[smallestIndex]) {
                priorityQueue.poll();
                priorityQueue.offer(i);
                indexes.remove(smallestIndex);
                indexes.add(i);
                smallestIndex = priorityQueue.peek();
            }
        }
        int[] result = new int[k];
        int finalIndex = 0;
        for (int index : indexes) {
            result[finalIndex++] = nums[index];
        }
        return result;
    }

    private static void check(int[] nums, int k, int[] expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("k is: " + k);
        System.out.println("expected is: " + Arrays.toString(expected));
        int[] maxSubsequence = maxSubsequence(nums, k); // Calls your implementation
        System.out.println("maxSubsequence is: " + Arrays.toString(maxSubsequence));
    }
}
