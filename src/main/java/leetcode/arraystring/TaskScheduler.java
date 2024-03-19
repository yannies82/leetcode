package leetcode.arraystring;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TaskScheduler {

	public static void main(String[] args) {
		check(new char[] { 'A', 'A', 'A', 'B', 'B', 'B' }, 2, 8);
		check(new char[] { 'A', 'C', 'A', 'B', 'D', 'B' }, 1, 6);
		check(new char[] { 'A', 'A', 'A', 'B', 'B', 'B' }, 3, 10);
	}

	/**
	 * Leetcode problem: https://leetcode.com/problems/task-scheduler. This solution
	 * keeps an array with the counts per task type. It then sorts this array and
	 * calculates the idle time between the executions of the most common task. It
	 * then tries to fill these gaps with executions of the other tasks. Time
	 * complexity is O(m) where m is the length of the tasks array.
	 * 
	 * @param tasks
	 * @param n
	 * @return
	 */
	public static int leastInterval(char[] tasks, int n) {
		// keeps count of each task
		int[] tasksCount = new int[26];
		for (char task : tasks) {
			tasksCount[task - 'A']++;
		}
		// sorts array so that tasks with less occurrences are first and
		// tasks with most occurrences are last
		Arrays.sort(tasksCount);
		// calculate the maximum idle time as a function of the max task count
		// and the cooldown time
		int maxTaskCount = tasksCount[25];
		int numberOfCooldownIntervals = maxTaskCount - 1;
		int idleTime = numberOfCooldownIntervals * n;
		// try to reduce the idle time by executing the other tasks between
		// executions of the task with max count
		for (int i = 24; i >= 0 && tasksCount[i] > 0 && idleTime > 0; i--) {
			// a task can be executed at most numberOfCooldownIntervals times
			// between executions of the task with max count
			idleTime -= Math.min(numberOfCooldownIntervals, tasksCount[i]);
		}
		// the final result will be the sum of the number of tasks
		// plus the idle time which cannot be less than 0
		return tasks.length + Math.max(idleTime, 0);
	}

	public static int leastInterval2(char[] tasks, int n) {
		int[] tasksCount = new int[26];
		Set<Integer> distinctTasks = new HashSet<>();
		for (int i = 0; i < tasks.length; i++) {
			int task = tasks[i] - 'A';
			tasksCount[task]++;
			distinctTasks.add(task);
		}
		int[] coolDownCount = new int[26];
		int intervals = 0;
		while (!distinctTasks.isEmpty()) {
			Integer selectedTask = null;
			int maxTasksLeft = 0;
			for (int task : distinctTasks) {
				if (coolDownCount[task] == 0) {
					if (selectedTask == null || tasksCount[task] > maxTasksLeft) {
						selectedTask = task;
						maxTasksLeft = tasksCount[task];
					}
				} else {
					coolDownCount[task]--;
				}
			}
			if (selectedTask != null) {
				int task = selectedTask;
				coolDownCount[task] += n;
				if (--tasksCount[task] == 0) {
					distinctTasks.remove(selectedTask);
				}
			}
			intervals++;
		}
		return intervals;
	}

	private static void check(char[] tasks, int n, int expected) {
		System.out.println("tasks is: " + Arrays.toString(tasks));
		System.out.println("n is: " + n);
		System.out.println("expected is: " + expected);
		int leastInterval = leastInterval(tasks, n); // Calls your implementation
		System.out.println("leastInterval is: " + leastInterval);
	}
}
