package leetcode.array;

import java.util.Arrays;

public class TypesOfTriangle {

    public static void main(String[] args) {
        check(new int[]{3, 3, 3}, "equilateral");
        check(new int[]{3, 4, 5}, "scalene");
        check(new int[]{3, 5, 5}, "isosceles");
        check(new int[]{3, 5, 10}, "none");
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/type-of-triangle.
     * Time complexity is O(1).
     *
     * @param nums
     * @return
     */
    public static String triangleType(int[] nums) {
        if (nums[0] == nums[1] && nums[1] == nums[2]) {
            return "equilateral";
        } else if (nums[0] + nums[1] <= nums[2] || nums[1] + nums[2] <= nums[0] || nums[2] + nums[0] <= nums[1]) {
            return "none";
        } else if (nums[0] != nums[1] && nums[1] != nums[2] && nums[2] != nums[0]) {
            return "scalene";
        }
        return "isosceles";
    }

    private static void check(int[] nums, String expected) {
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("expected is: " + expected);
        String triangleType = triangleType(nums); // Calls your implementation
        System.out.println("triangleType is: " + triangleType);
    }
}
