package leetcode.matrix;

import java.util.List;

public class TotalCharactersInStringAfterTransformations2 {

    public static void main(String[] args) {
        check("abcyy", 2, List.of(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2), 7);
        check("azbk", 1, List.of(2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2), 8);
    }

    private static final int MOD = 1000000007;

    /**
     * Leetcode problem: https://leetcode.com/problems/total-characters-in-string-after-transformations-ii.
     * This solution uses a transformation matrix and matrix multiplication to calculate the result.
     * Time complexity is O(n+logt) where n is the length of string s.
     *
     * @param s
     * @param t
     * @param nums
     * @return
     */
    public static int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        // initialize transformation matrix
        long[][] transform = new long[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 1; j <= nums.get(i); j++) {
                transform[i][(i + j) % 26]++;
            }
        }
        // multiply transformation matrix with itself t times
        transform = powerMatrix(transform, t);
        // calculate frequency for each character in the string
        long[][] frequency = new long[1][26];
        for (char c : s.toCharArray()) {
            frequency[0][c - 'a']++;
        }
        // multiply frequency with the transformation matrix
        frequency = multiplyMatrices(frequency, transform);
        // count total characters after t transformations
        long result = 0;
        for (long count : frequency[0]) {
            result = (result + count) % MOD;
        }
        return (int) result;
    }

    private static long[][] multiplyMatrices(long[][] A, long[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int colsB = B[0].length;
        long[][] result = new long[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                long sum = 0;
                for (int k = 0; k < colsA; k++) {
                    sum = (sum + (A[i][k] * B[k][j]) % MOD) % MOD;
                }
                result[i][j] = sum;
            }
        }
        return result;
    }

    private static long[][] powerMatrix(long[][] matrix, long exponent) {
        int n = matrix.length;
        long[][] result = new long[n][n];
        for (int i = 0; i < n; i++) result[i][i] = 1;
        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = multiplyMatrices(result, matrix);
            }
            matrix = multiplyMatrices(matrix, matrix);
            exponent >>= 1;
        }
        return result;
    }

    /**
     * Simple solution which calculates frequencies after each step of the transformation.
     *
     * @param s
     * @param t
     * @param nums
     * @return
     */
    public static int lengthAfterTransformations2(String s, int t, List<Integer> nums) {
        int[] offsets = new int[26];
        for (int i = 0; i < offsets.length; i++) {
            offsets[i] = nums.get(i);
        }
        int[] frequency = new int[26];
        for (char c : s.toCharArray()) {
            frequency[c - 'a']++;
        }
        for (int i = 0; i < t; i++) {
            int[] newFrequency = new int[26];
            for (int j = 0; j < offsets.length; j++) {
                long currentFrequency = frequency[j];
                for (int k = 1; k <= offsets[j]; k++) {
                    int newIndex = (j + k) % 26;
                    newFrequency[newIndex] = (int) ((newFrequency[newIndex] + currentFrequency) % MOD);
                }
            }
            frequency = newFrequency;
        }
        long result = 0;
        for (int freq : frequency) {
            result = (result + freq) % MOD;
        }
        return (int) result;
    }

    private static void check(String s, int t, List<Integer> nums, int expected) {
        System.out.println("s is: " + s);
        System.out.println("t is: " + t);
        System.out.println("nums is: " + nums);
        System.out.println("expected is: " + expected);
        int lengthAfterTransformations = lengthAfterTransformations(s, t, nums); // Calls your implementation
        System.out.println("lengthAfterTransformations is: " + lengthAfterTransformations);
    }
}
