package leetcode.dynamicprogramming;

public class FindTheOriginalTypedString2 {

    public static void main(String[] args) {
        check("aabbccdd", 7, 5);
        check("aabbccdd", 8, 1);
        check("aaabbb", 3, 8);
    }

    private static final int MOD = 1000000007;

    /**
     * Leetcode problem: https://leetcode.com/problems/find-the-original-typed-string-ii.
     * This solution uses dynamic programming. Time complexity is O(n*k).
     *
     * @param word
     * @param k
     * @return
     */
    public static int possibleStringCount(String word, int k) {
        int wordLength = word.length();
        if (wordLength == 0) {
            return 0;
        }
        // keep the count of groups of consecutive characters
        int[] groups = new int[wordLength];
        int groupIndex = 0;
        int count = 1;
        char prev = word.charAt(0);
        for (int i = 1; i < wordLength; i++) {
            char current = word.charAt(i);
            if (current == prev) {
                count++;
            } else {
                groups[groupIndex++] = count;
                count = 1;
            }
            prev = current;
        }
        groups[groupIndex++] = count;
        // count the total number of combinations between all character groups
        long total = 1;
        for (int i = 0; i < groupIndex; i++) {
            total = (total * groups[i]) % MOD;
        }
        // if k is less than the number of character groups, then any combination between groups contains
        // at least k characters
        if (k <= groupIndex) {
            return (int) total;
        }
        // find all possible combinations with up to k-1 characters using dynamic programming
        int[] dp = new int[k];
        dp[0] = 1;
        for (int i = 0; i < groupIndex; i++) {
            int[] newDp = new int[k];
            long sum = 0;
            for (int j = 1; j < k; j++) {
                sum = (sum + dp[j - 1]) % MOD;
                if (j > groups[i]) {
                    sum = (sum - dp[j - groups[i] - 1] + MOD) % MOD;
                }
                newDp[j] = (int) sum;
            }
            dp = newDp;
        }
        long invalid = 0;
        for (int i = groupIndex; i < k; i++) {
            invalid = (invalid + dp[i]) % MOD;
        }
        return (int) ((total - invalid + MOD) % MOD);
    }

    private static void check(String word, int k, int expected) {
        System.out.println("word is: " + word);
        System.out.println("k is: " + k);
        System.out.println("expected is: " + expected);
        int possibleStringCount = possibleStringCount(word, k); // Calls your implementation
        System.out.println("possibleStringCount is: " + possibleStringCount);
    }
}
