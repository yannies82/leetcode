package leetcode.bitmanipulation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BitwiseOrsOfSubarrays {

    public static void main(String[] args) {
        check(new int[]{0}, 1);
        check(new int[]{1, 1, 2}, 3);
        check(new int[]{1, 2, 4}, 6);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/bitwise-ors-of-subarrays.
     * This solution modifies the input array. Worst case time complexity is O(n^2) but average
     * case is much faster.
     *
     * @param arr
     * @return
     */
    public static int subarrayBitwiseORs(int[] arr) {
        Set<Integer> result = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            result.add(arr[i]);
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] == (arr[j] | arr[i])) {
                    break;
                }
                arr[j] |= arr[i];
                result.add(arr[j]);
            }
        }
        return result.size();
    }

    /**
     * This solution keeps currentOrs for subarrays ending at the current element. Time complexity is O(n*32)
     * where n is the length of the arr array. This is because the currentOrs set may only have at most 32 elements.
     *
     * @param arr
     * @return
     */
    public static int subarrayBitwiseORs2(int[] arr) {
        Set<Integer> resultOrs = new HashSet<>();
        Set<Integer> currentOrs = new HashSet<>();
        for (int num : arr) {
            Set<Integer> newOrs = new HashSet<>();
            newOrs.add(num);
            resultOrs.add(num);
            for (int currentOr : currentOrs) {
                int newOr = currentOr | num;
                newOrs.add(newOr);
                resultOrs.add(newOr);
            }
            currentOrs = newOrs;
        }
        return resultOrs.size();
    }

    private static void check(int[] arr, int expected) {
        System.out.println("arr is: " + Arrays.toString(arr));
        System.out.println("expected is: " + expected);
        int subarrayBitwiseORs = subarrayBitwiseORs(arr); // Calls your implementation
        System.out.println("subarrayBitwiseORs is: " + subarrayBitwiseORs);
    }

}
