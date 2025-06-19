package leetcode.array;

import java.util.Arrays;

public class PartitionArraySuchThatMaximumDifferenceIsK {

    public static void main(String[] args) {
        check(new int[]{3, 6, 1, 2, 5}, 2, 2);
        check(new int[]{1, 2, 3}, 1, 2);
        check(new int[]{2, 2, 4, 5}, 0, 3);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/partition-array-such-that-maximum-difference-is-k.
     * Time complexity is O(nlogn) where n is the length of the nums array.
     *
     * @param nums
     * @param k
     * @return
     */
    public static int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int current = nums[0] + k;
        int result = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > current) {
                current = nums[i] + k;
                result++;
            }
        }
        return result;
    }

    private static void check(int[] nums, int k, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("k is: " + k);
        System.out.println("expected is: " + expected);
        int partitionArray = partitionArray(nums, k); // Calls your implementation
        System.out.println("partitionArray is: " + partitionArray);
    }
}
