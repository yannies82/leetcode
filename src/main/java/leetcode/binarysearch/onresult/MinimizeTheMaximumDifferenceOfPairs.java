package leetcode.binarysearch.onresult;

import java.util.Arrays;

public class MinimizeTheMaximumDifferenceOfPairs {

    public static void main(String[] args) {
        check(new int[]{10, 1, 2, 7, 1, 3}, 2, 1);
        check(new int[]{4, 2, 1, 2}, 1, 0);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs.
     * Time complexity is O(nlogn) where n is the length of the nums array.
     *
     * @param nums
     * @param p
     * @return
     */
    public static int minimizeMax(int[] nums, int p) {
        if (p == 0) {
            return 0;
        }
        Arrays.sort(nums);
        // perform binary search on the range of possible results
        int n = nums.length;
        int left = 0;
        int right = nums[n - 1] - nums[0];
        while (left < right) {
            int mid = (left + right) >>> 1;
            // count the number of pairs with difference less than or equal to mid
            int pairs = 0;
            for (int i = 1; i < n; i++) {
                if (nums[i] - nums[i - 1] <= mid) {
                    pairs++;
                    // skip the next pair so that it does not include the same number
                    i++;
                }
            }
            if (pairs >= p) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static void check(int[] nums, int p, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("p is: " + p);
        System.out.println("expected is: " + expected);
        int minimizeMax = minimizeMax(nums, p); // Calls your implementation
        System.out.println("minimizeMax is: " + minimizeMax);
    }
}
