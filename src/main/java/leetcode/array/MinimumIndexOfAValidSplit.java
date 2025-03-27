package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimumIndexOfAValidSplit {

    public static void main(String[] args) {
        check(List.of(1, 2, 2, 2), 2);
        check(List.of(2, 1, 3, 1, 1, 1, 7, 1, 2, 1), 4);
        check(List.of(3, 3, 3, 3, 7, 2, 2), -1);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/minimum-index-of-a-valid-split. This
     * solution uses the Moore Voting algorithm to find the majority element. Time
     * complexity is O(n) where n is the length of the nums array.
     *
     * @param nums
     * @return
     */
    public static int minimumIndex(List<Integer> nums) {
        int majorityElement = findMajorityElement(nums);
        int majorityElementCount = 0;
        for (int num : nums) {
            if (num == majorityElement) {
                majorityElementCount++;
            }
        }
        int n = nums.size();
        int currentCount = 0;
        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            if (num == majorityElement) {
                currentCount++;
            }
            int currentLength = i + 1;
            // check that the majority element is prevalent in both subarrays
            if (((currentCount << 1) > currentLength) && (((majorityElementCount - currentCount) << 1) > (n - currentLength))) {
                return i;
            }
        }
        return -1;
    }

    private static int findMajorityElement(List<Integer> nums) {
        int majorityElement = -1;
        int count = 1;
        for (int num : nums) {
            if (num == majorityElement) {
                count++;
            } else if (count == 1) {
                majorityElement = num;
            } else {
                count--;
            }
        }
        return majorityElement;
    }

    private static void check(List<Integer> nums, int expected) {
        System.out.println("nums is: " + nums);
        System.out.println("expected is: " + expected);
        int minimumIndex = minimumIndex(nums); // Calls your implementation
        System.out.println("minimumIndex is: " + minimumIndex);
    }
}
