package leetcode.math;

public class CountSymmetricIntegers {

    public static void main(String[] args) {
        check(1, 100, 9);
        check(1200, 1230, 4);
        check(100, 10000, 615);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/count-symmetric-integers.
     * Time complexity is O(n) where n is the range between low and high.
     *
     * @param low
     * @param high
     * @return
     */
    public static int countSymmetricIntegers(int low, int high) {
        int result = 0;
        if (low < 100) {
            int offsetHigh = Math.min(high, 99);
            result += offsetHigh / 11 - (low - 1) / 11;
        }
        low = Math.max(low, 1001);
        high = Math.min(high, 9999);
        for (int i = low; i <= high; i++) {
            int num = i;
            int firstSum = num % 10 + (num /= 10) % 10;
            int secondSum = (num /= 10) % 10 + (num / 10) % 10;
            if (firstSum == secondSum) {
                result++;
            }
        }
        return result;
    }

    private static void check(int low, int high, int expected) {
        System.out.println("low is: " + low);
        System.out.println("high is: " + high);
        System.out.println("expected is: " + expected);
        int countSymmetricIntegers = countSymmetricIntegers(low, high); // Calls your implementation
        System.out.println("countSymmetricIntegers is: " + countSymmetricIntegers);
    }
}
