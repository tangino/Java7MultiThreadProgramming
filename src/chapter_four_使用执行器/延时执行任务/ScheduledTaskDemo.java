package chapter_four_使用执行器.延时执行任务;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledTaskDemo {

	public static void main(String[] args) {
		ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);

		System.out.printf("Main: starting at : %s\n", new Date());

		List<ScheduledFuture<String>> resultList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			ScheduledTask task = new ScheduledTask("Task " + i);
			ScheduledFuture<String> result = executor.schedule(task, i + 1, TimeUnit.SECONDS);
			resultList.add(result);
		}

		/*
		 * 当shutdown被调用的时候，默认的行为是如果仍有待处理的任务需要执行，继续执行剩下的任务。
		 * 可以使用setExecuteExistingDelayedTaskAfterShutdownPolicy方法改变这个行为。
		 * 如果传递false参数，则shutdown调用后，待处理的任务将不被执行
		 */
		executor.shutdown();

		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i<resultList.size(); i++){
			ScheduledFuture<String> finalResult = resultList.get(i);
			try {
				System.out.println("Result of task " + i + ": " + finalResult.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		System.out.printf("Main: Ends at : %s\n", new Date());
	}

}
