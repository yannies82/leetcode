package leetcode.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestPalindromeByConcatenatingTwoLetterWords {

    public static void main(String[] args) {
        check(new String[]{"em", "pe", "mp", "ee", "pp", "me", "ep", "em", "em", "me"}, 14);
        check(new String[]{"dd", "aa", "bb", "dd", "aa", "dd", "bb", "dd", "aa", "cc", "bb", "cc", "dd", "cc"}, 22);
        check(new String[]{"lc", "cl", "gg"}, 6);
        check(new String[]{"ab", "ty", "yt", "lc", "cl", "ab"}, 8);
        check(new String[]{"cc", "ll", "xx"}, 2);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words.
     *
     * @param words
     * @return
     */
    public static int longestPalindrome(String[] words) {
        int result = 0;
        // keeps occurrences of character pairs
        int[][] pairs = new int[26][26];
        for (String word : words) {
            int first = word.charAt(0) - 'a';
            int second = word.charAt(1) - 'a';
            if (pairs[second][first] > 0) {
                // palindrome pair exists, add 4 to result and subtract from palindrome count
                pairs[second][first]--;
                result += 4;
            } else {
                // palindrome pair does not exist (yet), add to word count
                pairs[first][second]++;
            }
        }
        // check if the remaining words contain a word with two same characters, which can be added in the middle of
        // the palindrome string
        for (int i = 0; i < 26; i++)
            if (pairs[i][i] > 0) {
                result += 2;
                break;
            }
        return result;
    }

    public static int longestPalindrome2(String[] words) {
        Map<String, Integer> countMap = new HashMap<>();
        for (String word : words) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }
        int offset = 0;
        int result = 0;
        for (String word : words) {
            char first = word.charAt(0);
            char second = word.charAt(1);
            String reversed = new String(new char[]{second, first});
            int count = countMap.get(word);
            Integer reversedCount = countMap.get(reversed);
            if (count > 0 && reversedCount != null) {
                if (first == second) {
                    if (reversedCount > 1) {
                        countMap.put(reversed, reversedCount - 2);
                        result += 4;
                    } else {
                        offset = 2;
                    }
                } else if (reversedCount > 0) {
                    countMap.put(reversed, reversedCount - 1);
                    countMap.put(word, countMap.get(word) - 1);
                    result += 4;
                }
            }
        }
        return result + offset;
    }

    private static void check(String[] words, int expected) {
        System.out.println("words is: " + Arrays.toString(words));
        System.out.println("expected is: " + expected);
        int longestPalindrome = longestPalindrome(words); // Calls your implementation
        System.out.println("longestPalindrome is: " + longestPalindrome);
    }
}
