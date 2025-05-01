package leetcode.array.queue;

import java.util.Arrays;

public class MaximumNumberOfTasksYouCanAssign {

    public static void main(String[] args) {
        check(new int[]{3, 2, 1}, new int[]{0, 3, 3}, 1, 1, 3);
        check(new int[]{5, 4}, new int[]{0, 0, 0}, 1, 5, 1);
        check(new int[]{10, 15, 30}, new int[]{0, 10, 10, 10, 10}, 3, 10, 2);
    }

    /**
     * Leetcode problem: https://leetcode.com/problems/maximum-number-of-tasks-you-can-assign.
     * This solution uses binary search. Time complexity is O(nlogn+mlogm+log(min(n,m))⋅(n+m)).
     *
     * @param tasks
     * @param workers
     * @param pills
     * @param strength
     * @return
     */
    public static int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(tasks);
        Arrays.sort(workers);
        // use this auxiliary array to keep the workers with greater strength than the one who takes the pill
        int[] boostedWorkers = new int[workers.length];
        int low = 0;
        int high = Math.min(tasks.length, workers.length) + 1;
        int result = 0;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (canAssign(tasks, workers, boostedWorkers, pills, strength, mid)) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return result;
    }

    private static boolean canAssign(int[] tasks, int[] workers, int[] boostedWorkers, int pills, int strength, int taskCount) {
        int head = 0;
        int tail = -1;
        int workersIndex = workers.length - 1;
        int freePills = pills;
        for (int tasksIndex = taskCount - 1; tasksIndex >= 0; tasksIndex--) {
            int task = tasks[tasksIndex];
            if (head <= tail && boostedWorkers[head] >= task) {
                head++;
            } else if (workersIndex >= 0 && workers[workersIndex] >= task) {
                workersIndex--;
            } else {
                // none of the remaining workers can complete this task
                if (freePills == 0) {
                    // there are no pills left, workers cannot be boosted
                    return false;
                }
                // add to the queue all workers who can complete the task when boosted with a pill
                int limit = task - strength;
                while (workersIndex >= 0 && workers[workersIndex] >= limit) {
                    boostedWorkers[++tail] = workers[workersIndex--];
                }
                if (head > tail) {
                    // no worker can complete the task even when boosted
                    return false;
                }
                // get the boosted worker with the least strength to complete the task
                tail--;
                freePills--;
            }
        }
        return true;
    }

    private static void check(int[] tasks, int[] workers, int pills, int strength, int expected) {
        System.out.println("tasks is: " + Arrays.toString(tasks));
        System.out.println("workers is: " + Arrays.toString(workers));
        System.out.println("pills is: " + pills);
        System.out.println("strength is: " + strength);
        System.out.println("expected is: " + expected);
        int maxTaskAssign = maxTaskAssign(tasks, workers, pills, strength); // Calls your implementation
        System.out.println("maxTaskAssign is: " + maxTaskAssign);
    }
}
