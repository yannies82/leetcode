package leetcode.binarysearch;

import java.util.Arrays;

public class MaximumCandiesAllocatedToKChildren {

    public static void main(String[] args) {
        check(new int[]{1, 2, 3, 4, 10}, 5, 3);
        check(new int[]{5, 8, 6}, 3, 5);
        check(new int[]{2, 5}, 11, 0);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/maximum-candies-allocated-to-k-children.
     * This solution uses binary search to check if x candies per kid can be distributed
     * in order to find the max number of candies per kid. Time complexity is O(n*log(m/k))
     * where n is the length of the candies array and m is the total number of candies.
     *
     * @param candies
     * @param k
     * @return
     */
    public static int maximumCandies(int[] candies, long k) {
        // find the total number of candies
        long sumCandies = candies[0];
        for (int i = 1; i < candies.length; i++) {
            sumCandies += candies[i];
        }
        if (sumCandies < k) {
            // candies are less than the kids, they cannot be distributed
            return 0;
        }
        int start = 1;
        // max candies per kid are (total candies / number of kids)
        int end = (int) (sumCandies / k + 1);
        int result = 0;
        // perform binary search to find the greatest number of candies per kid which can be distributed
        while (start < end) {
            int mid = (start + end) >>> 1;
            if (canDistributeCandies(candies, k, mid)) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return result;
    }

    private static boolean canDistributeCandies(int[] candies, long kids, int perKid) {
        long kidsWithCandies = 0;
        for (int i = 0; i < candies.length && kidsWithCandies < kids; i++) {
            kidsWithCandies += candies[i] / perKid;
        }
        return kidsWithCandies >= kids;
    }

    private static void check(int[] candies, long k, int expected) {
        System.out.println("candies is: " + Arrays.toString(candies));
        System.out.println("k is: " + k);
        System.out.println("expected is: " + expected);
        int maximumCandies = maximumCandies(candies, k); // Calls your implementation
        System.out.println("maximumCandies is: " + maximumCandies);
    }
}
