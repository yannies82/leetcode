package leetcode.math;

public class DistributeCandiesAmongChildren2 {

    public static void main(String[] args) {
        check(3, 2, 7);
        check(5, 2, 3);
        check(3, 3, 10);
        check(5, 2, 3);
        check(1, 2, 3);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/distribute-candies-among-children-ii.
     *
     * @param n
     * @param limit
     * @return
     */
    public static long distributeCandies(int n, int limit) {
        return totalWays(n) - 3 * totalWays(n - (limit + 1)) + 3 * totalWays(n - ((limit + 1) << 1))
                - totalWays(n - 3 * (limit + 1));
    }

    private static long totalWays(long sum) {
        if (sum < 0) return 0;
        return ((sum + 2) * (sum + 1)) >>> 1;
    }

    public static long distributeCandies2(int n, int limit) {
        // calculate the max candies per child
        int max = Math.min(n, limit);
        long total = 0;
        for (int i = 0; i <= max; i++) {
            total += Math.max(Math.min(limit, n - i) - Math.max(0, n - i - limit) + 1, 0);
        }
        return total;
    }

    private static void check(int n, int limit, long expected) {
        System.out.println("n is: " + n);
        System.out.println("limit is: " + limit);
        System.out.println("expected is: " + expected);
        long distributeCandies = distributeCandies(n, limit); // Calls your implementation
        System.out.println("distributeCandies is: " + distributeCandies);
    }
}
