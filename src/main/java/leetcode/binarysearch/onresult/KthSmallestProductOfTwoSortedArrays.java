package leetcode.binarysearch.onresult;

import java.util.Arrays;

public class KthSmallestProductOfTwoSortedArrays {

    public static void main(String[] args) {
        check(new int[]{2, 5}, new int[]{3, 4}, 2, 8);
        check(new int[]{2, 5}, new int[]{3, 4}, 2, 8);
        check(new int[]{-2, -1, 0, 1, 2}, new int[]{-3, -1, 2, 4, 5}, 3, -6);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/kth-smallest-product-of-two-sorted-arrays.
     * Time complexity is O(logr * n * logm) where r is the range between the smallest and largest product,
     * n is the length of nums1 and m is the length of nums2.
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public static long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        long prod1 = (long) nums1[0] * nums2[0];
        long prod2 = (long) nums1[0] * nums2[nums2.length - 1];
        long prod3 = (long) nums1[nums1.length - 1] * nums2[0];
        long prod4 = (long) nums1[nums1.length - 1] * nums2[nums2.length - 1];
        long left = Math.min(Math.min(prod1, prod2), Math.min(prod3, prod4));
        long right = Math.max(Math.max(prod1, prod2), Math.max(prod3, prod4));
        // find the possible range of the result and perform binary search on it
        while (left < right) {
            long mid = left + ((right - left) >>> 1);
            if (countProducts(nums1, nums2, mid) < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private static long countProducts(int[] nums1, int[] nums2, long target) {
        long count = 0;
        for (int num1 : nums1) {
            if (num1 == 0) {
                // shortcut for 0 if target >= 0, all products will be 0
                if (target >= 0) {
                    count += nums2.length;
                }
                continue;
            }
            int current = findProductsNotLessThanTarget(num1, nums2, target);
            count += (num1 > 0) ? current : nums2.length - current;
        }
        return count;
    }

    private static int findProductsNotLessThanTarget(int num1, int[] nums2, long target) {
        int low = 0, high = nums2.length;
        while (low < high) {
            int mid = (low + high) >>> 1;
            long product = (long) num1 * nums2[mid];
            if (product <= target) {
                if (num1 > 0) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            } else {
                if (num1 > 0) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return low;
    }

    private static void check(int[] nums1, int[] nums2, long k, long expected) {
        System.out.println("nums1 is: " + Arrays.toString(nums1));
        System.out.println("nums2 is: " + Arrays.toString(nums2));
        System.out.println("k is: " + k);
        System.out.println("expected is: " + expected);
        long kthSmallestProduct = kthSmallestProduct(nums1, nums2, k); // Calls your implementation
        System.out.println("kthSmallestProduct is: " + kthSmallestProduct);
    }
}
