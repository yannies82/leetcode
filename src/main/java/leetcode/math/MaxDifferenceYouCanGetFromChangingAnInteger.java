package leetcode.math;

public class MaxDifferenceYouCanGetFromChangingAnInteger {

    public static void main(String[] args) {
        check(555, 888);
        check(9, 8);
        check(111, 888);
        check(123456, 820000);
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
        // find all digits and the length of the number
        int currentNum = num;
        do {
            digits[length++] = currentNum % 10;
            currentNum /= 10;
        } while (currentNum > 0);
        // find the digit which should be replaced to get the lowest (and the digit that will replace it)
        int higherOrderDigit = digits[length - 1];
        int higherNon0Digit = higherOrderDigit;
        // highest order digit will be replaced by 1
        int numForLowest = 1;
        int index = length - 1;
        if (higherNon0Digit == 1 && length > 1) {
            // highest order digit is 1 and can't be replaced
            // next digits will be replaced by 0, but we can't replace a digit which is
            // equal to the higherOrderDigit
            numForLowest = 0;
            higherNon0Digit = digits[--index];
            while ((higherNon0Digit == 0 || higherNon0Digit == higherOrderDigit) && --index >= 0) {
                higherNon0Digit = digits[index];
            }
        }
        // find the digit which should be replaced to get the highest
        int higherNon9Digit = higherOrderDigit;
        index = length - 1;
        while (higherNon9Digit == 9 && --index >= 0) {
            higherNon9Digit = digits[index];
        }
        int lowest = 0;
        int highest = 0;
        int factor = 1;
        // build lowest and highest
        for (int i = 0; i < length; i++) {
            int current = factor * digits[i];
            if (digits[i] == higherNon0Digit) {
                lowest += factor * numForLowest;
            } else {
                lowest += current;
            }
            if (digits[i] == higherNon9Digit) {
                highest += factor * 9;
            } else {
                highest += current;
            }
            factor *= 10;
        }
        // if lowest is 0 then replace it with num (because no substitutions can be made)
        if (lowest == 0) {
            lowest = num;
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
