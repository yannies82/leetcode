package leetcode.math;

public class CountTheNumberOfPowerfulIntegers {

    public static void main(String[] args) {
        check(1, 6000, 4, "124", 5);
        check(15, 215, 6, "10", 2);
        check(1000, 2000, 4, "3000", 0);
        check(20, 1159, 5, "20", 8);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/count-the-number-of-powerful-integers.
     * Time complexity is O(n) where n is the length of the numbers start, finish.
     *
     * @param start
     * @param finish
     * @param limit
     * @param s
     * @return
     */
    public static long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        // calculate suffix
        long suffix = Long.parseLong(s);
        // early exit if suffix > finish
        if (suffix > finish) {
            return 0;
        }
        // divide start and finish with 10^n where n is the length of string s
        // so that we get offset values to calculate the result
        long div = (long) Math.pow(10, s.length());
        // add 1 to offsetStart if start % div > suffix
        long offsetStart = start / div + ((suffix - start % div) >>> 63);
        // add 1 to offsetFinish if finish % div >= suffix
        long offsetFinish = finish / div + ((suffix - 1 - finish % div) >>> 63);
        return countNumbers(offsetFinish, limit) - countNumbers(offsetStart, limit);
    }

    /**
     * Calculates how many numbers exist up to num with digits less than or equal to limit.
     *
     * @param num
     * @param limit
     * @return
     */
    private static long countNumbers(long num, long limit) {
        // early exits for edge cases of limit == 0 or limit == 9
        if (num == 0) {
            return 0;
        }
        if (limit == 9) {
            return num;
        }
        int digitsCount = (int) Math.log10(num);
        long div = (long) Math.pow(10, digitsCount);
        long result = 0L;
        for (int i = digitsCount; i >= 0; i--) {
            int digit = (int) (num / div);
            if (digit > limit) {
                return result + (long) Math.pow(limit + 1, i + 1);
            }
            result += digit * (long) Math.pow(limit + 1, i);
            num %= div;
            div /= 10;
        }
        return result;
    }

    /**
     * This solution uses digit dynamic programming. Time complexity is O(n) where n is the length of the numbers start, finish.
     *
     * @param start
     * @param finish
     * @param limit
     * @param s
     * @return
     */
    public static long numberOfPowerfulInt2(long start, long finish, int limit, String s) {
        long countToFinish = countValid(finish, limit, s);
        long countToStart = countValid(start - 1, limit, s);
        return countToFinish - countToStart;
    }

    private static long countValid(long num, int limit, String s) {
        if (num < Long.parseLong(s)) return 0;
        String numStr = Long.toString(num);
        Long[][] dpArray = new Long[numStr.length()][2];
        return dfs(0, true, numStr, limit, s, dpArray);
    }

    private static long dfs(int index, boolean tight, String num, int limit, String suffix, Long[][] dpArray) {
        if (index == num.length()) return 1L;
        int tightIndex = tight ? 1 : 0;
        if (dpArray[index][tightIndex] != null) {
            return dpArray[index][tightIndex];
        }
        long result = 0;
        int maxDigit = tight ? num.charAt(index) - '0' : 9;
        int suffixStart = num.length() - suffix.length();
        if (index >= suffixStart) {
            int suffixIdx = index - suffixStart;
            int digit = suffix.charAt(suffixIdx) - '0';
            if (digit <= maxDigit && digit <= limit)
                result += dfs(index + 1, tight && digit == maxDigit, num, limit, suffix, dpArray);
        } else {
            for (int d = 0; d <= Math.min(maxDigit, limit); d++) {
                result += dfs(index + 1, tight && d == maxDigit, num, limit, suffix, dpArray);
            }
        }
        dpArray[index][tightIndex] = result;
        return result;
    }

    private static void check(long start, long finish, int limit, String s, long expected) {
        System.out.println("start is: " + start);
        System.out.println("finish is: " + finish);
        System.out.println("limit is: " + limit);
        System.out.println("s is: " + s);
        System.out.println("expected is: " + expected);
        long numberOfPowerfulInt = numberOfPowerfulInt(start, finish, limit, s); // Calls your implementation
        System.out.println("numberOfPowerfulInt is: " + numberOfPowerfulInt);
    }
}
