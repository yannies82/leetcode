package leetcode.dynamicprogramming;

import java.util.Arrays;
import java.util.List;

public class LongestUnequalAdjacentGroupsSubsequence2 {

    public static void main(String[] args) {
        check(new String[]{"bab", "dab", "cab"}, new int[]{1, 2, 2}, List.of("bab", "dab"));
        check(new String[]{"a", "b", "c", "d"}, new int[]{1, 2, 3, 4}, List.of("a", "b", "c", "d"));
        check(new String[]{"bdb", "aaa", "ada"}, new int[]{2, 1, 3}, List.of("aaa", "ada"));
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-ii.
     * This solution uses bottom up dynamic programming.
     * Time complexity is O(m*n^2) where m is the average word length and n is the length of the words array.
     *
     * @param words
     * @param groups
     * @return
     */
    public static List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
        // convert strings to char arrays for faster comparisons
        char[][] chars = new char[words.length][];
        for (int i = 0; i < words.length; i++) {
            chars[i] = words[i].toCharArray();
        }
        // keeps length of longest subsequence ending at i
        int[] dpArray = new int[words.length];
        Arrays.fill(dpArray, 1);
        // keeps the index of the last word which belongs to the longest subsequence ending at i
        int[] maxIndexes = new int[words.length];
        for (int i = 1; i < words.length; i++) {
            for (int j = 0; j < i; j++) {
                if (dpArray[j] >= dpArray[i] && groups[i] != groups[j] && chars[i].length == chars[j].length
                        && hammingDistance(chars, i, j) == 1) {
                    dpArray[i] = dpArray[j] + 1;
                    maxIndexes[i] = j;
                }
            }
        }
        // find the longest subsequence
        int max = dpArray[0];
        int maxIndex = 0;
        for (int i = 1; i < dpArray.length; i++) {
            if (dpArray[i] > max) {
                max = dpArray[i];
                maxIndex = i;
            }
        }
        // build the longest subsequence by using the maxIndexes array
        String[] result = new String[max];
        for (int i = max - 1; i >= 0; i--) {
            result[i] = words[maxIndex];
            maxIndex = maxIndexes[maxIndex];
        }
        return Arrays.asList(result);
    }

    private static int hammingDistance(char[][] chars, int i, int j) {
        int result = 0;
        for (int k = 0; k < chars[i].length && result < 2; k++) {
            if (chars[i][k] != chars[j][k]) {
                result++;
            }
        }
        return result;
    }

    private static void check(String[] words, int[] groups, List<String> expected) {
        System.out.println("words is: " + Arrays.toString(words));
        System.out.println("groups is: " + Arrays.toString(groups));
        System.out.println("expected is: " + expected);
        List<String> getLongestSubsequence = getWordsInLongestSubsequence(words, groups); // Calls your implementation
        System.out.println("getWordsInLongestSubsequence is: " + getLongestSubsequence);
    }
}
