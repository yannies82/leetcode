package leetcode.string.frequency;

public class TotalCharactersInStringAfterTransformations {

    public static void main(String[] args) {
        check("abcyy", 2, 7);
        check("azbk", 1, 5);
    }

    private static final int MOD = 1000000007;

    /**
     * Leetcode problem: https://leetcode.com/problems/total-characters-in-string-after-transformations-i.
     * Time complexity is O(n+t/26) where n is the length of the s string.
     *
     * @param s
     * @param t
     * @return
     */
    public static int lengthAfterTransformations(String s, int t) {
        // count frequency of each character in string
        long[] frequency = new long[26];
        for (int c : s.toCharArray()) {
            frequency[c - 'a']++;
        }
        while (t >= 26) {
            // after 26 transformations: a -> a, z -> ab therefore z -> a
            frequency[0] = (frequency[0] + frequency[25]) % MOD;
            // on every 26 transformations all character will have been doubled
            for (int i = 25; i > 0; i--) {
                frequency[i] = (frequency[i] + frequency[i - 1]) % MOD;
            }
            t -= 26;
        }
        // count the total number of characters in the result
        int result = 0;
        for (int i = 0; i < 26; i++) {
            result = (int) ((result + frequency[i]) % MOD);
        }
        // the remaining transformations will increase the result only for the characters which
        // will be doubled
        for (int i = 26 - t; i < 26; i++) {
            result = (int) ((result + frequency[i]) % MOD);
        }
        return result;
    }

    public static int lengthAfterTransformations2(String s, int t) {
        // count frequency of each character in string
        int[] frequency = new int[26];
        for (char c : s.toCharArray()) {
            frequency[c - 'a']++;
        }
        for (int i = 0; i < t; i++) {
            int zFrequency = frequency[25];
            // on every transformation the frequency of all characters from b to z becomes the same
            // as the frequency of their previous character
            for (int j = 24; j >= 0; j--) {
                frequency[j + 1] = frequency[j];
            }
            // frequency of a becomes equal to previous frequency of z since z -> ab
            frequency[0] = zFrequency % MOD;
            // frequency of b becomes equal to previous frequency of a + previous frequency of z since z -> ab
            frequency[1] = (frequency[1] + zFrequency) % MOD;
        }
        // count the total number of characters in the result
        long result = 0;
        for (int freq : frequency) {
            result = (result + freq) % MOD;
        }
        return (int) result;
    }

    private static void check(String s, int t, int expected) {
        System.out.println("s is: " + s);
        System.out.println("t is: " + t);
        System.out.println("expected is: " + expected);
        int lengthAfterTransformations = lengthAfterTransformations(s, t); // Calls your implementation
        System.out.println("lengthAfterTransformations is: " + lengthAfterTransformations);
    }
}
