package leetcode.intervals;

import java.util.Arrays;

public class CheckIfGridCanBeCutIntoSections {

    public static void main(String[] args) {
        check(5, new int[][]{{1, 0, 5, 2}, {0, 2, 2, 4}, {3, 2, 5, 3}, {0, 4, 4, 5}}, true);
        check(4, new int[][]{{0, 0, 1, 1}, {2, 0, 3, 4}, {0, 2, 2, 3}, {3, 0, 4, 3}}, true);
        check(4, new int[][]{{0, 2, 2, 4}, {1, 0, 3, 2}, {2, 2, 3, 4}, {3, 0, 4, 2}, {3, 2, 4, 4}}, false);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/check-if-grid-can-be-cut-into-sections.
     * This solution sorts the rectangles first by x coordinates, then by y coordinates and
     * greedily checks if the start of the next rectangle does not overlap with the end of the previous
     * ones, so that a cut can be applied. Time complexity is O(nlogn) where n is the length
     * of the rectangles array.
     *
     * @param n
     * @param rectangles
     * @return
     */
    public static boolean checkValidCuts(int n, int[][] rectangles) {
        return checkValidCuts(n, rectangles, 1, 3) || checkValidCuts(n, rectangles, 0, 2);
    }

    private static boolean checkValidCuts(int n, int[][] rectangles, int indexStart, int indexEnd) {
        Arrays.sort(rectangles, (a, b) -> a[indexStart] - b[indexStart]);
        int numberOfCuts = 0;
        int i = 0;
        while (i < rectangles.length && numberOfCuts < 3) {
            int end = rectangles[i][indexEnd];
            while (++i < rectangles.length && rectangles[i][indexStart] < end) {
                end = Math.max(end, rectangles[i][indexEnd]);
            }
            numberOfCuts++;
        }
        return numberOfCuts == 3;
    }

    private static void check(int n, int[][] rectangles, boolean expected) {
        System.out.println("n is: " + n);
        System.out.println("rectangles is: ");
        for (int[] rectangle : rectangles) {
            System.out.println(Arrays.toString(rectangle));
        }
        System.out.println("expected is: " + expected);
        boolean checkValidCuts = checkValidCuts(n, rectangles); // Calls your implementation
        System.out.println("checkValidCuts is: " + checkValidCuts);
    }
}
