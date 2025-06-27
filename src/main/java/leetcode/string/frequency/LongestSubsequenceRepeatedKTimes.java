package leetcode.string.frequency;

import java.util.ArrayDeque;
import java.util.Queue;

public class LongestSubsequenceRepeatedKTimes {

    public static void main(String[] args) {
        check("letsleetcode", 2, "let");
        check("bb", 2, "b");
        check("ab", 2, "");
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/longest-subsequence-repeated-k-times.
     * This solution brute forces trying all subsequences in ascending lexicographical order.
     * Whenever a valid subsequence is found, it is added to the queue so that subsequences
     * starting from it are also tested.
     *
     * @param s
     * @param k
     * @return
     */
    public static String longestSubsequenceRepeatedK(String s, int k) {
        char[] result = new char[]{};
        char[] sChars = s.toCharArray();
        // count frequency of each char
        char[] frequency = new char[128];
        for (int i = 0; i < sChars.length; i++) {
            frequency[sChars[i]]++;
        }
        // only chars with frequency >= k can be candidates
        char[] candidates = new char[26];
        int candidatesLength = 0;
        for (char i = 'a'; i <= 'z'; i++) {
            if (frequency[i] >= k) {
                candidates[candidatesLength++] = i;
            }
        }
        // keeps only valid subsequences
        Queue<char[]> queue = new ArrayDeque<>();
        queue.offer(result);
        char[] chars = new char[sChars.length / k + 1];
        while (!queue.isEmpty()) {
            char[] currentChars = queue.poll();
            System.arraycopy(currentChars, 0, chars, 0, currentChars.length);
            int nextLength = currentChars.length + 1;
            for (int i = 0; i < candidatesLength; i++) {
                char ch = candidates[i];
                chars[currentChars.length] = ch;
                if (isRepeatedKTimes(sChars, k, chars, nextLength)) {
                    char[] resultChars = new char[nextLength];
                    System.arraycopy(chars, 0, resultChars, 0, nextLength);
                    result = resultChars;
                    queue.add(resultChars);
                }
            }
        }
        return new String(result);
    }

    private static boolean isRepeatedKTimes(char[] sChars, int k, char[] tChars, int tLength) {
        int count = 0;
        int tIndex = 0;
        for (char sChar : sChars) {
            if (sChar == tChars[tIndex]) {
                if (++tIndex == tLength) {
                    // found a full subsequence of t in s, increase count and reset tIndex
                    tIndex = 0;
                    if (++count == k) {
                        // this is the kth subsequence of t in s, return true
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static void check(String s, int k, String expected) {
        System.out.println("s is: " + s);
        System.out.println("k is: " + k);
        System.out.println("expected is: " + expected);
        String longestSubsequenceRepeatedK = longestSubsequenceRepeatedK(s, k); // Calls your implementation
        System.out.println("longestSubsequenceRepeatedK is: " + longestSubsequenceRepeatedK);
    }
}
