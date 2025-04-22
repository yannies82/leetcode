package leetcode.math;

import java.math.BigInteger;

public class CountTheNumberOfIdealArrays {

    public static void main(String[] args) {
        check(2, 5, 10);
        check(5, 3, 11);
    }

    private static final int MOD = 1000000007;

    /**
     * Leetcode problem: https://leetcode.com/problems/count-the-number-of-ideal-arrays.
     *
     * @param n
     * @param maxValue
     * @return
     */
    public static int idealArrays(int n, int maxValue) {
        // calculate min divisors for all numbers up to maxValue
        int[] minDivisor = new int[maxValue + 1];
        for (int p = 2; p <= maxValue; p++) {
            if (minDivisor[p] != 0) {
                continue;
            }
            for (int i = p; i <= maxValue; i += p) {
                if (minDivisor[i] == 0) {
                    minDivisor[i] = p;
                }
            }
        }
        // calculate log of maxValue with base 2
        int maxPow = (int) (Math.log(maxValue) / Math.log(2));
        // initialize and calculate binCoefficients array
        int[] binCoefficients = new int[maxPow + 1];
        BigInteger prod = BigInteger.ONE;
        BigInteger bigMod = BigInteger.valueOf(MOD);
        for (int i = 1; i <= maxPow; i++) {
            prod = prod.multiply(BigInteger.valueOf(n + i - 1));
            prod = prod.divide(BigInteger.valueOf(i));
            binCoefficients[i] = prod.mod(bigMod).intValue();
        }
        int sum = 0;
        for (int i = 1; i <= maxValue; i++) {
            int num = i;
            long prodBin = 1;
            while (num > 1) {
                int divisor = minDivisor[num];
                int coefficientIndex = 0;
                do {
                    coefficientIndex++;
                    num /= divisor;
                } while (num % divisor == 0);
                prodBin = prodBin * binCoefficients[coefficientIndex] % MOD;
            }
            sum = (sum + (int) prodBin) % MOD;
        }
        return sum;
    }

    private static void check(int n, int maxValue, int expected) {
        System.out.println("n is: " + n);
        System.out.println("maxValue is: " + maxValue);
        System.out.println("expected is: " + expected);
        int idealArrays = idealArrays(n, maxValue); // Calls your implementation
        System.out.println("idealArrays is: " + idealArrays);
    }
}
