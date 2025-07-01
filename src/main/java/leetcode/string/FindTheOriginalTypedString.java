package leetcode.string;

public class FindTheOriginalTypedString {

    public static void main(String[] args) {
        check("abbcccc", 5);
        check("abcd", 1);
        check("aaaa", 4);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/find-the-original-typed-string-i.
     * Time complexity is O(n) where n is the length of string word.
     *
     * @param word
     * @return
     */
    public static int possibleStringCount(String word) {
        int result = 1;
        int wordLength = word.length();
        char prev = word.charAt(0);
        for (int i = 1; i < wordLength; i++) {
            char current = word.charAt(i);
            if (current == prev) {
                result++;
            }
            prev = current;
        }
        return result;
    }

    private static void check(String word, int expected) {
        System.out.println("word is: " + word);
        System.out.println("expected is: " + expected);
        int possibleStringCount = possibleStringCount(word); // Calls your implementation
        System.out.println("possibleStringCount is: " + possibleStringCount);
    }
}
