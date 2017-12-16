package chapter_seven_定制并发类.实现ThreadFactory接口;

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
