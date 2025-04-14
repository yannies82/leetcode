package leetcode.array;

import java.util.Arrays;

public class CountGoodTriplets {

    public static void main(String[] args) {
        check(new int[] {3,0,1,1,9,7}, 7,2,3, 4);
        check(new int[] {1,1,2,2,3}, 0,0,1, 0);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/count-good-triplets.
     * Time complexity is O(n^3) where n is the length of the arr array.
     *
     *
     * @param arr
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static int countGoodTriplets(int[] arr, int a, int b, int c) {
        int result = 0;
        int limitA = arr.length - 2;
        int limitB = arr.length - 1;
        for (int i = 0; i < limitA; i++) {
            for (int j = i + 1; j < limitB; j++) {
                if (Math.abs(arr[i] - arr[j]) > a) {
                    continue;
                }
                for (int k = j + 1; k < arr.length; k++) {
                    if (Math.abs(arr[j] - arr[k]) <= b && Math.abs(arr[k] - arr[i]) <= c) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    private static void check(int[] arr, int a, int b, int c, int expected) {
        System.out.println("arr is: " + Arrays.toString(arr));
        System.out.println("a is: " + a);
        System.out.println("b is: " + b);
        System.out.println("c is: " + c);
        System.out.println("expected is: " + expected);
        int countGoodTriplets = countGoodTriplets(arr, a, b, c); // Calls your implementation
        System.out.println("countGoodTriplets is: " + countGoodTriplets);
    }
}
