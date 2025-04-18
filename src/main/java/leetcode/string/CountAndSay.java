package leetcode.string;

public class CountAndSay {

    public static void main(String[] args) {
        check(4, "1211");
        check(1, "1");
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/count-and-say. Recursive solution.
     * Time complexity is O(n*m) where m is the average length of the rle.
     *
     * @param n
     * @return
     */
    public static String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        return rle(countAndSay(n - 1));
    }

    /**
     * Iterative solution. Time complexity is O(n*m) where m is the average length of the rle.
     *
     * @param n
     * @return
     */
    public static String countAndSay2(int n) {
        String prevResult = "1";
        for (int i = 2; i <= n; i++) {
            prevResult = rle(prevResult);
        }
        return prevResult;
    }

    private static String rle(String s) {
        StringBuilder builder = new StringBuilder();
        char prev = s.charAt(0);
        int start = 0;
        int length = s.length();
        for (int i = 1; i < length; i++) {
            char current = s.charAt(i);
            if (prev != current) {
                builder.append(i - start).append(prev);
                prev = current;
                start = i;
            }
        }
        builder.append(length - start).append(prev);
        return builder.toString();
    }

    private static void check(int n, String expected) {
        System.out.println("n is: " + n);
        System.out.println("expected is: " + expected);
        String countAndSay = countAndSay(n); // Calls your implementation
        System.out.println("countAndSay is: " + countAndSay);
    }
}
