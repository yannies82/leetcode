package leetcode.math;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {

    public static void main(String[] args) {
        check(5, List.of(List.of(1), List.of(1, 1), List.of(1, 2, 1), List.of(1, 3, 3, 1), List.of(1, 4, 6, 4, 1)));
        check(1, List.of(List.of(1)));
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/pascals-triangle.
     * Time complexity is O(n^2) where n is the number of rows.
     *
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> prevList = List.of(1);
        result.add(prevList);
        for (int i = 1; i < numRows; i++) {
            List<Integer> currentList = new ArrayList<>();
            currentList.add(1);
            Integer prev = 1;
            for (int j = 1; j < i; j++) {
                Integer current = prevList.get(j);
                currentList.add(current + prev);
                prev = current;
            }
            currentList.add(1);
            result.add(currentList);
            prevList = currentList;
        }
        return result;
    }

    private static void check(int numRows, List<List<Integer>> expected) {
        System.out.println("numRows is: " + numRows);
        System.out.println("expected is: " + expected);
        List<List<Integer>> generate = generate(numRows); // Calls your implementation
        System.out.println("generate is: " + generate);
    }
}
