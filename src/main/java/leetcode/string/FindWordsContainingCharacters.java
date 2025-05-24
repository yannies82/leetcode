package leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindWordsContainingCharacters {

    public static void main(String[] args) {
        check(new String[]{"leet", "code"}, 'e', List.of(0, 1));
        check(new String[]{"abc", "bcd", "aaaa", "cbc"}, 'a', List.of(0, 2));
        check(new String[]{"abc", "bcd", "aaaa", "cbc"}, 'z', List.of());
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/find-words-containing-character.
     * Time complexity is O(m*n) where m is the length of the words array and m is the
     * length of each word.
     *
     * @param words
     * @param x
     * @return
     */
    public static List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            int wordLength = words[i].length();
            for (int j = 0; j < wordLength; j++) {
                if (words[i].charAt(j) == x) {
                    result.add(i);
                    break;
                }
            }
        }
        return result;
    }

    private static void check(String[] words, char x, List<Integer> expected) {
        System.out.println("words is: " + Arrays.toString(words));
        System.out.println("x is: " + x);
        System.out.println("expected is: " + expected);
        List<Integer> findWordsContaining = findWordsContaining(words, x); // Calls your implementation
        System.out.println("findWordsContaining is: " + findWordsContaining);
    }
}
