package chapter_four_使用执行器.运行多个任务并处理所有结果;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorInvokeAllDemo {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();

		List<Task> taskList = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			Task task = new Task("Task " + i);
			taskList.add(task);
		}

		List<Future<Result>> resultList = null;

		try {
			// invokeAll方法当所有任务结束，返回一个Future结果集
			resultList = executor.invokeAll(taskList);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		executor.shutdown();

		System.out.println("Main: Printing the results");
		for (int i = 0; i < resultList.size(); i++) {
			Future<Result> future = resultList.get(i);

			try {
				Result result = future.get();
				System.out.println(result.getName() + ": " + result.getValue());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
}
