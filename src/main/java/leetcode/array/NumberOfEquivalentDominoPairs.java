package leetcode.array;

import java.util.Arrays;

public class NumberOfEquivalentDominoPairs {

    public static void main(String[] args) {
        check(new int[][]{{1, 2}, {2, 1}, {3, 4}, {5, 6}}, 1);
        check(new int[][]{{1, 2}, {1, 2}, {1, 1}, {1, 2}, {2, 2}}, 3);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/number-of-equivalent-domino-pairs.
     * Time complexity is O(n) where n is the length of the dominoes array.
     *
     * @param dominoes
     * @return
     */
    public static int numEquivDominoPairs(int[][] dominoes) {
        int[][] existingPairs = new int[10][10];
        for (int i = 0; i < dominoes.length; i++) {
            existingPairs[dominoes[i][0]][dominoes[i][1]]++;
        }
        int result = 0;
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <= i; j++) {
                int current = existingPairs[i][j];
                if (i != j) {
                    current += existingPairs[j][i];
                }
                result += (current * (current - 1)) >>> 1;
            }
        }
        return result;
    }

    /**
     * Similar solution, branchless. Time complexity is O(n) where n is the length of the dominoes array.
     *
     * @param dominoes
     * @return
     */
    public static int numEquivDominoPairs2(int[][] dominoes) {
        int[][] existingPairs = new int[10][10];
        for (int i = 0; i < dominoes.length; i++) {
            existingPairs[dominoes[i][0]][dominoes[i][1]]++;
        }
        int result = 0;
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <= i; j++) {
                int current = existingPairs[i][j] + (((-(i ^ j)) >> 31) & existingPairs[j][i]);
                result += (current * (current - 1)) >>> 1;
            }
        }
        return result;
    }

    private static void check(int[][] dominoes, int expected) {
        System.out.println("dominoes is: ");
        for (int[] domino : dominoes) {
            System.out.println(Arrays.toString(domino));
        }
        System.out.println("expected is: " + expected);
        int numEquivDominoPairs = numEquivDominoPairs(dominoes); // Calls your implementation
        System.out.println("numEquivDominoPairs is: " + numEquivDominoPairs);
    }
}
