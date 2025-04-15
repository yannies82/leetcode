package leetcode.array.fenwick;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CountGoodTripletsInAnArray {

    public static void main(String[] args) {
        check(new int[]{2, 0, 1, 3}, new int[]{0, 1, 2, 3}, 1);
        check(new int[]{4, 0, 1, 3, 2}, new int[]{4, 1, 0, 2, 3}, 4);
        check(new int[]{13, 14, 10, 2, 12, 3, 9, 11, 15, 8, 4, 7, 0, 6, 5, 1},
                new int[]{8, 7, 9, 5, 6, 14, 15, 10, 2, 11, 4, 13, 3, 12, 1, 0}, 77);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/count-good-triplets-in-an-array.
     * This solution uses a Fenwick Tree for efficient insertions at random positions.
     * Time complexity is O(nlogn) where n is the length of the arrays.
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static long goodTriplets(int[] nums1, int[] nums2)
    {
        int n = nums1.length;
        // keep the index of each number in nums2
        int[] index2 = new int[n];
        for (int i = 0; i < n; i++)
        {
            index2[nums2[i]] = i;
        }
        // keeps the mapping of the index of a number in nums2 to its index in nums1
        int[] reversedIndexMapping = new int[n];
        for (int i = 0; i < n; i++)
        {
            reversedIndexMapping[index2[nums1[i]]] = i;
        }
        FenwickTree tree = new FenwickTree(n);
        long result = 0;
        // iterate all indexes of nums1
        for (int i = 0; i < n; i++)
        {
            // find index of the same element in nums2
            int indexInNums2 = reversedIndexMapping[i];
            // find how many numbers have been encountered in nums2 with index < indexInNums2
            int left = tree.query(indexInNums2);
            // find how many elements have been encountered with index > indexInNums2 in both arrays
            int right = (n - 1 - indexInNums2) - (i - left);
            result += (long) left * right;
            // add the index to the tree
            tree.update(indexInNums2, 1);
        }
        return result;
    }

    private static class FenwickTree
    {
        private final int[] tree;

        public FenwickTree(int size)
        {
            this.tree = new int[size + 1];
        }

        public void update(int index, int delta)
        {
            index++;
            while (index < tree.length)
            {
                this.tree[index] += delta;
                index += index & -index;
            }
        }

        public int query(int index)
        {
            index++;
            int res = 0;
            while (index > 0)
            {
                res += this.tree[index];
                index -= index & -index;
            }
            return res;
        }
    }

    /**
     * Similar solution which uses an ArrayList.
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static long goodTriplets2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        // keep the index for each number in nums1
        int[] indexes1 = new int[nums1.length];
        for (int i = 0; i < n; i++) {
            indexes1[nums1[i]] = i;
        }
        long result = 0;
        List<Integer> st = new ArrayList<>();
        for (int num : nums2) {
            // find index of element in nums1
            int index1 = indexes1[num];
            // find how many elements have been encountered in nums1 with index < index1
            int left = leftElements(st, index1);
            // find how many elements exist with index > index1 in both arrays
            int right = (n - 1 - index1) - (st.size() - left);
            result += (long) left * right;
            st.add(left, index1);
        }
        return result;
    }

    private static int leftElements(List<Integer> st, int key) {
        int pos = Collections.binarySearch(st, key);
        return pos < 0 ? -pos - 1 : pos;
    }

    private static void check(int[] nums1, int[] nums2, long expected) {
        System.out.println("nums1 is: " + Arrays.toString(nums1));
        System.out.println("nums2 is: " + Arrays.toString(nums2));
        System.out.println("expected is: " + expected);
        long goodTriplets = goodTriplets(nums1, nums2); // Calls your implementation
        System.out.println("goodTriplets is: " + goodTriplets);
    }
}
