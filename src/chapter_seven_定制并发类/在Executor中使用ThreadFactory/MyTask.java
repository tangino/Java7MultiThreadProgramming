package chapter_seven_定制并发类.在Executor中使用ThreadFactory;

import java.util.concurrent.TimeUnit;

public class MyTask implements Runnable {

	@Override
	public void run() {

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
