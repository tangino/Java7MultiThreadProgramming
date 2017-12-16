package chapter_seven_定制并发类.继承TheadPoolExecutor类;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		MyExecutor myExecutor = new MyExecutor(2, 4, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

		List<Future<String>> results = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			SleepTwoSecondsTask task = new SleepTwoSecondsTask();
			Future<String> result = myExecutor.submit(task);
			results.add(result);
		}

		// 前五个任务的结果
		for (int i = 0; i < 5; i++) {
			try {
				String result = results.get(i).get();
				System.out.printf("Main: Result for Task %d: %s\n", i, result);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		myExecutor.shutdown();

		for (int i = 0; i < 10; i++) {
			try {
				String result = results.get(i).get();
				System.out.printf("Main: Result for Task %d: %s\n", i, result);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		try {
			myExecutor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Main: End of the program.\n");
	}
}
