
package chapter_four_使用执行器.任务后续操作;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ExecutableTask implements Callable<String> {

	private String name;

	public String getName() {
		return this.name;
	}

	public ExecutableTask(String name) {
		this.name = name;
	}

	@Override
	public String call() throws Exception {

		try {
			long duration = (long) (Math.random() * 10);
			System.out.printf("%s: Waiting %d seconds for results.\n", this.name, duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
		}
		return "Hello, world. I'm " + name;
	}

}
