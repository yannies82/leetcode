package leetcode.math;

public class FindTheKthCharacterInString {

    public static void main(String[] args) {
        check(5, 'b');
        check(10, 'c');
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/find-the-k-th-character-in-string-game-i.
     * Time complexity is O(logk).
     *
     * @param k
     * @return
     */
    public static char kthCharacter(int k) {
        int result = 0;
        while (k > 1) {
            int logKCeil = 31 - Integer.numberOfLeadingZeros(k);
            int endIndex = 1 << logKCeil;
            if ((endIndex) == k) {
                endIndex >>>= 1;
            }
            k = k - endIndex;
            result++;
        }
        return (char) ('a' + result);
    }

    /**
     * Alternative solution. Time complexity is O(k).
     *
     * @param k
     * @return
     */
    public static char kthCharacter2(int k) {
        char[] chars = new char[k];
        int index = 1;
        while (index < k) {
            int currentIndex = index;
            int endIndex = Math.min(index << 1, k);
            for (int i = currentIndex; i < endIndex; i++) {
                char nextChar = (char) (chars[i - currentIndex] + 1);
                chars[i] = nextChar;
            }
            index = endIndex;
        }
        return (char) ((chars[k - 1] % 26) + 'a');
    }

    private static void check(int k, char expected) {
        System.out.println("k is: " + k);
        System.out.println("expected is: " + expected);
        char kthCharacter = kthCharacter(k); // Calls your implementation
        System.out.println("kthCharacter is: " + kthCharacter);
    }
}
