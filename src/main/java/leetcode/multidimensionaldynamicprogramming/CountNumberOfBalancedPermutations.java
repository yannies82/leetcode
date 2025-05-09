package leetcode.multidimensionaldynamicprogramming;

public class CountNumberOfBalancedPermutations {

    public static void main(String[] args) {
        check("123", 2);
        check("112", 1);
        check("12345", 0);
    }

    private static final int MOD = 1_000_000_007;

    /**
     * Leetcode problem: https://leetcode.com/problems/count-number-of-balanced-permutations.
     * This solution uses bottom up dynamic programming. Time complexity is O(m*n) where m
     * is the sum of the digits and n is the length of the num string.
     *
     * @param num
     * @return
     */
    public static int countBalancedPermutations(String num) {
        char[] chars = num.toCharArray();
        int length = num.length();
        int sum = 0;
        // calculate sum of all digitsFrequency
        for (char aChar : chars) {
            sum += aChar - '0';
        }
        // if sum is odd it cannot be equally split into two parts
        if ((sum & 1) == 1) {
            return 0;
        }
        // precompute all factorials and inverse factorials required for the calculations
        long[] fact = new long[length + 1];
        long[] inv = new long[length + 1];
        long[] invFact = new long[length + 1];
        precomputeFactorials(fact, inv, invFact);
        int halfSum = sum >>> 1;
        int halfLength = length >>> 1;
        // use dynamic programming, dimensions are the sum of the digits and the number of digits
        int[][] dpArray = new int[halfSum + 1][halfLength + 1];
        dpArray[0][0] = 1;
        // keeps frequency of each digit in the string
        int[] digitsFrequency = new int[10];
        for (char aChar : chars) {
            int digit = aChar - '0';
            digitsFrequency[digit]++;
            for (int i = halfSum; i >= digit; i--) {
                for (int j = halfLength; j > 0; j--) {
                    dpArray[i][j] = (dpArray[i][j] + dpArray[i - digit][j - 1]) % MOD;
                }
            }
        }
        // result is the solution for the last subproblem
        long result = dpArray[halfSum][halfLength];
        result = result * fact[halfLength] % MOD * fact[length - halfLength] % MOD;
        for (int count : digitsFrequency) {
            result = result * invFact[count] % MOD;
        }
        return (int) result;
    }

    private static void precomputeFactorials(long[] fact, long[] inv, long[] invFact) {
        fact[0] = inv[0] = invFact[0] = 1;
        for (int i = 1; i < fact.length; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        inv[1] = 1;
        for (int i = 2; i < inv.length; i++) {
            inv[i] = MOD - (MOD / i) * inv[MOD % i] % MOD;
        }
        for (int i = 1; i < invFact.length; i++) {
            invFact[i] = invFact[i - 1] * inv[i] % MOD;
        }
    }

    private static void check(String num, int expected) {
        System.out.println("num is: " + num);
        System.out.println("expected is: " + expected);
        int countBalancedPermutations = countBalancedPermutations(num); // Calls your implementation
        System.out.println("countBalancedPermutations is: " + countBalancedPermutations);
    }
}
