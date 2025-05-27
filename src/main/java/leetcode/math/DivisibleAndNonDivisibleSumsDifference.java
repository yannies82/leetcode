package leetcode.math;

public class DivisibleAndNonDivisibleSumsDifference {

    public static void main(String[] args) {
        check(10, 3, 19);
        check(5, 6, 15);
        check(5, 1, -15);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/divisible-and-non-divisible-sums-difference.
     * Time complexity is O(n).
     *
     * @param n
     * @param m
     * @return
     */
    public static int differenceOfSums(int n, int m) {
        int num1 = 0;
        int num2 = 0;
        for (int i = 1; i <= n; i++) {
            if (i % m == 0) {
                num2 += i;
            } else {
                num1 += i;
            }
        }
        return num1 - num2;
    }

    private static void check(int n, int m, int expected) {
        System.out.println("n is: " + n);
        System.out.println("m is: " + m);
        System.out.println("expected is: " + expected);
        int differenceOfSums = differenceOfSums(n, m); // Calls your implementation
        System.out.println("differenceOfSums is: " + differenceOfSums);
    }

}
