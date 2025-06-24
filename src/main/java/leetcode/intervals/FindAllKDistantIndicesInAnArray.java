package leetcode.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllKDistantIndicesInAnArray {

    public static void main(String[] args) {
        check(new int[]{3, 4, 9, 1, 3, 9, 5}, 9, 1, List.of(1, 2, 3, 4, 5, 6));
        check(new int[]{2, 2, 2, 2, 2}, 2, 2, List.of(0, 1, 2, 3, 4));
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/find-all-k-distant-indices-in-an-array.
     * Time complexity is O(n) where n is the length of the nums array.
     *
     * @param nums
     * @param key
     * @param k
     * @return
     */
    public static List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> result = new ArrayList<>();
        int lastIndex = nums.length - 1;
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == key) {
                start = Math.max(i - k, start);
                int end = Math.min(i + k, lastIndex);
                for (int j = start; j <= end; j++) {
                    result.add(j);
                }
                start = end + 1;
            }
        }
        return result;
    }

    /**
     * Alternative solution which uses a range array. Time complexity is O(n) where n is the length of the nums array.
     *
     * @param nums
     * @param key
     * @param k
     * @return
     */
    public static List<Integer> findKDistantIndices2(int[] nums, int key, int k) {
        List<Integer> result = new ArrayList<>();
        int[] range = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == key) {
                int start = Math.max(i - k, 0);
                int end = Math.min(i + k + 1, nums.length);
                range[start]++;
                range[end]--;
            }
        }
        int current = 0;
        for (int i = 0; i < nums.length; i++) {
            current += range[i];
            if (current > 0) {
                result.add(i);
            }
        }
        return result;
    }

    private static void check(int[] nums, int key, int k, List<Integer> expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("key is: " + key);
        System.out.println("k is: " + k);
        System.out.println("expected is: " + expected);
        List<Integer> findKDistantIndices = findKDistantIndices(nums, key, k); // Calls your implementation
        System.out.println("findKDistantIndices is: " + findKDistantIndices);
    }
}
