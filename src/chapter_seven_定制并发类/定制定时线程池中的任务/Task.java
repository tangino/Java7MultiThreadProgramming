package chapter_seven_定制并发类.定制定时线程池中的任务;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

	@Override
	public void run() {
		System.out.printf("Task: Begin.\n");

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Task: End.");
	}

}
