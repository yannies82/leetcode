package leetcode.array;

import java.util.Arrays;

public class MinimumDominoRotationsForEqualRow {

    public static void main(String[] args) {
        check(new int[]{2, 1, 2, 4, 2, 2}, new int[]{5, 2, 6, 2, 3, 2}, 2);
        check(new int[]{3, 5, 1, 2, 3}, new int[]{3, 6, 3, 3, 4}, -1);
        check(new int[]{1, 2, 3, 4, 6}, new int[]{6, 6, 6, 6, 5}, 1);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/minimum-domino-rotations-for-equal-row.
     * Time complexity is O(n) where n is the length of the tops array.
     *
     * @param tops
     * @param bottoms
     * @return
     */
    public static int minDominoRotations(int[] tops, int[] bottoms) {
        int result = minDominoRotationsSolve(tops, bottoms, tops[0]);
        if (result == -1) {
            result = minDominoRotationsSolve(tops, bottoms, bottoms[0]);
        }
        return result;
    }

    private static int minDominoRotationsSolve(int[] tops, int[] bottoms, int candidate) {
        int flipTopCount = 0;
        int flipBottomCount = 0;
        for (int i = 0; i < tops.length; i++) {
            if (tops[i] != candidate && bottoms[i] != candidate) {
                return -1;
            } else if (tops[i] != candidate) {
                flipTopCount++;
            } else if (bottoms[i] != candidate) {
                flipBottomCount++;
            }
        }
        return Math.min(flipTopCount, flipBottomCount);
    }

    public static int minDominoRotations2(int[] tops, int[] bottoms) {
        int candidate1 = tops[0];
        int candidate1CountTop = 0;
        int candidate1CountBottom = 0;
        int candidate2 = bottoms[0];
        int candidate2CountTop = 0;
        int candidate2CountBottom = 0;
        if (candidate1 == candidate2) {
            candidate2 = -1;
        }
        for (int i = 0; i < tops.length; i++) {
            if (candidate1 != -1) {
                boolean found1 = false;
                if (tops[i] == candidate1) {
                    found1 = true;
                    candidate1CountTop++;
                }
                if (bottoms[i] == candidate1) {
                    found1 = true;
                    candidate1CountBottom++;
                }
                if (!found1) {
                    candidate1 = -1;
                }
            }
            if (candidate2 != -1) {
                boolean found2 = false;
                if (tops[i] == candidate2) {
                    found2 = true;
                    candidate2CountTop++;
                }
                if (bottoms[i] == candidate2) {
                    found2 = true;
                    candidate2CountBottom++;
                }
                if (!found2) {
                    candidate2 = -1;
                }
            }
        }
        int result = -1;
        if (candidate1 != -1) {
            int duplicates = candidate1CountTop + candidate1CountBottom - tops.length;
            result = Math.min(candidate1CountTop, candidate1CountBottom) - duplicates;
        } else if (candidate2 != -1) {
            int duplicates = candidate2CountTop + candidate2CountBottom - tops.length;
            result = Math.min(candidate2CountTop, candidate2CountBottom) - duplicates;
        }
        return result;
    }

    private static void check(int[] tops, int[] bottoms, int expected) {
        System.out.println("tops is: " + Arrays.toString(tops));
        System.out.println("bottoms is: " + Arrays.toString(bottoms));
        System.out.println("expected is: " + expected);
        int minDominoRotations = minDominoRotations(tops, bottoms); // Calls your implementation
        System.out.println("minDominoRotations is: " + minDominoRotations);
    }
}
