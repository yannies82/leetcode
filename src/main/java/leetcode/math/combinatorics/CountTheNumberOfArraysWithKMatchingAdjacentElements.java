package leetcode.math.combinatorics;

public class CountTheNumberOfArraysWithKMatchingAdjacentElements {

    public static void main(String[] args) {
        check(3, 2, 1, 4);
        check(4, 2, 2, 6);
        check(5, 2, 0, 2);
    }

    private static final int MOD = 1000000007;

    private static final int[] FACTORIALS = new int[100001];

    static {
        FACTORIALS[0] = 1;
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/count-the-number-of-arrays-with-k-matching-adjacent-elements.
     * Time complexity is O(logn+k).
     *
     * @param n
     * @param m
     * @param k
     * @return
     */
    public static int countGoodArrays(int n, int m, int k) {
        // Formula:
        // m               --> choose value for the first element
        // pow(m-1, n-1-k) --> number of ways to keep same value (for n-1-k positions)
        // calculateNCr(n-1, n-1-k)   --> choose positions to remain the same among (n-1)
        long result = m * pow(m - 1, n - 1 - k) % MOD * calculateNCr(n - 1, n - 1 - k) % MOD;
        return (int) result;
    }

    // Fast exponentiation: computes (num^exponent) % mod
    public static long pow(int num, int exponent) {
        long result = 1;
        long base = num;
        while (exponent > 0) {
            int mod = exponent & 1;
            if (mod == 1) {
                result = result * base % MOD;
            }
            base = base * base % MOD;
            exponent >>>= 1;
        }
        return result;
    }

    // Computes nCr % mod using FACTORIALS and modular inverse
    public static long calculateNCr(int a, int b) {
        return factorial(a) * modularInverse(factorial(a - b)) % MOD * modularInverse(factorial(b)) % MOD;
    }

    // Computes FACTORIALS with memoization: f[a] = a!
    public static long factorial(int a) {
        if (FACTORIALS[a] != 0) {
            return FACTORIALS[a];
        }
        return FACTORIALS[a] = (int) (factorial(a - 1) * a % MOD);
    }

    // Modular inverse using Fermat's Little Theorem: a^(-1) ≡ a^(mod - 2)
    public static long modularInverse(long a) {
        if (a == 1) {
            return a;
        }
        return MOD - MOD / a * modularInverse(MOD % a) % MOD;
    }

    private static void check(int n, int m, int k, int expected) {
        System.out.println("n is: " + n);
        System.out.println("m is: " + m);
        System.out.println("k is: " + k);
        System.out.println("expected is: " + expected);
        int countGoodArrays = countGoodArrays(n, m, k); // Calls your implementation
        System.out.println("countGoodArrays is: " + countGoodArrays);
    }
}
