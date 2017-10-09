package chapter_four_使用执行器.周期性执行任务;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class FixedRateTaskDemo {

	public static void main(String[] args) {

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

		System.out.printf("Main: Startign at: %s\n", new Date());

		FixedRateTask task = new FixedRateTask("Task");

		// 使用executor的scheduleAtFixedRate方法执行周期任务
		ScheduledFuture<?> result = executor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);

		for (int i = 0; i < 10; i++) {
			System.out.printf("Main: Delay: %d\n", result.getDelay(TimeUnit.MILLISECONDS));

			try {
				TimeUnit.MICROSECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		executor.shutdown();

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Main: Finished at : %s\n", new Date());
	}

}
