package chapter_four_使用执行器.分离任务的启动和结果的处理;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ReportGenerator implements Callable<String> {

	private String sender;

	private String title;

	public ReportGenerator(String sender, String title) {
		this.sender = sender;
		this.title = title;
	}

	@Override
	public String call() throws Exception {

		try {
			long duration = (long) (Math.random() * 10);
			System.out.printf("%s_%s: ReportGenerator: Generating a report during %d seconds\n", this.sender,
					this.title, duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String ret = sender + ": " + title;
		return ret;
	}

}
