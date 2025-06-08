package leetcode.backtracking;

import java.util.*;

public class LexicographicalNumbers {

    public static void main(String[] args) {
        check(13, List.of(1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9));
        check(2, List.of(1, 2));
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/lexicographical-numbers.
     * This solution uses backtracking to try all combinations of digits and keep
     * the ones that are not greater than n. Time complexity is O(n).
     *
     * @param n
     * @return
     */
    public static List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            addNumbers(result, i, n);
        }
        return result;
    }

    private static void addNumbers(List<Integer> result, int i, int n) {
        if (i > n) {
            return;
        }
        result.add(i);
        int nextI = i * 10;
        for (int j = 0; j <= 9; j++) {
            int next = nextI + j;
            if (next > n) {
                return;
            }
            addNumbers(result, next, n);
        }
    }

    private static void check(int n, List<Integer> expected) {
        System.out.println("n is: " + n);
        System.out.println("expected is: " + expected);
        List<Integer> lexicalOrder = lexicalOrder(n); // Calls your implementation
        System.out.println("lexicalOrder is: " + lexicalOrder);
    }
}
