package leetcode.math;

import java.util.*;

public class FindTheCountOfGoodIntegers {

    public static void main(String[] args) {
        check(3, 5, 27);
        check(1, 4, 2);
        check(10, 3, 13831104);
        check(5, 6, 2468);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/find-the-count-of-good-integers.
     * Time complexity is O(m*10^m) where m = (n+1)/2.
     *
     * @param n
     * @param k
     * @return
     */
    public static long countGoodIntegers(int n, int k) {
        Map<Long, int[]> dict = new HashMap<>();
        int exponent = (n - 1) / 2;
        int digitsCount = exponent + 1;
        int base = (int) Math.pow(10, exponent);
        int skip = n & 1;
        int skipFactor = 9 * skip;
        int numDiv = 1 + skipFactor;
        int factor = (10 - skipFactor) * base;
        /* Enumerate the number of palindrome numbers of n digitsCount */
        for (int i = base; i < base * 10; i++) {
            long otherHalf = 0;
            int num = i / numDiv;
            for (int j = skip; j < digitsCount; j++) {
                int digit = num % 10;
                otherHalf = otherHalf * 10 + digit;
                num /= 10;
            }
            long palindromicInteger = (long) i * factor + otherHalf;
            if (palindromicInteger % k == 0) {
                // the number is palindromic, calculate the frequency of its digits and add to results
                int[] frequency = new int[10];
                for (int j = 0; j < n; j++) {
                    int digit = (int) (palindromicInteger % 10);
                    frequency[digit]++;
                    palindromicInteger /= 10;
                }
                long frequencyToNumber = 0;
                for (int j = 0; j < 10; j++) {
                    frequencyToNumber = frequencyToNumber * 10 + frequency[j];
                }
                dict.put(frequencyToNumber, frequency);
            }
        }
        long[] factorial = new long[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
        long result = 0;
        for (int[] frequency : dict.values()) {
            // calculate permutations and combinations per eligible number and add to the result
            long totalPermutations = (n - frequency[0]) * factorial[n - 1];
            for (int freq : frequency) {
                totalPermutations /= factorial[freq];
            }
            result += totalPermutations;
        }
        return result;
    }

    public long countGoodIntegers2(int n, int k) {
        Set<List<Integer>> dict = new HashSet<>();
        int exponent = (n - 1) / 2;
        int digitsCount = exponent + 1;
        int base = (int) Math.pow(10, exponent);
        int skip = n & 1;
        int factor = (10 - 9 * skip) * base;
        int[] digits = new int[n];
        /* Enumerate the number of palindrome numbers of n digitsCount */
        for (int i = base; i < base * 10; i++) {
            long otherHalf = 0;
            int num = i;
            for (int j = 0; j < digitsCount; j++) {
                int digit = num % 10;
                if (j >= skip) {
                    otherHalf = otherHalf * 10 + digit;
                    digits[n - j - 1 + skip] = digit;
                }
                num /= 10;
                digits[j] = digit;
            }
            long palindromicInteger = (long) i * factor + otherHalf;
            /* If the current palindrome number is a k-palindromic integer */
            if (palindromicInteger % k == 0) {
                Arrays.sort(digits);
                List<Integer> res = new ArrayList<>(n);
                for (int digit : digits) {
                    res.add(digit);
                }
                dict.add(res);
            }
        }
        long[] factorial = new long[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
        long ans = 0;
        for (List<Integer> s : dict) {
            int[] cnt = new int[10];
            for (int i : s) {
                cnt[i]++;
            }
            /* Calculate permutations and combinations */
            long tot = (n - cnt[0]) * factorial[n - 1];
            for (int x : cnt) {
                tot /= factorial[x];
            }
            ans += tot;
        }

        return ans;
    }

    private static void check(int n, int k, long expected) {
        System.out.println("n is: " + n);
        System.out.println("k is: " + k);
        System.out.println("expected is: " + expected);
        long countGoodIntegers = countGoodIntegers(n, k); // Calls your implementation
        System.out.println("countGoodIntegers is: " + countGoodIntegers);
    }
}
