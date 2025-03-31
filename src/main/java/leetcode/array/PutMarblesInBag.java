package leetcode.array;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class PutMarblesInBag {

    public static void main(String[] args) {
        check(new int[]{1, 3, 5, 1}, 2, 4);
        check(new int[]{1, 3}, 2, 0);
        check(new int[]{25, 74, 16, 51, 12, 48, 15, 5}, 1, 0);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/put-marbles-in-bags. This solution calculates
     * all intermediate pair sums and sorts them. It then subtracts the k lesser sums from the k greater ones.
     * Time complexity is O(nlogn) where n is the length of the weights array.
     *
     * @param weights
     * @param k
     * @return
     */
    public static long putMarbles(int[] weights, int k) {
        if (k == 1 || k == weights.length) {
            return 0;
        }
        // calculate all pair sums, then sort them
        int[] pairSums = new int[weights.length - 1];
        int limit = weights.length - 1;
        for (int i = 0; i < limit; i++) {
            pairSums[i] = weights[i] + weights[i + 1];
        }
        Arrays.sort(pairSums);
        // calculate the result by subtracting the k - 1 lesser pairs from the k - 1 greater ones
        long result = 0;
        int pairsLimit = weights.length - 2;
        int kLimit = k - 1;
        for (int i = 0; i < kLimit; i++) {
            result += pairSums[pairsLimit - i] - pairSums[i];
        }
        return result;
    }

    /**
     * Similar solution using two priority queues. Time complexity is O(nlogn) where n is the length of the weights array.
     *
     * @param weights
     * @param k
     * @return
     */
    public static long putMarbles2(int[] weights, int k) {
        if (k == 1) {
            return 0;
        }
        Queue<Integer> queueMin = new PriorityQueue<>((a, b) -> b - a);
        Queue<Integer> queueMax = new PriorityQueue<>((a, b) -> a - b);
        for (int i = 1; i < k; i++) {
            int sum = weights[i] + weights[i - 1];
            queueMin.offer(sum);
            queueMax.offer(sum);
        }
        for (int i = k; i < weights.length; i++) {
            int sum = weights[i] + weights[i - 1];
            if (sum < queueMin.peek()) {
                queueMin.poll();
                queueMin.offer(sum);
            }
            if (sum > queueMax.peek()) {
                queueMax.poll();
                queueMax.offer(sum);
            }
        }
        long minSum = 0;
        int minSize = queueMin.size();
        for (int i = 0; i < minSize; i++) {
            minSum += queueMin.poll();
        }
        long maxSum = 0;
        int maxSize = queueMax.size();
        for (int i = 0; i < maxSize; i++) {
            maxSum += queueMax.poll();
        }
        return maxSum - minSum;
    }

    private static void check(int[] weights, int k, long expected) {
        System.out.println("weights is: " + Arrays.toString(weights));
        System.out.println("k is: " + k);
        System.out.println("expected is: " + expected);
        long putMarbles = putMarbles(weights, k); // Calls your implementation
        System.out.println("putMarbles is: " + putMarbles);
    }
}
