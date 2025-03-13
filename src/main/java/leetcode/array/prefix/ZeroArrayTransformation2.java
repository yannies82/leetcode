package leetcode.array.prefix;

import java.util.Arrays;

public class ZeroArrayTransformation2 {

    public static void main(String[] args) {
        check(new int[]{2, 0, 2}, new int[][]{{0, 2, 1}, {0, 2, 1}, {1, 1, 3}}, 2);
        check(new int[]{4, 3, 2, 1}, new int[][]{{1, 3, 2}, {0, 2, 1}}, -1);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/zero-array-transformation-ii.
     * This solution uses range update technique with prefix sum in order to check how many
     * queries are needed for each number to be reduced to 0. Time complexity is O(n+k) where n is the
     * length of the nums array and k is the length of the queries array.
     *
     * @param nums
     * @param queries
     * @return
     */
    public static int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int prefixSum = 0;
        int k = 0;
        int[] range = new int[n + 1];  // Difference array for efficient range updates
        for (int i = 0; i < n; i++) {
            // Iterate through queries while the current num cannot be reduced to zero
            // prefixSum: Keeps track of the total effect of all previous updates (from range array).
            // range[i]: The new update that is being applied at this i.
            // prefixSum + range[i]: The effective value at i after applying all updates so far.
            while (prefixSum + range[i] < nums[i]) {
                // If we've used all queries and still can't make nums[i] zero, return -1
                if (k == queries.length) {
                    return -1;
                }

                // Extract left, right, and value from the next query
                int left = queries[k][0];
                int right = queries[k][1];
                int val = queries[k][2];

                // Apply range update using the difference array technique
                if (right >= i) {
                    //This ensures we only apply the update where it's relevant.
                    range[Math.max(left, i)] += val;  // Add `val` at the max of `left` and `i`

                    //We subtract val at right + 1 to stop the effect after right.
                    range[right + 1] -= val;  // Subtract `val` at `right + 1` to maintain range effect
                }
                k++;  // Increment query usage count
            }
            // Update prefix prefixSum at the current i
            prefixSum += range[i];
        }
        return k;  // Return the minimum number of queries required
    }

    /**
     * This solution uses binary search on the queries array to find the min number of queries which can provide a solution.
     * Time complexity is O((n+k) * logk) where n is the length of the nums array.
     *
     * @param nums
     * @param queries
     * @return
     */
    public static int minZeroArray2(int[] nums, int[][] queries) {
        int n = nums.length;
        // return 0 if all numbers are 0
        int numsOr = 0;
        for (int i = 0; i < nums.length; i++) {
            numsOr |= nums[i];
        }
        if (numsOr == 0) {
            return 0;
        }
        int left = 1;
        int right = queries.length;
        if (!canSolve(nums, queries, right)) {
            // it is not possible to have a solution when using all queries
            return -1;
        }
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (canSolve(nums, queries, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static boolean canSolve(int[] nums, int[][] queries, int k) {
        int n = nums.length;
        int[] range = new int[n + 1];
        for (int i = 0; i < k; i++) {
            range[queries[i][0]] += queries[i][2];
            range[queries[i][1] + 1] -= queries[i][2];
        }
        int current = 0;
        for (int i = 0; i < n; i++) {
            current += range[i];
            if (current < nums[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Naive solution which uses a nested loop to add the values to each number for each query.
     * Time complexity is O(n*m) where n is the number of queries and m is the average range of the queries.
     *
     * @param nums
     * @param queries
     * @return
     */
    public static int minZeroArray3(int[] nums, int[][] queries) {
        int[] reducedNums = new int[nums.length];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count++;
            }
        }
        if (count == nums.length) {
            return 0;
        }
        for (int i = 0; i < queries.length; i++) {
            for (int j = queries[i][0]; j <= queries[i][1]; j++) {
                if (reducedNums[j] < nums[j]) {
                    reducedNums[j] += queries[i][2];
                    if (reducedNums[j] >= nums[j]) {
                        count++;
                        if (count == nums.length) {
                            return i + 1;
                        }
                    }
                }
            }
        }
        return -1;
    }

    private static void check(int[] nums, int[][] queries, int expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("queries is: ");
        for (int[] query : queries) {
            System.out.println(Arrays.toString(query));
        }
        System.out.println("expected is: " + expected);
        int minZeroArray = minZeroArray(nums, queries); // Calls your implementation
        System.out.println("minZeroArray is: " + minZeroArray);
    }
}
