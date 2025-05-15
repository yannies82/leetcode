package leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestUnequalAdjacentGroupsSubsequence {

    public static void main(String[] args) {
        check(new String[]{"e", "a", "b"}, new int[]{0, 0, 1}, List.of("e", "b"));
        check(new String[]{"a", "b", "c", "d"}, new int[]{1, 0, 1, 1}, List.of("a", "b", "c"));
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-i.
     * Time complexity is O(n) where n is the length of the groups array.
     *
     * @param words
     * @param groups
     * @return
     */
    public static List<String> getLongestSubsequence(String[] words, int[] groups) {
        List<String> result = new ArrayList<>();
        int prev = -1;
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] != prev) {
                result.add(words[i]);
                prev = groups[i];
            }
        }
        return result;
    }

    private static void check(String[] words, int[] groups, List<String> expected) {
        System.out.println("words is: " + Arrays.toString(words));
        System.out.println("groups is: " + Arrays.toString(groups));
        System.out.println("expected is: " + expected);
        List<String> getLongestSubsequence = getLongestSubsequence(words, groups); // Calls your implementation
        System.out.println("getLongestSubsequence is: " + getLongestSubsequence);
    }
}
