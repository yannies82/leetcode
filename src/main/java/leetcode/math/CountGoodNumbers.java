package leetcode.math;

public class CountGoodNumbers {

    public static void main(String[] args) {
        check(1, 5);
        check(4, 400);
        check(50, 564908303);
    }

    private static final int MOD = 1000000007;

    /**
     * Leetcode problem: https://leetcode.com/problems/count-good-numbers.
     * Time complexity is O(logn).
     *
     * @param n
     * @return
     */
    public static int countGoodNumbers(long n) {
        return (int) ((fastExponentiation(5, (n + 1) >>> 1) * fastExponentiation(4, n >>> 1)) % MOD);
    }

    // use fast exponentiation to calculate num^exponent % mod
    private static long fastExponentiation(int num, long exponent) {
        long result = 1;
        long factor = num;
        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = (result * factor) % MOD;
            }
            factor = (factor * factor) % MOD;
            exponent >>>= 1;
        }
        return result;
    }

    private static void check(int n, int expected) {
        System.out.println("n is: " + n);
        System.out.println("expected is: " + expected);
        int countGoodNumbers = countGoodNumbers(n); // Calls your implementation
        System.out.println("countGoodNumbers is: " + countGoodNumbers);
    }
}
