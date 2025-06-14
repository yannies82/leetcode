package leetcode.math;

public class MaximumDifferenceByRemappingADigit {

    public static void main(String[] args) {
        check(11891, 99009);
        check(90, 99);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/maximum-difference-by-remapping-a-digit.
     * Time complexity is O(n) where n is the number of digits in num.
     *
     * @param num
     * @return
     */
    public static int minMaxDifference(int num) {
        int[] digits = new int[10];
        int length = 0;
        do {
            digits[length++] = num % 10;
            num /= 10;
        } while (num > 0);
        int higherOrderDigit = digits[length - 1];
        int higherNon9Digit = higherOrderDigit;
        int index = length - 1;
        while (higherNon9Digit == 9 && --index >= 0) {
            higherNon9Digit = digits[index];
        }
        int lowest = 0;
        int highest = 0;
        int factor = 1;
        for (int i = 0; i < length; i++) {
            int current = factor * digits[i];
            if (digits[i] != higherOrderDigit) {
                lowest += current;
            }
            if (digits[i] == higherNon9Digit) {
                highest += factor * 9;
            } else {
                highest += current;
            }
            factor *= 10;
        }
        return highest - lowest;
    }

    private static void check(int num, int expected) {
        System.out.println("num is: " + num);
        System.out.println("expected is: " + expected);
        int minMaxDifference = minMaxDifference(num); // Calls your implementation
        System.out.println("minMaxDifference is: " + minMaxDifference);
    }
}
