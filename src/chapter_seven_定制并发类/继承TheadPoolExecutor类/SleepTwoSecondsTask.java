package chapter_seven_定制并发类.继承TheadPoolExecutor类;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class SleepTwoSecondsTask implements Callable<String> {

	@Override
	public String call() throws Exception {

		TimeUnit.SECONDS.sleep(2);
		return new Date().toString();
	}

}
