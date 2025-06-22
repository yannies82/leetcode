package leetcode.string;

import java.util.Arrays;

public class DivideAStringIntoGroupsOfSizeK {

    public static void main(String[] args) {
        check("abcdefghi", 3, 'x', new String[]{"abc", "def", "ghi"});
        check("abcdefghij", 3, 'x', new String[]{"abc", "def", "ghi", "jxx"});
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/divide-a-string-into-groups-of-size-k.
     * Time complexity is O(n) where n is the length of String s.
     *
     * @param s
     * @param k
     * @param fill
     * @return
     */
    public static String[] divideString(String s, int k, char fill) {
        int length = s.length();
        char[] current = new char[k];
        int index = 0;
        int lastCurrentIndex = k - 1;
        String[] result = new String[(int) Math.ceil((double) length / k)];
        for (int i = 0; i < length; i++) {
            int mod = i % k;
            current[mod] = s.charAt(i);
            if (mod == lastCurrentIndex) {
                result[index++] = new String(current);
            }
        }
        int lengthModK = length % k;
        if (lengthModK != 0) {
            for (int i = lengthModK; i < k; i++) {
                current[i] = fill;
            }
            result[index] = new String(current);
        }
        return result;
    }

    private static void check(String s, int k, char fill, String[] expected) {
        System.out.println("s is: " + s);
        System.out.println("k is: " + k);
        System.out.println("fill is: " + fill);
        System.out.println("expected is: " + Arrays.toString(expected));
        String[] divideString = divideString(s, k, fill); // Calls your implementation
        System.out.println("divideString is: " + Arrays.toString(divideString));
    }
}
