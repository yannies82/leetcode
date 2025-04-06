package leetcode.dynamicprogramming;

import java.util.*;

public class LargestDivisibleSubset {

    public static void main(String[] args) {
        check(new int[]{1, 2, 3}, List.of(1, 2));
        check(new int[]{1, 2, 4, 8}, List.of(1, 2, 4, 8));
        check(new int[]{3, 4, 16, 8}, List.of(4, 8, 16));
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/largest-divisible-subset.
     * This solution sorts the input array and uses dynamic programming to calculate
     * the largest subset. Time complexity is O(n^2) where n is the length of the nums array.
     *
     * @param nums
     * @return
     */
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        // sort the input array
        Arrays.sort(nums);
        int maxI = 0;
        // keeps the size of the largest subset when it contains nums[i]
        int[] dpArray = new int[nums.length];
        // keeps the index of the previous element in the largest subset
        int[] prev = new int[nums.length];
        Arrays.fill(prev, -1);
        // for each element check if it is divisible with all elements with a lower value
        for (int i = 1; i < nums.length; i++) {
            // elements with greater value than nums[i]/2 cannot exactly divide nums[i]
            int mid = (nums[i] + 1) >>> 1;
            for (int j = 0; j < i && nums[j] <= mid; j++) {
                if (nums[i] % nums[j] == 0 && dpArray[j] + 1 > dpArray[i]) {
                    // nums[i] is divisible by nums[j] and this subset will contain more numbers
                    // than other subsets which contain nums[i]
                    dpArray[i] = dpArray[j] + 1;
                    prev[i] = j;
                    if (dpArray[i] > dpArray[maxI]) {
                        // update last index of largest subset
                        maxI = i;
                    }
                }
            }
        }
        // use maxI and the prev array to construct the result list from last element to first
        LinkedList<Integer> result = new LinkedList<>();
        do {
            result.offerFirst(nums[maxI]);
            maxI = prev[maxI];
        } while (maxI != -1);
        return result;
    }

    private static void check(int[] nums, List<Integer> expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("expected is: " + expected);
        List<Integer> largestDivisibleSubset = largestDivisibleSubset(nums); // Calls your implementation
        System.out.println("largestDivisibleSubset is: " + largestDivisibleSubset);
    }
}
