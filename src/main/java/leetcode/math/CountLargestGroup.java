package leetcode.math;

public class CountLargestGroup {

    public static void main(String[] args) {
        check(13, 4);
        check(2, 2);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/count-largest-group.
     * Time complexity is O(n).
     *
     * @param n
     * @return
     */
    public static int countLargestGroup(int n) {
        int result = 0;
        int max = 0;
        int[] digitsSum = new int[n + 1];
        int[] frequency = new int[101];
        // calculate digits sum for all numbers, as well as frequency of sums
        for (int i = 1; i <= n; i++) {
            digitsSum[i] = i % 10 + digitsSum[i / 10];
            frequency[digitsSum[i]]++;
        }
        // find occurrence of highest frequency
        for (int i = 0; i < frequency.length; i++) {
            int current = frequency[i];
            if (max < current) {
                max = current;
                result = 1;
            } else if (max == current) {
                result++;
            }
        }
        return result;
    }

    public static int countLargestGroup2(int n) {
        int maxGroupSize = 0;
        int digits = (int) Math.log10(n) + 1;
        int[] counts = new int[9 * digits + 1];
        int[] countsOfCounts = new int[1000];
        for (int i = 1; i <= n; i++) {
            int digitsSum = calculateDigitsSum(i);
            int prevCount = counts[digitsSum];
            countsOfCounts[prevCount]--;
            int newCount = ++counts[digitsSum];
            countsOfCounts[newCount]++;
            if (newCount > maxGroupSize) {
                maxGroupSize = newCount;
            }
        }
        return countsOfCounts[maxGroupSize];
    }

    private static int calculateDigitsSum(int n) {
        int sum = 0;
        do {
            sum += n % 10;
            n /= 10;
        } while (n > 0);
        return sum;
    }

    private static void check(int n, int expected) {
        System.out.println("n is: " + n);
        System.out.println("expected is: " + expected);
        int countLargestGroup = countLargestGroup(n); // Calls your implementation
        System.out.println("countLargestGroup is: " + countLargestGroup);
    }
}
