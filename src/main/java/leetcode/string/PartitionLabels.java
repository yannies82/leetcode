package leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {

    public static void main(String[] args) {
        check("ababcbacadefegdehijhklij", List.of(9, 7, 8));
        check("eccbbbbdec", List.of(10));
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/partition-labels.
     * This solution keeps the last occurence of each character in the string in order to perform
     * the requested partitioning. Time complexity is O(n) where n is the length of string s.
     *
     * @param s
     * @return
     */
    public static List<Integer> partitionLabels(String s) {
        int[] lastIndex = new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            lastIndex[chars[i] - 'a'] = i;
        }
        List<Integer> result = new ArrayList<>();
        byte[] exists = new byte[26];
        int totalCount = 0;
        int count = 0;
        int prevSplitIndex = -1;
        for (int i = 0; i < chars.length; i++) {
            int charIndex = chars[i] - 'a';
            totalCount += 1 - exists[charIndex];
            exists[charIndex] = 1;
            count += 1 - ((i - lastIndex[charIndex]) >>> 31);
            if (count == totalCount) {
                result.add(i - prevSplitIndex);
                prevSplitIndex = i;
            }
        }
        return result;
    }

    private static void check(String partitionLabels, List<Integer> expected) {
        System.out.println("partitionLabels is: " + partitionLabels);
        System.out.println("expected is: " + expected);
        List<Integer> partitionLabelsResult = partitionLabels(partitionLabels); // Calls your implementation
        System.out.println("partitionLabels is: " + partitionLabelsResult);
    }
}
