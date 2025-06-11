package leetcode.string.prefix;

import java.util.Arrays;

public class MaximumDifferenceBetweenEvenAndOddFrequency2 {

    public static void main(String[] args) {
        check("12233", 4, -1);
        check("1122211", 3, 1);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/maximum-difference-between-even-and-odd-frequency-ii.
     * This solution uses prefix sums. Time complexity is O(n) where n is the length of string s.
     *
     * @param s
     * @param k
     * @return
     */
    public static int maxDifference(String s, int k) {
        int n = s.length();
        // Initialize answer to a very small number.
        int result = Integer.MIN_VALUE;

        // Step 1: Iterate through all possible pairs of distinct characters (a, b).
        for (char a = '0'; a <= '4'; a++) {
            for (char b = '0'; b <= '4'; b++) {
                if (a == b) {
                    continue;
                }
                // --- Start of logic for a single (a, b) pair ---

                // best[status] stores the minimum (prevA - prevB) for a prefix
                // with the parity state 'status'. Initialize with a large value.
                int[] best = new int[4];
                Arrays.fill(best, Integer.MAX_VALUE);

                // countA, countB: Prefix counts for the 'right' pointer (s[0...right]).
                int countA = 0, countB = 0;
                // prevA, prevB: Prefix counts for the 'left' pointer (s[0...left]).
                int prevA = 0, prevB = 0;
                // 'left' tracks the end of the prefix we are recording in the 'best' array.
                int left = -1;

                // The main loop iterating through the string with the 'right' pointer.
                for (int right = 0; right < n; ++right) {
                    // Update prefix counts for the current 'right' position.
                    char rightChar = s.charAt(right);
                    if (rightChar == a) {
                        countA++;
                    } else if (rightChar == b) {
                        countB++;
                    }
                    // This loop updates the 'best' array. It advances the 'left' pointer
                    // and records the state of the prefix ending at 'left'.
                    // The conditions ensure that any substring starting at 'left + 1'
                    // will have a length of at least 'k'.
                    // Also, `countB - prevB >= 2` since b appears an even number of times in the substring,
                    // but zero occurrences must be excluded (and 1 must also be excluded since it's odd, obviously)
                    while (right - left >= k && countB - prevB >= 2) {
                        // Get the parity state for the prefix ending at 'left'.
                        int leftStatus = getStatus(prevA, prevB);
                        // Update the 'best' array with the minimum value of (prevA - prevB)
                        // for this specific state.
                        best[leftStatus] = Math.min(best[leftStatus], prevA - prevB);
                        // Advance the left pointer and its corresponding prefix counts.
                        char leftChar = s.charAt(++left);
                        if (leftChar == a) {
                            prevA++;
                        } else if (leftChar == b) {
                            prevB++;
                        }
                    }

                    // Now, calculate the potential answer for the current 'right' pointer.
                    // 1. Get the parity state for the prefix ending at 'right'.
                    int rightStatus = getStatus(countA, countB);
                    // 2. Determine the required state for the start-prefix.
                    // We need leftStatus = rightStatus XOR 2
                    int requiredStatus = rightStatus ^ 2;
                    // 3. If we have seen a valid starting prefix with the required state...
                    if (best[requiredStatus] != Integer.MAX_VALUE) {
                        // Calculate the difference: (countA-countB) - min(prevA-prevB).
                        // This maximizes the expression.
                        result = Math.max(result, countA - countB - best[requiredStatus]);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Helper function to calculate the 2-bit parity state.
     * Bit 1: parity of countA. Bit 0: parity of countB.
     */
    private static int getStatus(int countA, int countB) {
        // (countA & 1) is 1 if countA is odd, 0 if even.
        // << 1 shifts it to the second bit position.
        // | (countB & 1) puts the parity of countB in the first bit position.
        return ((countA & 1) << 1) | (countB & 1);
    }

    private static void check(String s, int k, int expected) {
        System.out.println("s is: " + s);
        System.out.println("k is: " + k);
        System.out.println("expected is: " + expected);
        int maxDifference = maxDifference(s, k); // Calls your implementation
        System.out.println("maxDifference is: " + maxDifference);
    }
}
