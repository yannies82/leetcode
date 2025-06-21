package leetcode.slidingwindow;

import java.util.Arrays;

public class MinimumDeletionsToMakeStringKSpecial {

    public static void main(String[] args) {
        check("aabcaba", 0, 3);
        check("dabdcbdcdcd", 2, 2);
        check("aaabaaa", 2, 1);
        check("itatwtiwwi", 1, 1);
        check("vvnowvov", 2, 1);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/minimum-deletions-to-make-string-k-special.
     * Time complexity is O(n) where n is the length of string word.
     *
     * @param word
     * @param k
     * @return
     */
    public static int minimumDeletions(String word, int k) {
        int[] frequency = new int[26];
        int wordLength = word.length();
        for (int i = 0; i < wordLength; i++) {
            frequency[word.charAt(i) - 'a']++;
        }
        Arrays.sort(frequency);
        int firstNonZeroFrequencyIndex = 0;
        while (frequency[firstNonZeroFrequencyIndex] == 0) {
            firstNonZeroFrequencyIndex++;
        }
        int result = Integer.MAX_VALUE;
        int leftSum = 0;
        int currentSum = 0;
        int right = firstNonZeroFrequencyIndex;

        // 4. Slide the window start i across all frequencies
        for (int left = firstNonZeroFrequencyIndex; left < frequency.length; left++) {
            int frequencyFrom = frequency[left];
            int frequencyTo = frequencyFrom + k;

            // Expand right pointer while freq ≤ to
            while (right < frequency.length && frequency[right] <= frequencyTo) {
                currentSum += frequency[right++];
            }

            // Elements to the right of window: [j..size-1]
            int rightCount = frequency.length - right;
            int rightSum = wordLength - leftSum - currentSum;
            // If we want them all to be at most `to`, we must delete:
            // rightSum - (rightCount * to)
            int deletionsRight = rightSum - rightCount * frequencyTo;

            // Total deletions = left deletions + right deletions
            int totalDel = leftSum + deletionsRight;
            result = Math.min(result, totalDel);

            // Move window start forward by “deleting” updated[left] frequencyFrom window/total
            leftSum += frequency[left];
            currentSum -= frequency[left];
        }
        return result;
    }

    private static void check(String word, int k, int expected) {
        System.out.println("word is: " + word);
        System.out.println("k is: " + k);
        System.out.println("expected is: " + expected);
        int minimumDeletions = minimumDeletions(word, k); // Calls your implementation
        System.out.println("minimumDeletions is: " + minimumDeletions);
    }
}
