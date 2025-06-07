package leetcode.stack;

import java.util.*;

public class LexicographicallyMinimumStringAfterRemovingStars {

    public static void main(String[] args) {
        check("e*d*", "");
        check("aaba*", "aab");
        check("abc", "abc");
        check("gh", "gh");
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/lexicographically-minimum-string-after-removing-stars.
     * This solution uses a priority queue to track the current smallest char and a stack per character to
     * keep track of the indexes. Time complexity is O(nlogn) where n is the length of string s.
     *
     * @param s
     * @return
     */
    public static String clearStars(String s) {
        Queue<Character> priorityQueue = new PriorityQueue<>();
        Map<Character, Deque<Integer>> indexesMap = new HashMap<>();
        char[] sChars = s.toCharArray();
        boolean[] markedForRemoval = new boolean[sChars.length];
        for (int i = 0; i < sChars.length; i++) {
            char currentChar = sChars[i];
            if (currentChar == '*') {
                Character smallestChar = priorityQueue.poll();
                Integer lastIndex = indexesMap.get(smallestChar).removeLast();
                markedForRemoval[i] = true;
                markedForRemoval[lastIndex] = true;
            } else {
                priorityQueue.add(currentChar);
                indexesMap.computeIfAbsent(currentChar, k -> new ArrayDeque<>()).offer(i);
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < sChars.length; i++) {
            if (!markedForRemoval[i]) {
                builder.append(sChars[i]);
            }
        }
        return builder.toString();
    }

    /**
     * This solution checks for the smallest character whenever a * is encountered and removes both.
     * Time complexity is O(n^2) where n is the length of string s.
     *
     * @param s
     * @return
     */
    public static String clearStars2(String s) {
        StringBuilder builder = new StringBuilder(s);
        int[] frequency = new int[26];
        int currentIndex = 0;
        int builderLength = builder.length();
        while (currentIndex < builderLength) {
            char currentChar = builder.charAt(currentIndex);
            if (currentChar == '*') {
                int smallestChar = 0;
                while (frequency[smallestChar] == 0) {
                    smallestChar++;
                }
                int smallestCharIndex = currentIndex;
                char smallest = (char) (smallestChar + 'a');
                while (builder.charAt(--smallestCharIndex) != smallest) ;
                builder.deleteCharAt(currentIndex);
                builder.deleteCharAt(smallestCharIndex);
                frequency[smallestChar]--;
                builderLength -= 2;
                currentIndex--;
            } else {
                frequency[currentChar - 'a']++;
                currentIndex++;
            }
        }
        return builder.toString();
    }

    private static void check(String s, String expected) {
        System.out.println("s is: " + s);
        System.out.println("expected is: " + expected);
        String clearStars = clearStars(s); // Calls your implementation
        System.out.println("clearStars is: " + clearStars);
    }
}
