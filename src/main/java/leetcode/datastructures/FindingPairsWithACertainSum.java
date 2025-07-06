package leetcode.datastructures;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindingPairsWithACertainSum {

    public static void main(String[] args) {
        check();
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/finding-pairs-with-a-certain-sum.
     * Time complexity is O(n) for construction, O(1) for add and O(m) for count, where
     * m is length of nums1 and n is length of nums2.
     */
    public static class FindSumPairs {

        int[] nums1;
        int[] nums2;
        Map<Integer, Integer> countNums2 = new HashMap<>();

        public FindSumPairs(int[] nums1, int[] nums2) {
            this.nums1 = nums1;
            Arrays.sort(nums1);
            this.nums2 = nums2;
            for (int num : nums2) {
                countNums2.put(num, countNums2.getOrDefault(num, 0) + 1);
            }
        }

        public void add(int index, int val) {
            countNums2.put(nums2[index], countNums2.get(nums2[index]) - 1);
            nums2[index] += val;
            countNums2.put(nums2[index], countNums2.getOrDefault(nums2[index], 0) + 1);
        }

        public int count(int tot) {
            int sum = 0;
            for (int i = 0; i < nums1.length && nums1[i] < tot; i++) {
                sum += countNums2.getOrDefault(tot - nums1[i], 0);
            }
            return sum;
        }
    }

    private static void check() {
        FindSumPairs findSumPairs = new FindSumPairs(new int[]{1, 1, 2, 2, 2, 3}, new int[]{1, 4, 5, 2, 5, 4});
        printValue(8, findSumPairs.count(7));
        findSumPairs.add(3, 2);
        printValue(2, findSumPairs.count(8));
        printValue(1, findSumPairs.count(4));
        findSumPairs.add(0, 1);
        findSumPairs.add(1, 1);
        printValue(11, findSumPairs.count(7));
    }

    private static void printValue(int expected, int actual) {
        System.out.println("Expected value is: " + expected + ", actual value is: " + actual);
    }

}
