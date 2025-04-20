package leetcode.array.frequency;

import java.util.Arrays;

public class RabbitsInForest {

    public static void main(String[] args) {
        check(new int[]{1, 1, 2}, 5);
        check(new int[]{10, 10, 10}, 11);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/rabbits-in-forest.
     * This solution uses the frequencies to calculate the result. Time complexity is O(n)
     * where n is the length of the answers array
     *
     * @param answers
     * @return
     */
    public static int numRabbits(int[] answers) {
        int[] count = new int[1000];
        int result = 0;
        for (int i = 0; i < answers.length; i++) {
            int limit = answers[i] + 1;
            if (++count[answers[i]] == 1) {
                result += limit;
            }
            if (count[answers[i]] == limit) {
                count[answers[i]] = 0;
            }
        }
        return result;
    }

    /**
     * This solution sorts the input array. Time complexity is O(nlogn) where n is the length
     * of the answers array.
     *
     * @param answers
     * @return
     */
    public static int numRabbits2(int[] answers) {
        Arrays.sort(answers);
        int result = 0;
        int prev = -1;
        int currentCount = 0;
        int limit = -1;
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == prev) {
                if (currentCount == limit) {
                    currentCount = 1;
                    result += limit;
                } else {
                    currentCount++;
                }
            } else {
                limit = answers[i] + 1;
                currentCount = 1;
                result += limit;
                prev = answers[i];
            }
        }
        return result;
    }

    private static void check(int[] answers, int expected) {
        System.out.println("answers is: " + Arrays.toString(answers));
        System.out.println("expected is: " + expected);
        int numRabbits = numRabbits(answers); // Calls your implementation
        System.out.println("numRabbits is: " + numRabbits);
    }
}
