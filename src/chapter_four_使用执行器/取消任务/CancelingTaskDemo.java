package chapter_four_使用执行器.取消任务;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CancelingTaskDemo {

	public static void main(String[] args) {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

		CancelableTask task = new CancelableTask();

		System.out.printf("Main: Executing the task\n");

		Future<String> result = executor.submit(task);

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Main: Canceling the task\n");

		result.cancel(true);

		System.out.printf("Main: Cancelled: %s\n", result.isCancelled());
		System.out.printf("Main: Done: %s\n", result.isDone());

		executor.shutdown();
		System.out.printf("Main: The executor has finished\n");

	}

}
