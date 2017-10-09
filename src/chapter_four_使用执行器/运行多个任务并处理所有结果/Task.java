package chapter_four_使用执行器.运行多个任务并处理所有结果;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Task implements Callable<Result> {

	private String name;

	public Task(String name) {
		this.name = name;
	}

	@Override
	public Result call() throws Exception {

		try {
			long duration = (long) (Math.random() * 10);
			System.out.printf("%s: Waiting %d seconds for result.\n", this.name, duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int value = 0;

		for (int i = 0; i < 5; i++) {
			value += (int) (Math.random() * 100);
		}

		Result result = new Result();
		result.setName(this.name);
		result.setValue(value);

		System.out.println(this.name + ": Ends");
		return result;
	}

}
