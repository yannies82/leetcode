package leetcode.string.frequency;

public class MaximumDifferenceBetweenEvenAndOddFrequency {

    public static void main(String[] args) {
        check("aaaaabbc", 3);
        check("abcabcab", 1);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/maximum-difference-between-even-and-odd-frequency-i.
     * Time complexity is O(n) where n is the length of string s.
     *
     * @param s
     * @return
     */
    public static int maxDifference(String s) {
        int[] frequency = new int[26];
        int sLength = s.length();
        for (int i = 0; i < sLength; i++) {
            frequency[s.charAt(i) - 'a']++;
        }
        int maxOdd = 0;
        int minEven = sLength;
        for (int i = 0; i < 26; i++) {
            if ((frequency[i] & 1) == 1) {
                maxOdd = Math.max(maxOdd, frequency[i]);
            } else if (frequency[i] > 0) {
                minEven = Math.min(minEven, frequency[i]);
            }
        }
        return maxOdd - minEven;
    }

    private static void check(String s, int expected) {
        System.out.println("s is: " + s);
        System.out.println("expected is: " + expected);
        int maxDifference = maxDifference(s); // Calls your implementation
        System.out.println("maxDifference is: " + maxDifference);
    }
}
