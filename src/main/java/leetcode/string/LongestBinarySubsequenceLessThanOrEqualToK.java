package leetcode.string;

public class LongestBinarySubsequenceLessThanOrEqualToK {

    public static void main(String[] args) {
        check("1001010", 5, 5);
        check("00101001", 1, 6);
        check("000101010011011001011101111000111111100001011000000100010000111100000011111001000111100111101001111001011101001011011101001011011001111111010011100011110111010000010000010111001001111101100001111", 300429827, 108);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/longest-binary-subsequence-less-than-or-equal-to-k.
     * This solution counts the number of 0s in the string as well as the number of 1s which sum up to less
     * than k, in order to calculate the length of the final string. Time complexity is O(n) where n is the length
     * of string s.
     *
     * @param s
     * @param k
     * @return
     */
    public static int longestSubsequence(String s, int k) {
        int zeroCount = 0;
        int oneCount = 0;
        int sLength = s.length();
        long sum = 0;
        int lastIndex = sLength - 1;
        boolean exceeded = false;
        // traverse the string characters from last to first (lower to higher order digits)
        for (int i = 0; i < sLength; i++) {
            int current = s.charAt(lastIndex - i) - '0';
            zeroCount += 1 - current;
            if (!exceeded && current == 1) {
                // if the digit is 1 and k has not been exceeded yet, add to sum
                sum += 1L << i;
                if (sum > k) {
                    // set this flag so that higher order digits are not considered at all
                    exceeded = true;
                } else {
                    // sum is within k, this '1' digit can be included
                    oneCount++;
                }
            }
        }
        return oneCount + zeroCount;
    }

    private static void check(String s, int k, int expected) {
        System.out.println("s is: " + s);
        System.out.println("k is: " + k);
        System.out.println("expected is: " + expected);
        int longestSubsequence = longestSubsequence(s, k); // Calls your implementation
        System.out.println("longestSubsequence is: " + longestSubsequence);
    }
}
