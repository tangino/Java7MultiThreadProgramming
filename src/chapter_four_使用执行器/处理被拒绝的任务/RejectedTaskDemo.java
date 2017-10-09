package chapter_four_使用执行器.处理被拒绝的任务;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


/*
 * 主要知识点：
 * 利用RejectedExecutionHandler接口和ThreadPoolExecutor的setRejectedExecutionHandler方法
 * 设置被拒绝的任务处理器
 * 
 * 当executor的shutdown调用的时候，它并没有马上结束，
 * 而是等待正在运行的任务或者等待执行的任务结束后，才能真正结束。
 * 这期间，我们可以向executor发送任务，但是这些任务会被拒绝。
 */
public class RejectedTaskDemo {

	public static void main(String[] args) {

		RejectedTaskController controller = new RejectedTaskController();

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

		executor.setRejectedExecutionHandler(controller);

		System.out.printf("Main: Starting.\n");

		for (int i = 0; i < 3; i++) {
			RejectedTask task = new RejectedTask("Task" + i);
			executor.submit(task);
		}

		System.out.printf("Main: Shutting down the executor.\n");
		executor.shutdown();

		System.out.printf("Main: sending another Task.\n");
		RejectedTask task = new RejectedTask("RejectedTask");
		executor.submit(task);

		System.out.println("Main: End");
		System.out.printf("Main: End.\n");
	}
}
