package leetcode.slidingwindow;

public class NumberOfSubstringsContainingAll3Characters {

    public static void main(String[] args) {
        check("abcabc", 10);
        check("aaacb", 3);
        check("abc", 1);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/number-of-substrings-containing-all-three-characters.
     * This solution uses a sliding window to count all substrings starting at an index and containing all 3
     * characters. Time complexity is O(n) where n is the length of string s.
     *
     * @param s
     * @return
     */
    public static int numberOfSubstrings(String s) {
        byte[] bytes = s.getBytes();
        int[] frequency = new int[128];
        int result = 0;
        int letterCount = 0;
        for (int right = 0, left = 0; right < bytes.length; right++) {
            if (++frequency[bytes[right]] == 1) {
                letterCount++;
            }
            while (letterCount == 3) {
                result += bytes.length - right;
                if (frequency[bytes[left++]]-- == 1) {
                    letterCount--;
                }
            }
        }
        return result;
    }

    private static void check(String s, int expected) {
        System.out.println("s is: " + s);
        System.out.println("expected is: " + expected);
        long numberOfSubstrings = numberOfSubstrings(s); // Calls your implementation
        System.out.println("numberOfSubstrings is: " + numberOfSubstrings);
    }
}
