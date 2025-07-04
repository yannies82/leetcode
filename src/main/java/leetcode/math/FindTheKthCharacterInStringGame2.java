package leetcode.math;

import java.util.Arrays;

public class FindTheKthCharacterInStringGame2 {

    public static void main(String[] args) {
        check(5, new int[]{0, 0, 0}, 'a');
        check(10, new int[]{0, 1, 0, 1}, 'b');
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/find-the-k-th-character-in-string-game-ii.
     * Time complexity is O(logk).
     *
     * @param k
     * @param operations
     * @return
     */
    public static char kthCharacter(long k, int[] operations) {
        int changeCount = 0;
        int index = 0;
        long length = 1;
        // calculate the length of the final string which includes index k and the last relevant index in the operations array
        while (length < k) {
            length <<= 1;
            index++;
        }
        // iterate all operations from last to first
        long currentLength = length;
        for (int i = index - 1; i >= 0; i--) {
            int operation = operations[i];
            long half = currentLength >> 1;
            if (k > half) {
                k -= half;
                if (operation == 1) {
                    changeCount++;
                }
            }
            // else: k remains the same
            currentLength = half;
        }
        // Apply total changeCount from 'a'
        return (char) ('a' + (changeCount % 26));
    }

    public static char kthCharacter(int k) {
        int result = 0;
        while (k > 1) {
            int logKCeil = 31 - Integer.numberOfLeadingZeros(k);
            int endIndex = 1 << logKCeil;
            if ((endIndex) == k) {
                endIndex >>>= 1;
            }
            k = k - endIndex;
            result++;
        }
        return (char) ('a' + result);
    }

    private static void check(long k, int[] operations, char expected) {
        System.out.println("k is: " + k);
        System.out.println("operations is: " + Arrays.toString(operations));
        System.out.println("expected is: " + expected);
        char kthCharacter = kthCharacter(k, operations); // Calls your implementation
        System.out.println("kthCharacter is: " + kthCharacter);
    }
}
