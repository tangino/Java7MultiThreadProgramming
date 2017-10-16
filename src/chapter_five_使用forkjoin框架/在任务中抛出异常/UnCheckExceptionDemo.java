package chapter_five_使用forkjoin框架.在任务中抛出异常;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class UnCheckExceptionDemo {

	public static void main(String[] args) {

		int array[] = new int[100];

		Task task = new Task(array, 0, 100);

		ForkJoinPool pool = new ForkJoinPool();

		pool.execute(task);

		pool.shutdown();

		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 检查任务是否正常终止
		if (task.isCompletedAbnormally()) {
			System.out.printf("Main: An exception has ocurred\n");
			// 对于非正常终止的任务通过getException可以获取抛出的异常
			System.out.printf("Main: %s\n", task.getException());
		}

		System.out.printf("Main: Result: %d\n", task.join());
	}

}
