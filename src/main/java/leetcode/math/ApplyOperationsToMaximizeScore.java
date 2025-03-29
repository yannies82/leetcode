package leetcode.math;

import java.util.*;

public class ApplyOperationsToMaximizeScore {

    public static void main(String[] args) {
        check(List.of(3289, 2832, 14858, 22011), 6, 256720975);
        check(List.of(19, 12, 14, 6, 10, 18), 3, 4788);
        check(List.of(8, 3, 9, 3, 8), 2, 81);
    }

    private static final int MOD = 1000000007;

    /**
     * Leetcode problem: https://leetcode.com/problems/apply-operations-to-maximize-score.
     * This solution calculates the prime factor count for all numbers. It then calculates for
     * each number the first index on its left with at least as many prime factors and the first
     * index on its right with more prime factors. Finally it uses this data to calculate the result.
     * Time complexity is O(n * sqrt(m) + n log n) where n is the size of the nums list and m is the
     * mean value of the nums list elements.
     *
     * @param nums
     * @param k
     * @return
     */
    public static int maximumScore(List<Integer> nums, int k) {
        int n = nums.size();
        // calculate prime factor counts for each number and keep them in an array along with the
        // index and the number
        int[][] primeFactorCounts = new int[n][];
        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            primeFactorCounts[i] = new int[]{i, countPrimeFactors(num), num};
        }
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        int[] stack = new int[n];
        int stackIndex = -1;
        // use the stack to calculate for each number the index of the number on its left with
        // at least as many prime factors
        for (int i = 0; i < n; i++) {
            int currentCount = primeFactorCounts[i][1];
            while (stackIndex >= 0 && primeFactorCounts[stack[stackIndex]][1] < currentCount) {
                stackIndex--;
            }
            if (stackIndex >= 0) {
                left[i] = stack[stackIndex];
            }
            stack[++stackIndex] = i;
        }
        stackIndex = -1;
        // use the stack to calculate for each number the index of the number on its right with
        // more prime factors
        for (int i = n - 1; i >= 0; --i) {
            int currentCount = primeFactorCounts[i][1];
            while (stackIndex >= 0 && primeFactorCounts[stack[stackIndex]][1] <= currentCount) {
                stackIndex--;
            }
            if (stackIndex >= 0) {
                right[i] = stack[stackIndex];
            }
            stack[++stackIndex] = i;
        }
        // sort prime factor counts array by number descending so that larger numbers are
        // selected first
        Arrays.sort(primeFactorCounts, (a, b) -> b[2] - a[2]);
        long result = 1;
        // calculate the result by multiplying the larger numbers first
        // for each number in index i calculate the number of subarrays for which it is the selected
        // element by using the left[i] and right[i] values
        for (int i = 0; i < n && k > 0; i++) {
            int index = primeFactorCounts[i][0];
            int number = primeFactorCounts[i][2];
            int leftIndex = left[index];
            int rightIndex = right[index];
            // total number of subarrays is the number of left subarrays * number of right subarrays
            long count = (long) (index - leftIndex) * (rightIndex - index);
            // calculate the number^times value and multiply the result with it, calculating modulo with 10^9 + 7
            // for intermediate operations in order to avoid overflow
            long times = Math.min(count, k);
            result = result * adjustedPower(number, times) % MOD;
            k -= (int) times;
        }
        return (int) result;
    }

    private static int adjustedPower(long number, long times) {
        long result = 1;
        do {
            if ((times & 1) == 1) {
                result = result * number % MOD;
            }
            number = number * number % MOD;
            times >>>= 1;
        } while (times > 0);
        return (int) result;
    }

    /**
     * This solution calculates the count of prime factors for each number, then keeps the count
     * of each selected number for each subarray.
     *
     * @param nums
     * @param k
     * @return
     */
    public static int maximumScore2(List<Integer> nums, int k) {
        int n = nums.size();
        int[] factorCount = new int[n];
        int min = Integer.MAX_VALUE;
        Map<Integer, Integer> sortedMap = new HashMap<>();
        int max = 0;
        for (int i = 0; i < n; i++) {
            Integer num = nums.get(i);
            if (sortedMap.get(num) == null) {
                int primitiveNum = num;
                min = Math.min(min, primitiveNum);
                max = Math.max(max, primitiveNum);
                sortedMap.put(num, countPrimeFactors(primitiveNum));
            }
            factorCount[i] = sortedMap.get(num);
        }
        int[] range = new int[max - min + 1];
        for (int start = 0; start < n; start++) {
            int current = nums.get(start);
            int currentFactorCount = factorCount[start];
            int index = start;
            for (int end = start + 1; end < n; end++) {
                if (factorCount[end] > currentFactorCount) {
                    range[current - min] += end - index;
                    index = end;
                    current = nums.get(end);
                    currentFactorCount = factorCount[end];
                }
            }
            range[current - min] += n - index;
        }
        long total = 1;
        int left = k;
        for (int i = range.length - 1; i >= 0 && left > 0; i--) {
            if (range[i] > 0) {
                int times = Math.min(left, range[i]);
                for (int j = 0; j < times; j++) {
                    total = (total * (i + min)) % 1000000007;
                }
                left -= times;
            }
        }
        return (int) total;
    }

    // A function to print all prime factors
    // of a given number n
    private static int countPrimeFactors(int n) {
        int count = 0;
        // check if n is divisible by 2
        while ((n & 1) == 0) {
            count = 1;
            n >>>= 1;
        }

        // n must be odd at this point.  So we can
        // skip one element (Note i = i + 2)
        for (int i = 3; i * i <= n; i += 2) {
            // While i divides n, print i and divide n
            int isFactor = 0;
            while (n % i == 0) {
                isFactor = 1;
                n /= i;
            }
            count += isFactor;
        }

        // This condition is to handle the case when
        // n is a prime number greater than 2
        if (n > 2) {
            count++;
        }
        return count;
    }

    private static void check(List<Integer> nums, int k, int expected) {
        System.out.println("nums is: " + nums);
        System.out.println("k is: " + k);
        System.out.println("expected is: " + expected);
        int maximumScore = maximumScore(nums, k); // Calls your implementation
        System.out.println("maximumScore is: " + maximumScore);
    }
}
