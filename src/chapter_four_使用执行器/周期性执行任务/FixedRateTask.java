package chapter_four_使用执行器.周期性执行任务;

import java.util.Date;

public class FixedRateTask implements Runnable{
	
	private String name;
	
	public FixedRateTask(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.printf("%s: Starting at: %s\n", name, new Date());
	}

}
