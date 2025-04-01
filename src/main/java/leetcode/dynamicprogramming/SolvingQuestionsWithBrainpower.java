package leetcode.dynamicprogramming;

import java.util.Arrays;

public class SolvingQuestionsWithBrainpower {

    public static void main(String[] args) {
        check(new int[][]{{3, 2}, {4, 3}, {4, 4}, {2, 5}}, 5);
        check(new int[][]{{1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}}, 7);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/solving-questions-with-brainpower.
     * This solution uses bottom up dynamic programming. It calculates the solutions to all
     * subproblems if the questions started from index i. Time complexity is O(n) where n
     * is the length of the questions array.
     *
     * @param questions
     * @return
     */
    public static long mostPoints(int[][] questions) {
        // keeps solution for each subproblem if starting from question i
        long[] dpArray = new long[questions.length + 1];
        int lastIndex = questions.length - 1;
        dpArray[lastIndex] = questions[lastIndex][0];
        for (int i = questions.length - 2; i >= 0; i--) {
            int index = Math.min(i + questions[i][1] + 1, questions.length);
            // calculate the max points between selecting the current question and not selecting it
            dpArray[i] = Math.max(dpArray[i + 1], questions[i][0] + dpArray[index]);
        }
        // solution to our problem is the one starting from question 0
        return dpArray[0];
    }

    /**
     * This solution uses top down dynamic programming. Time complexity is O(n) where n
     * is the length of the questions array.
     *
     * @param questions
     * @return
     */
    public static long mostPoints2(int[][] questions) {
        // keeps solution for each subproblem if starting from question i
        long[] dpArray = new long[questions.length + 1];
        int lastIndex = questions.length - 1;
        dpArray[lastIndex] = questions[lastIndex][0];
        // solution to our problem is the one starting from question 0
        return dp(questions, dpArray, 0);
    }

    private static long dp(int[][] questions, long[] dpArray, int i) {
        if (dpArray[i] > 0 || i == questions.length) {
            return dpArray[i];
        }
        int index = Math.min(i + questions[i][1] + 1, questions.length);
        dpArray[i] = Math.max(dp(questions, dpArray, i + 1), questions[i][0] + dp(questions, dpArray, index));
        return dpArray[i];
    }

    private static void check(int[][] questions, long expected) {
        System.out.println("questions is: ");
        for (int[] question : questions) {
            System.out.println(Arrays.toString(question));
        }
        System.out.println("expected is: " + expected);
        long mostPointsBottomUp = mostPoints(questions); // Calls your implementation
        System.out.println("mostPoints (bottom up) is: " + mostPointsBottomUp);
        long mostPointsTopDown = mostPoints2(questions); // Calls your implementation
        System.out.println("mostPoints (top down) is: " + mostPointsTopDown);
    }

}
