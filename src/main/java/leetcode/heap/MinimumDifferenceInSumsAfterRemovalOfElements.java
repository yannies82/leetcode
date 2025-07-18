package leetcode.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinimumDifferenceInSumsAfterRemovalOfElements {

    public static void main(String[] args) {
        check(new int[]{3, 1, 2}, -1);
        check(new int[]{7, 9, 5, 8, 1, 3}, 1);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/minimum-difference-in-sums-after-removal-of-elements.
     * This solution uses two priority queues to keep the n smallest and n largest elements. Time complexity
     * is O(nlogn) where n is the length of the nums array.
     *
     * @param nums
     * @return
     */
    public static long minimumDifference(int[] nums) {
        int n = nums.length / 3;

        long[] smallestLeft = new long[nums.length];
        long[] largestRight = new long[nums.length];

        Queue<Integer> smallestElements = new PriorityQueue<>((a, b) -> b - a);
        long leftSum = 0;
        for (int i = 0; i < n; ++i) {
            smallestElements.offer(nums[i]);
            leftSum += nums[i];
        }
        smallestLeft[n - 1] = leftSum;
        int currentFromMin = smallestElements.peek();
        for (int i = n; i < nums.length; ++i) {
            if (nums[i] < currentFromMin) {
                leftSum += nums[i] - smallestElements.poll();
                smallestElements.offer(nums[i]);
                currentFromMin = smallestElements.peek();
            }
            smallestLeft[i] = leftSum;
        }

        Queue<Integer> largestElements = new PriorityQueue<>();
        long rightSum = 0;
        int limit = n << 1;
        for (int i = nums.length - 1; i >= limit; i--) {
            largestElements.offer(nums[i]);
            rightSum += nums[i];
        }
        largestRight[limit] = rightSum;
        int currentFromMax = largestElements.peek();
        for (int i = limit - 1; i >= 0; --i) {
            if (nums[i] > currentFromMax) {
                rightSum += nums[i] - largestElements.poll();
                largestElements.offer(nums[i]);
                currentFromMax = largestElements.peek();
            }
            largestRight[i] = rightSum;
        }

        // Calculate minimum difference
        long result = Long.MAX_VALUE;
        for (int i = n - 1; i <= limit - 1; i++) {
            result = Math.min(result, smallestLeft[i] - largestRight[i + 1]);
        }

        return result;
    }

    private static void check(int[] nums, long expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("expected is: " + expected);
        long minimumDifference = minimumDifference(nums); // Calls your implementation
        System.out.println("minimumDifference is: " + minimumDifference);
    }
}
