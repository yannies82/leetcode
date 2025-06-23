package leetcode.math;

public class SumOfKMirrorNumbers {

    public static void main(String[] args) {
        check(2, 5, 25);
        check(3, 7, 499);
        check(7, 17, 20379000);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/sum-of-k-mirror-numbers.
     * This solution generates all possible palindromes in base 10, then checks if they
     * are palindromes in base k and puts them in the result array until it contains n
     * results.
     *
     * @param k
     * @param n
     * @return
     */
    public static long kMirror(int k, int n) {
        int[] palindromeChecker = new int[100];
        long[] result = new long[n];
        int index = 0;
        // check numbers 1-9 which are always palindromes in base 10
        for (long i = 1; i < 10 && index < result.length; i++) {
            if (isPalindromeBaseK(i, k, palindromeChecker)) {
                result[index++] = i;
            }
        }
        long start = 1;
        long factor = 10;
        while (index < result.length) {
            // check even palindromes first because they are smaller than odd palindromes
            for (long i = start; i < factor && index < result.length; i++) {
                long reversedI = reverse(i);
                long evenPalindrome = i * factor + reversedI;
                if (isPalindromeBaseK(evenPalindrome, k, palindromeChecker)) {
                    result[index++] = evenPalindrome;
                }
            }
            // check odd palindromes, middle number should range from 0 to 9
            long oddFactor = factor * 10;
            for (long i = start; i < factor && index < result.length; i++) {
                long reversedI = reverse(i);
                long iFactor = i * oddFactor + reversedI;
                for (int j = 0; j < 10 && index < result.length; j++) {
                    long oddPalindrome = iFactor + j * factor;
                    if (isPalindromeBaseK(oddPalindrome, k, palindromeChecker)) {
                        result[index++] = oddPalindrome;
                    }
                }
            }
            start *= 10;
            factor = oddFactor;
        }
        // sum all numbers
        long sum = 0;
        for (long num : result) {
            sum += num;
        }
        return sum;
    }

    private static long reverse(long num) {
        long result = 0;
        do {
            result = result * 10 + num % 10;
            num /= 10;
        } while (num > 0);
        return result;
    }

    private static boolean isPalindromeBaseK(long num, int k, int[] palindromeChecker) {
        int index = 0;
        do {
            palindromeChecker[index++] = (int) (num % k);
            num /= k;
        } while (num > 0);
        return isPalindrome(palindromeChecker, index);
    }

    private static boolean isPalindrome(int[] palindromeChecker, int length) {
        int mid = length >>> 1;
        int lastIndex = length - 1;
        for (int i = 0; i < mid; i++) {
            if (palindromeChecker[i] != palindromeChecker[lastIndex - i]) {
                return false;
            }
        }
        return true;
    }

    private static void check(int k, int n, int expected) {
        System.out.println("k is: " + k);
        System.out.println("n is: " + n);
        System.out.println("expected is: " + expected);
        long kMirror = kMirror(k, n); // Calls your implementation
        System.out.println("kMirror is: " + kMirror);
    }

}
