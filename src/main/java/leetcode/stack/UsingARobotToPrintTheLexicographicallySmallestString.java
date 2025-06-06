package leetcode.stack;

public class UsingARobotToPrintTheLexicographicallySmallestString {

    public static void main(String[] args) {
        check("zza", "azz");
        check("bac", "abc");
        check("gh", "gh");
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/using-a-robot-to-print-the-lexicographically-smallest-string.
     * This solution simulates the robot behavior by using a stack. Time complexity is O(n) where n is the length of
     * string s.
     *
     * @param s
     * @return
     */
    public static String robotWithString(String s) {
        char[] chars = s.toCharArray();
        int[] frequency = new int[26];
        for (int i = 0; i < chars.length; i++) {
            frequency[chars[i] - 'a']++;
        }
        int smallestChar = -1;
        for (int i = 0; i < 26; i++) {
            if (frequency[i] > 0) {
                smallestChar = i;
                break;
            }
        }
        StringBuilder result = new StringBuilder();
        int[] stack = new int[chars.length];
        int stackIndex = -1;
        for (int i = 0; i < chars.length; i++) {
            int currentChar = chars[i] - 'a';
            stack[++stackIndex] = currentChar;
            frequency[currentChar]--;
            if (currentChar == smallestChar && frequency[currentChar] == 0) {
                while (smallestChar < 25 && frequency[++smallestChar] == 0) ;
            }
            while (stackIndex >= 0 && stack[stackIndex] <= smallestChar) {
                result.append((char) (stack[stackIndex--] + 'a'));
            }
        }
        while (stackIndex >= 0) {
            result.append((char) (stack[stackIndex--] + 'a'));
        }
        return result.toString();
    }

    private static void check(String s, String expected) {
        System.out.println("s is: " + s);
        System.out.println("expected is: " + expected);
        String robotWithString = robotWithString(s); // Calls your implementation
        System.out.println("robotWithString is: " + robotWithString);
    }
}
