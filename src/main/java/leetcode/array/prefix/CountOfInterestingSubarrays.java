package leetcode.array.prefix;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountOfInterestingSubarrays {

    public static void main(String[] args) {
        check(List.of(3, 2, 4), 2, 1, 3);
        check(List.of(3, 1, 9, 6), 3, 0, 2);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/count-of-interesting-subarrays.
     * This solution uses prefix sums.
     * Time complexity is O(n) where n is the number of elements in the nums list.
     *
     * @param nums
     * @param modulo
     * @param k
     * @return
     */
    public static long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        if (k > n) {
            return 0;
        }
        int[] count = new int[n + 1];
        count[0] = 1;
        long result = 0;
        int prefix = 0;
        for (int num : nums) {
            num %= modulo;
            if (num == k) {
                prefix++;
            }
            prefix %= modulo;
            int r = prefix - k;
            if (r < 0) {
                r += modulo;
            }
            if (r < n) {
                result += count[r];
            }
            count[prefix]++;
        }
        return result;
    }

    public static long countInterestingSubarrays2(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        Map<Integer, Integer> countMap = new HashMap<>();
        long result = 0;
        int prefix = 0;
        countMap.put(0, 1);
        for (int i = 0; i < n; i++) {
            prefix += nums.get(i) % modulo == k ? 1 : 0;
            result += countMap.getOrDefault((prefix - k + modulo) % modulo, 0);
            countMap.put(prefix % modulo, countMap.getOrDefault(prefix % modulo, 0) + 1);
        }
        return result;
    }

    private static void check(List<Integer> nums, int modulo, int k, int expected) {
        System.out.println("nums is: " + nums);
        System.out.println("modulo is: " + modulo);
        System.out.println("k is: " + k);
        System.out.println("expected is: " + expected);
        long countInterestingSubarrays = countInterestingSubarrays(nums, modulo, k); // Calls your implementation
        System.out.println("countInterestingSubarrays is: " + countInterestingSubarrays);
    }
}
