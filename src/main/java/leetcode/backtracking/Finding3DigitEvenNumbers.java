package leetcode.backtracking;

import java.util.Arrays;

public class Finding3DigitEvenNumbers {

    public static void main(String[] args) {
        check(new int[]{2, 1, 3, 0}, new int[]{102, 120, 130, 132, 210, 230, 302, 310, 312, 320});
        check(new int[]{2, 2, 8, 8, 2}, new int[]{222, 228, 282, 288, 822, 828, 882});
        check(new int[]{3, 7, 5}, new int[]{});
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/finding-3-digit-even-numbers.
     * Time complexity is O(n) where n is the length of the digits array.
     *
     * @param digits
     * @return
     */
    public static int[] findEvenNumbers(int[] digits) {
        int[] frequency = new int[10];
        int index = 0;
        int[] result = new int[1000];
        for (int digit : digits) {
            frequency[digit]++;
        }
        for (int i = 1; i < 10; i++) {
            if (frequency[i] == 0) {
                continue;
            }
            frequency[i]--;
            int hundreds = i * 100;
            for (int j = 0; j < 10; j++) {
                if (frequency[j] == 0) {
                    continue;
                }
                frequency[j]--;
                int tens = j * 10;
                for (int k = 0; k < 10; k += 2) {
                    if (frequency[k] == 0) {
                        continue;
                    }
                    result[index++] = hundreds + tens + k;
                }
                frequency[j]++;
            }
            frequency[i]++;
        }
        int[] finalResult = new int[index];
        System.arraycopy(result, 0, finalResult, 0, index);
        return finalResult;
    }

    /**
     * Alternative solution. Time complexity is O(n^3) where n is the length of the digits array.
     *
     * @param digits
     * @return
     */
    public static int[] findEvenNumbers2(int[] digits) {
        int[] exists = new int[1000];
        int resultCount = 0;
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == 0) {
                continue;
            }
            int hundreds = digits[i] * 100;
            for (int j = 0; j < digits.length; j++) {
                if (j == i) {
                    continue;
                }
                int tens = digits[j] * 10;
                for (int k = 0; k < digits.length; k++) {
                    if (k == i || k == j || ((digits[k] & 1) == 1)) {
                        continue;
                    }
                    int number = hundreds + tens + digits[k];
                    resultCount += 1 - exists[number];
                    exists[number] = 1;
                }
            }
        }
        int[] result = new int[resultCount];
        int index = 0;
        for (int i = 100; i < exists.length; i++) {
            if (exists[i] == 1) {
                result[index++] = i;
            }
        }
        return result;
    }

    private static void check(int[] digits, int[] expected) {
        System.out.println("digits is: " + Arrays.toString(digits));
        System.out.println("expected is: " + Arrays.toString(expected));
        int[] findEvenNumbers = findEvenNumbers(digits); // Calls your implementation
        System.out.println("findEvenNumbers is: " + Arrays.toString(findEvenNumbers));
    }
}
