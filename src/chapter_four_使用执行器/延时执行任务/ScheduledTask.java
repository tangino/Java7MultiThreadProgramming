
package chapter_four_使用执行器.延时执行任务;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Callable;

public class ScheduledTask implements Callable<String> {
	private String name;

	public ScheduledTask(String name) {
		this.name = name;
	}

	@Override
	public String call() throws Exception {
		System.out.printf("%s: Starting at : %s\n", name, new Date());
		int result = (new Random()).nextInt(20);
		String retResult = Integer.toString(result);
		return retResult;
	}

}
